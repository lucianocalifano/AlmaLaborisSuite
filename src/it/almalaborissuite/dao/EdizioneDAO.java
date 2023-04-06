package it.almalaborissuite.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.almalaborissuite.bean.EdizioneBean;

public interface EdizioneDAO extends GenericDAO<EdizioneBean, Integer> {
	 
	 /**
	   * Recupera la lista master relativi ad una specifica edizione
	   * @param edizione rappresenta un edizione di Alma Laboris
	   * @return una lista di master
	   * @throws SQLException
	   */
	 public Collection<EdizioneBean> getEdizioni() throws SQLException;
	 
	 /**
	  * Recupera le edizioni a partire dal nome dell'edizione stessa
	  * 
	  * @param nome il nome delle edizioni che si desiderano recuperare
	  * @return
	  */
	 public Collection<EdizioneBean> getEdizioniByName(String nome) throws SQLException;
	 
	 /**
	  * Permette di recuperare un edizione basandosi sul nome dell'edizione e di un master
	  * specificato
	  * 
	  * @param nomeEdizione nome dell'edizione che ci interessa
	  * @param nomeMaster nome del master che ci interessa
	  * @return edizione con nome edizione e nome master specificati
	  * @throws SQLException
	  */
	 public EdizioneBean getEdizioneByNomeEMaster(String nomeEdizione, String nomeMaster) throws SQLException;
}
