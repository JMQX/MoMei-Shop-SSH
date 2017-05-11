package com.momei.dao;

import java.sql.Connection;

public class ScoreGoodsDao {
	
	private Connection connection;

	
	public  ScoreGoodsDao(Connection connection){
		this.connection = connection;
	}

}
