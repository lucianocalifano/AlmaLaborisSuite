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
import it.almalaborissuite.dao.UtenteDAO;
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
 * Definisce i metodi per gestire le funzionalit&agrave; relative all&rsquo;account.
 * 
 * @author OperatoreEntry
 */
public class GestioneAccountFacade {

  /**
   * Permette di prelevare informazioni relative ad un utente in fase di login.
   * PRE: email != null AND pwd != null
   * @param email l'email immessa dall'utente in fase di login 
   * @param pwd la password immessa dall'utente in fase di login
   * @return utente != null se le credenziali immesse in fase di login sono giuste, utente == null se l'email o la password sono errate
   */
  public UtenteBean login(String email, String pwd) {
    UtenteBean utente = null;
    UtenteDAO utenteDao = new Utente();

    //Cerca l'utente nel database...
    try {
      //Se le credenziali sono giuste restituisci utenteBean
      utente = utenteDao.checkLogin(email, pwd);

      //Se l'utente != null allora le credenziali sono giuste...
      if(utente != null) {
        //...quindi controllo il ruolo
        String ruolo = checkRuolo(utente);
        
        
        if(ruolo.equals("DATAENTRY")) {
        	//Recupera le informazioni dell'utente data entry dal db...
	        OperatoreDataEntryDAO operatoreDao = new OperatoreDataEntry();
	        OperatoreDataEntryBean operatore = operatoreDao.retrieveByKey(email);
	        utente = operatore;
        }
        
        if(ruolo.equals("FRONTOFFICE")) {
        	//Recupera le informazioni dell'utetne Front Office dal db...
        	OperatoreFrontOfficeDAO operatoreDao = new OperatoreFrontOffice();
            OperatoreFrontOfficeBean operatore = operatoreDao.retrieveByKey(email);
            utente = operatore;
        }
        
        if(ruolo.equals("SEGRETERIA")) {
            //Recupera le informazioni dell'utente Segreteria del db...
          	OperatoreSegreteriaDAO operatoreDao = new OperatoreSegreteria();
            OperatoreSegreteriaBean operatore = operatoreDao.retrieveByKey(email); 
            utente = operatore;
        }
        
        if(ruolo.equals("CONTENT")) {
        	//Recupera le informazioni dell'utente content dal db...
        	OperatoreContentDAO operatoreDao = new OperatoreContent();
	        OperatoreContentBean operatore = operatoreDao.retrieveByKey(email);
	        utente = operatore;
        }
        
        if(ruolo.equals("ADVERTISER")) {
        	//Recupera le informazioni dell'utente advertiser dal db...
	        OperatoreAdvertiserDAO operatoreDao = new OperatoreAdvertiser();
	        OperatoreAdvertiserBean operatore = operatoreDao.retrieveByKey(email);
	        utente = operatore;
        }
        
        if(ruolo.equals("TECNICO")) {
            //Recupera le informazioni dell'utente operatore tecnico dal db...
          	OperatoreTecnicoDAO operatoreDao = new OperatoreTecnico();
            OperatoreTecnicoBean operatore = operatoreDao.retrieveByKey(email);
            utente = operatore;
        } 
        
        if(ruolo.equals("DIREZIONE")) {
	        //Recupera le informazioni di tutor aziendale dal db...
	        OperatoreDirezioneDAO operatoreDao = new OperatoreDirezione();
	        OperatoreDirezioneBean operatore = operatoreDao.retrieveByKey(email);
	        utente = operatore;
        }
        
        if(ruolo.equals("SELEZIONATORE")) {
            //Recupera le informazioni dell'utente Selezionatore dal db...
          	OperatoreSelezionatoreDAO operatoreDao = new OperatoreSelezionatore();
            OperatoreSelezionatoreBean operatore = operatoreDao.retrieveByKey(email);
            utente = operatore;
        }
        
        if(ruolo.equals("CANDIDATO")) {
        	//Recupera le informazioni dell'utente candidato dal db...
            CandidatoDAO candidatoDao = new Candidato();
            CandidatoBean candidato = candidatoDao.retrieveByKey(email);
            utente = candidato;
        }
        
        if(ruolo.equals("PARTECIPANTE")) {
            //Recupera le informazioni di tutor universitario dal db...
          	PartecipanteDAO operatoreDao = new Partecipante();
            PartecipanteBean operatore = operatoreDao.retrieveByKey(email);
            utente = operatore;
        }
        
        return utente;
        
      } else
        //..altrimenti le credenziali sono errate e return null
        return null;
  
      } catch (SQLException e) {
      //...altrimenti c'&egrave; stato un errore di accesso al db e return null
      return null;
    }
 }

