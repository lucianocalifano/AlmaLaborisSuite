package it.almalaborissuite.bean;

import java.io.Serializable;

/**
 * Un oggetto AulaBean rappresenta un'aula appartenente ai vari poli didattici di Alma Laboris Business
 * School.
 * Un'aula ha un id che identifica univocamente l'aula, un nome, un piano in cui quest'ultima è situata 
 * e una sede di appartenenza. 
 * 
 * @author AlmaDevelopers
 */
public class AulaBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Rappresenta l'identificativo dell'aula 
	 */
	private int id;

	/**
	* Rappresenta il nome dell'aula
	*/
	private String nome;
	
	/**
	* Rappresenta il piano in cui è situata l'aula
	*/
	private String piano;
	
	/**
	* Rappresenta la sede in cui è situata l'aula
	*/
	private SedeBean sede;
		
	/**
	* Costruttore vuoto di AulaBean
	*/
	public AulaBean() { }
	
	/**
	* Costruttore di AulaBean
	* 
	* @param nome nome dell'aula
	* @param piano piano in cui è situata l'aula
	* @param sede sede in cui è situata l'aula
	*/
	public AulaBean(Integer id, String nome, String piano, SedeBean sede) {
		this.id=id;
		this.nome = nome;
		this.piano = piano;
		this.sede = sede;
	}
	
	/**
	* Restituisce l'identificativo dell'aula
	* 
	* @return id dell'aula
	*/
	public int getId() {
		return id;
	}

	/**
	* Setta un nuovo identificativo per l'aula di AulaBean
	* 
	* Pre: id != null
	* @param id nuovo identificativo dell'aula di Alma Laboris
	*/
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	* Restituisce il nome dell'aula
	* 
	* @return nome dell'aula
	*/
	public String getNome() {
		return nome;
	}

	/**
	* Setta un nuovo nome per l'aula di AulaBean
	* 
	* Pre: nome != null
	* @param nome nuovo nome dell'aula di Alma Laboris
	*/
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	* Restituisce il piano in cui si trova l'aula
	* 
	* @return piano piano in cui si trova l'aula
	*/
	public String getPiano() {
		return piano;
	}
	
	/**
	* Setta un nuovo piano in cui è situata l'aula di AulaBean
	* 
	* Pre: piano != null
	* @param piano nuovo piano in cui è situata l'aula di Alma Laboris
	*/
	public void setPiano(String piano) {
		this.piano = piano;
	}

	/**
	* Restituisce la sede in cui si trova l'aula
	* 
	* @return sede sede in cui si trova l'aula
	*/
	public SedeBean getSede() {
		return sede;
	}
	
	/**
	* Setta una nuova Sede per l'aula di AulaBean
	* 
	* Pre: sede != null
	* @param sede nuova sede dell'aula di Alma Laboris
	*/
	public void setSede(SedeBean sede) {
		this.sede = sede;
	}

	@Override
	public String toString() {
		return "AulaBean [id=" + id + ", nome=" + nome + ", piano=" + piano + ", sede=" + sede + "]";
	}
	
	
}
