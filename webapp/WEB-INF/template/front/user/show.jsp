<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>

</head>
<body>
	<h1>User</h1>
	<div>
		<label>姓名</label>
		${user.name}
	</div>
	<div>
		<label>昵称</label>
		${user.nickname}
	</div>
	<div>
		<label>角色</label>
		<c:forEach items="${user.roleList}" var="obj">
			${obj.name}
		</c:forEach>
	</div>
</body>
</html>
