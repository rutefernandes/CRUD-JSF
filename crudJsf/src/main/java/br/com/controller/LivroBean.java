package br.com.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
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
    private Livro livro = new Livro()  ;
    
    public LivroBean(){
        this.livroDao = new LivroDAO();
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

    public void setListarVeiculos(DataModel listarVeiculos) {
        this.listarLivros = listarVeiculos;
    }

	public List <Livro> listar(){  
		livros = getLivroDao().listar();  
		return livros;  
    } 
    
    public String novoLivro(){
        livro = new Livro();
        return "adicionarLivro";
    }
    
    public String adicionar(){
    	getLivroDao().adicionar(getLivro());
        return "index";
    }
       
    public String salvar(ActionEvent event) {
    	getLivroDao().adicionar(getLivro());
        livro = new Livro();
        
        return "index";
    }
 
    
    public String deletar() {
        Livro objTemporario = (Livro) (listarLivros.getRowData());
        getLivroDao().deletar(objTemporario);
        return "index";
    }
    
    public String atualizar() {
        livro = (Livro) (listarLivros.getRowData());
        return "adicionarLivro";
    }
    
    public String salvarAtualizacao(){
        getLivroDao().alterar(livro);
        return "index";
    }
    
}