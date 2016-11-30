<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang ="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>



body {
	font-family: sans-serif;
	font-size: 11pt;
	background-size: cover;
	background-attachment: fixed;
	background-image: url(http://randomwallpapers.net/university-of-otago-dunedin-new-zealand-world-1920x1200-wallpaper159079.jpg);
}

table {
	width: 80%;
	font-size: 20px;
	font-family: "proxima:nova";	
}
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
	opacity: 0.95;
}
th, td {
	padding: 10px;
	text-align: center;
}
th {
	background-color: #31A6FF;
}

tr {
	background-color: white;
}

#header {
	background-color: red;
	color: white;
}




</style>


</head>
<body>



	<p>Message:</p>
	
	
	<table align = "center">
		<tr><td id="header" colspan="5"><h1>University details</h1></td></tr>
		<tr>
			<th>University Name</th>
			<th>Homepage</th>
			<th>Location</th>
			<th>State</th>
			<th>Chairperson</th>
		</tr>
		<tr>
		<td>${requestScope['pojo'].name}</td>
		<td>${requestScope['pojo'].homePage}</td>
		<td>${requestScope['pojo'].location}</td>
		<td>${requestScope['pojo'].state}</td>
		<td>${requestScope['pojo'].chairPerson}</td>
		</tr>		
	</table>	
</body>
</html>