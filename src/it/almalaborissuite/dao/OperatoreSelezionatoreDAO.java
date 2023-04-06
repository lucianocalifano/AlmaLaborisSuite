package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreSelezionatoreBean;
import it.almalaborissuite.bean.CandidatoBean;

public interface OperatoreSelezionatoreDAO extends GenericDAO<OperatoreSelezionatoreBean, String> {

	/**
	 * Permette l'inserimernto di un operatore selezionatore in piattaforma
	 * 
	 * @param operatore operatore Selezionatore da inserire come account in piattaforma 
	 * @param pwd passowrd relativa all'operatore da inserire
	 * @throws SQLException
	 */
	public void insert(OperatoreSelezionatoreBean operatore, String pwd) throws SQLException;
	
	/**
	 * Permette al selezionatore di attribuire un esito al processo di selezione per uno specifico
	 * candidato
	 * 
	 * @param candidato al quale si vuole attribuire l'esito della selezione
	 * @param esito esito della selezione
	 * @return restituisce true se l'aggiornamento dell'esisto della selezione è andato a buon fine, false altrimenti
	 * @throws SQLException
	 */
	public boolean setEsitoSelezione(CandidatoBean candidato, boolean esito) throws SQLException;
	
}
