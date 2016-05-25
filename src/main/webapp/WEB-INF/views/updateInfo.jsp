<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
	<title>Social Network</title>
	
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	
	<!-- Bootstrap -->
 	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" integrity="sha256-7s5uDGW3AHqw6xtJmNNtr+OBRJUlgkNJEo78P4b0yRw= sha512-nNo+yCHEyn0smMxSswnf/OnX6/KwJuZTlNZBjauKhTK0c+zT+q5JOCx0UFhXQ6rJR9jg6Es8gPuD2uZcYDLqSw==" crossorigin="anonymous">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript">
	
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
				
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>

	<div class="container" >
	<div id="left">
		<form name="project_form" id="project_form" action="/SE-project-team-six/update" enctype="multipart/form-data" method="POST">
			
			<div class="row col-lg-12">
			<h3 class="text-left">Please complete your profile to proceed further </h3>
			<hr size="10" width="100%" noshade style="color:#000000" align="left" />
				
			<h5 class="text-left"><b>This section contains information provided on registration page which can be edited here </b></h5>
				<div class="form-group col-lg-6">
					<label for="emailId">Email</label>
					<input type="email" name="emailId" required class="form-control" id="emailId" value="${registerDetail.emailId}" >
				</div>
				
			
				<div class="form-group col-lg-6" >
						
					<label for="password">Password</label>
					<input type="password" name="password" required class="form-control" id="password" value="${registerDetail.password}">
				</div>
			
			   		
				<div class="form-group col-lg-6" >
					<label for="dob">Date of Birth</label>
					<input type="text" name="dob" required class="form-control" id="dob" value="${registerDetail.dob}">
				</div>
			    <div class="form-group col-lg-6" >
						
					<label for="gender">Gender</label>
					<input type="text" name="gender" required class="form-control" id="gender" value="${registerDetail.gender}">
				</div>
			
			<div class="form-group col-lg-6" >
					<label for="securityQuestion1">Your mother's maiden name ?</label>
					<input type="text" name="securityQuestion1" required class="form-control" id="securityQuestion1" value="${registerDetail.securityQuestion1}">
				</div>
				
				<div class="form-group col-lg-6" >
						
					<label for="securityQuestion2">Your father's maiden name ?</label>
					<input type="text" name="securityQuestion2" required class="form-control" id="securityQuestion2" value="${registerDetail.securityQuestion2}">
				</div>
				</div>
			<div class="row col-lg-12">
			
	<hr size="10" width="100%" noshade style="color:#000000" align="left" />
		<h5 class="text-left"><b>This section requires few mandatory information required to join the Social Network </b></h5>
			
		<div class="form-group col-lg-6" >
					<label for="fullName">Full Name</label>
					<input type="text" name="fullName" class="form-control" id="fullName" placeholder="Enter Your Full Name">
				</div>
			    <div class="form-group col-lg-6" >
						
					<label for="school">School</label>
					<input type="text" name="school" required class="form-control" id="school" placeholder="Enter Your School">
				</div>
				<div class="form-group col-lg-6" >
						
					<label for="phoneNumber">Phone number</label>
					<input type="text" name="phoneNumber" required class="form-control" id="phoneNumber" placeholder="Enter Your Phone Number">
				</div>
				<div class="form-group col-lg-6" >
						
					<label for="address">Address number</label>
					<input type="text" name="address" required class="form-control" id="address" placeholder="Enter Your Address">
				</div>
				
				<div class="form-group col-lg-6" >
				    <label for="profilePic">Upload Your Profile Picture</label>
					<input type="file" name="profilePic" required accept="image/*" class="form-control" id="profilePic" >
				</div>
		       
		       <div class="form-group col-lg-6" >
				    <label for="backgroundPic">Upload Your Background Picture</label>
					<input type="file" name="backgroundPic" required accept="image/*" class="form-control" id="backgroundPic" >
				</div>
					
		<div class="form-group row text-left col-lg-9">
			<div class="col-lg-12">
				<button type="submit" name="save" value="save" id="save" class="btn btn-primary"> Save </button>
			</div>
		</div>
		
		</div>
		
	</form>	
	</div>
  <br style="clear:both;"/>	
	
	
</div>
	
</body>
</html>