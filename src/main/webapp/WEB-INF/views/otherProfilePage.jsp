<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Social Network</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap -->
 	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" crossorigin="anonymous">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
	
	function likeThePost(postId){
		data1 = { index : postId };
		var json = JSON.stringify(data1);
		$.ajax({
			 headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
	    dataType : "json",
	    type: "POST",
	    async:false,
	    url: "/SE-project-team-six/homePage/like",
	    data: json,
	    error: function(xhr,err){
	        alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
	        alert("responseText: "+xhr.responseText+" "+err);
	     },
	    success: function(response){
	       window.location.href = response.moveTo;
	     	callBack(postId);  
	      }
	    });
	  }
	  
	  function callBack(postId){
		  
	  }	function showLikes(likeId){
		$('#'+likeId).toggle();
	  }
		  
	function showComments(commentId){
		//alert('#'+commentId);
		$('#'+commentId).toggle();
	  }

	
	$(document).ready(function(){
		$('#newPost').val('');
		$("#basicInfo").hide();
		$("#contactInfo").hide();
		$("#oldPosts").hide();
	})
	function doBasic(){
	
		$("#basicInfo").show();
		$("#contactInfo").hide();
		$("#oldPosts").hide();
    
	}
	
	function doContact(){
		$("#basicInfo").hide();
		$("#contactInfo").show();
		$("#oldPosts").hide();
	}
	
	function doOldPost(){
		$("#basicInfo").hide();
		$("#contactInfo").hide();
		$("#oldPosts").show();
	}
	
	
	function doAdd(one,two){
	
	data1 = { addFriend : one , name : two };
		var json = JSON.stringify(data1);
		$.ajax({
			 headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
	   dataType : "json",
	   type: "POST",
	   url: "/SE-project-team-six/searchPage/addFriend",
	   data: json,
	   error: function(xhr,err){
	       alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
	       alert("responseText: "+xhr.responseText+" "+err);
		    console.log(xhr.responseText);
	   },
	   success: function(response){
	   	alert("Friend Request Sent !!");
	   	 }
	   });
		
	   }
	
function doPost(){
		if($('#newPost').val().length>0){
		var fromData = $('#post_form').serializeArray();
		var dataObj = {};
		for(var i=0;i < fromData.length;i++){
			var var2  = fromData[i].value;
		    dataObj[fromData[i].name] = var2;
		   }    
		var json = JSON.stringify(dataObj);
	//	alert(json);
		$.ajax({
			 headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
        dataType : "json",
        type: "POST",
        url: "/SE-project-team-six/homePage/doPost",
        data: json,
        error: function(xhr,err){
            alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
            alert("responseText: "+xhr.responseText+" "+err);
     	    console.log(xhr.responseText);
        },
        success: function(response){
     //   alert(response.key);
        //window.location.href = response.key;
        $('#newPost').val('');
        }
        
        });
		}
		else{
			prompt("your textbox is empty!");
		}
}
	
	
	</script>
