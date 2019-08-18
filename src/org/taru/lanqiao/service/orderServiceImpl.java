package org.taru.lanqiao.service;

import java.util.List;

import org.taru.lanqiao.dao.orderDaoImpl;
import org.taru.lanqiao.model.Detil;
import org.taru.lanqiao.model.Dingdan;
import org.taru.lanqiao.model.Order;
import org.taru.lanqiao.model.OrderList;

public class orderServiceImpl {
	/**
	 * 
	 * @param orderid
	 * @订单发货
	 */
	public int orderSend(String orderid) {
		orderDaoImpl daoImpl=new orderDaoImpl();
		return daoImpl.orderSend(orderid);
	}
	
	/**
	 * 
	 * @param orderid
	 * @订单作废
	 */
	public int deleteOrderById(String orderid) {
		orderDaoImpl daoImpl=new orderDaoImpl();
		return daoImpl.deleteOrderById(orderid);
	}
	
	/**
	 * 
	 * @param orderid
	 * @导出订单
	 */
	public OrderList exportOrder(String orderid) {
		orderDaoImpl daoImpl=new orderDaoImpl();
		return daoImpl.queryOrderListById(orderid);
	}
	
	/**
	 * 根据订单id查询订单详情
	 * @author Administrator
	 *
	 */
	public List<Detil> queryOrderById(String orderid) throws Exception{
		orderDaoImpl impl=new orderDaoImpl();
		return impl.queryDetailByOrderId(orderid);
			
	}
	
	/**
	 * 查询历史订单
	 * @author Administrator
	 *
	 */
		public List<Order> queryHisByUid(String userid) throws Exception {
			orderDaoImpl impl=new orderDaoImpl();
			return impl.queryHistory(userid);
		}
	
		 /**
	      * 根据订单id查询单个订单信息
	      */
	public Dingdan queryDd1(String orderid) {
	       orderDaoImpl s = new orderDaoImpl();
	       return  s.Queryorder1(orderid);	
	}
	
	/**
     * 根据订单id查询订单购买详情
     */
	public List queryDd(String orderid) {
		orderDaoImpl s = new orderDaoImpl();
    	   return  s.Queryorder(orderid);
    }
	
	/**
	 * 查询所有订单
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public Dingdan queryDingdan(int pageNum,int pageSize){
		orderDaoImpl   daoImpl  =new orderDaoImpl();
		return daoImpl.queryDingdan(pageNum, pageSize);
		}
	
}
