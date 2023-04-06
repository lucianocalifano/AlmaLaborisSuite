package it.almalaborissuite.dao;

import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreContentBean;

public interface OperatoreContentDAO extends GenericDAO<OperatoreContentBean, String>{
	
	 public void insert(OperatoreContentBean utente, String pwd) throws SQLException;
}