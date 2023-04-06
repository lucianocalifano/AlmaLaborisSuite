package it.almalaborissuite.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Un oggetto EdizioneBean rappresenta un'edizione di uno specifico specifico master.
 * Un'edizione ha un identificativo, un nome che ne caratterizza il periodo di partenza di tale edizione, una
 * data invio scheda che rappresenta la data prefissata in cui le schede d'iscrizione dovranno essere inviate
 * ai candidati che superato il colloquio devono formalizzare l'iscrizione al master, data fine che
 * rappresenta la data in cui l'edizione si chiude, inizio verifica finale che rappresenta la data e l'ora
 * in cui i pax dovranno sostenere l'esame di verifica finale, fine verifica finale che rappresenta la data
 * e l'ora in cui si termina la verifica finale, un master che rappresenta il master a cui è legata
 * la specifica edizione, e un aula che rappresenta il dove l'edizione si svolgerà.
 * 
 * @author AlmaDevelopers
 */
public class EdizioneBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Rappresenta l'identificativo
	 */
	private int id;
	
	/**
	 * Rappresenta il nome dell'edizione
	 */
	private String nome;
	
	/**
	 * Rappresenta la data di inivio delle schede di iscrizione
	 */
	private LocalDateTime dataInvioScheda;
	
	/**
	* Rappresenta la data di inzio di un Edizione
	*/
	private LocalDateTime dataInizio;
	
	/**
	* Rappresenta la data di fine di un Edizione
	*/
	private LocalDateTime dataFine;
	
	/**
	* Rappresenta la data di inizio di una verifica finale
	*/
	private LocalDateTime inizioVf;
	
	/**
	* Rappresenta la data di fine di una verifica finale
	*/
	private LocalDateTime fineVf;
	
	/**
	* Rappresenta il master di un edizione
	*/
	private MasterBean master;
	
	/**
	* Rappresenta un aula di un edizione
	*/
	private AulaBean aula;
	
	/**
	* Costruttore vuoto di EdizioneBean
	*/
	public EdizioneBean() {}

	/**
	 * Costruttore di EdizioneBean
	 * 
	 * @param id id dell'Edizione
	 * @param nome nome dell'Edizione
	 * @param dataInvioScheda la data di invio automatico delle schede d'iscrizione
	 * @param dataInizio data Inizio Edizione
	 * @param dataFine data fine Edizione
	 * @param inizioVf data inizio verifica finale
	 * @param fineVf data fine verifica finale
	 * @param master master dell'Edizione
	 * @param aula aula dell'Edizione
	 */
	public EdizioneBean(int id, String nome, LocalDateTime dataInvioScheda, LocalDateTime dataInizio, LocalDateTime dataFine, LocalDateTime inizioVf, LocalDateTime fineVf, MasterBean master, AulaBean aula) {
		this.id = id;
		this.nome = nome;
		this.dataInvioScheda = dataInvioScheda;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.inizioVf = inizioVf;
		this.fineVf = fineVf;
		this.master = master;
		this.aula = aula;
	}
	
	/**
	* Restituisce l'id dell'edizione
	* 
	* @return id dell'edizione
	*/
	public int getId() {
		return id;
	}

	/**
	* Setta un nuovo id di un edizione
	* 
	* Pre: id != null
	* @param id id dell'Edizione
	*/
	public void setId(int id) {
		this.id = id;
	}

	/**
	* Restituisce il nome dell'edizione
	* 
	* @return nome dell'edizione
	*/
	public String getNome() {
		return nome;
	}

	/**
	* Setta un nuovo nome per l'Edizione di Alma Laboris
	* 
	* Pre: nome != null
	* @param nome nuovo nome dell'edizione di Alma Laboris
	*/
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Restituisce la data di invio automatico delle schede di iscrizione
	 * 
	 * @return data di invio automatico delle schede di iscrizione
	 */
	public LocalDateTime getDataInvioScheda() {
		return dataInvioScheda;
	}

	/**
	 * Setta la data d'invio automatico per le schede di iscrizione
	 * 
	 * Pre: dataInvioScheda != null
	 * @param dataInvioScheda nuova data di invio delle schede di iscrizione
	 */
	public void setDataInvioScheda(LocalDateTime dataInvioScheda) {
		this.dataInvioScheda = dataInvioScheda;
	}

	/**
	* Restituisce la data di inzio di un edizione
	* 
	* @return dataInizio dell'edizione
	*/
	public LocalDateTime getDataInizio() {
		return dataInizio;
	}

	/**
	* Setta un data di inizio dell'Edizione
	* 
	* Pre: nome != null
	* @param nome nuovo nome dell'aula di Alma Laboris
	*/
	public void setDataInizio(LocalDateTime dataInizio) {
		this.dataInizio = dataInizio;
	}

	/**
	* Restituisce la data di fine di un edizione
	* 
	* @return dataFine dell'edizione
	*/
	public LocalDateTime getDataFine() {
		return dataFine;
	}
	
	/**
	* Setta una data di fine dell'Edizione
	* 
	* Pre: dataFine != null
	* @param dataFine data di fine dell'Edizione
	*/
	public void setDataFine(LocalDateTime dataFine) {
		this.dataFine = dataFine;
	}

	/**
	* Restituisce la data di inzio di una verifica finale
	* 
	* @return inizioVf inizio della verifica finale
	*/
	public LocalDateTime getInizioVf() {
		return inizioVf;
	}

	/**
	* Setta data di inizio di una verifica finale
	* 
	* Pre: inizioVf != null
	* @param inizioVf data di inizio di una verifica finale
	*/
	public void setInizioVf(LocalDateTime inizioVf) {
		this.inizioVf = inizioVf;
	}

	/**
	* Restituisce la data di fine di una verifica finale
	* 
	* @return fineVf data di fine di una verifica finale
	*/
	public LocalDateTime getFineVf() {
		return fineVf;
	}
	
	/**
	* Setta una data di fine di una verifica finale
	* 
	* Pre: fineVf != null
	* @param fineVf data di fine di una verifica finale
	*/
	public void setFineVf(LocalDateTime fineVf) {
		this.fineVf = fineVf;
	}

	/**
	* Restituisce il master di un edizione
	* 
	* @return master dell'edizione
	*/
	public MasterBean getMaster() {
		return master;
	}

	/**
	* Setta un master relativo ad un Edizione
	* 
	* Pre: master != null
	* @param master master relativo ad un Edizione di Alma Laboris
	*/
	public void setMaster(MasterBean master) {
		this.master = master;
	}

	/**
	* Restituisce un aula dell'edizione
	* 
	* @return aula dell'edizione
	*/
	public AulaBean getAula() {
		return aula;
	}

	/**
	* Setta un aula di un'Edizione
	* 
	* Pre: aula != null
	* @param aula nuova aula dell'Edizione
	*/
	public void setAula(AulaBean aula) {
		this.aula = aula;
	}

	@Override
	public String toString() {
		return "EdizioneBean [id=" + id + ", nome=" + nome + ", dataInvioScheda=" + dataInvioScheda + ", dataInizio="
				+ dataInizio + ", dataFine=" + dataFine + ", inizioVf=" + inizioVf + ", fineVf=" + fineVf + ", master="
				+ master + ", aula=" + aula + "]";
	}
	
}
