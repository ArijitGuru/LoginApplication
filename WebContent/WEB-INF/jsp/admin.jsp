<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Welcome Admin</title>

</head>

<body>

	<table>

		<tr>

			<td><a href="welcome">Home</a>
			<td><a href="updateAccount">Account Update</a></td>
			<td><a href="tutorials">Tutorials</a></td>
			<c:if test="${sessionScope.user_admin eq true}">
				<td><a href="admin" class="btn">Admin</a></td>
			</c:if>
			<td><a href="login?logout">Logout</a></td>

		</tr>
		<tr>
			<td><br /></td>
		</tr>
		<tr>
			<td><br /></td>
		</tr>

		<tr>

			<td><h2>${message}</h2></td>

		</tr>

		<tr>

		</tr>

		<tr>

		</tr>

	</table>

</body>

</html>