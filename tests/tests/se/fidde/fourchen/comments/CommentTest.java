package tests.se.fidde.fourchen.comments;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.util.Tools;

public class CommentTest {

	Comment comment = null;
	@Before
	public void setUp() throws Exception {
		comment = Tools.getMockComment();
	}

	@After
	public void tearDown() throws Exception {
		comment = null;
	}

	@Test
	public void testId_valid() {
		long expected = comment.getID() + 1;
		long actual = Tools.getMockComment().getID();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testId_invalid() {
		long expected = comment.getID();
		long actual = Tools.getMockComment().getID();
		assertNotEquals(expected, actual);
	}

}
