package it.almalaborissuite.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DriverManagerConnectionPool  {

  private static List<Connection> freeDbConnections;

  static {
    freeDbConnections = new LinkedList<Connection>();
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("DB driver not found:"+ e.getMessage());
    } 
  }

  private static synchronized Connection createDBConnection() throws SQLException {
    Connection newConnection = null;
    String ip = "localhost";
    String port = "3308";
    String db = "almalaborissuite";
    String username = "root";
    String password = "alma2020";

    newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip + ":" + port + "/" + db +
        "?zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=UTF-8&characterSetResults=UTF-8&useSSL=false", 
        username, password);

    newConnection.setAutoCommit(true);
    return newConnection;
  }


  public static synchronized Connection getConnection() throws SQLException {
    Connection connection;

    if (!freeDbConnections.isEmpty()) {
      connection = (Connection) freeDbConnections.get(0);
      freeDbConnections.remove(0);

      try {
        if (connection.isClosed())
          connection = getConnection();
      } catch (SQLException e) {
        connection.close();
        connection = getConnection();
      }
    } else {
      connection = createDBConnection();		
    }

    return connection;
  }

  public static synchronized void releaseConnection(Connection connection) throws SQLException {
    if(connection != null) freeDbConnections.add(connection);
  }
}
