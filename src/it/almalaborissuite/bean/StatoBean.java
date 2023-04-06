package it.almalaborissuite.bean;

import java.io.Serializable;

/**
 * Un oggetto StatoBean rappresenta lo stato di un utente loggato ad AlmaLaborisSuite.
 * Uno stato ha un utente, un boolean interessato, un boolean contattato, un boolean attesaDataSelezione,
 * un boolean presenzaSelezione, un boolean attesaEsitoSelezione, un boolean ammesso, un boolean
 * invioSchedaIscrizione, un boolean iscritto, un boolean quietanza1Pagata, un boolean quietaza2Pagata,
 * un boolean quietanza3Pagata.  
 * Ogni campo &egrave; rappresentato con la propria variabile di istanza.
 * 
 * @author AlmaDevelopers
 */
public class StatoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Rappresenta l'utente di riferimento dello stato
	 */
	private UtenteBean utente;
	
	/**
	 * Rappresenta il nome dell'edizione a cui si riferisce lo stato dell'utente
	 */
	private String edizione;
	
	/**
	 * Rappresenta il master relativo allo stato del'utente
	 */
	private MasterBean master;
	
	/**
	 * Indica l'interessamento di un utente
	 */
	private boolean interessato;
	
	/**
	 * Indica la provenienza da una fonte specifica
	 */
	private boolean infoSpecifica;
	
	/**
	 * Indica la provenienza da una fonte generica
	 */
	private boolean infoGenerica;
	
	/**
	 * Indica se l'operatore front office ha provveduto a contattare l'utente 
	 */
	private boolean contattato;
	
	/**
	 * Indica se il candidato si è prenotato per la giornata di selezione indipendentemente dal format scelto
	 */
	private boolean prenotazioneColloquio;
	
	/**
	 * Indica se l'utente &egrave; in attesa di selezionare la data di selezione
	 */
	private boolean attesaDataSelezione;
	
	/**
	 * Indica se l'utente ha confermato la presenza alla selezione fisica
	 */
	private boolean presenzaSelezione;
	
	/**
	 * Indica se l'utente &egrave; in attesa dell'esisto della selezione
	 */
	private boolean attesaEsitoSelezione;
	
	/**
	 * Indica se l'utente &egrave; stato ammesso
	 */
	private boolean ammesso;
	
	/**
	 * Indica se l'utente ha inviato la scheda di iscrizione
	 */
	private boolean schedaIscrizioneInviata;
	
	/**
	 * Indica se la scheda d'iscrizione dell'utente &egrave; stata approvata
	 */
	private boolean schedaIscrizioneApprovata;
	
	/**
	 * Indica se l'utente &egrave; iscritto
	 */
	private boolean iscritto;
	
	/**
	 * Indica se il pagamento dell'utente relativo alla prima quietanza &egrave; stato inviato 
	 */
	private boolean quietanza1Inviata;
	
	/**
	 * Indica se il pagamento dell'utente relativo alla seconda quietanza &egrave; stato inviato
	 */
	private boolean quietanza2Inviata;
	
	/**
	 * Indica se il pagamento dell'utente relativo alla terza quietanza &egrave; stato inviato
	 */
	private boolean quietanza3Inviata;
	
	/**
	 * Indica se il pagamento dell'utente relativo alla prima quietanza &egrave; stato approvato 
	 */
	private boolean quietanza1Pagata;
	
	/**
	 * Indica se il pagamento dell'utente relativo alla seconda quietanza &egrave; stato approvato 
	 */
	private boolean quietanza2Pagata;
	
	/**
	 * Indica se il pagamento dell'utente relativo alla terza quietanza &egrave; stato approvato 
	 */
	private boolean quietanza3Pagata;
	
	/**
	 * Costruttore vuoto di StudenteBean
	 */
	public StatoBean() { }
	
	/**
	 * Costruttore di StudenteBean
	 * 
	 * @param utente utente del relativo stato
	 * @param edizione nome dell'edizione a cui è collegato lo stato
	 * @param master master relativo allo stato
	 * @param interessato interessato dello stato
	 * @param infoSpecifica provenienza da una fonte specifica
	 * @param infoGenerica provenienza da una fonte generica
	 * @param contattato contattato dello stato
	 * @param prenotazioneColloquio prenotazione al colloquio di selezione
	 * @param attesaDataSelezione attesa della data selezione dello stato
	 * @param presenzaSelezione conferma della presenza alla selezione dello stato
	 * @param attesaEsitoSelezione attesa dell'esito della selezione dello stato
	 * @param ammesso ammesso dello stato
	 * @param schedaIscrizioneInviata conferma dell'invio della scheda d'iscrizione dello stato
	 * @param schedaIscrizioneApprovata conferma dell'approvazione della scheda d'iscrizione dello stato
	 * @param iscritto iscritto dello stato
	 * @param quietanza1Inviata conferma dell'invio della prima quietanza dello stato
	 * @param quietanza2Inviata conferma dell'invio della seconda quietanza dello stato
	 * @param quietanza3Inviata conferma dell'invio della terza quietanza dello stato
	 * @param quietanza1Pagata conferma del pagamento della prima quietanza dello stato
	 * @param quietanza2Pagata conferma del pagamento della seconda quietanza dello stato
	 * @param quietanza3Pagata conferma del pagamento della terza quietanza dello stato
	 */
	public StatoBean(UtenteBean utente, String edizione, MasterBean master, boolean interessato, 
			boolean infoSpecifica, boolean infoGenerica, boolean contattato, boolean prenotazioneColloquio,
			boolean attesaDataSelezione, boolean presenzaSelezione, boolean attesaEsitoSelezione, 
			boolean ammesso, boolean schedaIscrizioneInviata,boolean schedaIscrizioneApprovata, boolean iscritto,
			boolean quietanza1Inviata, boolean quietanza2Inviata, boolean quietanza3Inviata, 
			boolean quietanza1Pagata, boolean quietanza2Pagata, boolean quietanza3Pagata) {
		this.utente = utente;
		this.edizione = edizione;
		this.master = master;
		this.interessato = interessato;
		this.infoSpecifica = infoSpecifica;
		this.infoGenerica = infoGenerica;
		this.contattato = contattato;
		this.prenotazioneColloquio = prenotazioneColloquio;
		this.attesaDataSelezione = attesaDataSelezione;
		this.presenzaSelezione = presenzaSelezione;
		this.attesaEsitoSelezione = attesaEsitoSelezione;
		this.ammesso = ammesso;
		this.schedaIscrizioneInviata = schedaIscrizioneInviata;
		this.schedaIscrizioneApprovata = schedaIscrizioneApprovata;
		this.iscritto = iscritto;
		this.quietanza1Inviata = quietanza1Inviata;
		this.quietanza2Inviata = quietanza2Inviata;
		this.quietanza3Inviata = quietanza3Inviata;
		this.quietanza1Pagata = quietanza1Pagata;
		this.quietanza2Pagata = quietanza2Pagata;
		this.quietanza3Pagata = quietanza3Pagata;
	}

	/**
	 * Restituisce l'utente a cui è associato lo stato
	 * 
	 * @return utente a cui è associato lo stato
	 */
	public UtenteBean getUtente() {
		return utente;
	}

	/**
	 * Setta un nuovo utente relativo allo stato
	 * 
	 * Pre: utente != null 
	 * @param utente nuovo utente relativo allo stato
	 */
	public void setUtente(UtenteBean utente) {
		this.utente = utente;
	}

	/**	
	 * Restituisce il nome dell'edizione a cui è legato lo stato
	 * 
	 * @return nome dell'edizione a cui è collegato lo stato
	 */
	public String getEdizione() {
		return edizione;
	}

	/**
	 * Setta una nuova edizione a cui è relativo lo stato
	 * 
	 * Pre: edizione != null
	 * @param edizione nuovo nome dell'edizione relativa allo stato
	 */
	public void setEdizione(String edizione) {
		this.edizione = edizione;
	}
	
	/**
	 * Restituisce il master relativo allo stato dell'utente
	 * 
	 * @return master master relativo allo stato dell'utente
	 */
	public MasterBean getMaster() {
		return master;
	}

	/**
	 * Setta un nuovo master per lo stato
	 * 
	 * Pre: master != null
	 * @param master nuovo master relativo allo stato
	 */
	public void setMaster(MasterBean master) {
		this.master = master;
	}

	/**
	 * Restituisce l'interessamento dell'utente
	 * 
	 * @return interessamento dell'utente
	 */
	public boolean getInteressato() {
		return interessato;
	}

	/**
	 * Setta l'interessamento dell'utente
	 * 
	 * Pre: interessato = 0 || interessato = 1
	 * @param interessato nuovo interessamento dell'utente
	 */
	public void setInteressato(boolean interessato) {
		this.interessato = interessato;
	}

	/**
	 * Restituisce se l'utente proviene da una fonte specifica
	 * 
	 * @return provenienza da una fonte specifica
	 */
	public boolean getInfoSpecifica() {
		return infoSpecifica;
	}
	
	/**
	 * Setta la provenienza di un utente alla fonte specifica
	 * 
	 * Pre: infoSpecifica = 0 || infoSpecifica = 1
	 * @param infoSpecifica nuova fonte specifica
	 */
	public void setInfoSpecifica(boolean infoSpecifica) {
		this.infoSpecifica = infoSpecifica;
	}
	
	/**
	 * Restituisce se l'utente proviene da una fonte specifica
	 * 
	 * @return provenienza da una fonte generica
	 */
	public boolean getInfoGenerica() {
		return infoGenerica;
	}
	
	/**
	 * Setta la provenienza di un utente alla fonte specifica
	 * 
	 * Pre: infoSpecifica = 0 || infoSpecifica = 1
	 * @param infoGenerica nuova fonte generica 
	 */
	public void setInfoGenerica(boolean infoGenerica) {
		this.infoGenerica = infoGenerica;
	}
	
	/**
	 * Restituisce l'esito del contatto tra l'operatore front office e l'utente
	 * 
	 * @return esito del contatto con l'utente
	 */
	public boolean getContattato() {
		return contattato;
	}
	
	/**
	 * Setta l'esito del contatto tra l'operatore front office e l'utente 
	 * 
	 * Pre: contattato = 1 || contattato = 0
	 * @param contattato nuovo esito del contatto tra front office ed utente
	 */
	public void setContattato(boolean contattato) {
		this.contattato = contattato;
	}

	/**	
	 * Restituisce la prenotazione del colloquio di un candidato
	 * 
	 * @return prenotazione del colloquio da parte di un candidato
	 */
	public boolean getPrenotazioneColloquio() {
		return prenotazioneColloquio;
	}

	/**
	 * Setta la prenotazione al colloquio da parte di un candidato
	 * 
	 * Pre: prenotazioneColloquio = 0 || prenotazioneColloquio = 1
	 * @param prenotazioneColloquio nuova prenotazione colloquio del candidato
	 */
	public void setPrenotazioneColloquio(boolean prenotazioneColloquio) {
		this.prenotazioneColloquio = prenotazioneColloquio;
	}

	/**
	 * Restituisce l'attesa della data della selezione di un utente
	 * 
	 * @return attesa della data di selezione
	 */
	public boolean getAttesaDataSelezione() {
		return attesaDataSelezione;
	}

	/**
	 * Setta l'attesa della data di selezione dell'utente
	 * 
	 * Pre: attesaDataSelezione = 0 || attesaDataSelezione = 1 
	 * @param attesaDataSelezione nuova attesa data di selezione dell'utente
	 */
	public void setAttesaDataSelezione(boolean attesaDataSelezione) {
		this.attesaDataSelezione = attesaDataSelezione;
	}

	/**
	 * Restituisce la presenza alla selezione fisica dell'utente
	 * 
	 * @return presenza alla selezione fisica dell'utente
	 */
	public boolean getPresenzaSelezione() {
		return presenzaSelezione;
	}

	/**
	 * Setta la presenza della selezione dell'utente
	 * 
	 * Pre: presenzaSelezione = 0 || presenzaSelezione = 1
	 * @param presenzaSelezione nuova presenza alla selezione dell'utente
	 */
	public void setPresenzaSelezione(boolean presenzaSelezione) {
		this.presenzaSelezione = presenzaSelezione;
	}

	/**
	 * Restituisce l'attesa dell'esito della selezione di un utente
	 * 
	 * @return attesa esito selezione dell'utente
	 */
	public boolean getAttesaEsitoSelezione() {
		return attesaEsitoSelezione;
	}

	/**
	 * Setta l'attesa esito selezione dell'utente
	 * 
	 * Pre: attesaEsitoSelezione = 0 || attesaEsitoSelezione = 1
	 * @param attesaEsitoSelezione nuova attesa esito selezione dell'utente
	 */
	public void setAttesaEsitoSelezione(boolean attesaEsitoSelezione) {
		this.attesaEsitoSelezione = attesaEsitoSelezione;
	}

	/**
	 * Restituisce l'ammesso dell'utente
	 * 
	 * @return ammesso dell'utente
	 */
	public boolean getAmmesso() {
		return ammesso;
	}

	/**
	 * Setta l'ammesso di un utente
	 * 
	 * Pre: ammesso = 1 || ammesso = 0
	 * @param ammesso nuovo ammesso del'utente
	 */
	public void setAmmesso(boolean ammesso) {
		this.ammesso = ammesso;
	}

	/**
	 * Restituisce l'invio della scheda d'iscrizione dell'utente
	 * 
	 * @return invio della scheda d'iscrizione 
	 */
	public boolean getSchedaIscrizioneInviata() {
		return schedaIscrizioneInviata;
	}

	/**
	 * Setta l'invio della scheda d'iscrizione dell'utente
	 * 
	 * Pre: schedaIscrizioneInviata = 1 || schedaIscrizioneInviata = 2
	 * @param schedaIscrizioneInviata nuovo invio scheda d'iscrizione dell'utente
	 */
	public void setSchedaIscrizioneInviata(boolean schedaIscrizioneInviata) {
		this.schedaIscrizioneInviata = schedaIscrizioneInviata;
	}

	/**
	 * Restituisce la conferma della scheda d'iscrizione dell'utente
	 * 
	 * @return approvazione della scheda d'iscrizione
	 */
	public boolean getSchedaIscrizioneApprovata() {
		return schedaIscrizioneApprovata;
	}
	
	/**
	 * Setta l'approvazione della scheda d'iscrizione dell'utente
	 * 
	 * Pre: schedaIscrizioneApprovata = 1 || schedaIscrizioneApprovata = 0
	 * @param schedaIscrizioneApprovata nuova approvazione scheda d'iscrizione dell'utente
	 */
	public void setSchedaIscrizioneApprovata(boolean schedaIscrizioneApprovata) {
		this.schedaIscrizioneApprovata = schedaIscrizioneApprovata;
	}
	
	/**
	 * Restituisce l'iscritto dell'utente
	 * 
	 * @return l'iscritto dell'utente
	 */
	public boolean getIscritto() {
		return iscritto;
	}

	/**
	 * Setta l'iscritto dell'utente
	 * 
	 * Pre: iscritto = 0 || iscritto = 1
	 * @param iscritto nuovo iscritto dell'utente
	 */
	public void setIscritto(boolean iscritto) {
		this.iscritto = iscritto;
	}

	/**
	 * Restituisce l'invio della prima quietanza dell'utente
	 * 
	 * @return invio della prima quietanza dell'utente
	 */
	public boolean getQuietanza1Inviata() {
		return quietanza1Inviata;
	}
	
	/**
	 * Setta l'invio della prima quietanza dell'utente
	 * 
	 * Pre: quietanza1Inviata = 1 || quietanza1Inviata = 0
	 * @param quietanza1Inviata nuovo invio della prima quietanza dell'utente
	 */
	public void setQuietanza1Inviata(boolean quietanza1Inviata) {
		this.quietanza1Inviata = quietanza1Inviata;
	}
	
	/**
	 * Restituisce l'invio della seconda quietanza dell'utente
	 * 
	 * @return invio della seconda quietanza dell'utente
	 */
	public boolean getQuietanza2Inviata() {
		return quietanza2Inviata;
	}
	
	/**
	 * Setta l'invio della seconda quietanza dell'utente
	 * 
	 * Pre: quietanza2Inviata = 1 || quietanza2Inviata = 0
	 * @param quietanza2Inviata nuovo invio della seconda quietanza dell'utente
	 */
	public void setQuietanza2Inviata(boolean quietanza2Inviata) {
		this.quietanza2Inviata = quietanza2Inviata;
	}
	
	/**
	 * Restituisce l'invio della terza quietanza dell'utente
	 * 
	 * @return invio della terza quietanza dell'utente
	 */
	public boolean getQuietanza3Inviata() {
		return quietanza3Inviata;
	}
	
	/**
	 * Setta l'invio della terza quietanza dell'utente
	 * 
	 * Pre: quietanza3Inviata = 1 || quietanza3Inviata = 0
	 * @param quietanza3Inviata nuovo invio della terza quietanza dell'utente
	 */
	public void setQuietanza3Inviata(boolean quietanza3Inviata) {
		this.quietanza3Inviata = quietanza3Inviata;
	}
	
	/**
	 * Restituisce il pagamento della prima quietanza dell'utente
	 * 
	 * @return il pagamento della prima quitanza dell'utente
	 */
	public boolean getQuietanza1Pagata() {
		return quietanza1Pagata;
	}
	
	/**
	 * Setta il pagamento della prima quietanza dell'utente
	 * 
	 * Pre: quietanza1Pagata = 0 || quietanza1Pagata = 1
	 * @param quietanza1Pagata nuovo pagamento della prima quietanza
	 */
	public void setQuietanza1Pagata(boolean quietanza1Pagata) {
		this.quietanza1Pagata = quietanza1Pagata;
	}

	/**
	 * Restituisce il pagamento della seconda quietanza dell'utente
	 *  
	 * @return il pagamento della seconda quietanza dell'utente
	 */
	public boolean getQuietanza2Pagata() {
		return quietanza2Pagata;
	}

	/**
	 * Setta il pagamento della seconda quietanza dell'utente
	 * 
	 * Pre: quietanza2Pagata = 0 || quietanza2Pagata = 1
	 * @param quietanza2Pagata nuovo pagamento della seconda quietanza
	 */
	public void setQuietanza2Pagata(boolean quietanza2Pagata) {
		this.quietanza2Pagata = quietanza2Pagata;
	}

	/**
	 * Restituisce il pagamento della terza quietanza dell'utente
	 * 
	 * @return il pagamento della terza quietanza dell'utente
	 */
	public boolean getQuietanza3Pagata() {
		return quietanza3Pagata;
	}

	/**
	 * Setta il pagamento della terza quietanza dell'utente
	 * 
	 * Pre: quietanza3Pagata = 0 || quietanza3Pagata = 1
	 * @param quietanza3Pagata nuovo pagamento della terza quietanza
	 */
	public void setQuietanza3Pagata(boolean quietanza3Pagata) {
		this.quietanza3Pagata = quietanza3Pagata;
	}

	@Override
	public String toString() {
		return "StatoBean [utente=" + utente + ", interessato=" + interessato + ", infoSpecifica=" + infoSpecifica
				+ ", infoGenerica=" + infoGenerica + ", contattato=" + contattato + ", prenotazioneColloquio="
				+ prenotazioneColloquio + ", attesaDataSelezione=" + attesaDataSelezione + ", presenzaSelezione="
				+ presenzaSelezione + ", attesaEsitoSelezione=" + attesaEsitoSelezione + ", ammesso=" + ammesso
				+ ", schedaIscrizioneInviata=" + schedaIscrizioneInviata + ", schedaIscrizioneApprovata="
				+ schedaIscrizioneApprovata + ", iscritto=" + iscritto + ", quietanza1Inviata=" + quietanza1Inviata
				+ ", quietanza2Inviata=" + quietanza2Inviata + ", quietanza3Inviata=" + quietanza3Inviata
				+ ", quietanza1Pagata=" + quietanza1Pagata + ", quietanza2Pagata=" + quietanza2Pagata
				+ ", quietanza3Pagata=" + quietanza3Pagata + "]";
	}
	
}
