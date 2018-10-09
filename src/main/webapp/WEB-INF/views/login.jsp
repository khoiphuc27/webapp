<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
<title>Login</title>
<style>
	p.errorMsg {
		color: red;
	}
</style>
</head>
<body>
<h1>Login</h1>

<c:url var="login" value="/login" ></c:url>
<form:form action="${login}" commandName="account">
	<table>
		<tr>
			<td>
				<form:label path="userName">
					<spring:message text="User Name"/>
				</form:label>
			</td>
			<td>
				<form:input path="userName"/>
			</td> 
		</tr>
		<tr>
			<td>
				<form:label path="password">
					<spring:message text="Password"/>
				</form:label>
			</td>
			<td>
				<form:input path="password"/>
			</td> 
		</tr>
		<tr>
			<td colspan="2">
				<input type="reset"
					value="<spring:message text="Reset"/>" />
				<input type="submit"
					value="<spring:message text="Login"/>" />
			</td>
		</tr>
	</table>
		<c:if test="${!empty errorMsg}">
			<p class="errorMsg">${errorMsg}</p>
		</c:if>
</form:form>
</body>
</html>
