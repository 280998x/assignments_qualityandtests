package com.mayab.dbunit.integration;

import static org.junit.Assert.*;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Vector;

import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mayab.calidad.dao.DAOMySQL;
import com.mayab.calidad.dao.Student;

import junit.framework.Assert;

public class TestStudentDAOMySQLInsert extends DBTestCase {
	
	DAOMySQL dao = new DAOMySQL();

	public TestStudentDAOMySQLInsert(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:mysql://localhost:3306/quality");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "root");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}
	
	@Before
	public void setUp() throws Exception{
		super.setUp();
		IDatabaseConnection con = getConnection();
		con.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
		try {
			DatabaseOperation.CLEAN_INSERT.execute(con, getDataSet());
		} finally {
			con.close();
		}
	}
	
	@Test
	public void testInsert() throws Exception {
		Student s = new Student("338437","Karli Huchis","00338437@anahuac.mx",21,9.4f);
		
		dao.addStudent(s);
		
		Student student = dao.getStudent("338437");
		
		assertTrue(s.equals(student));
	}
	
	@Test
	public void testGet() {
		Student s = new Student("331754","Alan Zuniga","00331754@anahuac.mx",22,7.8f);
		
		Student student = dao.getStudent("331754");
		
		assertTrue(s.equals(student));
	}
	
	@Test
	public void testUpdate() {
		Student s = new Student("335486","Fredy Fuzman","00335486@anahuac.mx",21,8.2f);
		
		dao.updateStudentAverage(s, 9.1f);
		
		Student student = dao.getStudent("335486");
		
		assertEquals(9.1f, student.getAverage());
	}
	
	@Test
	public void testDelete() {
		Student s = new Student("336174","Jose Rasgado","00336174@anahuac.mx",22,8.8f);
		
		dao.removeStudent(s);
		s = dao.getStudent("336174");
		
		assertEquals(null, s);
	}
	
	@After
	public void tearDown() throws Exception{
		
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		InputStream xmlFile = getClass().getResourceAsStream("student_init.xml");
		return new FlatXmlDataSetBuilder().build(new File("src/resources/student_init.xml"));
	}

}
