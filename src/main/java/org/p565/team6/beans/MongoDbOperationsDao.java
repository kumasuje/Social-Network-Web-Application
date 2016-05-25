package org.p565.team6.beans;

import java.io.IOException;
import java.util.ArrayList;

import org.p565.team6.model.AddFriendModel;
import org.p565.team6.model.MutualFriend;
import org.p565.team6.model.NewPostModel;
import org.p565.team6.model.RegisterModel;
import org.p565.team6.model.SearchModel;
import org.p565.team6.model.UserModel;

public interface MongoDbOperationsDao {

	public Object searchOne(String searchField, String searchValue, String searchCollectionis);
	public Object searchOneUserModel(String searchField, String searchValue, String searchCollectionis);
	public ArrayList<NewPostModel> searchPost(String searchField, ArrayList<String> searchList, String searchCollectionis);
	public Object searchFriend(SearchModel searchModel, String searchCollectionis);	
	public boolean insertOne(Object object,String collectionName);
	public boolean insertOneFile(Object object,String collectionName);
	public ArrayList<MutualFriend> searchOneProfile(String name);
	public Object updateUserModel(AddFriendModel userModel,AddFriendModel currentUser);
	public void findAndRemoveArray(String searchValue,String setSearchValue,String searchFeild,String setSearchFeild,String Collection);
	public void findAndAddToSet(String userModel,String currentUser);
	public void findAndAddToSetObject(String userModel,UserModel currentUser);
	public int findLastValue(String collectinsName);
	public void updateLastValue(String collectinsName , int value);
	public void likeAPost(String userEmail, int postId,String collections);
	public void addComment(String commentText, int index, String commentedBy,String collections);
	public UserModel searchOneUser(String searchField, String searchValue, String searchCollections);
	public void findAndUpdatePass(String emailToBeupdated, String newPass, String FeildToBeUpdate,String Collection);
	
	
	// Suraj's 
	
	public boolean updateOne(String searchField, String searchValue,Object object,String collectionName);

}
