package com.hh.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DButils1 {
	public static SessionFactory factory;
	static{
		//��ȡ�������ù�����
        Configuration configuration = new Configuration();

        //����������Ĭ�ϼ���hibernate.cfg.xml�ļ���
        configuration.configure();

        //����Session��������
         factory = configuration.buildSessionFactory();
	}
	
}
