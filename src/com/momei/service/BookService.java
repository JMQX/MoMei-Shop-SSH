package com.momei.service;

import java.sql.Connection;
import java.util.Calendar;
import java.util.List;

import com.momei.dao.BookDao;
import com.momei.domain.Book;
import com.momei.util.DBUtils;
import com.momei.util.ServiceException;


public class BookService {
	
private static final BookService instance = new BookService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static BookService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private BookService() {
	
	}

	//添加订单
	public void addBook(int bookId,int userId,String message,String send_time,String pay_way,String phoneNum,String address,Calendar dates_order,int count,double pay)
	{
			 Connection conn = null;
			 try{
					conn = DBUtils.getConnection();
					BookDao bd = new BookDao(conn);
					DBUtils.beginTransaction(conn);
					bd.addBook(bookId,userId, message, send_time, pay_way, phoneNum, address, dates_order,count,pay);
					DBUtils.commit(conn);
				} catch (ServiceException e) {
					throw e;
				} catch (Exception e) {
					DBUtils.rollback(conn);
					throw new ServiceException("添加订单错误", e);
				} finally {
					DBUtils.closeConnection(conn);
				}
	}
	
	//查询订单Id最大值
	public int find_Max_bookId()
	{
		int result=999;//默认999
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    result=bd.find_Max_bookId();
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
		
		return result;
	}
	
	//查询用户最新订单(下订时间前三)
	public List<Book> getBook_Recent(int userId)
	{
		 List<Book> list=null;
		    Connection conn = null;
		    try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
				list=bd.getBook_Recent(userId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("查询订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
			return list;
	}
	
	//根据订单号查询订单
	public Book getBookById(int bookId)
	{
		Book b=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			b=bd.getBookById(bookId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
	}
	
	/*
	 * 根据订单号和订单状态查询订单
	 */
	public Book getBookById(int bookId,String statu)
	{
		Book b=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			b=bd.getBookById(bookId, statu);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return b;
	}
	
	
	/*
	 * 获取rowCount和pageCount(最新订单)
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 获取rowCount和pageCount(已接订单)
	 * 
	 */
	public int[] getPageCountAndRowCount_recieve()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_recieve();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 获取rowCount和pageCount(已完成订单)
	 */
	public int[] getPageCountAndRowCount_finish()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_finish();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 获取rowCount和pageCount(已取消订单)
	 */
	public int[] getPageCountAndRowCount_giveup()
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_giveup();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 获取rowCount和pageCount(具体用户的所有订单)
	 */
	public int[] getPageCountAndRowCount_user(int userId)
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCount_user(userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	/*
	 * 获取rowCount和pageCount(具体用户的所有已取消订单)
	 */
	public int[] getPageCountAndRowCountGiveup_user(int userId)
	{
		int res[]=new int[2];
		Connection conn = null;
		try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			res=bd.getPageCountAndRowCountGiveup_user(userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return res;
		
	}
	
	
	
	//管理员分页获取最新订单(按下订时间的降序)
	public List<Book> getAllNewBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllNewBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//管理员分页获取已接收订单(按下订时间的降序)
	public List<Book> getAllRecieveBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllRecieveBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//管理员分页获取已完成订单(按下订时间的降序)
	public List<Book> getAllFinishBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllFinishBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	
	//管理员分页获取已取消订单(按下订时间的降序)
	public List<Book> getAllGiveupBooks(int pageNow)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllGiveupBooks(pageNow);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//用户分页获取所有订单(按下订时间的降序)
	public List<Book> getAllBooks_user(int pageNow,int userId)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllBooks_user(pageNow, userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	//用户分页获取所有已取消订单(按下订时间的降序)
	public List<Book> getAllBooksGiveup_user(int pageNow,int userId)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getAllBooksGiveup_user(pageNow, userId);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	/*
	 * 修改订单状态为已接单
	 */
	public void upbook_recieve(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.upbook_recieve(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("修改订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * 修改订单状态为已完成
	 */
	public void upbook_finish(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.upbook_finish(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("修改订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * 取消订单
	 */
	
	public void up_give_up(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.up_give_up(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("修改订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	
	/*
	 * 修改订单评价状态为已评价
	 */
	
	public void up_comment(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.up_comment(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("修改订单评价状态错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * 删除订单
	 */
	
	public void deleteBook(int bookId)
	{
		 Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.deleteBook(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("删除订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * 批量删除订单
	 */
	
	public void deleteSomeBooks(int[] bookId)
	{
		Connection conn = null;
		 try{
				conn = DBUtils.getConnection();
				BookDao bd = new BookDao(conn);
				DBUtils.beginTransaction(conn);
			    bd.deleteSomeBooks(bookId);
				DBUtils.commit(conn);
			} catch (ServiceException e) {
				throw e;
			} catch (Exception e) {
				DBUtils.rollback(conn);
				throw new ServiceException("删除订单错误", e);
			} finally {
				DBUtils.closeConnection(conn);
			}
	}
	
	/*
	 * 根据日期获取订单id集合
	 */
	
	public List<Book> getBookIdByDate(String date_str)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getBookIdByDate(date_str);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	/*
	 * 根据月份获取订单id集合
	 */
	
	public List<Book> getBookIdByMonth(String month_str)
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getBookIdByMonth(month_str);
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	
	/*
	 * 获取订单id集合
	 */
	
	public List<Book> getBookId()
	{
		List<Book> list=null;
	    Connection conn = null;
	    try{
			conn = DBUtils.getConnection();
			BookDao bd = new BookDao(conn);
			DBUtils.beginTransaction(conn);
			list=bd.getBookId();
			DBUtils.commit(conn);
		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			DBUtils.rollback(conn);
			throw new ServiceException("查询订单错误", e);
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}
	

}
