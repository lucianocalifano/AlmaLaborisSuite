package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;


import it.almalaborissuite.bean.ColloquioBean;
import it.almalaborissuite.bean.SedeBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.ColloquioDAO;
import it.almalaborissuite.dao.SedeDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Colloquio implements ColloquioDAO {

	@Override
	public ColloquioBean retrieveByKey(Integer key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select * from "+Dizionario.TABLE_COLLOQUIO+" where id = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          ColloquioBean bean = new ColloquioBean();
	          //Recuperiamo i dati relativi all'Utente
	          UtenteDAO utenteDao = new Utente();
	          UtenteBean utenteBean = utenteDao.retrieveByKey(rs.getString("utente"));
	          //Recuperiamo i dati relativi alla sede
	          SedeDAO sedeDao = new Sede();
	          SedeBean sedeBean = sedeDao.retrieveByKey(rs.getInt("id_sede"));
	          
	          bean.setId(rs.getInt("id"));
	          bean.setUtente(utenteBean);
	          bean.setData_inzio(LocalDateTime.parse(rs.getString("data_inizio"), Dizionario.DATE_FORMAT));
	          bean.setData_fine(LocalDateTime.parse(rs.getString("data_fine"), Dizionario.DATE_FORMAT));
	          bean.setTipo(rs.getString("tipo"));
	          bean.setSedeColloquio(sedeBean);
	          
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
	public Collection<ColloquioBean> retrieveAll(String order) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<ColloquioBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_COLLOQUIO+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_COLLOQUIO+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        ColloquioBean bean = new ColloquioBean();
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
	public void insert(ColloquioBean colloquio) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_COLLOQUIO+" (id, utente , data_inizio , data_fine, tipo, id_sede) values (?, ?, ?, ?, ?, ?);";
	    
	    try {
	    	connection = DriverManagerConnectionPool.getConnection();
		      preparedStatement = connection.prepareStatement(sql);
		      preparedStatement.setInt(1, colloquio.getId());
		      preparedStatement.setString(2, colloquio.getUtente().getEmail());
		      preparedStatement.setString(3, Dizionario.DATE_FORMAT.format(colloquio.getData_inzio()).toString());
		      preparedStatement.setString(4, Dizionario.DATE_FORMAT.format(colloquio.getData_fine()).toString());
		      preparedStatement.setString(5, colloquio.getTipo());
		      preparedStatement.setInt(6, colloquio.getSedeColloquio().getIdentificativo());
	      
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
	public void update(ColloquioBean colloquio) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_COLLOQUIO+" set data_inizio = ?, data_fine = ?, tipo = ?, id_sede = ? WHERE utente = ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1, Dizionario.DATE_FORMAT.format(colloquio.getData_inzio()).toString());
	      preparedStatement.setString(2, Dizionario.DATE_FORMAT.format(colloquio.getData_fine()).toString());
	      preparedStatement.setString(3, colloquio.getTipo());
	      preparedStatement.setInt(4, colloquio.getSedeColloquio().getIdentificativo());
	      preparedStatement.setString(5, colloquio.getUtente().getEmail());
	      

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

		      String sql = "delete from "+Dizionario.TABLE_COLLOQUIO+" where id = ?;";

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

	@Override
	public Collection<ColloquioBean> getColloquiPerData(String dataColloqui) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<ColloquioBean> listColloqui = new ArrayList<ColloquioBean>();
		
		String query = "select * from "+Dizionario.TABLE_COLLOQUIO+" where data_inizio = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, dataColloqui);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ColloquioBean colloquio = new ColloquioBean();
				UtenteDAO utenteDao = new Utente();
				SedeDAO sedeDao = new Sede();				
				
				colloquio.setId(rs.getInt("id"));
				colloquio.setUtente(utenteDao.retrieveByKey(rs.getString("utente")));
				colloquio.setData_inzio(LocalDateTime.parse(rs.getString("data_inizio"), Dizionario.DATE_FORMAT));
				colloquio.setData_fine(LocalDateTime.parse(rs.getString("data_fine"), Dizionario.DATE_FORMAT));
				colloquio.setTipo(rs.getString("tipo"));
				colloquio.setSedeColloquio(sedeDao.retrieveByKey(rs.getInt("id_sede")));
				
				listColloqui.add(colloquio);
			}
			
			return listColloqui;
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


