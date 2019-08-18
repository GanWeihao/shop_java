package org.taru.lanqiao.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.taru.lanqiao.model.Detil;
import org.taru.lanqiao.model.Dingdan;
import org.taru.lanqiao.model.Order;
import org.taru.lanqiao.model.OrderList;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.model.User1;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.util.SqlUtil;
import org.taru.lanqiao.util.StringUtil;

public class orderDaoImpl {
	
	/**
	 * 
	 * @param orderid
	 * @订单发货
	 */
	public int orderSend(String orderid) {
		String sql="update order_list set ORDER_IS_SEND=1 where ORDER_ID=?";
		int row=SqlUtil.executeUpdate(sql, orderid);
		return row;
	}
	
	/**
	 * 
	 * @param orderid
	 * @订单作废
	 */
	public int deleteOrderById(String orderid) {
		String sql="update order_list set ORDER_STATUS=0 where ORDER_ID=?";
		int row=SqlUtil.executeUpdate(sql, orderid);
		return row;
	}
	
	/**
	 * 
	 * @param orderid
	 * @导出订单
	 */
	public OrderList queryOrderListById(String orderid) {
		String sql="select * from order_list where ORDER_ID=?";
		List<HashMap<String,Object>> list=SqlUtil.executeQuery(sql, orderid);
		OrderList orderlist=null;
		if(list.size()>0) {
			orderlist=new OrderList();
			orderlist.setOrderId(StringUtil.valueOf(list.get(0).get("ORDER_ID")));
			orderlist.setOrderUserId(StringUtil.valueOf(list.get(0).get("ORDER_USER_ID")));
			orderlist.setOrderIsSend(Byte.parseByte(StringUtil.valueOf(list.get(0).get("ORDER_IS_SEND"))));
			orderlist.setOrderIsValid(Byte.parseByte(StringUtil.valueOf(list.get(0).get("ORDER_IS_VALID"))));
			orderlist.setOrderStatus(Byte.parseByte(StringUtil.valueOf(list.get(0).get("ORDER_STATUS"))));
			//double ordertotalprice=DoubleTo.value(StringUtil.valueOf(list.get(0).get("ORDER_TOTAL_PRICE")));
			//orderlist.setOrderTotalPrice(ordertotalprice);
		}
		return orderlist;
	}
	
	/**
	 * 查询历史订单
	 * @author Administrator
	 *
	 */
		public List<Order> queryHistory(String userid) throws Exception {
			String sql="select * from order_list where ORDER_USER_ID=?";
			ArrayList<HashMap<String,Object>> list=SqlUtil.executeQuery(sql,userid);
			List<Order> list1=new ArrayList();
			Order order=null;
				for(int i=0;i<list.size();i++) {
				order=new Order();
				order.setOrderUserId(list.get(i).get("ORDER_USER_ID").toString());
				order.setOrderId(StringUtil.valueOf(list.get(i).get("ORDER_ID")));
				SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
				order.setOrderDateTime(format.parse(list.get(i).get("ORDER_DATE_TIME").toString()));
				order.setOrderComment(list.get(i).get("ORDER_COMMENT").toString());
				BigDecimal big=new BigDecimal(StringUtil.isnull(list.get(i).get("ORDER_TOTAL_PRICE")).toString());
				order.setOrderTotalPrice(big);
				list1.add(order);
			}
			return list1;
		}
		
