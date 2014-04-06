package se.fidde.fourchen.util;

import javax.servlet.http.HttpServletRequest;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.comments.EmptyComment;
import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.AdminService;
import se.fidde.fourchen.model.services.AdminServices;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.users.User;

public class ThreadServletUtil {

	public static ForumThread getThread(HttpServletRequest req) {
		if(req == null)
			return new EmptyThread();
		
		try{
			String threadIdString = req.getParameter(Keys.THREAD_ID.toString());
			long threadId = Long.parseLong(threadIdString);
			
			ForumThread thread = ForumThreads.INSTANCE.getThread(threadId);
			return thread;
			
		}catch(Exception e){return new EmptyThread();}
	}

	public static Comment parseComment(HttpServletRequest req) {
		if(req == null)
			return new EmptyComment();
			
		try{
			String userName = req.getParameter(Keys.USERNAME.toString());
			String email = req.getParameter(Keys.EMAIL.toString());
			String text = req.getParameter(Keys.COMMENT.toString()).trim();
			User user = new User(userName, email);
			
			return new Comment(user, text);
			
		} catch(Exception e){return new EmptyComment();}
	}

	public static boolean applyAdminUpdate(HttpServletRequest req) {
		if(req == null)
			return false;
		
		try{
			String threadIdString = req.getParameter(Keys.THREAD_ID.toString());
			String commentIdString = req.getParameter(Keys.DELETE.toString());
			if(commentIdString != null && threadIdString != null){
					long commentId = Long.parseLong(commentIdString);
					long threadId = Long.parseLong(threadIdString);
					AdminService admin = AdminServices.INSTANCE;
					admin.removeComment(threadId, commentId);
					return true;
			}
			
			return false;
		}catch(Exception e){return false;}
	}

	public static boolean setAttributes(HttpServletRequest req, ForumThread thread) {
		if(req == null || thread == null)
			return false;
		
		try{
			setThreadUrlWithId(req, thread);
			setPostUrl(req);
			setAdminUrl(req, thread);
			Tools.setTemplateUrls(req);
			req.setAttribute(Keys.POST.toString(), RelativeUrl.POST_JSP.toString());
			req.setAttribute(Keys.THREAD.toString(), thread);
			setDeleteUrl(req, thread);
			req.setAttribute(Keys.COMMENTS.toString(), thread.getCommentsAsSortedList());
			return true;
			
		}catch(Exception e){return false;}
	}

	private static void setDeleteUrl(HttpServletRequest req, ForumThread thread) {
		assert req != null && thread != null;
		
		String idString = String.valueOf(thread.getID());
		StringBuilder id = new StringBuilder(idString).append("&").append(Keys.DELETE.toString()).append("=");
		req.setAttribute(Keys.DELETE_URL.toString(), req.getContextPath() + RelativeUrl.DELETE_THREAD.toString() + id);
	}

	private static void setAdminUrl(HttpServletRequest req, ForumThread thread) {
		assert req != null && thread != null;
		
		String adminUrl = req.getContextPath() + RelativeUrl.ADMIN_THREAD.toString() + thread.getID();
		req.setAttribute(Keys.ADMIN_URL.toString(), adminUrl);
	}

	private static void setPostUrl(HttpServletRequest req) {
		assert req != null;
		
		String postUrl = req.getContextPath() + RelativeUrl.THREAD_SERVLET;
		req.setAttribute(Keys.POST_URL.toString(), postUrl);
	}

	private static void setThreadUrlWithId(HttpServletRequest req, ForumThread thread) {
		assert req != null && thread != null;
		StringBuilder urlString = new StringBuilder(req.getContextPath()).append(RelativeUrl.THREAD_SERVLET.toString()).append("?")
				.append(Keys.THREAD_ID.toString()).append("=").append(thread.getID());
		req.setAttribute(Keys.THREAD_URL.toString(), urlString.toString());
	}
}
