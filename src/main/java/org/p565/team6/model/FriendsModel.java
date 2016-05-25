package org.p565.team6.model;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FriendsModel {

	private String userEmail;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	
	
}
