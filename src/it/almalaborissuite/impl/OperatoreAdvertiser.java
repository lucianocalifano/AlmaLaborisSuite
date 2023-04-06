package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.OperatoreAdvertiserBean;
import it.almalaborissuite.dao.OperatoreAdvertiserDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;
import it.almalaborissuite.dao.UtenteDAO;

public class OperatoreAdvertiser implements OperatoreAdvertiserDAO {

	@Override
	public OperatoreAdvertiserBean retrieveByKey(String key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select als_utente.email, pw, ruolo, nome, cognome, telefono, indirizzo, cap, citta, provincia ,data_nascita FROM  als_operatore_advertiser INNER JOIN als_utente ON als_operatore_advertiser.email= als_utente.email WHERE als_utente.email= ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setString(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          OperatoreAdvertiserBean bean = new OperatoreAdvertiserBean();
	          bean.setEmail(rs.getString("email"));;
	          bean.setRuolo(rs.getString("ruolo"));
	          bean.setNome(rs.getString("nome"));
	          bean.setCognome(rs.getString("cognome"));
	          bean.setTelefono(rs.getString("telefono"));
	          bean.setIndirizzo(rs.getString("indirizzo"));
	          bean.setCap(rs.getString("cap"));
	          bean.setCitta(rs.getString("citta"));
	          bean.setProvincia(rs.getString("provincia"));
	          bean.setDataNascita(LocalDateTime.parse(rs.getString("data_nascita"), Dizionario.DATE_FORMAT));
	             
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
	public Collection<OperatoreAdvertiserBean> retrieveAll(String order) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<OperatoreAdvertiserBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_OPERATORE_ADV+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_OPERATORE_ADV+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        OperatoreAdvertiserBean bean = new OperatoreAdvertiserBean();
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
	public void insert(OperatoreAdvertiserBean operatoreadvertiser) throws SQLException {
		//do something
	}

	
	
	@Override
	public void update(OperatoreAdvertiserBean operatoreadvertiser) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_OPERATORE_ADV+" set email = ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1,operatoreadvertiser.getEmail());
	      
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
	public boolean delete(String key) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;

		    try {
		      connection = DriverManagerConnectionPool.getConnection();

		      String sql = "delete from "+Dizionario.TABLE_OPERATORE_ADV+" where email = ?;";

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
	public void insert(OperatoreAdvertiserBean utente, String pwd) throws SQLException {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		      connection = DriverManagerConnectionPool.getConnection();

		      //inserimento dell'utente
		      UtenteDAO utenteDao = new Utente();
		      utenteDao.insert(utente, pwd);

		      //inserimento Operatore_Advertiser
		      String sql = "insert into als_operatore_advertiser (email) values (?);";

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
