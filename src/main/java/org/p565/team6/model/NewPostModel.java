package org.p565.team6.model;


import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

@Document
public class NewPostModel {

	@Id
	public int index;

	private String newPost;
	private String imagePostedBy;
	private ArrayList<String> postPicString;
	private MultipartFile postPic2;
	private MultipartFile postPic3;
	private MultipartFile postPic4;
	private MultipartFile postPic5;
	
	private String fullName;
	private String email;
	private boolean like = false;
	private int totalLikeCount =0;
	private ArrayList<NameAndComment> comments;

	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public int getTotalLikeCount() {
		return totalLikeCount;
	}

	public void setTotalLikeCount(int totalLikeCount) {
		this.totalLikeCount = totalLikeCount;
	}
	
	public ArrayList<String> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(ArrayList<String> likedBy) {
		this.likedBy = likedBy;
	}

	public ArrayList<String> getPostPicString() {
		return postPicString;
	}

	public void setPostPicString(ArrayList<String> postPicString) {
		this.postPicString = postPicString;
	}

	public String getImagePostedBy() {
		return imagePostedBy;
	}

	public void setImagePostedBy(String imagePostedBy) {
		this.imagePostedBy = imagePostedBy;
	}
	private MultipartFile postPic1;
	public MultipartFile getPostPic1() {
		return postPic1;
	}

	public void setPostPic1(MultipartFile postPic1) {
		this.postPic1 = postPic1;
	}

	public MultipartFile getPostPic2() {
		return postPic2;
	}

	public void setPostPic2(MultipartFile postPic2) {
		this.postPic2 = postPic2;
	}

	public MultipartFile getPostPic3() {
		return postPic3;
	}

	public void setPostPic3(MultipartFile postPic3) {
		this.postPic3 = postPic3;
	}

	public MultipartFile getPostPic4() {
		return postPic4;
	}

	public void setPostPic4(MultipartFile postPic4) {
		this.postPic4 = postPic4;
	}

	public MultipartFile getPostPic5() {
		return postPic5;
	}

	public void setPostPic5(MultipartFile postPic5) {
		this.postPic5 = postPic5;
	}

	public ArrayList<NameAndComment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<NameAndComment> comments) {
		this.comments = comments;
	}

	private ArrayList<String> likedBy;
	private ArrayList<String> accessedBy;
	
	public ArrayList<String> getAccessedBy() {
		return accessedBy;
	}

	public void setAccessedBy(ArrayList<String> accessedBy) {
		this.accessedBy = accessedBy;
	}

	public String getNewPost() {
		return newPost;
	}

	public void setNewPost(String newPost) {
		this.newPost = newPost;
	}
	
}