</head>
<body>
	
	<!-- Navigator for the website, Home is active. Once finalised override -->
	<nav class="navbar navbar-inverse">

		<!-- To cover the complete width -->
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" >Social network</a>
				</div>
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="/SE-project-team-six/profilePage">Home Page</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="/SE-project-team-six/myProfilePage">Profile Page</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="/SE-project-team-six/searchPage">Search Friend</a></li>
					</ul>
				</div>	
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
			
 	 <div class="container" style="padding-left:25px;">  	
			<a href="backgroundPicture"><img src="data:image/jpeg;base64,${otherProfilePage.backgroundPicture}" style="position:absolute;
		left:25px;
		top:50px;
		z-index:0;
		border: 2px solid grey;" width=90% height=350px   alt="[]" ></a>
		    
		    <a href="profilePicture"><img src="data:image/jpeg;base64,${otherProfilePage.profilePicture}" style="position:relative;
		left:30px;
		top:30px;
		z-index:1;border: 2px solid grey;" width=200px height=200px alt="[]" /></a>
		<h2 style="position:relative;
		left:30px;
		top: 20px;
		z-index:1; color: white;">
       		${Picture.fullName}
    	</h2>
		<div class="container-fluid" >
					<div class="col-lg-9" style="padding:15px 15px 15px 15px;" align= right>
						<a href="updateInfo"> <button type="button" style="width:100px" name="Edit" value="Edit" id="Edit" class="btn btn-primary"> Edit </button> </a>
					
						<button type="button" style="width:100px" name="Add" value="Add" id="Add" class="btn btn-primary" onclick="doAdd('${otherProfilePage.emailId}','${otherProfilePage.fullName}')" > Add </button>
					
						<button type="button" style="width:100px" name="Chat" value="Chat" id="Chat" class="btn btn-primary" onclick="doOldPost()" disabled> Chat </button>
					</div>
		</div>
	</div>
	<div class="container" style="position:relative;padding-top: 50px;" >
	
	<div class="container-fluid" >
				<div class="col-lg-12" style="padding:15px 15px 15px 15px; position:center;">
					<button type="button" style="width:250px" name="basic" value="basic" id="basic" class="btn btn-primary" onclick="doBasic()"> Basic Information </button>
				
					<button type="button" style="width:250px" name="contact" value="contact" id="contact" class="btn btn-primary" onclick="doContact()"> Contact </button>
				
					<button type="button" style="width:250px" name="post" value="post" id="post" class="btn btn-primary" onclick="doOldPost()"> Old Post </button>
				</div>
	</div>
	<div id="information" style="padding-top:50px">
		<div id="oldPosts">
				
				<c:forEach items="${OtherUserPost}" var="post">
		<div id="left" class="row col-lg-12">
			
			
				<div class="form-group col-lg-3">
					<div style="width:100%;height:100%;">
    					<a href="<c:url value="/SE-project-team-six/viewPic/${post.imagePostedBy}" />" >	</a>
						
    						<img src="data:image/jpeg;base64,${post.imagePostedBy}"  id="${post.index}" style="padding-left:15px;padding-top:20px;padding-right:05px;position:relative;z-index:11" width="100%"  onclick="change();" height="100%" alt="[]" />
    					
    				</div>
    			</div>
				<div class="form-group col-lg-6">	
							<label for="PostBy" style="margin-top: 15%;font-size: 20px">Posted By ${post.fullName}  </label>
				</div>
				
				
				<div class="form-group col-lg-12" >
					<input type="text" name="postDiasbled" class="form-control" id="postDiasbled"  readonly="readonly" value="${post.newPost}">
				</div>
				<c:forEach items="${post.postPicString}" var="pics">
					<div class="form-group col-lg-6" style="width:100%;height:100%;">
					<a onclick="return goToURL();">to my link</a>
						<a href="<c:url value="/SE-project-team-six/viewPic/${pics}" />" >	</a>
							<img src="data:image/jpeg;base64,${pics}" style="padding-left:15px;padding-top:20px;padding-right:05px;position:relative;" onclick="change()" width="100%"  height="100%" alt="[]" />
    				
    				</div>
    			</c:forEach>
		</div>
	</c:forEach>				
				
				
		</div>
			
		<div id="contactInfo">
				<div class="form-group-lg col-lg-9">
				
				<div class="form-group col-lg-12" >
					<label for="emailId">Phone</label>
					<input type="text" name="something" readonly="readonly" class="form-control" id="something" value="${otherProfilePage.phoneNumber}">
				</div>
				
				<div class="form-group col-lg-12" >
					<label for="emailId">Email</label>
					<input type="text" name="something" readonly="readonly" class="form-control" id="something" value="${otherProfilePage.emailId}">
				</div>
				
				<div class="form-group col-lg-12" >
					<label for="emailId">Address</label>
					<input type="text" name="something" readonly="readonly" class="form-control" id="something" value="${otherProfilePage.address}">
				</div>
				
				</div>
		</div>
		
		<div id="basicInfo" >
		
		<div class="form-group-lg col-lg-9">
		<div class="form-group col-lg-12" >
					<label for="emailId">Gender</label>
					<input type="text" name="something" readonly="readonly" class="form-control" id="something" value="${otherProfilePage.gender}">
		</div>
		
		<div class="form-group col-lg-12" >
					<label for="emailId">Date Of Birth</label>
					<input type="text" name="something" readonly="readonly" class="form-control" id="something" value="${otherProfilePage.dob}">
		</div>		
				
		<div class="form-group col-lg-12" >
					<label for="emailId">School</label>
					<input type="text" name="something" readonly="readonly" class="form-control" id="something" value="${otherProfilePage.school}">
		</div>	
		</div>

				</div>
		</div>
	
	</div>
	
	
	




</body>
</html>