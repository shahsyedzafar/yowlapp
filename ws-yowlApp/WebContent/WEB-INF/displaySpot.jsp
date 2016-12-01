<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang ="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="ws-yowlApp/css/style3.css">
<style>
body {
	font-family: sans-serif;
	font-size: 11pt;
	background-size: cover;
	background-attachment: fixed;
	background-image: url(http://travel.aarp.org/content/dam/travel/destination-images/grand-canyon-national-park/2014-01/1400-grand-canyon-national-park-hiker.jpg);
	opacity:0.95;
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

tr:nth-child(even) td{
	background-color: white;
}

tr:NTH-CHILD(odd) td{
	background-color:#EEEEEE;
}

#nodata {
	font-family: proxima:nova;
	text-align: center;
	font-size: 50px;
	color: white;
	opacity:10.0;

}

#spot_div {
	position:relative;
	top:0px;
	bottom: 80px;
}

</style>


<title>DisplaySpots</title>
</head>
<body>

<c:choose>
	<c:when test="${not empty value}">
		<a href = "IndexServlet?univName=${value}">
		<img src="back_button.png">
		</a>
	</c:when>
	<c:otherwise>
		
		<a  href="buttonsPage.html">
			<img src="back_button.png">
		</a>
	
	</c:otherwise>

</c:choose>




<div id = "spot_div">

	<table align ="center">
	<tr>
		<td id="header" colspan= "4"><h1>Tourist Spot Details</h1></td>
	</tr>
	<tr>
	
		<th>Name</th>	
		<th>City</th>
		<th>ImagePath</th>
		<th>Comment</th>
	</tr>
	<c:choose>
		<c:when test="${not empty spot}">
			<tr>
				<td>${spot.getName()}</td>
				<td>${spot.getLocation()}</td>
				<td>${spot.getImg_path()}</td>
				<td>${spot.getComment()}</td>
			</tr>

		</c:when>
		<c:otherwise>
			<tr>
				<td colspan="4">No Data Available</td>
			</tr>
		</c:otherwise>
	
	
	
	</c:choose>
	
	
</table>
	

</div>

<c:choose> 
	<c:when test="${not empty tweets}">
		<table align ="center">
			<tr>
				<td id="header" colspan= "4"><h1>Tweets Data</h1></td>
			</tr>
			<tr>
				<th>TouristSpot</th>
				<th>Hashtag</th>
		<th>Data</th>
		<th>Media</th>
		
	</tr>
	
	<c:forEach items = "${tweets}" var = "tweet">
		<tr>
			<td>${tweet.getTouristSpot()}</td>
			<td>${tweet.getHashTag()}</td>
			<td>${tweet.getData()}</td>
		<c:choose>
			<c:when test="${fn:contains(tweet.getMedia(), 'Absent') }">
				<td>${tweet.getMedia()}</td>
			</c:when>
			<c:otherwise>
				<td><a href="${tweet.getMedia()}">${tweet.getMedia()}</a></td>
			</c:otherwise>
		</c:choose>			
		</tr>
	</c:forEach>
</table>
		
	</c:when>
	<c:otherwise>
		<h1 id = "nodata">No tweet data Available for ${spot.getName()}</h1>
	</c:otherwise>
</c:choose>






</body>
</html>