package com.mayab.calidad;

public class Password {
	
	private String password;
	
	public Password(String password) {
		verifyPassword(password);
		this.password = password;
	}
	
	private boolean hasUppercase(String str) {
	    char ch;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if (Character.isUpperCase(ch)) {
	        	return true;
	        }
	    }
	    return false;
	}
	
	private boolean hasLowercase(String str) {
	    char ch;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if (Character.isLowerCase(ch)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean hasNumber(String str) {
	    char ch;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if( Character.isDigit(ch)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private boolean hasSymbol(String str) {
		char ch;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if (!Character.isLetter(ch) && !Character.isDigit(ch)) {
	            return true;
	        }
	    }
	    return false;
	}
	
	private void verifyPassword(String password) {
		if (password == "" || password == null) {
			throw new IllegalArgumentException("Password is blank or null");
		}
		if (password.length() < 8) {
			throw new IllegalArgumentException("Password contains less than 8 characters");
		}
		if (password.length() > 12) {
			throw new IllegalArgumentException("Password contains more than 12 characters");
		}
		if (!hasUppercase(password)) {
			throw new IllegalArgumentException("Password does not contain an uppercase");
		}
		if (!hasLowercase(password)) {
			throw new IllegalArgumentException("Password does not contain a lowercase");
		}
		if (!hasNumber(password)) {
			throw new IllegalArgumentException("Password does not contain a number");
		}
		if (!hasSymbol(password)) {
			throw new IllegalArgumentException("Password does not contain a symbol");
		}
	}

}
