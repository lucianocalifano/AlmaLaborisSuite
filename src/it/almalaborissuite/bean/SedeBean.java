package it.almalaborissuite.bean;

import java.io.Serializable;

/**
 * Un oggetto SedeBean rappresenta una sede di Alma Laboris Business School.
 * Una sede ha un nome, un riferimento, un indirizzo, un cap e una citta.
 * Ogni campo &egrave; rappresentato con la propria variabile di istanza.
 * 
 * @author AlmaDevelopers
 */
public class SedeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Rappresenta l'identificativo della sede
	 */
	private int identificativo;
	
	/**
	 * Rappresenta il nome della sede
	 */
	private String nome;
	
	/**
	 * Rappresenta il riferimento della sede
	 */
	private String riferimento;
	
	/**
	 * Rappresenta l'indirizzo della sede
	 */
	private String indirizzo;
	
	/**
	 * Rappresenta il codice di avviamento postale della sede
	 */
	private String cap;
	
	/**
	 * Rappresenta la citta della sede
	 */
	private String citta;
	
	/**
	 * Costruttore vuoto di SedeBean
	 */
	public SedeBean() { }
	
	/**
	 * Costruttore di SedeBean
	 * 
	 * @param nome nome della sede
	 * @param riferimento riferimento della sede
	 * @param indirizzo indirizzo della sede
	 * @param cap codice di avviamento postale della sede
	 * @param citta citta della sede
	 */
	public SedeBean(int identificativo, String nome, String riferimento, String indirizzo, String cap, String citta) {
		this.identificativo = identificativo;
		this.nome = nome;
		this.riferimento = riferimento;
		this.indirizzo = indirizzo;
		this.cap = cap;
		this.citta = citta;
	}

	/**
	 * Restituisce l'identificativo della sede
	 * 
	 * @return l'dentificativo della sede
	 */
	public int getIdentificativo() {
		return identificativo;
	}
	
	/**
	 * Setta l'identificativo della sede
	 * 
	 * Pre: identificativo != null
	 * @param identificativo
	 */
	public void setIdentificativo(int identificativo) {
		this.identificativo = identificativo;
	}
	
	/**
	 * Restituisce il nome della sede
	 * 
	 * @return nome della sede
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Setta un nuovo nome per la sede
	 * 
	 * Pre: nome != null
	 * @param nome nuovo nome per la sede
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restituisce il riferimento della sede
	 * 
	 * @return riferimento della sede
	 */
	public String getRiferimento() {
		return riferimento;
	}

	/**
	 * Setta un nuovo riferimento per la sede
	 * 
	 * Pre: riferimento != null
	 * @param riferimento nuovo riferimento per la sede
	 */
	public void setRiferimento(String riferimento) {
		this.riferimento = riferimento;
	}

	/**
	 * Restituisce l'indirizzo della sede
	 * 
	 * @return indirizzo della sede
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * Setta un nuovo indirizzo per la sede
	 * 
	 * Pre: indirizzo != null
	 * @param indirizzo nuovo indirizzo per la sede
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * Restituisce il codice di avviamento postale della sede
	 * 
	 * @return codice di avviamento postale della sede
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * Setta un nuovo codice di avviamento postale per la sede
	 * 
	 * Pre: cap != null
	 * @param cap nuovo codice di avviamento postale per la sede
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * Restituisce la citta della sede
	 * 
	 * @return citta della sede
	 */
	public String getCitta() {
		return citta;
	}

	/**
	 * Setta una nuova citta per la sede
	 * 
	 * Pre: citta != null
	 * @param citta nuova citta per la sede
	 */
	public void setCitta(String citta) {
		this.citta = citta;
	}

	@Override
	public String toString() {
		return "SedeBean [identificativo=" + identificativo + ", nome=" + nome + ", riferimento=" + riferimento
				+ ", indirizzo=" + indirizzo + ", cap=" + cap + ", citta=" + citta + "]";
	}
	
}