		/**
		 * 根据订单id查询订单详情
		 * @author Administrator
		 *
		 */
			public List<Detil> queryDetailByOrderId(String orderid) throws Exception{
				String sql="select dl.*,pt.* from order_detail dl left join product pt on dl.DETAIL_PRODUCT_ID=pt.PRODUCT_ID where DETAIL_ORDER_ID=?";
				ArrayList<HashMap<String,Object>> list=SqlUtil.executeQuery(sql, orderid);
				List<Detil> details =new ArrayList<Detil>();
				if(list !=null && list.size()>0) {
					for(Map<String,Object> map:list) {
						Detil detail=new Detil();
						detail.setDetailId(StringUtil.valueOf(map.get("DETAIL_ID")));
						detail.setDetailProductId(StringUtil.valueOf(map.get("DETAIL_PRODUCT_ID")));
						Integer pc=Integer.parseInt(StringUtil.valueOf(map.get("DETAIL_PRODUCT_COUNT")));
						detail.setDetailProductCount(pc);
						detail.setDetailProductUnit(StringUtil.valueOf(map.get("DETAIL_PRODUCT_UNIT")));
						BigDecimal detailProductPrice=new BigDecimal(StringUtil.isnull(map.get("DETAIL_PRODUCT_PRICE")).toString());
						detail.setDetailProductPrice(detailProductPrice);
						BigDecimal detailTotalPrice=new BigDecimal(StringUtil.isnull(map.get("DETAIL_TOTAL_PRICE")).toString());
						detail.setDetailTotalPrice(detailTotalPrice);
						detail.setDetailComment(StringUtil.valueOf(map.get("DETAIL_COMMENT")));
						Integer detailStatus=Integer.parseInt(StringUtil.valueOf(map.get("DETAIL_STATUS")));
						detail.setDetailStatus(detailStatus);
						detail.setDetailOrderId(StringUtil.valueOf(map.get("DETAIL_ORDER_ID")));
						detail.setDetailUserId(StringUtil.valueOf(map.get("DETAIL_USER_ID")));
						SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
						detail.setDetailDateTime(format.parse(map.get("DETAIL_DATE_TIME").toString()));
						Product product=new Product();
						product.setProductId(StringUtil.valueOf(map.get("PRODUCT_ID")));
						product.setProductCode(StringUtil.valueOf(map.get("PRODUCT_CODE")));
						product.setProductName(StringUtil.valueOf(map.get("PRODUCT_NAME")));
						product.setProductStandard(StringUtil.valueOf(map.get("PRODUCT_STANDARD")));
						product.setProductSmallUnit(StringUtil.valueOf(map.get("PRODUCT_SMALL_UNIT")));
						BigDecimal productSmallPrice=new BigDecimal(StringUtil.valueOf(map.get("PRODUCT_SMALL_PRICE")).toString());
						product.setProductSmallPrice(productSmallPrice);
						product.setProductLargerUnit(StringUtil.valueOf(map.get("PRODUCT_LARGER_UNIT")));
						BigDecimal productLargerPrice=new BigDecimal(StringUtil.valueOf(map.get("PRODUCT_LARGER_PRICE")).toString());
						product.setProductLargerPrice(productLargerPrice);
						product.setProductCategoryId(StringUtil.valueOf(map.get("PRODUCT_CATEGORY_ID")));
						product.setProductBrandId(StringUtil.valueOf(map.get("PRODUCT_BRAND_ID")));
						product.setProductPhoto(StringUtil.valueOf("PRODUCT_PHOTO"));
						Byte isscale=Byte.parseByte(StringUtil.valueOf(map.get("PRODUCT_IS_SALE")));
						product.setProductIsSale(isscale);
						Byte islack=Byte.parseByte(StringUtil.valueOf(map.get("PRODUCT_IS_LACK")));
						product.setProductIsLack(islack);
						Byte status=Byte.parseByte(StringUtil.valueOf(map.get("PRODUCT_STATUS")));
						product.setProductStatus(status);
						Integer productOrder=Integer.parseInt(StringUtil.valueOf(map.get("PRODUCT_ORDER")));
						product.setProductOrder(productOrder);
						SimpleDateFormat format1 = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
						product.setProductDateTime(format1.parse(map.get("PRODUCT_DATE_TIME").toString()));
						product.setProductLargerStandard(StringUtil.valueOf(map.get("PRODUCT_LARGER_STANDARD")));
						BigDecimal productSuggestPrice=new BigDecimal(StringUtil.valueOf(map.get("PRODUCT_SUGGEST_PRICE")).toString());
						product.setProductSuggestPrice(productSuggestPrice);
						detail.setProduct(product);
						details.add(detail);
						
					}
				}
				
				return details;
		}
			/**
			 * 查询所有订单
			 * @param pageNum
			 * @param pageSize
			 * @return
			 */
			public Dingdan queryDingdan(int pageNum,int pageSize) {
				String  sql="select  *  from  order_list  limit ?,?";
				List<Map<String,Object>>  list=  DbUtil.query(sql,pageNum,pageSize);
				List<Dingdan> list2=new ArrayList<Dingdan>();
				String s=null;
				List<Map<String, Object>> list3=null; 
				for(int i=0;i<list.size();i++) {
				 s=list.get(i).get("ORDER_USER_ID").toString();
				 System.out.println(s);
				  String sql1="SELECT * from user  where USER_ID=?";
			       list3=DbUtil.query(sql1, s);
				}
				User1 user = new User1();
				for(int i=0;i<list3.size();i++) {
					String userId = list3.get(i).get("USER_ID").toString();
					String userUserName = list3.get(i).get("USER_USER_NAME").toString();
					String userPassword = list3.get(i).get("USER_PASSWORD").toString();
					String userTelphone = list3.get(i).get("USER_TELPHONE").toString();
					String userAddress = list3.get(i).get("USER_ADDRESS").toString();
					String userShopName = list3.get(i).get("USER_SHOP_NAME").toString();
					String userComment = list3.get(i).get("USER_COMMENT").toString();
					String status = list3.get(i).get("USER_STATUS").toString();
					String userStatus = "1";
					if(status == "" && status == null) {
						userStatus = "0";
					}
					user.setUserId(userId);
					user.setUserUserName(userUserName);
					user.setUserPassword(userPassword);
					user.setUserTelphone(userTelphone);
					user.setUserAddress(userAddress);
					user.setUserShopName(userShopName);
					user.setUserComment(userComment);
					user.setUserStatus(userStatus);
				}
				
				  for(int i=0;i<list.size();i++) {
					Dingdan dingdan =new Dingdan();
					 dingdan.setOrderId(list.get(i).get("ORDER_ID").toString());
					 dingdan.setOrderUserId(list.get(i).get("ORDER_USER_ID").toString());
					 dingdan.setOrderDateTime(list.get(i).get("ORDER_DATE_TIME").toString());
					 dingdan.setOrderIsSend(list.get(i).get("ORDER_IS_SEND").toString());
					 dingdan.setOrderIsValid(list.get(i).get("ORDER_IS_VALID").toString());
					 dingdan.setOrderTotalPrice(list.get(i).get("ORDER_TOTAL_PRICE").toString());
					 dingdan.setOrderStatus(list.get(i).get("ORDER_STATUS").toString());
					 dingdan.setOrderComment(list.get(i).get("ORDER_COMMENT").toString());
					 dingdan.setUser(user);
					 list2.add(dingdan);
				}
				Dingdan dingdan=new Dingdan();
				dingdan.setList(list2);
				String sql2="select count(*) as row from product";
				List<Map<String,Object>> countList =DbUtil.query(sql2);
				String count=String.valueOf(countList.get(0).get("row"));
				int size=Integer.parseInt(count);
				dingdan.setPages(size%pageSize==0?size/pageSize:size/pageSize+1);
				dingdan.setTotal(size);
			
				return dingdan;
			}

			
			/**
		      * 根据订单id查询订单购买详情
		      */
		     
