<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>

</head>
<body>
	<h1>Edit User</h1>
	<div>${alert}</div>
	<form:form modelAttribute="user" action="${pageContext.request.contextPath}/user/${user.id}" method="PUT">
		<div>
			<form:label path="name">用户名</form:label>
			<form:input path="name" />
		</div>
		<div>
			<form:label path="nickname">昵称</form:label>
			<form:input path="nickname" />
		</div>
		<div>
			<button type="submit">提交</button>
		</div>
	</form:form>
</body>
</html>
