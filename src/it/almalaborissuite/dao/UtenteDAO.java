package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.UtenteBean;

public interface UtenteDAO extends GenericDAO<UtenteBean, String> {

  /**
   * Controlla che le credenziali inserite durante il login siano corrette e recupera le informazioni dell'utente.
   * @param email l'email inserita durante il login
   * @param pwd la password inserita durante il login
   * @return le informazioni dell'utente se le credenziali sono corrette, null altrimenti
   * @throws SQLException
   */
  public UtenteBean checkLogin(String email, String pwd) throws SQLException;

  /**
   * Inserisce un utente nel database.
   * @param utente le informazioni dell'utente da inserire
   * @param pwd la password dell'utente
   * @throws SQLException
   */
  public void insert(UtenteBean utente, String pwd) throws SQLException;
  
  /**
   * Consente di cambiare il ruolo di un utente, ad esempio consentire ad un operatore segreteria
   * di divenire operatore placement.
   * 
   * @param utente utente a cui vogliamo cambiare ruolo
   * @param newRuolo nuovo ruolo da assegnare all'utente specificato
   * @throws SQLException
   */
  public void changeRuoloOperatore(UtenteBean utente, String newRuolo) throws SQLException;
  
  /**
   * Consente di trasferire un candidato nella tabella partecipante quando diviene tale
   * 
   * @param candidato candidato che diverrà partecipante
   * @throws SQLException
   */
  public void changeCandidatoToPax(CandidatoBean candidato) throws SQLException;

}
