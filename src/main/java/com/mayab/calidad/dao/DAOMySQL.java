package com.mayab.calidad.dao;

import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOMySQL implements DAO {
	
	public DAOMySQL(){
		
	}
	
	public Connection getConnection() {
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/quality?serverTimezone=EST";
        String user = "root";
        String password = "";

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
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id = ?");
			
			ps.setString(1, student.getId());			
			ps.executeUpdate();
			
			con.close();			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public void updateStudentAverage(Student student, Float average) {
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE students SET average = ? WHERE id = ?");

			ps.setFloat(1, average);
			ps.setString(2, student.getId());
			
			ps.executeUpdate();
			
			con.close();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public Student getStudent(String id) {
		Student student = null;
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE id = ?");
			
			ps.setString(1, id);
			
			ResultSet result = ps.executeQuery();
			if(result.next())
				student = new Student(result.getString("id"), result.getString("name"), result.getString("email"), result.getInt("age"), result.getFloat("average"));
			
			con.close();
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return student;
	}

	@Override
	public HashMap<String, Student> getAllStudents() {
		HashMap<String, Student> students = new HashMap<String, Student>();
		Connection con = getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM students");
			
			ResultSet result = ps.executeQuery();
			
			Student student = new Student(result.getString("id"), result.getString("name"), result.getString("email"), result.getInt("age"), result.getFloat("average"));
			students.put(student.getId(), student);
			
			while(result.next()) {
				student = new Student(result.getString("id"), result.getString("name"), result.getString("email"), result.getInt("age"), result.getFloat("average"));
				students.put(student.getId(), student);
			}
			con.close();
			
		} catch(Exception ex){
			ex.printStackTrace();
		}
		return students;
	}

	@Override
	public String getStudentName(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
