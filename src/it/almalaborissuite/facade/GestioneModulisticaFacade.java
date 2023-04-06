package it.almalaborissuite.facade;

import java.io.File;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.PartecipanteBean;
import it.almalaborissuite.bean.UtenteBean;



/**
 * Definisce i metodi per gestire le funzionalit&agrave; relative all' upload e download della modulistica.
 */
public class GestioneModulisticaFacade {

  /**
   * Permette di caricare sul server un progetto Formativo relativo ad un'azienda in fase di inserimento o modifica dell'azienda.
   * PRE: azienda != null AND relative != null
   * @param azienda azienda di cui caricare il file
   * @param relative path relativo dove salvare il file 
   * @return String path assoluto corretto in cui salvare il file 
   */
  public static String uploadCurriculumVitaeCandidato(CandidatoBean candidato, String relative) {
    String path=relative+"\\files\\curriculumCandidati";
    File dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }

    String fileName=candidato.getEmail();
    path=""+path+File.separator+fileName+".pdf";

    return path;
  }
  
  public static String uploadCurriculumVitaePartecipante(PartecipanteBean partecipante, String relative) {
	    String path=relative+"\\files\\curriculumPartecipanti";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }

	    String fileName=partecipante.getEmail();
	    path=""+path+File.separator+fileName+".pdf";

	    return path;
	  }


  /**
   * Permette di caricare sul server una Richiesta di Tirocinio relativa ad uno studente.
   * PRE: richiesta != null AND relative != null
   * @param richiesta richiesta di cui caricare il file
   * @param relative path relativo dove salvare il file 
   * @return String path assoluto corretto in cui salvare il file 
   */
  public static String uploadSchedaIscrizioneCandidato(CandidatoBean candidato, String relative) {
    String path=relative+"\\files\\SchedeIscrizioniCandidati";
    File dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }

    //da UTILIZZARE NEL CASO in cui si voglia mettere il file in una cartella specifica o crearla se non esiste
    /*//apro la ccrtella relativa allo studente specifico
    path=""+path+File.separator+richiesta.getStudente().getMatricola();

    dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }*/

    String fileName=candidato.getEmail();
    path=""+path+File.separator+fileName;

    return path;
  }
  
  public static String uploadSchedaIscrizionePartecipante(PartecipanteBean partecipante, String relative) {
	    String path=relative+"\\files\\SchedeIscrizioniPartecipanti";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=partecipante.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  }
  
  public static String uploadQuietanza1Candidato(CandidatoBean candidato, String relative) {
	    String path=relative+"\\files\\Quietanze1Candidati";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=candidato.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  }
  
  public static String uploadQuietanza1Partecipante(PartecipanteBean partecipante, String relative) {
	    String path=relative+"\\files\\Quietanze1Partecipanti";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=partecipante.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  } 
  
  public static String uploadQuietanza2Candidato(CandidatoBean candidato, String relative) {
	    String path=relative+"\\files\\Quietanze2Candidati";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=candidato.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  }
  
  public static String uploadQuietanza2Partecipante(PartecipanteBean partecipante, String relative) {
	    String path=relative+"\\files\\Quietanze2Partecipanti";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=partecipante.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  }
  
  public static String uploadQuietanza3Candidato(CandidatoBean candidato, String relative) {
	    String path=relative+"\\files\\Quietanze3Candidati";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=candidato.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  }
  	
  public static String uploadQuietanza3Partecipante(PartecipanteBean partecipante, String relative) {
	    String path=relative+"\\files\\Quietanze3Partecipanti";
	    File dir=new File(path);

	    if(!dir.exists()) {
	      //se la cartella non esiste la crea e poi procede
	      dir.mkdirs();
	    }
	    String fileName=partecipante.getEmail();
	    path=""+path+File.separator+fileName;

	    return path;
	  }

  
  /**
   * Permette di caricare sul server un Report relativo ad uno studente.
   * PRE: report != null AND report != null
   * @param report report di cui caricare il file
   * @param relative path relativo dove salvare il file 
   * @return String path assoluto corretto in cui salvare il file 
   */
  /*public static String uploadReport(ReportBean report, String relative) {

    String path=relative+"\\files\\studenti";
    File dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }

    //apro la ccrtella relativa allo studente specifico
    path=""+path+File.separator+report.getRegistroTirocinio().getStudente().getMatricola();

    dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }

    //dopo aver avuto accesso alla cartella dello studente deve creare, se non esiste, la cartella RegistroTirocinio
    path=""+path+File.separator+"RegistroTirocinio";
    dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }


    String fileName=""+report.getDataInserimento().toString()+".pdf";
    path=""+path+File.separator+fileName;

    return path;
  }*/


  /**
   * Permette di caricare sul server un'immagine relativa ad uno studente.
   * PRE: uetnte != null AND relative != null
   * @param utente Utente di cui si desidera caricare l'immagine.
   * @param relative path relativo dove salvare il file 
   * @return String path assoluto corretto in cui salvare il file 
   */
  public static String uploadImmagine(UtenteBean utente, String relative) {
    String path=relative+"\\files\\imgUtenti";
    File dir=new File(path);

    if(!dir.exists()) {
      //se la cartella non esiste la crea e poi procede
      dir.mkdirs();
    }

    String fileName=utente.getEmail()+".jpg"; 
    path=""+path+File.separator+fileName;

    return path;
  }


  /**
   * Permette di scaricare dal server un progetto formativo relativo ad un'azienda.
   * PRE: azienda != null AND relative != null
   * @param azienda azienda della quale si desidera scaricare il progetto formativo. 
   * @param relative path relativo dove recuperare il file. 
   * @return File Progetto formativo richiesto (se disponibile), null altrimenti.
   */
  public static File downloadCurriculumCandidato(CandidatoBean candidato, String relative) {
    String filePath=relative+"\\files\\CurriculumCandidati";
    String fileName=""+candidato.getEmail();
    filePath=filePath+File.separator+fileName;

    System.out.println("Downloading: "+filePath);

    File f=new File(filePath);

    if(!f.exists()) {
      return null;
    }else {
      return f;
    }

  }


  /**
   * Permette di scaricare dal server una Richiesta di tirocinio relativa ad uno studente.
   * PRE: richiesta != null AND relative != null
   * @param richiesta richiesta della quale si desidera scaricare il file. 
   * @param relative path relativo dove recuperare il file. 
   * @return File Richiesta di tirocinio richiesta (se disponibile), null altrimenti.
   */
  public static File downloadCurriculumPartecipante(PartecipanteBean partecipante, String relative) {
	  String filePath=relative+"\\files\\CurriculumPartecipanti";
	    String fileName=""+partecipante.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadSchedaIscrizioneCandidato(CandidatoBean candidato, String relative) {
	  String filePath=relative+"\\files\\SchedeIscrizioneCandidati";
	    String fileName=""+candidato.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadSchedaIscrizionePartecipante(PartecipanteBean partecipante, String relative) {
	  String filePath=relative+"\\files\\SchedeIscrizionePartecipanti";
	    String fileName=""+partecipante.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadQuietanza1Candidato(CandidatoBean candidato, String relative) {
	  String filePath=relative+"\\files\\Quietanze1Candidati";
	    String fileName=""+candidato.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadQuietanza1Partecipante(PartecipanteBean partecipante, String relative) {
	  String filePath=relative+"\\files\\Quietanze1Partecipanti";
	    String fileName=""+partecipante.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadQuietanza2Candidato(CandidatoBean candidato, String relative) {
	  String filePath=relative+"\\files\\Quietanze2Candidati";
	    String fileName=""+candidato.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadQuietanza2Partecipante(PartecipanteBean partecipante, String relative) {
	  String filePath=relative+"\\files\\Quietanze2Partecipanti";
	    String fileName=""+partecipante.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadQuietanza3Candidato(CandidatoBean candidato, String relative) {
	  String filePath=relative+"\\files\\Quietanze3Candidati";
	    String fileName=""+candidato.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }
  
  public static File downloadQuietanza3Partecipante(PartecipanteBean partecipante, String relative) {
	  String filePath=relative+"\\files\\Quietanze3Partecipanti";
	    String fileName=""+partecipante.getEmail();
	    filePath=filePath+File.separator+fileName;

	    System.out.println("Downloading: "+filePath);

	    File f=new File(filePath);

	    if(!f.exists()) {
	      return null;
	    }else {
	      return f;
	    }
  }


  /**
   * Permette di scaricare dal server un Report relativo ad uno studente.
   * PRE: azienda != null AND relative != null
   * @param report report del quale si desidera ottenere il file. 
   * @param relative path relativo dove recuperare il file. 
   * @return File Report richiesto (se disponibile), null altrimenti.
   */
  /*public static File downloadReport(ReportBean report, String relative) {
    String path=relative+"\\files\\studenti"+File.separator+report.getRegistroTirocinio().getStudente().getMatricola()+File.separator+"RegistroTirocinio";
    String fileName=""+report.getDataInserimento().toString()+".pdf";
    path=""+path+File.separator+fileName;
    System.out.println("Downloading: "+path);
    File f=new File(path);
    if(!f.exists()) {
      return null;
    }else {
      return f;
    }
  }


  private static String modificaEmail(String email) {
    email=email.replace(".", "_");
    email=email.replace("@","_");
    System.out.println(email);
    return email;
  } */

}
