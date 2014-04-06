<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thread</title>
<link rel="stylesheet" href="view/css/main.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<section>
		<c:import url="${headerJsp }">
			<c:param name="url" value="${adminUrl }"></c:param>
		</c:import>
		
		<c:import url="${formJsp }">
			<c:param name="isNewThread" value="false"></c:param>
			<c:param name="postUrl" value="${postUrl}"></c:param>
			<c:param name="id" value="${thread.ID }"></c:param>
		</c:import>

		<h1>${thread.NAME }</h1>		
		<c:forEach items="${comments }" var="comment">
			<c:import url="${postJsp}">
				<c:param name="user" value="${comment.USER.USER_HASH }"></c:param>
				<c:param name="date" value="${comment.DATE }"></c:param>
				<c:param name="text" value="${comment.TEXT }"></c:param>
				<c:param name="delete" value="${deleteUrl }${comment.ID }"></c:param>
			</c:import>
		</c:forEach>
	</section>
</body>
<script>

</script>
</html>