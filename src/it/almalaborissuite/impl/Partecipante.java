package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.PartecipanteBean;
import it.almalaborissuite.dao.EdizioneDAO;
import it.almalaborissuite.dao.PartecipanteDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Partecipante implements PartecipanteDAO {
	
	@Override
	public void insert(PartecipanteBean partecipante, String pw) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			UtenteDAO utenteDao = new Utente();
			utenteDao.insert(partecipante, pw);
			
			String query = "INSERT INTO "+Dizionario.TABLE_PAX+"(email, id_edizione, fonte, format_scelto, cv,"
					     + "scheda_iscrizione, quietanza1, quietanza2,  quietanza3) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

			ps = con.prepareStatement(query);
			
			ps.setString(1, partecipante.getEmail());
			ps.setInt(2, partecipante.getEdizione().getId());
			ps.setString(3, partecipante.getFonte());
			ps.setString(4, partecipante.getFormatScelto());
			ps.setString(5, partecipante.getCv());
			ps.setString(6, partecipante.getSchedaIscrizione());
			ps.setString(7, partecipante.getQuietanza1());
			ps.setString(8, partecipante.getQuietanza2());
			ps.setString(9, partecipante.getQuietanza3());
		
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
	public void insert(PartecipanteBean partecipante) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO "+Dizionario.TABLE_PAX+""
					   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, partecipante.getEmail());
			ps.setString(2, partecipante.getDataInserimento().toString());
			ps.setString(3, partecipante.getFonte());
			ps.setString(4, partecipante.getFormatScelto());
			ps.setString(5, partecipante.getCv());
			ps.setString(6, partecipante.getSchedaIscrizione());
			ps.setString(7, partecipante.getQuietanza1());
			ps.setString(8, partecipante.getQuietanza2());
			ps.setString(9, partecipante.getQuietanza3());
			
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
	public PartecipanteBean retrieveByKey(String key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta,"
						 + "u.provincia, u.data_nascita, p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
						 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
						 + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
						 + "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON u.email = p.email "
						 + "WHERE p.email = ?;";
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, key);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();
				
				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				return partecipante;
			}else
				return null;
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
	public Collection<PartecipanteBean> retrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			String query = null;
			
			if(order == null || order.equals("")) {
				query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo,"
						+ "u.cap, u.citta, u.provincia, u.data_nascita, p.id_edizione, p.data_inserimento,"
						+ "p.fonte, p.format_scelto, p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
						+ "FROM "+Dizionario.TABLE_UTENTE+" AS u "
						+ "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON p.email = u.email;";
			} else {
				query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo,"
						+ "u.cap, u.citta, u.provincia, u.data_nascita, p.id_edizione, p.data_inserimento,"
						+ "p.fonte, p.format_scelto, p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
						+ "FROM "+Dizionario.TABLE_UTENTE+" AS u "
						+ "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON p.email = u.email "
						+ "ORDER BY "+order+";";
			}
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
				
			return listPartecipanti;
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
	public void update(PartecipanteBean partecipante) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "UPDATE "+Dizionario.TABLE_PAX+" "
					 + "SET id_edizione = ?, data_inserimento = ?, fonte = ?,"
					 + "format_scelto = ?, cv = ?, scheda_iscrizione = ?,"
					 + "quietanza1 = ?, quietanza2 = ?, quietanza3 = ? WHERE email = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			UtenteDAO utenteDao = new Utente();
			utenteDao.update(partecipante);
			
			ps = con.prepareStatement(query);
			
			ps.setInt(1, partecipante.getEdizione().getId());
			ps.setString(2, Dizionario.DATE_FORMAT.format(partecipante.getDataInserimento()).toString());
			ps.setString(3, partecipante.getFonte());
			ps.setString(4, partecipante.getFormatScelto());
			ps.setString(5, partecipante.getCv());
			ps.setString(6, partecipante.getSchedaIscrizione());
			ps.setString(7, partecipante.getQuietanza1());
			ps.setString(8, partecipante.getQuietanza2());
			ps.setString(9, partecipante.getQuietanza3());
			ps.setString(10, partecipante.getEmail());
			
			ps.executeUpdate();
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.getConnection();
			}
		}
	}

	@Override
	public boolean delete(String key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "DELETE FROM "+Dizionario.TABLE_PAX+" WHERE email = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, key);
			
			result = ps.executeUpdate();
			
			//se il partecipante &egrave; stato cancellato...
		    if(result == 1) {
		    	//...cancella l'utente
		        UtenteDAO utenteDao = new Utente();
		        if(utenteDao.delete(key)) {
		        	//se l'utente &egrave; stato cancellato return true
		        	return true;
		        } else {
		        	//altrimenti return false
		        	return false;
		        }
		    } else {
		    	//...altrimenti cancellazione non avvenuta
		        return false;
		      }

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
	public Collection<PartecipanteBean> getPartecipantiPerEdizione(String edizione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo,"
					  + "u.cap, u.citta, u.provincia, u.data_nascita, p.id_edizione,"
					  + "p.data_inserimento, p.fonte, p.format_scelto, p.cv, p.scheda_iscrizione,"
					  + "p.quietanza1, p.quietanza2, p.quietanza3 "
					  + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
				      + "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON p.email = u.email "
				      + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				      + "WHERE e.nome = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiPerMaster(String master) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String masterName = "%"+master+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
				      + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
				      + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
				      + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
				      + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
				      + "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON p.email = u.email "
				      + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				      + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				      + "WHERE m.nome LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, masterName);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap,"
					  + "u.citta, u.provincia, u.data_nascita, p.id_edizione, p.data_inserimento, p.fonte,"
					  + "p.format_scelto, p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
				      + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
				      + "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON p.email = u.email "
				      + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
			          + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				      + "WHERE e.nome = ? AND m.nome = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiPerNome(String nome) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		nome = "%"+nome+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "WHERE u.nome LIKE ? ;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, nome);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiPerCognome(String cognome) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		cognome = "%"+cognome+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "WHERE u.cognome LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, cognome);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiPerFonte(String fonte) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		fonte = "%"+fonte+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "WHERE p.fonte LIKE ? ;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, fonte);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiPerFormat(String format) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<PartecipanteBean> listPax = new ArrayList<PartecipanteBean>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
					 + "INNER JOIN "+Dizionario.TABLE_PAX+" AS p ON u.email = p.email "
					 + "WHERE p.format_scelto = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, format);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPax.add(partecipante);	
			}
			
			return listPax;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.getConnection();
			}
		}
	}
	
	/******** START GESTIONE PRIMA QUIETANZA *******/
	
	@Override
	public Collection<PartecipanteBean> getPartecipantiQuietanza1Pagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza1_pagata = 1 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza1PagataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza1_pagata = 1 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza1PagataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		master = "%"+master+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza1_pagata = 1 AND e.nome = ? AND  m.nome LIKE ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	/******** END GESTIONE PRIMA QUIETANZA *******/
	
	/******** START GESTIONE SECONDA QUIETANZA *******/
	
	@Override
	public Collection<PartecipanteBean> getPartecipantiQuietanza2Inviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_inviata = 1 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonInviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza1_inviata = 0 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2Pagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_pagata = 1 "
				     + "GROUP BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonPagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_pagata = 0 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2InviataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_inviata = 1 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonInviataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_inviata = 0 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2PagataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_pagata = 1 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonPagataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza2_pagata = 0 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2InviataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		master = "%"+master+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza2_inviata = 1 AND e.nome = ? AND m.nome LIKE ?"
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonInviataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza2_inviata = 0 AND e.nome = ? AND m.nome LIKE ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2PagataPerEdizioneMaster(String edizione, String master) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		master = "%"+master+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza2_pagata = 1 AND e.nome = ? AND m.nome LIKE ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza2NonPagataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		master = "%"+master+"%";
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza2_pagata = 0 AND e.nome = ? AND m.nome LIKE ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	/******** END GESTIONE SECONDA QUIETANZA *******/
	
	/******** START GESTIONE TERZA QUIETANZA *******/
	
	@Override
	public Collection<PartecipanteBean> getPartecipantiQuietanza3Inviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_inviata = 1 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonInviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_inviata = 0 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3Pagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_pagata = 1 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonPagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_pagata = 0 "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3InviataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_inviata = 1 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonInviataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo,u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_inviata = 0 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3PagataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_pagata = 1 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonPagataPerEdizione(String edizione)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "WHERE s.quietanza3_pagata = 0 AND e.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3InviataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza3_inviata = 1 AND e.nome = ? AND m.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonInviataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza3_inviata = 0 AND e.nome = ? AND m.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3PagataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza3_pagata = 1 AND e.nome = ? AND m.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
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
	public Collection<PartecipanteBean> getPartecipantiQuietanza3NonPagataPerEdizioneMaster(String edizione, String master)
			throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<PartecipanteBean> listPartecipanti = new ArrayList<>();
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono,"
					 + "u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita,"
					 + "p.id_edizione, p.data_inserimento, p.fonte, p.format_scelto,"
					 + "p.cv, p.scheda_iscrizione, p.quietanza1, p.quietanza2, p.quietanza3 "
					 + "FROM "+Dizionario.TABLE_PAX+" AS p "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON p.email = u.email "
				     + "INNER JOIN "+Dizionario.TABLE_STATO+" AS s ON s.email = p.email "
				     + "INNER JOIN "+Dizionario.TABLE_EDIZIONE+" AS e ON e.id = p.id_edizione "
				     + "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "
				     + "WHERE s.quietanza3_pagata = 0 AND e.nome = ? AND m.nome = ? "
				     + "ORDER BY e.nome;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, edizione);
			ps.setString(2, master);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				PartecipanteBean partecipante = new PartecipanteBean();
				EdizioneDAO edizioneDao = new Edizione();

				//Recupero dati di utente bean
				partecipante.setEmail(rs.getString("email"));
				partecipante.setRuolo(rs.getString("ruolo"));
				partecipante.setNome(rs.getString("nome"));
				partecipante.setCognome(rs.getString("cognome"));
				partecipante.setTelefono(rs.getString("telefono"));
				partecipante.setIndirizzo(rs.getString("indirizzo"));
				partecipante.setCap(rs.getString("cap"));
				partecipante.setCitta(rs.getString("citta"));
				partecipante.setProvincia(rs.getString("provincia"));
				partecipante.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				//Recupero dati di partecipante bean
				partecipante.setEdizione(edizioneDao.retrieveByKey(rs.getInt("id_edizione")));
				partecipante.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				partecipante.setFonte(rs.getString("fonte"));
				partecipante.setFormatScelto(rs.getString("format_scelto"));
				partecipante.setCv(rs.getString("cv"));
				partecipante.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				partecipante.setQuietanza1(rs.getString("quietanza1"));
				partecipante.setQuietanza2(rs.getString("quietanza2"));
				partecipante.setQuietanza3(rs.getString("quietanza3"));
				
				listPartecipanti.add(partecipante);
			}
			
			return listPartecipanti;
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}

	/******** END GESTIONE TERZA QUIETANZA *******/
}
