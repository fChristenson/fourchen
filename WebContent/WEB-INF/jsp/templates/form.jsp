<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<form class="comment" method="post" action="${param.postUrl }">
		<c:choose>
			<c:when test="${param.isNewThread }">
				<label for="threadName">Thread Title 
					<span>
						<em><small>(required)</small></em> 
					</span>
				</label>
				<input id="threadName" name="thread" type="text" required autofocus placeholder="Thread Title" tabindex="1">
			</c:when>
			
			<c:otherwise>
				<input type="hidden" name="threadId" value="${param.id }">
			</c:otherwise>
		</c:choose>
		
		<label for="userName">User name 
			<span>
				<em><small>(required)</small></em> 
			</span>
		</label>
		<input id="userName" name="userName" required type="text" placeholder="First Name" tabindex="2">
		
		<label for="email">Email 
			<span>
				<em><small>(required)</small></em>
			</span>
		</label>
		<input id="email" name="email" type="email" required placeholder="Email" tabindex="3">
		
		<label for="textarea">Comment 
			<span>
				<em><small>(required)</small></em> 
			</span>
		</label>
		<textarea id="textarea" name="comment" required="required" placeholder="Comment" tabindex="4"></textarea>
		<input type="submit" value="Submit" tabindex="5">
		<a href="${pageContext.request.contextPath }/Forum.servlet">Back</a>
		
		<script>
			$("form").on("change", function(event){
				var target = event.target;

				if(target.value.length > 0)
					$("label[for=" + target.id).children("span").css("color", "green");
				
				else
					$("label[for=" + target.id).children("span").css("color", "red");
			});
		</script>
</form>