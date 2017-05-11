package com.momei.service;

public class ScoreGoodsService {
	
private static final ScoreGoodsService instance = new ScoreGoodsService();
	
	/**
	 * 取得实例 
	 * @return 实例对象
	 */
	public static ScoreGoodsService getInstance() {
		return instance;
	}
	
	/**
	 * 构造方法 
	 */
	private ScoreGoodsService() {
	
	}

}
