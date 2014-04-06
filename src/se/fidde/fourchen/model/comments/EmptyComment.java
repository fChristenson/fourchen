package se.fidde.fourchen.model.comments;

import se.fidde.fourchen.model.users.EmptyUser;
import se.fidde.fourchen.util.Keys;

public final class EmptyComment extends Comment {

	public EmptyComment() {
		super(new EmptyUser(), Keys.EMPTY.toString());
	}

}
