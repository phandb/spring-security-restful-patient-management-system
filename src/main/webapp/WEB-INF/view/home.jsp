<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

	<head>
	
		<title>Spring Security, RESTful, and Hibernate</title>
	</head>
	
	<body>
		<h2> Welcome to Patient Management System</h2>
		<hr>
		
		<!-- Display user name and role -->
		<p>
			User:<security.authentication property="principal.username"/>
			<br><br>
			Role(s): <security.authentication property="principal.authorities"/>
			
		</p>
		<security:authorize access="hasRole('MANAGER')">
		<!-- Add a link to point to leaders -->
			<p>
				<a href="${pageContext.request.contextPath }/leaders">Leadership Meeting</a>
			</p>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<!-- Add a link to point to admin -->
			<p>
				<a href="${pageContext.request.contextPath }/systems">IT Systems Meeting</a>
			</p>
		</security:authorize>
		<hr>
		<!-- Add a logout button -->
		<form:form action="${pageContext.request.contextPath }/logout"
				method="POST">
				<input type="submit" value="Logout"/>
		</form:form>
	</body>

</html>