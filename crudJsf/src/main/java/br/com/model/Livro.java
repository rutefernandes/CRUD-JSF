package br.com.model;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.Id;   
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;  
import javax.persistence.Temporal;  
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;  
import static javax.persistence.GenerationType.IDENTITY;  


@Entity  
@Table(name = "livro")  
public class Livro implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String titulo; 
	private String isbn;
	private String autor;
	private String editora;
	private Date data_pub;
	
	public Livro() {
        super();
    }
	
    public Livro(int id, String titulo, String isbn, String autor, String editora,
    		Date data_pub) {
        super();
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.autor = autor;
        this.editora = editora;
        this.data_pub = data_pub;
    }
	
    @Id @GeneratedValue(strategy = IDENTITY)  
    @Column(name = "id", unique = true, nullable = false) 
	public int getId() {
		return id;
	}
    
    
	public void setId(int id) {
		this.id = id;
	
	}
	
    @Column(name = "titulo", length = 50)
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(name = "isbn", length = 15)
	public String getIsbn() {
		return isbn;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	@Column(name = "autor", length = 50)
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	@Column(name = "editora", length = 50)
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	@Temporal(TemporalType.DATE) 
    @Column(name = "data_pub", length = 9)  
	public Date getData_pub() {
		return data_pub;
	}
	
	public void setData_pub(Date data_pub) {
		this.data_pub = data_pub;
	}
}
