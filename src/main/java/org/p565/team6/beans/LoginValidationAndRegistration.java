package org.p565.team6.beans;

import java.io.IOException;
import java.util.ArrayList;

import org.p565.team6.model.MutualFriend;
import org.p565.team6.model.RegisterModel;
import org.p565.team6.model.UserModel;
import org.p565.team6.model.RegisterModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MultipartFilter;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Component
public class LoginValidationAndRegistration {

	public UserModel getAuthentication(UserModel registerTemplateModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao login = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);

		UserModel t1 = (UserModel) login.searchOneUserModel("_id", registerTemplateModel.getEmailId(), "UserModel");
	//	login.searchOneProfile(t1.getEmailId());
		ctx.close();
		return t1;

	}
	
	public ArrayList<MutualFriend> getSuggestion(UserModel registerTemplateModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao login = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);

		UserModel t1 = (UserModel) login.searchOneUserModel("_id", registerTemplateModel.getEmailId(), "UserModel");
		ArrayList<MutualFriend> temp = login.searchOneProfile(t1.getEmailId());
		ctx.close();
		return temp;

	}
	
	public UserModel getAuthentication(String emailId) {
		System.out.println("getAuthentication EmailID"+emailId);
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao login = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);

		UserModel t1 = (UserModel) login.searchOneUserModel("_id", emailId, "UserModel");
		ctx.close();
		return t1;

	}

	public void doRegistration(Object userModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
		register.insertOne(userModel, userModel.getClass().getSimpleName());
		ctx.close();
	}

	public void profileCreation(RegisterModel registerTemplateModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations = ctx.getBean("GridFSOperationDao", GridFSOperationDao.class);
		try {
			gridOperations.insertOneFile(registerTemplateModel.getProfilePic().getInputStream(),
					registerTemplateModel.getEmailId() + "_profile");
			gridOperations.insertOneFile(registerTemplateModel.getBackgroundPic().getInputStream(),
					registerTemplateModel.getEmailId() + "_background");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.close();
	}

	public void postPic(MultipartFile picFile, String metaDataId) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations = ctx.getBean("GridFSOperationDao", GridFSOperationDao.class);
		try {
			gridOperations.insertOneFile(picFile.getInputStream(), metaDataId);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.close();
	}

	public MultipartFile getProfile(String identifer) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations = ctx.getBean("GridFSOperationDao", GridFSOperationDao.class);
		GridFSDBFile gridFsdbFile = gridOperations.searchOneFile(identifer);
		MultipartFile retrivingFile = null;
		try {
			retrivingFile = new MockMultipartFile(gridFsdbFile.getFilename(), gridFsdbFile.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.close();
		return retrivingFile;
	}

	public ArrayList<MultipartFile> getProfileList(String identifer) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations = ctx.getBean("GridFSOperationDao", GridFSOperationDao.class);
		ArrayList<GridFSDBFile> gridFsdbFile = (ArrayList<GridFSDBFile>) gridOperations.searchFiles(identifer);
		ArrayList<MultipartFile> retrivingFile = new ArrayList<MultipartFile>();
		try {
			for (int i = 0; i < gridFsdbFile.size(); i++) {

				retrivingFile.add(
						new MockMultipartFile(gridFsdbFile.get(i).getFilename(), gridFsdbFile.get(i).getInputStream()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ctx.close();
		return retrivingFile;
	}

	public void doUpdate(@ModelAttribute UserModel userModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
		register.updateOne("_id", userModel.getEmailId(), userModel, userModel.getClass().getSimpleName());
		ctx.close();
	}

	public void profileUpdate(RegisterModel registerTemplateModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("grid.xml");
		GridFSOperationDao gridOperations = ctx.getBean("GridFSOperationDao", GridFSOperationDao.class);
		try {
			gridOperations.updateOneFile(registerTemplateModel.getProfilePic().getInputStream(),
					registerTemplateModel.getEmailId() + "_profile");
			gridOperations.updateOneFile(registerTemplateModel.getBackgroundPic().getInputStream(),
					registerTemplateModel.getEmailId() + "_background");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception Bro Exception....");
			e.printStackTrace();
		}
		ctx.close();
	}

	public UserModel doSearch(@ModelAttribute UserModel userModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao register = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);

		UserModel userModelFound;
		userModelFound = (UserModel) register.searchOne("_id", userModel.getEmailId(),
				userModel.getClass().getSimpleName());
		ctx.close();

		return userModelFound;
	}

	public boolean verifyUser(RegisterModel rModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao login = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);
		System.out.println("LoginValidationAndRegistration -1");
		System.out.println(rModel.getEmailId());
		UserModel t2 = (UserModel) login.searchOneUserModel("_id", rModel.getEmailId(), "UserModel");

		if (t2 == null) {
			System.out.println("LoginValidationAndRegistration -2");
			ctx.close();
			return false;
		} else {
			System.out.println("LoginValidationAndRegistration -3");
			System.out.println("New pwd entered is: " + rModel.getTempPassword());
			if (t2.getPassword().equals(rModel.getPassword())) {
				System.out.println("Old password matches entered password"+rModel.getTempPassword());
				//t2.setPassword(rModel.getTempPassword());
				System.out.println("next 1");
			login.findAndUpdatePass(rModel.getEmailId(), rModel.getTempPassword(),"password", "UserModel");
				System.out.println("next 2");
				ctx.close();
				return true;
			} else {
				System.out.println("did nit rea");
				ctx.close();
				return false;
			}
		}
	}
	
	public String getPassword(RegisterModel registerTemplateModel) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("dispatcher-servlet.xml");
		MongoDbOperationsDao login = ctx.getBean("MongoDbOperationsDao", MongoDbOperationsDao.class);

		UserModel t1 = (UserModel) login.searchOneUser("emailId", registerTemplateModel.getEmailId(), "UserModel");
		if (t1 == null) {
			ctx.close();
			System.out.println("+LoginValidationAndRegistration t1 == null):");
			return null;
		} else {
			if (t1.getSecurityQuestion1().equals(registerTemplateModel.getSecurityQuestion1())
					&& t1.getSecurityQuestion2().equals(registerTemplateModel.getSecurityQuestion2())) {
				System.out.println("");
				String oldPassword = t1.getPassword();
				System.out.println("+LoginValidationAndRegistration t1 true):"+oldPassword);
				ctx.close();
				System.out.println("OldPass"+oldPassword);
				return oldPassword;
			} else {
				ctx.close();
				return null;
			}

		}

	}


}
