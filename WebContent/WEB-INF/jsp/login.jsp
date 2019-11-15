<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
a.btn {
	display: inline-block;
	text-decoration: none;
	background-color: aqua;
	padding: 10px, 5px;
	margin: 10px;
	height: 25px;
	width: 60px;
	text-align: center;
	border-radius: 5px;
}
</style>
</head>
<body>
	<form:form id="loginForm" modelAttribute="login" action="loginProcess"
		method="post">
		<table align="center">
			<tr>
				<td></td>
				<td><a href="home" class="btn">Home</a></td>
			</tr>
			<tr>
				<td><form:label path="username">Username: </form:label></td>
				<td><form:input path="username" name="username" id="username" />
				</td>
			</tr>
			<tr>
				<td><form:label path="password">Password:</form:label></td>
				<td><form:password path="password" name="password"
						id="password" /></td>
			</tr>
			<tr>
				<td>Captcha#</td>
				<td>
					<div>
						<img id="captcha_id" name="imgCaptcha" src="captcha.jpg">
					</div>
				</td>

				<td align="left"><a href="javascript:;"
					title="change captcha text"
					onclick="document.getElementById('captcha_id').src = 'captcha.jpg?' + Math.random();  return false">
						<img width='25px' height='25px' src="<c:url value="/images/refresh.png" /> "/>
				</a></td>

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
				<td align="left"><form:button id="login" name="Submit">Submit</form:button></td>
				<td align="left"><form:button id="reset" name="Reset">Reset</form:button></td>

			</tr>
			<tr></tr>
		</table>
	</form:form>
	<table align="center">
		<tr>
			<td style="font-style: italic; color: red;">${message}</td>
		</tr>
	</table>
</body>
</html>