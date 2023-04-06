package it.almalaborissuite.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.almalaborissuite.bean.ColloquioBean;

public interface ColloquioDAO extends GenericDAO <ColloquioBean, Integer> {

	/**
	 * Permette di ottenere tutti i colloqui che si svolgono in una data specifica
	 * 
	 * @param dataColloqui la data dei colloqui che vogliamo ottenere
	 * @return lista dei colloqui che si tengono nella data specificata
	 * @throws SQLException
	 */
	public Collection<ColloquioBean> getColloquiPerData(String dataColloqui) throws SQLException;
	
}
