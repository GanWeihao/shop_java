package org.taru.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.orderServiceImpl;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.util.Constants;
import org.taru.lanqiao.util.JsonWriter;

/**
 * 
 * @author Administrator
 *订单发货
 */
@WebServlet("/order/send")
public class orderSendServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderid=request.getParameter("orderid");
		orderServiceImpl serviceImpl=new orderServiceImpl();
		JsonResult result=null;
		try {
			int row=serviceImpl.orderSend(orderid);
			result=new JsonResult(Constants.STATUS_SUCCESS,"成功",row);
		}catch(Exception e) {
			result=new JsonResult(Constants.STATUS_ERROR,"失败");
		}
		JsonWriter.writer(response, result);
	}
}
