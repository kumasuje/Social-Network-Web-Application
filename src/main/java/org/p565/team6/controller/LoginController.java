package org.p565.team6.controller;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.p565.team6.beans.EmailHandler;
import org.p565.team6.beans.LoginValidationAndRegistration;
import org.p565.team6.beans.PostAndComment;
import org.p565.team6.beans.SearchFriendAndSuggestion;
import org.p565.team6.model.MutualFriend;
import org.p565.team6.model.NewPostModel;
import org.p565.team6.model.RegisterModel;
import org.p565.team6.model.SearchModel;
import org.p565.team6.model.UserModel;
import org.p565.team6.model.RegisterModel;



@Controller
@SessionAttributes({"UserModel","registerDetail","profilePic","backgroundPic","otherProfilePage","UserPost","OtherUserPost","Suggestion","recommendedResult"})
public class LoginController {

	@Autowired
	private LoginValidationAndRegistration loginValidation;
	
	@Autowired
	private LoginValidationAndRegistration registration;
	
	@Autowired
	private SearchFriendAndSuggestion searchFriendAndSuggestion;
	
	@Autowired
	private PostAndComment postComment;
	
	@RequestMapping(value = "/")
	public String loginPage(){
		
		return "login";
		
	}
	
	// amol-Reset
	@RequestMapping(value = "/doReset")
	public String retrievepassword() {

		System.out.println("In reset method");
		return "resetPage";

	}
	
	@RequestMapping(value = "/doForget")
	public String forgetPassword() {

		System.out.println("In forget method");
		return "forgetPassword";

	}
	

	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
	public String forgetpassWord_Email(@ModelAttribute RegisterModel rModel) throws Exception {
		System.out.println("in forget password");
		System.out.println(
				"rec: " + rModel.getEmailId() + rModel.getSecurityQuestion1() + " " + rModel.getSecurityQuestion2());

		String oldPassword = loginValidation.getPassword(rModel);
		System.out.println("LoginContro" + oldPassword);
		String sent = EmailHandler.sendEmail(rModel.getEmailId(), oldPassword);
		System.out.println("sendEmail Checker" + sent);
		if (sent=="false") {
			return "invalidEntery";
		} 
		return "actionSuccess";
	}
	
	@RequestMapping(value = "/validateDetails", method = RequestMethod.POST)
	public String validateDetails(@ModelAttribute RegisterModel rModel) {
		boolean isValid = loginValidation.verifyUser(rModel);
		if(isValid){
		return "actionSuccess";
		}
		else
		{
			return "invalidEntery";
		}
	}	
	@RequestMapping(value = "/doLogin",method = RequestMethod.POST)
	public @ResponseBody String sayHello(@RequestBody  UserModel registerTemplateModel,ModelMap model ){
		
		
		JSONObject json = 	new JSONObject();
		UserModel userModel = loginValidation.getAuthentication(registerTemplateModel);
		
		if (userModel == null) {
			
			json.put("moveTo","invalidEntery");
			System.out.println(json.toString());
			return json.toString();
			
		} else {
			
				if(userModel.getPassword().equals(registerTemplateModel.getPassword())==true)
				{
					json.put("moveTo", "profilePage");
					model.addAttribute("UserModel", userModel);
					return json.toString();
				}
				else{
					json.put("moveTo","invalidEntery");
					System.out.println(json.toString());
					return json.toString();
				}
		}
   	}
	
	@RequestMapping(value = "/doRegister",method = RequestMethod.POST)
	public @ResponseBody String doRegister(@RequestBody  RegisterModel registerTemplateModel,ModelMap model ){
		JSONObject json = 	new JSONObject();
		//registration.doRegistration(registerTemplateModel);
		json.put("moveTo", "continue");
		model.addAttribute("registerDetail", registerTemplateModel);
		return json.toString();
  	}
	
	@RequestMapping(value = "/newsFeed",method = RequestMethod.POST)
	public String returnNewsfeed(@ModelAttribute RegisterModel registerTemplateModel,@ModelAttribute  UserModel userModel, ModelMap model ){

		System.out.println(" redirect to profile page from newsFeed");
		userModel.getUsersFriendList().add(userModel.getEmailId());
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
		userModel.setProfilePicture(profilePic);
		userModel.setBackgroundPicture(backgroundPic);
		registration.doRegistration(userModel);
		
		model.addAttribute("UserModel",userModel );
		model.addAttribute("profilePic",profilePic);
		model.addAttribute("backgroundPic", backgroundPic);
		return "forward:/SE-project-team-six/profilePage";
	}
	
	

