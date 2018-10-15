<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%-- <%@ page session="false" %> --%>
<html>
<head>
<title>Customer</title>
<style>
	h1 {
		text-align: center;
	}
	form {
		width: 60%;
		margin: auto;
	}
	form * {
		margin-top: 10px;
		margin-bottom: 10px;
	}
	form label {
		float: left;
		width: 20%;
	}
	form input[type="text"], form textarea {
		width: 80%;
	}
	form input[type="radio"] {
	}
	input.button {
		float: right;
		margin: 5px
	}
	
</style>
</head>
<body>
<h1>Customer Information</h1>
<c:url var="customer" value="/customer"></c:url>
	<form:form action="${customer}" method="POST" modelAttribute="customer">
		<label for="name">Name</label>
    	<form:input type="text" id="name" name="name" path="name" />
    	<br/>
    	<label for="birthday">Birthday</label>
    	<form:input type="text" id="birthday" name="birthday" path="birthday" />
    	<br/>
    	<label for="phone">Phone</label>
		<form:input type="text" id="phone" name="phone" path="phone" />
    	<br/>
    	<label for="email">Email</label>
    	<form:input type="text" id="email" name="email" path="email" />
    	<br/>
    	<label for="gender">Gender</label>
    	<form:radiobutton path="gender" value="true" />Male
    	<form:radiobutton path="gender" value="false" />Female
	    <br/>
	    <label for="address">Address</label>
	    <form:textarea id="address" name="address" path="address" rows="3" cols="20" />
	    <br/>
	    <label for="title">Title</label>
	    <form:select id="title" name="title" path="title" items="${titleItems}"/>
	    <br/>
	    <input type="hidden" name="customerId" value="${customerId}"/>
	    <input class="button" type="submit" name="resetBtn" value="Reset">
		<input class="button" type="submit" name="saveBtn" value="Save">
		<p><font color="red"><form:errors path="name"/></font></p>
		<p><font color="red"><form:errors path="birthday"/></font></p>
		<p><font color="red"><form:errors path="phone"/></font></p>
		<p><font color="red"><form:errors path="email"/></font></p>
		<p><font color="red"><form:errors path="address"/></font></p>
	</form:form>
<!-- checking -->
<%-- <c:if test="${not empty customer}"> --%>
<!-- has value name: -->
<%-- ${customer.name} --%>
<%-- </c:if> --%>
</body>
</html>
