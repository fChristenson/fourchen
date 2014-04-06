package se.fidde.fourchen.controller.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import se.fidde.fourchen.util.Keys;

public class AdminFilter implements Filter{

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		
			String token = req.getParameter("token");
			if(token != null && token.equals(Keys.ADMIN.toString()))
				req.setAttribute(Keys.ADMIN.toString(),true);
			
			req.setCharacterEncoding("UTF-8");
			chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}
}
