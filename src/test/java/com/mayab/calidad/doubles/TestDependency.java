package com.mayab.calidad.doubles;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
	
	@Test
	public void testAnswer() {
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				return arg+20;
			}
		});
		assertEquals(30, dependency.addTwo(10));
	}
	
	@Test
	public void multiplyTest() {
		when(dependency.multiply(anyInt(), anyInt())).thenAnswer(new Answer<Integer>() {
			public Integer answer(InvocationOnMock invocation) throws Throwable{
				int arg = (Integer) invocation.getArguments()[0];
				int arg2 = (Integer) invocation.getArguments()[1];
				return arg*arg2;
			}
		});
		assertEquals(100, dependency.multiply(10, 10));
	}

}
