package com.mayab.calidad.doubles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

public class TestDependency {
	
	private Dependency dependency;

	@Before
	public void setup() {
		dependency = mock(Dependency.class);
		//dependency = new Dependency(new Subdependency());
	}
	
	@Test
	public void test() {
		when(dependency.getClassName()).thenReturn("Alan");
		
		assertThat(dependency.getClassName(), is("Alan"));
		
		//assertNull(dependency.getClassName());
		//assertNull(dependency.getClassNameUppercase());
		//assertNull(dependency.getSubdependencyClassName());
	}
	
	@Test
	public void anotherTest() {
		when(dependency.getClassName()).thenReturn("Alex");
		
		assertThat(dependency.getClassName(), is("Alex"));
	}

}
