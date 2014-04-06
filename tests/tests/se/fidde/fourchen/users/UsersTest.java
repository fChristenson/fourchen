package tests.se.fidde.fourchen.users;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Random;

import org.junit.Test;

import se.fidde.fourchen.model.users.User;
public class UsersTest {

	private final String NAME1 = "foo";
	private final String NAME2 = "bar";
	private final String EMAIL1 = "foo@bar.se";
	private final String EMAIL2 = "foo@bar.com";

	@Test
	public void testGetUserHash_valid_lower() {
		long expected = new User(NAME1, EMAIL1).getUSER_HASH();
		long actual = new User(NAME1, EMAIL1).getUSER_HASH();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUserHash_invalid_lower() {
		long expected = new User(NAME2, EMAIL2).getUSER_HASH();
		long actual = new User(NAME1, EMAIL1).getUSER_HASH();
		assertNotEquals(expected, actual);
	}

	@Test
	public void testGetUserHash_valid_upper() {
		long expected = new User(NAME1.toUpperCase(), EMAIL1.toUpperCase()).getUSER_HASH();
		long actual = new User(NAME1.toUpperCase(), EMAIL1.toUpperCase()).getUSER_HASH();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUserHash_invalid_upper() {
		long expected = new User(NAME2, EMAIL2).getUSER_HASH();
		long actual = new User(NAME1.toUpperCase(), EMAIL1.toUpperCase()).getUSER_HASH();
		assertNotEquals(expected, actual);
	}

	@Test
	public void testGetUserHash_valid_randomCase() {
		String name = getRandomCaseString(NAME1);
		String email = getRandomCaseString(EMAIL1);
		long expected = new User(name, email).getUSER_HASH();
		long actual = new User(name, email).getUSER_HASH();
		assertEquals(expected, actual);
	}

	@Test
	public void testGetUserHash_invalid_randomCase() {
		String name = getRandomCaseString(NAME1);
		String email = getRandomCaseString(EMAIL1);
		String name2 = getRandomCaseString(NAME2);
		String email2 = getRandomCaseString(EMAIL2);

		long expected = new User(name2, email2).getUSER_HASH();
		long actual = new User(name, email).getUSER_HASH();
		assertNotEquals(expected, actual);
	}

	private String getRandomCaseString(String string) {
		StringBuilder builder = new StringBuilder();
		String letter = "";

		for (int i = 0; i < string.length(); i++) {
			letter = string.substring(i, i + 1);
			builder.append(getRandomCaseLetter(letter));

		}
		return builder.toString();
	}

	private String getRandomCaseLetter(String letter) {
		Random rand = new Random();
		int i = rand.nextInt(2);

		if (i <= 0)
			return letter.toLowerCase();

		return letter.toUpperCase();
	}

}
