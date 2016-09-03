<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>页面跳转</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">

 	var i = 0, totalSeconds = 3;
 
	$(document).ready(function(){ 
		setInterval("startRequest()",1000); 
	});
	
	function startRequest() 
	{ 
		if(++i >= totalSeconds)
			document.location.href = "${redirectUrl}";
		else
			$("#left_time").html(totalSeconds-i);
	}
</script>
</head>
<body>
	<div style="border: 1px solid #DDD; background-color: rgb(51, 153, 240); padding: 10px; margin: 10px;">
		${content}
		<br/><br/>
		<span id="left_time">3</span>秒内将会跳转到<a id="redirectLink" href="${redirectUrl}">这里</a>	
	</div>
</body>
</html>