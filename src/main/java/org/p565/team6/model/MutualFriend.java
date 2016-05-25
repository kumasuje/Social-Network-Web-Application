package org.p565.team6.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MutualFriend {
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfMutualfriend() {
		return numberOfMutualfriend;
	}
	public void setNumberOfMutualfriend(int numberOfMutualfriend) {
		this.numberOfMutualfriend = numberOfMutualfriend;
	}
	private String emailId;
	private String name;
	private int numberOfMutualfriend;
}
