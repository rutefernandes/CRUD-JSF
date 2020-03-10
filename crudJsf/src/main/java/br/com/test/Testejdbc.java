package br.com.test;

import org.hibernate.Session;

import br.com.util.HibernateUtil;
  
public class Testejdbc {
public static void main(String[] args) { 
Session sessao = null;
       try{
       sessao = HibernateUtil.getSessionFactory().openSession();
       System.out.println("Conectou!");
      } finally { 
    	  System.out.println("n conectou");
       //  sessao.close(); 
     }
  }
}