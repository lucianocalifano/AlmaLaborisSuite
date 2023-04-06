package it.almalaborissuite.facade;

import java.sql.SQLException;

import it.almalaborissuite.bean.AulaBean;
import it.almalaborissuite.bean.EdizioneBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.SedeBean;
import it.almalaborissuite.bean.SettoreBean;
import it.almalaborissuite.dao.AulaDAO;
import it.almalaborissuite.dao.EdizioneDAO;
import it.almalaborissuite.dao.MasterDAO;
import it.almalaborissuite.dao.SedeDAO;
import it.almalaborissuite.dao.SettoreDAO;
import it.almalaborissuite.dao.StatoDAO;
import it.almalaborissuite.impl.Aula;
import it.almalaborissuite.impl.Edizione;
import it.almalaborissuite.impl.Master;
import it.almalaborissuite.impl.Sede;
import it.almalaborissuite.impl.Settore;
import it.almalaborissuite.impl.Stato;

public class GestionePercorsoFormativoFacade {

	
  public boolean setCandidatoInteressato(String email, String edizione, boolean var) {
	    StatoDAO statoDao = new Stato();
	  //setto interessato uguale a var
	    try {
	      statoDao.setUtenteInteressato(email, edizione, var);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore di modifica stato");
	      return false;
	    }
	    return true;
	  }
  
  public boolean setCandidatoContattato(String email, String edizione) {
	    StatoDAO statoDao = new Stato();
	  //setto interessato uguale a var
	    try {
	      statoDao.setUtenteContattato(email, edizione, true);
	      statoDao.setUtenteAttesaDataSelezione(email, edizione, true);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore di modifica stato");
	      return false;
	    }
	    return true;
	  }
  
  public boolean setCandidatoAmmesso(String email, String edizione, String master, boolean var) {
	    StatoDAO statoDao = new Stato();
	    
	    //setto interessato uguale a var
	    try {
	      statoDao.setUtenteAmmesso(email, edizione, master, var);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore di modifica stato");
	      return false;
	    }
	    return true;
	  }
 
  
  public boolean setCandidatoSchedaIscrizioneApprovata(String email, String edizione, boolean var) {
	    StatoDAO statoDao = new Stato();
	    //setto interessato uguale a var
	    try {
	      statoDao.setUtenteSchedaIscrizioneApprovata(email, edizione, var);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore di modifica stato");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiungiAula(AulaBean aula) {
	    AulaDAO aulaDao = new Aula();
	    
	    //Inserisco l'aula
	    try {
	      aulaDao.insert(aula);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'aula");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiornaAula(AulaBean aula) {
	    AulaDAO aulaDao = new Aula();
	    
	    //Aggiorno l'aula
	    try {
	      aulaDao.update(aula);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'update aula");
	      return false;
	    }
	    return true;
	  }
  
  public boolean eliminaAula(AulaBean aula) {
	    AulaDAO aulaDao = new Aula();
	    
	    //Elimino l'aula
	    try {
	      aulaDao.delete(aula.getId());
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'update aula");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiungiEdizione(EdizioneBean edizione) {
	    EdizioneDAO edizioneDao = new Edizione();
	    
	    //Inserisco l'edizione
	    try {
	      edizioneDao.insert(edizione);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'edizione");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiornaEdizione(EdizioneBean edizione) {
	    EdizioneDAO edizioneDao = new Edizione();
	    
	    //Aggiorno l'edizione
	    try {
	      edizioneDao.update(edizione);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'eliminazione dell'edizione");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiungiMaster(MasterBean master) {
	    MasterDAO masterDao = new Master();
	    
	    //Inserisco il Master
	    try {
	      masterDao.insert(master);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento del Master");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiornaMaster(MasterBean master) {
	    MasterDAO masterDao = new Master();
	    
	    //Aggiorno il Master
	    try {
	      masterDao.update(master);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'aggiornamento del Master");
	      return false;
	    }
	    return true;
	  }
  
  public boolean eliminaMaster(MasterBean master) {
	    MasterDAO masterDao = new Master();
	    
	    //Elimino il Master
	    try {
	      masterDao.delete(master.getId());
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'eliminazione del Master");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiungiSede(SedeBean sede) {
	    SedeDAO sedeDao = new Sede();
	    
	    //Inserisco la Sede
	    try {
	      sedeDao.insert(sede);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento Sede");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiornaSede(SedeBean sede) {
	    SedeDAO sedeDao = new Sede();
	    
	    //Aggiorno la sede
	    try {
	      sedeDao.update(sede);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento Sede");
	      return false;
	    }
	    return true;
	  }
  
  public boolean eliminaSede(SedeBean sede) {
	    SedeDAO sedeDao = new Sede();
	    
	    //Elimino la sede
	    try {
	      sedeDao.delete(sede.getIdentificativo());
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'eliminazione della Sede");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiungiSettore(SettoreBean settore) {
	    SettoreDAO settoreDao = new Settore();
	    
	    //Inserisco Settore
	    try {
	      settoreDao.insert(settore);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento Settore");
	      return false;
	    }
	    return true;
	  }
  
  public boolean aggiornaSettore(SettoreBean settore) {
	    SettoreDAO settoreDao = new Settore();
	    
	    //Aggiorno Settore
	    try {
	      settoreDao.update(settore);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'aggiornamento Settore");
	      return false;
	    }
	    return true;
	  }
  
  public boolean eliminaSettore(SettoreBean settore) {
	    SettoreDAO settoreDao = new Settore();
	    
	    //Elimino Settore
	    try {
	      settoreDao.delete(settore.getIdentificativo());
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore nell'aggiornamento Settore");
	      return false;
	    }
	    return true;
	  }
  
}
