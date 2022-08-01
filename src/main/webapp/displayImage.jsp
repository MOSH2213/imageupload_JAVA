<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Image</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
</head>
<body>
	<h1 style="color: blue" align="center">UPLOADED IMAGE DETAIL</h1>

	<div align="center">
		<form action="Dimg" method="post">
			Enter Image Id : <input type="number" name="imageId"> 
			<button class="btn btn-primary">SHOW</button>
		</form>
	</div>

	<hr>

	<%
		String imgFileName = (String) request.getAttribute("img");
		String imgId = (String) request.getAttribute("id");
		System.out.println(imgFileName);
	%>

	<div align="center">
			<%
				if (imgFileName != "" && imgId != "") {
			%>
			<div class="card w-100 border-0" >
				<img class="card-img-top mx-auto w-25"  src="img/<%=imgFileName%>" alt="Enter IMAGE id">
				<div class="card-body d-flex justify-content-center">
					<h5 class="card-title" style="color:red;">IMAGE NAME : <%=imgFileName%></h5>
					<!-- <p class="card-text "></p> -->
				</div>
			</div>

			<%
			}
			%>
	</div>
	

</body>
</html>