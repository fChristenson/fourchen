package se.fidde.fourchen.util;

public enum Keys {

	FORUM_THREADS("forumThreads"), EMPTY("empty"), MOCK_NAME("foo"), MOCK_EMAIL("foo@bar.se"), MOCK_TEXT("foo bar baz"), 
	THREAD("thread"), THREAD_ID("threadId"), USER_ID("userId"), USERNAME("userName"), EMAIL("email"), COMMENT("comment"),
	THREAD_URL("threadUrl"), POST_URL("postUrl"), COMMENTS("comments"), FIRST_COMMENT("firstComment"), REMOVED_COMMENT("Comment Removed"),
	ADMIN("admin"), ADMIN_URL("adminUrl"), HEADER("headerJsp"), FORM("formJsp"), POST("postJsp"), TABLE("tableJsp"), DELETE_URL("deleteUrl"),
	DELETE("delete"), CONTEXT_PATH("/fourChen/");
	
	private final String NAME;
	
	private Keys(String name) {NAME = name;}
	
	@Override
	public String toString() {return NAME;}
}
