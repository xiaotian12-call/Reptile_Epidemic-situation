package com.me.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.me.domain.Info;
import com.me.domain.Provinces;
import com.me.utils.DBUtils;

/**
 * @author 王正帅
 * @date: 2020年3月3日 下午3:39:20 
 * 
 */
public class InfoDao {

	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<Info> getList(String date) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from info1 where date = ?";
		List<Info> query = qr.query(sql, new BeanListHandler<Info>(Info.class),date);
		return query;
	}
	public List<Info> getListT(String date) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from info1 where date = ? ";
		List<Info> query = qr.query(sql, new BeanListHandler<Info>(Info.class),date);
		return query;
	}
	public List<Info> getListC(String date,String province) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from info1 where date = ? and province =?";
		List<Info> query = qr.query(sql, new BeanListHandler<Info>(Info.class),date,province);
		return query;
	}
	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<Provinces> getList4() throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from provinces";
		List<Provinces> query = qr.query(sql, new BeanListHandler<Provinces>(Provinces.class));
		return query;
	}
	/**
	 * @param province
	 * @return
	 * @throws SQLException 
	 */
	public Provinces getProvinces(String province) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from provinces where  name=? ";
		Provinces user01 = qr.query(sql, new BeanHandler<Provinces>(Provinces.class), province);
		return user01;
	}
	/**
	 * @param i
	 * @return
	 * @throws SQLException 
	 */
	public List<Provinces> getListC2(String id) throws SQLException {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from citys where id like ?";
		List<Provinces> query = qr.query(sql, new BeanListHandler<Provinces>(Provinces.class),id+"%");
		return query;
	}
	
}
