<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="post">
	<header>
		<img alt="user" src="view/assets/images/user.jpg">
		<label>User: ${param.user }</label>
		<label>Posted: ${param.date }</label>
	</header>
	<article>
		${param.text }
		
		<c:if test="${admin}">
			<a href="${param.delete }">Delete</a>
		</c:if>
	</article>
</div>