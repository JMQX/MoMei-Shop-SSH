package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.GoodsDao;
import com.momei.domain.Goods;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class GoodService {
	
private static final GoodService instance = new GoodService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static GoodService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private GoodService() {
	
	}
	
	/**
	 * 添加商品
	 */
	public void addGoods(Goods goods){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.addGoods(goods);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加商品错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * 修改商品
	 */
	public void UpdateGoods(Goods goods){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.UpdateGoods(goods);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改商品错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * 修改商品状态为下架
	 */
	public void updateGoodsStatu(int goodsId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.updateGoodsStatu(goodsId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改商品错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 修改商品数量(减少)
	 */
	public void update_count(int goodsId,int number)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.update_count(goodsId, number);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改商品错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * 删除商品
	 */
	public void deleteGoods(int goodsId){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.deleteGoods(goodsId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("删除商品错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/**
	 * 批量删除商品
	 */
	public void deleteGoodsSome(int int_checkGoods[]){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsDao gd=new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			gd.deleteGoodsSome(int_checkGoods);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("批量删除商品错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			res=gd.getPageCountAndRowCount();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询商品错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 根据商品类型获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCountByType(int goodsTypeId)
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			res=gd.getPageCountAndRowCountByType(goodsTypeId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询商品错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	
	
	
	
	/*
	 * 商品分页查询
	 */
	public  List<Goods> getGoodsByPage(int pageNow)
	{
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByPage(pageNow);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * 根据商品类型分页查询
	 */
	public  List<Goods> getGoodsByPageByType(int pageNow,int goodsTypeId)
	{
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByPageByType(pageNow,goodsTypeId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * 根据商品类型查询
	 */
	public  List<Goods> getGoodsByType(int goodsTypeId)
	{
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByType(goodsTypeId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * 根据类型id获取所有商品id
	 */
	public List<Integer> getGoodsIdByType(int goodsTypeId)
	{
		List<Integer> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
			list=gd.getGoodsIdByType(goodsTypeId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询商品错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	
	/*
	 * 查询所有上架商品
	 */
	
	 public List<Goods> getAllGoods()
	 {
		 List<Goods> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getAllGoods();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	 }
	 
	    /*
		 * 查询所有商品
		 */
		 public List<Goods> getAll_Goods()
		 {
			 List<Goods> list=null;
			    Connection conn = null;
			    try{
					conn = DBUtils.getConnection();
					GoodsDao gd = new GoodsDao(conn);
					DBUtils.beginTransaction(conn);
					list=gd.getAll_Goods();
					DBUtils.commit(conn);
				} catch (ServiceException e) {
					throw e;
				} catch (Exception e) {
					DBUtils.rollback(conn);
					throw new ServiceException("查询商品错误", e);
				} finally {
					DBUtils.closeConnection(conn);
				}
				return list;
		 }
	
	/*
	 * 根据商品id查询商品
	 */
	
	public Goods getGoods(int goodsId)
	{
		Goods g=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				g=gd.getGoods(goodsId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return g;
	}
	
	/*
	 * 根据商品名查找上架商品(商品允许同名)
	 */
	public List<Goods> getGoodsByName(String goodsName)
	{
		List<Goods> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
				list=gd.getGoodsByName(goodsName);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}

	/*
	 * 模糊查询上架商品
	 */
	public List<Goods> getGoodsByFind(String goodsName)
	{
		List<Goods> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsDao gd = new GoodsDao(conn);
				DBUtils.beginTransaction(conn);
			    list=gd.getGoodsByFind(goodsName);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * 根据商品id获取商品类型名
	 */
	
	public String getTypeName(int goodsId)
	{
		String res=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			GoodsDao gd = new GoodsDao(conn);
			DBUtils.beginTransaction(conn);
		    res=gd.getTypeName(goodsId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询商品错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
	}
	
}
