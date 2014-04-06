package se.fidde.fourchen.util;

public enum RelativeUrl {

	FORUM_SERVLET("/Forum.servlet"), THREAD_SERVLET("/Thread.servlet"), FORUM_JSP("/WEB-INF/jsp/forum.jsp"), THREAD_JSP("/WEB-INF/jsp/thread.jsp"),
	HEADER_JSP("/WEB-INF/jsp/templates/header.jsp"), FORM_JSP("/WEB-INF/jsp/templates/form.jsp"), TABLE_JSP("/WEB-INF/jsp/templates/table.jsp"),
	POST_JSP("/WEB-INF/jsp/templates/post.jsp"), ADMIN_FORUM("/Forum.servlet?token=admin"), ADMIN_THREAD("/Thread.servlet?token=admin&threadId="),
	DELETE_FORUM("/Forum.servlet?delete="), DELETE_THREAD("/Thread.servlet?threadId=");
	
	private final String NAME;
	
	private RelativeUrl(String name) {NAME = name;}
	
	@Override
	public String toString() {return NAME;}
}
