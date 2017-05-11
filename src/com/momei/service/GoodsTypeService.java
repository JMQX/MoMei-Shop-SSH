package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.GoodsTypeDao;
import com.momei.domain.GoodsType;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class GoodsTypeService {
	
private static final GoodsTypeService instance = new GoodsTypeService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static GoodsTypeService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private GoodsTypeService() {
	
	}


	/**
	 * 添加商品分类
	 */
	public void addGoodsType(GoodsType gt){
		
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsTypeDao gd=new GoodsTypeDao(conn);
			DBUtils.beginTransaction(conn);
			gd.addGoodsType(gt);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加商品分类错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 修改商品分类
	 */
	
	public void uptype(GoodsType gt)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsTypeDao gd=new GoodsTypeDao(conn);
			DBUtils.beginTransaction(conn);
			gd.uptype(gt);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改商品分类错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 修改商品分类状态为下架
	 */
	public void updateTypeStatu(int goodsTypeId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			GoodsTypeDao gd=new GoodsTypeDao(conn);
			DBUtils.beginTransaction(conn);
			gd.updateTypeStatu(goodsTypeId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("修改商品分类错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 删除商品分类
	 */
	
	  public void deltype(int goodsTypeId)
	  {
		  Connection conn=null;
			try{
				conn=DBUtils.getConnection();
				GoodsTypeDao gd=new GoodsTypeDao(conn);
				DBUtils.beginTransaction(conn);
				gd.deltype(goodsTypeId);
				DBUtils.commit(conn);
			}catch(ServiceException e){
				throw e;
			}catch(Exception e){
				DBUtils.rollback(conn);
				throw new ServiceException("删除商品分类错误");
			}finally{
				DBUtils.closeConnection(conn);
			}
	  }
	
	/*
	 * 获得所有商品分类
	 */
	
	public  List<GoodsType> getGoodsType()
	{
		 List<GoodsType> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsTypeDao gd=new GoodsTypeDao(conn);
				DBUtils.beginTransaction(conn);
			    list=gd.getGoodsType();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品分类错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	/*
	 * 获得所有上架商品分类
	 */
	public  List<GoodsType> getGoods_Type()
	{
		 List<GoodsType> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				GoodsTypeDao gd=new GoodsTypeDao(conn);
				DBUtils.beginTransaction(conn);
			    list=gd.getGoods_Type();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询商品分类错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
}
