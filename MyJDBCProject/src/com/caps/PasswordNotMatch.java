package com.caps;

public class PasswordNotMatch extends RuntimeException {
	 @Override
	public String getMessage() {
		return "New Passwords are not Matching";
	}
}