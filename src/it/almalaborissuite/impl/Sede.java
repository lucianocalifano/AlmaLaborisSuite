package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.SedeBean;
import it.almalaborissuite.dao.SedeDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Sede implements SedeDAO{
	
	@Override
	public SedeBean retrieveByKey(Integer key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			String query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" WHERE id = ?;";
			
			ps = con.prepareStatement(query);
			
			ps.setInt(1, key);
			
			rs = ps.executeQuery();
			if(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				return sede;
			}else
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
	public Collection<SedeBean> retrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<SedeBean> listSede = new ArrayList<>();
				
		try {
			con = DriverManagerConnectionPool.getConnection();
			String query = null;
			
			if(order == null || order.equals("")) {
				query = "SELECT * FROM "+Dizionario.TABLE_SEDE+";";
			} else {
				query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" ORDER BY "+order+";";
			}
		
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				listSede.add(sede);
			}
			
			return listSede;
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
	public void insert(SedeBean sede) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO "+Dizionario.TABLE_SEDE+"(nome, riferimento, indirizzo, cap, citta) VALUES (?, ?, ?, ?, ?);";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);

			ps.setString(1, sede.getNome());
			ps.setString(2, sede.getRiferimento());
			ps.setString(3, sede.getIndirizzo());
			ps.setString(4, sede.getCap());
			ps.setString(5, sede.getCitta());
			
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
	public void update(SedeBean sede) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		String query = "UPDATE "+Dizionario.TABLE_SEDE+" SET nome = ?, riferimento = ?, indirizzo = ?, cap = ?, citta = ? WHERE id = ?";
		
		try {
			
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, sede.getNome());
			ps.setString(2, sede.getRiferimento());
			ps.setString(3, sede.getIndirizzo());
			ps.setString(4, sede.getCap());
			ps.setString(5, sede.getCitta());
			ps.setInt(6, sede.getIdentificativo());
			
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
	public boolean delete(Integer key) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		String query = "DELETE FROM "+Dizionario.TABLE_SEDE+" WHERE id = ?;";

		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setInt(1, key);
			
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

 	@Override
	public Collection<SedeBean> getSediPerNome(String nome) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		nome = "%"+nome+"%";
		
		Collection<SedeBean> listSedi = new ArrayList<>();
		
		String query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" WHERE nome LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, nome);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				listSedi.add(sede);
			}
			
			return listSedi;
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
	public Collection<SedeBean> getSediPerRiferimento(String riferimento) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		riferimento = "%"+riferimento+"%";
		
		Collection<SedeBean> listSedi = new ArrayList<>();
		String query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" WHERE riferimento LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, riferimento);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				listSedi.add(sede);
			}
			
			return listSedi;
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
	public Collection<SedeBean> getSediPerIndirizzo(String indirizzo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		indirizzo = "%"+indirizzo+"%";
		
		Collection<SedeBean> listSedi = new ArrayList<>();
		String query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" WHERE indirizzo LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, indirizzo);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				listSedi.add(sede);
			}
			
			return listSedi;
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
	public Collection<SedeBean> getSediPerCap(String cap) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		cap = "%"+cap+"%";
		
		Collection<SedeBean> listSedi = new ArrayList<>();
		String query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" WHERE cap LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, cap);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				listSedi.add(sede);
			}
			
			return listSedi;
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
	public Collection<SedeBean> getSediPerCitta(String citta) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		citta = "%"+citta+"%";
		
		Collection<SedeBean> listSedi = new ArrayList<SedeBean>();
		
		String query = "SELECT * FROM "+Dizionario.TABLE_SEDE+" WHERE citta LIKE ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			ps.setString(1, citta);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SedeBean sede = new SedeBean();
				
				sede.setIdentificativo(rs.getInt("id"));
				sede.setNome(rs.getString("nome"));
				sede.setRiferimento(rs.getString("riferimento"));
				sede.setIndirizzo(rs.getString("indirizzo"));
				sede.setCap(rs.getString("cap"));
				sede.setCitta(rs.getString("citta"));
				
				listSedi.add(sede);
			}
			
			return listSedi;
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
