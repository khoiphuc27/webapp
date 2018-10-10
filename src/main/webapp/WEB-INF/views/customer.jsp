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
<c:url var="customer" value="/customer" ></c:url>
	<form:form action="customer" method="POST" modelAttribute="customer">
		<label for="name">Name</label>
    	<form:input type="text" id="name" name="name" path="name" value="${customerDTO.name}" />
    	<br/>
    	<label for="birthday">Birthday</label>
    	<form:input type="text" id="birthday" name="birthday" path="dateOfBirth" value="${customerDTO.birthday}" />
    	<br/>
    	<label for="phone">Phone</label>
		<form:input type="text" id="phone" name="phone" path="phone" value="${customerDTO.phone}" />
    	<br/>
    	<label for="email">Email</label>
    	<form:input type="text" id="email" name="email" path="email" value="${customerDTO.email}" />
    	<br/>
    	<label for="gender">Gender</label>
    	<form:radiobutton path="gender" value="true" />Male
    	<form:radiobutton path="gender" value="false" />Female
	    <br/>
	    <label for="address">Address</label>
	    <form:textarea id="address" name="address" path="addressLine" rows="3" cols="20" value="${customerDTO.address}" />
	    <br/>
<!-- 	    <label for="title">Title</label> -->
<%-- 	    <form:select id="title" name="title" path="title" items="${titleItems}" /> --%>
	    <br/>
	    <input class="button" type="reset" value="Reset">
		<input class="button" type="submit" value="Search">
	</form:form>
<!-- checking -->
<%-- <c:if test="${not empty customerDTO}"> --%>
<!-- has value name: -->
<%-- ${customerDTO.name} --%>
<%-- </c:if> --%>
</body>
</html>
