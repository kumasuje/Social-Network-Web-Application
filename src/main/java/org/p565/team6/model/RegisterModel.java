package org.p565.team6.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Document
public class RegisterModel {
	
	
	@Indexed(unique=true)
	@Id
	private String emailId;
	
	private String password;
	private String dob;
	private String gender;
	private String securityQuestion1;
	private String securityQuestion2;
	private String fullName;
	private String school;
	private String phoneNumber;
	private String address;
	private MultipartFile profilePic;
	private MultipartFile backgroundPic;
	private String tempPassword;
	
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	RegisterModel(){
		System.out.println("It's in cons");
		
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}
	public void setSecurityQuestion1(String securityQuestion1) {
		this.securityQuestion1 = securityQuestion1;
	}
	
	public String getSecurityQuestion2() {
		return securityQuestion2;
	}
	public void setSecurityQuestion2(String securityQuestion2) {
		this.securityQuestion2 = securityQuestion2;
	}
	

	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public MultipartFile getProfilePic() {
		return profilePic;
	}
	public void setProfilePic(MultipartFile profilePic) {
		this.profilePic = profilePic;
	}
	public MultipartFile getBackgroundPic() {
		return backgroundPic;
	}
	public void setBackgroundPic(MultipartFile backgroundPic) {
		this.backgroundPic = backgroundPic;
	}
}
