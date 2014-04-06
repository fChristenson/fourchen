package se.fidde.fourchen.model.comments;

import java.text.SimpleDateFormat;
import java.util.Date;

import se.fidde.fourchen.model.users.User;
import se.fidde.fourchen.util.Keys;

public class Comment {
	
	private static long commentId = 0;
	private final User USER;
	private final Date DATE;
	private final String TEXT;
	private final long ID;
	private volatile boolean isRemoved;
	
	public Comment(User user, String text) {
		synchronized (this) {
			USER = user;
			TEXT = newLineToBr(text).trim();

			if(this instanceof EmptyComment){
				DATE = new Date();
				ID = -1;
				
			}else{
				DATE = new Date();
				ID = commentId++;
			}
		}
	}
	
	private String newLineToBr(String text) {return text.replaceAll("\n", "<br/>");}

	public User getUSER() {return USER;}

	public synchronized String getDATE() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd hh:mm");
		return dateFormat.format(DATE);
	}

	public String getTEXT() {return (isRemoved) ? Keys.REMOVED_COMMENT.toString() : TEXT;}

	public long getID() {return ID;}
	
	public void setRemoved(boolean isRemoved) {this.isRemoved = isRemoved;}

	public boolean isRemoved() {return isRemoved;}
}
