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

public class TestStudentDAOMySQLInsert extends DBTestCase {

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
		Student s = new Student("00335486","Fredy Fuzman","00335486@anahuac.mx",21,8.2f);
		DAOMySQL dao = new DAOMySQL();
		
		dao.addStudent(s);
		
		try{
			IDataSet databaseDataSet = getConnection().createDataSet();
			ITable actualTable = databaseDataSet.getTable("students");
			IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("src/resources/insert_result.xml"));
			ITable expectedTable = expectedDataSet.getTable("students");
			Assertion.assertEquals(expectedTable, actualTable);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testGet() {
		Student s = new Student("331754","Alan Zuniga","00331754@anahuac.mx",22,7.8f);
		DAOMySQL dao = new DAOMySQL();
		Student student = dao.getStudent("331754");
		assertTrue(s.equals(student));
	}
	
	@Test
	public void testGetAll() {
		Student s = new Student("335486","Fredy Fuzman","00335486@anahuac.mx",21,8.2f);
		Student student = new Student("331754","Alan Zuniga","00331754@anahuac.mx",22,7.8f);
		HashMap<String, Student> students = new HashMap<String, Student>();
		students.put(student.getId(), student);
		students.put(s.getId(), s);
		DAOMySQL dao = new DAOMySQL();
		assertTrue(dao.getAllStudents().equals(students));
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
