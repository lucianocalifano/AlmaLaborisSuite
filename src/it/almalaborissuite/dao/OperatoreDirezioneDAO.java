package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreDirezioneBean;

public interface OperatoreDirezioneDAO extends GenericDAO<OperatoreDirezioneBean, String>{
	
	public void insert(OperatoreDirezioneBean utente, String pwd) throws SQLException;
}