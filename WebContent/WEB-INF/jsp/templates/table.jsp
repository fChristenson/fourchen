<%@page import="se.fidde.fourchen.util.Keys"%>
<%@page import="se.fidde.fourchen.util.RelativeUrl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<table class="thread-table">
	<thead> 
		<th><h2>Thread</h2></th>
		<th><h2>Posts</h2></th>
		<th><h2>Author</h2></th>
		<th><h2>Posted</h2></th>
	</thead>
	
	<tbody>
		<c:forEach items="${forumThreads }" var="thread">
			<tr>
				<td>
					<a href="${threadUrl }${thread.ID}">
						<c:out value="${thread.NAME }"></c:out>
					</a>
				</td>
				
				<td>${fn:length(thread.comments) }</td>
				
				<td>${thread.FIRST_COMMENT.USER.USER_HASH }</td>
				
				<td>
					${thread.FIRST_COMMENT.DATE}
					<c:if test="${admin}"><a href="${deleteUrl }${thread.ID}"> Delete </a></c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>