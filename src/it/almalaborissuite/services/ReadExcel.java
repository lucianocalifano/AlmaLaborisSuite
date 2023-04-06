package it.almalaborissuite.services;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.EdizioneBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.StatoBean;
import it.almalaborissuite.dao.CandidatoDAO;
import it.almalaborissuite.dao.EdizioneDAO;
import it.almalaborissuite.dao.MasterDAO;
import it.almalaborissuite.dao.StatoDAO;
import it.almalaborissuite.impl.Candidato;
import it.almalaborissuite.impl.Edizione;
import it.almalaborissuite.impl.Master;
import it.almalaborissuite.impl.Stato;

/* JAR NECESSARI: poi-5.0.0.JAR and commons-math3-3.6.1  */
/*PROVA GENERALE EXCEL READ-WRITE*/
public class ReadExcel {
	
	public ReadExcel() { }
	
	public boolean insertCandidatiFromExcel(String pathfile) throws FileNotFoundException, IOException {
		boolean result = true;
		
		//Istanzio le classi che mi servono
		PathComposer pc = new PathComposer();
		ArrayList<CandidatoBean> listCandidati = new ArrayList<CandidatoBean>();
		ArrayList<String> edizioni = new ArrayList<String>();
		
		//Inizio a lavorare sull'excel
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(pathfile));
		HSSFSheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        //ITERATOR RIGA DEL FILE EXCEL (-> TUPLA DA AGGIUNGERE AL DB)
        while (iterator.hasNext()) {
        	CandidatoBean candidato = new CandidatoBean();
        	MasterDAO masterDao = new Master();
            Row nextRow = iterator.next();
           
            //SALTO LA PRIMA RIGA DELL'EXCEL
            if(nextRow.getRowNum() == 0) {
        		continue;
        	}
            
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            //CELLITERATOR RAPPRESENTA LA SINGOLA CELLA (-> SINGOLO COLONNA SUL DB)
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getColumnIndex()) {
                    case 0 : //EMAIL
                    	candidato.setEmail(cell.getStringCellValue());
                        break;
                    case 1: //NOME
                    	candidato.setNome(cell.getStringCellValue());
                        break;
                    case 2: //COGNOME
                    	candidato.setCognome(cell.getStringCellValue());
                        break;
                    case 3: //TELEFONO
                    	if(nextRow.getCell(3).getCellType() == CellType.NUMERIC) {
                    		Long tel = (long) cell.getNumericCellValue();
                    		candidato.setTelefono(tel.toString());
                    	} else if(nextRow.getCell(3).getCellType() == CellType.STRING){
                    		candidato.setTelefono(cell.getStringCellValue());
                    	}
                        break;
                    case 4: //INDIRIZZO
                    	if(cell.getStringCellValue().isEmpty() != true)
                    		candidato.setIndirizzo(cell.getStringCellValue());
                    	else
                    		candidato.setIndirizzo(null);
                        break;
                    case 5: //CAP
                    	if(nextRow.getCell(5).getCellType() == CellType.NUMERIC) {
                    		Long cap = (long) cell.getNumericCellValue();
                    		candidato.setCap(cap.toString());
                    	} else if(nextRow.getCell(3).getCellType() == CellType.STRING) {
                    		if(cell.getStringCellValue().isEmpty() != true)
                    			candidato.setCap(cell.getStringCellValue());
                    		else
                    			candidato.setCap(null);
                    	}
                        break;
                    case 6: //CITTA
                    	if(cell.getStringCellValue().isEmpty() != true)
                    		candidato.setCitta(cell.getStringCellValue());
                    	else
                    		candidato.setCitta(null);
                        break;
                    case 7: //PROVINCIA
                    	if(cell.getStringCellValue().isEmpty() != true)
                    		candidato.setProvincia(cell.getStringCellValue());
                    	else
                    		candidato.setProvincia(null);
                    	break; 
                    case 8: //DATA NASCITA
                    	if(nextRow.getCell(8).getCellType() == CellType.NUMERIC) {
                    		LocalDateTime dateLt = nextRow.getCell(8).getLocalDateTimeCellValue();
                    		System.out.println(dateLt);
                    		candidato.setDataNascita(dateLt);
                    	} else if (nextRow.getCell(8).getCellType() == CellType.STRING) {
                    		String dateStr = nextRow.getCell(8).getStringCellValue();
                    		System.out.println("DATA STRINGA"+dateStr);
                    		
                    		String slaStr = dateStr.substring(2, 3);
                    		System.out.println("substring indice 2 "+slaStr);
                    		
                    		if(slaStr.equals("/")) {
                    			
                    			String[] dateApp = dateStr.split(slaStr);
                    			
                    		    dateStr = dateApp[2]+"-"+dateApp[1]+"-"+dateApp[0]+" 00:00:00";
                    		} else if(slaStr.equals("-")) {
                    			String[] dateApp = dateStr.split(slaStr);
                    			
                    		    dateStr = dateApp[2]+"-"+dateApp[1]+"-"+dateApp[0]+" 00:00:00";
                    		}
                    		
                    		System.out.println("DATA STRINGA POST FORMAT "+dateStr);
                    		Timestamp ts = Timestamp.valueOf(dateStr);
                    		candidato.setDataNascita(ts.toLocalDateTime());
                    	}
                    	break;
                    case 9: //MASTER NOME
                    	try {
                    		candidato.setMaster(masterDao.getMasterPerNome(cell.getStringCellValue()));
                    	} catch (SQLException e) {
                    		System.out.println("Errore associazione candidato master");
                    		result = false;
                    		e.printStackTrace();
                    	}
                    	break; 
                    case 10: //FONTE
                    	candidato.setFonte(cell.getStringCellValue());
                    	break;
                    case 11: //FORMAT
                    	if(cell.getStringCellValue().isEmpty() != true)
                    		candidato.setFormatScelto(cell.getStringCellValue());
                    	else
                    		candidato.setFormatScelto(null);
                    	break;
                    case 12: //EDIZIONE PER LA QUALE SI CANDIDANO
                    	edizioni.add(cell.getStringCellValue());
                    	break;
                    default:
    					break;
                }
            } 
            
            candidato.setRuolo("CANDIDATO");
            
            File note = new File(pc.getPathFolderForInsertCandidatoExcel(candidato)+
            		candidato.getNome()+"_"+candidato.getCognome()+"_note.txt");
            
            try {
    			note.createNewFile();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
            
            candidato.setNote(note);
            listCandidati.add(candidato);       	
        }
        
        //QUA DEVO INSERIRE
        for(int i = 0; i < listCandidati.size(); i++) {
        	CandidatoDAO cDao = new Candidato();
        	
        	try {
    			cDao.insert(listCandidati.get(i),GestorePassword.generateInitPassword(listCandidati.get(i)));
    		} catch (SQLException e) {
    			System.out.println("Errore nell'inserimento del candidato");
    			result = false;
    			e.printStackTrace();
    		}
        	
        	StatoDAO sDao = new Stato();
        	StatoBean sBean = new StatoBean();	
        	try {
    			sBean.setUtente(cDao.retrieveByKey(listCandidati.get(i).getEmail()));
    		} catch (SQLException e) {
    			System.out.println("Errore nel reperimento dell'utente");
    			result = false;
    			e.printStackTrace();
    		}
        	
        	sBean.setEdizione(edizioni.get(i));
        	sBean.setMaster(listCandidati.get(i).getMaster());
        	
        	try {
    			sDao.insert(sBean);
    		} catch (SQLException e1) {
    			System.out.println("Errore nell'inserimento dello stato");
    			result = false;
    			e1.printStackTrace();
    		}
        	
        }
        
        workbook.close();
        return result;
	}
	
}
	/* Creare un metodo che restituisca una HashMap<Collection<UtenteBean>, Collection<String>>
	   dove la collection di UtenteBean sono gli utenti da inserire in piattaforma e la 
	   collection di String è relativa alle password relative agli utenti:
	   Dunque una volta restituita la HashMap occorrerà scorrere la collection di utenti insieme
	   alla collection di string per poter richiamare l'insert dell'utente nel modo seguente:
	   UtenteDAO.insert(UtenteBean[i], gestorePassword.generateStrongPassowrd(String[i])) */




