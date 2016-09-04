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
	<a href="${pageContext.request.contextPath}/user/add">新增用户</a>
	<a href="${pageContext.request.contextPath}/user/save-fail-test">新增用户Lily(失败回滚)</a>
	<div>${notice}</div>
	<table border="1">
		<thead>
			<tr>
				<th width="200">Name</th>
				<th width="200">Nick Name</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="obj">
			<tr>
				<td><a href="${pageContext.request.contextPath}/user/${obj.id}">${obj.name}</a></td>
				<td>${obj.nickname}</td>
				<td>
					<a href="${pageContext.request.contextPath}/user/${obj.id}/edit">Edit</a>
					<a href="${pageContext.request.contextPath}/user/${obj.id}" data-method="DELETE">Delete</a>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js" ></script>
<script type="text/javascript">
$(function(){
	// Restful Delete
	$('a[data-method]').click(function(event){
		event.preventDefault();
		
		var url = $(this).attr('href');
		var http_verb = $(this).attr('data-method');
		$('<form action="'+ url +'" method="POST"><input type="hidden" name="_method" value="'+ http_verb +'" /></form>').submit();
		<!--
		$.ajax({
            url: url,
            type: http_verb,
            success: function(data) {
            	location.href = location.href;
            }
        });
		-->
	});
});
</script>
</html>
