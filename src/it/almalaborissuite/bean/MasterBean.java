package it.almalaborissuite.bean;

import java.io.Serializable;

/**
 * Un oggetto MasterBean rappresenta un percorso formativo (master) offerto da AlmaLaboris Business School.
 * Un master ha un identificativo, un nome che rappresenta la denominazione dello stesso, un tag che
 * rappresenta un'abbreviazione del nome del master, e un settore che rappresenta l'ambito di pertinenza del
 * percorso formativo.
 * 
 * @author AlmaDevelopers
 */
public class MasterBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	* Rappresenta l'id del Master
	*/
	private int id;
	
	/**
	* Rappresenta il nome dell Master
	*/
	private String nome;
	
	/**
	* Rappresenta il tag del Master
	*/
	private String tag;
	
	/**
	* Rappresenta il Settore del Master
	*/
	private SettoreBean settore;
	
	/**
	* Costruttore vuoto di MasterBean
	*/
	public MasterBean() { }
	
	/**
	* Costruttore di MasterBean
	* 
	* @param id identificativo del Master
	* @param nome nome del Master
	* @param tag tag del Master
	* @param settore settore del Master
	*/
	public MasterBean(int id, String nome, String tag, SettoreBean settore) {
		this.id=id;
		this.nome = nome;
		this.tag = tag;
		this.settore = settore;
	}
	
	/**
	* Restituisce l'id del Master
	* 
	* @return id del Master
	*/
	public int getId() {
		return id;
	}


	/**
	* Setta un id del Master
	* 
	* Pre: id != null
	* @param id nuovo identificativo del master di Alma Laboris
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* Restituisce il nome del Master
	* 
	* @return nome del Master
	*/
	public String getNome() {
		return nome;
	}
	
	/**
	* Setta un nuovo nome per il Master di MasterBean
	* 
	* Pre: nome != null
	* @param nome nuovo nome del Master di Alma Laboris
	*/
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	* Restituisce il tag del Master
	* 
	* @return tag del Master
	*/
	public String getTag() {
		return tag;
	}
	
	/**
	* Setta un nuovo tag per il Master di MasterBean
	* 
	* Pre: tag != null
	* @param tag nuovo tag per il Master di MasterBean
	*/
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	/**
	* Restituisce il settore del Master
	* 
	* @return settore del Master
	*/
	public SettoreBean getSettore() {
		return settore;
	}
	
	/**
	* Setta un nuovo settore per il Master di MasterBean
	* 
	* Pre: settore != null
	* @param settore nuovo settore per il Master di MasterBean
	*/
	public void setSettore(SettoreBean settore) {
		this.settore = settore;
	}

	@Override
	public String toString() {
		return "MasterBean [id=" + id + ", nome=" + nome + ", tag=" + tag + ", settore=" + settore + "]";
	}
	
	
}
