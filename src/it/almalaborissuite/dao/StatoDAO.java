package it.almalaborissuite.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.StatoBean;

public interface StatoDAO extends GenericDAO<StatoBean, String>{

	/**
	 * Permette di restituire lo stato di uno specifico utente inerente ad una specifica
	 * edizione
	 * 
	 * @param emailUtente email dell'utente di cui ci interessa recuperare lo stato
	 * @param edizione nome dell'edizione a cui si riferisce lo stato
	 * @param master master di riferimento dello stato
	 * @return stato dell'utente specificato relativo all'edizione specificata
	 * @throws SQLException
	 */
	public StatoBean retrieveByKey(String emailUtente, String nomeEdizione, MasterBean master) throws SQLException;
	
	/**
	 * Permette di controllare se un utente &egrave; interessato
	 * 
	 * @param utente l'utente di cui ci interessa controllare l'interesse
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return interesse dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteInteressato(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di controllare se un utente rientra nella categoria info specifica
	 * 
	 * @param utente l'utente d'interesse
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return info specifica dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteInfoSpecifica(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di controllare se un utente rientra nella categoria info generica
	 * 
	 * @param utente l'utente d'interesse
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return info generica dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteInfoGenerica(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di controllare se un utente è stato contattato o meno
	 * 
	 * @param utente l'utente di cui ci interessa controllare se è stato contattato
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return lo stato contattato dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteContattato(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di controllare se un utente ha effettuato la prenotazione al colloquio
	 * 
	 * @param utente l'utente di cui ci interessa controllare se si è prenotato per il colloquio
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return lo stato della prenotazione al colloquio dell'utente specificato
	 * @throws SQLException
	 */
	public boolean checkUtentePrenotazioneColloquio(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente è in attesa della data di selezione
	 * 
	 * @param utente l'utente di cui ci interessa controllare se è in stato di attesa della data per la selezione
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return lo stato attesa data selezione dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteAttesaDataSelezione(String emailUtente, String edizione) throws SQLException;

	/**
	 * Permette di controllare se l'utente ha confermato la presenza alla selezione 
	 * 
	 * @param utente l'utente di cui ci interessa controllare la conferma alla selezione
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return conferma della presenza alla selezione dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtentePresenzaSelezione(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente è in attesa dell'esito della selezione
	 * 
	 * @param utente l'utente di cui ci interessa controllare se è in attesa dell'esito della selezione
	 * @param edizione edizione di cui ci interessa controllare l'interesse
	 * @return stato di attesa esito selezione dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteAttesaEsitoSelezione(String emailUtente, String edizione) throws SQLException;	
	
	/**
	 * Permette di controllare se l'utente è stato ammesso
	 * 
	 * @param utente l'utente di cui ci interessa controllare se è stato ammesso
	 * @return stato ammesso dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteAmmesso(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente ha inviato la scheda d'iscrizione 
	 * 
	 * @param utente l'utente di cui ci interessa controllare se ha inviato la scheda d'iscrizione o meno
	 * @return stato dell'invio della scheda d'iscrizione dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteSchedaIscrizioneInviata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se la scheda d'iscrizione dell'utente è stata approvata
	 * 
	 * @param utente utente proprietario della scheda d'iscrizione
	 * @return stato dell'approvazione della scheda d'iscrizione dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteSchedaIscrizioneApprovata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente è iscritto
	 * 
	 * @param utente l'utente di cui ci interessa controllare se è iscritto
	 * @return stato dell'iscrizione dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteIscritto(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente ha inviato la prima quietanza
	 * 
	 * @param utente l'utente di cui ci interessa controllare se ha inviato la prima quietanza
	 * @return stato di invio della prima quietanza dell'utente
	 * @throws SQLException
	 */
	public boolean checkUtenteQuietanza1Inviata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente ha inviato la seconda quietanza
	 * 
	 * @param utente l'utente di cui ci interessa controllare se ha inviato la seconda quietanza
	 * @return stato di invio della seconda quietanza
	 * @throws SQLException
	 */
	public boolean checkUtenteQuietanza2Inviata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se l'utente ha inviato la terza quietanza
	 * 
	 * @param utente l'utente di cuin ci interessa controllare se ha inviato la terza quietanza 
	 * @return stato di invio della terza quietanza
	 * @throws SQLException
	 */
	public boolean checkUtenteQuietanza3Inviata(String emailUtente) throws SQLException;

	/**
	 * Permette di controllare se la prima quietanza dell'utente è stata approvata
	 * 
	 * @param utente l'utente di cui ci interessa controllare lo stato di approvazione della prima quietanza
	 * @return stato di approvazione della prima quietanza
	 * @throws SQLException
	 */
	public boolean checkUtenteQuietanza1Pagata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se la seconda quietanza dell'utente è stata approvata
	 * 
	 * @param utente l'utente di cui ci interessa controllare lo stato di approvazione della seconda quietanza
	 * @return stato di approvazione della seconda quietanza
	 * @throws SQLException
	 */
	public boolean checkUtenteQuietanza2Pagata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di controllare se la terza quietanza dell'utente è stata approvata
	 * 
	 * @param utente l'utente di cui ci interessa controllare lo stato di approvazione della prima quietanza
	 * @return stato di approvazione della terza quietanza
	 * @throws SQLException
	 */
	public boolean checkUtenteQuietanza3Pagata(String emailUtente) throws SQLException;
	
	/**
	 * Permette di settare l'interessamento dell'utente 
	 *  
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param interesse nuovo valore dello stato interessato
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteInteressato(String emailUtente, String edizione, boolean interesse) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 *  
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param infoSpec nuovo valore della info specifica
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteInfoSpecifica(String emailUtente, String edizione, boolean infoSpec) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param infoGen nuovo valore della info generica
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteInfoGenerica(String emailUtente, String edizione, boolean infoGen) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param contattato nuovo valore dello stato contattato
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteContattato(String emailUtente, String edizione, boolean contattato) throws SQLException;
	
	/**
	 * Permette di settare la prenotazione al colloquio dell'utente specificato
	 * 
	 * @param utente l'utente a cui si vuole aggiornare lo stato di prenotazione del colloquio
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param nomeMaster master d'interesse per lo stato
	 * @param prenotazione il nuovo stato relativo alla prenotazione del colloquio
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtentePrenotazioneColloquio(String emailUtente, String edizione, String nomeMaster, boolean prenotazione) throws SQLException;

	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param attesa nuovo valore dello stato attesa data selezione
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteAttesaDataSelezione(String emailUtente, String edizione, boolean attesa) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param presenza nuovo valore dello stato presenza selezione
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtentePresenzaSelezione(String emailUtente, String edizione, boolean presenza) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 *  
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param attesa nuovo valore dello stato atteda esito selezione
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteAttesaEsitoSelezione(String emailUtente, String edizione, boolean attesa) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione edizione a cui risulterà iscritto l'utente quando diverrà partecipante
	 * @param master master d'interesse per lo stato
	 * @param ammesso nuovo valore dello stato ammesso
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteAmmesso(String emailUtente, String edizione, String nomeMaster, boolean ammesso) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param schedaInviata nuovo valore dello stato scheda inviata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteSchedaIscrizioneInviata(String emailUtente, String edizione, boolean schedaInviata) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param schedaApprovata nuovo valore dello stato scheda approvata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteSchedaIscrizioneApprovata(String emailUtente, String edizione, boolean schedaApprovata) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param iscritto nuovo valore dello stato iscritto
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteIscritto(String emailUtente, String edizione, boolean iscritto) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param quietanza nuovo valore dello stato quietanza1 inviata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteQuietanza1Inviata(String emailUtente, String edizione, boolean quietanza) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param quietanza nuovo valore dello stato quietanza2 inviata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteQuietanza2Inviata(String emailUtente, String edizione, boolean quietanza) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param quietanza nuovo valore dello stato quietanza3 inviata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteQuietanza3Inviata(String emailUtente, String edizione, boolean quietanza) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param quietanza nuovo valore dello stato quietanza1 pagata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteQuietanza1Pagata(String emailUtente, String edizione, boolean quietanza) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param quietanza nuovo valore dello stato quietanza2 pagata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteQuietanza2Pagata(String emailUtente, String edizione, boolean quietanza) throws SQLException;
	
	/**
	 * Permette di settare l'info specifica dell'utente 
	 * 
	 * @param utente l'utente a cui vogliamo settare lo stato
	 * @param edizione l'edizione d'interesse per lo stato
	 * @param quietanza nuovo valore dello stato quietanza3 pagata
	 * @return true se l'aggiornamento dello stato va a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setUtenteQuietanza3Pagata(String emailUtente, String edizione, boolean quietanza) throws SQLException;
	
	/**
	 * Permette di recuperare tutti gli stati associati ad un utente
	 * 
	 * @param emailUtente email dell'utente di cui ci interessa recuperare gli stati
	 * @param edizione nome dell'edizione di cui ci interessano gli stati
	 * @return lista degli stati appartenenti agli utenti
	 * @throws SQLException
	 */
	public Collection<StatoBean> getStatiPerUtenteEdizione(String emailUtente, String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare lo stato degli utenti iscritti ad una sepcifica edizione, indipendentemente
	 * dal master d'interesse.
	 * 
	 * @param nomeEdizione l'edizione della quale vogliamo ottenere lo stato degli utenti
	 * @return lista degli stati degli utenti iscritti all'edizione specificata
	 * @throws SQLException
	 */
	public Collection<StatoBean> getStatoUtentiPerEdizione(String nomeEdizione) throws SQLException;
	
	/**
	 * Permette di recuperare lo stato degli utenti che hanno sostenuto uno specifico master,
	 * indipendentemente dall'edizione in cui essi si svolgono o si sono svolti
	 * 
	 * @param nomeMaster il master di cui ci interessa recuperare lo stato degli utenti
	 * @return lista degli stati relativi agli utenti che sostengono il master specificato
	 * @throws SQLException
	 */
	public Collection<StatoBean> getStatoUtentiPerMaster(String nomeMaster) throws SQLException;
	
	/**
	 * Permette di recuperare lo stato degli utenti che sono iscritti ad una specifica edizione e
	 * che sostengono uno specifico master
	 * 
	 * @param nomeEdizione nome dell'edizione alla quale risulta iscritto l'utente
	 * @param nomeMaster nome del master che l'utente ha scelto di sostenere
	 * @return lista degli stati degli utenti
	 * @throws SQLException
	 */
	public Collection<StatoBean> getStatoUtentiPerEdizioneMaster(String nomeEdizione, String nomeMaster) throws SQLException;
	
}
