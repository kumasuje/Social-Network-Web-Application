package org.p565.team6.beans;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.p565.team6.model.LastValueModel;
import org.p565.team6.model.NewPostModel;
import org.p565.team6.model.RegisterModel;
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
public class PostAndComment {

	@Autowired
	private LoginValidationAndRegistration registration;
	
	public boolean getAuthentication(RegisterModel registerTemplateModel){
	ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	MongoDbOperationsDao login = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	
	 
	 RegisterModel t1 = (RegisterModel) login.searchOne( "emailId",registerTemplateModel.getEmailId(),"RegisterModel");
	 if( t1 ==  null)
	 {
		 ctx.close();
		 return false;
	 }
	 else{
		 ctx.close(); return (t1.getPassword().equals(registerTemplateModel.getPassword())) ? true : false; 
		 }
	 
	}
	
	public void doPost(NewPostModel post,String collectionName,UserModel userModel){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	 	
	    int lastValue =  register.findLastValue("LastValueModel");
	    
	    
	    if(collectionName.equals("CommonPost")){}
	    else{
	    registration.postPic(post.getPostPic1(),new Integer(lastValue).toString());
	    registration.postPic(post.getPostPic2(),new Integer(lastValue).toString());
	    registration.postPic(post.getPostPic3(),new Integer(lastValue).toString());
	    registration.postPic(post.getPostPic4(),new Integer(lastValue).toString());
	    registration.postPic(post.getPostPic5(),new Integer(lastValue).toString());
	    }
	    post.setPostPic1(null);
	    post.setPostPic2(null);
	    post.setPostPic3(null);
	    post.setPostPic4(null);
	    post.setPostPic5(null);
	    
	    ArrayList<MultipartFile> postPicture = registration.getProfileList(new Integer(lastValue).toString());
		
		MultipartFile profilePicMultiPart = registration.getProfile(userModel.getEmailId()+"_profile");
		String profilePic = null;
		ArrayList<String> temp1 = new ArrayList<String>();
		try {
			profilePic = new String(Base64.encodeBase64(profilePicMultiPart.getBytes()), "UTF-8");
		
			for(int i=0; i < postPicture.size();i++){
			
				String checkTemp = new String(Base64.encodeBase64(postPicture.get(i).getBytes()), "UTF-8");
				if(checkTemp.equals(""))
				{}else{
				temp1.add(new String(Base64.encodeBase64(postPicture.get(i).getBytes()), "UTF-8"));
				}
			}
			post.setPostPicString(temp1);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 	post.setImagePostedBy(profilePic);
	    post.setAccessedBy(userModel.getUsersFriendList());
	 	post.setEmail(userModel.getEmailId());
	 	post.setFullName(userModel.getFullName());
	 	post.setIndex(lastValue+1);
	 	register.insertOne(post,collectionName);
	 	
	 	ctx.close();
	}
	
	public void commentOnPost(String commentText,int index,String commentedBy,String collection){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    register.addComment(commentText,index,commentedBy,collection);
	 	ctx.close();
	}
	
	public void likeIt(String userEmail,int postId,String collections){
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    register.likeAPost(userEmail,postId,collections);
		
	    ctx.close();

	}
	public void updateLastValue(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	 	
	    int lastValue =  register.findLastValue("LastValueModel");
	 	register.updateLastValue("LastValueModel", lastValue);
		
	}
	
	
	public ArrayList<NewPostModel> getPost(ArrayList<String> friendList){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    ArrayList<NewPostModel> t1 = (ArrayList<NewPostModel>)register.searchPost("accessedBy", friendList, "CommonPost");
		ctx.close();
		return t1;
	}
	
	public ArrayList<NewPostModel> getPostForUser(ArrayList<String> friendList, String userEmailD){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
	    MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
	    ArrayList<NewPostModel> t1 = (ArrayList<NewPostModel>)register.searchPost("accessedBy", friendList, "NewPost_"+userEmailD);
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
	
}
