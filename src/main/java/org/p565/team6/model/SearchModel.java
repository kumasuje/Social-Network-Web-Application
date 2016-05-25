package org.p565.team6.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;

@Document
public class SearchModel {
	
	private String nameSearch;
	private String locationSearch;
	private String schoolSearch;
	private String professionSearch;
	
	SearchModel(){
	}
	public String getNameSearch() {
		return nameSearch;
	}
	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
	public String getLocationSearch() {
		return locationSearch;
	}
	public void setLocationSearch(String locationSearch) {
		this.locationSearch = locationSearch;
	}
	public String getSchoolSearch() {
		return schoolSearch;
	}
	public void setSchoolSearch(String schoolSearch) {
		this.schoolSearch = schoolSearch;
	}
	public String getProfessionSearch() {
		return professionSearch;
	}
	public void setProfessionSearch(String professionSearch) {
		this.professionSearch = professionSearch;
	}
	
}
