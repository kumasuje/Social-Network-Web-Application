package org.p565.team6.controller;


import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.p565.team6.beans.LoginValidationAndRegistration;
import org.p565.team6.beans.PostAndComment;
import org.p565.team6.model.RegisterModel;
import org.p565.team6.model.UserModel;
import org.p565.team6.model.CommentModel;
import org.p565.team6.model.NewPostModel;
import org.p565.team6.model.RegisterModel;



@Controller
@RequestMapping(value = "/homePage")
@SessionAttributes({"UserModel","registerDetail","NewPostModel","backgroundPic","profilePic"})
public class homeController {

	@Autowired
	private LoginValidationAndRegistration loginValidation;
	
	@Autowired
	private LoginValidationAndRegistration registration;
	
	@Autowired
	private PostAndComment postComment;
	
	@RequestMapping(value = "/test")
	public String loginPage(ModelMap model){
		
		UserModel userModelAtt = (UserModel) model.get("UserModel");
		String userEmail = userModelAtt.getEmailId();
		return "tryhard";
	}
	
	@RequestMapping(value = "/doPost",method = RequestMethod.POST)
	public  String sayHello(@RequestBody  NewPostModel newPostModel,ModelMap model ){
		
		JSONObject json = 	new JSONObject();
		json.put("key", "profilePage");
		UserModel userModel = (UserModel) model.get("UserModel");
		
		postComment.doPost(newPostModel,"NewPost_"+userModel.getEmailId(),userModel);
		postComment.doPost(newPostModel, "CommonPost",userModel);
		postComment.updateLastValue();
		return json.toString();
		
   	}
	
	
	
	@RequestMapping(value = "/like",method = RequestMethod.POST)
	public @ResponseBody String likePost(@RequestBody  NewPostModel newPostModel,ModelMap model ){
		
		
		JSONObject json = 	new JSONObject();
		json.put("moveTo", "profilePage");
		UserModel userModel = (UserModel) model.get("UserModel");
		postComment.likeIt(userModel.getFullName(),newPostModel.getIndex(),"NewPost_"+userModel.getEmailId());
		postComment.likeIt(userModel.getFullName(),newPostModel.getIndex(),"CommonPost");
		
		return json.toString();
   	}
	
	@RequestMapping(value = "/doComment",method = RequestMethod.POST)
	public @ResponseBody String doComment(@RequestBody  CommentModel commentModel,ModelMap model ){
		JSONObject json = 	new JSONObject();
		json.put("moveTo", "profilePage");
		UserModel userModel = (UserModel) model.get("UserModel");
		postComment.commentOnPost(commentModel.commentText,commentModel.index,userModel.getFullName(),"CommonPost");
		return json.toString();
   	}
	
	@RequestMapping(value = "/doRegister",method = RequestMethod.POST)
	public @ResponseBody String doRegister(@RequestBody  RegisterModel registerTemplateModel,ModelMap model ){
		JSONObject json = 	new JSONObject();
		registration.doRegistration(registerTemplateModel);
		json.put("moveTo", "continue");
		model.addAttribute("name", "Halu");
		model.addAttribute("registerDetail", registerTemplateModel);
		return json.toString();
		
   	}
	
	@RequestMapping(value = "/newsFeed",method = RequestMethod.POST)
	public String returnNewsfeed(@ModelAttribute RegisterModel registerTemplateModel,@ModelAttribute  UserModel userModel, ModelMap model ){

		registration.doRegistration(registerTemplateModel);
		registration.profileCreation(registerTemplateModel);
		MultipartFile profilePicMultiPart = registration.getProfile(registerTemplateModel.getEmailId()+"_profile");
		MultipartFile backgroundPicMultiPart = registration.getProfile(registerTemplateModel.getEmailId()+"_background");
		
		String profilePic = null;
		String backgroundPic = null;
		try {
			profilePic = new String(Base64.encodeBase64(profilePicMultiPart.getBytes()), "UTF-8");
			backgroundPic = new String(Base64.encodeBase64(backgroundPicMultiPart.getBytes()), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("profilePic",profilePic);
		model.addAttribute("backgroundPic", backgroundPic);
		return "profilePage";
	}

	
	@RequestMapping(value = "/newsFeed",method = RequestMethod.GET)
	public String reloadNewsfeed(ModelMap model ){
		UserModel userModelAtt = (UserModel) model.get("UserModel");
		ArrayList<NewPostModel> returnPost = postComment.getPost(userModelAtt.getUsersFriendList());
		model.put("NewPostModel", returnPost);
		return "profilePage";
	}
	
	@RequestMapping(value = "/continue")
	public String newsFeed(ModelMap model){
		
		return "continue";
	}
	@RequestMapping(value = "/invalidEntery")
	public String invalidEntery(){
		System.out.println("....invalid");
		return "invalidEntery";
	}
	
//	@RequestMapping(value = "/profilePage")
//	public String profilePage(ModelMap model){
//		
//		return "profilePage";
//	}
	@RequestMapping(value = "/backgroundPicture")
	public String backgroundPicture(ModelMap model){
		
		return "backgroundPicture";
	}
	
	}
