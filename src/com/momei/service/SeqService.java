package com.momei.service;

import java.sql.Connection;
import java.util.List;

import com.momei.dao.SeqDao;
import com.momei.domain.BookSeqence;
import com.momei.domain.Type_Date_Count;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;

public class SeqService {
	
private static final SeqService instance = new SeqService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static SeqService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private SeqService() {
	
	}

	//添加订单子序列
	public void addSeq(int goodsId,int bookId,int count)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				sd.addSeq(goodsId, bookId, count);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("添加订单序列错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	//根据订单号查询订单序列
	public List<BookSeqence> getSeq(int bookId)
	{
		 List<BookSeqence> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				list=sd.getSeq(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询订单序列错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	//根据订单号删除订单序列
	public void deleteSeqByBookId(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				sd.deleteSeqByBookId(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("删除订单序列错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	//根据批量订单号批量删除订单序列
	public void deleteSeqBySomeBooks(int[] bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				SeqDao sd = new SeqDao(conn);
				DBUtils.beginTransaction(conn);
				sd.deleteSeqBySomeBooks(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("删除订单序列错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * 根据订单id获取订单序列的干果和数量
	 */
	public List<Type_Date_Count> getType_Date_count(int bookId)
	{
		List<Type_Date_Count> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			SeqDao sd = new SeqDao(conn);
			DBUtils.beginTransaction(conn);
			list=sd.getType_Date_count(bookId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单序列错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	 /*
	 * 根据商品号返回相关所有的订单子序列号
	 */
	public List<Integer> getSeqIdByGoodsId(int goodsId)
	{
		List<Integer> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			SeqDao sd = new SeqDao(conn);
			DBUtils.beginTransaction(conn);
			list=sd.getSeqIdByGoodsId(goodsId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单序列错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}

}
