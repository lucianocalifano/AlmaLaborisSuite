package it.almalaborissuite.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Un oggetto ChiamataBean rappresenta la call effettuata da un operatore di Alma Laboris ad un 
 * potenziale cliente della Business School.
 * Una chiamata ha un identificativo, un utente che rappresenta il destinatario della chiamata, un
 * operatore che rappresenta il mittente della chiamata, una data chiamata che rappresenta il quando
 * la call avviene, un motivo che rappresenta il motivo della chiamata e un tipo che specifica se la 
 * chiamata è stata effettuata o ricevuta.
 * 
 * @author AlmaDevelopers
 */
public class ChiamataBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	* Rappresenta l'identificativo della chiamata
	*/
	private int id;
	
	/**
	* Rappresenta l'utente a cui è stata effettuata la chiamata
	*/
	private UtenteBean utente;
	
	/**
	* Rappresenta l'operatore che ha effettuato la chiamata
	*/
	private UtenteBean operatore;
	
	/**
	* Rappresenta la data in cui è stata effettuata la chiamata
	*/
	private LocalDateTime data_chiamata;
	
	/**
	* Rappresenta il motivo per cui è stata effettuata la chiamata
	*/
	private String motivo;
	
	/**
	* Rappresenta il tipo di chiamata effettuata
	*/
	private String tipo;
	
	/**
	* Costruttore vuoto di ChiamataBean
	*/
	public ChiamataBean () { }
	
	/**
	* Costruttore di ChiamataBean
	* 
	* @param id identificativo della chiamata
	* @param utente utente della chiamata
	* @param operatore operatore della chiamata
	* @param data_chiamata data della chiamata
	* @param motivo motivo della chiamata
	* @param tipo tipo della chiamata
	*/
	public ChiamataBean(int id, UtenteBean utente, UtenteBean operatore, LocalDateTime data_chiamata, String motivo,
			String tipo) {
		super();
		this.id = id;
		this.utente = utente;
		this.operatore = operatore;
		this.data_chiamata = data_chiamata;
		this.motivo = motivo;
		this.tipo = tipo;
	}
	
	/**
	* Restituisce l'id della chiamata
	* 
	* @return id della chiamata
	*/
	public int getId() {
		return id;
	}
	
	/**
	* Setta un nuovo id di una chiamata
	* 
	* Pre: id != null
	* @param id nuovo id di una chiamata
	*/
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	* Restituisce l'utente a cui è stata effettuata la chiamata
	* 
	* @return utente del sistema a cui è stata effettuata la chiamata
	*/
	public UtenteBean getUtente() {
		return utente;
	}
	
	/**
	* Setta un nuovo Utente di una chiamata
	* 
	* Pre: utente != null
	* @param utente nuovo utente di una chiamata
	*/
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}
	
	/**
	* Restituisce l'Operatore che ha effettuato la chiamata
	* 
	* @return operatore Operatore di Alma Laboris
	*/
	public UtenteBean getOperatore() {
		return operatore;
	}
	
	/**
	* Setta un nuovo Operatore che effettua una chiamata
	* 
	* Pre: operatore != null
	* @param operatore nuovo Operatore che ha effettuato una chiamata
	*/
	public void setOperatore(UtenteBean operatore) {
		this.operatore = operatore;
	}
	
	/**
	* Restituisce la data di una chiamata
	* 
	* @return data_chiamata chiamata effettuata
	*/
	public LocalDateTime getData_chiamata() {
		return data_chiamata;
	}
	
	/**
	* Setta una data di una chiamata
	* 
	* Pre: data_chiamata != null
	* @param data_chiamata data della chiamata effettuata
	*/
	public void setData_chiamata(LocalDateTime data_chiamata) {
		this.data_chiamata = data_chiamata;
	}
	
	/**
	* Restituisce il motivo della chiamata
	* 
	* @return motivo motivo della chiamata
	*/
	public String getMotivo() {
		return motivo;
	}
	
	/**
	* Setta un motivo di una chiamata effettuata
	* 
	* Pre: nome != null
	* @param nome nuovo nome dell'aula di Alma Laboris
	*/
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	/**
	* Restituisce il tipo della chiamata
	* 
	* @return tipo tipo della chiamata
	*/
	public String getTipo() {
		return tipo;
	}
	
	/**
	* Setta il tipo della chiamata
	* 
	* Pre: tipo != null
	* @param tipo tipo della chiamata
	*/
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChiamataBean [id=" + id + ", utente=" + utente + ", operatore=" + operatore + ", data_chiamata="
				+ data_chiamata + ", motivo=" + motivo + ", tipo=" + tipo + "]";
	}
	
	
	
}
