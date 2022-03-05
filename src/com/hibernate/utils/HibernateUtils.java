package com.hibernate.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.ismo.brevets.models.Brevet;
import com.ismo.brevets.models.Domaine;
import com.ismo.brevets.models.Entreprise;
import com.ismo.brevets.models.Inventeur;
import com.ismo.brevets.models.Invention;


public class HibernateUtils {

	private static final SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	
	static {
		Configuration config = new Configuration();
		config.configure();
		config.addAnnotatedClass(Domaine.class);
		config.addAnnotatedClass(Inventeur.class);
		config.addAnnotatedClass(Invention.class);
		config.addAnnotatedClass(Brevet.class);
		config.addAnnotatedClass(Entreprise.class);
		
		serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
		sessionFactory = config.buildSessionFactory(serviceRegistry);
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
