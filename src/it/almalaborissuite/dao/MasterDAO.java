package it.almalaborissuite.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.SettoreBean;

public interface MasterDAO extends GenericDAO<MasterBean, Integer> {
	
	/**
	   * Recupera la lista dei masters divisi per settore
	   * @return una lista di masters
	   * @throws SQLException
	   */ 
	public Collection<MasterBean> getMastersPerSettore(SettoreBean settore) throws SQLException;
	
	/**
	   * Recupera la lista dei masters divisi per edizione
	   * @throws SQLException
	   */ 
	public Collection<MasterBean> getMastersPerEdizione(String nomeEdizione) throws SQLException;
	
	/**
	   * Recupera la lista dei masters scelti da un singolo candidato
	   * @throws SQLException
	   */ 
	public Collection<MasterBean> getMastersPerCandidato(CandidatoBean candidato) throws SQLException;
	
	/**
	   * Restituisce il singolo Master di quel nome
	   * @throws SQLException
	   */ 
	public MasterBean getMasterPerNome(String nome) throws SQLException;
}
