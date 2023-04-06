package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.AulaBean;
import it.almalaborissuite.bean.EdizioneBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.dao.AulaDAO;
import it.almalaborissuite.dao.EdizioneDAO;
import it.almalaborissuite.dao.MasterDAO;
import it.almalaborissuite.dao.SettoreDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Edizione implements EdizioneDAO {	
	
	@Override
	public EdizioneBean retrieveByKey(Integer key) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet rs = null;
	    try {
	        connection = DriverManagerConnectionPool.getConnection();
	        String sql = "select * from "+Dizionario.TABLE_EDIZIONE+" where id = ?;";

	        preparedStatement = connection.prepareStatement(sql);

	        preparedStatement.setInt(1, key);

	        rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	          EdizioneBean bean = new EdizioneBean();
	          bean.setId(rs.getInt("id"));
	          bean.setNome(rs.getString("nome"));
	          bean.setDataInvioScheda(LocalDateTime.parse(rs.getString("data_invio_scheda"), Dizionario.DATE_FORMAT));
			  bean.setDataInizio(LocalDateTime.parse(rs.getString("data_inizio"), Dizionario.DATE_FORMAT));
			  bean.setDataFine(LocalDateTime.parse(rs.getString("data_fine"), Dizionario.DATE_FORMAT));
			  bean.setInizioVf(LocalDateTime.parse(rs.getString("inizio_vf"), Dizionario.DATE_FORMAT));
			  bean.setFineVf(LocalDateTime.parse(rs.getString("fine_vf"), Dizionario.DATE_FORMAT));
	          
	          //Recuperiamo i dati relativi al master
	          MasterDAO masterDao = new Master();
	          MasterBean masterBean = masterDao.retrieveByKey(rs.getInt("id"));
	          //Recuperiamo i dati relativi all'aula
	          AulaDAO aulaDao= new Aula();
	          AulaBean aulabean= aulaDao.retrieveByKey(rs.getInt("id_aula"));	
	          
	          bean.setMaster(masterBean);
	          bean.setAula(aulabean);
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
	public Collection<EdizioneBean> retrieveAll(String order) throws SQLException {
		 Connection connection = null;
		 PreparedStatement preparedStatement = null;
		 ResultSet rs = null;
		 Collection<EdizioneBean> list = new ArrayList<>();
		 try {
		      connection = DriverManagerConnectionPool.getConnection();
		      String sql = null;
		      if (order == null || order.equals("")) {
		        sql = "select * from "+Dizionario.TABLE_EDIZIONE+";";
		      } else {
		        sql = "select * from "+Dizionario.TABLE_EDIZIONE+" order by " + order + ";";
		      }

		      preparedStatement = connection.prepareStatement(sql);
		      rs = preparedStatement.executeQuery();

		      while (rs.next()) {
		        EdizioneBean bean = new EdizioneBean();
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
	public void insert(EdizioneBean edizione) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    String sql = "insert into "+Dizionario.TABLE_EDIZIONE+" (id, nome, data_invio_scheda, data_inizio, data_fine, inizio_vf, fine_vf, id_master, id_aula) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setInt(1,edizione.getId());
	      preparedStatement.setString(2,edizione.getNome());
	      preparedStatement.setString(3, Dizionario.DATE_FORMAT.format(edizione.getDataInvioScheda()).toString());
	      preparedStatement.setString(4, Dizionario.DATE_FORMAT.format(edizione.getDataInizio()).toString());
	      preparedStatement.setString(5, Dizionario.DATE_FORMAT.format(edizione.getDataFine()).toString());
	      preparedStatement.setString(6, Dizionario.DATE_FORMAT.format(edizione.getInizioVf()).toString());
	      preparedStatement.setString(7, Dizionario.DATE_FORMAT.format(edizione.getFineVf()).toString());
	      preparedStatement.setInt(8, edizione.getMaster().getId());
	      preparedStatement.setInt(9, edizione.getAula().getId());
	      
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
	public void update(EdizioneBean edizione) throws SQLException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    
	    try {
	      connection = DriverManagerConnectionPool.getConnection();

	      String sql = "update "+Dizionario.TABLE_EDIZIONE+" "
	      		+ "set nome = ?, data_invio_scheda = ?, data_inizio = ?, data_fine = ?, inizio_vf = ?, fine_vf = ?, id_master = ?, id_aula = ? "
	      		+ "WHERE id = ?;";

	      System.out.println(sql);

	      preparedStatement = connection.prepareStatement(sql);
	      connection = DriverManagerConnectionPool.getConnection();
	      preparedStatement = connection.prepareStatement(sql);
	      preparedStatement.setString(1,edizione.getNome());
	      preparedStatement.setString(2, Dizionario.DATE_FORMAT.format(edizione.getDataInvioScheda()).toString());
	      preparedStatement.setString(3, Dizionario.DATE_FORMAT.format(edizione.getDataInizio()).toString());
	      preparedStatement.setString(4, Dizionario.DATE_FORMAT.format(edizione.getDataFine()).toString());
	      preparedStatement.setString(5, Dizionario.DATE_FORMAT.format(edizione.getInizioVf()).toString());
	      preparedStatement.setString(6, Dizionario.DATE_FORMAT.format(edizione.getFineVf()).toString());
	      preparedStatement.setInt(7, edizione.getMaster().getId());
	      preparedStatement.setInt(8, edizione.getAula().getId());
	      
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

		      String sql = "delete from "+Dizionario.TABLE_EDIZIONE+" where id = ?;";

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
	public Collection<EdizioneBean> getEdizioniByName(String nome) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Collection<EdizioneBean> listEdizioni = new ArrayList<EdizioneBean>();
		
		String query = "SELECT * FROM als_edizione AS e WHERE e.nome = ?";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, nome);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				EdizioneBean edizione = new EdizioneBean();
				
				edizione.setId(rs.getInt("id"));
				edizione.setNome(rs.getString("nome"));
				edizione.setDataInizio(rs.getTimestamp("data_inizio").toLocalDateTime());
				edizione.setDataInvioScheda(LocalDateTime.parse(rs.getString("data_invio_scheda"), Dizionario.DATE_FORMAT));
				edizione.setDataFine(LocalDateTime.parse(rs.getString("data_fine"), Dizionario.DATE_FORMAT));
				edizione.setInizioVf(LocalDateTime.parse(rs.getString("inizio_vf"), Dizionario.DATE_FORMAT));
				edizione.setFineVf(LocalDateTime.parse(rs.getString("fine_vf"), Dizionario.DATE_FORMAT));
				
				MasterDAO masterDao = new Master();
				edizione.setMaster(masterDao.retrieveByKey(rs.getInt("id_master")));
				
				AulaDAO aulaDao = new Aula();
				edizione.setAula(aulaDao.retrieveByKey(rs.getInt("id_aula")));
				
				listEdizioni.add(edizione);
			}
			
			return listEdizioni;
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
	public Collection<EdizioneBean> getEdizioni() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<EdizioneBean> listEdizione = new ArrayList<>();
		String query = "SELECT distinct ed.nome FROM als_edizione as ed;";
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				EdizioneBean edizione = new EdizioneBean();
				edizione.setNome(rs.getString("nome"));
				
				listEdizione.add(edizione);
			}
			return listEdizione;
			
		} finally {
			try {
				if(!con.isClosed())
					con.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(con);
			}
		}
	}
	
	public EdizioneBean getEdizioneByNomeEMaster(String nomeEdizione, String nomeMaster) throws SQLException{
		Connection con = null;
		PreparedStatement psM = null;
		ResultSet rsM = null;
		MasterBean mBean = new MasterBean();
		
		PreparedStatement psE = null;
		ResultSet rsE = null;
		EdizioneBean eBean = new EdizioneBean();
		
		nomeMaster = "%"+nomeMaster+"%";
		
		String searchMas =  "SELECT * "
							+ "FROM "+Dizionario.TABLE_MASTER+" AS m "
							+ "WHERE m.nome LIKE ?;";
		
		nomeEdizione = "%"+nomeEdizione+"%";
		
		String searchEdi = "SELECT * "
							+ "FROM "+Dizionario.TABLE_EDIZIONE+" AS e "
							+ "INNER JOIN "+Dizionario.TABLE_MASTER+" AS m ON m.id = e.id_master "						
							+ "WHERE e.nome LIKE ? AND m.id = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			psM = con.prepareStatement(searchMas);
			psM.setString(1, nomeMaster);
			
			rsM = psM.executeQuery();
			
			if(rsM.next()) {
				
				mBean.setId(rsM.getInt("id"));
				mBean.setNome(rsM.getString("nome"));
				mBean.setTag(rsM.getString("tag"));
				
				SettoreDAO settoreDao = new Settore();
				mBean.setSettore(settoreDao.retrieveByKey(rsM.getInt("id_settore")));
				
			}
			
			psE = con.prepareStatement(searchEdi);
			
			psE.setString(1, nomeEdizione);
			psE.setInt(2, mBean.getId());
			
			rsE = psE.executeQuery();
			
			if(rsE.next()) {
				
				eBean.setId(rsE.getInt("id"));
				eBean.setNome(rsE.getString("nome"));
				eBean.setDataInvioScheda(LocalDateTime.parse(rsE.getString("data_invio_scheda"), Dizionario.DATE_FORMAT));
				eBean.setDataInizio(LocalDateTime.parse(rsE.getString("data_inizio"), Dizionario.DATE_FORMAT));
				eBean.setDataFine(LocalDateTime.parse(rsE.getString("data_fine"), Dizionario.DATE_FORMAT));
				eBean.setInizioVf(LocalDateTime.parse(rsE.getString("inizio_vf"), Dizionario.DATE_FORMAT));
				eBean.setFineVf(LocalDateTime.parse(rsE.getString("fine_vf"), Dizionario.DATE_FORMAT));
				
				MasterDAO masterDao = new Master();
				eBean.setMaster(masterDao.retrieveByKey(rsE.getInt("id_master")));
				
				AulaDAO aulaDao = new Aula();
				eBean.setAula(aulaDao.retrieveByKey(rsE.getInt("id_aula")));
				
			}

			return eBean;
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
