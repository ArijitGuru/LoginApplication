<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="user" action="registerProcess"
		method="post">
		<table align="center">
			<tr>
				<td></td>
				<td><a href="welcome">Home</a></td>
			</tr>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" name="name" id="name" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" name="email" id="email" /></td>
			</tr>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" name="password"
						id="password" /></td>
			</tr>
			<tr>
				<td>Captcha#</td>
				<td>
					<div>
						<img id="captcha_id" name="imgCaptcha"
							src="${pageContext.request.contextPath }/captcha">
					</div>
				</td>

				<td align="left"><a href="javascript:;"
					title="change captcha text"
					onclick="document.getElementById('captcha_id').src = '${pageContext.request.contextPath }/captcha?' + Math.random();  return false">
						<img width='25px' height='25px' src="${pageContext.request.contextPath }/images/refresh.png"/>
				</a>
				</td>

			</tr>
			<tr>
				<td colspan="2"><form:errors path="captcha" cssClass="error" /></td>
			</tr>

			<tr>
				<td>Enter above Captcha text#</td>
				<td><form:input path="captcha" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button id="register" name="register">Register</form:button>
				</td>
				<td><input type="reset" value="Reset"></td>
			</tr>
			<tr></tr>
			
		</table>
	</form:form>
</body>
</html>