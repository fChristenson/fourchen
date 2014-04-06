package se.fidde.fourchen.model.services;

import java.util.List;

import se.fidde.fourchen.model.forumthreads.ForumThread;


public interface ThreadService {

	public List<ForumThread> getAllThreads();
	
	public ForumThread getThread(long id);
	
	public boolean removeThread(long id);
	
	public boolean addThread(ForumThread thread);

	public List<ForumThread> getAllThreadsAsSortedList();
	
}
