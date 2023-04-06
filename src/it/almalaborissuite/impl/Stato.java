package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.StatoBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.MasterDAO;
import it.almalaborissuite.dao.StatoDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Stato implements StatoDAO{
	
	@Override
	public StatoBean retrieveByKey(String emailUtente, String nomeEdizione, MasterBean master) throws SQLException {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			String query = "SELECT * FROM "+Dizionario.TABLE_STATO+" "
					     + "WHERE email = ? AND edizione = ? AND id_master = ?;";
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, emailUtente);
			ps.setString(2, nomeEdizione);
			ps.setInt(3, master.getId());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {				
			    StatoBean stato = new StatoBean();
				
			    //Recupero l'utente proprietario dello stato
			    UtenteDAO utenteDao = new Utente();
				UtenteBean utente = utenteDao.retrieveByKey(rs.getString("email"));
				
				//Recupero il master relativo allo stato
				MasterDAO masterDao = new Master();
				
				//Setto i dati dello stato
				stato.setUtente(utente);
				stato.setEdizione(rs.getString("edizione"));
				stato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				return stato;
			} else
				return null;
		}finally {
			try {
				if(!con.isClosed())
					con.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	@Override
	public StatoBean retrieveByKey(String key) throws SQLException {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			String query = "SELECT * FROM "+Dizionario.TABLE_STATO+" WHERE email = ?;";
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, key);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {				
			    StatoBean stato = new StatoBean();
				
			    //Recupero l'utente proprietario dello stato
			    UtenteDAO utenteDao = new Utente();
				UtenteBean utente = utenteDao.retrieveByKey(rs.getString("email"));
				
				//Recupero il master relativo allo stato
				MasterDAO masterDao = new Master();
				
				//Setto i dati dello stato
				stato.setUtente(utente);
				stato.setEdizione(rs.getString("edizione"));
				stato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				return stato;
			} else
				return null;
		}finally {
			try {
				if(!con.isClosed())
					con.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	@Override
	public Collection<StatoBean> retrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<StatoBean> listStati = new ArrayList<>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			String query = null;
			if(order == null || order.equals("")) {
				query = "SELECT * FROM "+Dizionario.TABLE_STATO+";";
			}else {
				query = "SELECT * FROM "+Dizionario.TABLE_STATO+" ORDER BY "+order+";";
			}
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatoBean stato = new StatoBean();
				
				//Recupero dati dell'utente
				UtenteDAO utenteDao = new Utente();
				UtenteBean utente = utenteDao.retrieveByKey(rs.getString("email"));
				
				//Recupero il master relativo allo stato
				MasterDAO masterDao = new Master();
				
				//Setto i dati dello stato
				stato.setUtente(utente);
				stato.setEdizione(rs.getString("edizione"));
				stato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				listStati.add(stato);
			}
			
			return listStati;
		}finally {
			try {
				if(!con.isClosed())
					con.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	@Override
	public void insert(StatoBean stato) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String query = new String();
		
		query = "INSERT INTO "+Dizionario.TABLE_STATO+"(email, edizione, id_master) VALUES (?, ?, ?);";
		
		try {
			
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, stato.getUtente().getEmail());
			ps.setString(2, stato.getEdizione());
			ps.setInt(3, stato.getMaster().getId());
			
			ps.executeUpdate();
			
		} finally {
			try {
				if (!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
	}

	@Override
	public void update(StatoBean stato) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO
				+" SET interessato = ?, contatto = ?, attesa_data_selezione = ?, presenza_selezione = ?, "
				+ "attesa_esito_selezione = ?, ammesso = ?, scheda_iscrizione_inviata = ?, scheda_iscrizione_approvata = ?, "
				+ "iscritto = ?, quietanza1_inviata = ?, quietanza2_inviata = ?, quietanza3_inviata = ?, "
				+ "quietanza1_pagata = ?, quietanza2_pagata = ?, quietanza3_pagata = ? "
				+ "WHERE email = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, stato.getInteressato());
			ps.setBoolean(2, stato.getContattato());
			ps.setBoolean(3, stato.getAttesaDataSelezione());
			ps.setBoolean(4, stato.getPresenzaSelezione());
			ps.setBoolean(5, stato.getAttesaEsitoSelezione());
			ps.setBoolean(6, stato.getAmmesso());
			ps.setBoolean(7, stato.getSchedaIscrizioneInviata());
			ps.setBoolean(8, stato.getSchedaIscrizioneApprovata());
			ps.setBoolean(9, stato.getIscritto());
			ps.setBoolean(10, stato.getQuietanza1Inviata());
			ps.setBoolean(11, stato.getQuietanza2Inviata());
			ps.setBoolean(12, stato.getQuietanza3Inviata());
			ps.setBoolean(13, stato.getQuietanza1Pagata());
			ps.setBoolean(14, stato.getQuietanza2Pagata());
			ps.setBoolean(15, stato.getQuietanza3Pagata());
			ps.setString(16, stato.getUtente().getEmail());
			
			ps.executeUpdate();
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
	}

	@Override
	public boolean delete(String key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "DELETE FROM "+Dizionario.TABLE_STATO+" WHERE email = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, key);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean checkUtenteInteressato(String emailUtente, String edizione) throws SQLException {
		
		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getInteressato() == true)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUtenteInfoSpecifica(String emailUtente, String edizione) throws SQLException {

		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getInfoSpecifica() == true)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUtenteInfoGenerica(String emailUtente, String edizione) throws SQLException {
		
		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getInfoGenerica() == true)
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean checkUtenteContattato(String emailUtente, String edizione) throws SQLException {

		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getContattato() == true)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUtentePrenotazioneColloquio(String emailUtente, String edizione) throws SQLException {
		
		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getPrenotazioneColloquio() == true)
				return true;
		}
		
		return false;
	}
	
	@Override
	public boolean checkUtenteAttesaDataSelezione(String emailUtente, String edizione) throws SQLException {

		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getAttesaDataSelezione() == true)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUtentePresenzaSelezione(String emailUtente, String edizione) throws SQLException {

		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getPresenzaSelezione() == true)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUtenteAttesaEsitoSelezione(String emailUtente, String edizione) throws SQLException {
		
		Collection<StatoBean> listStati = getStatiPerUtenteEdizione(emailUtente, edizione);
		
		for(StatoBean s: listStati) {
			if(s.getAttesaEsitoSelezione() == true)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean checkUtenteAmmesso(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getAmmesso();
	}

	@Override
	public boolean checkUtenteSchedaIscrizioneInviata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getSchedaIscrizioneInviata();
	}

	@Override
	public boolean checkUtenteSchedaIscrizioneApprovata(String emailUtente) throws SQLException {
		
		return retrieveByKey(emailUtente).getSchedaIscrizioneApprovata();
	}

	@Override
	public boolean checkUtenteIscritto(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getIscritto();
	}
	
	@Override
	public boolean checkUtenteQuietanza1Inviata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getQuietanza1Inviata();
	}

	@Override
	public boolean checkUtenteQuietanza2Inviata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getQuietanza2Inviata();
	}

	@Override
	public boolean checkUtenteQuietanza3Inviata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getQuietanza3Inviata();
	}
	
	@Override
	public boolean checkUtenteQuietanza1Pagata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getQuietanza1Pagata();
	}

	@Override
	public boolean checkUtenteQuietanza2Pagata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getQuietanza2Pagata();
	}

	@Override
	public boolean checkUtenteQuietanza3Pagata(String emailUtente) throws SQLException {

		return retrieveByKey(emailUtente).getQuietanza3Pagata();
	}

	@Override
	public boolean setUtenteInteressato(String emailUtente, String edizione, boolean interesse) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET interessato VALUES (?) "
				 	 + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, interesse);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteInfoSpecifica(String emailUtente, String edizione, boolean infoSpec) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET info_specifica VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, infoSpec);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteInfoGenerica(String emailUtente, String edizione, boolean infoGen) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET info_generica VALUES (?) "
					 + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, infoGen);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteContattato(String emailUtente, String edizione, boolean contattato) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET contattato VALUES (?) "
					 + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, contattato);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtentePrenotazioneColloquio(String emailUtente, String edizione, String nomeMaster, boolean prenotazione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String query = new String();
		
		if(nomeMaster == null) {
			//Qui devo aggiornare tutte le occorrenze dello stato di quel candidato di quella edizione
			query = "UPDATE "+Dizionario.TABLE_STATO+" SET contattato VALUES (?) "
					 + "WHERE email LIKE ? AND edizione = ?;";
			
			try {
				con = DriverManagerConnectionPool.getConnection();
			
				ps = con.prepareStatement(query);
			
				ps.setBoolean(1, prenotazione);
				ps.setString(2, emailUtente);
				ps.setString(3, edizione);
			
				result = ps.executeUpdate();
			} finally {
				try {
					if(!con.isClosed())
						con.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(con);
				}
			}
		} else {
			//Qui devo aggiornare solamente l'occorrenza dello stato di quel candidato di quella edizione e 
			//di quel master specificato eliminando le ocorrenze degli altri stati
			query = "UPDATE "+Dizionario.TABLE_STATO+" SET contattato VALUES (?) "
					 + "WHERE email LIKE ? AND edizione = ? AND id_master = ?;";
			
			String queryDelete = "DELETE FROM "+Dizionario.TABLE_STATO+" "
					+ "WHERE email = ? AND edizione = ? AND id_master != ?;";
			
			try {
				con = DriverManagerConnectionPool.getConnection();
			
				ps = con.prepareStatement(queryDelete);
			
				ps.setBoolean(1, prenotazione);
				ps.setString(2, emailUtente);
				ps.setString(3, edizione);
				MasterDAO mDao = new Master();
				ps.setInt(4, mDao.getMasterPerNome(nomeMaster).getId());
			
				result = ps.executeUpdate();
				
				ps = con.prepareStatement(query);
				
				ps.setString(1, emailUtente);
				ps.setString(2, edizione);
				ps.setInt(3, mDao.getMasterPerNome(nomeMaster).getId());
				
				result = ps.executeUpdate();
			} finally {
				try {
					if(!con.isClosed())
						con.close();
				} finally {
					DriverManagerConnectionPool.releaseConnection(con);
				}
			}	
		}
		
		return (result != 0);
	}
	
	@Override
	public boolean setUtenteAttesaDataSelezione(String emailUtente, String edizione, boolean attesa) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET attesa_data_selezione VALUES (?) "
				+ "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, attesa);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtentePresenzaSelezione(String emailUtente, String edizione, boolean presenza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET presenza_selezione VALUES (?) "
				+ "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, presenza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteAttesaEsitoSelezione(String emailUtente, String edizione, boolean attesa) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET attesa_esito_selezione VALUES (?) "
				+ "WHERE email LIKE ? AND edizione = ? AND id_master = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, attesa);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteAmmesso(String emailUtente, String edizione, String nomeMaster, boolean ammesso) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int resultUpdate = 0;
		int resultDelete = 0;
		
		String queryUpdate = "UPDATE "+Dizionario.TABLE_STATO+" SET ammesso VALUES (?) "
				+ "WHERE email LIKE ? AND edizione = ? AND id_master = ?;";
		
		String queryDelete = "DELETE FROM "+Dizionario.TABLE_STATO+" WHERE email = ? AND edizione = ? AND id_master != ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(queryUpdate);
			
			ps.setBoolean(1, ammesso);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			MasterDAO mDao = new Master();
			ps.setInt(4, mDao.getMasterPerNome(nomeMaster).getId());
			
			resultUpdate = ps.executeUpdate();
			
			ps = con.prepareStatement(queryDelete);
			
			ps.setString(1, emailUtente);
			ps.setString(2, edizione);
			ps.setInt(3, mDao.getMasterPerNome(nomeMaster).getId());
			
			resultDelete = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (resultUpdate != 0 && resultDelete != 0);
	}

	@Override
	public boolean setUtenteSchedaIscrizioneInviata(String emailUtente, String edizione, boolean schedaInviata) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET scheda_iscrizione_inviata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, schedaInviata);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteSchedaIscrizioneApprovata(String emailUtente, String edizione, boolean schedaApprovata) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET scheda_approvata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, schedaApprovata);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteIscritto(String emailUtente, String edizione, boolean iscritto) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET iscritto VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, iscritto);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteQuietanza1Inviata(String emailUtente, String edizione, boolean quietanza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET quietanza1_inviata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, quietanza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteQuietanza2Inviata(String emailUtente, String edizione, boolean quietanza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET quietanza2_inviata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ? AND id_master = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, quietanza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteQuietanza3Inviata(String emailUtente, String edizione, boolean quietanza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET quietanza3_inviata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, quietanza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteQuietanza1Pagata(String emailUtente, String edizione, boolean quietanza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET quietanza1_pagata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, quietanza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteQuietanza2Pagata(String emailUtente, String edizione, boolean quietanza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET quietanza2_pagata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, quietanza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean setUtenteQuietanza3Pagata(String emailUtente, String edizione, boolean quietanza) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "UPDATE "+Dizionario.TABLE_STATO+" SET quietanza3_pagata VALUES (?) "
			 	     + "WHERE email LIKE ? AND edizione = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setBoolean(1, quietanza);
			ps.setString(2, emailUtente);
			ps.setString(3, edizione);
			
			result = ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
		
		return (result != 0);
	}
	
	@Override
	public Collection<StatoBean> getStatiPerUtenteEdizione(String emailUtente, String edizione) throws SQLException {
		Connection con= null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<StatoBean> listStati = new ArrayList<StatoBean>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			String query = "SELECT * FROM "+Dizionario.TABLE_STATO+" WHERE email = ? AND edizone = ?;";
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, emailUtente);
			ps.setString(2, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {				
			    StatoBean stato = new StatoBean();
				
			    //Recupero l'utente proprietario dello stato
			    UtenteDAO utenteDao = new Utente();
				UtenteBean utente = utenteDao.retrieveByKey(rs.getString("email"));
				
				//Recupero il master relativo allo stato
				MasterDAO masterDao = new Master();
				
				//Setto i dati dello stato
				stato.setUtente(utente);
				stato.setEdizione(rs.getString("edizione"));
				stato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				listStati.add(stato);
			}
			
			return listStati;
		}finally {
			try {
				if(!con.isClosed())
					con.close();
			}finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
	
	@Override
	public Collection<StatoBean> getStatoUtentiPerEdizione(String nomeEdizione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<StatoBean> listStati = new ArrayList<StatoBean>();
		
		String query = "SELECT s.email, s.interessato, s.contattato, s.attesa_data_selezione, "
					 + "s.presenza_selezione, s.attesa_esito_selezione, s.ammesso, s.scheda_iscrizione_inviata,"
					 + "s.scheda_iscrizione_approvata, s.iscritto, s.quietanza1_inviata, s.quietanza2_inviata,"
					 + "s.quietanza3_inviata, s.quietanza1_pagata, s.quietanza2_pagata, s.quietanza3_pagata "
					 + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
					 + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON u.email = s.email "
					 + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON s.id_edizione = e.id "
					 + "WHERE e.nome = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, nomeEdizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatoBean stato = new StatoBean();
				UtenteDAO utenteDao = new Utente();
				
				stato.setUtente(utenteDao.retrieveByKey(rs.getString("email")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				listStati.add(stato);
			}
			
			return listStati;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	@Override
	public Collection<StatoBean> getStatoUtentiPerMaster(String nomeMaster) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<StatoBean> listStati = new ArrayList<StatoBean>();
		
		String query = "SELECT s.email, s.interessato, s.contattato, s.attesa_data_selezione, "
					 + "s.presenza_selezione, s.attesa_esito_selezione, s.ammesso, s.scheda_iscrizione_inviata, "
					 + "s.scheda_iscrizione_approvata, s.iscritto, s.quietanza1_inviata, s.quietanza2_inviata, "
					 + "s.quietanza3_inviata, s.quietanza1_pagata, s.quietanza2_pagata, s.quietanza3_pagata "
					 + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
					 + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON u.email = s.email"
					 + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON s.id_edizione = e.id"
					 + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON e.id_master = m.id"
					 + "WHERE m.nome = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, nomeMaster);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatoBean stato = new StatoBean();
				UtenteDAO utenteDao = new Utente();
				
				stato.setUtente(utenteDao.retrieveByKey(rs.getString("email")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				listStati.add(stato);
			}
			
			return listStati;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	@Override
	public Collection<StatoBean> getStatoUtentiPerEdizioneMaster(String nomeEdizione, String nomeMaster)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<StatoBean> listStati = new ArrayList<StatoBean>();
		
		String query = "SELECT s.email, s.interessato, s.contattato, s.attesa_data_selezione, "
					 + "s.presenza_selezione, s.attesa_esito_selezione, s.ammesso, s.scheda_iscrizione_inviata,"
					 + "s.scheda_iscrizione_approvata, s.iscritto, s.quietanza1_inviata, s.quietanza2_inviata,"
					 + "s.quietanza3_inviata, s.quietanza1_pagata, s.quietanza2_pagata, s.quietanza3_pagata "
					 + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
					 + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON u.email = s.email "
					 + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON s.id_edizione = e.id "
					 + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON e.id_master = m.id "
					 + "WHERE m.nome = ? AND e.nome = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, nomeEdizione);
			ps.setString(2, nomeMaster);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				StatoBean stato = new StatoBean();
				UtenteDAO utenteDao = new Utente();
				
				stato.setUtente(utenteDao.retrieveByKey(rs.getString("email")));
				stato.setInteressato(rs.getBoolean("interessato"));
				stato.setContattato(rs.getBoolean("contattato"));
				stato.setAttesaDataSelezione(rs.getBoolean("attesa_data_selezione"));
				stato.setPresenzaSelezione(rs.getBoolean("presenza_selezione"));
				stato.setAttesaEsitoSelezione(rs.getBoolean("attesa_esito_selezione"));
				stato.setAmmesso(rs.getBoolean("ammesso"));
				stato.setSchedaIscrizioneInviata(rs.getBoolean("scheda_iscrizione_inviata"));
				stato.setSchedaIscrizioneApprovata(rs.getBoolean("scheda_iscrizione_approvata"));
				stato.setIscritto(rs.getBoolean("iscritto"));
				stato.setQuietanza1Inviata(rs.getBoolean("quietanza1_inviata"));
				stato.setQuietanza2Inviata(rs.getBoolean("quietanza2_inviata"));
				stato.setQuietanza3Inviata(rs.getBoolean("quietanza3_inviata"));
				stato.setQuietanza1Pagata(rs.getBoolean("quietanza1_pagata"));
				stato.setQuietanza2Pagata(rs.getBoolean("quietanza2_pagata"));
				stato.setQuietanza3Pagata(rs.getBoolean("quietanza3_pagata"));
				
				listStati.add(stato);
			}
			
			return listStati;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

}
