package it.almalaborissuite.bean;

import java.io.Serializable;

/**
 * Un oggetto SettoreBean rappresenta un settore relativo ai master proposti da Alma Laboris Business
 * School.
 * Un settore ha un nome.  
 * Ogni campo &egrave; rappresentato con la propria variabile di istanza.
 * 
 * @author AlmaDevelopers
 */
public class SettoreBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Rappresenta l'identificativo del settore
	 */
	private int identificativo;
	
	/**
	 * Rappresenta il nome del settore
	 */
	private String nome;
	
	/**
	 * Costruttore vuoto di SettoreBean
	 */
	public SettoreBean() { }
	
	/**
	 * Costruttore di SettoreBean
	 * 
	 * @param identificativo identificativo del settore
	 * @param nome nome del settore
	 */
	public SettoreBean(int identificativo, String nome) { 
		this.identificativo = identificativo;
		this.nome = nome;
	}

	/**
	 * Restituisce l'identificativo del settore
	 * 
	 * @return identificativo del settore
	 */
	public int getIdentificativo() {
		return identificativo;
	}
	
	/**
	 * Setta un nuovo identificativo per il settore
	 * 
	 * Pre: identificativo != null
	 * @param identificativo
	 */
	public void setIdentificativo(int identificativo) {
		this.identificativo = identificativo;
	}
	
	/**
	 * Restituisce il nome del settore
	 * 
	 * @return nome del settore
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setta un nuovo nome per il settore
	 * 
	 * Pre: nome != null
	 * @param nome nuovo nome per il settore
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "SettoreBean [identificativo=" + identificativo + ", nome=" + nome + "]";
	}
	
}
