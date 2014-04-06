package se.fidde.fourchen.model.services;

public interface AdminService {

	public boolean removeThread(long id);
	
	public boolean removeComment(long threadId, long commentId);
}
