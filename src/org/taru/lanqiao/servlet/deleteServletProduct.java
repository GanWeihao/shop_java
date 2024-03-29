package org.taru.lanqiao.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.service.productServiceImpl;
import org.taru.lanqiao.vo.JsonResult;
import org.taru.lanqiao.util.Constants;
import org.taru.lanqiao.util.JsonWriter;

/**
 * 管理员废除删除商品
 * @author Administrator
 *
 */
@WebServlet("/product/drop")
public class deleteServletProduct extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productId=request.getParameter("productId");
		productServiceImpl serviceImpl=new productServiceImpl();
		JsonResult result=null;
		try {
			int row=serviceImpl.deleteProductById(productId);
			result=new JsonResult(Constants.STATUS_SUCCESS,"成功",row);
		}catch(Exception e) {
			result=new JsonResult(Constants.STATUS_ERROR,"成功");
		}
		JsonWriter.writer(response, result);
	}
}
