package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.OperatoreDirezioneBean;
import it.almalaborissuite.dao.OperatoreDirezioneDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class OperatoreDirezione implements OperatoreDirezioneDAO {

	@Override
	public OperatoreDirezioneBean retrieveByKey(String key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
				     + "FROM "+Dizionario.TABLE_OPERATORE_DIR+" AS od "
				     + "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = od.email "
				     + "WHERE od.email = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setString(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	        	OperatoreDirezioneBean operatore = new OperatoreDirezioneBean();
	        	
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
	          if (!connection.isClosed())
	            connection.close();
	        } finally {
	          DriverManagerConnectionPool.releaseConnection(connection);
	        }
	      }
	}

	@Override
	public Collection<OperatoreDirezioneBean> retrieveAll(String order) throws SQLException {
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<OperatoreDirezioneBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
					+ "FROM "+Dizionario.TABLE_OPERATORE_DIR+" AS od "
					+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = od.email;";
		      } else {
		        sql = "SELECT u.email, u.ruolo, u.nome, u.cognome, u.telefono, u.indirizzo, u.cap, u.citta, u.provincia, u.data_nascita "
					+ "FROM "+Dizionario.TABLE_OPERATORE_DIR+" AS od "
					+ "INNER JOIN "+Dizionario.TABLE_UTENTE+" AS u ON u.email = od.email "
					+ "ORDER BY "+order+";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		    	OperatoreDirezioneBean operatore = new OperatoreDirezioneBean();  
		    	  
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
		    	  
		        list.add(operatore);
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
	public void insert(OperatoreDirezioneBean operatoredirezione) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_OPERATORE_DIR+" (email) values (?);";
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, operatoredirezione.getEmail());
	      
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
	public void update(OperatoreDirezioneBean operatoredirezione) throws SQLException {
		Connection connection = null;
	    UtenteDAO userDao = new Utente();

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      userDao.update(operatoredirezione);

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
	public boolean delete(String key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "DELETE FROM "+Dizionario.TABLE_OPERATORE_DIR+" WHERE email = ?;";

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1, key);

	      int result = preparedStatement.executeUpdate();

	      //Se l'operatore direzione &egrave; stato cancellato...
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
	public void insert(OperatoreDirezioneBean utente, String pwd) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      //inserimento dell'utente
	      UtenteDAO utenteDao = new Utente();
	      utenteDao.insert(utente, pwd);

	      //inserimento Operatore_Advertiser
	      String sql = "insert into "+Dizionario.TABLE_OPERATORE_DIR+" (email) values (?);";

	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, utente.getEmail());

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

}
