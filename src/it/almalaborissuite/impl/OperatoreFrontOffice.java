package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.OperatoreFrontOfficeBean;
import it.almalaborissuite.dao.OperatoreFrontOfficeDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class OperatoreFrontOffice implements OperatoreFrontOfficeDAO{
	
	@Override
	public OperatoreFrontOfficeBean retrieveByKey(String key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
				     + "FROM "+Dizionario.TABLE_OPERATORE_FRO+" AS ofo "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = ofo.email "
				     + "WHERE ofo.email = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, key);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				OperatoreFrontOfficeBean operatore = new OperatoreFrontOfficeBean();
				
				operatore.setEmail(rs.getString("email"));
				operatore.setRuolo(rs.getString("ruolo"));
				operatore.setNome(rs.getString("nome"));
				operatore.setCognome(rs.getString("cognome"));
				operatore.setTelefono(rs.getString("telefono"));
				operatore.setIndirizzo(rs.getString("indirizzo"));
				operatore.setCap(rs.getString("cap"));
				operatore.setCitta(rs.getString("citta"));
				operatore.setProvincia(rs.getString("provincia"));
				operatore.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				return operatore;				
			} else
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
	public Collection<OperatoreFrontOfficeBean> retrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		
		Collection<OperatoreFrontOfficeBean> listOperatori = new ArrayList<OperatoreFrontOfficeBean>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			if(order == null || order.equals("")) {
				query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
						+ "FROM "+Dizionario.TABLE_OPERATORE_FRO+" AS ofo "
						+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = ofo.email;";
			} else {
				query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
						+ "FROM "+Dizionario.TABLE_OPERATORE_FRO+" AS ofo "
						+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = ofo.email "
						+ "ORDER BY "+order+";";
			}
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OperatoreFrontOfficeBean operatore = new OperatoreFrontOfficeBean();
				
				operatore.setEmail(rs.getString("email"));
				operatore.setRuolo(rs.getString("ruolo"));
				operatore.setNome(rs.getString("nome"));
				operatore.setCognome(rs.getString("cognome"));
				operatore.setTelefono(rs.getString("telefono"));
				operatore.setIndirizzo(rs.getString("indirizzo"));
				operatore.setCap(rs.getString("cap"));
				operatore.setCitta(rs.getString("citta"));
				operatore.setProvincia(rs.getString("provincia"));
				operatore.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
				
				listOperatori.add(operatore);
			}
			
			return listOperatori;
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
	public void insert(OperatoreFrontOfficeBean object) throws SQLException { }

	@Override
	public void update(OperatoreFrontOfficeBean operatore) throws SQLException {
		Connection con = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			UtenteDAO utenteDao = new Utente();
			utenteDao.update(operatore);
			
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

	      String sql = "DELETE FROM "+Dizionario.TABLE_OPERATORE_FRO+" WHERE email = ?;";

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1, key);

	      int result = preparedStatement.executeUpdate();

	      //Se l'operatore front office &egrave; stato cancellato...
	      if(result == 1) {
	        //...cancella l'utente
	        UtenteDAO utenteDao = new Utente();
	        if(utenteDao.delete(key)) {
	          //se l'utente &egrave; stato cancellato return true
	          return true;
	        }else {
	          //altrimenti return false
	          return false;
	        }
	      }else {
	        //...altrimenti cancellazione non avvenuta
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
	public void insert(OperatoreFrontOfficeBean operatore, String pwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			UtenteDAO utenteDao = new Utente();
			utenteDao.insert(operatore, pwd);
			
			String query = "INSERT INTO "+Dizionario.TABLE_OPERATORE_FRO+" (email) VALUES (?);";
			
			ps = con.prepareStatement(query);
			ps.setString(1, operatore.getEmail());
			
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

}
