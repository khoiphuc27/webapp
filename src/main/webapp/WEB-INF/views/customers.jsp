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
</style>
</head>
<body>
<h3>Search</h3>
<div id="search-div">
<c:url var="customers" value="/customers" ></c:url>
	<form:form action="customers" method="POST">
		<table>
			<tr>
				<td>
					<label for="name">Name:</label>
    				<input type="text" id="name" name="name" value="<c:if test="${!empty name}">${name}</c:if>">
    			</td>
    			<td>
				    <label for="phone">Phone:</label>
				    <input type="text" id="phone" name="phone" value="<c:if test="${!empty phone}">${phone}</c:if>">
				</td>
			</tr>
			<tr>
				<td>
					<label for="birthday">Birthday:</label>
    				<input type="date" id="birthday" name="birthday" value="<c:if test="${!empty birthday}">${birthday}</c:if>">
				</td>
				<td>
					<label for="gender">Gender:</label>
    				<input type="radio" id="gender" name="gender" value="Male" <c:if test="${(!empty gender) and (gender=='Male')}"> checked </c:if>>Male
    				<input type="radio" id="gender" name="gender" value="Female" <c:if test="${(!empty gender) and (gender=='Female')}"> checked </c:if>>Female
				</td>
			</tr>
			<tr>
				<td>
					<label for="email">Email:</label>
    				<input type="text" id="email" name="email" value="<c:if test="${!empty email}">${email}</c:if>">
				</td>
				<td>
    				<input type="reset" value="Reset">
    				<input type="submit" value="Search">
				</td>
			</tr>
		</table>
	</form:form> 
	<c:if test="${!empty invalidInputNoti}">
		<p class="invalid-noti">${invalidInputNoti}</p>
	</c:if>   
</div>

<c:url var="customer" value="/customer" ></c:url>
<form:form action="customer" method="POST">
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
			<th>Name</th>
			<th>Birthday</th>
			<th>Phone</th>
			<th>Email</th>
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
