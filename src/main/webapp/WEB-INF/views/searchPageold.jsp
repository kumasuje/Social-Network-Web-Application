<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	function doAdd(one) {

		var addData = $("#" + one).serializeArray();
		var dataObj = {};
		for (var i = 0; i < addData.length; i++) {
			var var2 = addData[i].value;
			dataObj[addData[i].name] = var2;
		}
		var json = JSON.stringify(dataObj);

		$.ajax({
			headers : {
				'Accept' : 'application/json',
				'Content-Type' : 'application/json'
			},
			dataType : "json",
			type : "POST",
			url : "/SE-project-team-six/searchPage/addFriend",
			data : json,
			error : function(xhr, err) {
				alert("readyState: " + xhr.readyState + "\nstatus: "
						+ xhr.status);
				alert("responseText: " + xhr.responseText + " " + err);
				console.log(xhr.responseText);
			},
			success : function(response) {
				alert("Friend Request Sent !!");
			}
		});

	}
	
	
	
	/*//amol $(document).ready(call_recommend()); */
	
	function doSearch() {

		var fromData = $('#post_form').serializeArray();
		var dataObj = {};
		for (var i = 0; i < fromData.length; i++) {
			var var2 = fromData[i].value;
			dataObj[fromData[i].name] = var2;
		}
		var json = JSON.stringify(dataObj);
		alert(json);
		$
				.ajax({
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					dataType : "json",
					type : "POST",
					url : "/SE-project-team-six/searchPage/doSearch",
					data : json,
					error : function(xhr, err) {
						alert("readyState: " + xhr.readyState + "\nstatus: "
								+ xhr.status);
						alert("responseText: " + xhr.responseText + " " + err);
						console.log(xhr.responseText);
					},
					success : function(response) {
						
						/* $(
								'<hr size="100" id="searchtext" width="100%" noshade style="color:#000000padding-top:05px;margin-bottom:05px;" align="left" /><br><h3> Search Result Displayed below </h3>')
								.appendTo("#try1"); */
						jQuery('#try1 div').html('');
						
						for (var i = 0; i < response.searchResult.length; i++) {
							var var1 = response.searchResult[i].fullName;
							var var2 = response.searchResult[i].emailId;
							$(
									'<form name="add_form" id="'+i+'" action="" method="POST"><div class="form-group-sm col-sm-6"><label for="locationSearch">'
											+ var1
											+ '</label><input type="text" name="addFriend" style="margin-bottom:5px;" class="form-control" id="addFriend" value="'+ var2 + '" "><button type="button" name="addFriend"  value="addFriend" style="padding-left:15px;margin-bottom:25px;"id="addFriend" class="btn btn-primary" onclick="doAdd('
											+ i
											+ ');"> Add Friend </button></div></form>')
									.appendTo("#try1");
						}

						//  	gobal=response.searchResult;

						//  $("c\\:forEach").attr("items", response.searchResult );
						// document.getElementsByTagName("c\\:forEach").items(response.searchResult);
						window.location.hash = "#try1";
						// document.form.y.items = response.searchResult;
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
						<li class="active"><a href="newsFeed">Home Page</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="profilePage">Profile Page</a></li>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<li class="active"><a href="searchPage">Search Friend</a></li>
					</ul>
				</div>	
			</div><!-- /.navbar-collapse -->
		</div><!-- /.container-fluid -->
	</nav>

		<div class="container" style="padding-left: 25px;">
			<a href="backgroundPicture"><img
				src="data:image/jpeg;base64,${backgroundPic}"
				style="position: absolute; left: 25px; top: 50px; z-index: 0; border: 2px solid grey;"
				width=90% height=350px alt="[]"></a> <a href="profilePicture"><img
				src="data:image/jpeg;base64,${profilePic}"
				style="position: relative; left: 30px; top: 30px; z-index: 1; border: 2px solid grey;"
				width=200px height=200px alt="[]" /></a>
			<h2
				style="position: relative; left: 30px; top: 20px; z-index: 1; color: white;">
				${name}</h2>
		</div>


		<div class="container" style="position: relative; padding-top: 250px;">


			<div id="left" class="row col-lg-9">

				<form name="post_form" id="post_form" action="" method="POST">
					<hr size="100" width="100%" noshade style="color: #000000"
						align="left" />

					<br>
					<h3>Search Friend</h3>

					<div class="row col-lg-12">
						<div class="form-group-lg col-lg-12">
							<label for="nameSearch">Name</label> <input type="text"
								name="nameSearch" class="form-control" id="nameSearch"
								Placeholder="Search by name ">
						</div>
						<br>
						<div class="form-group-lg col-lg-12">
							<label for="locationSearch">Location</label> <input type="text"
								name="locationSearch" class="form-control" id="locationSearch"
								Placeholder="Search by location ">
						</div>
						<br>
						<div class="form-group-lg col-lg-12">
							<label for="schoolSearch">School</label> <input type="text"
								name="schoolSearch" class="form-control" id="schoolSearch"
								Placeholder="Search by school ">
						</div>
						<br>
						
						<div class="form-group row text-left col-lg-12"
							style="padding-top: 10px;">
							<div class="col-lg-12">
								<label><h3></h3></label>
								<button type="button" name="post" value="post" id="post"
									class="btn btn-primary" onclick="doSearch()">Click to
									Post</button>
							</div>
						</div>
				</form>
			</div>
			
			<div id="try1">
			<hr size="100" id="searchtext" width="100%" noshade style="color:#000000padding-top:05px;margin-bottom:05px;" align="left" /><br><h3> Search Results </h3>
			</div>
			
			<div id="recommendblock">
			
				 <h3>Recommended friends</h3>
				 <c:if test="${fn:length(recommendedResult) eq 0}">
   					<p>There are currently no friend suggestions!</p>
				</c:if>
				<c:forEach items="${recommendedResult}" var="u">
					<form name="add_form1" id="$(u)" action="" method="GET">
						<div class="form-group-sm col-sm-12"
							style="display: inline block;" id="${u.fullName}">
							<label for="locationSearch1"> </label>
							<div class="form-group-sm col-sm-9" style="display: inline block">
								<p align="left" style="padding-top: 05px; margin-bottom: 5px;"
									class="form-control" id="addFriend1">${u.fullName}</p>

							</div>
							<div class="form-group-sm col-sm-3">
								<button align="right" type="submit" name="addFriend"
									value="addFriend"
									style="padding-left: 15px; margin-bottom: 25px;" id="addFriend"
									class="btn btn-primary" onclick="doAdd('${u}')">Add
									Friend</button>
							</div>
							<br>
						</div>
					</form>

				</c:forEach>

			</div>
			<form name="add_form" id="add_form" action="" method="POST"></form>
			
			<form name="logout_form" id="logout_form"
				action="/SE-project-team-six/do_logout" method="POST">
				<div class="form-group row text-right col-lg-3">
					<div class="col-lg-12">
						<button type="submit" name="logoutbutton" value="logoutbutton"
							id="logoutbutton" class="btn btn-primary pull-right">Logout</button>
					</div>
				</div>
			</form>

		</div>
</body>
</html>