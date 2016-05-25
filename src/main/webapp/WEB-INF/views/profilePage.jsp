<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
	<title>Social Network</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap -->
 	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" crossorigin="anonymous">
	<style type="text/css">
	
		.dropdown-content {
    
     display: none;
    position: relative;
    background-color: grey;
    width: 250px;
    min-width: 200px;
    top : 00px;
    left: 00px;
    margin: 0;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    color: #0000;
    opacity: 0.6;
}

.dropdown:hover .dropdown-content {
    display: block;
    
}
	
	
	</style>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
	
	function change(){
	//	alert(" bik");
		
	}
	
	function doAccept1(){
		
	//	alert(" mom ");
	}
	function bigImg(x) {
	    x.style.height = "64px";
	    x.style.width = "64px";
	}

	function normalImg(x) {
	    x.style.height = "32px";
	    x.style.width = "32px";
	}
		
	</style>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
	
function doPost(){
		
		var fromData = $('#post_form').serializeArray();
		var dataObj = {};
		for(var i=0;i < fromData.length;i++){
			var var2  = fromData[i].value;
		    dataObj[fromData[i].name] = var2;
		  //  alert("field: "+fromData[i].name+" value: "+var2);
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
        url: "/SE-project-team-six/homePage/doPost1",
        data: json,
        error: function(xhr,err){
            alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
            alert("responseText: "+xhr.responseText+" "+err);
     	    console.log(xhr.responseText);
        },
        success: function(response){
        alert("Posted Successfully ");
        window.location.href = response.key;
        }
        });
        }
	
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
   //  	callBack(postId);  
      }
    });
  }
  
  function callBack(postId){
	//  alert("aaya"+postId);
	  
  }
function commentOnPost(commentPostId){
	//alert("aaja");
	
	var fromData = $('#comment'+commentPostId).serializeArray();
	data1 = { index : commentPostId , commentText : fromData[0].value };
	var json = JSON.stringify(data1);
	$.ajax({
		 headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
    dataType : "json",
    type: "POST",
    url: "/SE-project-team-six/homePage/doComment",
    data: json,
    error: function(xhr,err){
        alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
        alert("responseText: "+xhr.responseText+" "+err);
 	    console.log(xhr.responseText);
    },
    success: function(response){
    window.location.href = response.moveTo;
    }
    });
    }
function showLikes(likeId){
	$('#'+likeId).toggle();
  }
	  
function showComments(commentId){
	$('#'+commentId).toggle();
  }

function doAccept(acceptEmail){
	data1 = { email : acceptEmail };
	$.ajax({
		 headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
    dataType : "json",
    type: "POST",
    url: "/SE-project-team-six/searchPage/acceptRequest",
    data: JSON.stringify(data1),
    error: function(xhr,err){
        alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
        alert("responseText: "+xhr.responseText+" "+err);
 	    console.log(xhr.responseText);
    },
    success: function(response){
        window.location.href = response.moveTo;
    }
    });
    }
    
