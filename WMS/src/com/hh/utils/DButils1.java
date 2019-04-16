package com.hh.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DButils1 {
	public static SessionFactory factory;
	static{
		//获取加载配置管理类
        Configuration configuration = new Configuration();

        //不给参数就默认加载hibernate.cfg.xml文件，
        configuration.configure();

        //创建Session工厂对象
         factory = configuration.buildSessionFactory();
	}
	
}
