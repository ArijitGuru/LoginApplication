<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Account</title>
</head>
<body>
	<form:form id="updateForm" modelAttribute="user"
		action="updateAccountProcess" method="post">
		<h2 align="center">Update Account Details:</h2>

		<table align="center">
			<tr>

				<td><a href="welcome">Home</a>
				<td><a href="tutorials">Tutorials</a></td>
				<td><a href="admin">Admin</a></td>
				<td><a href="login?logout">Logout</a></td>

			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td><br /></td>
			</tr>
			<tr>
				<td><br /></td>
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
						<img width='25px' height='25px'
						src="${pageContext.request.contextPath }/images/refresh.png" />
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
				<td><form:button id="update" name="update">Update</form:button>
				</td>
				<td><form:button id="reset" name="reset" style="align: left">Reset</form:button>
				</td>
			</tr>
			<tr></tr>
			<tr>

				<td style="font-style: italic; color: red;">${message}</td>

			</tr>


		</table>
	</form:form>
</body>
</html>