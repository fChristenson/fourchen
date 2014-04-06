package se.fidde.fourchen.controller.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.util.ForumServletUtil;
import se.fidde.fourchen.util.RelativeUrl;
import se.fidde.fourchen.util.Tools;

public class ForumServlet extends HttpServlet {
	private static final long serialVersionUID = 1420097883800703732L;
	private Logger log = Logger.getLogger(getClass());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("doGet start");
		try {
			ForumServletUtil.applyAdminThreadDelete(req);
			ForumServletUtil.setRequestAttributes(req);
		} catch (Exception e) {
			log.debug("doGet exception");
			resp.setStatus(Tools.INTERNAL_ERROR);
		}
		log.debug("doGet complete");
		RequestDispatcher dispatcher = req
				.getRequestDispatcher(RelativeUrl.FORUM_JSP.toString());
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		log.debug("doPost start");
		try {
			ForumThread thread = ForumServletUtil.parseThread(req);
			if (thread instanceof EmptyThread)
				throw new Exception("Could not parse thread");

			ForumThreads.INSTANCE.addThread(thread);
			ForumServletUtil.setRequestAttributes(req);

			String url = resp.encodeRedirectURL(req.getContextPath()
					+ RelativeUrl.FORUM_SERVLET.toString());

			resp.sendRedirect(url);
			log.debug("doPost complete");

		} catch (Exception e) {
			log.debug("doPost exception");
			resp.setStatus(Tools.INTERNAL_ERROR);
		}

	}
}
