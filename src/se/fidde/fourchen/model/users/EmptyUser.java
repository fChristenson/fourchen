package se.fidde.fourchen.model.users;

import se.fidde.fourchen.util.Keys;

public final class EmptyUser extends User {

	public EmptyUser() {
		super(Keys.EMPTY.toString(), Keys.EMPTY.toString());
	}

}
