package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreDataEntryBean;

public interface OperatoreDataEntryDAO extends GenericDAO<OperatoreDataEntryBean, String>{
	
	public void insert(OperatoreDataEntryBean utente, String pwd) throws SQLException;
}