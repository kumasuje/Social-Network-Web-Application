<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Social Network</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- Bootstrap -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw=="
	crossorigin="anonymous">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script type="text/javascript">
	function enableBtn() {
		document.getElementById('register').disabled = false;
	}
	function ValidateEmail(inputText)  
	{  
	var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
	if(inputText.value.match(mailformat))  
	{  
	document.form1.text1.focus();  
	return true;  
	}  
	else  
	{  
	alert("You have entered an invalid email address!");  
	document.form1.text1.focus();  
	return false;  
	}  
	}  

	function doLogin() {
		
		
		
		var fromData = $('#project_form').serializeArray();
		
		var dataObj = {};
		for (var i = 0; i < fromData.length; i++) {
			var var2 = fromData[i].value;
			dataObj[fromData[i].name] = var2;
		}
		var x= dataObj["emailId"];
		if(dataObj["emailId"] == ""){
			console.log("blank email");
			alert("Please fill the email address");
			return;
		}
		var atpos = x.indexOf("@");
		var dotpos = x.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
	    	console.log("invalid email");
	        alert("Please enter a valid e-mail address");
	        //return false;
	        return;
	    }
	    
	    if(dataObj["password"] == ""){
	    	console.log("blank password");
	    	alert("Please enter a password");
	    	return;
	    }
		
		else
		{
		var json = JSON.stringify(dataObj);
		//alert(json);
		//if(json["email"])
		$.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			dataType : "json",
			type : "POST",
			url : "/SE-project-team-six/doLogin",
			data : json,
			error : function(xhr, err) {
				alert("readyState: " + xhr.readyState + "\nstatus: "
						+ xhr.status);
				alert("responseText: " + xhr.responseText + " " + err);
				console.log(xhr.responseText);
			},
			success : function(response) {
				
				window.location.href = response.moveTo;
			}
		});
		}
	}

	function doRegister() {

		var fromData = $('#project_form1').serializeArray();
		var dataObj = {};
		for (var i = 0; i < fromData.length; i++) {
			var var2 = fromData[i].value;
			dataObj[fromData[i].name] = var2;
		}
		var x= dataObj["emailId"];
		if(dataObj["emailId"] == ""){
			console.log("blank email");
			alert("Please fill the email address");
			return;
		}
		var atpos = x.indexOf("@");
		var dotpos = x.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=x.length) {
	    	console.log("invalid email");
	        alert("Please enter a valid e-mail address");
	        //return false;
	        return;
	    }
	    
	    if(dataObj["password"] == ""){
	    	console.log("blank password");
	    	alert("Please enter a password");
	    	return;
	    }
	    if(dataObj["dob"] == ""){
	    	console.log("blank DOB");
	    	alert("Please enter  DOB");
	    	return;
	    }
	    
	    else{
	    	
	    
		var json = JSON.stringify(dataObj);
	
		$.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			dataType : "json",
			type : "POST",
			url : "/SE-project-team-six/doRegister",
			data : json,
			error : function(xhr, err) {
				alert("readyState: " + xhr.readyState + "\nstatus: "
						+ xhr.status);
				alert("responseText: " + xhr.responseText + " " + err.Message);
				console.log(xhr.responseText);
			},
			success : function(response) {
				
				window.location.href = response.moveTo;
			}
		});
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
					<a class="navbar-brand">Social network</a>
				</div>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="container">
		<div id="left">
			<form name="project_form" id="project_form" action="" method="POST">

				<div class="row col-lg-6">
					<h2 class="text-left">EXISTING USER</h2>
					<div class="form-group col-lg-6">
						<br> <label for="emailId">Email</label> <input type="email"
							name="emailId" class="form-control" id="emailId"
							placeholder="Enter the Email" required="true"> 
<!-- 					<input type="email" name= "emailId" placeholder="Enter your email"> -->

					</div>

					<br>
					<div class="form-group col-lg-6">

						<label for="password">Password</label> <input type="password"
							name="password" class="form-control" id="password"
							placeholder="Enter the password">
					</div>


					<div class="form-group row text-left col-lg-9">
						<div class="col-lg-12">
							<button type="button" name="login" value="login" id="login"
								class="btn btn-primary" onclick="doLogin()">Click to
								Login</button>
						</div>
					</div>
			</form>

			<form name="reset password_form" id="reset_form"
				action="/SE-project-team-six/doReset" method="POST">
				<div class="form-group row text-center col-lg-6">
					<div class="col-lg-6">
						<button type="submit" name="resetPassword" value="restPassword"
							id="restPassword" class="btn btn-primary">Reset Password</button>
					</div>


				</div>
			</form>
			<form name="forget password_form" id="forget_form"
				action="/SE-project-team-six/doForget" method="POST">
				<div class="form-group row text-right col-lg-6">
					<div class="col-lg-12">
						<button type="submit" name="forgotPassword" value="forgotPassword"
							id="forgotPassword" class="btn btn-primary">Forgot
							Password</button>
					</div>
				</div>
			</form>




		</div>
		<div id="right">

			<form name="project_form1" id="project_form1" action="" method="POST">

				<div class="row col-lg-6">
					<h2 class="text-center">Registration for New Users</h2>
					<div class="form-group col-lg-6">
						<br> <label for="emailId">Email</label> <input type="text"
							name="emailId" class="form-control" id="emailId"
							placeholder="Enter the Email">
					</div>

					<br>
					<div class="form-group col-lg-6">

						<label for="password">Password</label> <input type="Password"
							name="password" class="form-control" id="password"
							placeholder="Enter the password">
					</div>
					<div class="form-group col-lg-6">
						<label for="dob">Date of Birth</label> <input type="date"
							name="dob" class="form-control" id="dob" placeholder="MM/DD/YYYY">
					</div>
					<div class="form-group col-lg-6">

						<label for="gender">Gender</label> <input type="text"
							name="gender" class="form-control" id="gender"
							placeholder="Enter the Gender">
					</div>

					<div class="form-group col-lg-6">
						<label for="securityQuestion1">Your mother's maiden name ?</label>
						<input type="text" name="securityQuestion1" class="form-control"
							id="securityQuestion1"
							placeholder="Enter the mother's maiden name">
					</div>

					<div class="form-group col-lg-6">

						<label for="securityQuestion2">Your father's  name ?</label>
						<input type="text" name="securityQuestion2" class="form-control"
							id="securityQuestion2"
							placeholder="Enter the father's  name">
					</div>

					<div class="form-group col-lg-12">
						<div class="g-recaptcha"
							data-sitekey="6Ldoox0TAAAAADw4GntNjafGTATs3nIeJSTWu9Ot"
							data-callback="enableBtn"></div>
					</div>

					<!-- disabled="disabled" -->
					<div class="form-group row text-center col-lg-6">
						<div class="col-lg-6">
							<button type="button" name="register" value="register"
								id="register" disabled="disabled"   class="btn btn-primary" onclick="doRegister()">Click
								to Register</button>
						</div>
					</div>
			</form>
		</div>
		<br style="clear: both;" />


	</div>

</body>
</html>