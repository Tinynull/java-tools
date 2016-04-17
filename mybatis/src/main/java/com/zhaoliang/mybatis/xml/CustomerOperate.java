package com.zhaoliang.mybatis.xml;

import com.zhaoliang.mybatis.dao.Customers;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CustomerOperate {
	
	private static Logger logger = LoggerFactory.getLogger(CustomerOperate.class);

	public static void main(String[] args) {
		selectCustomers();
//		System.out.println("--------------");
//		getOneCustomer();
//		System.out.println("--------------");
//		addCustomers();
		
//		updateCustomer();
//		deleteCustomer();
		
	}
	
	public static void updateCustomer() {
		SqlSession session = Utils.getSqlSession();
		String updateCustomer = "com.zhaoliang.mybatis.study.mapping.customersMapper.updateCustomer";// 映射sql的标识字符串
		Customers cust = new Customers();
		cust.setCust_email("hellokitty@163.com");
		cust.setCust_id("201");
		cust.setCust_name("zhaoliang");
		cust.setCust_zip("9999999");
		session.update(updateCustomer, cust);
		session.commit();
		session.close();
	}
	
	public static void deleteCustomer() {
		SqlSession session = Utils.getSqlSession();
		String deleteCustomer = "com.zhaoliang.mybatis.study.mapping.customersMapper.deleteCustomer";// 映射sql的标识字符串
		session.delete(deleteCustomer, "201");
		session.commit();
		session.close();
	}

	public static void selectCustomers() {
		SqlSession session = Utils.getSqlSession();
		String selectCustomers = "com.zhaoliang.mybatis.study.mapping.customersMapper.selectCustomers";// 映射sql的标识字符串
		logger.info("selectCustomers = {}",selectCustomers);
		List<Customers> customers = session.selectList(selectCustomers, "%");
		for (Customers c : customers) {
			System.out.println(c);
		}
		session.close();
	}
	
	public static void addCustomers() {
		SqlSession session = Utils.getSqlSession();
		String addCustomers = "com.zhaoliang.mybatis.study.mapping.customersMapper.addCustomers";// 映射sql的标识字符串
		Customers cust = new Customers();
		cust.setCust_address("changsha");
		cust.setCust_contact("5135");
		cust.setCust_country("china");
		cust.setCust_email("hello@163.com");
		cust.setCust_id("201");
		cust.setCust_name("zhaoliang");
		cust.setCust_state("hunan");
		cust.setCust_zip("515324");
		session.insert(addCustomers, cust);
		session.commit();
		session.close();
	}

	public static void getOneCustomer() {
		SqlSession session = Utils.getSqlSession();
		String getCustomer = "com.zhaoliang.mybatis.study.mapping.customersMapper.getCustomer";// 映射sql的标识字符串
		Customers customers = session.selectOne(getCustomer, "1000000001");
		System.out.println(customers);
		session.close();
	}
}
