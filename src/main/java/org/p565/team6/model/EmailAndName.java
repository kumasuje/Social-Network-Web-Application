package org.p565.team6.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EmailAndName {
	
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
