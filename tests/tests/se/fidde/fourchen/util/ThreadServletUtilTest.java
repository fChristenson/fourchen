package tests.se.fidde.fourchen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.comments.Comment;
import se.fidde.fourchen.model.comments.EmptyComment;
import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.services.ThreadService;
import se.fidde.fourchen.util.Keys;
import se.fidde.fourchen.util.ThreadServletUtil;
import se.fidde.fourchen.util.Tools;

public class ThreadServletUtilTest {

	private ThreadService service;
	private HttpServletRequest req;
	private long threadId;
	private long commentId;
	private ForumThread thread;
	
	@Before
	public void setUp() throws Exception {
		
		service = ForumThreads.INSTANCE;
		thread = Tools.getMockThread();
		threadId = thread.getID();
		commentId = thread.getFIRST_COMMENT().getID();
		service.addThread(thread);

		req = EasyMock.createNiceMock(HttpServletRequest.class);
		
		EasyMock.expect(req.getParameter(Keys.THREAD_ID.toString())).andReturn(String.valueOf(threadId));
		EasyMock.expect(req.getParameter(Keys.USERNAME.toString())).andReturn(Keys.MOCK_NAME.toString());
		EasyMock.expect(req.getParameter(Keys.EMAIL.toString())).andReturn(Keys.MOCK_EMAIL.toString());
		EasyMock.expect(req.getParameter(Keys.COMMENT.toString())).andReturn(Keys.MOCK_TEXT.toString());
		EasyMock.expect(req.getContextPath()).andReturn(Keys.CONTEXT_PATH.toString());
		EasyMock.expect(req.getParameter(Keys.DELETE.toString())).andStubReturn(String.valueOf(commentId));
		EasyMock.expect(req.getParameterMap()).andStubReturn(new HashMap<String, String[]>());

		EasyMock.replay(req);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
		req = null;
	}

	@Test
	public void testGetThread_valid() {
		ForumThread actual = ThreadServletUtil.getThread(req);
		
		assertTrue(actual instanceof ForumThread);
	}
	
	@Test
	public void testGetThread_invalid() {
		ForumThread actual = ThreadServletUtil.getThread(null);
		
		assertTrue(actual instanceof EmptyThread);
	}
	
	@Test
	public void testParseComment_valid() {
		Comment actual = ThreadServletUtil.parseComment(req);
		
		assertTrue(actual instanceof Comment);
	}
	
	@Test
	public void testParseComment_invalid() {
		Comment actual = ThreadServletUtil.parseComment(null);
		
		assertTrue(actual instanceof EmptyComment);
	}
	
	@Test
	public void testApplyAdminUpdate_valid() {
		ThreadServletUtil.applyAdminUpdate(req);
		ForumThread thread = service.getThread(threadId);
		
		assertTrue(thread.getFIRST_COMMENT().isRemoved());
	}
	
	@Test
	public void testApplyAdminUpdate_invalid() {
		ThreadServletUtil.applyAdminUpdate(null);
		ForumThread thread = service.getThread(threadId);
		
		assertFalse(thread.getFIRST_COMMENT().isRemoved());
	}
	
	@Test
	public void testSetAttributes_valid() {
		boolean expected = true;
		boolean actual = ThreadServletUtil.setAttributes(req, thread);
		
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testSetAttributes_invalid() {
		boolean expected = false;
		boolean actual = ThreadServletUtil.setAttributes(null, null);
		
		assertEquals(expected, actual);
	}
}
