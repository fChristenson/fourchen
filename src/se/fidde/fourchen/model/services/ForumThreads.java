package se.fidde.fourchen.model.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.util.Tools;

public enum ForumThreads implements ThreadService{

	INSTANCE;
	
	private Map<Long, ForumThread> threads;
	
	private ForumThreads() {
		threads = new ConcurrentHashMap<>();
	}

	@Override
	public synchronized ForumThread getThread(long id){
		ForumThread result = threads.get(id);
		if(result == null)
			return new EmptyThread();
			
		return result;
	}

	@Override
	public synchronized boolean removeThread(long id) {
		Object result = threads.remove(id);
		if(result == null)
			return false;
			
		return true;
	}

	@Override
	public synchronized boolean addThread(ForumThread thread) {
		threads.put(thread.getID(), thread);
		
		return true;
	}

	@Override
	public synchronized List<ForumThread> getAllThreads() {
		List<ForumThread> result = new ArrayList<>();
		
		for(ForumThread thread : threads.values())
			result.add(thread);
		
		return result;
	}

	@Override
	public synchronized List<ForumThread> getAllThreadsAsSortedList() {
		List<ForumThread> result = new ArrayList<>();
		List<Long> threadId = Tools.getSortedIdList(threads);
		Collections.reverse(threadId);
		
		for(Long id : threadId){
			ForumThread thread = threads.get(id);
			result.add(thread);
		}
		return result;
	}

}
