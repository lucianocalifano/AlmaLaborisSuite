package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


import it.almalaborissuite.bean.OperatoreContentBean;
import it.almalaborissuite.dao.OperatoreContentDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class OperatoreContent implements OperatoreContentDAO {

	@Override
	public OperatoreContentBean retrieveByKey(String key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select als_utente.email, pw, ruolo, nome, cognome, telefono, indirizzo, cap, citta, provincia ,data_nascita FROM  als_operatore_content INNER JOIN als_utente ON als_operatore_content.email= als_utente.email WHERE als_utente.email= ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setString(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          OperatoreContentBean bean = new OperatoreContentBean();
	          bean.setEmail(rs.getString("email"));
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
	public Collection<OperatoreContentBean> retrieveAll(String order) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<OperatoreContentBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_OPERATORE_CON+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_OPERATORE_CON+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        OperatoreContentBean bean = new OperatoreContentBean();
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
	public void insert(OperatoreContentBean operatorecontent) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_OPERATORE_CON+" (email) values (?);";
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, operatorecontent.getEmail());
	      
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
	public void update(OperatoreContentBean operatorecontent) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_OPERATORE_CON+" set email = ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1,operatorecontent.getEmail());
	      
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

		      String sql = "delete from "+Dizionario.TABLE_OPERATORE_CON+" where email = ?;";

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
	public void insert(OperatoreContentBean utente, String pwd) throws SQLException {
		    Connection connection = null;
		    PreparedStatement preparedStatement = null;

		    try {
		      connection = DriverManagerConnectionPool.getConnection();

		      //inserimento dell'utente
		      UtenteDAO utenteDao = new Utente();
		      utenteDao.insert(utente, pwd);

		      //inserimento Operatore_Advertiser
		      String sql = "insert into "+Dizionario.TABLE_OPERATORE_CON+" (email) values (?);";

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


