package br.com.controller;

import br.com.dao.LivroDAO; 
import br.com.model.Livro;import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "livroMB", eager = true)
@ViewScoped
public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List <Livro> livros;  
    LivroDAO livroDao = new LivroDAO();  
    Livro livro = new Livro();
    Livro novoLivro;
    
	public LivroDAO getLivroDao() {
		return livroDao;
	}

	public void setLivroDao(LivroDAO livroDao) {
		this.livroDao = livroDao;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Livro getNovoLivro() {
		return novoLivro;
	}

	public void setNovoLivro(Livro novoLivro) {
		this.novoLivro = novoLivro;
	}

	public List <Livro> listar(){  
		livros = getLivroDao().listar();  
		return livros;  
    } 
    
    public void adicionar(Livro livro) {
    	setNovoLivro(new Livro());
        //int id = 0;  
        //id = getLivroDao().getId();  
        //novoLivro.setId(id);  
        getLivroDao().adicionar(getNovoLivro());  
        System.out.println("Livro adicionado!");
    }
    
    public void deletar() {
        try {
            getLivroDao().deletar(getLivro());
            System.out.println("Livro deletado com sucesso");
        } catch (Exception e) {
            System.out.println("n deu");
        }
    }
    
    public void atualizar() {
    	try{
			getLivroDao().alterar(getLivro());  
		    System.out.println("livro atualizado!");  
    	} catch (Exception e){
    		System.out.println("n deu");
    	}
    }
}