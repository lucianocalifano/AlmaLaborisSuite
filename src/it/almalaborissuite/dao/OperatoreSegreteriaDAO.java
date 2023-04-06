package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreSegreteriaBean;

public interface OperatoreSegreteriaDAO extends GenericDAO<OperatoreSegreteriaBean, String> {

	/**
	 * Permette di inserire un operatore segreteria con relativa password
	 * 
	 * @param operatore operatore segreteria da inserire come account nel db 
	 * @param pwd password relativa all'operatore da inserire
	 * @throws SQLException
	 */
	public void insert(OperatoreSegreteriaBean operatore, String pwd) throws SQLException;
	
}
