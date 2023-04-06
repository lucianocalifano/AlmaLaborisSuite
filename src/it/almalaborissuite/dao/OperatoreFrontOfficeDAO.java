package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreFrontOfficeBean;

public interface OperatoreFrontOfficeDAO extends GenericDAO<OperatoreFrontOfficeBean, String>{
	
	/**
	 * Permette l'inserimento di un operatore Front Office in piattaforma.
	 * 
	 * @param operatore operatore Front Office da inserire in piattaforma 
	 * @param pwd password relativa all'operatore da inserire
	 * @throws SQLException
	 */
	public void insert(OperatoreFrontOfficeBean operatore, String pwd) throws SQLException;
	
}
