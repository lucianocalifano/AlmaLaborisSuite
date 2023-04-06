package it.almalaborissuite.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.MasterBean;

public interface CandidatoDAO extends GenericDAO<CandidatoBean, String> {
	 
	/**
	 * Consente l'inserimento di un candidato nel db
	 * 
	 * @param candidato il candidato da aggiungere al db
	 * @param pw password relativa al candidato da aggiungere
	 * @throws SQLException
	 */
	 public void insert(CandidatoBean candidato, String pw) throws SQLException;
	 
	 /**
		 * Consente l'inserimento di un candidato nel db tramite Excel
		 * 
		 * @param candidato il candidato da aggiungere al db
		 * @param pw password relativa al candidato da aggiungere
		 * @throws SQLException
		 */
	 public void insertCandidatiFromExcel(String pathfile) throws FileNotFoundException, IOException;
	 
	 /**
		 * Consente di reperire un candidato con uno specifico Master di interesse
		 * 
		 * @param candidato il candidato da aggiungere al db
		 * @param pw password relativa al candidato da aggiungere
		 * @throws SQLException
		 */
	 public CandidatoBean retrieveByKey(CandidatoBean utente, MasterBean master) throws SQLException;
	
	 /**
	   * Recupera la lista dei candidati di Alma Laboris in base al Master scelto.
	   * @param master rappresenta il Master di Alma Laboris
	   * @return una lista di candidati per master
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiperMaster(MasterBean master) throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati di Alma Laboris in base alla fonte di provenienza
	   * @param fonte rappresenta la fonte di provenienza
	   * @return una lista di candidati per fonte
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiperFonte(String fonte) throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che hanno inviato la scheda di iscrizione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiSchedaIscrizioneInviata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che non hanno inviato la scheda di iscrizione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiSchedaIscrizioneNonInviata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati a cui è stata approvata la scheda di iscrizione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiSchedaIscrizioneApprovata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati a cui non è stata approvata la scheda di iscrizione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiSchedaIscrizioneNonApprovata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che hanno inviato la prima quietanza
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiQuietanza1Inviata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che non hanno inviato la prima quietanza
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiQuietanza1NonInviata() throws SQLException;
	
	  /**
	   * Recupera la lista dei candidati interessati ad Alma Laboris
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiInteressati() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati non interessati ad Alma Laboris
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonInteressati() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati contattati da Alma Laboris
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiContattati() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati non ancora contattati da Alma Laboris
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonContattati() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati in attesa della data di selezione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiAttesaDataSelezione() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati non in attesa della data di selezione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonAttesaDataSelezione() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che saranno presenti alle selezioni
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiPresenzaSelezione() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che non saranno presenti alle selezioni
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonPresenzaSelezione() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati in attesa dell'esito della selezione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiAttesaEsitoSelezione() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati non in attesa dell'esito della selezione
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonAttesaEsitoSelezione() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati ammessi alle selezioni
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiAmmessi() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati non ammessi alle selezioni
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonAmmessi() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che hanno pagato la prima quietanza
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiQuietanza1pagata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che non hanno pagato la prima quietanza
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiQuietanza1Nonpagata() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati che hanno scelto di seguire i master in aula oppure online
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiperFormat(String format) throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati iscritti ad Alma Laboris
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiIscritti() throws SQLException;
	  
	  /**
	   * Recupera la lista dei candidati non iscritti ad Alma Laboris
	   * @return una lista di candidati
	   * @throws SQLException
	   */
	  public Collection<CandidatoBean> getCandidatiNonIscritti() throws SQLException;
	  
	  
}
