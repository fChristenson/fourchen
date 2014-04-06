package tests.se.fidde.fourchen.forumthreads;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.util.Tools;

public class ForumThreadTest {

	private ForumThread thread;
	private ForumThread thread2;
	private List<Comment> list;
	private Object commentId; 

	
	@Before
	public void setUp() throws Exception {
		thread = Tools.getMockThread();
		thread2 = Tools.getMockThread();
		commentId = thread.getFIRST_COMMENT().getID();
	}

	@After
	public void tearDown() throws Exception {
		thread = null;
	}

	@Test
	public void testID_valid() {
		long expected = thread.getID() + 1;
		long actual = thread2.getID();
		assertEquals(expected, actual);
	}

	@Test
	public void testID_invalid() {
		long expected = thread.getID();
		long actual = thread2.getID();
		assertNotEquals(expected, actual);
	}
	
	@Test
	public void testGetComment_valid() {
		Comment actual = thread.getComments().get(commentId);
		assertNotNull(actual);
	}

	@Test
	public void testGetComment_invalid() {
		try{
			thread.getComments().get(-1);
			
		} catch(IndexOutOfBoundsException e){
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void testGetCommentsAsSortedList_valid() {
		list = thread.getCommentsAsSortedList();
		assertNotNull(list);
		
		boolean isSorted = true;
		long threadId = -1;
		
		for(Comment comment : list){
			if(threadId > comment.getID()){
				isSorted = false;
				break;
			}
			threadId = comment.getID();
		}
		assertTrue(isSorted);
	}

	@Test
	public void testGetCommentsAsSortedList_invalid() {
		list = thread.getCommentsAsSortedList();
		assertNotNull(list);
		
		list.set(0, Tools.getMockComment());
		boolean isSorted = true;
		long threadId = -1;
		
		for(Comment comment : list){
			if(threadId > comment.getID()){
				isSorted = false;
				break;
			}
			threadId = comment.getID();
		}
		assertTrue(isSorted);
	}
}
