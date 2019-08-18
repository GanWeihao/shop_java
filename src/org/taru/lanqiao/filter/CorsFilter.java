package org.taru.lanqiao.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CorsFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest  request  =(HttpServletRequest)req;
		HttpServletResponse response  =(HttpServletResponse)res;
		
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin")); //*������Я��ƾ֤ ��cookie��  
		/* ������������󷽷�GET, POST, HEAD �� */
		response.setHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET");    //
		/* ����Ԥ�������Ļ���ʱ�� (s) */
		response.setHeader("Access-Control-Max-Age", "3600");  
		/* �������������ͷ */
		response.setHeader("Access-Control-Allow-Headers", "*");  
		/* �Ƿ�Я��cookie */
		response.setHeader("Access-Control-Allow-Credentials", "true");  
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}