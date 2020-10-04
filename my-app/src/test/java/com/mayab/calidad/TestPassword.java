package com.mayab.calidad;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPassword {

	@Test(expected = IllegalArgumentException.class)
	public void emptyPassword() {
		Password pass = new Password("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void twelveCharsPassword() {
		Password pass = new Password("123456789123");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void oneUppercasePassword() {
		Password pass = new Password("123456789ABC");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void oneLowercasePassword() {
		Password pass = new Password("123456789ABc");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void oneNumberPassword() {
		Password pass = new Password("abcdefghIJK1");
	}
	
	@Test
	public void oneSymbolPassword() {
		Password pass = new Password("123456789aB!");
	}

}
