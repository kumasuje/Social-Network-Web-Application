package org.p565.team6.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.p565.team6.model.AddFriendModel;
import org.p565.team6.model.NewPostModel;
import org.p565.team6.model.RegisterModel;
import org.p565.team6.model.SearchModel;
import org.p565.team6.model.UserModel;
import org.p565.team6.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;


@Component
public class SearchFriendAndSuggestion {

	

	public ArrayList<UserModel> getFriendSearchResult(SearchModel searchFriend){
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	MongoDbOperationsDao search = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	ArrayList<UserModel> returnResult = (ArrayList<UserModel>)search.searchFriend(searchFriend, "UserModel");
	ctx.close();
	return returnResult;
	
	}
	
	public Object getModel(String email){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao getmodel = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
		UserModel returnObject = (UserModel)getmodel.searchOne("_id", email, "UserModel");
		ctx.close();
		return returnObject;
	}
	
	public void addFriend(AddFriendModel emailId,UserModel currentUser){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    AddFriendModel temp = new AddFriendModel();
	    temp.setAddFriend(currentUser.getEmailId());
	    temp.setName(currentUser.getFullName());
	 	register.updateUserModel(emailId,temp);
	 	ctx.close();
		
	}
	public void doPost(Object post,String collectionName){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	 	register.insertOne(post,collectionName);
		ctx.close();
	}
	
	public ArrayList<NewPostModel> getPost(ArrayList<String> friendList){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    ArrayList<NewPostModel> t1 = (ArrayList<NewPostModel>)register.searchPost("accessedBy", friendList, "CommonPost");
	    ctx.close();
		return t1;
	}

	
	public void profileCreation(RegisterModel registerTemplateModel){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations  =  ctx.getBean("GridFSOperationDao",GridFSOperationDao.class);
	    try {
			gridOperations.insertOneFile(registerTemplateModel.getProfilePic().getInputStream(), registerTemplateModel.getEmailId()+"_profile");
			gridOperations.insertOneFile(registerTemplateModel.getBackgroundPic().getInputStream(), registerTemplateModel.getEmailId()+"_background");

	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    ctx.close();
	}
	
	public MultipartFile getProfile(String identifer){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations  =  ctx.getBean("GridFSOperationDao",GridFSOperationDao.class);
	    GridFSDBFile gridFsdbFile = gridOperations.searchOneFile(identifer);
		    MultipartFile retrivingFile = null;
		    try {
		    	retrivingFile = new MockMultipartFile(gridFsdbFile.getFilename(),gridFsdbFile.getInputStream());
		    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ctx.close();
		    return retrivingFile;
		
	}
	
	public ArrayList<String> recommendFriends(UserModel currentUser) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao getmodel = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);

		HashSet<String> recommendedFriendsSet = new HashSet<>();

		ArrayList<String> allFriends = (ArrayList<String>) currentUser.getUsersFriendList();
		
		for (int i = 1; i < allFriends.size() - 1; i++) {
			UserModel friend1 = (UserModel) getmodel.searchOneUserModel("_id", allFriends.get(i), "UserModel");

			HashSet<String> friendsList1 = new HashSet<>();
			for (String aFriend : friend1.getUsersFriendList()) {
				friendsList1.add(aFriend);
			}

			for (int j = i + 1; j < allFriends.size(); j++) {
				UserModel friend2 = (UserModel) getmodel.searchOneUserModel("_id", allFriends.get(j), "UserModel");

				for (String aFriend2 : friend2.getUsersFriendList()) {
					if (friendsList1.contains(aFriend2)) {
						recommendedFriendsSet.add(aFriend2);
					}
				}
			}
		}

		ArrayList<String> recommendedFriends = new ArrayList<>();
		int count = 0;
		Iterator<String> iterator = recommendedFriendsSet.iterator();
		while ((count < 5) && (iterator.hasNext())) {
			String aRecommendedFriend = iterator.next();
			if(!allFriends.contains(aRecommendedFriend)){
				recommendedFriends.add(aRecommendedFriend);
				count++;
			}
			iterator.remove();
		}

		for (String suggestedFriend : recommendedFriends) {
			System.out.println(suggestedFriend);
		}
		
		return recommendedFriends;
	}
	
	public ArrayList<UserModel> getRecommendedFriends(UserModel currentUser) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao getmodel = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
		
		System.out.println("Calling recommendFriends()");
		ArrayList<String> recommendedFriendsEmails = recommendFriends(currentUser);
		ArrayList<UserModel> recommendedFriendsUserModels = new ArrayList<>();
		System.out.println("Calling recommendFriends()");
		
		for(String aRecommendedFriend : recommendedFriendsEmails){
			recommendedFriendsUserModels.add((UserModel) getmodel.searchOneUserModel("_id", aRecommendedFriend, "UserModel"));
		}
		
		return recommendedFriendsUserModels;
	}
	
	
	public void rejectFriendRequest(String userEmail, String rejectEmail){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao rejectReq = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    rejectReq.findAndRemoveArray(userEmail,rejectEmail,"_id","pendingFriendList","UserModel");
	    ctx.close();
	}
	
	public void acceptFriendRequest(String userEmail, String acceptEmail){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao acceptReq = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    UserModel acceptor = (UserModel)acceptReq.searchOne("_id",userEmail, "UserModel");
	    UserModel requestor = (UserModel)acceptReq.searchOne("_id",acceptEmail, "UserModel");
	    acceptor.setFriendDetails(null);
	    requestor.setFriendDetails(null);
	    acceptReq.findAndAddToSetObject(userEmail, requestor);
	    acceptReq.findAndAddToSetObject(acceptEmail,acceptor);
	    acceptReq.findAndAddToSet(userEmail, acceptEmail);
	    acceptReq.findAndAddToSet(acceptEmail, userEmail);
	    ctx.close();
	}

	
}
