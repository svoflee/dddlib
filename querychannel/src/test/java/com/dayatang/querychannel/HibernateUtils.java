package com.dayatang.querychannel;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import com.dayatang.querychannel.domain.MyEntity;

public class HibernateUtils {
	private static Configuration cfg;
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			cfg = new Configuration()
					.addAnnotatedClass(MyEntity.class)
					.configure();
			new SchemaExport(cfg).create(true, true);
			return cfg.buildSessionFactory();
		} catch (Exception ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static synchronized SessionFactory getSessionFactory() {
		if (sessionFactory == null || sessionFactory.isClosed()) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

	public static void close() {
		sessionFactory.close();
	}

}
