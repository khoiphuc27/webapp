<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
<title>Customers</title>
<style>
	div#search-div, div#command-div, table#customers-listing {
		width: 70%;
		border: 1px solid black;
		margin: auto;
		padding: 10px;
	}
	div#command-div a {
	    display: inline-block;
	    background: #ddd;
	    padding: 5px;
	    margin: 10px;
	    text-align: center;
	    text-decoration: none;
	    color: black;
	}
	table {
		width: 100%;
		table-layout: fixed;
	}
	table#customers-listing, table#customers-listing th, table#customers-listing td {
		border: 1px solid black;
		border-collapse: collapse;	
	}
	table#customers-listing th, table#customers-listing td {
		padding: 5px;
	}
	tr {
		width: 100%;
	}
	td {
		width: 50%;
	}
	label {
		display: inline-block;
		width: 25%;
	}
	input["text"], input["date"] {
		display: inline-block;
		width: 75%;
	}
	.invalid-noti {
		color: red;
	}
	div.pagination {
		width: 70%;
		margin: auto;
		text-align: right;
	}
	div.pagination a {
		display: inline-block;
		padding: 5px;
		margin: 5px;
		text-decoration: none;
		font-weight: bold;
		border: 1px solid black;
	}
</style>
</head>
<body>
<h3>Search</h3>
<div id="search-div">
<c:url var="customers" value="/customers" ></c:url>
	<form:form action="customers" method="POST" modelAttribute="customer">
		<table>
			<tr>
				<td>
					<label for="name">Name:</label>
    				<form:input type="text" id="name" name="name" path="name"/>
    			</td>
    			<td>
				    <label for="phone">Phone:</label>
				    <form:input type="text" id="phone" name="phone" path="phone" />
				</td>
			</tr>
			<tr>
				<td>
					<label for="birthday">Birthday:</label>
    				<form:input type="date" id="birthday" name="birthday" path="dateOfBirth" />
				</td>
				<td>
					<label for="gender">Gender:</label>
    				<form:radiobutton id="gender" name="gender" path="gender" value="true" />Male
    				<form:radiobutton id="gender" name="gender" path="gender" value="false" />Female
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">Email:</label>
    				<form:input type="text" id="email" name="email" path="email" />
				</td>
				<td>
    				<input type="submit" name="resetBtn" value="Reset">
    				<input type="submit" name="searchBtn" value="Search">
				</td>
			</tr>
		</table>
	<p><font color="red"><form:errors path="name"/></font></p>
	<p><font color="red"><form:errors path="phone"/></font></p>
	<p><font color="red"><form:errors path="email"/></font></p>
	</form:form>
</div>

<c:url var="customers" value="/customers" ></c:url>
<form:form action="customers" method="POST">
<h3>Commands</h3>
<div id="command-div">
<%-- 	<a type="button" href="<spring:url value="/customer"/>">New</a> --%>
<%-- 	<a type="button" href="<spring:url value="/customer"/>">Update</a> --%>
<%-- 	<a type="button" href="<spring:url value="/customer"/>">Delete</a> --%>
<%-- 	<a type="button" href="<spring:url value="/customer"/>">Export</a> --%>

	<input type="submit" name="newBtn" value="New">
	<input type="submit" name="updateBtn" value="Update">
	<input type="submit" name="deleteBtn" value="Delete">
	<input type="submit" name="exportBtn" value="Export">
</div>

<h3>Customers Listing</h3>
<c:if test="${!empty listCustomers}">
	<table id="customers-listing">
		<col width="6%">
		<col width="23%">
		<col width="23%">
		<col width="23%">
		<col width="25%">
		<tr>
			<th></th>
			<th><a href="customers?sort=name">Name</a></th>
			<th><a href="customers?sort=birthday">Birthday</a></th>
			<th><a href="customers?sort=phone">Phone</a></th>
			<th><a href="customers?sort=email">Email</a></th>
		</tr>
	<c:forEach items="${listCustomers}" var="customer">
		<tr>
			<td><input type="checkbox" name="selectedIds" id="seletedIds" value="${customer.id}"></td>
			<td>${customer.name}</td>
			<td>${customer.dateOfBirth}</td>
			<td>${customer.phone}</td>
			<td>${customer.email}</td>
		</tr>
	</c:forEach>
	</table>
	
	<div class="pagination">
		<c:if test="${numOfPages > 1}">
			<c:if test="${page != 1}">
				<a href="customers?page=1">First</a>
				<a href="customers?page=${page - 1}">Previous</a>
			</c:if>
			<c:forEach items="${listPages}" var="pageNumber">
				<a href="customers?page=${pageNumber}">${pageNumber}</a>
			</c:forEach>
			<c:if test="${page != numOfPages}">
				<a href="customers?page=${page + 1}">Next</a>
				<a href="customers?page=${numOfPages}">Last</a>
			</c:if>
		</c:if>
	</div>	
</c:if>

</form:form>

<h3>Input Search:</h3>

Name: 
<c:if test="${!empty name}">
${name}
</c:if>
<br/>
Phone:
<c:if test="${!empty phone}">
${phone}
</c:if>
<br/>
Birthday:
<c:if test="${!empty birthday}">
${birthday}
</c:if>
<br/>
Gender:
<c:if test="${!empty gender}">
${gender}
</c:if>
<br/>
Email:
<c:if test="${!empty email}">
${email}
</c:if>
<br/>
</body>
</html>
