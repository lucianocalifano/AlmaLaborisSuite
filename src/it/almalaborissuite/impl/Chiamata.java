package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.ChiamataBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.ChiamataDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Chiamata implements ChiamataDAO {

	@Override
	public ChiamataBean retrieveByKey(Integer key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select * from "+Dizionario.TABLE_CHIAMATA+" where id = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          ChiamataBean bean = new ChiamataBean();
	          //Recuperiamo i dati relativi all'Utente
	          UtenteDAO utenteDao = new Utente();
	          UtenteBean utenteBeanutente = utenteDao.retrieveByKey(rs.getString("utente"));
	          UtenteBean utenteBeanoperatore = utenteDao.retrieveByKey(rs.getString("operatore"));
	          bean.setId(rs.getInt("id"));
	          bean.setUtente(utenteBeanutente);
	          bean.setUtente(utenteBeanoperatore);
	          bean.setData_chiamata(LocalDateTime.parse(rs.getString("data_chiamata"), Dizionario.DATE_FORMAT));
	          bean.setMotivo(rs.getString("motivo"));
	          bean.setTipo(rs.getString("tipo"));
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
	public Collection<ChiamataBean> retrieveAll(String order) throws SQLException {
		Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<ChiamataBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_CHIAMATA+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_CHIAMATA+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        ChiamataBean bean = new ChiamataBean();
		        Integer id = rs.getInt("id");
		        bean = retrieveByKey(id);
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
	public void insert(ChiamataBean chiamata) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_CHIAMATA+" (id, utente , operatore, data_chiamata , motivo, tipo) values (?, ?, ?, ?, ?, ?);";
	    
	    try {
	    	connection = DriverManagerConnectionPool.getConnection();
		      preparedStatement = connection.prepareStatement(sql);
		      preparedStatement.setInt(1, chiamata.getId());
		      preparedStatement.setString(2, chiamata.getUtente().getEmail());
		      preparedStatement.setString(3, chiamata.getUtente().getEmail());
		      preparedStatement.setString(4, chiamata.getData_chiamata().toString());
		      preparedStatement.setString(5, chiamata.getMotivo());
		      preparedStatement.setString(6, chiamata.getTipo());
	      
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
	public void update(ChiamataBean chiamata) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_CHIAMATA+" set utente = ?, operatore = ?, data_chiamata = ?, motivo = ?, tipo= ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1, chiamata.getUtente().getEmail());
	      preparedStatement.setString(2, chiamata.getUtente().getEmail());
	      preparedStatement.setString(3, chiamata.getData_chiamata().toString());
	      preparedStatement.setString(4, chiamata.getMotivo());
	      preparedStatement.setString(5, chiamata.getTipo());
	      

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
	public boolean delete(Integer key) throws SQLException {
		Connection connection = null;
		 PreparedStatement preparedStatement = null;

		    try {
		      connection = DriverManagerConnectionPool.getConnection();

		      String sql = "delete from "+Dizionario.TABLE_CHIAMATA+" where id = ?;";

		      preparedStatement = connection.prepareStatement(sql);

		      preparedStatement.setInt(1, key);

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
	}
