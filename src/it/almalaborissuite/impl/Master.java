package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.SettoreBean;
import it.almalaborissuite.dao.MasterDAO;

import it.almalaborissuite.dao.SettoreDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Master implements MasterDAO {
	
	@Override
	public MasterBean retrieveByKey(Integer key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select * from "+Dizionario.TABLE_MASTER+" where id = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          MasterBean bean = new MasterBean();
	          bean.setId(rs.getInt("id"));
	          bean.setNome(rs.getString("nome"));
	          bean.setTag(rs.getString("tag"));
	        
	          //Recuperiamo i dati relativi al settore del master
	          SettoreDAO settoreDao = new Settore();
	          SettoreBean settoreBean = settoreDao.retrieveByKey(rs.getInt("id_settore"));
	        
	          bean.setSettore(settoreBean);
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
	public Collection<MasterBean> retrieveAll(String order) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<MasterBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_MASTER+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_MASTER+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        MasterBean bean = new MasterBean();
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
	public void insert(MasterBean master) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_MASTER+" (id, nome, tag, id_settore) values (?, ?, ?, ?);";
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setInt(1,master.getId());
	      preparedStatement.setString(2,master.getNome());
	      preparedStatement.setString(3,master.getTag());
	      preparedStatement.setInt(4,master.getSettore().getIdentificativo());
	      
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
	public void update(MasterBean master) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_MASTER+" set nome = ?, tag = ?, id_settore = ? WHERE id= ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);

	      preparedStatement.setString(1,master.getNome());
	      preparedStatement.setString(2,master.getTag());
	      preparedStatement.setInt(3,master.getSettore().getIdentificativo());
	      preparedStatement.setInt(4,master.getId());
	      
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

		      String sql = "delete from "+Dizionario.TABLE_MASTER+" where id = ?;";

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
	public Collection<MasterBean> getMastersPerSettore(SettoreBean settore) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection<MasterBean> listMaster = new ArrayList<>();
		String query = "select * from "+Dizionario.TABLE_MASTER+" where id_settore like "+settore.getIdentificativo()+";";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				MasterBean master = new MasterBean();
				SettoreDAO settoreDao = new Settore();
				master.setId(rs.getInt("id"));
				master.setNome(rs.getString("nome"));
				master.setTag(rs.getString("tag"));
				master.setSettore(settoreDao.retrieveByKey(rs.getInt("id_settore")));
				
				listMaster.add(master);
			}
			
			return listMaster;
			
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
	public Collection<MasterBean> getMastersPerEdizione(String nomeEdizione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection<MasterBean> listMaster = new ArrayList<>();
		String query = "SELECT m.id, m.nome, m.tag, m.id_settore FROM als_master as m\r\n"
				+ "INNER JOIN als_edizione as ed ON m.id = ed.id_master where ed.nome = ?;";
		
		try {
					con = DriverManagerConnectionPool.getConnection();
					ps = con.prepareStatement(query);
					ps.setString(1, nomeEdizione);
					rs = ps.executeQuery();
					
					while(rs.next()) {
						MasterBean master = new MasterBean();
						SettoreDAO settoreDao = new Settore();
						master.setId(rs.getInt("id"));
						master.setNome(rs.getString("nome"));
						master.setTag(rs.getString("tag"));
						master.setSettore(settoreDao.retrieveByKey(rs.getInt("id_settore")));
						
						listMaster.add(master);
					}
					
					return listMaster;
					
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
	public Collection<MasterBean> getMastersPerCandidato(CandidatoBean candidato) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		Collection<MasterBean> listMaster = new ArrayList<>();
		String query = "SELECT m.id, m.nome, m.tag, m.id_settore\r\n"
				+ "FROM als_master as m\r\n"
				+ "INNER JOIN als_candidato  as c ON m.id = c.id_master where c.email = ?;";
		
		try {
					con = DriverManagerConnectionPool.getConnection();
					
					ps = con.prepareStatement(query);
					ps.setString(1, candidato.getEmail());
					rs = ps.executeQuery();
					
					while(rs.next()) {
						MasterBean master = new MasterBean();
						SettoreDAO settoreDao = new Settore();
						master.setId(rs.getInt("id"));
						master.setNome(rs.getString("nome"));
						master.setTag(rs.getString("tag"));
						master.setSettore(settoreDao.retrieveByKey(rs.getInt("id_settore")));
						
						listMaster.add(master);
					}
					
					return listMaster;
					
				} finally {
					try {
						if(!con.isClosed())
							con.close();
					} finally {
						DriverManagerConnectionPool.releaseConnection(con);
					}
				}
		}

	//da rivedere
	@Override
	public MasterBean getMasterPerNome(String nome) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		nome = "%"+nome+"%";
		
		String query = "select * from "+Dizionario.TABLE_MASTER+" where nome LIKE ?";
		
		try {
					con = DriverManagerConnectionPool.getConnection();
					ps = con.prepareStatement(query);
					ps.setString(1, nome);
					rs = ps.executeQuery();
					MasterBean master = new MasterBean();
					SettoreDAO settoreDao = new Settore();
					
					while(rs.next()) {
						master.setId(rs.getInt("id"));
						master.setNome(rs.getString("nome"));
						master.setTag(rs.getString("tag"));
						master.setSettore(settoreDao.retrieveByKey(rs.getInt("id_settore")));
					}
		
					return master;
					
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
