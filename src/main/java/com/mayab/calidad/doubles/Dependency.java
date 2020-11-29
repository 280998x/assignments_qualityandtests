package com.mayab.calidad.doubles;

public class Dependency {
	
	private final Subdependency subdependency;
	
	public Dependency(Subdependency subdependency) {
		super();
		this.subdependency = subdependency;
	}
	
	public String getClassName() {
		return this.getClass().getSimpleName();
	}
	
	public String getSubdependencyClassName() {
		return subdependency.getClassName();
	}
	
	public int addTwo(int i) {
		return i+2;
	}
	
	public int multiply(int i, int j) {
		return i*j;
	}
	
	public String getClassNameUppercase() {
		return getClassName().toUpperCase();
	}

}
