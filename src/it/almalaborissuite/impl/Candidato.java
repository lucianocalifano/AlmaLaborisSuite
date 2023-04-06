package it.almalaborissuite.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.CandidatoDAO;
import it.almalaborissuite.dao.MasterDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;
import it.almalaborissuite.services.GestorePassword;

public class Candidato implements CandidatoDAO{

	@Override
	public void insert(CandidatoBean candidato, String pw) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			UtenteDAO utenteDao = new Utente();
			utenteDao.insert(candidato, pw);
			
			String query = "insert into "+Dizionario.TABLE_CANDIDATO+" (email, id_master, fonte, format_scelto , cv, scheda_iscrizione, quietanza1) values (?, ?, ?, ?, ?, ?, ?);";
			
			ps = con.prepareStatement(query);
			ps.setString(1, candidato.getEmail());
			ps.setInt(2, candidato.getMaster().getId());
		    ps.setString(3, candidato.getFonte());
		    ps.setString(4, candidato.getFormatScelto());
		    ps.setString(5, candidato.getCv());
		    ps.setString(6, candidato.getSchedaIscrizione());
		    ps.setString(7, candidato.getQuietanza1());
			
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
	public void insert(CandidatoBean candidato) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_CANDIDATO+" (email, id_master, fonte, format_scelto, cv, scheda_iscrizione, quietanza1) values (?, ?, ?, ?, ?, ?, ?);";
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, candidato.getEmail());
	      preparedStatement.setInt(2, candidato.getMaster().getId());
	      preparedStatement.setString(3, candidato.getFonte());
	      preparedStatement.setString(4, candidato.getFormatScelto());
	      preparedStatement.setString(5, candidato.getCv());
	      preparedStatement.setString(6, candidato.getSchedaIscrizione());
	      preparedStatement.setString(7, candidato.getQuietanza1());
	      
	      preparedStatement.executeUpdate();
	    }finally {
	      try {
	        if (!connection.isClosed())
	          connection.close();
	      } finally {
	        DriverManagerConnectionPool.releaseConnection(connection);
	      }
	   }
	}
	
	@Override
	public Collection<CandidatoBean> retrieveAll(String order) throws SQLException {
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<CandidatoBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_CANDIDATO+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_CANDIDATO+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        CandidatoBean bean = new CandidatoBean();
		        String email = rs.getString("email");
		        bean = retrieveByKey(email);
		        list.add(bean);
		      }
		      return list;
		      
		    } finally {
		      try {
		        if (!connection.isClosed())
		          connection.close();
		      } finally {
		        DriverManagerConnectionPool.releaseConnection(connection);
		      }
		   }
	}

	@Override
	public Collection<CandidatoBean> getCandidatiperMaster(MasterBean master) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "select * from "+Dizionario.TABLE_CANDIDATO+" where id_master like "+master.getId()+";";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiperFonte(String fonte) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "select * from "+Dizionario.TABLE_CANDIDATO+" where fonte = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1,fonte);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiSchedaIscrizioneInviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1 \r\n"
				+ "FROM als_candidato as c \r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE scheda_iscrizione_inviata = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiSchedaIscrizioneApprovata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato  as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE scheda_iscrizione_approvata = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiQuietanza1Inviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE quietanza1_inviata = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiInteressati() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email \r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE interessato = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiContattati() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE contattato = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiAttesaDataSelezione() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE attesa_data_selezione = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiPresenzaSelezione() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE presenza_selezione = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiAttesaEsitoSelezione() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE attesa_esito_selezione = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiAmmessi() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE ammesso = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiQuietanza1pagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE quietanza1_pagata = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiQuietanza1Nonpagata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE quietanza1_pagata = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiperFormat(String format) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "select * from "+Dizionario.TABLE_CANDIDATO+" where format_scelto = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, format);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
		Connection connection = null;
		 PreparedStatement preparedStatement = null;

		    try {
		      connection = DriverManagerConnectionPool.getConnection();

		      String sql = "delete from "+Dizionario.TABLE_CANDIDATO+" where email = ?;";

		      preparedStatement = connection.prepareStatement(sql);

		      preparedStatement.setString(1, key);

		      int result = preparedStatement.executeUpdate();

		      if (result == 1) {
		        return true;
		      } else {
		        return false;
		      }
		    } finally {
		      try {
		        if (!connection.isClosed())
		          connection.close();
		      } finally {
		        DriverManagerConnectionPool.releaseConnection(connection);
		      }
		    }
	}
	

	@Override
	public CandidatoBean retrieveByKey(String key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta,"
					 + "u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto,"
					 + "c.cv, c.scheda_iscrizione, c.quietanza1 "
					 + "FROM "+Dizionario.TABLE_UTENTE+" AS u "
					 + "INNER JOIN "+Dizionario.TABLE_CANDIDATO+" AS c ON u.email = c.email "
					 + "WHERE c.email = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setString(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          CandidatoBean bean = new CandidatoBean();
	          UtenteDAO userDao = new Utente();
	          
			  bean.setEmail(rs.getString("email"));
			  bean.setRuolo(rs.getString("ruolo"));
			  bean.setNome(rs.getString("nome"));
			  bean.setCognome(rs.getString("cognome"));
		      bean.setTelefono(rs.getString("telefono"));
			  bean.setIndirizzo(rs.getString("indirizzo"));
		      bean.setCap(rs.getString("cap"));
			  bean.setCitta(rs.getString("citta"));
			  bean.setProvincia(rs.getString("provincia"));
			  
			  if(rs.getTimestamp("data_nascita") == null) {
				  bean.setDataNascita(null);
			  } else {
				  bean.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());  
			  }

			  //Recuperiamo i dati relativi al Master
	          MasterDAO masterDao = new Master();
	          MasterBean masterBean = masterDao.retrieveByKey(rs.getInt("id_master"));
	          bean.setMaster(masterBean);
	          bean.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
	          bean.setFonte(rs.getString("fonte"));
	          bean.setFormatScelto(rs.getString("format_scelto"));
	          bean.setCv(rs.getString("cv"));
	          bean.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
	          bean.setQuietanza1(rs.getString("quietanza1"));
	          
	          return bean;
	        } else
	          return null;
	      } finally {
	        try {
	          if (!connection.isClosed())
	            connection.close();
	        } finally {
	          DriverManagerConnectionPool.releaseConnection(connection);
	        }
	      }
	}

	@Override
	public void update(CandidatoBean candidato) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_CANDIDATO+" set data_inserimento = ?, fonte = ?, format_scelto = ?, cv = ?, scheda_iscrizione = ?, quietanza1= ?"
	      		+ " WHERE email = ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1, candidato.getDataInserimento().toString());
	      preparedStatement.setString(2, candidato.getFonte());
	      preparedStatement.setString(3, candidato.getFormatScelto());
	      preparedStatement.setString(4, candidato.getCv());
	      preparedStatement.setString(5, candidato.getSchedaIscrizione());
	      preparedStatement.setString(6, candidato.getQuietanza1());
	      preparedStatement.setString(7, candidato.getEmail());
	      
	      System.out.println("doUpdate: "+ preparedStatement.toString());

	      preparedStatement.executeUpdate();

	    } finally {
	      try {
	        if (!connection.isClosed())
	          connection.close();
	      } finally {
	        DriverManagerConnectionPool.releaseConnection(connection);
	      }
	    }
		
	}

	@Override
	public Collection<CandidatoBean> getCandidatiSchedaIscrizioneNonInviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1 \r\n"
				+ "FROM als_candidato as c \r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE scheda_iscrizione_inviata = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiSchedaIscrizioneNonApprovata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1 \r\n"
				+ "FROM als_candidato  as c \r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email \r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email \r\n"
				+ "WHERE scheda_iscrizione_approvata = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFormatScelto(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiQuietanza1NonInviata() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE quietanza1_inviata = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonInteressati() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE interessato = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonContattati() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM  als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato  as s ON s.email = c.email\r\n"
				+ "WHERE contattato = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonAttesaDataSelezione() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato  as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE attesa_data_selezione = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonPresenzaSelezione() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE presenza_selezione = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonAttesaEsitoSelezione() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato  as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE attesa_esito_selezione = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonAmmessi() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE ammesso = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiIscritti() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE iscritto = 1;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public Collection<CandidatoBean> getCandidatiNonIscritti() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<CandidatoBean> listCandidato = new ArrayList<>();
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita, c.id_master, c.data_inserimento, c.fonte, c.format_scelto, c.cv, c.scheda_iscrizione, c.quietanza1\r\n"
				+ "FROM als_candidato as c\r\n"
				+ "INNER JOIN als_utente as u ON u.email = c.email\r\n"
				+ "INNER JOIN als_stato as s ON s.email = c.email\r\n"
				+ "WHERE iscritto = 0;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CandidatoBean candidato = new CandidatoBean();
				MasterDAO masterDao = new Master();
				candidato.setEmail(rs.getString("email"));
				candidato.setRuolo(rs.getString("ruolo"));
				candidato.setNome(rs.getString("nome"));
				candidato.setCognome(rs.getString("cognome"));
				candidato.setTelefono(rs.getString("telefono"));
				candidato.setIndirizzo(rs.getString("indirizzo"));
				candidato.setCap(rs.getString("cap"));
				candidato.setProvincia(rs.getString("provincia"));
				candidato.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());
				candidato.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				candidato.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
				candidato.setFonte(rs.getString("fonte"));
				candidato.setFonte(rs.getString("format_scelto"));
				candidato.setCv(rs.getString("cv"));
				candidato.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
				candidato.setQuietanza1(rs.getString("quietanza1"));
				
				listCandidato.add(candidato);
			}
			
			return listCandidato;
			
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
	public CandidatoBean retrieveByKey(CandidatoBean candidato, MasterBean master) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "SELECT * "
	        			+ "FROM "+Dizionario.TABLE_CANDIDATO+" AS c "
	        			+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = c.email "
	        			+ "where c.email = ? AND c.id_master = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setString(1, candidato.getEmail());
	        preparedStatement.setInt(2, master.getId());

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          CandidatoBean bean = new CandidatoBean();
	          
	          //Recuperiamo i dati dell'utente
	          bean.setEmail(rs.getString("email"));
	          bean.setRuolo(rs.getString("ruolo"));
	          bean.setNome(rs.getString("nome"));
	          bean.setCognome(rs.getString("cognome"));
	          bean.setTelefono(rs.getString("telefono"));
	          bean.setIndirizzo(rs.getString("telefono"));
	          bean.setCap(rs.getString("cap"));
	          bean.setCitta(rs.getString("citta"));
	          bean.setProvincia(rs.getString("provincia"));
	          bean.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));

	          //Gestisco le note
	          File note = new File(System.getProperty("user.dir")+"/WebContent/edizioni/"+bean.getNome()+"_"+bean.getCognome()+"_note.txt");
	          
	          try {
	  			note.createNewFile();
	  		  } catch (IOException e) {
	  			e.printStackTrace();
	  		  }
	          
	          BufferedOutputStream bos = null;
	  		  try {
	  			  bos = new BufferedOutputStream(new FileOutputStream(note));
	  		  } catch (FileNotFoundException e) {
	  			  e.printStackTrace();
	  		  }
	  		
	  		  if(rs.getBinaryStream("note") != null) {
	  			  InputStream is = rs.getBinaryStream("note");
	  			  byte[] buffer = new byte[256];
	  			  try {
	  				  while (is.read(buffer) > 0) {
	  					  bos.write(buffer);
	  				  }
	  				  bos.close();
	  			  } catch (IOException e) {
	  				  e.printStackTrace();
	  			  }
	  		  }
	          
	          bean.setNote(note);
	          
	          //Recuperiamo i dati relativi al master
	          MasterDAO masterDao = new Master();
	          MasterBean masterBean = masterDao.retrieveByKey(rs.getInt("id_master"));
	          
	          //Settiamo i dati relativi al candidato
	          bean.setMaster(masterBean);
			  bean.setDataInserimento(rs.getTimestamp("data_inserimento").toLocalDateTime());
	          bean.setFonte(rs.getString("fonte"));
	          bean.setFormatScelto(rs.getString("format_scelto"));
	          bean.setCv(rs.getString("cv"));
	          bean.setSchedaIscrizione(rs.getString("scheda_iscrizione"));
	          bean.setQuietanza1(rs.getString("quietanza1"));
	          
	          return bean;
	        } else
	          return null;
	      } finally {
	        try {
	          if (!connection.isClosed())
	            connection.close();
	        } finally {
	          DriverManagerConnectionPool.releaseConnection(connection);
	        }
	      }
	}

	@Override
	public void insertCandidatiFromExcel(String pathfile) throws FileNotFoundException, IOException {
		Collection <CandidatoBean> listaCandidati = new ArrayList <CandidatoBean>();
		//creo il workbook
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(pathfile));
		//creo il foglio Excel
		HSSFSheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        //ITERATOR RIGA DEL FILE EXCEL (-> TUPLA DA AGGIUNGERE AL DB)
        while (iterator.hasNext()) {
        	CandidatoBean candidato= new CandidatoBean();
        	MasterDAO masterDao = new Master();
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            //CELLITERATOR RAPPRESENTA LA SINGOLA CELLA (-> SINGOLO COLONNA SUL DB)
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                File note = new File("note.txt");
                switch (cell.getColumnIndex()) {
                    case 0 : //EMAIL
                    	candidato.setEmail(cell.getStringCellValue());
                        break;
                    case 1: //NOME
                    	candidato.setNome(cell.getStringCellValue());
                        break;
                    case 2: //COGNOME
                    	candidato.setCognome(cell.getStringCellValue());
                        break;
                    case 3: //TELEFONO
                    	candidato.setTelefono(cell.getStringCellValue());
                        break;
                    case 4: //INDIRIZZO
                    	candidato.setIndirizzo(cell.getStringCellValue());
                        break;
                    case 5: //CAP
                    	candidato.setCap(cell.getStringCellValue());
                        break;
                    case 6: //CITTA
                    	candidato.setCitta(cell.getStringCellValue());
                        break;
                    case 7: //PROVINCIA
                    	candidato.setProvincia(cell.getStringCellValue());
                    	break; 
                    case 8: //DATA NASCITA
                    	if(nextRow.getCell(8).getCellType() == CellType.NUMERIC) {
                    	LocalDateTime dateLt = nextRow.getCell(8).getLocalDateTimeCellValue();
                    	System.out.println(dateLt);
                    	candidato.setDataNascita(dateLt);}
                    	break;
                    case 9: //MASTER
                    	try {
                    		candidato.setMaster(masterDao.getMasterPerNome(cell.getStringCellValue()));
                    		System.out.println(candidato.getMaster());
                    	} catch (SQLException e) {
                    		System.out.println("Errore associazione candidato master");
                    		e.printStackTrace();
                    	}
                    	break; 
                    case 10: //FONTE
                    	candidato.setFonte(cell.getStringCellValue());
                    	break;
                    default:
    					break;
                }
                candidato.setRuolo("CANDIDATO");
                candidato.setNote(note);
                
             //riempiere con campi automatici
           	 /* //INSERT CANDIDATO
        	 * pw (creare un metodo in GestionePw che prende )
        	 * ruolo (a seconda della funzione)
        	 * //INESRT CANDIDATO
        	 * data_inserimento (quella corrente) //fatto nel db?Si.
        	 */
            }
            listaCandidati.add(candidato);
        }
        workbook.close();
        for(CandidatoBean d : listaCandidati) {
        	CandidatoDAO cDao= new Candidato();	
        	try {
				cDao.insert(d,GestorePassword.generateInitPassword(d));
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}
}



