package it.almalaborissuite.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Un oggetto ColloquioBean rappresenta il colloquio che un candidato ad un master offerto dalla 
 * Business School deve sostenere per essere ammesso.
 * Un colloquio ha un identificativo, un utente che rappresenta il candidato che sosterrà il colloquio, una
 * data inizio che rappresenta il quando avverrà il colloquio, una data fine che rappresenta il quando si 
 * terminerà il colloquio, un tipo che specifica se il colloquio è fisico oppure telefonico, e una sede che
 * rappresenta il dove il colloquio si svolgerà.
 * 
 * @author AlmaDevelopers
 */
public class ColloquioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	* Rappresenta l'id del colloquio
	*/
	private int id;
	
	/**
	* Rappresenta l'utente del colloquio
	*/
	private UtenteBean utente;
	
	/**
	* Rappresenta la data di inizio del colloquio intesa anche come orario
	*/
	private LocalDateTime data_inzio;
	
	/**
	* Rappresenta la data di fine del colloquio intesa anche come orario
	*/
	private LocalDateTime data_fine;
	
	/**
	* Rappresenta il tipo di colloquio fisisco o telefonico
	*/
	private String tipo;
	
	/**
	 * Rappresenta la sede dove l'utente sceglie di sostenere il colloquio
	 */
	private SedeBean sedeColloquio;
	
	
	/**
	* Costruttore vuoto di ColloquioBean
	*/
	public ColloquioBean(){ }
	
	/**
	* Costruttore di ColloquioBean
	* 
	* @param id identificativo del colloquio
	* @param utente utente del colloquio
	* @param data_inizio data di inizio del colloquio 
	* @param data_fine data di fine del colloquio
	* @param tipo tipo del colloquio
	* @param sede sede in cui viene effettuato il colloquio
 	*/ ColloquioBean(int id, UtenteBean utente, LocalDateTime data_inzio, LocalDateTime data_fine,
			String tipo, SedeBean sedeColloquio) {
		super();
		this.id = id;
		this.utente = utente;
		this.data_inzio = data_inzio;
		this.data_fine = data_fine;
		this.tipo = tipo;
		this.sedeColloquio = sedeColloquio;
	}
 	
 	/**
	* Restituisce l'id del colloquio
	* 
	* @return nome dell'aula
	*/
	public int getId() {
		return id;
	}
	/**
	 * Setta l'id del colloquio
	 * 
	 * Pre: id != null
	 * @param id id del colloquio
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	* Restituisce l'utente del colloquio
	* 
	* @return utente del colloquio
	*/
	public UtenteBean getUtente() {
		return utente;
	}
	
	/**
	 * Setta un Utente che deve sostenere un colloquio
	 * 
	 * Pre: utente != null
	 * @param utente utente che sostiene un colloquio
	 */
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}
	
	/**
	* Restituisce la data di inizio del colloquio
	* 
	* @return data_inizio di inizio del colloquio
	*/
	public LocalDateTime getData_inzio() {
		return data_inzio;
	}
	
	/**
	 * Setta la data di inizio di un colloquio
	 * 
	 * Pre: data_inizio != null
	 * @param data_inizio data dell'inizio del colloquio
	 */
	public void setData_inzio(LocalDateTime data_inzio) {
		this.data_inzio = data_inzio;
	}
	
	/**
	* Restituisce la data di fine del colloquio
	* 
	* @return data_fine del colloquio
	*/
	public LocalDateTime getData_fine() {
		return data_fine;
	}
	
	/**
	 * Setta la data di fine del colloquio
	 * 
	 * Pre: data_fine != null
	 * @param data_fine data di fine del colloquio
	 */
	public void setData_fine(LocalDateTime data_fine) {
		this.data_fine = data_fine;
	}

	/**
	* Restituisce il tipo del colloquio
	* 
	* @return tipo del colloquio
	*/
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Setta il tipo del colloquio
	 * 
	 * Pre: tipo != null
	 * @param tipo tipo del colloquio
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Restituisce la sede dove l'utente sceglie di sostenere il colloquio
	 * 
	 * @return sede dove l'utente sceglie di sostenere il colloquio
	 */
	public SedeBean getSedeColloquio() {
		return sedeColloquio;
	}
	
	/**
	 * Setta la sede dove l'utente sceglie di sostenere il colloquio
	 * 
	 * Pre: sedeColloquio != null
	 * @param sedeColloquio nuova sede dove l'utente sceglie di sostenere il colloquio
	 */
	public void setSedeColloquio(SedeBean sedeColloquio) {
		this.sedeColloquio = sedeColloquio;
	}

	@Override
	public String toString() {
		return "ColloquioBean [id=" + id + ", utente=" + utente + ", data_inzio=" + data_inzio + ", data_fine="
				+ data_fine + ", tipo=" + tipo + ", sedeColloquio=" + sedeColloquio + "]";
	}
	
	
	
}
