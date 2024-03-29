package org.taru.lanqiao.service;

import java.math.BigDecimal;
import java.util.List;

import org.taru.lanqiao.dao.productDaoImpl;
import org.taru.lanqiao.model.OrderDetail;
import org.taru.lanqiao.model.PageProduct;
import org.taru.lanqiao.model.Product;
import org.taru.lanqiao.util.DbUtil;
import org.taru.lanqiao.vo.PageResult;
import org.taru.lanqiao.vo.productAll;
import org.taru.lanqiao.vo.shopCar;

public class productServiceImpl {
	/**
	 * 
	 * @param productId
	 * @根据商品ID查询商品详情
	 */
	public Product queryById(String productId) {
		productDaoImpl product=new productDaoImpl();
		return product.queryById(productId);
	}
	
	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @查询所有商品，并进行分页
	 */
	public PageResult productList(int pageNum,int pageSize) {
		productDaoImpl daoImpl=new productDaoImpl();
		return daoImpl.productList(pageNum, pageSize);
	}
	
	/**
	 * 
	 * @param uId
	 * @查询购物车
	 */
	public List<shopCar> queryByUid(String uId) {
		productDaoImpl daoImpl=new productDaoImpl();
		return daoImpl.queryByUid(uId); 
	}
	
	/**
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param categoryId
	 * @param productName
	 * @param isLack
	 * @管理员查询所有商品
	 */
	public productAll queryProductAll(int pageNum,int pageSize,String categoryId,String productName,String productisLack) {
		productDaoImpl daoImpl=new productDaoImpl();
		return daoImpl.queryProductAll(pageNum, pageSize, categoryId, productName, productisLack);
	}
	
	/**
	 * 
	 * @param product
	 * @新增商品
	 */
	public int insertProduct(String id,Product product) {
		productDaoImpl daoImpl=new productDaoImpl();
		return daoImpl.insertProduct(id, product);
	}
	
	/**
	 * 
	 * @param productId
	 * @管理员移除商品，废除商品
	 */
	public int deleteProductById(String productId) {
		productDaoImpl daoImpl=new productDaoImpl();
		return daoImpl.deleteProductById(productId);
	}
	
	/**
	 * 
	 * @param productId
	 * @根据id查询单个商品
	 */
	public Product queryProductOne(String productId) {
		productDaoImpl daoImpl=new productDaoImpl();
		return daoImpl.queryProductOne(productId);
	}
	
	/**
	 * 查询用户购买过的商品
	 * @param userId
	 * @return
	 */
	public Product userOld(String userId) {
		productDaoImpl pdimpl=new productDaoImpl();   
		return pdimpl.queryUserId(userId);
	}
	
	/**
	 * 
	 * 根据类型查询商品
	 * @param categoryId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageProduct queryProductCid(String categoryId,int pageNum,int pageSize) {
		productDaoImpl  daoImpl=new productDaoImpl();
		return daoImpl.selectCategoryId(categoryId, pageNum, pageSize);
	}
	
	
	/**
	 * 修改商品
	 * @param product
	 * @return
	 */
	public int update(Product product){
		productDaoImpl impl=new productDaoImpl();
		return impl.updateProduct(product);
	}
	
	/**
	 * 移除购物车
	 */
	public int deleteOrder(String id) {
		productDaoImpl daoimpl=new productDaoImpl();
		return daoimpl.deleteOrderById(id);

	}
	
	/**
	 * 提交订单
	 */
	public int commint(String orderUserId,String orderTotalPrice,String orderComment) {
		productDaoImpl daoimpl=new productDaoImpl();
		return daoimpl.commentCar(orderUserId, orderTotalPrice, orderComment);
	}
	
	/**
	 * 模糊搜索商品
	 */
	public List<Product> queryProductByname(String name){
		productDaoImpl daoImpl =new productDaoImpl();
		return daoImpl.queryByName("%"+name+"%");
		
	}
	/**
	 *多条件查询
	 */
	public List<Product> queryProductByManay(String categoryId,String orderStr){
		productDaoImpl daoImpl =new productDaoImpl();
		return daoImpl.queryByMany(categoryId,orderStr);
	}
	
	
	/**
	 * 缺货设置
	 * @param id
	 */
	public void Wuhuo(String id) {
		System.out.println("---------------------------");
		productDaoImpl  daoImpl=new productDaoImpl();
		daoImpl.ModifyById(id);
	    System.out.println("成功");
	}
	
	
	/**
	 * 上货
	 * @param id
	 */
	public void youhuo(String id) {
		productDaoImpl  dao=new productDaoImpl();
		dao.ModifyById1(id);
	    System.out.println("成功");
	}
	
	
	/**
	 * 按年度统计商品销售排行
	 * @param year
	 * @return
	 */
	public List  queryYear(String year) {
		productDaoImpl yy= new productDaoImpl();
		 return yy.QueryYear(year);
		}
}
