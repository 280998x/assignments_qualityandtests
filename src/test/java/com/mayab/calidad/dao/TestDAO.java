package com.mayab.calidad.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestDAO {

private DAOFake dao;
	
	@Before
	public void setup(){
		dao = new DAOFake();
	}
	
	@After
	public void tearDown(){
		
	}
	
	@Test
	public void insertData() {
		Student student = new Student("1","nombre", "xx@xx.xxx", 22, 7.0f);
		
		dao.addStudent(student);
		
		String expected = "nombre";
		String result = dao.getStudentName("1");
		
		assertThat(result, is(expected));	
	}
	
	@Test
	public void deleteData(){
		Student student = new Student("1","nombre", "xx@xx.xx", 22, 7.0f);

		dao.addStudent(student);
		dao.removeStudent(student);
		
		String expected = "";
		String result = dao.getStudentName("1");
		assertThat(result, is(expected));
	}
	
	@Test
	public void updateAverage(){
		Student student = new Student("1","nombre", "xx@xx.xx", 22, 7.0f);
		
		dao.addStudent(student);
		dao.updateStudentAverage(student, 5.0f);
		
		Float expected = 5.0f;
		Float result = student.getAverage();
		assertThat(result, is(expected));
	}

}
