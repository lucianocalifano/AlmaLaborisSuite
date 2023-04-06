package it.almalaborissuite.bean;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Un oggetto CandidatoBean rappresenta la relazione tra UtenteBean e MasterBean e eticamente rappresenta un potenziale cliente
 * avvicinatosi alla Business School.
 * Un candidato ha oltre agli attributi di UtenteBean, un oggetto MasterBean, una data di inserimento o creazione del record automatica, una fonte
 * di provenienza, un formatScelto (in aula oppure online), un Curriculum vitae che rappresenta il path relativo al pdf sul server, come anche la schedaIscrizione e  
 * la prima quietanza di pagamento.
 * 
 * @author AlmaDevelopers
 */
public class CandidatoBean extends UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	* Rappresenta l'oggetto Master di preferenza del candidato
	*/
	private MasterBean master;
	
	/**
	* Rappresenta la data in cui è stato inserito il record reltivo al candidato all'interno del database
	*/
	private LocalDateTime dataInserimento;
	
	/**
	* Rappresenta la fonte di provenienza
	*/
	private String fonte;
	
	/**
	* Rappresenta la modalità con cui il candidato ha scelto di seguire il master
	*/
	private String formatScelto;
	
	/**
	* Rappresenta il path relativo al Curriculum vitae
	*/
	private String cv;
	
	/**
	* Rappresenta il path relativo alla scheda iscrizione
	*/
	private String schedaIscrizione;
	
	/**
	* Rappresenta il path relativo alla prima quietanza di pagamento
	*/
	private String quietanza1;
	
	/**
	* Restituisce il format scelto dal candidato
	* 
	* @return modalità con cui si è scelto di seguire il Master
	*/
	public String getFormatScelto() {
		return formatScelto;
	}

	/**
	* Setta un format per il candidato di Alma Laboris
	* 
	* Pre: formatScelto != null
	* @param formatScelto modalità in cui si è scelto di seguire il Master
	*/
	public void setFormatScelto(String formatScelto) {
		this.formatScelto = formatScelto;
	}

	/**
	* Costruttore vuoto di CandidatoBean
	*/
	public CandidatoBean() { }
	
	/**
	* Costruttore di CandidatoBean
	* 
	* @param email email del candidato
	* @param ruolo ruolo del candidato
	* @param nome nome del candidato
	* @param cognome cognome del candidato
	* @param telefono telefono del candidato
	* @param indirizzo indirizzo del candidato
	* @param cap cap del candidato
	* @param citta citta di residenza del candidato
	* @param provincia provincia di residenza
	* @param dataNascita data di nascita del candidato
	* @param note note relative al candidato
	* @param master Master di preferenza del candidato
	* @param dataInserimento data crezione record del candidato nel database
	* @param fonte fonte di provenienza del candidato
	* @param formatScelto modalità di svolgimento del master del candidato
	* @param cv path relativo al cv del candidato
	* @param schedaIscrizione path relativo alla scheda di iscrizione del candidato
	* @param quietanza1 path relativo alla prima quietanza del candidato
	*/
	public CandidatoBean(String email, String ruolo, String nome, String cognome, String telefono, String indirizzo, String cap, 
			String citta, String provincia, LocalDateTime dataNascita, File note, MasterBean master, LocalDateTime dataInserimento, 
			String fonte, String formatScelto, String cv, String schedaIscrizione, String quietanza1) {
		super(email, ruolo, nome, cognome, telefono, indirizzo, cap, citta, provincia, dataNascita, note);
		this.master = master;
		this.dataInserimento = dataInserimento;
		this.fonte = fonte;
		this.formatScelto = formatScelto;
		this.cv = cv;
		this.schedaIscrizione = schedaIscrizione;
		this.quietanza1 = quietanza1;
	}

	/**
	* Restituisce l'oggetto Master relativo al candidato
	* 
	* @return Master di tipo MasterBean
	*/
	public MasterBean getMaster() {
		return master;
	}

	
	/**
	* Setta un nuovo oggetto di tipo MasterBean del candidato
	* 
	* Pre: Master != null
	* @param master nuovo master del candidato di Alma Laboris
	*/
	public void setMaster(MasterBean master) {
		this.master = master;
	}

	/**
	* Restituisce la data in cui è stato inserito il record nel database
	* 
	* @return LocalDateTime dataInserimento
	*/
	public LocalDateTime getDataInserimento() {
		return dataInserimento;
	}

	
	/**
	* Setta un data di inserimento del candidato nel database
	* 
	* Pre: dataInserimento != null
	* @param dataInserimento raprresenta la data in cui il candidato è stato inserito nel database
	*/
	public void setDataInserimento(LocalDateTime dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
	
	/**
	* Restituisce la fonte di provenienza del candidato
	* 
	* @return String fonte 
	*/
	public String getFonte() {
		return fonte;
	}

	
	/**
	* Setta una fonte di provenienza del candidato
	* 
	* Pre: fonte != null
	* @param fonte rappresenta la fonte di provenienza del candidato
	*/
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}

	/**
	* Restituisce path relativo al CV del candidato
	* 
	* @return String Curriculum Vitae
	*/
	public String getCv() {
		return cv;
	}

	/**
	* Setta un path relativo al curriculum vitae del candidao
	* 
	* Pre: cv != null
	* @param cv nuovo path relativo al curriclum vitae del candidato
	*/
	public void setCv(String cv) {
		this.cv = cv;
	}

	/**
	* Restituisce il path relativo alla scheda iscrizione del candidato
	* 
	* @return String schedaIscrizione
	*/
	public String getSchedaIscrizione() {
		return schedaIscrizione;
	}

	/**
	* Setta il path relativo alla scheda iscrizione del candidato
	* 
	* Pre: SchedaIscrizione != null
	* @param SchedaIscrizione nuovo path relativo alla scheda di iscrizione del candidato
	*/
	public void setSchedaIscrizione(String schedaIscrizione) {
		this.schedaIscrizione = schedaIscrizione;
	}

	/**
	* Restituisce il path relativo alla prima quietanza di pagamento
	* 
	* @return String Quietanza1
	*/
	public String getQuietanza1() {
		return quietanza1;
	}

	/**
	* Setta il path relativo alla prima quietanza di pagamento
	* 
	* Pre: Quietanza1 != null
	* @param Quietanza1 nuovo path relativo alla prima quietanza di pagamento
	*/
	public void setQuietanza1(String quietanza1) {
		this.quietanza1 = quietanza1;
	}

	
	@Override
	public String toString() {
		return "CandidatoBean [email="+getEmail()+", nome="+getNome()+", cognome="+getCognome()+", ruolo="+getRuolo()+", master=" + master + ", dataInserimento=" + dataInserimento + ", fonte=" + fonte
				+ ", formatScelto=" + formatScelto + ", cv=" + cv + ", schedaIscrizione=" + schedaIscrizione
				+ ", quietanza1=" + quietanza1 + "]";
	}
		
}
