package br.com.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import br.com.dao.LivroDAO;
import br.com.model.Livro;

@ManagedBean(name = "livroMB", eager = true)
@SessionScoped
public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private List <Livro> livros;  
    private DataModel<Livro> listarLivros;
    private LivroDAO livroDao;  
    private Livro livro;
    
    @PostConstruct
    public void init() {
        livro = new Livro();
    }
    
    public LivroBean(){
        this.livroDao = new LivroDAO();
        livros = getLivroDao().listar();
    }
    
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

	public DataModel<Livro> getListarLivros() {
        List<Livro> lista = new LivroDAO().listar();
        listarLivros = new ListDataModel<Livro>(lista);
        return listarLivros;
    }

	public List<Livro> getLivros() {
		livros = new LivroDAO().listar();
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public List <Livro> listar(){  
		return getLivros(); 
    } 
    
    public String novoLivro(){
        return "adicionarLivro";
    }
    
       
    public String salvar() {
    		new LivroDAO().adicionar(livro);
    		livro = new Livro();
    		listar();
        return "index?faces-redirect=true";
    }
    
    
    public String deletar() {
        Livro objTemporario = (Livro) (listarLivros.getRowData());
        getLivroDao().deletar(objTemporario);
        return "index";
    }
    
    public String atualizar() {
        livro = (Livro) (listarLivros.getRowData());
        System.out.println(livro.getAutor());
        return "adicionarLivro";
    }
    
    public String salvarAtualizacao(){
    	System.out.println(livro.getAutor());
        getLivroDao().alterar(livro);
        return "index?faces-redirect=true";
    }
    
}