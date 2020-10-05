package com.mayab.calidad.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMySQL implements DAO {
	
	DAOMySQL(){
		
	}
	
	public Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/quality?serverTimezone=EST";
        String user = "root";
        String password = "Luneta1803";

        try {
        	con = DriverManager.getConnection(url, user, password);		
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DAOMySQL.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        
        return con;
	}

	@Override
	public void addStudent(Student student) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO students (id,name,email,age,average) values (?,?,?,?,?)");
			
			ps.setString(1, student.getId());
			ps.setString(2, student.getName());
			ps.setString(3, student.getEmail());
			ps.setInt(4, student.getAge());
			ps.setFloat(5, student.getAverage());
			
			ps.executeUpdate();
			
			con.close();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void removeStudent(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateStudentAverage(Student student, Float average) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getStudentName(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
