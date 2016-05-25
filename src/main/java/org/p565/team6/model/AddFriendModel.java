package org.p565.team6.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AddFriendModel {
	
	private String addFriend;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddFriend() {
		return addFriend;
	}

	public void setAddFriend(String addFriend) {
		this.addFriend = addFriend;
	}
}
