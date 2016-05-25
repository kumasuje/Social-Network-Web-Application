package org.p565.team6.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;

@Document
public class UserModel {
	
	
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
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	public String getBackgroundPicture() {
		return backgroundPicture;
	}
	public void setBackgroundPicture(String backgroundPicture) {
		this.backgroundPicture = backgroundPicture;
	}
	public ArrayList<UserModel> getFriendDetails() {
		return friendDetails;
	}
	public void setFriendDetails(ArrayList<UserModel> friendDetails) {
		this.friendDetails = friendDetails;
	}
	private String profilePicture;
	private String backgroundPicture;
	private ArrayList<UserModel> friendDetails = new ArrayList<UserModel>();
	private ArrayList<AddFriendModel> pendingFriendList = new ArrayList<AddFriendModel>();
	private ArrayList<String> usersFriendList = new ArrayList<String>();
	UserModel(){
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
	public ArrayList<String> getUsersFriendList() {
		return usersFriendList;
	}
	public void setUsersFriendList(ArrayList<String> usersFriendList) {
		this.usersFriendList = usersFriendList;
	}
	
	public ArrayList<AddFriendModel> getPendingFriendList() {
		return pendingFriendList;
	}
	public void setPendingFriendList(ArrayList<AddFriendModel> pendingFriendList) {
		this.pendingFriendList = pendingFriendList;
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
}
