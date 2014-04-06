package se.fidde.fourchen.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.users.User;

public class Tools {

	public static final int INTERNAL_ERROR = 500;
	public static final int PAGE_NOT_FOUND = 404;
	public static final int REDIRECTED = 302;
	public static final int OK = 200;
	
	public static ForumThread getMockThread(){
		return new ForumThread(Keys.MOCK_NAME.toString(), getMockComment());
	}

	public static User getMockUser(){
		return new User(Keys.MOCK_NAME.toString(), Keys.MOCK_EMAIL.toString());
	}
	
	public static Comment getMockComment(){
		User user = getMockUser();
		return new Comment(user, Keys.MOCK_TEXT.toString());
	}

	public static void setTemplateUrls(HttpServletRequest req) {
		req.setAttribute(Keys.HEADER.toString(), RelativeUrl.HEADER_JSP.toString());
		req.setAttribute(Keys.FORM.toString(), RelativeUrl.FORM_JSP.toString());
		req.setAttribute(Keys.TABLE.toString(), RelativeUrl.TABLE_JSP.toString());
	}
	
	public static <T> List<Long> getSortedIdList(Map<Long, T> map){
		List<Long> result = new ArrayList<>();
		for(Long id : map.keySet())
			result.add(id);

		Collections.sort(result);
		return result;
	}
}
