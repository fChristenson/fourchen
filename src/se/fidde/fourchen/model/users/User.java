package se.fidde.fourchen.model.users;

public class User {

	private final long USER_HASH;
	
	public User(String name, String email) {
		synchronized (this) {
			USER_HASH = createUserHash(name, email);
		}
	}
	
	public long getUSER_HASH() {return USER_HASH;}
	
	private final long createUserHash(String name, String email){
		int result = 6;
		int prime = 31;
		char[] nameArray = name.toCharArray();
		char[] emailArray = email.toCharArray();

		int value = 0;
		for(char letter : nameArray)
			value += letter;
		
		result = prime * result + value;
		
		for(char letter : emailArray)
			value += letter;
			
		return prime * result + value;
	}
}
