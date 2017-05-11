package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.StoreDao;
import com.momei.domain.Store;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class StoreService {

	private static final StoreService instance=new StoreService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static StoreService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private StoreService() {
	
	}
	
	/*
	 * 添加收藏
	 */
	
	public void addStore(int goodsId,int userId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			StoreDao sd=new StoreDao(conn);
			DBUtils.beginTransaction(conn);
			sd.addStore(goodsId, userId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("添加商品收藏错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 判断用户是否已经添加过某商品收藏了
	 */
	
	public Store getJudge(int goodsId,int userId)
	{
		Store store=null;
			Connection conn = null;
			try{
				conn = DBUtils.getConnection();
				StoreDao sd=new StoreDao(conn);
				DBUtils.beginTransaction(conn);
				store=sd.getJudge(goodsId, userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询收藏错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return store;
	}
	
	/*
	 * 查询用户全部收藏商品
	 */
	public List<Store> getAllStore(int userId)
	{
		List<Store> list=null;
		  Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				StoreDao sd=new StoreDao(conn);
				DBUtils.beginTransaction(conn);
				list=sd.getAllStore(userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询用户收藏错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
		
		return list;
	}
	
	/*
	 * 删除收藏
	 */
	
	public void deleteStore(int storeId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			StoreDao sd=new StoreDao(conn);
			DBUtils.beginTransaction(conn);
			sd.deleteStore(storeId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("删除商品收藏错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
	
	/*
	 * 根据商品id删除商品收藏
	 */
	
	public void deleteStoreByGoodsId(int goodsId)
	{
		Connection conn=null;
		try{
			conn=DBUtils.getConnection();
			StoreDao sd=new StoreDao(conn);
			DBUtils.beginTransaction(conn);
			sd.deleteStoreByGoodsId(goodsId);
			DBUtils.commit(conn);
		}catch(ServiceException e){
			throw e;
		}catch(Exception e){
			DBUtils.rollback(conn);
			throw new ServiceException("删除商品收藏错误");
		}finally{
			DBUtils.closeConnection(conn);
		}
	}
}
