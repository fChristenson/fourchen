package se.fidde.fourchen.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.AdminService;
import se.fidde.fourchen.model.services.AdminServices;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.users.User;

public class ForumServletUtil {

	public static ForumThread parseThread(HttpServletRequest req){
		if(req == null)
			return new EmptyThread();
		
		try {
			String threadName = req.getParameter(Keys.THREAD.toString());
			String userName = req.getParameter(Keys.USERNAME.toString());
			String email = req.getParameter(Keys.EMAIL.toString());
			String commentString = req.getParameter(Keys.COMMENT.toString());
			
			User user = new User(userName, email);
			Comment comment = new Comment(user, commentString);
			return new ForumThread(threadName, comment);
			
		} catch (Exception e) {return new EmptyThread();}			
		
	}

	public static boolean applyAdminThreadDelete(HttpServletRequest req) {
		if(req == null)
			return false;
		
		try{
		String idString = req.getParameter(Keys.DELETE.toString());
			long id = Long.parseLong(idString); 
			AdminService admin = AdminServices.INSTANCE;
			admin.removeThread(id);
			return true;
			
		}catch(Exception e){return false;}
	}

	public static boolean setRequestAttributes(HttpServletRequest req) {
		if(req == null)
			return false;
		
		try {
			setPostUrl(req);
			setThreads(req);
			setThreadUrl(req);
			setAdminUrl(req);
			req.setAttribute(Keys.DELETE_URL.toString(), req.getContextPath() + RelativeUrl.DELETE_FORUM.toString());
			Tools.setTemplateUrls(req);
			return true;
			
		} catch (Exception e) {return false;}
	}

	private static void setAdminUrl(HttpServletRequest req) {
		assert req != null;
		
		String adminUrl = req.getContextPath() + RelativeUrl.ADMIN_FORUM.toString();
		req.setAttribute(Keys.ADMIN_URL.toString(), adminUrl);
	}

	private static void setThreadUrl(HttpServletRequest req) {
		assert req != null;
		
		StringBuilder urlString = new StringBuilder(req.getContextPath()).append(RelativeUrl.THREAD_SERVLET.toString()).append("?")
				.append(Keys.THREAD_ID.toString()).append("=");
		
		req.setAttribute(Keys.THREAD_URL.toString(), urlString);
	}

	private static void setThreads(HttpServletRequest req) {
		assert req != null;
		
		List<ForumThread> threads = ForumThreads.INSTANCE.getAllThreadsAsSortedList();
		req.setAttribute(Keys.FORUM_THREADS.toString(), threads);
	}

	private static void setPostUrl(HttpServletRequest req) {
		assert req != null;
		
		String postUrl = req.getContextPath() + RelativeUrl.FORUM_SERVLET;
		req.setAttribute(Keys.POST_URL.toString(), postUrl);
	}
}
