package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.momei.domain.BookSeqence;
import com.momei.domain.Type_Date_Count;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class SeqDao {
	
private Connection connection;
	
	
	public  SeqDao(Connection connection){
		this.connection = connection;
	}
	
	//添加订单子序列
	public void addSeq(int goodsId,int bookId,int count)
	{
		String sql = "insert into bookseqence (sequenceId,goodsId,bookId,count) values(bookseqence_seq.nextval,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
		    pstmt.setInt(2,bookId );
		    pstmt.setInt(3,count);
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on add", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	//根据订单号查询订单序列
	public List<BookSeqence> getSeq(int bookId)
	{
		List<BookSeqence> list=new ArrayList<BookSeqence>();
		String sql="select a2.goodsId,a2.goodsname,a2.price,a1.count from bookseqence a1,goods a2 where a1.goodsid=a2.goodsid and a1.bookid=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,bookId);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 BookSeqence bs=new BookSeqence();
				 bs.setCount(rs.getInt("count"));
				 bs.setGoodsName(rs.getString("goodsName"));
				 bs.setPrice(rs.getDouble("price"));
				 bs.setGoodsId(rs.getInt("goodsId"));
				 
				 list.add(bs);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting bookSeqence", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	

	//根据订单号删除订单序列
	public void deleteSeqByBookId(int bookId)
	{
		String sql = "delete from bookseqence where bookId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1,bookId );
		   
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	//根据批量订单号批量删除订单序列
	public void deleteSeqBySomeBooks(int[] bookId)
	{
		String BooksAll="";
		 for(int i=0;i<bookId.length-1;i++)  
		  {  
			 BooksAll = BooksAll + bookId[i] + ","  ;  
		  }
		 BooksAll = BooksAll + bookId[bookId.length-1];  
		
		String sql="delete from bookseqence where bookId in("+BooksAll+")";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete bookSeqence", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 根据订单id获取订单序列的干果和数量
	 */
	public List<Type_Date_Count> getType_Date_count(int bookId)
	{
		List<Type_Date_Count> list=new ArrayList<Type_Date_Count>();
		String sql="select a.count,b.goodsname,b.price,c.goodstypename from bookseqence a,goods b,goodstype c where bookId=? and a.goodsid=b.goodsid and b.goodstypeid=c.goodstypeid";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,bookId);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 Type_Date_Count tdc=new Type_Date_Count();
				 tdc.setCount(rs.getInt("count"));
				 tdc.setGoodsTypeName(rs.getString("goodsTypeName"));
				 tdc.setGoodsName(rs.getString("goodsName"));
				 tdc.setPrice(rs.getDouble("price"));
				 
				 list.add(tdc);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting bookSeqence", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	 /*
	 * 根据商品号返回相关所有的订单子序列号
	 */
	public List<Integer> getSeqIdByGoodsId(int goodsId)
	{
		List<Integer> list=new ArrayList<Integer>();
		String sql="select sequenceId from bookseqence where goodsId=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,goodsId);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
			
				 list.add(rs.getInt("sequenceId"));
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting bookSeqence", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	

}
