package com.zhaoliang.mybatis.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.zhaoliang.mybatis.study.dao.Customers;
import com.zhaoliang.mybatis.study.dao.Orders;
import com.zhaoliang.mybatis.study.dao.Products;
import com.zhaoliang.mybatis.study.dao.Vendors;

public class Test1 {

	public static String configXMLFile = "conf.xml";

	public static void main(String[] args) throws IOException {
//		getOneOrder();
//		getOneProducts();
//		getOneVendor();
		selectCustomers();
	}
	
	public static void getOneVendor(){
		SqlSession session = Utils.getSqlSession();
		String statement = "com.zhaoliang.mybatis.study.mapping.vendorsMapper.getOneVendor";// 映射sql的标识字符串
		Vendors vendors = session.selectOne(statement, "BRE02");
		System.out.println(vendors);
		session.close();
	}

	public static void getOneOrder() {
		SqlSession session = Utils.getSqlSession();
		String statement = "com.zhaoliang.mybatis.study.mapping.ordersMapper.getOneOrder";// 映射sql的标识字符串
		Orders orders = session.selectOne(statement, 20005);
		System.out.println(orders);
		session.close();
	}
	
	public static void getOneProducts(){
		SqlSession session = Utils.getSqlSession();
		String statement = "com.zhaoliang.mybatis.study.mapping.productsMapper.getOneProduct";// 映射sql的标识字符串
		Products products = session.selectOne(statement, "BNBG02");
		System.out.println(products);
		session.close();
	}
	
	public static void selectCustomers(){
		SqlSession session = Utils.getSqlSession();
		String statement = "com.zhaoliang.mybatis.study.mapping.customersMapper.selectCustomers";// 映射sql的标识字符串
		List<Customers> customers = session.selectList(statement, "%");
		for(Customers c : customers){
			System.out.println(c);
		}
		session.close();
	}

	public static void test() throws FileNotFoundException {
		String resource = "conf.xml";// mybatis的配置文件
		// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
		// InputStream is =
		// Test1.class.getClassLoader().getResourceAsStream(resource);
		InputStream is = new FileInputStream(new File(resource));
		// 构建sqlSession的工厂
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);
		// 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
		// Reader reader = Resources.getResourceAsReader(resource);
		// 构建sqlSession的工厂
		// SqlSessionFactory sessionFactory = new
		// SqlSessionFactoryBuilder().build(reader);
		// 创建能执行映射文件中sql的sqlSession
		SqlSession session = sessionFactory.openSession();
		/**
		 * 映射sql的标识字符串，
		 * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
		 * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
		 */
		String statement = "com.zhaoliang.mybatis.study.mapping.customersMapper.getCustomer";// 映射sql的标识字符串
		// 执行查询返回一个唯一user对象的sql
		Customers customers = session.selectOne(statement, "1000000001");
		System.out.println(customers);
		System.out.println(customers.getCust_address());
	}
}
