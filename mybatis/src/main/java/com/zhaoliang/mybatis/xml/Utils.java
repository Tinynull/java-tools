package com.zhaoliang.mybatis.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Utils {
	
	private Utils() {}
	
	public static String configXMLFile = "conf.xml";
	
	/**
	 * 获取数据库会话。
	 * 
	 * @return
	 */
	public static SqlSession getSqlSession() {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(configXMLFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
				.build(is);

		SqlSession session = sessionFactory.openSession();
		return session;
	}
}
