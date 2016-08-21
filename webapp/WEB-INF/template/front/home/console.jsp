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
<h1>管理平台页面（需登录）</h1>
<p>
	<ul>
		<li><a href="${pageContext.request.contextPath}/user/">用户管理</a></li>
		<li><a href="${pageContext.request.contextPath}/role/">角色管理</a></li>
		<li><a href="${pageContext.request.contextPath}/authority/">权限管理</a></li>
	</ul>
</p>
<p>
	<a href="signout">退出</a>
</p>
</body>
</html>
