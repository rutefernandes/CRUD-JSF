package br.com.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.model.Livro;
import br.com.util.HibernateUtil;

public class LivroDAO {
    private Session session;
    private Transaction transaction;
   
    public Livro getById(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Livro) session.load(Livro.class, id);
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
             System.out.println("deletado!");
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
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction operacaoSQL = session.beginTransaction();
        List objetos = session.createQuery("from Livro").list();
        operacaoSQL.commit();
        return objetos;

    }

   
    public void saveOrUpdate(Livro livro) {
        try {
           this.session = HibernateUtil.getSessionFactory().openSession();
           this.transaction = this.session.beginTransaction();
           if (livro.getId() == 0) {
           	this.session.save(livro);
           } else {
        	   this.session.update(livro);
           }
           this.transaction.commit();
           System.out.println("deletado!");
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


}
