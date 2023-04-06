package it.almalaborissuite.bean;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Un oggetto PartecipanteBean rappresenta un partecipante loggato al sistema AlmaLaborisSuite.
 * Un partecipante ha una email, un ruolo, un nome, un cognome, un numero di telefono, un indirizzo,
 * un cap, una città, una provincia, una data di nascita, un edizione, una data di inserimento, una
 * fonte, un cv, una scheda di iscrizione, una prima quietanza, una seconda quietanza e una terza
 * quietanza.  
 * Ogni campo &egrave; rappresentato con la propria variabile di istanza.
 * 
 * @author AlmaDevelopers
 */
public class PartecipanteBean extends UtenteBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Rappresenta l'edizione a cui il partecipante &egrave; iscritto
	 */
	private EdizioneBean edizione;
	
	/**
	 * Rappresenta la data di inserimento del partecipante in piattaforma
	 */
	private LocalDateTime dataInserimento;
	
	/**
	 * Rappresenta la fonte di provenienza del partecipante
	 */
	private String fonte;
	
	/**
	 * Rappresenta il format di preferenza selezionato dall'utente 
	 */
	private String formatScelto;
	
	/**
	 * Rappresenta il path del cv del partecipante
	 */
	private String cv;
	
	/**
	 * Rappresenta il path della scheda d'iscrizione del partecipante
	 */
	private String schedaIscrizione;
	
	/**
	 * Rappresenta il path della prima quietanza del partecipante
	 */
	private String quietanza1;
	
	/**
	 * Rappresenta il path della seconda quietanza del partecipante
	 */
	private String quietanza2;
	
	/**
	 * Rappresenta il path della terza quietanza del partecipante
	 */
	private String quietanza3;
	
	/**
	 * Costruttore vuoto di PartecipanteBean
	 */
	public PartecipanteBean() {}
	
	/**
	 * Costruttore di PartecipanteBean
	 * 
	 * @param email email del partecipante
	 * @param ruolo ruolo dell'utente nel db
	 * @param nome  nome del partecipante
	 * @param cognome cognome del partecipante
	 * @param telefono telefono del partecipante
	 * @param indirizzo indirizzo del partecipante
	 * @param cap cap del partecipante
	 * @param citta citta del partecipante
	 * @param provincia provincia del partecipante
	 * @param dataNascita data di nascita del partecipante
	 * @param note note relative all'utente
	 * @param edizione edizione a cui è iscritto il partecipante
	 * @param dataInserimento data d'inserimento del partecipante
	 * @param fonte fonte di provenienza del partecipante
	 * @param cv path relativo al cv del partecipante
	 * @param schedaIscrizione path relativo alla scheda d'iscrizione del partecipante
	 * @param quietanza1 path relativo alla prima quietanza del partecipante
	 * @param quietanza2 path relativo alla seconda quietanza del partecipante
	 * @param quietanza3 path relativo alla terza quietanza del partecipante
	 */
	public PartecipanteBean(String email, String ruolo, String nome, String cognome, String telefono, String indirizzo, 
			String cap, String citta, String provincia, LocalDateTime dataNascita, File note, EdizioneBean edizione, 
			LocalDateTime dataInserimento, String fonte, String formatScelto, String cv, String schedaIscrizione, 
			String quietanza1, String quietanza2, String quietanza3) {
		super(email, ruolo, nome, cognome, telefono, indirizzo, cap, citta, provincia, dataNascita, note);
		this.edizione = edizione;
		this.dataInserimento = dataInserimento;
		this.fonte = fonte;
		this.formatScelto = formatScelto;
		this.cv = cv;
		this.schedaIscrizione = schedaIscrizione;
		this.quietanza1 = quietanza1;
		this.quietanza2 = quietanza2;
		this.quietanza3 = quietanza3;
	}

	/**
	 * Restituisce l'edizione a cui è iscritto il partecipante
	 * 
	 * @return edizione a cui è iscritto il partecipante
	 */
	public EdizioneBean getEdizione() {
		return edizione;
	}

	/**
	 * Setta una nuova edizione per il partecipante
	 * 
	 * Pre: edizione != null
	 * @param edizione nuova edizione del partecipante
	 */
	public void setEdizione(EdizioneBean edizione) {
		this.edizione = edizione;
	}

	/**
	 * Restituisce la data d'inserimento del partecipante in piattaforma
	 * 
	 * @return data d'inserimento del partecipante in piattaforma
	 */
	public LocalDateTime getDataInserimento() {
		return dataInserimento;
	}

	/**
	 * Setta una nuova data d'inserimento del partecipante in piattaforma
	 * 
	 * Pre: dataInserimento != null
	 * @param dataInserimento nuova data d'inserimento del partecipante in piattaforma
	 */
	public void setDataInserimento(LocalDateTime dataInserimento) {
		this.dataInserimento = dataInserimento;
	}

	/**
	 * Restituisce la fonte di provenienza del partecipante
	 * 
	 * @return fonte di provenienza del partecipante
	 */
	public String getFonte() {
		return fonte;
	}

	/**
	 * Setta una nuova fonte di provenienza del partecipante
	 * 
	 * Pre: fonte != null 
	 * @param fonte nuova fonte di provenieneza del partecipante
	 */
	public void setFonte(String fonte) {
		this.fonte = fonte;
	}
	
	/**
	 * Restituisce il format di preferenza scelto dall'utente
	 * 
	 * @return il format di preferenza scelto dall'utente
	 */
	public String getFormatScelto() {
		return formatScelto;
	}

	/**
	 * Setta un nuovo format di preferenza per l'utente
	 * 
	 * Pre: formatScelto == AULA || formatScelto == ONLINE
	 * @param formatScelto nuovo format di preferenza
	 */
	public void setFormatScelto(String formatScelto) {
		this.formatScelto = formatScelto;
	}

	/**
	 * Restituisce il path relativo al cv del partecipante
	 * 
	 * @return path del cv del partecipante
	 */
	public String getCv() {
		return cv;
	}

	/**
	 * Setta il nuovo path relativo al cv del partecipante
	 * 
	 * Pre: cv != null
	 * @param cv nuovo path relativo al cv del partecipante
	 */
	public void setCv(String cv) {
		this.cv = cv;
	}
	
	/**
	 * Restituisce il path relativo alla scheda d'iscrizione del partecipante
	 * 
	 * @return il path relativo alla scheda d'iscrizione del partecipante
	 */
	public String getSchedaIscrizione() {
		return schedaIscrizione;
	}
	
	/**
	 * Setta il nuovo path relativo alla scheda d'iscrizione del partecipante
	 * 
	 * Pre: schedaIscrione != null
	 * @param schedaIscrizione nuovo path relativo alla scheda d'iscrizione del partecipante
	 */
	public void setSchedaIscrizione(String schedaIscrizione) {
		this.schedaIscrizione = schedaIscrizione;
	}

	/**
	 * Restituisce il path relativo alla prima quietanza del partecipante
	 * 
	 * @return il path relativo alla prima quietanza del partecipante
	 */
	public String getQuietanza1() {
		return quietanza1;
	}

	/**
	 * Setta il nuovo path relativo alla prima quietanza del partecipante
	 * 
	 * Pre: quietanza1 != null
	 * @param quietanza1 nuovo path relativo alla prima quietanza del partecipante
	 */
	public void setQuietanza1(String quietanza1) {
		this.quietanza1 = quietanza1;
	}

	/**
	 * Restituisce il path relativo alla seconda quietanza del partecipante
	 * 
	 * @return il path relativo alla seconda quietanza del partecipante
	 */
	public String getQuietanza2() {
		return quietanza2;
	}

	/**
	 * Setta il nuovo path relativo alla seconda quietanza del partecipante
	 * 
	 * Pre: quietanza2 != null
	 * @param quietanza2 nuovo path relativo alla seconda quietanza del partecipante
	 */
	public void setQuietanza2(String quietanza2) {
		this.quietanza2 = quietanza2;
	}

	/**
	 * Restituisce il path relativo alla terza quietanza del partecipante
	 * 
	 * @return il path relativo alla terza quietanza del partecipante
	 */
	public String getQuietanza3() {
		return quietanza3;
	}

	/**
	 * Setta il nuovo path relativo alla terza quietanza del partecipante
	 * 
	 * Pre: quietanza3 != null
	 * @param quietanza3 nuovo path relativo alla terza quietanza del partecipante
	 */
	public void setQuietanza3(String quietanza3) {
		this.quietanza3 = quietanza3;
	}

	@Override
	public String toString() {
		return "PartecipanteBean [email="+getEmail()+", nome="+getNome()+", cognome="+getCognome()+", edizione=" + edizione.getNome() + ", dataInserimento=" + dataInserimento + ", fonte=" + fonte
				+ ", formatScelto=" + formatScelto + ", cv=" + cv + ", schedaIscrizione=" + schedaIscrizione
				+ ", quietanza1=" + quietanza1 + ", quietanza2=" + quietanza2 + ", quietanza3=" + quietanza3 + "]";
	}	

}
