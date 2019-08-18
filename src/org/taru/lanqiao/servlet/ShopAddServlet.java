package org.taru.lanqiao.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 
 * 加入购物车
 */
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.taru.lanqiao.dao.ShopCarDaoImpl;
import org.taru.lanqiao.service.productServiceImpl;
import org.taru.lanqiao.util.JsonWriter;
import org.taru.lanqiao.util.StringUtil;
import org.taru.lanqiao.vo.JsonResult;
@WebServlet("/api/detail/join")
public class ShopAddServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShopCarDaoImpl shopcar=new ShopCarDaoImpl();
		JsonResult jsonResult=null;
		try {
			String detailProductId=request.getParameter("detailProductId");
			
			String detailProductCount0=request.getParameter("detailProductCount");
			Integer detailProductCount=Integer.parseInt(detailProductCount0);
			
			String detailProductUnit=request.getParameter("detailProductUnit");
			
			String detailProductPrice0=request.getParameter("detailProductPrice");
			BigDecimal detailProductPrice=new BigDecimal(detailProductPrice0);
			
			String detailComment=request.getParameter("detailComment");
			String detailUserId=request.getParameter("detailUserId");
			
			int car=shopcar.addShopCar(detailProductId, detailProductCount, detailProductUnit, detailProductPrice, detailComment, detailUserId);
			//System.out.println(shopcar);
			if(car==0) {
				jsonResult=new JsonResult("400", "加入购物车失败");	
			}else {
				jsonResult=new JsonResult("200", "加入购物车成功",car);	
			}
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult=new JsonResult("500", "加入购物车异常",e.getMessage());	
		}
			JsonWriter.writer(response,jsonResult);
	}

}