  /**
   * Permette di registrare un nuovo utente ad almalaborissuite.
   * @param utente l'utente da aggiungere da registrare
   * @param pwd la password dell'utente da registrare
   * @return true se la registrazione &egrave; andata a buon fine, false altrimenti
   */
  public boolean registrazione(UtenteBean utente, String pwd) {
	  
    try {
    	switch (utente.getRuolo()) {
    		
	    	case "CANDIDATO": //Registro un candidato ad AlmaLaborisSuite
	    					  CandidatoDAO candidatoDao = new Candidato();
	    					  candidatoDao.insert((CandidatoBean) utente, pwd);
	    					  break;
	    	case "PARTECIPANTE": //Registro un partecipante ad AlmaLaborisSuite
	    						 PartecipanteDAO partecipanteDao = new Partecipante();
	    						 partecipanteDao.insert((PartecipanteBean) utente, pwd);
	    						 break;
	    	case "DIREZIONE": //Registro un partecipante ad AlmaLaborisSuite
	    					  OperatoreDirezioneDAO operatoreDirDao = new OperatoreDirezione();
							  operatoreDirDao.insert((OperatoreDirezioneBean) utente, pwd);
							  break;
	    	case "DATAENTRY": //Registro un operatore DataEntry ad AlmaLaborisSuite
	    					  OperatoreDataEntryDAO operatoreDatDao = new OperatoreDataEntry();
							  operatoreDatDao.insert((OperatoreDataEntryBean) utente, pwd);
						 	  break;
	    	case "FRONTOFFICE": //Registro un operatore FrontOffice ad AlmaLaborisSuite
	    						OperatoreFrontOfficeDAO operatoreFroDao = new OperatoreFrontOffice();
								operatoreFroDao.insert((OperatoreFrontOfficeBean) utente, pwd);
								break;
	    	case "SEGRETERIA": //Registro un operatore Segreteria ad AlmaLaborisSuite
	    					   OperatoreSegreteriaDAO operatoreSegDao = new OperatoreSegreteria();
							   operatoreSegDao.insert((OperatoreSegreteriaBean) utente, pwd);
							   break;
	    	case "SELEZIONATORE": //Registro un operatore Selezionatore ad AlmaLaborisSuite
	    						  OperatoreSelezionatoreDAO operatoreSelDao = new OperatoreSelezionatore();
								  operatoreSelDao.insert((OperatoreSelezionatoreBean) utente, pwd);
								  break;
	    	case "TECNICO": //Registro un operatore Tecnico ad AlmaLaborisSuite
	    					OperatoreTecnicoDAO operatoreTecDao = new OperatoreTecnico();
	        				operatoreTecDao.insert((OperatoreTecnicoBean) utente, pwd);
	    					break;
	    	case "ADVERTISER": //Registro un operatore Advertiser ad AlmaLaborisSuite
	    					   OperatoreAdvertiserDAO operatoreAdvDao = new OperatoreAdvertiser();
	        				   operatoreAdvDao.insert((OperatoreAdvertiserBean) utente, pwd);
	        				   break;
	    	case "CONTENT": //Registro un operatore Content Creator ad AlmaLaborisSuite
	    					OperatoreContentDAO operatoreConDao = new OperatoreContent();
							operatoreConDao.insert((OperatoreContentBean) utente, pwd);
							break;
	    	default: return false;
    		
    	}
    
    } catch (SQLException e) {
      // ... errore nella registrazione
      return false;
    }

    return true;
  }

