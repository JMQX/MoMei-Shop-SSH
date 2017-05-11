package com.momei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.momei.domain.Goods;
import com.momei.util.DBUtils;
import com.momei.util.DaoException;

public class GoodsDao {
	
private Connection connection;

private int pageSize=5;
private int rowCount=0;
private int pageCount=0;
	
	
	public  GoodsDao(Connection connection){
		this.connection = connection;
	}
	
	/**
	 * 添加商品
	 */
	public void addGoods(Goods goods){
		String sql="insert into goods values(goods_seq.nextval,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, goods.getGoodsName());
			pstmt.setDouble(2, goods.getPrice());
			pstmt.setString(3, goods.getPic());
			pstmt.setString(4, goods.getUnit());
			pstmt.setInt(5, goods.getCount());
			pstmt.setString(6, goods.getContent());
			pstmt.setInt(7, goods.getGoodsTypeId());
			pstmt.setString(8, "上架");
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on adding Goods", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/**
	 * 修改商品
	 */
	public void UpdateGoods(Goods goods){
		String sql="";
		if(goods.getPic().length()>0)
		{
		    sql="update goods set goodsName=?,price=?,pic=?,unit=?,count=?,content=? where goodsId=?";
		}else
		{
			sql="update goods set goodsName=?,price=?,unit=?,count=?,content=? where goodsId=?";
		}
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			if(goods.getPic().length()>0)
			{
			pstmt.setString(1, goods.getGoodsName());
			pstmt.setDouble(2, goods.getPrice());
			pstmt.setString(3, goods.getPic());
			pstmt.setString(4, goods.getUnit());
			pstmt.setInt(5, goods.getCount());
			pstmt.setString(6, goods.getContent());
			pstmt.setInt(7, goods.getGoodsId());
			}else
			{
				pstmt.setString(1, goods.getGoodsName());
				pstmt.setDouble(2, goods.getPrice());
				pstmt.setString(3, goods.getUnit());
				pstmt.setInt(4, goods.getCount());
				pstmt.setString(5, goods.getContent());
				pstmt.setInt(6, goods.getGoodsId());
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update Goods", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	

	/**
	 * 修改商品状态为下架
	 */
	public void updateGoodsStatu(int goodsId)
	{
		String sql="update goods set statu='下架' where goodsId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update Goods", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 修改商品数量(减少)
	 */
	public void update_count(int goodsId,int number)
	{
		String sql="update goods set count=count-? where goodsId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, number);
			pstmt.setInt(2, goodsId);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on update Goods", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/**
	 * 删除商品
	 */
	public void deleteGoods(int goodsId){
		String sql="delete from goods where goodsId=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, goodsId);
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete Goods", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/**
	 * 批量删除商品
	 */
	public void deleteGoodsSome(int int_checkGoods[]){
		
		String GoodsAll="";
		 for(int i=0;i<int_checkGoods.length-1;i++)  
		  {  
			 GoodsAll = GoodsAll + int_checkGoods[i] + ","  ;  
		  }
		 GoodsAll = GoodsAll + int_checkGoods[int_checkGoods.length-1];  
		
		String sql="delete from goods where goodsId in("+GoodsAll+")";
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
		
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Error on delete Goods", e);
			
		} finally {
			DBUtils.closeStatement(null, pstmt);
		}
	}
	
	/*
	 * 获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCount()
	{
		int res[]=new int[2];
		String sql="select * from goods where statu='上架'";
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
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	

	/*
	 * 根据商品类型获取rowCount和pageCount
	 */
	public int[] getPageCountAndRowCountByType(int goodsTypeId)
	{
		int res[]=new int[2];
		String sql="select * from goods where goodsTypeId=? and statu='上架'";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql); 
			 pstmt.setInt(1, goodsTypeId);
			 rs = pstmt.executeQuery();
			 int temp=0;
			 while(rs.next()){
			   temp++;
			 }
			 res[0]=temp;//rowCount
			 res[1]=(temp-1)/pageSize+1;  //pageCount
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
			return res;
	}
	
	
	
	
	/*
	 * 商品分页查询
	 */
	public  List<Goods> getGoodsByPage(int pageNow)
	{
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from (select a1.*,rownum rn from (select * from goods where statu='上架' order by goodsId) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 Goods g=new Goods();
				 g.setGoodsId(rs.getInt("goodsId"));
				 g.setGoodsName(rs.getString("goodsName"));
				 g.setPrice(rs.getDouble("price"));
				 g.setPic(rs.getString("pic"));
				 g.setUnit(rs.getString("unit"));
				 g.setCount(rs.getInt("count"));
				 g.setContent(rs.getString("content"));
				 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
				
				 list.add(g);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
		
	}
	
	/*
	 * 根据商品类型分页查询
	 */
	public  List<Goods> getGoodsByPageByType(int pageNow,int goodsTypeId)
	{
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from (select a1.*,rownum rn from (select * from goods where goodsTypeId=? and statu='上架' order by goodsId) a1 where rownum<="+pageNow*pageSize+") where rn>="+((pageNow-1)*pageSize+1)+"";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, goodsTypeId);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 Goods g=new Goods();
				 g.setGoodsId(rs.getInt("goodsId"));
				 g.setGoodsName(rs.getString("goodsName"));
				 g.setPrice(rs.getDouble("price"));
				 g.setPic(rs.getString("pic"));
				 g.setUnit(rs.getString("unit"));
				 g.setCount(rs.getInt("count"));
				 g.setContent(rs.getString("content"));
				 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
				
				 list.add(g);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
		
	}
	
	/*
	 * 根据商品类型查询
	 */
	public  List<Goods> getGoodsByType(int goodsTypeId)
	{
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from goods where goodsTypeId=? and statu='上架' order by goodsId";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, goodsTypeId);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 Goods g=new Goods();
				 g.setGoodsId(rs.getInt("goodsId"));
				 g.setGoodsName(rs.getString("goodsName"));
				 g.setPrice(rs.getDouble("price"));
				 g.setPic(rs.getString("pic"));
				 g.setUnit(rs.getString("unit"));
				 g.setCount(rs.getInt("count"));
				 g.setContent(rs.getString("content"));
				 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
				
				 list.add(g);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 根据类型id获取所有商品id
	 */
	public List<Integer> getGoodsIdByType(int goodsTypeId)
	{
		List<Integer> list=new ArrayList<Integer>();
		String sql="select goodsId from goods where goodsTypeId=? order by goodsId";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1, goodsTypeId);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				
				 list.add(rs.getInt("goodsId"));
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 查询所有上架商品
	 */
	
	 public List<Goods> getAllGoods()
	 {
		 List<Goods> list=new ArrayList<Goods>();
			String sql="select * from  goods where statu='上架' order by goodsId";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				
				 rs = pstmt.executeQuery();
				 while(rs.next()){
					 Goods g=new Goods();
					 g.setGoodsId(rs.getInt("goodsId"));
					 g.setGoodsName(rs.getString("goodsName"));
					 g.setPrice(rs.getDouble("price"));
					 g.setPic(rs.getString("pic"));
					 g.setUnit(rs.getString("unit"));
					 g.setCount(rs.getInt("count"));
					 g.setContent(rs.getString("content"));
					 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
					
					 list.add(g);
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting goods", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return list;
	 }
	 
	    /*
		 * 查询所有商品
		 */
		 public List<Goods> getAll_Goods()
		 {
			 List<Goods> list=new ArrayList<Goods>();
				String sql="select * from  goods order by goodsId";
				PreparedStatement pstmt = null;
				 ResultSet rs=null;
				 try{
					 pstmt = connection.prepareStatement(sql);
					
					 rs = pstmt.executeQuery();
					 while(rs.next()){
						 Goods g=new Goods();
						 g.setGoodsId(rs.getInt("goodsId"));
						 g.setGoodsName(rs.getString("goodsName"));
						 g.setPrice(rs.getDouble("price"));
						 g.setPic(rs.getString("pic"));
						 g.setUnit(rs.getString("unit"));
						 g.setCount(rs.getInt("count"));
						 g.setContent(rs.getString("content"));
						 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
						
						 list.add(g);
					 }
				 } catch (SQLException e) {
						throw new DaoException("Error on getting goods", e);
					} finally {
						DBUtils.closeStatement(rs, pstmt);
					}
				 
				
				return list;
		 }
		 
	
	/*
	 * 根据商品id查询商品
	 */
	
	public Goods getGoods(int goodsId)
	{
		 Goods g=new Goods();
		String sql="select * from goods where goodsId=?";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setInt(1,goodsId);
			
			 rs = pstmt.executeQuery();
			 if(rs.next()){
				 g.setGoodsId(rs.getInt("goodsId"));
				 g.setGoodsName(rs.getString("goodsName"));
				 g.setPrice(rs.getDouble("price"));
				 g.setPic(rs.getString("pic"));
				 g.setUnit(rs.getString("unit"));
				 g.setCount(rs.getInt("count"));
				 g.setContent(rs.getString("content"));
				 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
			
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return g;
	}
	
	/*
	 * 根据商品名查找上架商品
	 */
	public List<Goods> getGoodsByName(String goodsName)
	{
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from goods where goodsName=? and statu='上架' order by goodsId";
		PreparedStatement pstmt = null;
		 ResultSet rs=null;
		 try{
			 pstmt = connection.prepareStatement(sql);
			 pstmt.setString(1, goodsName);
			 rs = pstmt.executeQuery();
			 while(rs.next()){
				 Goods g=new Goods();
				 g.setGoodsId(rs.getInt("goodsId"));
				 g.setGoodsName(rs.getString("goodsName"));
				 g.setPrice(rs.getDouble("price"));
				 g.setPic(rs.getString("pic"));
				 g.setUnit(rs.getString("unit"));
				 g.setCount(rs.getInt("count"));
				 g.setContent(rs.getString("content"));
				 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
				
				 list.add(g);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, pstmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 模糊查询上架商品
	 */
	public List<Goods> getGoodsByFind(String goodsName)
	{
		List<Goods> list=new ArrayList<Goods>();
		String sql="select * from goods where statu='上架' and goodsName like '%"+goodsName+"%' order by goodsId";
		Statement stmt = null;
		 ResultSet rs=null;
		 try{
			stmt=connection.createStatement();
			rs=stmt.executeQuery(sql);
			 while(rs.next()){
				 Goods g=new Goods();
				 g.setGoodsId(rs.getInt("goodsId"));
				 g.setGoodsName(rs.getString("goodsName"));
				 g.setPrice(rs.getDouble("price"));
				 g.setPic(rs.getString("pic"));
				 g.setUnit(rs.getString("unit"));
				 g.setCount(rs.getInt("count"));
				 g.setContent(rs.getString("content"));
				 g.setGoodsTypeId(rs.getInt("goodsTypeId"));
				
				 list.add(g);
			 }
		 } catch (SQLException e) {
				throw new DaoException("Error on getting goods", e);
			} finally {
				DBUtils.closeStatement(rs, stmt);
			}
		 
		
		return list;
	}
	
	/*
	 * 根据商品id获取商品类型名
	 */
	
	public String getTypeName(int goodsId)
	{
		String res=null;
			String sql="select b.goodstypename from goods a,goodstype b where a.goodstypeid=b.goodstypeid and a.goodsid=?";
			PreparedStatement pstmt = null;
			 ResultSet rs=null;
			 try{
				 pstmt = connection.prepareStatement(sql);
				 pstmt.setInt(1,goodsId);
				
				 rs = pstmt.executeQuery();
				 if(rs.next()){
					
				    res=rs.getString("goodsTypeName");
				 }
			 } catch (SQLException e) {
					throw new DaoException("Error on getting goods", e);
				} finally {
					DBUtils.closeStatement(rs, pstmt);
				}
			 
			
			return res;
	}
	
}
