package it.almalaborissuite.facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.EdizioneBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.StatoBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.StatoDAO;
import it.almalaborissuite.impl.Stato;

/**
 * Definisce i metodi per gestire le funzionalit&agrave; relative ai report necessari agli utenti della piattaforma 
 * 
 * @author OperatoreEntry
 *
 */
public class GestioneReportFacade {

	/*  START - REPORT RELATIVI A STATO IMPL   */
	
	/**
	 * Permette di prelevare le informazioni relative allo stato di un utente
	 * 
	 * @param utente l'utente del quale vogliamo reperire lo stato
	 * @return stato dell'utente
	 */
	public StatoBean visualizzaStatoUtente(UtenteBean utente) {
		StatoDAO statoDao = new Stato();
		StatoBean stato = new StatoBean();
		
		try {
			stato = statoDao.retrieveByKey(utente.getEmail());
		} catch (SQLException e) {
			e.printStackTrace();
		    System.out.println("Errore nel reperimento dello stato dell'utente");
		    return null;
		}
				
		return stato;
	}
	
	/**
	 * Permette di reperire le informazioni relative allo stato di tutti gli utenti
	 * della piattaforma
	 * 
	 * @param order preferenza di ordinamento delle informazioni reperite
	 * @return
	 */
	public Collection<StatoBean> visualizzaStatoUtenti(String order) {
		StatoDAO statoDao = new Stato();
		Collection<StatoBean> listaStati = new ArrayList<StatoBean>();
		
		try {
			listaStati = statoDao.retrieveAll(order);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nel reperimento della lista degli stati ");
		}
		
		return listaStati;
	}
	
	/**
	 * Permette di reperire le informazioni relative allo stato dei partecipanti iscritti
	 * ad una sepcifica edizione
	 * 
	 * @param edizione l'edizione a cui sono iscritti i partecipanti di cui vogliamo reperire lo stato
	 * @return
	 */
	public Collection<StatoBean> visualizzaStatoUtentiPerEdizione(EdizioneBean edizione){
		StatoDAO statoDao = new Stato();
		Collection<StatoBean> listaStati = new ArrayList<StatoBean>();
		
		try {
			listaStati = statoDao.getStatoUtentiPerEdizione(edizione.getNome());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nel reperimento della lista degli stati");
		}
		
		return listaStati;
	}
	
	/**
	 * 
	 * @param master
	 * @return
	 */
	public Collection<StatoBean> visualizzaStatoUtentiPerMaster(MasterBean master){
		StatoDAO statoDao = new Stato();
		Collection<StatoBean> listaStati = new ArrayList<StatoBean>();
		
		try {
			listaStati = statoDao.getStatoUtentiPerMaster(master.getNome());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nel reperimento della lista degli stati");
		}
		
		return listaStati;
	}
	
	public Collection<StatoBean> visualizzaStatoUtentiPerEdizioneMaster(EdizioneBean edizione, MasterBean master){
		StatoDAO statoDao = new Stato();
		Collection<StatoBean> listaStati = new ArrayList<StatoBean>();
		
		try {
			listaStati = statoDao.getStatoUtentiPerEdizioneMaster(edizione.getNome(), master.getNome());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore nel reperimento della lista degli stati");
		}
		
		return listaStati;
	}
	
	/*  END - REPORT RELATIVI A STATO IMPL   */
	
	
}
