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
<h1>Spring MVC + MyBatis + Base Mapper</h1>
<a href="${pageContext.request.contextPath}/user/add">新增Test用户</a>
<ul>
	<c:forEach items="${userList}" var="obj">
		<li><a href="${pageContext.request.contextPath}/user/${obj.id}">${obj.name}</a> | 昵称:${obj.nickname} | 创建时间: ${obj.createAt}</li>
	</c:forEach>
</ul>
</body>
</html>
