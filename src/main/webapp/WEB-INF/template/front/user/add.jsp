<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List</title>

</head>
<body>
	<h1>Add User</h1>
	<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/user/">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<div>
			<label>姓名</label>
			<form:input path="name" />
		</div>
		<div>
			<label>昵称</label>
			<input type="text" name="nickname" />
		</div>
		<div>
			<label>密码</label>
			<input type="text" name="password" />
		</div>
		<div>
			<button type="submit">提交</button>
		</div>
	</form:form>
</body>
</html>