			 public List Queryorder(String orderid) {
	    		 String sql="SELECT DETAIL_ID,DETAIL_PRODUCT_ID,DETAIL_PRODUCT_COUNT,DETAIL_PRODUCT_UNIT,DETAIL_PRODUCT_PRICE,DETAIL_TOTAL_PRICE,DETAIL_COMMENT,DETAIL_STATUS,DETAIL_ORDER_ID,DETAIL_USER_ID,DETAIL_DATE_TIME from order_detail WHERE DETAIL_ORDER_ID=?";
	    		 List<Map<String, Object>> list=DbUtil.query(sql, orderid);
	  		  String s=list.get(0).get("DETAIL_PRODUCT_ID").toString();
	  		  
	  		  
	  		  
	  		  String sql1="SELECT PRODUCT_ID,PRODUCT_CODE,PRODUCT_NAME,PRODUCT_STANDARD,PRODUCT_SMALL_UNIT,PRODUCT_SMALL_PRICE,PRODUCT_LARGER_UNIT,PRODUCT_LARGER_PRICE,PRODUCT_CATEGORY_ID,PRODUCT_BRAND_ID,PRODUCT_PHOTO,PRODUCT_IS_SALE,PRODUCT_IS_LACK,PRODUCT_STATUS,PRODUCT_ORDER,PRODUCT_DATE_TIME,PRODUCT_LARGER_STANDARD,PRODUCT_SUGGEST_PRICE\r\n" + 
	   		 	"from product  WHERE PRODUCT_ID=?";
	  		 List<Map<String, Object>> list2=DbUtil.query(sql1, s);
	    		 Dingdan dingdan=new Dingdan();
	    	  List<Dingdan> li = new ArrayList();
	    		 if(list.size()>0) {
	    			 dingdan.setDetailId(list.get(0).get("DETAIL_ID").toString());
	    			 dingdan.setDetailProductId(list.get(0).get("DETAIL_PRODUCT_ID").toString());
	    			 dingdan.setDetailProductCount(list.get(0).get("DETAIL_PRODUCT_COUNT").toString());
	    			 dingdan.setDetailProductUnit(list.get(0).get("DETAIL_PRODUCT_UNIT").toString());
	    			 dingdan.setDetailProductPrice(list.get(0).get("DETAIL_PRODUCT_PRICE").toString());
	    			 dingdan.setDetailTotalPrice(list.get(0).get("DETAIL_TOTAL_PRICE").toString());
	    			 dingdan.setDetailComment(list.get(0).get("DETAIL_COMMENT").toString());
	    			 dingdan.setDetailStatus(list.get(0).get("DETAIL_STATUS").toString());
	    			 dingdan.setDetailOrderId(list.get(0).get("DETAIL_ORDER_ID").toString());
	    			 dingdan.setDetailUserId(list.get(0).get("DETAIL_USER_ID").toString());
	    			 dingdan.setDetailDateTime(list.get(0).get("DETAIL_DATE_TIME").toString());
	    		     
	    		}
	    		 
	    		 if(list2.size() > 0) {
		  			  for(int i=0;i<list2.size();i++) {
		  				Product p = new Product();
		  				String productId = list2.get(i).get("PRODUCT_ID").toString();
	    				String productBrandId = list2.get(i).get("PRODUCT_BRAND_ID").toString();
	    				String productCategoryId = list2.get(i).get("PRODUCT_CATEGORY_ID").toString();
	    				String productCode = list2.get(i).get("PRODUCT_CODE").toString();
	    				Date productDateTime = (Date) list2.get(i).get("PRODUCT_DATE_TIME");
	    				Byte productIsLack = Byte.parseByte(StringUtil.valueOf(list2.get(i).get("PRODUCT_IS_LACK")));
	    				Byte productIsSale = Byte.parseByte(StringUtil.valueOf(list2.get(i).get("PRODUCT_IS_SALE")));
//	    				BigDecimal productLargerPrice = (BigDecimal) list2.get(i).get("PRODUCT_LARGER_PRICE");
	    				String productLargerStandard = list2.get(i).get("PRODUCT_LARGER_STANDARD").toString();
	    				String productLargerUnit = list2.get(i).get("PRODUCT_LARGER_UNIT").toString();
	    				String productName = list2.get(i).get("PRODUCT_NAME").toString();
	    				Integer productOrder = (Integer) list2.get(i).get("PRODUCT_ORDER");
	    				String productPhoto = list2.get(i).get("PRODUCT_PHOTO").toString();
//	    				BigDecimal productSmallPrice = (BigDecimal) list2.get(i).get("PRODUCT_SMALL_PRICE");
	    				String productSmallUnit = list2.get(i).get("PRODUCT_SMALL_UNIT").toString();
	    				String productStandard = list2.get(i).get("PRODUCT_STANDARD").toString();
	    				Byte productStatus = Byte.parseByte(StringUtil.valueOf(list2.get(i).get("PRODUCT_STATUS")));
//	    				BigDecimal productSuggestPrice = (BigDecimal) list2.get(i).get("PRODUCT_SUGGEST_PRICE");
	    				
	    				p.setProductBrandId(productBrandId);
	    				p.setProductCategoryId(productCategoryId);
	    				p.setProductCode(productCode);
	    				p.setProductDateTime(productDateTime);
	    				p.setProductId(productId);
	    				p.setProductIsLack(productIsLack);
	    				p.setProductIsSale(productIsSale);
//	    				p.setProductLargerPrice(productLargerPrice);
	    				p.setProductLargerStandard(productLargerStandard);
	    				p.setProductLargerUnit(productLargerUnit);
	    				p.setProductName(productName);
	    				p.setProductOrder(productOrder);
	    				p.setProductPhoto(productPhoto);
//	    				p.setProductSmallPrice(productSmallPrice);
	    				p.setProductSmallUnit(productSmallUnit);
	    				p.setProductStandard(productStandard);
	    				p.setProductStatus(productStatus);
//	    				p.setProductSuggestPrice(productSuggestPrice);
	    				dingdan.setList1(p);
	    				li.add(dingdan);
		  			  }
		  		  }
	    		
	    		
	    		 
	    		return li;
	     }

		     
		     /**
		      * 根据订单id查询单个订单信息
		      */
		     
