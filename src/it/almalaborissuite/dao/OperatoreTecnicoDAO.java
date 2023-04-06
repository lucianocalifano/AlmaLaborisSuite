package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreTecnicoBean;

public interface OperatoreTecnicoDAO extends GenericDAO<OperatoreTecnicoBean, String>{
	
	/**
	 * Permette di aggiugnere un operatore tecnico nel database
	 * 
	 * @param operatore operatore tecnico da inserire nel database
	 * @param pwd password associata all'operatore da inserire
	 * @throws SQLException
	 */
	public void insert(OperatoreTecnicoBean operatore, String pwd) throws SQLException;
}
