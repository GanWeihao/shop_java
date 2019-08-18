package org.taru.lanqiao.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import org.taru.lanqiao.model.OrderDetail;
import org.taru.lanqiao.util.SqlUtil;

public class ShopCarDaoImpl {
	/**
	 * 加入购物车
	 */
	public  int  addShopCar(String detailProductId,int detailProductCount,String detailProductUnit,BigDecimal detailProductPrice,String detailComment,String detailUserId) {
		String detailId=UUID.randomUUID().toString();
		String sql="insert into order_detail(DETAIL_ID,DETAIL_PRODUCT_ID,DETAIL_PRODUCT_COUNT,DETAIL_PRODUCT_UNIT,DETAIL_PRODUCT_PRICE,DETAIL_COMMENT,DETAIL_USER_ID,DETAIL_ORDER_ID,DETAIL_STATUS,DETAIL_TOTAL_PRICE) values(?,?,?,?,?,?,?,null,1,DETAIL_PRODUCT_COUNT*DETAIL_PRODUCT_PRICE)";
	    int car=SqlUtil.executeUpdate(sql,detailId,detailProductId,detailProductCount,detailProductUnit,detailProductPrice,detailComment,detailUserId);
	    OrderDetail od=new OrderDetail();
		return car;
	}

}
