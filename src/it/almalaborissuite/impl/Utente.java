package it.almalaborissuite.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.services.Dizionario;
import it.almalaborissuite.services.DriverManagerConnectionPool;
import it.almalaborissuite.services.PathComposer;

/**
 * Implementazione di UtenteDAO, che definisce i metodi per gestire i dati
 * relativi agli utenti.
 */
public class Utente implements UtenteDAO {
  
  @Override
  public synchronized UtenteBean retrieveByKey(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    PathComposer pc = new PathComposer();
    
    try {
      connection = DriverManagerConnectionPool.getConnection();
       
      String sql = "select * from "+Dizionario.TABLE_UTENTE+" where email = ?;";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, email);

      rs = preparedStatement.executeQuery();
      
      if (rs.next()) {
        UtenteBean bean = new UtenteBean();
        
        bean.setEmail(rs.getString("email"));
        bean.setRuolo(rs.getString("ruolo"));
        bean.setNome(rs.getString("nome"));
        bean.setCognome(rs.getString("cognome"));
        bean.setTelefono(rs.getString("telefono"));
        bean.setIndirizzo(rs.getString("indirizzo"));
        bean.setCap(rs.getString("cap"));
        bean.setCitta(rs.getString("citta"));
        bean.setProvincia(rs.getString("provincia"));
        
        if(rs.getTimestamp("data_nascita") == null) {
			  bean.setDataNascita(null);
		  } else {
			  bean.setDataNascita(rs.getTimestamp("data_nascita").toLocalDateTime());  
		  }
        
        bean.setDataNascita(null);
        
        //MANCA IL REPERIMENTO DELLE NOTE COME FILE BLOB
        //File note = new File(pc.getPathNote(bean));
        File note = null;
        /*
        try {
			note.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        BufferedOutputStream bos = null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream(note));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		if(rs.getBinaryStream("note") != null) {
	        InputStream is = rs.getBinaryStream("note");
	        byte[] buffer = new byte[256];
	        try {
				while (is.read(buffer) > 0) {
				 bos.write(buffer);
				}
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        */
        bean.setNote(note);
        
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
  public synchronized UtenteBean checkLogin(String email, String pwd) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;

    try {
      connection = DriverManagerConnectionPool.getConnection();

      String sql = "select * from "+Dizionario.TABLE_UTENTE+" where email = ?";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, email);

      rs = preparedStatement.executeQuery();
      String storedPassword = null;
      boolean pwdOK = false;

      // Se l'utente &egrave; iscritto al sito...
      if (rs.next()) {
        storedPassword = rs.getString("pw");
      } else // ...altrimenti non &egrave; iscritto al sito
        return null;

      /*
      // Controllo sulla password inserita...
      try {
        pwdOK = GestorePassword.validatePassword(pwd, storedPassword);
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      } catch (InvalidKeySpecException e) {
        e.printStackTrace();
      }*/
      pwdOK = pwd.equals(storedPassword);

      if (pwdOK) {
        UtenteBean bean = new UtenteBean();

        bean = retrieveByKey(email);

        return bean;
      } else {
        return null;
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
  public synchronized void insert(UtenteBean utente, String pwd) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      
      String pwdSicura = null;
      /*
      try {
        pwdSicura = GestorePassword.generateStrongPasswordHash(pwd);
      } catch (NoSuchAlgorithmException e) {
        e.printStackTrace();
      } catch (InvalidKeySpecException e) {
        e.printStackTrace();
      } */
      
      pwdSicura = pwd;

      String sql = "insert into "+Dizionario.TABLE_UTENTE+" (email, pw, ruolo, nome, cognome, telefono, indirizzo, cap, citta, provincia, data_nascita, "
      		+ "note) values (?, ?, ?, ?, ? ,?, ?, ?, ?, ?, ?, ?);";

      preparedStatement = connection.prepareStatement(sql);
      
      preparedStatement.setString(1, utente.getEmail());
      preparedStatement.setString(2, pwdSicura);
      preparedStatement.setString(3, utente.getRuolo());
      preparedStatement.setString(4, utente.getNome());
      preparedStatement.setString(5, utente.getCognome());
      preparedStatement.setString(6, utente.getTelefono());
      preparedStatement.setString(7, utente.getIndirizzo());
      preparedStatement.setString(8, utente.getCap());
      preparedStatement.setString(9, utente.getCitta());
      preparedStatement.setString(10, utente.getProvincia());
     
      if(utente.getDataNascita() != null)
    	  preparedStatement.setString(11, Dizionario.DATE_FORMAT.format(utente.getDataNascita()).toString());
      else
    	  preparedStatement.setString(11, null);
      
      BufferedInputStream bis = null;
      try {
		bis = new BufferedInputStream(new FileInputStream(utente.getNote()));
      } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
      }
      preparedStatement.setBinaryStream(12, bis);

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
  public synchronized void update(UtenteBean utente) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = DriverManagerConnectionPool.getConnection();
      
      String sql = "update "+Dizionario.TABLE_UTENTE+" set ruolo = ?, nome = ?, cognome = ?, telefono = ?, indirizzo = ?, cap = ?, citta = ?, "
      		+ "provincia = ?, data_nascita = ?, note = ? where email = ?;";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, utente.getRuolo());
      preparedStatement.setString(2, utente.getNome());
      preparedStatement.setString(3, utente.getCognome());
      preparedStatement.setString(4, utente.getTelefono());
      preparedStatement.setString(5, utente.getIndirizzo());
      preparedStatement.setString(6, utente.getCap());
      preparedStatement.setString(7, utente.getCitta());
      preparedStatement.setString(8, utente.getProvincia());
      preparedStatement.setString(9, Dizionario.DATE_FORMAT.format(utente.getDataNascita()).toString());
      BufferedInputStream bis = null;
      try {
		bis = new BufferedInputStream(new FileInputStream(utente.getNote()));
      } catch (FileNotFoundException e) {
		e.printStackTrace();
      }
      preparedStatement.setBinaryStream(10, bis);
      preparedStatement.setString(11, utente.getEmail());

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
  public synchronized boolean delete(String email) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      connection = DriverManagerConnectionPool.getConnection();

      String sql = "delete from "+Dizionario.TABLE_UTENTE+" where email = ?;";

      preparedStatement = connection.prepareStatement(sql);

      preparedStatement.setString(1, email);

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
  public synchronized Collection<UtenteBean> retrieveAll(String order) throws SQLException {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet rs = null;
    Collection<UtenteBean> list = new ArrayList<>();

    try {
      connection = DriverManagerConnectionPool.getConnection();
      String sql = null;
      if (order == null || order.equals("")) {
        sql = "select * from "+Dizionario.TABLE_UTENTE+";";
      } else {
        sql = "select * from "+Dizionario.TABLE_UTENTE+" order by " + order + ";";
      }

      preparedStatement = connection.prepareStatement(sql);

      rs = preparedStatement.executeQuery();

      while (rs.next()) {
        UtenteBean bean = new UtenteBean();

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
  public void insert(UtenteBean object) throws SQLException {
  }

  @Override
  public void changeRuoloOperatore(UtenteBean utente, String newRuolo) throws SQLException {
	  Connection con = null;
	  PreparedStatement ps = null;
	  String oldRuolo = utente.getRuolo();
	  String updateUserQuery = new String();
	  String insertUserQuery = new String();
	  String deleteUserQuery = new String();
	  String tabDaPulire = new String();
	  
	  try {
		  con = DriverManagerConnectionPool.getConnection();
		  
		  //AGGIORNO IL RUOLO DELL'UTENTE SPECIFICATO NELLA TABELLA ALS_UTENTE
		  updateUserQuery = "UPDATE "+Dizionario.TABLE_UTENTE+" SET ruolo = ? WHERE email = ?;";
		  
		  ps = con.prepareStatement(updateUserQuery);
		  
		  ps.setString(1, newRuolo);
		  ps.setString(2, utente.getEmail());
		  
		  ps.executeUpdate();
		  
		  //CONTROLLO IL VECCHIO RUOLO DELL'UTENTE SPECIFICATO E RECUPERO I DATI AGGIUNTIVI ASSOCIATI
		  if(oldRuolo.equals("DATAENTRY")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_DAT;
		  } else if (oldRuolo.equals("FRONTOFFICE")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_FRO;
		  } else if (oldRuolo.equals("SEGRETERIA")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_SEG;
		  } else if (oldRuolo.equals("SELEZIONATORE")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_SEL;
		  } else if (oldRuolo.equals("CONTENT")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_CON;
		  } else if (oldRuolo.equals("ADVERTISER")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_ADV;
		  } else if (oldRuolo.equals("TECNICO")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_TEC;
		  } else if (oldRuolo.equals("DIREZIONE")) {
			  tabDaPulire = Dizionario.TABLE_OPERATORE_DIR;
		  }

		  //CANCELLO LA TUPLA DEL VECCHIO RUOLO RELATIVO ALL'UTENTE SPECIFICATO
		  if(!tabDaPulire.equals(Dizionario.TABLE_CANDIDATO)) {
			  deleteUserQuery = "DELETE FROM "+tabDaPulire+" WHERE email = ?;";
		  }
		  
		  ps = con.prepareStatement(deleteUserQuery);
		  
		  ps.setString(1, utente.getEmail());
		  
		  ps.executeQuery();
		  		  
		  //INSERIMENTO DEL VECCHIO UTENTE NELLA TABELLA CORRISPONDENTE AL NUOVO RUOLO
		  if(newRuolo.equals("DATAENTRY")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_DAT+"(email) VALUES (?);";
		  } else if (newRuolo.equals("FRONTOFFICE")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_FRO+"(email) VALUES (?);";
		  } else if (newRuolo.equals("SEGRETERIA")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_SEG+"(email) VALUES (?);";
		  } else if (newRuolo.equals("SELEZIONATORE")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_SEL+"(email) VALUES (?);";
		  } else if (newRuolo.equals("CONTENT")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_CON+"(email) VALUES (?);";
		  } else if (newRuolo.equals("ADVERTISER")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_ADV+"(email) VALUES (?);";
		  } else if (newRuolo.equals("TECNICO")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_TEC+"(email) VALUES (?);";
		  } else if (newRuolo.equals("DIREZIONE")) {
			  insertUserQuery = "INSERT INTO "+Dizionario.TABLE_OPERATORE_DIR+"(email) VALUES (?);";
		  }
		  
		  ps = con.prepareStatement(insertUserQuery);
		  
		  ps.setString(1, utente.getEmail());
		  
		  ps.executeQuery();
		  
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
	public void changeCandidatoToPax(CandidatoBean candidato) throws SQLException {
		 /* PER CANDIDATO VA PREVISO UN AGGIORNAMENTO SPECIALE 
		   * Va svuotata la tabella als_candidato di tutte le tuple corrispondenti alla mail dell'utente specificato
		   * poi va aggiunta una tupla in partecipante prestando attenzione ad assegnare al candidato la giusta edizione
		   * (OCCORRE PREVEDERE UNA MODIFICA PER LO STATO, UNO PER OGNI MASTER POICHè ALTRIMENTI NON SAPPIAMO A QUALE EDIZIONE
		   *  SPECIFICA ASSEGNARE IL CANDIDATO QUANDO DIVENTA PARTECIPANTE) */		
	}
  
}