	@RequestMapping(value = "/profilePage")
	public String profilePage(ModelMap model){
		
		System.out.println(" login -- > profilepage");
		UserModel userModelAtt = (UserModel) model.get("UserModel");
		UserModel retUserModel = (UserModel)searchFriendAndSuggestion.getModel(userModelAtt.getEmailId());
		model.put("UserModel", retUserModel);
		ArrayList<NewPostModel> returnPost = postComment.getPost(userModelAtt.getUsersFriendList());
		model.put("NewPostModel", returnPost);
		return "profilePage";
	}
	
	
	@RequestMapping(value = "/searchPage",method = RequestMethod.GET)
	public String searchPage(ModelMap model ){
		UserModel userModelAtt = (UserModel) model.get("UserModel");
		ArrayList<NewPostModel> returnPost = postComment.getPost(userModelAtt.getUsersFriendList());
		MultipartFile profilePicMultiPart = registration.getProfile(userModelAtt.getEmailId()+"_profile");
		MultipartFile backgroundPicMultiPart = registration.getProfile(userModelAtt.getEmailId()+"_background");
		//ArrayList<UserModel> t2 = searchFriendAndSuggestion.getRecommendedFriends(userModelAtt);
		
		ArrayList<MutualFriend> suggestionResult = registration.getSuggestion(userModelAtt);
		model.put("Suggestion",suggestionResult);
		
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
		model.addAttribute("name" ,userModelAtt.getFullName());
		model.addAttribute("school" ,userModelAtt.getSchool());
		model.addAttribute("dateOfBirth" ,userModelAtt.getDob());
		model.addAttribute("gender" ,userModelAtt.getGender());
		model.addAttribute("number" ,userModelAtt.getPhoneNumber());
		model.addAttribute("address" ,userModelAtt.getAddress());
		model.addAttribute("email" ,userModelAtt.getEmailId());
		model.put("NewPostModel", returnPost);
	//	model.put("recommendedResult", t2);
		
		return "searchPage";
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
	@RequestMapping(value = "/backgroundPicture")
	public String backgroundPicture(ModelMap model){
		
		return "backgroundPicture";
	}
	
	@RequestMapping(value = "/doPost1",method = RequestMethod.POST)
	public  String sayHello1(@ModelAttribute  NewPostModel newPostModel,ModelMap model ){
		System.out.println("new post 1");
		UserModel userModel = (UserModel) model.get("UserModel");
		MultipartFile temp1 = newPostModel.getPostPic1();
		MultipartFile temp2 = newPostModel.getPostPic2();
		MultipartFile temp3 = newPostModel.getPostPic3();
		MultipartFile temp4 = newPostModel.getPostPic4();
		MultipartFile temp5 = newPostModel.getPostPic5();
		postComment.doPost(newPostModel,"NewPost_"+userModel.getEmailId(),userModel);
		newPostModel.setPostPic1(temp1);
		newPostModel.setPostPic2(temp2);
		newPostModel.setPostPic3(temp3);
		newPostModel.setPostPic4(temp4);
		newPostModel.setPostPic5(temp5);
		postComment.doPost(newPostModel, "CommonPost",userModel);
		postComment.updateLastValue();
		return "forward:/SE-project-team-six/profilePage";
		
   	}
	@RequestMapping(value = "/viewOtherProfile/{argument:.+}", method = RequestMethod.GET)
    public  String Controller(@PathVariable("argument") String argument,ModelMap model) {
		System.out.println("argument"+argument);
	UserModel otherProfilePage =registration.getAuthentication(argument);
     System.out.println(otherProfilePage.getEmailId());
     model.addAttribute("otherProfilePage", otherProfilePage);
	
	ArrayList<NewPostModel> returnPost = postComment.getPostForUser(otherProfilePage.getUsersFriendList(),argument);
	model.put("OtherUserPost", returnPost);

     
     
	return "otherProfilePage";
    }
	@RequestMapping(value = "/test2", method = RequestMethod.GET, params = {"argName"})
    public String Controller1(@RequestParam(value="argName", required = true) String argName) {
		System.out.println(argName);
		return null;
     
	}
	
	@RequestMapping(value = "/updateInfo",method = RequestMethod.GET)
	public String goUpdate(ModelMap model){
		
		return "updateInfo";
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.POST)
	public String updateInfo(@ModelAttribute RegisterModel registerTemplateModel,@ModelAttribute  UserModel userModel, ModelMap model ){
		
		registration.doUpdate(userModel);
		registration.profileUpdate(registerTemplateModel);
		
		
		ArrayList<NewPostModel> returnPost = postComment.getPost(userModel.getUsersFriendList());
		model.put("NewPostModel", returnPost);
		
		MultipartFile profilePicMultiPart = registration.getProfile(userModel.getEmailId()+"_profile");
		MultipartFile backgroundPicMultiPart = registration.getProfile(userModel.getEmailId()+"_background");
		
		
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
		model.put("profilePic",profilePic);
		model.put("backgroundPic", backgroundPic);
		model.put("name" ,userModel.getFullName());
		model.put("school" ,userModel.getSchool());
		model.put("dateOfBirth" ,userModel.getDob());
		model.put("gender" ,userModel.getGender());
		model.put("number" ,userModel.getPhoneNumber());
		model.put("address" ,userModel.getAddress());
		model.put("email" ,userModel.getEmailId());
		model.put("UserModel", userModel);

		return "myProfilePage";
	}
	

	//Suraj's code
	

		@RequestMapping(value = "/myProfilePage")
		public String profilePage1(ModelMap model){
			UserModel userModelAtt = (UserModel) model.get("UserModel");
			ArrayList<NewPostModel> returnPost = postComment.getPostForUser(userModelAtt.getUsersFriendList(),userModelAtt.getEmailId());
			model.put("UserPost", returnPost);
			return "myProfilePage";
		}
		
		
		@RequestMapping(value = "/do_logout")
	    public String doLogout(HttpSession session, Model model) {
	        session.invalidate();
	        if (model.containsAttribute("counter"))
	            model.asMap().remove("counter");
	        model.asMap().clear();
	 
	        System.out.println("Logout controller called.");
	        return "login";
	    }
	    

		
		
		
}
