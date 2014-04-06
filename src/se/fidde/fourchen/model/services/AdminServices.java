package se.fidde.fourchen.model.services;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;


public enum AdminServices implements AdminService{
	INSTANCE;

	private ThreadService threads = ForumThreads.INSTANCE;
	
	@Override
	public boolean removeThread(long id) {return threads.removeThread(id); }

	@Override
	public boolean removeComment(long threadId, long commentId) {
		ForumThread thread = threads.getThread(threadId);
		if(thread instanceof EmptyThread)
			return false;
		
		Comment comment = thread.getComments().get(commentId);
		if(comment == null)
			return false;
		
		comment.setRemoved(true);
		return true;
	}

}
