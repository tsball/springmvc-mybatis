<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>

</head>
<body>
	<h1>Edit User</h1>
	<form:form action="${pageContext.request.contextPath}/user/${user.id}" method="PUT">
		<div>
			<label>姓名</label>
			<input type="text" name="name" value="${user.name}" />
		</div>
		<div>
			<label>昵称</label>
			<input type="text" name="nickname" value="${user.nickname}" />
		</div>
		<div>
			<button type="submit">提交</button>
		</div>
	</form:form>
</body>
</html>