  /**
   * Permette di controllare il ruolo di un utente.
   * PRE: utente != null
   * @param utente l'utente di cui si vuole controllare il ruolo
   * @return il ruolo dell'utente come String, null se l'utente non esiste
   */
  public String checkRuolo(UtenteBean utente) {
    String email = utente.getEmail();
    String ruolo = null;
    UtenteDAO utenteDao = new Utente();

    //Controlla il ruolo dell'utente
    try {
      //Se l'utente esiste...
      if(utenteDao.retrieveByKey(email) != null) {

        //Controllo se l'utente &egrave; presidente
        OperatoreDataEntryDAO operatoreDatDao = new OperatoreDataEntry();
        if(operatoreDatDao.retrieveByKey(email) != null) {
          ruolo = "DATAENTRY";
        }
          
        //Controllo se l'utente &egrave; operatore front office
        OperatoreFrontOfficeDAO operatoreFroDao = new OperatoreFrontOffice();
        if(operatoreFroDao.retrieveByKey(email) != null) {
          ruolo = "FRONTOFFICE";
        }
        
        //Controllo se l'utente &egrave; operatore segreteria
        OperatoreSegreteriaDAO operatoreSegDao = new OperatoreSegreteria();
        if(operatoreSegDao.retrieveByKey(email) != null) {
          ruolo = "SEGRETERIA";
        }
        
        //Controllo se l'utente &egrave; operatore segreteria
        OperatoreContentDAO operatoreContDao = new OperatoreContent();
        if(operatoreContDao.retrieveByKey(email) != null) {
          ruolo = "CONTENT";
        }

        //Controllo se l'utente &egrave; segreteria
        OperatoreAdvertiserDAO operatoreAdvDao = new OperatoreAdvertiser();
        if(operatoreAdvDao.retrieveByKey(email) != null) {
          ruolo = "ADVERTISER";
        }
        
        //Controllo se l'utente &egrave; operatore tecnico
        OperatoreTecnicoDAO operatoreTecDao = new OperatoreTecnico();
        if(operatoreTecDao.retrieveByKey(email) != null) {
          ruolo = "TECNICO";
        }
        
        //Controllo se l'utente &egrave; operatore direzione
        OperatoreDirezioneDAO operatoreDirDao = new OperatoreDirezione();
        if(operatoreDirDao.retrieveByKey(email) != null) {
          ruolo = "DIREZIONE";
        }
        
        // Controllo se l'utente &egrave; operatore content 
        OperatoreSelezionatoreDAO operatoreSeleDao = new OperatoreSelezionatore();
        if(operatoreSeleDao.retrieveByKey(email) != null) {
          ruolo = "SELEZIONATORE";
        }
        
        //Controllo se l'utente &egrave; candidato
        CandidatoDAO candidatoDao = new Candidato();
        if(candidatoDao.retrieveByKey(email) != null) {
          ruolo = "CANDIDATO";
        }
        
        //Controllo se l'utente &egrave; partecipante
        PartecipanteDAO partecipanteDao = new Partecipante();
        if(partecipanteDao.retrieveByKey(email) != null) {
          ruolo = "PARTECIPANTE";
        }
        
        return ruolo;
      }else {
        //...altrimenti l'utente non esiste (return null)
        return null;
      }
    } catch (SQLException e) {
      //...altrimenti c'&egrave; stato un errore di accesso al db e return null
      return null;
    }
  }
  
