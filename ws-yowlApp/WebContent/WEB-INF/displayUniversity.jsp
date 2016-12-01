<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang ="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>University_Display</title>

<style>



body {
	font-family: sans-serif;
	font-size: 11pt;
	background-size: cover;
	background-attachment: fixed;
	background-image: url(http://randomwallpapers.net/university-of-otago-dunedin-new-zealand-world-1920x1200-wallpaper159079.jpg);
	opacity: 0.95;
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


#gframe {
	align:center;
	position: relative;
	top: 0px;
	left:0px;

}

#location_heading {
	position: absolute;
	left: 100px;
	right: 100px;
	
}
#location_heading h {
	position: relative;
	font-family: proxima:nova;
	font-size: 50px;
	color: white;
	left: 80px;
	top: 85px;
	opacity:10.0;
}

#spots_suggest {
	position:relative;
	top:80px;
	right: 60x;

	
}



#div_container {
	position: relative;
	bottom:30px;
}

#spot_suggest_container {
	position: absolute;
	right: 420px;
}

#location_container {
	position: absolute;
	left:50px; 
	top: 288px;
	width: 90px;
}

#spots_table {
	width: 155%;
	height: 300%
}
#loc_table{
	width: 10%;
	height: 80%;
	position: relative;
	left: 120px;

}
#loc_table td{
	width: 20%;

}

#tab_pane {
	position: absolute;
	left:20px;
	top:20px;
	width: 2%;
	height: 2%;
}
.back-btn {
	position:absolute;
	top: 0px;
	left: 0px;
}

</style>
</head>
<body>

<a  href="buttonsPage.html">
	<img src="back_button.png">
</a>

<div id = "div_container">
	<div id = "univ_container">
				<table class = "table table-striped" align = "center">
		<tr><td id="header" colspan="6"><h1>University details</h1></td></tr>
		<tr>
			<th>University Name</th>
			<th>Homepage</th>
			<th>City</th>
			<th>State</th>
			<th>Chairperson</th>
		</tr>
		<tr>
		<td>${requestScope['pojo'].name}</td>
		<td><a href="http://${pojo.getHomePage()}">${requestScope['pojo'].homePage}</a></td>
		<td>${requestScope['pojo'].location}</td>
		<td>${requestScope['pojo'].state}</td>
		<td>${requestScope['pojo'].chairPerson}</td>
		</tr>		
	</table>	
	</div>
	<div id = "spot_suggest_container">
			<c:if test="${not empty touristSpots}">
	<div id ="spots_suggest" align="center">
	<table id="spots_table">
		<tr><td id="header" colspan="1"><h1>Tourist Spots around!</h1></td></tr>
		<tr>
			<th>Spots</th>
		</tr>	
			<tr>
			<td>
			<ul style="list-style-type:none" class="list-group">
				<c:forEach items="${touristSpots}" var = "spot">
					<li class = "list-group-item"><a href = "IndexServlet?touristSpotName=${spot}&value=${requestScope['pojo'].name}">${spot}</a></li>
				</c:forEach>
			</ul>
			
			</td>	
			</tr>			
	</table>
</div>
	</c:if>
	</div>
	
	<div id="location_container">
	

	<table id = "loc_table">
		<tr>
			<td id="header"><b>Location</b></h></td>
		</tr>
		<tr>
		<td>
				<div id="gframe">
					<iframe width="550" height="340" frameborder="0" style="border:0" src="https://www.google.com/maps/embed/v1/search?key=AIzaSyARCDT0mTYFGJ1sAiXEGkwbZKA5D8DWdU4&q=${GoogleUrl}/" >
					</iframe>
				</div>
		</td>	
		</tr>
	</table>
	</div>
	
	
	
	
</div>
	
	
	
	
	
	
	
	

	
	
	
	
	
</body>
</html>