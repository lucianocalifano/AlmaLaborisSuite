package it.almalaborissuite.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.SettoreBean;
import it.almalaborissuite.dao.SettoreDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;

public class Settore implements SettoreDAO{
	
	@Override
	public SettoreBean retrieveByKey(Integer key) throws SQLException {
		Connection con =  null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			String query = "SELECT * FROM "+Dizionario.TABLE_SETTORE+" WHERE id = ?;";
			
			ps = con.prepareStatement(query);
			
			ps.setInt(1, key);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				SettoreBean settore = new SettoreBean();
				
				settore.setIdentificativo(rs.getInt("id"));
				settore.setNome(rs.getString("nome"));
				
				return settore;
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
	public Collection<SettoreBean> retrieveAll(String order) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Collection<SettoreBean> listSettori = new ArrayList<>();
			
		try {
			con = DriverManagerConnectionPool.getConnection();
			String query = null;
			
			if(order == null || order.equals("")) {
				query = "SELECT * FROM "+Dizionario.TABLE_SETTORE+";";
			} else {
				query = "SELECT * FROM "+Dizionario.TABLE_SETTORE+" ORDER BY "+order+";";
			}
			
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				SettoreBean settore = new SettoreBean();
				
				settore.setIdentificativo(rs.getInt("id"));
				settore.setNome(rs.getString("nome"));
				
				listSettori.add(settore);
			}
			
			return listSettori;
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
	public void insert(SettoreBean settore) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "INSERT INTO "+Dizionario.TABLE_SETTORE+" VALUES (?, ?);";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setInt(1, settore.getIdentificativo());
			ps.setString(2, settore.getNome());
			
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
	public void update(SettoreBean settore) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String query = "UPDATE "+Dizionario.TABLE_SETTORE+" SET nome = ? WHERE id = ?;";
		
		try {
			con = DriverManagerConnectionPool.getConnection();
			
			ps = con.prepareStatement(query);
			
			ps.setString(1, settore.getNome());
			ps.setInt(2, settore.getIdentificativo());
			
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
		
		String query = "DELETE FROM "+Dizionario.TABLE_SETTORE+" WHERE id = ?;";
		
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
	
}