		     public Dingdan Queryorder1(String orderid) {
				 String sql="SELECT * FROM  order_list where  ORDER_ID=?";
				 List<Map<String, Object>> list=DbUtil.query(sql, orderid);
				  String s=list.get(0).get("ORDER_USER_ID").toString();
				  System.out.println(s);
				  String sql1="SELECT * from user  where USER_ID=?";
			         List<Map<String, Object>> list2=DbUtil.query(sql1, s);
				 Dingdan dingdan=new Dingdan();
			  
				
				if(list.size()>0) {
					User1 user = new User1();
					if(list2.size()>0) {
						
						String userId = list2.get(0).get("USER_ID").toString();
						String userUserName = list2.get(0).get("USER_USER_NAME").toString();
						String userPassword = list2.get(0).get("USER_PASSWORD").toString();
						String userTelphone = list2.get(0).get("USER_TELPHONE").toString();
						String userAddress = list2.get(0).get("USER_ADDRESS").toString();
						String userShopName = list2.get(0).get("USER_SHOP_NAME").toString();
						String userComment = list2.get(0).get("USER_COMMENT").toString();
						String status = list2.get(0).get("USER_STATUS").toString();
						String userStatus = "1";
						if(status == "" && status == null) {
							userStatus = "0";
						}
						user.setUserId(userId);
						user.setUserUserName(userUserName);
						user.setUserPassword(userPassword);
						user.setUserTelphone(userTelphone);
						user.setUserAddress(userAddress);
						user.setUserShopName(userShopName);
						user.setUserComment(userComment);
						user.setUserStatus(userStatus);
					}
					
					 dingdan.setOrderId(list.get(0).get("ORDER_ID").toString());
					 dingdan.setOrderUserId(list.get(0).get("ORDER_USER_ID").toString());
					 dingdan.setOrderDateTime(list.get(0).get("ORDER_DATE_TIME").toString());
					 dingdan.setOrderIsSend(list.get(0).get("ORDER_IS_SEND").toString());
					 dingdan.setOrderIsValid(list.get(0).get("ORDER_IS_VALID").toString());
					 dingdan.setOrderTotalPrice(list.get(0).get("ORDER_TOTAL_PRICE").toString());
					 dingdan.setOrderStatus(list.get(0).get("ORDER_STATUS").toString());
					 dingdan.setOrderComment(list.get(0).get("ORDER_COMMENT").toString());
				     dingdan.setUser(user); 
				}
				 
				return dingdan;
		 }

}
