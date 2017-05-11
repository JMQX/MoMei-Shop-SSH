package com.momei.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.momei.domain.GoodsType;
import com.momei.service.GoodService;
import com.momei.service.GoodsTypeService;
import com.momei.service.SeqService;
import com.momei.service.StoreService;

public class GoodsTypeServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            this.doPost(request, response);
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
               
		request.setCharacterEncoding("UTF-8");
		
		String flag=request.getParameter("flag");
		
		if(flag.equals("add"))
		{
			String goodsTypeName=request.getParameter("goodsTypeName");
			String  content=request.getParameter("content");
			GoodsType gt=new GoodsType();
			gt.setGoodsTypeName(goodsTypeName);
			gt.setContent(content);
			GoodsTypeService gts=GoodsTypeService.getInstance();
			gts.addGoodsType(gt);
			request.setAttribute("Msg", "添加成功!");
			 request.getRequestDispatcher("admin/addType.jsp").forward(request, response);
		
		}else if(flag.equals("show"))
		{
			GoodsTypeService gts=GoodsTypeService.getInstance();
			List<GoodsType> list=gts.getGoods_Type();
			request.setAttribute("list", list);
			 request.getRequestDispatcher("admin/typeManage.jsp").forward(request, response);
			
		}else if(flag.equals("up"))
		{
			int goodsTypeId=Integer.parseInt(request.getParameter("goodsTypeId"));
			String goodsTypeName=request.getParameter("goodsTypeName");
			String content=request.getParameter("content");
			GoodsType gt=new GoodsType();
			gt.setGoodsTypeId(goodsTypeId);
			gt.setGoodsTypeName(goodsTypeName);
			gt.setContent(content);
			GoodsTypeService gts=GoodsTypeService.getInstance();
			gts.uptype(gt);
			List<GoodsType> list=gts.getGoodsType();
			request.setAttribute("list", list);
			request.setAttribute("Msg", "修改成功!");
			request.setAttribute("goodsTypeName", goodsTypeName);
			request.setAttribute("content", content);
			 request.getRequestDispatcher("admin/typeManage.jsp").forward(request, response);
			
		}else if(flag.equals("del"))
		{
			int goodsTypeId=Integer.parseInt(request.getParameter("goodsTypeId"));
			
			//删除商品类型
			
			//根据类型id获取所有商品id
			GoodService gs=GoodService.getInstance();
			List<Integer> list_goodsId=gs.getGoodsIdByType(goodsTypeId);
			
			//遍历删除所有商品id对应的商品收藏
			StoreService sts=StoreService.getInstance();
			for(int i=0;i<list_goodsId.size();i++)
			{
				sts.deleteStoreByGoodsId(list_goodsId.get(i));
			}
			
			SeqService ss=SeqService.getInstance();
			
			int flag_index=0;//计数器
			
			//遍历判断商品子记录订单序列是否为空
			for(int i=0;i<list_goodsId.size();i++)
			{
				List<Integer> list_bookId=ss.getSeqIdByGoodsId(list_goodsId.get(i));
				 //无订单序列子记录，即无级联问题，删除商品
				if(list_bookId.size()==0)
				{
				 gs.deleteGoods(list_goodsId.get(i));
				 flag_index++;
				}else
				{
					//有订单序列子记录，为不影响历史订单信息的查询，不删除商品，修改商品状态为下架
					gs.updateGoodsStatu(list_goodsId.get(i));
					
				}
			}
			
			GoodsTypeService gts=GoodsTypeService.getInstance();
			if(flag_index==list_goodsId.size())
			{
			//该类型商品无订单条目子记录，删除分类
			gts.deltype(goodsTypeId);
			}else
			{
				//该类型商品有订单条目子记录，更改分类状态为下架
				gts.updateTypeStatu(goodsTypeId);
			}
			
			List<GoodsType> list=gts.getGoodsType();
			request.setAttribute("list", list);
			request.getRequestDispatcher("admin/typeManage.jsp").forward(request, response);
		}
	}

}
