package org.crm.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(urlPatterns = "/*", initParams = { @WebInitParam(name = "charset", value = "utf-8") })
public class CharsetFilter implements Filter {
	public static FilterConfig config = null;

	@Override
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		response.setContentType("text/html;charset="+config.getInitParameter("charset"));
		String serverInfo = request.getServletContext().getServerInfo();
		System.out.println(serverInfo);
		int idx = serverInfo.lastIndexOf("/");
		String version = serverInfo.substring(idx+1, idx+2);
		if("post".equalsIgnoreCase(((HttpServletRequest)request).getMethod())) {
			request.setCharacterEncoding(config.getInitParameter("charset"));
			chain.doFilter(request, response);
		}else {
			if(Integer.valueOf(version)>=7) {
				chain.doFilter(request, response);
			}else {
				chain.doFilter(new MyHttpServlet(req,config.getInitParameter("charset")).req, response);
			}
			
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		config = fConfig;
	}

	class MyHttpServlet extends HttpServletRequestWrapper {
		private HttpServletRequest req = null;
		private String charset;

		public MyHttpServlet(HttpServletRequest request, String charset) {
			super(request);
			req = request;
			this.charset = charset;
		}

		public String getParameter(String param) {
			String str = req.getParameter(param);
			try {
				str = new String(param.getBytes("ISO-8859-1"), charset);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}

	}

}