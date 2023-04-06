package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.OperatoreSelezionatoreBean;
import it.almalaborissuite.dao.OperatoreSelezionatoreDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class OperatoreSelezionatore implements OperatoreSelezionatoreDAO{
	
	@Override
	public OperatoreSelezionatoreBean retrieveByKey(String key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
				     + "FROM "+Dizionario.TABLE_OPERATORE_SEL+" AS os "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = os.email "
				     + "WHERE os.email = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, key);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				OperatoreSelezionatoreBean operatore = new OperatoreSelezionatoreBean();
				
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
	public Collection<OperatoreSelezionatoreBean> retrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = null;
		
		Collection<OperatoreSelezionatoreBean> listOperatori = new ArrayList<OperatoreSelezionatoreBean>();
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			if(order == null || order.equals("")) {
				query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
						+ "FROM "+Dizionario.TABLE_OPERATORE_SEL+" AS os "
						+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = os.email;";
			} else {
				query = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
						+ "FROM "+Dizionario.TABLE_OPERATORE_SEL+" AS os "
						+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = os.email "
						+ "ORDER BY "+order+";";
			}
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				OperatoreSelezionatoreBean operatore = new OperatoreSelezionatoreBean();
				
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
	public void insert(OperatoreSelezionatoreBean operatore) throws SQLException {	}

	@Override
	public void update(OperatoreSelezionatoreBean operatore) throws SQLException {
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

	      String sql = "DELETE FROM "+Dizionario.TABLE_OPERATORE_SEL+" WHERE email = ?;";

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1, key);

	      int result = preparedStatement.executeUpdate();

	      //Se il selzionatore &egrave; stato cancellato...
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
	public void insert(OperatoreSelezionatoreBean operatore, String pwd) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			UtenteDAO utenteDao = new Utente();
			utenteDao.insert(operatore, pwd);
			
			String query = "INSERT INTO "+Dizionario.TABLE_OPERATORE_SEL+" (email) VALUES (?);";
			
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

	@Override
	public boolean setEsitoSelezione(CandidatoBean candidato, boolean esito) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String query = null;
		int result = 0;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			query = "UPDATE "+Dizionario.TABLE_STATO+" SET attesa_esito_selezione = ?, ammesso = ? WHERE email = ?;";
			
			ps = con.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setBoolean(2, esito);
			ps.setString(3, candidato.getEmail());
			
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

}
