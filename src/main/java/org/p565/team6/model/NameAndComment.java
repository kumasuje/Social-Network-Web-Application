package org.p565.team6.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class NameAndComment {
	
	private String commentText;
	private String commentedBy;
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getCommentedBy() {
		return commentedBy;
	}
	public void setCommentedBy(String commentedBy) {
		this.commentedBy = commentedBy;
	}
	}
