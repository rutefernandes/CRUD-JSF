package br.com.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.model.Livro;
import br.com.util.HibernateUtil;

public class LivroDAO {
    private List<Livro> livros = new ArrayList<Livro>();
    private Session session;
    private Transaction transaction;
   
    public Integer getId(){  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from User U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer id = 1;  
        if (results.get(0) != null){  
            id = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return id;  
    }  
    
    public void adicionar(Livro livro) {
          try {
	         this.session = HibernateUtil.getSessionFactory().openSession();
	         this.transaction = this.session.beginTransaction();
	         this.session.save(livro);
	         this.transaction.commit();
          }catch (HibernateException e) {
              System.out.println("Erro na inserção:" + e.getMessage());
          } finally {
        	  try {
        		  if (this.session.isOpen()) {
        			  this.session.close();
                  }
              } catch (Throwable e) {
            	  System.out.println("Erro ao tentar encerrar operação. Mensagem:" + e.getMessage());
              }
          }
    }
    
    public void deletar(Livro livro) {
          try {
             this.session = HibernateUtil.getSessionFactory().openSession();
             this.transaction = this.session.beginTransaction();
             this.session.delete(livro);
             this.transaction.commit();
          }catch (HibernateException e) {
              System.out.println("Erro ao deletar:" + e.getMessage());
          } finally {
	         try {
                if (this.session.isOpen()) {
                       this.session.close();
                }
	         } catch (Throwable e) {
	        	 System.out.println("Erro ao tentar encerrar operação. Mensagem:" + e.getMessage());
	         }
          }
    }
    
    public void alterar(Livro livro) {
          try {
             this.session = HibernateUtil.getSessionFactory().openSession();
             this.transaction = this.session.beginTransaction();
             this.session.update(livro);
             this.transaction.commit();
          }catch (HibernateException e) {
                 System.out.println("Não foi possível alterar. Erro:" + e.getMessage());
          } finally {
             try {
                if (this.session.isOpen()) {
                	this.session.close();
                }
             } catch (Throwable e) {
                 System.out.println("Erro ao tentar encerrar operação. Mensagem:" + e.getMessage());
             }
          }
    }
    
    public List<Livro> listar() {
          try {
             this.session = HibernateUtil.getSessionFactory().openSession();
             this.transaction = this.session.beginTransaction();
             Criteria filtro = this.session.createCriteria(Livro.class);
             livros = (List<Livro>) filtro.list();
             this.transaction.commit();
          } catch (Throwable e) {
	             if (this.transaction.isActive()) {
	                    this.transaction.rollback();
	             }
          } finally {
             try {
	            if (this.session.isOpen()) {
	                   this.session.close();
	            }
             } catch (Throwable e) {
                System.out.println("Erro ao tentar encerrar operação. Mensagem:" + e.getMessage());
             }
          }
          return livros;
    }


}
