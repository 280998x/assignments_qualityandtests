package com.mayab.calidad.dao;

import java.util.HashMap;

public interface DAO {
	public void addStudent(Student student);
	public void removeStudent(Student student);
	public void updateStudentAverage(Student student, Float average);
	public String getStudentName(String id);
	public HashMap<String, Student> getAllStudents();
	public Student getStudent(String id);
}
