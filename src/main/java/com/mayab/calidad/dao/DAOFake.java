package com.mayab.calidad.dao;

import java.util.HashMap;

public class DAOFake implements DAO {
	private HashMap<String,Student> dbStudents = new HashMap<String, Student>();
	
	DAOFake() {
		dbStudents = new HashMap<String, Student>();
	}

	public void addStudent(Student student) {
		dbStudents.put(student.getId(), student);
	}

	public void removeStudent(Student student) {
		// TODO Auto-generated method stub
		dbStudents.remove(student.getId());
	}

	public void updateStudentAverage(Student student, Float promedio) {
		Student a = dbStudents.get(student.getId());
		a.setAverage(promedio);
		dbStudents.put(student.getId(), a);
	}

	public String getStudentName(String id) {
		String result = "";
		if (dbStudents.get(id) != null)
			result = dbStudents.get(id).getName();
		return result;
	}

	public HashMap<String, Student> getAllStudents() {
		return dbStudents;
	}

	public Student getStudent(String id){
		return dbStudents.get(id);
	}
	
}
