package it.almalaborissuite.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WriteExcel {
		
		public WriteExcel() { }
	
		public void getUtentiExcel(String pathfile) throws FileNotFoundException, IOException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    PathComposer path = new PathComposer();
        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
        Workbook writeWorkbook = new HSSFWorkbook();
        Sheet desSheet = writeWorkbook.createSheet("new sheet");
        ResultSet rs = null;
        try{
        	connection = DriverManagerConnectionPool.getConnection();
            String query ="SELECT * FROM als_utente";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            Row desRow1 = desSheet.createRow(0);
            for(int col=0 ;col < columnsNumber;col++) {
                Cell newpath = desRow1.createCell(col);
                newpath.setCellValue(rsmd.getColumnLabel(col+1));
            }
            while(rs.next()) {
                System.out.println("Row number" + rs.getRow() );
                Row desRow = desSheet.createRow(rs.getRow());
                for(int col=0 ;col < columnsNumber;col++) {
                    Cell newpath = desRow.createCell(col);
                    newpath.setCellValue(rs.getString(col+1));  
                }
                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"utenti.xls");
                writeWorkbook.write(fileOut);
                fileOut.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get data from database");
        }
    }
		public void getCandidatiExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_candidato";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"candidati.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
		
		public void getAuleExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_aula";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"aule.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
		
		public void getChiamateExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_chiamata";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"chiamate.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
		
		public void getColloquiExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_utente";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"colloqui.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }   
	    }
		
		public void getEdizioniExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_edizione";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"edizioni.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
		
		public void getMastersExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_master";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"masters.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
		
		public void getSediExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_sede";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"sedi.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
		
		public void getSettoriExcel(String pathfile) throws FileNotFoundException, IOException {
			Connection connection = null;
		    PreparedStatement preparedStatement = null;
		    PathComposer path = new PathComposer();
	        //Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("C:\\prova2.xls"));
	        Workbook writeWorkbook = new HSSFWorkbook();
	        Sheet desSheet = writeWorkbook.createSheet("new sheet");
	        ResultSet rs = null;
	        try{
	        	connection = DriverManagerConnectionPool.getConnection();
	            String query ="SELECT * FROM als_settore";
	            preparedStatement = connection.prepareStatement(query);
	            rs = preparedStatement.executeQuery(query);
	            ResultSetMetaData rsmd = rs.getMetaData();
	            int columnsNumber = rsmd.getColumnCount();
	            Row desRow1 = desSheet.createRow(0);
	            for(int col=0 ;col < columnsNumber;col++) {
	                Cell newpath = desRow1.createCell(col);
	                newpath.setCellValue(rsmd.getColumnLabel(col+1));
	            }
	            while(rs.next()) {
	                System.out.println("Row number" + rs.getRow() );
	                Row desRow = desSheet.createRow(rs.getRow());
	                for(int col=0 ;col < columnsNumber;col++) {
	                    Cell newpath = desRow.createCell(col);
	                    newpath.setCellValue(rs.getString(col+1));  
	                }
	                FileOutputStream fileOut = new FileOutputStream(path.getPathForWriteExcel()+"settori.xls");
	                writeWorkbook.write(fileOut);
	                fileOut.close();
	            }
	        }
	        catch (SQLException e) {
	            System.out.println("Failed to get data from database");
	        }
	    }
}

