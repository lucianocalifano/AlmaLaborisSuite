package it.almalaborissuite.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.almalaborissuite.bean.PartecipanteBean;

public interface PartecipanteDAO extends GenericDAO<PartecipanteBean, String> {
	
	/**
	 * Permette l'inserimento di un partecipante nel db
	 * 
	 * @param partecipante il partecipante da inserire nel db
	 * @param pw password relativa al partecipante da inserire
	 * @throws SQLException
	 */
	public void insert(PartecipanteBean partecipante, String pw) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti relativi ad una specifica edizione
	 * indipendentemente dal master a cui è associata l'edizione. (ESEMPIO: Tutti gli
	 * iscritti all'edizione NOVEMBRE 2020 di tutti i master partiti a novembre)
	 * 
	 * @param edizione nome dell'edizione di cui si vogliono recuperare tutti i partecipanti
	 * @return partecipanti relativi all'edizione specificata 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti relativi ad uno specifico master
	 * indipendentemente dall'edizione a cui esso è associato. (ESEMPIO: Tutti gli
	 * iscritti al master RISORSE UMANE di tutte le edizioni)
	 * 
	 * @param master master di cui si vogliono recuperare tutti i partecipanti
	 * @return partecipanti relativi all'edizione specificata 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerMaster(String master) throws SQLException;

	/**
	 * Permette di recuperare tutti i partecipanti relativi ad uno specifico master
	 * tenutosi in una specifica edizione. (ESEMPIO: Tutti gli iscritti al master 
	 * RISORSE UMANE dell'edizione NOVEMBRE2020)
	 *  
	 * @param edizione edizione di cui si vogliono recuperare tutti i partecipanti
	 * @param master master specifico di cui si vogliono recuperare tutti i partecipanti
	 * @return partecipanti iscritti all'edizione e al master specificato
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/**
	 * Permette di recuperare i partecipanti che presentano nel loro nome il nome specificato
	 * 
	 * @param nome nome del partecipante che ci interessa recuperare
	 * @return partecipanti che presentano nel loro nome il nome specificato
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerNome(String nome) throws SQLException;
	
	/**
	 * Permette di recuperare i partecipanti che presentano nel loro cognome il cognome specificato
	 * 
	 * @param cognome cognome del partecipante che ci interessa recuperare
	 * @return partecipanti che presentano nel loro cognome il cognome specificato
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerCognome(String cognome) throws SQLException;
	
	/**
	 * Permette di recuperare i partecipanti provenienti ad una fonte specificata
	 * 
	 * @param fonte fonte di provenienza dei partecipanti d'interesse
	 * @return partecipanti provenienti dalla fonte specificata
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerFonte(String fonte) throws SQLException;
	
	/**
	 * Permette di recuperare i partecipanti che hanno scelto un determinato format
	 * 
	 * @param format il format scelto dai partecipanti
	 * @return partecipanti lista dei partecipanti che hanno scelto il format specificato
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiPerFormat(String format) throws SQLException;
	
	/***********************  START QUIETANZA 1   ***********************************/
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni, per i quali risulta pagata 
	 * la prima quietanza
	 * 
	 * @return partecipanti che hanno pagato la prima quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza1Pagata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad un'edizione specifica,
	 * per i quali risulta approvato il pagamento della prima quietanza
	 * 
	 * @param edizione edizione alla quale risutano iscritti i partecipanti
	 * @return partecipanti che hanno pagato la prima quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza1PagataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di un edizione ed un master specifico,
	 * per i quali risulta approvato il pagamento della prima quietanza
	 * 
	 * @param edizione edizione alla quale risutano iscritti i partecipanti
	 * @master master master specifico di cui ci interessa reperire i partecipanti
	 * @return partecipanti che hanno pagato la prima quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza1PagataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/***********************  END QUIETANZA 1   ***********************************/
	
	/***********************  START QUIETANZA 2   ***********************************/
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali risulta inviata la seconda quietanza
	 * 
	 * @return partecipanti che hanno inviato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2Inviata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali risulta non inviata la seconda quietanza
	 * 
	 * @return partecipanti che non hanno inviato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonInviata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali risulta approvato il pagamento della seconda quietanza
	 * 
	 * @return partecipanti che hanno pagato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2Pagata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali non risulta approvato il pagamento della seconda quietanza
	 * 
	 * @return partecipanti che non hanno pagato la seconda quietanza 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonPagata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali risulta inviata la seconda quietanza
	 * 
	 * @param edizione edizione alla quale sono iscritti i partecipanti
	 * @return partecipanti che hanno inviato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2InviataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali risulta non inviata la seconda quietanza
	 * 
	 * @param edizione edizione alla quale sono iscritti i partecipanti
	 * @return partecipanti che non hanno inviato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonInviataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali risulta approvato il pagamento della seconda quietanza
	 * 
	 * @param edizione edizione alla quale sono iscritti i partecipanti
	 * @return partecipanti che hanno pagato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2PagataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali nessun operatore ha approvato il pagamento della seconda quietanza
	 * 
	 * @param edizione edizione alla quale risultano isciritti i partecipanti
	 * @return partecipanti che non hanno pagato la seconda quietanza 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonPagataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione ed a uno specifico master,
	 * per i quali risulta inviata la seconda quietanza
	 * 
	 * @param edizione edizione alla quale sono iscritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che hanno inviato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2InviataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione ed a uno specifico master,
	 * per i quali risulta non inviata la seconda quietanza
	 * 
	 * @param edizione edizione alla quale sono iscritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che non hanno inviato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonInviataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione ed a uno specifico master,
	 * per i quali risulta approvato il pagamento della seconda quietanza
	 * 
	 * @param edizione edizione alla quale sono iscritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che hanno pagato la seconda quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2PagataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali nessun operatore ha approvato il pagamento della seconda quietanza
	 * 
	 * @param edizione edizione alla quale risultano isciritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che non hanno pagato la seconda quietanza 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonPagataPerEdizioneMaster(String edizione, String master) throws SQLException;

	/***********************  END QUIETANZA 2   ***********************************/
	
	/***********************  START QUIETANZA 3   ***********************************/
	
	/**
	 * Permette di recuperare tutti i partecipanti per i quali risulta inviata la terza quietanza
	 * 
	 * @return partecipanti che hanno inviato la terza quietanza 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3Inviata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti per i quali risulta non inviata la terza quietanza
	 * 
	 * @return partecipanti che non hanno inviato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonInviata() throws SQLException;

	/**
	 * Permette di recuperare i partecipanti per i quali risulta approvato il pagamento della terza quietanza
	 * 
	 * @return partecipanti che hanno pagato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3Pagata() throws SQLException;
	
	/**
	 * Permette di recuperare i partecipanti per i quali non risulta pagata la terza quietanza, dunque
	 * tutti i partecipanti che non hanno inviato la quietanza o per i quali nessun operatore ha
	 * approvato il pagamento
	 * 
	 * @return partecipanti che non hanno pagato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonPagata() throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali risulta inviata la terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @return partecipanti che hanno inviato la terza quietanza 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3InviataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali risulta non inviata la terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @return partecipanti che non hanno inviato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonInviataPerEdizione(String edizione) throws SQLException;

	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali risulta approvato il pagamento della terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @return partecipanti che hanno pagato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3PagataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, iscritti ad una specifica edizione indipendentemente
	 * dal master di appartenenza, per i quali non risulta pagata la terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @return partecipanti che non hanno pagato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonPagataPerEdizione(String edizione) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali risulta inviata la terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che hanno inviato la terza quietanza 
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3InviataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali risulta non inviata la terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che non hanno inviato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonInviataPerEdizioneMaster(String edizione, String master) throws SQLException;

	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali risulta approvato il pagamento della terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @param master master al quale sono iscritti i partecipanti
	 * @return partecipanti che hanno pagato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3PagataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/**
	 * Permette di recuperare tutti i partecipanti, di tutte le edizioni e di tutti i master,
	 * per i quali non risulta pagata la terza quietanza
	 * 
	 * @param edizione edizione alla quale risultano iscritti i partecipanti
	 * @parma master master al quale sono iscritti i partecipanti
	 * @return partecipanti che non hanno pagato la terza quietanza
	 * @throws SQLException
	 */
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonPagataPerEdizioneMaster(String edizione, String master) throws SQLException;
	
	/***********************  END QUIETANZA 3   ***********************************/
	
}
