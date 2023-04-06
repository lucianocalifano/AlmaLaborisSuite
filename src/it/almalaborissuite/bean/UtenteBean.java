package it.almalaborissuite.bean;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Un oggetto UtenteBean rappresenta un utente loggato al sistema AlmaLaborisSuite.
 * Un utente ha una email, un ruolo, un nome, un cognome, un numero di telefono, un indirizzo,
 * un cap, una città, una provincia e una data di nascita. 
 * Ogni campo &egrave; rappresentato con la propria variabile di istanza.
 * 
 * @author AlmaDevelopers
 */
public class UtenteBean implements Serializable{

  private static final long serialVersionUID = 1L;
  
  /**
   * Rappresenta la email di un utente.
   */
  private String email;
  
  /**
   * Rappresenta il ruolo di un utente in piattaforma
   */
  private String ruolo;
  
  /**
   * Rappresenta il nome di un utente.
   */
  private String nome;
  
  /**
   * Rappresenta il cognome di un utente.
   */
  private String cognome;
  
  /**
   * Rappresenta il numero di telefono di un utente.
   */
  private String telefono;
  
  /**
   * Rappresenta l'indirizzo di un utente.
   */
  private String indirizzo;
  
  /**
   * Rappresenta il cap di un utente
   */
  private String cap;
  
  /**
   * Rappresenta la citta di un utente
   */
  private String citta;
  
  /**
   * Rappresenta la provincia di un utente
   */
  private String provincia;
  
  /**
   * Rappresenta la data di nascita di un utente.
   */
  private LocalDateTime dataNascita;
  
  /**
   * Rappresenta il campo note di un utente
   */
  private File note;

  /**
   * Costruttore vuoto di UtenteBean.
   */
  public UtenteBean() { }
  
  /**
   * Costruttore di UtenteBean.
   * 
   * @param email email dell'utente.
   * @param ruolo ruolo dell'utente.
   * @param nome nome dell'utente.
   * @param cognome cognome dell'utente.
   * @param telefono telefono dell'utente.
   * @param indirizzo indirizzo  dell'utente.
   * @param cap cap dell'utente.
   * @param citta citta dell'utente.
   * @param privincia provincia dell'utente.
   * @param dataNascita2 data di nascita dell'utente.
   * @param note campo note dell'utente
   */
  public UtenteBean(String email, String ruolo, String nome, String cognome, String telefono, String indirizzo, String cap, String citta, 
		  String provincia, LocalDateTime dataNascita, File note) {
    this.email = email;
    this.ruolo = ruolo;
    this.nome = nome;
    this.cognome = cognome;
    this.telefono = telefono;
    this.indirizzo = indirizzo;
    this.cap = cap;
    this.citta = citta;
    this.provincia = provincia;
    this.dataNascita = dataNascita;
    this.note = note;
  }

  /*
    |--------------------------------------------------------------------------
    | Interfaccia pubblica
    |--------------------------------------------------------------------------
   */

  /**
   * Restituisce la email dell'utente.
   * 
   * @return email email dell'utente.
   */
  public String getEmail() {
    return email;
  }
  
  /**
   * Setta una nuova email per utente.
   * 
   * Pre: email != null.
   * @param email nuova email per utente.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Restituisce il ruolo dell'utente
   * 
   * @return ruolo, il ruolo dell'utente
   */
  public String getRuolo() {
	  return ruolo;
  }
  
  /**
   * Setta un nuovo ruolo per l'utente
   * 
   * Pre: ruolo != null
   * @param ruolo, nuovo ruolo dell'utente
   */
  public void setRuolo(String ruolo) {
	  this.ruolo = ruolo;
  }
  
