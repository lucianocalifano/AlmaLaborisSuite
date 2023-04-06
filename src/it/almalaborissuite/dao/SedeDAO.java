package it.almalaborissuite.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.almalaborissuite.bean.SedeBean;

public interface SedeDAO extends GenericDAO<SedeBean, Integer>{
	
	/**
	 * Permette di recuperare una lista di sedi a seconda del nome
	 * 
	 * @param nome nome delle sedi che si desiderano recuperare
	 * @return sedi che hanno nel loro nome il nome specificato
	 * @throws SQLException
	 */
	public Collection<SedeBean> getSediPerNome(String nome) throws SQLException;
	
	/**
	 * Permette di recuperare una lista di sedi a seconda del riferimento
	 * 
	 * @param riferimento riferimento delle sedi che si desiderano recuperare
	 * @return sedi che hanno nel loro riferimento il riferimento specificato
	 * @throws SQLException
	 */
	public Collection<SedeBean> getSediPerRiferimento(String riferimento) throws SQLException;
	
	/**
	 * Permette di recuperare una lista di sedi a seconda dell'indirizzo
	 * 
	 * @param indirizzo indirizzo delle sedi che si desiderano recuperare
	 * @return sedi che hanno nel loro indirizzo l'indirizzo specificato
	 * @throws SQLException
	 */
	public Collection<SedeBean> getSediPerIndirizzo(String indirizzo) throws SQLException;
	
	/**
	 * Permette din recuperare una lista di sedi a seconda del cap
	 * 
	 * @param cap cap delle sedi che si desiderano recuperare
	 * @return sedi che hanno il cap specificato in comune
	 * @throws SQLException
	 */
	public Collection<SedeBean> getSediPerCap(String cap) throws SQLException;
	
	/**
	 * Permette di recuperare una lista di sedi a seconda della citta
	 * 
	 * @param citta citta delle sedi che si desiderano recuperare
	 * @return sedi che hanno la citta specificata in comune
	 * @throws SQLException
	 */
	public Collection<SedeBean> getSediPerCitta(String citta) throws SQLException;

}
