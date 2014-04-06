package tests.se.fidde.fourchen.servlet;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.fidde.fourchen.model.forumthreads.ForumThread;
import se.fidde.fourchen.model.services.ForumThreads;
import se.fidde.fourchen.model.services.ThreadService;
import se.fidde.fourchen.util.Keys;
import se.fidde.fourchen.util.Tools;

public class ForumServletTest {

	private final String URL = "http://localhost:8080/fourChen/Forum.servlet";
	
	//I am aware that tests should be independant but as i am too lazy to create a inner ThreadService class so there will be one dependancy
	private ThreadService service;
	private CloseableHttpClient client;
	private HttpGet get;
	private HttpPost post;
	private CloseableHttpResponse response;
	private ForumThread thread;
	
	
	@Before
	public void setUp() throws Exception {
		service = ForumThreads.INSTANCE;
		thread = Tools.getMockThread();
		service.addThread(thread);
		client = HttpClients.createDefault();
		get = new HttpGet(URL);

		post = new HttpPost(URL);
		List<NameValuePair> list = new ArrayList<>();
		list.add(new BasicNameValuePair(Keys.THREAD.toString(), Keys.MOCK_NAME.toString()));
		list.add(new BasicNameValuePair(Keys.USERNAME.toString(), Keys.MOCK_NAME.toString()));
		list.add(new BasicNameValuePair(Keys.EMAIL.toString(), Keys.MOCK_EMAIL.toString()));
		list.add(new BasicNameValuePair(Keys.COMMENT.toString(), Keys.MOCK_TEXT.toString()));
		
		post.setEntity(new UrlEncodedFormEntity(list));
	}

	@After
	public void tearDown() throws Exception {
		service.removeThread(thread.getID());
		service = null;
		get = null;
		post = null;
		client = null;
	}

	@Test
	public void testGet_valid() {
		try {
			response = client.execute(get);
			int expected = Tools.OK;
			int actual = response.getStatusLine().getStatusCode();
			assertEquals(expected, actual);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testGet_invalid() {
		try {
			response = client.execute(new HttpGet(URL + "/foo"));
			int expected = Tools.PAGE_NOT_FOUND;
			int actual = response.getStatusLine().getStatusCode();
			assertEquals(expected, actual);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testPost_valid() {
		try {
			response = client.execute(post);
			int expected = Tools.REDIRECTED;
			int actual = response.getStatusLine().getStatusCode();
			assertEquals(expected, actual);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	@Test
	public void testPost_invalid() {
		try {
			response = client.execute(new HttpPost(URL));
			int expected = Tools.INTERNAL_ERROR;
			int actual = response.getStatusLine().getStatusCode();
			assertEquals(expected, actual);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