  /**
   * Restituisce il nome dell'utente.
   * 
   * @return nome nome dell'utente.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Setta un nuovo nome per l'utente.
   * 
   * Pre: nome != null.
   * @param nome nuovo nome per l'utente.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Restituisce il cognome dell'utente.
   * 
   * @return cognome cognome dell'utente.
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Setta un nuovo cognome per l'utente.
   * 
   * Pre: cognome != null.
   * @param cognome nuovo cognome dell'utente.
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Restituisce il numero di telefono dell'utente.
   * 
   * @return telefono numero di telefono dell'utente.
   */
  public String getTelefono() {
    return telefono;
  }

  /**
   * Setta un nuovo numero di telefono per l'utente.
   * 
   * Pre: telefono != null.
   * @param telefono nuovo numero di telefono dell'utente.
   */
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  /**
   * Restituisce l'indirizzo dell'utente.
   * 
   * @return indirizzo indirizzo dell'utente.
   */
  public String getIndirizzo() {
    return indirizzo;
  }

  /**
   * Setta un nuovo indirizzo per l'utente.
   * 
   * Pre: indirizzo != null.
   * @param indirizzo nuovo indirizzo per l'utente.
   */
  public void setIndirizzo(String indirizzo) {
    this.indirizzo = indirizzo;
  }
  
  /** email | pw | ruolo | nome	| cognome | telefono | indirizzo | cap | citta | provincia | data_nascita */ 
  
  /**
   * Restituisce il cap di un utente
   * 
   * @return cap, cap dell'utente
   */
  public String getCap() {
	  return cap;
  }
  
  /**
   * Setta un nuovo cap per l'utente
   * 
   * Pre: cap != null
   * @param cap, nuovo cap dell'utente
   */
  public void setCap(String cap) {
	  this.cap = cap;
  }
  
  /**
   * Restituisce la citta dell'utente
   * 
   * @return  citta, citta dell'utente
   */
  public String getCitta() {
	  return citta;
  }
  
  /**
   * Setta una nuova citta per l'utente
   * 
   * Pre: citta != null
   * @param citta, nuova citta dell'utente
   */
  public void setCitta(String citta) {
	 this.citta = citta; 
  }
  
  /**
   * Restituisce la provincia dell'utente
   * 
   * @return provincia, provincia dell'utente
   */
  public String getProvincia() {
	return provincia;  
  }
  
  /**
   * Setta una nuova provincia per l'utente
   * 
   * Pre: provincia != provincia
   */
  public void setProvincia(String provincia) {
	  this.provincia = provincia;
  }
  
  /**
   * Restituisce la data di nascita dell'utente.
   * 
   * @return dataNascita data di nascita dell'utente.
   */
  public LocalDateTime getDataNascita() {
    return dataNascita;
  }

  /**
   * Setta una nuova data di nascita per l'utente.
   * 
   * Pre: dataNascita != null.
   * @param dataNascita nuova data di nascita per l'utente.
   */
  public void setDataNascita(LocalDateTime dataNascita) {
    this.dataNascita = dataNascita;
  }
  
  /**
   * Restituisce il file Blob delle note dell'utente
   * 
   * @return note dell'utente
   */
  public File getNote() {
	return note;
  }

  /**
   * Permette di settare un nuovo file Note per l'utente
   * 
   * Pre: note != null
   * @param blob nuovo file blob per le note dell'utente
   */
  public void setNote(File blob) {
	this.note = blob;
  }

  @Override
  public String toString() {
	  if(note != null)
	  return "UtenteBean [email=" + email + ", ruolo=" + ruolo + ", nome=" + nome + ", cognome=" + cognome + ", telefono="
			+ telefono + ", indirizzo=" + indirizzo + ", cap=" + cap + ", citta=" + citta + ", provincia=" + provincia
			+ ", dataNascita=" + dataNascita + ", note="+note.toString()+"]";
	  else
		  return "UtenteBean [email=" + email + ", ruolo=" + ruolo + ", nome=" + nome + ", cognome=" + cognome + ", telefono="
			+ telefono + ", indirizzo=" + indirizzo + ", cap=" + cap + ", citta=" + citta + ", provincia=" + provincia
			+ ", dataNascita=" + dataNascita + ", note=null]";
  }

}