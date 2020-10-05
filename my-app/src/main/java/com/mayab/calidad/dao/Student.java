package com.mayab.calidad.dao;

public class Student {
	private String id;
	private String name;
	private String email;
	private Integer age;
	private Float average;

	Student(){
		this.name="";
		this.email="";
		this.age=0;
		this.average=0f;
	}
	
	Student(String id, String name, String email, Integer age, Float average){
		this.setId(id);
		this.setName(name);
		this.setEmail(email);
		this.setAge(age);
		this.setAverage(average);
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public void setAge(Integer age) {
		this.age = age < 0 ? 0 : age;
	}
	
	public void setAverage(Float average) {
		this.average = average;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public Float getAverage() {
		return this.average;
	}
	
	
	
}