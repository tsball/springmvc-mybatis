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
	<h1>Add User</h1>
	<form action="${pageContext.request.contextPath}/user/" method="post">
		<div>
			<label>姓名</label>
			<input type="text" name="name" />
		</div>
		<div>
			<label>昵称</label>
			<input type="text" name="nickname" />
		</div>
		<div>
			<button type="submit">提交</button>
		</div>
	</form>
</body>
</html>
