package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.momei.domain.Book;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class BookDao {
	
private Connection connection;

private int pageSize=5;
private int rowCount=0;
private int pageCount=0;
	
	
	public  BookDao(Connection connection){
		this.connection = connection;
	}
	

	//添加订单
	public void addBook(int bookId,int userId,String message,String send_time,String pay_way,String phoneNum,String address,Calendar dates_order,int count,double pay)
	{
		String sql = "insert into book (bookId,userId,STATU,DATES_ORDER,ADDRESS,PHONENUM,PAY_WAY,SEND_TIME,MESSAGE,count,pay,COMMENT_STATU) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		    pstmt.setInt(2,userId );
		    pstmt.setString(3,"订单已提交");
		    pstmt.setTime(4, new Time(dates_order.getTimeInMillis()));
		    pstmt.setString(5, address);
		    pstmt.setString(6, phoneNum);
		    pstmt.setString(7, pay_way);
		    pstmt.setString(8, send_time);
		    pstmt.setString(9, message);
		    pstmt.setInt(10, count);
		    pstmt.setDouble(11, pay);
		    pstmt.setString(12, "待评价");
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on add", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	//查询当前订单最大的id号
	public synchronized int find_Max_bookId()
	{
		int result=999;//默认999
		
		 String sql ="select max(bookId) from book";
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 result=rs.getInt("max(bookId)");
				 if(result==0)
				 {
					 result=999;//当前数据库中无订单记录，默认返回999，订单号从1000开始自增长
				 }
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		return result;
			
	}
	
	//查询用户最新订单(下订时间前三)
	public List<Book> getBook_Recent(int userId)
	{
		List<Book> list=new ArrayList<Book>();
		String sql="select a1.*,rownum rn from(select * from book where userId=? order by dates_order desc) a1 where rownum<=3";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,userId);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setStatu(rs.getString("statu"));
				b.setPay_way(rs.getString("pay_way"));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//根据订单号查询订单
	public Book getBookById(int bookId)
	{
		Book b=new Book();
		String sql="select * from book where bookId=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,bookId);
			
			 rs = pstmt.executeQuery();
			 if(rs.next()){
		
					b.setBookId(rs.getInt("bookId"));
					b.setStatu(rs.getString("statu"));
					b.setPay_way(rs.getString("pay_way"));
					b.setSend_time(rs.getString("send_time"));
					b.setPhoneNum(rs.getString("phoneNum"));
					b.setAddress(rs.getString("address"));
					b.setMessage(rs.getString("message"));
					b.setPay(rs.getDouble("pay"));
					b.setComment_statu(rs.getString("comment_statu"));
				
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return b;
	}
	
	
	/*
	 * 根据订单号和订单状态查询订单
	 */
	public Book getBookById(int bookId,String statu)
	{
		Book b=null;
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and bookId=? and statu=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,bookId);
			 pstmt.setString(2, statu);
			
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				   b=new Book();
				 b.setBookId(rs.getInt("bookId"));
					b.setName(rs.getString("name"));
					b.setUserName(rs.getString("userName"));
					b.setStatu(rs.getString("statu"));
					b.setSend_time(rs.getString("send_time"));
					b.setPay_way(rs.getString("pay_way"));
					Calendar cal = Calendar.getInstance();
					java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
					 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
					b.setPay(rs.getDouble("pay"));
					b.setCount(rs.getInt("count"));
					b.setPhoneNum(rs.getString("phoneNum"));
					b.setAddress(rs.getString("address"));
					b.setMessage(rs.getString("message"));
				
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return b;
	}
	
	/*
	 * 获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='订单已提交' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
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
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='商家已接单' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	

	/*
	 * 获取rowCount和pageCount(已完成订单)
	 */
	public int[] getPageCountAndRowCount_finish()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='订单已完成' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * 获取rowCount和pageCount(已取消订单)
	 */
	public int[] getPageCountAndRowCount_giveup()
	{
		int res[]=new int[2];
		String sql="select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='订单已取消' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * 获取rowCount和pageCount(具体用户的所有订单)
	 */
	public int[] getPageCountAndRowCount_user(int userId)
	{
		int res[]=new int[2];
		String sql="select * from book where userId=? order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 pstmt.setInt(1, userId);
			 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	/*
	 * 获取rowCount和pageCount(具体用户的所有已取消订单)
	 */
	public int[] getPageCountAndRowCountGiveup_user(int userId)
	{
		int res[]=new int[2];
		String sql="select * from book where userId=? and statu='订单已取消' order by dates_order desc";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 pstmt.setInt(1, userId);
			 
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	//管理员分页获取最新订单(按下订时间的降序)
	public List<Book> getAllNewBooks(int pageNow)
	{
		List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='订单已提交' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setName(rs.getString("name"));
				b.setUserName(rs.getString("userName"));
				b.setStatu(rs.getString("statu"));
				b.setSend_time(rs.getString("send_time"));
				b.setPay_way(rs.getString("pay_way"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				b.setPhoneNum(rs.getString("phoneNum"));
				b.setAddress(rs.getString("address"));
				b.setMessage(rs.getString("message"));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//管理员分页获取已接收订单(按下订时间的降序)
	public List<Book> getAllRecieveBooks(int pageNow)
	{

		List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='商家已接单' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setName(rs.getString("name"));
				b.setUserName(rs.getString("userName"));
				b.setStatu(rs.getString("statu"));
				b.setSend_time(rs.getString("send_time"));
				b.setPay_way(rs.getString("pay_way"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				b.setPhoneNum(rs.getString("phoneNum"));
				b.setAddress(rs.getString("address"));
				b.setMessage(rs.getString("message"));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//管理员分页获取已完成订单(按下订时间的降序)
	public List<Book> getAllFinishBooks(int pageNow)
	{
         List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='订单已完成' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setName(rs.getString("name"));
				b.setUserName(rs.getString("userName"));
				b.setStatu(rs.getString("statu"));
				b.setSend_time(rs.getString("send_time"));
				b.setPay_way(rs.getString("pay_way"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				b.setPhoneNum(rs.getString("phoneNum"));
				b.setAddress(rs.getString("address"));
				b.setMessage(rs.getString("message"));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	//管理员分页获取已取消订单(按下订时间的降序)
	public List<Book> getAllGiveupBooks(int pageNow)
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from (select a1.*,rownum rn from (select b.*,u.name,u.userName from book b,userInfo u where b.userid=u.userid and statu='订单已取消' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
			
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					b.setName(rs.getString("name"));
					b.setUserName(rs.getString("userName"));
					b.setStatu(rs.getString("statu"));
					b.setSend_time(rs.getString("send_time"));
					b.setPay_way(rs.getString("pay_way"));
					Calendar cal = Calendar.getInstance();
					java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
					 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
					b.setPay(rs.getDouble("pay"));
					b.setCount(rs.getInt("count"));
					b.setPhoneNum(rs.getString("phoneNum"));
					b.setAddress(rs.getString("address"));
					b.setMessage(rs.getString("message"));
					 
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
	//用户分页获取所有订单(按下订时间的降序)
	public List<Book> getAllBooks_user(int pageNow,int userId)
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from (select a1.*,rownum rn from (select * from book where userId=? order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				 pstmt.setInt(1, userId);
			
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					b.setStatu(rs.getString("statu"));
					b.setPay_way(rs.getString("pay_way"));
					b.setPay(rs.getDouble("pay"));
					b.setCount(rs.getInt("count"));
					Calendar cal = Calendar.getInstance();
					java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
					 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
					 
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
	//用户分页获取所有已取消订单(按下订时间的降序)
	public List<Book> getAllBooksGiveup_user(int pageNow,int userId)
	{
		List<Book> list=new ArrayList<Book>();
		
		String sql="select * from (select a1.*,rownum rn from (select * from book where userId=? and statu='订单已取消' order by dates_order desc) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, userId);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				b.setStatu(rs.getString("statu"));
				b.setPay_way(rs.getString("pay_way"));
				b.setPay(rs.getDouble("pay"));
				b.setCount(rs.getInt("count"));
				Calendar cal = Calendar.getInstance();
				java.sql.Timestamp s_dates = rs.getTimestamp("dates_order", cal);
				 b.setDates_order(new java.sql.Date(cal.getTimeInMillis()));
				 
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 修改订单状态为已接单
	 */
	public void upbook_recieve(int bookId)
	{
		String sql = "update book set statu='商家已接单' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 修改订单状态为已完成
	 */
	public void upbook_finish(int bookId)
	{
		String sql = "update book set statu='订单已完成' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 取消订单
	 */
	
	public void up_give_up(int bookId)
	{
		String sql = "update book set statu='订单已取消' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 修改订单评价状态为已评价
	 */
	
	public void up_comment(int bookId)
	{
		String sql = "update book set comment_statu='已评价' where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}

	/*
	 * 删除订单
	 */
	
	public void deleteBook(int bookId)
	{
		String sql = "delete from book where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, bookId);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 批量删除订单
	 */
	
	public void deleteSomeBooks(int[] bookId)
	{
		String BooksAll="";
		 for(int i=0;i<bookId.length-1;i++)  
		  {  
			 BooksAll = BooksAll + bookId[i] + ","  ;  
		  }
		 BooksAll = BooksAll + bookId[bookId.length-1];  
		
		String sql="delete from book where bookId in("+BooksAll+")";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete book", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 根据日期获取订单id集合
	 */
	
	public List<Book> getBookIdByDate(String date_str)
	{
     List<Book> list=new ArrayList<Book>();
		
		String sql="select * from  book where statu='订单已完成' and to_char(dates_order,'yyyy-mm-dd')=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, date_str);
		
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				Book b=new Book();
				
				b.setBookId(rs.getInt("bookId"));
				
				 list.add(b);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting book", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 根据月份获取订单id集合
	 */
	
	public List<Book> getBookIdByMonth(String month_str)
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from  book where statu='订单已完成' and to_char(dates_order,'yyyy-mm')=?";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				 pstmt.setString(1, month_str);
			
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
	/*
	 * 获取订单id集合
	 */
	
	public List<Book> getBookId()
	{
		 List<Book> list=new ArrayList<Book>();
			
			String sql="select * from  book where statu='订单已完成'";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					Book b=new Book();
					
					b.setBookId(rs.getInt("bookId"));
					
					 list.add(b);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting book", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	}
	
}
