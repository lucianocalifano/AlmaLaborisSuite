package it.almalaborissuite.bean;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Un oggetto OperatoreDataEntryBean rappresenta un utente loggato al sistema AlmaLaborisSuite.
 * Un utente ha una email,un ruolo, un nome, un cognome, un numero di telefono, un indirizzo,
 * un cap, una città, una provincia, e una data nascita. 
 * Ogni campo &egrave; rappresentato con la propria variabile di istanza.
 * 
 * @author AlmaDevelopers
 */
public class OperatoreDataEntryBean extends UtenteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore vuoto di OperatoreDataEntryBean
	 */
	public OperatoreDataEntryBean() {
		super();
	}

	/**
	 * Costruttore di OperatoreDataEntryBean
	 * 
	 * @param email l'email dell'operatore
	 * @param ruolo il ruolo dell'operatore
	 * @param nome il nome dell'operatore
	 * @param cognome il cognome dell'operatore
	 * @param telefono il telefono dell'operatore
	 * @param indirizzo l'indirizzo dell'operatore
	 * @param cap il cap dell'operatore
	 * @param citta la citta dell'operatore
	 * @param provincia la provincia dell'operatore
	 * @param dataNascita la data di nascita dell'operatore
	 * @param note eventuali note dell'operatore
	 */
	public OperatoreDataEntryBean(String email, String ruolo, String nome, String cognome, String telefono, String indirizzo, 
			String cap, String citta, String provincia, LocalDateTime dataNascita, File note) {
		super(email, ruolo, nome, cognome, telefono, indirizzo, cap, citta, provincia, dataNascita, note);
	}

	@Override
	public String toString() {
		return "OperatoreDataEntryBean: "+super.toString();
	}
	
}
