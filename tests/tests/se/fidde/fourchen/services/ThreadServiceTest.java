package tests.se.fidde.fourchen.services;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.forumthreads.EmptyThread;
import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.services.ThreadService;
import se.fidde.fourchen.util.Tools;

public class ThreadServiceTest {

	private ThreadService service;
	private long threadId;
	
	@Before
	public void setUp() throws Exception {
		ForumThread thread = Tools.getMockThread();
		threadId = thread.getID();
		service = ForumThreads.INSTANCE;
		service.addThread(thread);
	}

	@After
	public void tearDown() throws Exception {
		service.removeThread(threadId);
		service = null;
	}

	@Test
	public void testGetAllThreads_valid() {
		int expected = 1;
		int actual = service.getAllThreads().size();
		assertTrue(actual >= expected);
	}
	
	@Test
	public void testGetAllThreads_invalid() {
		int expected = -1;
		int actual = service.getAllThreads().size();
		assertNotEquals(expected , actual);
	}

	@Test
	public void testGetThread_valid() {
		assertTrue(service.getThread(threadId) instanceof ForumThread);
	}
	
	@Test
	public void testGetThread_invalid() {
		assertTrue(service.getThread(-1) instanceof EmptyThread);
	}
	
	@Test
	public void testGetAllThreadsAsSortedList_valid() {
		List<ForumThread> list = service.getAllThreadsAsSortedList();
		assertNotNull(list);
		
		boolean isSorted = true;
		
		for(int i = 0; i < (list.size() - 1); i++){
			long id1 = list.get(i).getID();
			long id2 = list.get(i + 1).getID();
			if(id1 < id2){
				isSorted = false;
				break;
			}
		}
		assertTrue(isSorted);
	}

}
