package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.momei.domain.GoodsType;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class GoodsTypeDao {
	
	private Connection connection;

	
		public  GoodsTypeDao(Connection connection){
			this.connection = connection;
		}

		/**
		 * 添加商品分类
		 */
		public void addGoodsType(GoodsType gt){
			String sql="insert into goodstype values(goodstype_seq.nextval,?,?,?)";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, gt.getGoodsTypeName());
				pstmt.setString(2, gt.getContent());
				pstmt.setString(3, "上架");
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on adding GoodsType", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}
		
		/*
		 * 修改商品分类
		 */
		
		public void uptype(GoodsType gt)
		{
			String sql="update goodstype set goodsTypeName=?,content=? where goodsTypeId=?";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setString(1, gt.getGoodsTypeName());
				pstmt.setString(2, gt.getContent());
				pstmt.setInt(3, gt.getGoodsTypeId());
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on update GoodsType", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}
		
		
		/*
		 * 修改商品分类状态为下架
		 */
		public void updateTypeStatu(int goodsTypeId)
		{
			String sql="update goodstype set statu='下架' where goodsTypeId=?";
			PreparedStatement pstmt = null;
			try {
				pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, goodsTypeId);
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new DaoException("Error on update GoodsType", e);
				
			} finally {
				DBUtils.closeStatement(null, pstmt);
			}
		}
		
		/*
		 * 删除商品分类
		 */
		
		  public void deltype(int goodsTypeId)
		  {
			  String sql="delete from goodstype where goodsTypeId=?";
				PreparedStatement pstmt = null;
				try {
					pstmt = connection.prepareStatement(sql);
					pstmt.setInt(1, goodsTypeId);
					
					pstmt.executeUpdate();
				} catch (SQLException e) {
					System.out.println(e.getMessage());
					throw new DaoException("Error on delete GoodsType", e);
					
				} finally {
					DBUtils.closeStatement(null, pstmt);
				}
		  }
		
		/*
		 * 获得所有商品分类
		 */
		
		public  List<GoodsType> getGoodsType()
		{
			List<GoodsType> list=new ArrayList<GoodsType>();
			String sql="select * from goodstype order by goodstypeId";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					 GoodsType gt=new GoodsType();
					 gt.setGoodsTypeId(rs.getInt("goodsTypeId"));
					 gt.setGoodsTypeName(rs.getString("goodsTypeName"));
					 gt.setContent(rs.getString("content"));
					 list.add(gt);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting goodstype", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
		}
		
		/*
		 * 获得所有上架商品分类
		 */
		public  List<GoodsType> getGoods_Type()
		{
			List<GoodsType> list=new ArrayList<GoodsType>();
			String sql="select * from goodstype where statu='上架' order by goodstypeId";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					 GoodsType gt=new GoodsType();
					 gt.setGoodsTypeId(rs.getInt("goodsTypeId"));
					 gt.setGoodsTypeName(rs.getString("goodsTypeName"));
					 gt.setContent(rs.getString("content"));
					 list.add(gt);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting goodstype", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
		}
}
