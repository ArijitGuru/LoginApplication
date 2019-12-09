<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome</title>
<style>
a.btn {
	display: inline-block;
	text-decoration: none;
	background-color: aqua;
	padding: 10px, 5px;
	margin: 10px;
	height: 25px;
	width: 160px;
	text-align: center;
	border-radius: 5px;
}
</style>
</head>

<body>

	<table align="center">

		<tr>

			<td>Welcome ${user.name} ${user.email}</td>

		</tr>

		<tr>

		</tr>

		<tr>

		</tr>

		<tr>

			<td><a href="welcome" class="btn">Home</a>
			<td><a href="updateAccount" class="btn">Account Update</a></td>
			<td><a href="tutorials" class="btn">Tutorials</a></td>

			<c:if test="${sessionScope.user_admin eq true}">
				<td><a href="admin" class="btn">Admin</a></td>
			</c:if>
			<%-- Is User role from session admin: ${sessionScope.user_admin } --%>
			<td><a href="login?logout" class="btn">Logout</a></td>

		</tr>
		<tr>
			<td>
				<h2>This is home page</h2>
			</td>
		</tr>


	</table>

</body>

</html>