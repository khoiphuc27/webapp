<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
<title>Login</title>
</head>
<body>
<h1>Login</h1>

<c:url var="customers" value="/customers" ></c:url>
<form:form action="${customers}" commandName="account">
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
		<c:if test="${!empty msg}">
		${msg}
		</c:if>
	</table>
</form:form>
</body>
</html>
