package org.taru.lanqiao.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.taru.lanqiao.model.OrderDetail;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.service.productServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.vo.JsonResult;

//�Ƴ����ﳵ
@WebServlet("/api/detail/cartDel")
public class DeleteOrderImpl extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonResult jsonResult=null;
		try {
			productServiceImpl impl=new productServiceImpl();
			String id=request.getParameter("detailId");
			int row=impl.deleteOrder(id);
			if(row == 1 ) {
				jsonResult=new JsonResult("200","�Ƴ����ﳵ�ɹ�",row);
			}else {
				jsonResult=new JsonResult("400","�Ƴ����ﳵʧ��");
			}
		}catch(Exception e) {
			jsonResult=new JsonResult("500","�Ƴ����ﳵʧ��",e.getMessage());
		}
		JsonWriter.writer(response, jsonResult);
	}
}
