package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.momei.domain.Store;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class StoreDao {
	
private Connection connection;
	
	public  StoreDao(Connection connection){
		this.connection = connection;
	}

	/*
	 * 添加收藏
	 */
	
	public void addStore(int goodsId,int userId)
	{
		String sql="insert into store values(store_seq.nextval,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
			pstmt.setInt(2, userId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on adding store", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 判断用户是否已经添加过某商品收藏了
	 */
	
	public Store getJudge(int goodsId,int userId)
	{
		 String sql ="select * from store where goodsId=? and userId=?";
		 Store store=null;
		 PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
		     pstmt.setInt(1, goodsId);
		     pstmt.setInt(2, userId);
		  
			 rs = pstmt.executeQuery();
			 if(rs.next()){
			 store=new Store();
			 store.setStoreId(rs.getInt("storeId"));
			 
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting store", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return store;
	}
	
	/*
	 * 查询用户全部收藏商品
	 */
	public List<Store> getAllStore(int userId)
	{
		List<Store> list=new ArrayList<Store>();
		
		String sql="select a.storeid,b.* from store a,goods b where a.goodsid=b.goodsid and a.userid=? order by a.storeid";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, userId);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
			  
				Store s=new Store();
				s.setStoreId(rs.getInt("storeId"));
				s.setGoodsId(rs.getInt("goodsId"));
				s.setGoodsName(rs.getString("goodsName"));
				s.setPrice(rs.getDouble("price"));
				s.setPic(rs.getString("pic"));
				s.setCount(rs.getInt("count"));
				
				 list.add(s);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting store", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 删除收藏
	 */
	
	public void deleteStore(int storeId)
	{
		String sql="delete from store where storeId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, storeId);
	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete store", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 根据商品id删除商品收藏
	 */
	
	public void deleteStoreByGoodsId(int goodsId)
	{
		String sql="delete from store where goodsId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
	
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete store", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
}
