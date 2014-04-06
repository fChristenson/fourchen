<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Forum</title>
<link rel="stylesheet" href="view/css/main.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="view/js/TableSlider.js"></script>
</head>
<body>
	<c:import url="${headerJsp }">
		<c:param value="${adminUrl }" name="url"></c:param>
	</c:import>
	
	<c:import url="${formJsp }">
		<c:param name="isNewThread" value="true"></c:param>
		<c:param name="postUrl" value="${postUrl }"></c:param>
	</c:import>
	
	<c:import url="${tableJsp }">
		<c:param value="${forumThreads }" name="forumThreads"></c:param>
	</c:import>
</body>
<script>
	
	var slider = new TableSlider("${postUrl}");
	
	slider.setTrigger("input[type='submit']");
	slider.setTargetTable("table");
</script>
</html>