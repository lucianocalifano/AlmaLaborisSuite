package it.almalaborissuite.facade;

import java.sql.SQLException;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.OperatoreAdvertiserBean;
import it.almalaborissuite.bean.OperatoreContentBean;
import it.almalaborissuite.bean.OperatoreDataEntryBean;
import it.almalaborissuite.bean.OperatoreDirezioneBean;
import it.almalaborissuite.bean.OperatoreFrontOfficeBean;
import it.almalaborissuite.bean.OperatoreSegreteriaBean;
import it.almalaborissuite.bean.OperatoreSelezionatoreBean;
import it.almalaborissuite.bean.OperatoreTecnicoBean;
import it.almalaborissuite.bean.PartecipanteBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.CandidatoDAO;
import it.almalaborissuite.dao.OperatoreAdvertiserDAO;
import it.almalaborissuite.dao.OperatoreContentDAO;
import it.almalaborissuite.dao.OperatoreDataEntryDAO;
import it.almalaborissuite.dao.OperatoreDirezioneDAO;
import it.almalaborissuite.dao.OperatoreFrontOfficeDAO;
import it.almalaborissuite.dao.OperatoreSegreteriaDAO;
import it.almalaborissuite.dao.OperatoreSelezionatoreDAO;
import it.almalaborissuite.dao.OperatoreTecnicoDAO;
import it.almalaborissuite.dao.PartecipanteDAO;
import it.almalaborissuite.impl.Candidato;
import it.almalaborissuite.impl.OperatoreAdvertiser;
import it.almalaborissuite.impl.OperatoreContent;
import it.almalaborissuite.impl.OperatoreDataEntry;
import it.almalaborissuite.impl.OperatoreDirezione;
import it.almalaborissuite.impl.OperatoreFrontOffice;
import it.almalaborissuite.impl.OperatoreSegreteria;
import it.almalaborissuite.impl.OperatoreSelezionatore;
import it.almalaborissuite.impl.OperatoreTecnico;
import it.almalaborissuite.impl.Partecipante;
import it.almalaborissuite.impl.Utente;

/**
 * Definisce i metodi per gestire le funzionalit&agrave; relative all&rsquo;area personale.
 */
public class GestioneAreaPersonaleFacade {

  /**
   * Permette ad un utente generico di modificare il proprio profilo.
   * @param utente utente da modificare
   * @return boolean true se la modifica &egrave; avvenuta, false altrimenti 
   * */
  public static boolean modificaProfilo(UtenteBean utente) {
    Utente ut=new Utente();
    try {
      ut.update(utente);
      return true;
    } catch (SQLException e) {
      return false;
    }	
  }

  /**
   * Permette ad uno studente di modificare il proprio profilo.
   * @param studente studente da modificare
   * @return boolean true se la modifica &egrave; avvenuta, false altrimenti 
   * */
  public static boolean modificaProfiloCandidato(CandidatoBean candidato) {
    Candidato cand=new Candidato();
    Utente ut=new Utente();
    try {
      cand.update(candidato);
      ut.update(candidato);
      return true;
    } catch (SQLException e) {
      return false;
    }	
  }
  
  public static boolean modificaProfiloPartecipante(PartecipanteBean partecipante) {
	    Partecipante part=new Partecipante();
	    Utente ut=new Utente();
	    try {
	      part.update(partecipante);
	      ut.update(partecipante);
	      return true;
	    } catch (SQLException e) {
	      return false;
	    }	
	  }
  public CandidatoBean GetInfoCandidato(String email)
  {
	  CandidatoBean candidato= new CandidatoBean();
	  CandidatoDAO cand= new Candidato();
	  try {candidato = cand.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return candidato;
  }
  
  public OperatoreAdvertiserBean GetInfoOperatoreAdvertiser(String email)
  {
	  OperatoreAdvertiserBean operatoread= new OperatoreAdvertiserBean();
	  OperatoreAdvertiserDAO op= new OperatoreAdvertiser();
	  try {operatoread = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatoread;
  }
  
  public OperatoreContentBean GetInfoOperatoreContent(String email)
  {
	  OperatoreContentBean operatorec= new OperatoreContentBean();
	  OperatoreContentDAO op= new OperatoreContent();
	  try {operatorec = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatorec;
  }
  
  public OperatoreDataEntryBean GetInfoOperatoreDataEntry(String email)
  {
	  OperatoreDataEntryBean operatoredata= new OperatoreDataEntryBean();
	  OperatoreDataEntryDAO op= new OperatoreDataEntry();
	  try {operatoredata = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatoredata;
  }
  
  public OperatoreDirezioneBean GetInfoOperatoreDirezione(String email)
  {
	  OperatoreDirezioneBean operatoredirezione= new OperatoreDirezioneBean();
	  OperatoreDirezioneDAO op= new OperatoreDirezione();
	  try {operatoredirezione = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatoredirezione;
  }
  
  public OperatoreFrontOfficeBean GetInfoOperatoreFrontOffice(String email)
  {
	  OperatoreFrontOfficeBean operatorefront= new OperatoreFrontOfficeBean();
	  OperatoreFrontOfficeDAO op= new OperatoreFrontOffice();
	  try {operatorefront = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatorefront;
  }
  
  public OperatoreSegreteriaBean GetInfoOperatoreSegreteria(String email)
  {
	  OperatoreSegreteriaBean operatoreseg= new OperatoreSegreteriaBean();
	  OperatoreSegreteriaDAO op= new OperatoreSegreteria();
	  try {operatoreseg = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatoreseg;
  }
  
  public OperatoreSelezionatoreBean GetInfoOperatoreSelezionatore(String email)
  {
	  OperatoreSelezionatoreBean operatoresel= new OperatoreSelezionatoreBean();
	  OperatoreSelezionatoreDAO op= new OperatoreSelezionatore();
	  try {operatoresel = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatoresel;
  }
  
  public OperatoreTecnicoBean GetInfoOperatoreTecnico(String email)
  {
	  OperatoreTecnicoBean operatoretec= new OperatoreTecnicoBean();
	  OperatoreTecnicoDAO op= new OperatoreTecnico();
	  try {operatoretec = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return operatoretec;
  }
  
  public PartecipanteBean GetInfoPartecipante(String email)
  {
	  PartecipanteBean partecipante= new PartecipanteBean();
	  PartecipanteDAO op= new Partecipante();
	  try {partecipante = op.retrieveByKey(email);} 
	  
	  catch (SQLException e) 
	  {return null;}	
	  
	  return partecipante;
  }
 
  

}
