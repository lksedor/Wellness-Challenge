<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
<link href="resources/css/styling.css" rel="stylesheet" type="text/css">
</head>
<body>


	<h1>Fitbit Profile Response Body</h1>

	<P>${lauraProfileText}</P>

	<table cellpadding="0" cellspacing="0">
	<thead>
	<tr>
		<th>Date</th>
		<th>Weight</th>
		<th>BMI</th>
	</tr>
	</thead>
	<tbody>
	

			<c:forEach items="${lauraWeight.weight}" var="weight" varStatus="loop">
<tr>

				<td>${weight.date}</td>
				<td><fmt:formatNumber type="number" pattern="###.##"
					value="${weight.weight * 2.2}" /></td>
				<td>${weight.bmi}</td>
					
			</tr>
			</c:forEach>
	
		</tbody>
	</table>
	<br/>
		<table cellpadding="0" cellspacing="0">
	<thead>
	<tr>
		<th>Date</th>
		<th>Weight</th>
		<th>BMI</th>
	</tr>
	</thead>
	<tbody>
	
<P>${momProfileText}</P>
			<c:forEach items="${momWeight.weight}" var="weight" varStatus="loop">
<tr>

				<td>${weight.date}</td>
				<td><fmt:formatNumber type="number" pattern="###.##"
					value="${weight.weight * 2.2}" /></td>
				<td>${weight.bmi}</td>
					
			</tr>
			</c:forEach>
	
		</tbody>
	</table><br/>
	<P>${dadProfileText}</P>
		<table cellpadding="0" cellspacing="0">
	<thead>
	<tr>
		<th>Date</th>
		<th>Weight</th>
		<th>BMI</th>
	</tr>
	</thead>
	<tbody>
	

			<c:forEach items="${dadWeight.weight}" var="weight" varStatus="loop">
<tr>

				<td>${weight.date}</td>
				<td><fmt:formatNumber type="number" pattern="###.##"
					value="${weight.weight * 2.2}" /></td>
				<td>${weight.bmi}</td>
					
			</tr>
			</c:forEach>
	
		</tbody>
	</table>
	
</body>
</html>
