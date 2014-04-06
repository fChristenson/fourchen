package tests.se.fidde.fourchen.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.services.ThreadService;
import se.fidde.fourchen.util.ForumServletUtil;
import se.fidde.fourchen.util.Keys;
import se.fidde.fourchen.util.Tools;

public class ForumServletUtilTest {

	private ThreadService service;
	private HttpServletRequest req;
	private long id;
	private ForumThread thread;
	
	@Before
	public void setUp() throws Exception {
		service = ForumThreads.INSTANCE;
		thread = Tools.getMockThread();
		id = thread.getID();
		service.addThread(thread);

		req = EasyMock.createNiceMock(HttpServletRequest.class);
		
		EasyMock.expect(req.getParameter(Keys.THREAD.toString())).andReturn(Keys.MOCK_NAME.toString());
		EasyMock.expect(req.getParameter(Keys.USERNAME.toString())).andReturn(Keys.MOCK_NAME.toString());
		EasyMock.expect(req.getParameter(Keys.EMAIL.toString())).andReturn(Keys.MOCK_EMAIL.toString());
		EasyMock.expect(req.getParameter(Keys.COMMENT.toString())).andReturn(Keys.MOCK_TEXT.toString());
		EasyMock.expect(req.getContextPath()).andReturn(Keys.CONTEXT_PATH.toString());
		EasyMock.expect(req.getParameter(Keys.DELETE.toString())).andStubReturn(String.valueOf(id));
		EasyMock.expect(req.getParameterMap()).andStubReturn(new HashMap<String, String[]>());

		EasyMock.replay(req);
	}

	@After
	public void tearDown() throws Exception {
		service = null;
		req = null;
	}

	@Test
	public void testParseThread_valid() {
		ForumThread actual = ForumServletUtil.parseThread(req);
		
		assertTrue(actual instanceof ForumThread);
	}
	
	@Test
	public void testParseThread_invalid() {
		ForumThread actual = ForumServletUtil.parseThread(null);
		
		assertTrue(actual instanceof EmptyThread);
	}
	
	@Test
	public void testSetRequestAttributes_valid() {
		int expected = req.getParameterMap().size();
		ForumServletUtil.setRequestAttributes(req);
		int actual = req.getParameterMap().size();
		
		assertTrue(expected < actual);
		
	}
	
	@Test
	public void testSetRequestAttributes_invalid() {
		int expected = req.getParameterMap().size();
		ForumServletUtil.setRequestAttributes(null);
		int actual = req.getParameterMap().size();
		
		assertTrue(expected == actual);
	}
	
	@Test
	public void testApplyAdminThreadDelete_valid() {
		
		ForumServletUtil.applyAdminThreadDelete(req);
		ForumThread actual = service.getThread(id);
		
		assertTrue(actual instanceof EmptyThread);
	}
	
	@Test
	public void testApplyAdminThreadDelete_invalid() {
		
		ForumServletUtil.applyAdminThreadDelete(null);
		long expected = id;
		long actual = service.getThread(id).getID();
		
		assertEquals(expected, actual);
	}
}
