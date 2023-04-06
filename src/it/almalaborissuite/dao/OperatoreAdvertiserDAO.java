package it.almalaborissuite.dao;


import java.sql.SQLException;

import it.almalaborissuite.bean.OperatoreAdvertiserBean;


public interface OperatoreAdvertiserDAO extends GenericDAO <OperatoreAdvertiserBean, String>  {
	
	 public void insert(OperatoreAdvertiserBean utente, String pwd) throws SQLException;

}
