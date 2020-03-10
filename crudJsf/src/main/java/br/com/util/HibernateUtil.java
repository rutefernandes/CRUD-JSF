package br.com.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	private static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        try{
	        Configuration configuration = new Configuration();
	        configuration.configure("hibernate.cfg.xml");
	        return configuration.buildSessionFactory();
        }catch (Throwable e){
          System.out.println("Erro ao iniciar: " + e);
          	throw new ExceptionInInitializerError(e);
        }
    }  
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
