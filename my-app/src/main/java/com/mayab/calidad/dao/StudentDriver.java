package com.mayab.calidad.dao;

public class StudentDriver {
	
	public static void main(String[] args) {
		DAOMySQL dao = new DAOMySQL();
		Student student = new Student("00331754","Alan","00331754@anahuac.mx",22,7.8f);
		
		dao.addStudent(student);
	}
}
