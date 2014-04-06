package se.fidde.fourchen.model.forumthreads;

import se.fidde.fourchen.model.comments.EmptyComment;
import se.fidde.fourchen.util.Keys;

public final class EmptyThread extends ForumThread {

	public EmptyThread() {
		super(Keys.EMPTY.toString(), new EmptyComment());
	}

}
