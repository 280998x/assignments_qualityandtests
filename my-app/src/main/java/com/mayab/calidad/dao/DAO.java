package com.mayab.calidad.dao;

import java.util.HashMap;

public interface DAO {
	public void addStudent(Student alumno);
	public void removeStudent(Student alumno);
	public void updateStudentAverage(Student alumno, Float promedio);
	public String getStudentName(String id);
	public HashMap<String, Student> getAllStudents();
	public Student getStudent(String id);
}
