package tests.se.fidde.fourchen.services;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.AdminService;
import se.fidde.fourchen.model.services.AdminServices;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.services.ThreadService;
import se.fidde.fourchen.util.Keys;
import se.fidde.fourchen.util.Tools;

public class AdminServiceTest {

	private AdminService admin;
	private ThreadService threads;
	private Comment firstComment;
	private long threadId;
	private long commentId;
	
	@Before
	public void setUp() throws Exception {
		threads = ForumThreads.INSTANCE;
		ForumThread thread = Tools.getMockThread();
		firstComment = thread.getFIRST_COMMENT();
		threadId = thread.getID();
		commentId = thread.getFIRST_COMMENT().getID();
		threads.addThread(thread);
		admin = AdminServices.INSTANCE;
	}

	@After
	public void tearDown() throws Exception {
		firstComment = null;
		threads.removeThread(threadId);
		threads = null;
		admin = null;
	}

	@Test
	public void testRemoveThread_valid() {
		boolean actual = admin.removeThread(threadId);
		assertTrue(actual);
		
		ForumThread thread = threads.getThread(0);
		assertTrue(thread instanceof EmptyThread);
	}
	
	@Test
	public void testRemoveThread_invalid() {
		boolean actual = admin.removeThread(-1);
		assertFalse(actual);
		
		ForumThread thread = threads.getThread(0);
		assertTrue(thread instanceof ForumThread);
	}
	
	@Test
	public void testRemoveComment_valid() {
		boolean actual = admin.removeComment(threadId, commentId);
		assertTrue(actual);
		
		actual = firstComment.getTEXT().equals(Keys.REMOVED_COMMENT.toString());
		assertTrue(actual);
	}
	
	@Test
	public void testRemoveComment_invalid() {
		boolean actual = admin.removeComment(-1, -1);
		assertFalse(actual);
		
		actual = firstComment.getTEXT().equals(Keys.REMOVED_COMMENT.toString());
		assertFalse(actual);
	}

}