function doReject(RejectEmail){
	
	data1 = { email : RejectEmail };
		$.ajax({	
		 headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
	dataType : "json",
	type: "POST",
    url:  "/SE-project-team-six/searchPage/rejectRequest",
    data: JSON.stringify(data1),
    error: function(xhr,err){
        alert("readyState: "+xhr.readyState+"\nstatus: "+xhr.status);
        alert("responseText: "+xhr.responseText+" "+err);
 	    console.log(xhr.responseText);
    },
    success: function(response){
    window.location.href = response.moveTo;
    }
    });
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
						<li class="active"><a href="profilePage">Home Page</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="myProfilePage">Profile Page</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="searchPage">Search Friend</a></li>
					</ul>
				</div>	
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>
			
 	 <div class="container" >  	
			<a href="backgroundPicture"><img src="data:image/jpeg;base64,${UserModel.backgroundPicture}" style="position:absolute;
		left:200px;
		top:50px;
		z-index:0;
		border: 2px solid grey;" width=75% height=350px   alt="[]" ></a>
		    
		    <a href="profilePicture"><img src="data:image/jpeg;base64,${UserModel.profilePicture}" style="position:relative;
		left:30px;
		top:30px;
		z-index:1;border: 2px solid grey;" width=250px height=250px alt="[]" /></a>
		<h2 style="position:relative;
		left:30px;
		top: 20px;
		z-index:1; color: white;">
       		${UserModel.fullName}
    	</h2>
         	
	</div>

	<div class="container" style="position:relative;padding-top: 50px;" >
	<div id="left" class="row col-lg-9">
		<form name="project_form" id="project_form" action="/SE-project-team-six/doPost1" enctype="multipart/form-data" method="POST">
			
			<br>
			<div class="row col-lg-12">
				<div class="form-group-lg col-lg-12">
					<label for="newPost"><h3>Write a new post</h3></label>
					<textarea name="newPost" class="form-control" id="newPost" style="height=300px;margin-bottom: 05px;" placeholder="Write a new post ">
					</textarea>
				</div>
				<br>	
				
				<div class="form-group col-lg-12" >
					<div class="form-group col-lg-12" >
					   <label for="postPic"> Attach Pictures To Your Post</label>
					</div>
					   		<div class="form-group col-lg-4" >
								<input type="file" name="postPic1" style="color:#000000;margin-bottom: 10px;line-height: 06px;"  accept="image/*" class="form-control" id="postPic1" >
							</div>
						<div class="form-group col-lg-4" >
							<input type="file" name="postPic2" style="color:#000000;margin-bottom: 10px;line-height: 06px;"  accept="image/*" class="form-control" id="postPic2" >
						</div>
						<div class="form-group col-lg-4" >
							<input type="file" name="postPic3" style="color:#000000;margin-bottom: 10px;line-height: 06px;"  accept="image/*" class="form-control" id="postPic3" >
						</div>
						<div class="form-group col-lg-4" >
							<input type="file" name="postPic4" style="color:#000000;margin-bottom: 10px;line-height: 06px;"  accept="image/*" class="form-control" id="postPic4" >
						</div>
						<div class="form-group col-lg-4" >
							<input type="file" name="postPic5" style="color:#000000;margin-bottom: 10px;line-height: 06px;"  accept="image/*" class="form-control" id="postPic5" >
						</div>
				</div>
		
			<div class="form-group row text-left col-lg-12" style="padding-top: 10px;">
			<div class="col-lg-12">
				<label><h3></h3></label>
				<button type="submit" name="post"  value="post" id="post" class="btn btn-primary" > Click to Post </button>
				<hr size="10" width="100%" noshade style="color:#000000;margin-bottom:05px;" align="left" />
					
			</div>
	
		</div>
	
	</form>	

	</div>
		
	<c:forEach items="${NewPostModel}" var="post">
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
						<a href="<c:url value="/SE-project-team-six/viewPic/${pics}" />" >	</a>
							<img src="data:image/jpeg;base64,${pics}" style="padding-left:15px;padding-top:20px;padding-right:05px;position:relative;" onclick="change()" width="100%"  height="100%" alt="[]" />
    				
    				</div>
    			</c:forEach>
				<div class="form-group col-lg-12">
					<button type="button" name="like" value="like" id="like" class="btn btn-primary" onclick="likeThePost(${post.index})"> Like This Post </button>
					<button type="button" name="ShowComment"  value="ShowComment" id="ShowComment" class="btn btn-primary" onclick="showComments('comments${post.index}')"> Show Comments </button>
					<button type="button" name="ShowLikes"  value="ShowLikes" id="ShowLikes" class="btn btn-primary" onclick="showLikes('like${post.index}')"> People Who Liked This Post </button>
					
						<form name="comment${post.index}"  id="comment${post.index}" action="" method="POST">			
							<input type="text" name="commentText" style="color:#000000;margin-top:10px;margin-bottom: 10px;" class="form-control" value="">
							<button type="button" name="commentButton" style="width: 100%;" value="commentButton" id="commentButton" class="btn btn-primary" onclick="commentOnPost('${post.index}')"> Click to Comment on This Post </button>
						</form>	
					
					<div id ="like${post.index}"  hidden="true">
					<div class="form-group col-lg-12">	
							<label for="commentsByOther">Liked by  </label>
					</div>
					
						<c:forEach items="${post.likedBy}" var="like">
							<div class="form-group col-lg-3" >
								<input type="text" name="postDiasbled" class="form-control" style="color:#000000;margin-top:02px;" id="postDiasbled"  readonly="readonly" value="${like}" >
							</div>		
						</c:forEach>
						
					</div>
					
					<div id ="comments${post.index}"  hidden="true">
					
					<div class="form-group col-lg-12">	
							<label for="commentsByOther">Comments by others </label>
					</div>
						<c:forEach items="${post.comments}" var="com">
							<div class="form-group col-lg-3" >
								<input type="text" name="postDiasbled" class="form-control" style="color:#000000;margin-top:05px;" id="postDiasbled"  readonly="readonly" value="${com.commentedBy}" >
							</div>	
							<div class="form-group col-lg-9" >
								<input type="text" name="postDiasbled" class="form-control" style="color:#000000;margin-top:05px;" id="postDiasbled"  readonly="readonly" value="${com.commentText}" >
							</div>	
						</c:forEach>
					</div>
					<div class="form-group col-lg-12">	
						<hr size="100" width="100%" noshade style="color:#000000;margin-bottom:05px;" align="left" />
					</div>
				
				</div>
		</div>
	</c:forEach>
	
</div>


<div style="float:left;line-height:35px;">
	<div  class="pre-scrollable" id="list" style="float:right;width:300px;height:700px;position:fixed;max-height:600px;">
		<div class="form-group col-lg-12">		
		<div style="margin-top: 05px;">
			<h4>Pending Friend Request </h4>
		</div>
		
		
		<c:if test="${fn:length(UserModel.pendingFriendList) eq 0}">
                   	<input type="text" name="something" readonly="readonly" class="form-control" value="No Pending Friend Request">
       </c:if>

		<c:forEach items="${UserModel.pendingFriendList}" var="pendingRequest">
			
				<div class="form-group col-lg-12" >
			
				
					<a style="font-size: 20px;" href="<c:url value="/SE-project-team-six/viewOtherProfile/${pendingRequest.addFriend}" />" > ${pendingRequest.name}</a>		
			
				</div>
				<div class="form-group col-lg-12">
					<button type="button" name="Accept"  value="Accept" id="Accept" class="btn btn-primary" onclick="doAccept('${pendingRequest.addFriend}')"> Accept </button>
					<button type="button" name="Reject"  value="Reject" id="Reject" class="btn btn-primary" onclick="doReject('${pendingRequest.addFriend}')"> Reject </button>
				</div>
				
				<div class="form-group col-lg-12" >
					<hr size="10" width="100%" noshade style="color:#000000" align="left" />
				</div>
				
			</c:forEach>
			
		</div>
		
		<div class="form-group col-lg-12">		
		<div style="margin-top: 00px;">
			<h4> Friend List </h4>
		</div>
		<div class="form-group col-lg-12" >
				<hr size="10" width="100%" noshade style="color:#000000" align="left" />
		</div>
		<c:forEach items="${UserModel.friendDetails}" var="friendDetails">
		
		<div class="form-group col-lg-12" >
			<div class="dropdown">
					<div class="form-group col-lg-6">
							
							<img src="data:image/jpeg;base64,${friendDetails.profilePicture} " style="padding-left:15px;padding-top:10px;padding-right:00px;position:relative;z-index:-1" width="120%"  height="120%" alt="[]" />
    					
    				</div>	
    				
					<div class="dropdown-content"> 
						<img src="data:image/jpeg;base64,${friendDetails.profilePicture} " style="padding-left:00px;padding-top:-10px;padding-right:00px;position:relative;z-index:-1" width="100%"  height="100%" alt="[]" />
						<input type="text" name="something" readonly="readonly" class="form-control" value="Email   :${friendDetails.emailId}">
						<input type="text" name="something" readonly="readonly" class="form-control" value="Name    :${friendDetails.fullName}">
						<input type="text" name="something" readonly="readonly" class="form-control" value="Gender  :${friendDetails.gender}">
						<input type="text" name="something" readonly="readonly" class="form-control" value="Address :${friendDetails.address}">
						<input type="text" name="something" readonly="readonly" class="form-control" value="School  :${friendDetails.school}">
					</div>
			</div>
			
			<div class="form-group col-lg-6" style="margin-top: 10px;">
    					<a href="<c:url value="/SE-project-team-six/viewOtherProfile/${friendDetails.emailId}" />" >${friendDetails.emailId}</a>		
    				</div>
					<div class="form-group col-lg-12" >
						<hr size="10" width="100%" noshade style="color:#000000" align="left" />
			</div>
			</div>
				
			</c:forEach>
			
			
			<div class="form-group col-lg-12" >
			<form name="logout_form" id="logout_form"
				action="/SE-project-team-six/do_logout" method="POST">
					
						<button type="submit" name="logoutbutton" value="logoutbutton"
							id="logoutbutton" class="btn btn-primary pull-right">Logout</button>
					
				
			</form>
			<div class="form-group col-lg-12" >
			
			
			
			<div class="form-group col-lg-12" >
				<hr size="10" width="100%" noshade style="color:#000000" align="left" />
			</div>
		</div>
	</div>	
	</div>
	
	</div>
</body>
</html>