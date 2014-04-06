package se.fidde.fourchen.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.util.RelativeUrl;
import se.fidde.fourchen.util.ThreadServletUtil;
import se.fidde.fourchen.util.Tools;

public class ThreadServlet extends HttpServlet {

	private static final long serialVersionUID = -405534731418779684L;
	private Logger log = Logger.getLogger(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("doGet start");
		RequestDispatcher dispatcher = req
				.getRequestDispatcher(RelativeUrl.THREAD_JSP.toString());

		try {
			ForumThread thread = ThreadServletUtil.getThread(req);
			if (thread instanceof EmptyThread)
				throw new Exception("Thread does not exist!");

			ThreadServletUtil.applyAdminUpdate(req);
			ThreadServletUtil.setAttributes(req, thread);

		} catch (Exception e) {
			log.debug("doGet exception");
			resp.setStatus(Tools.INTERNAL_ERROR);
		}
		log.debug("doGet complete");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("doPost start");
		try {
			ForumThread thread = ThreadServletUtil.getThread(req);
			if (thread instanceof EmptyThread) {
				throw new Exception("Thread could not be found!");

			} else {
				Comment comment = ThreadServletUtil.parseComment(req);
				thread.getComments().put(comment.getID(), comment);
				log.debug("doPost comment: " + comment.getID() + " added");
			}

		} catch (Exception e) {
			log.debug("doPost exception");
			resp.setStatus(Tools.INTERNAL_ERROR);
		}
		log.debug("doPost complete");
		String url = resp.encodeRedirectURL(req.getContextPath()
				+ RelativeUrl.FORUM_SERVLET.toString());

		resp.sendRedirect(url);
	}
}
