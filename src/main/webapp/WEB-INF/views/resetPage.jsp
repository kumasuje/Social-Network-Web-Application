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
					<a class="navbar-brand">Social network</a>
				</div>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="container">
		<div id="left">
			<form name="reset_form" id="reset_form"
				action="/SE-project-team-six/validateDetails" method="POST">

				<div class="row col-lg-12">
					<h3 class="text-left">Welcome to Social Network, Please enter
						the details to change your password</h3>
					<hr size="10" width="100%" noshade style="color: #000000"
						align="left" />

					<div class="form-group col-lg-12">
						<label for="emailId">Email</label> <input type="text"
							name="emailId" class="form-control" id="emailId">
					</div>



					<!-- <div class="form-group col-lg-12">
						<label for="dob">Date of Birth</label> <input type="text"
							name="dob" class="form-control" id="dob">
					</div>


					<div class="form-group col-lg-12">
						<label for="securityQuestion1">Your mother's maiden name ?</label>
						<input type="text" name="securityQuestion1" class="form-control"
							id="securityQuestion1">
					</div>

					<div class="form-group col-lg-12">

						<label for="securityQuestion2">Your father's maiden name ?</label>
						<input type="text" name="securityQuestion2" class="form-control"
							id="securityQuestion2">
					</div> -->

					<div class="form-group col-lg-12">
						<label for="password">Your current password</label>
						<input type="text" name="password" class="form-control"
							id="password">
					</div>

					<div class="form-group col-lg-12">

						<label for="tempPassword">New password</label>
						<input type="password" name="tempPassword" class="form-control"
							id="tempPassword">
					</div>
					

					<div class="form-group row text-left col-lg-9">
						<div class="col-lg-12">
							<button type="submit" name="continue" value="continue"
								id="continue" class="btn btn-primary">Confirm Reset Password</button>
						</div>
					</div>
				</div>

			</form>
		</div>
		<br style="clear: both;" />


	</div>

</body>
</html>