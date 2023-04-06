package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.AulaBean;
import it.almalaborissuite.bean.SedeBean;
import it.almalaborissuite.dao.AulaDAO;
import it.almalaborissuite.dao.SedeDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Aula implements AulaDAO {

	@Override
	public AulaBean retrieveByKey(Integer key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select * from "+Dizionario.TABLE_AULA+" where id = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          AulaBean bean = new AulaBean();
	          bean.setId(rs.getInt("id"));
	          bean.setNome(rs.getString("nome"));
	          bean.setPiano(rs.getString("piano"));
	          
	          //Recuperiamo i dati relativi alla sede
	          SedeDAO sedeDao = new Sede();
	          SedeBean sedeBean = sedeDao.retrieveByKey(rs.getInt("id_sede"));
	         
	          bean.setSede(sedeBean);
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
	public Collection<AulaBean> retrieveAll(String order) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<AulaBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_AULA+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_AULA+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        AulaBean bean = new AulaBean();
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
	public void insert(AulaBean aula) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_AULA+" (nome, piano, id_sede) values (?, ?, ?);";
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1, aula.getNome());
	      preparedStatement.setString(2, aula.getPiano());
	      preparedStatement.setInt(3, aula.getSede().getIdentificativo());
	      
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
	public void update(AulaBean aula) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_AULA+" set nome = ?, piano = ?, id_sede= ?"
		      		+ " WHERE id = ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);
	      
	      preparedStatement.setString(1, aula.getNome());
	      preparedStatement.setString(2, aula.getPiano());
	      preparedStatement.setInt(3, aula.getSede().getIdentificativo());
	      preparedStatement.setInt(4, aula.getId());
	      

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
	public boolean delete(Integer id) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;

		    try {
		      connection = DriverManagerConnectionPool.getConnection();

		      String sql = "delete from "+Dizionario.TABLE_AULA+" where id = ?;";

		      preparedStatement = connection.prepareStatement(sql);

		      preparedStatement.setInt(1, id);

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

