package org.p565.team6.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LastValueModel {
	
	private int lastIndexValue;
	public int getLastIndexValue() {
		return lastIndexValue;
	}

	public void setLastIndexValue(int lastIndexValue) {
		this.lastIndexValue = lastIndexValue;
	}
}
