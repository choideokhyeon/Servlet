package com.test.dto;

public class AuthDTO {
	private String email;
	private String grade;
	
	public AuthDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return "AuthDTO [email=" + email + ", grade=" + grade + "]";
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