  public boolean inserisciOperatoreAdvertiser(OperatoreAdvertiserBean operatoread, String pw) {
	    OperatoreAdvertiserDAO opDao = new OperatoreAdvertiser();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatoread,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreContent(OperatoreContentBean operatorec, String pw) {
	    OperatoreContentDAO opDao = new OperatoreContent();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatorec,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreDataEntry(OperatoreDataEntryBean operatoredata, String pw) {
	    OperatoreDataEntryDAO opDao = new OperatoreDataEntry();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatoredata,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreDirezione(OperatoreDirezioneBean operatoredirezione, String pw) {
	    OperatoreDirezioneDAO opDao = new OperatoreDirezione();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatoredirezione,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreFrontOffice(OperatoreFrontOfficeBean operatorefo, String pw) {
	    OperatoreFrontOfficeDAO opDao = new OperatoreFrontOffice();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatorefo,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreSegreteria(OperatoreSegreteriaBean operatoreseg, String pw) {
	    OperatoreSegreteriaDAO opDao = new OperatoreSegreteria();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatoreseg,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreSelezionatore(OperatoreSelezionatoreBean operatoresel, String pw) {
	    OperatoreSelezionatoreDAO opDao = new OperatoreSelezionatore();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatoresel,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciOperatoreTecnico(OperatoreTecnicoBean operatoretec, String pw) {
	    OperatoreTecnicoDAO opDao = new OperatoreTecnico();
	    
	    //Inserisco l'operatore
	    try {
	      opDao.insert(operatoretec,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }

  public boolean inserisciCandidato(CandidatoBean candidato, String pw) {
	    CandidatoDAO cDao = new Candidato();
	    
	    //Inserisco l'operatore
	    try {
	      cDao.insert(candidato,pw);
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("Errore d'inserimento dell'operatore");
	      return false;
	    }
	    return true;
	  }
  


//IN VALUTAZIONE - DA VALUTARE SE CON L'AGGIUNTA DELL'IDENTIFICATIVO AD OGNI BEAN DEL PROGETTO SERVONO FUNZIONI DI ASSOCIAZIONE DEI DATI

  /**
   * Permette di recuperare tutti i dati associati allo studente mancanti dal retrieveByKey (listaRegistri e listaRichieste)
   * @param studente lo studente di cui si vogliono recuperare i dati
   * @return lo studente con tutti i dati associati 
   */
  /*public UtenteBean getDatiOperatoreAdvertiser(OperatoreAdvertiser operatoread) {

    try {
      // prendi dati relativi all'utente
      OperatoreAdvertiserDAO operatoreadDao = new OperatoreAdvertiser();
      UtenteBean utentead= new UtenteBean();
      RichiestaTirocinioDAO ricDao = new RichiestaTirocinio();
      ArrayList<RichiestaTirocinioBean> richieste = (ArrayList<RichiestaTirocinioBean>) ricDao.getRichiestePerStudente(studente);

      // prendi stati delle richieste
      StatoRichiestaDAO statoRicDao = new StatoRichiesta();
      for (RichiestaTirocinioBean richiestaTirocinioBean : richieste) {
        ArrayList<StatoRichiestaBean> statiRichiesta = (ArrayList<StatoRichiestaBean>) statoRicDao.getStatiRichiesta(richiestaTirocinioBean);
        for (StatoRichiestaBean statoRichiesta : statiRichiesta) {
          statoRichiesta.setRichiestaId(richiestaTirocinioBean);
        }
      }
      studente.setRichiesteTirocinio(richieste);

      // prendi dati dei registri
      RegistroTirocinioDAO regDao = new RegistroTirocinio();
      ArrayList<RegistroTirocinioBean> registri;

      registri = (ArrayList<RegistroTirocinioBean>) regDao.getRegistriDiStudente(studente);
      for (RegistroTirocinioBean registroTirocinioBean : registri) {
        registroTirocinioBean = getDatiDiRegistro(registroTirocinioBean);
        registroTirocinioBean.setStudente(studente);

        //aggiungi il registro alla richiesta
        RichiestaTirocinioBean richiesta = studente.getRichiestaTirocinio(registroTirocinioBean.getRichiestaTirocinio().getId());
        if(richiesta !=null)
          richiesta.setRegistroTirocinio(registroTirocinioBean);
      }
      studente.setRegistriTirocinio(registri);


    } catch (SQLException e) {
      // c'&egrave; stato un errore nel reperimento dati dal db, restituisci lo studente con i dati recuperati fin'ora
      return studente;
    }
    return null;
  }*/


  /**
   * Permette di recuperare tutti i dati associati al tutor aziendale mancanti dal retrieveByKey (listaRegistriTirocinio)
   * @param tutor il tutor universitario del quale si vogliono recuperare i dati
   * @return il tutor universitario con tutti i dati associati
   */
  /*public TutorAziendaleBean getDatiDiTutorAziendale(TutorAziendaleBean tutor) {

    RegistroTirocinioDAO regDao = new RegistroTirocinio();
    ArrayList<RegistroTirocinioBean> registri;

    ReportDAO repoDao = new Report();
    ArrayList<ReportBean> reportFirmati = null;

    try {
      // prendi dati dei registri
      registri = (ArrayList<RegistroTirocinioBean>) regDao.getRegistriDiTutorAziendale(tutor);
      for (RegistroTirocinioBean registroTirocinioBean : registri) {
        registroTirocinioBean = getDatiDiRegistro(registroTirocinioBean);

        //prendi report firmati
        reportFirmati = (ArrayList<ReportBean>) repoDao.getReportFirmati(registroTirocinioBean);
        for (ReportBean reportBean : reportFirmati) {
          reportBean.setRegistroTirocinio(registroTirocinioBean);
          reportBean.setTutorAziendale(tutor);
        }
        tutor.setReports(reportFirmati);
      }
      tutor.setRegistriTirocinio(registri);


    } catch (SQLException e) {
      // c'&egrave; stato un errore nel reperimento dati dal db, restituisci il tutor aziendale con i dati recuperati fin'ora
      System.out.println(e.getMessage());
      return tutor;
    }
    return null;
  }*/


  /**
   * Permette di recuperare tutti i dati associati al tutor universitario mancanti dal retrieveByKey (listaRegistriTirocinio)
   * @param tutor il tutor universitario del quale si vogliono recuperare i dati
   * @return il tutor universitario con tutti i dati associati
   */
  /*public TutorUniversitarioBean getDatiDiTutorUniversitario(TutorUniversitarioBean tutor) {

    RegistroTirocinioDAO regDao = new RegistroTirocinio();
    ArrayList<RegistroTirocinioBean> registri;

    try {
      // prendi dati dei registri
      registri = (ArrayList<RegistroTirocinioBean>) regDao.getRegistriDiTutorUniversitario(tutor);
      for (RegistroTirocinioBean registroTirocinioBean : registri) {
        registroTirocinioBean = getDatiDiRegistro(registroTirocinioBean);
      }
      tutor.setRegistriTirocinio(registri);

    } catch (SQLException e) {
      // c'&egrave; stato un errore nel reperimento dati dal db, restituisci il tutor universitario con i dati recuperati fin'ora
      System.out.println(e.getMessage());
      return tutor;
    }
    return tutor;
  }*/


  /**
   * Permette di recuperare tutti i dati associati al registro di tirocinio mancanti dal retrieveByKey
   *  (richiestaTirocinio, listaReports, listaStatiTirocinio, questionarioAzienda, questionarioStudente)
   * @param registroTirocinioBean il registro del quale si vogliono recuperare i dati
   * @return il registro con tutti i dati associati
   * @throws SQLException
   */
  /*public RegistroTirocinioBean getDatiDiRegistro(RegistroTirocinioBean registroTirocinioBean) throws SQLException {

    ReportDAO repDao = new Report();
    StatoReportDAO sRepoDao = new StatoReport();

    StatoTirocinioDAO statoTirocinioDao = new StatoTirocinio();

    QuestionarioAziendaDAO qADao = new QuestionarioAzienda();
    QuestionarioStudenteDAO qSDao = new QuestionarioStudente();

    //recupera questionari
    registroTirocinioBean.setQuestionarioAzienda(qADao.retrieveByKey(registroTirocinioBean.getIdentificativo()));
    registroTirocinioBean.getQuestionarioAzienda().setRegistroTirocinio(registroTirocinioBean);
    registroTirocinioBean.setQuestionarioStudente(qSDao.retrieveByKey(registroTirocinioBean.getIdentificativo()));
    registroTirocinioBean.getQuestionarioStudente().setRegistroTirocinio(registroTirocinioBean);

    //recupera reports
    ArrayList<ReportBean> reports = (ArrayList<ReportBean>) repDao.getReportRegistro(registroTirocinioBean);
    for (ReportBean report : reports) {
      report.setRegistroTirocinio(registroTirocinioBean);
      //recupera stati reports
      ArrayList<StatoReportBean> statiReport = (ArrayList<StatoReportBean>) sRepoDao.getStatiReport(report);
      for (StatoReportBean statoBean : statiReport) {
        statoBean.setRegistroTirocinio(registroTirocinioBean);
        statoBean.setReport(report);
      }
      report.setStatiReport(statiReport);
    }
    registroTirocinioBean.setReports(reports);

    //recupera stati tirocinio
    ArrayList<StatoTirocinioBean> statiTirocinio = (ArrayList<StatoTirocinioBean>) statoTirocinioDao.getStatiTirocinio(registroTirocinioBean); 
    registroTirocinioBean.setStatiTirocinio(statiTirocinio);
    for (StatoTirocinioBean statoBean : statiTirocinio) {
      statoBean.setRegistroTirocinio(registroTirocinioBean);
    }
    return registroTirocinioBean;
  }*/
 
}
