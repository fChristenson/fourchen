package se.fidde.fourchen.model.forumthreads;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.util.Tools;

public class ForumThread {
	private static long threadCount = 0;
	private final long ID;
	private final String NAME;
	private final Comment FIRST_COMMENT;
	private Map<Long, Comment> comments;
	
	public ForumThread(String name, Comment firstComment) {
		NAME = name;
		FIRST_COMMENT = firstComment;
	
		synchronized (this) {
			if(this instanceof EmptyThread){
				comments = new ConcurrentHashMap<Long, Comment>();
				ID = -1;
				
			}else{
				comments = new ConcurrentHashMap<Long, Comment>();
				comments.put(FIRST_COMMENT.getID(), FIRST_COMMENT);
				ID = threadCount++;
			}
		}
	}

	public long getID() {return ID;}

	public String getNAME() {return NAME;}
	
	public Comment getFIRST_COMMENT() {return FIRST_COMMENT;}
	
	public synchronized Map<Long, Comment> getComments() {return comments;}

	public synchronized List<Comment> getCommentsAsSortedList() {
		List<Long> commentId = Tools.getSortedIdList(comments);
		List<Comment> result = new ArrayList<>();
		
		for(Long id : commentId){
			Comment comment = comments.get(id);
			result.add(comment);
		}
		return result;
	}

}
