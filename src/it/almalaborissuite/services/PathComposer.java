package it.almalaborissuite.services;

import java.io.File;
import java.sql.SQLException;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.CandidatoDAO;
import it.almalaborissuite.impl.Candidato;

public class PathComposer {
	
	public PathComposer() {}
	
	public String getPathForWriteExcel() {
		return System.getProperty("user.home")+"\\Desktop\\";
	}
	
	public String getPathFolderForInsertCandidatoExcel(CandidatoBean candidato) {
		String path = System.getProperty("user.dir")+"/WebContent/file/candidati/";
		File directoryRoot = new File(path);
		
		if(directoryRoot.exists() != true && directoryRoot.isDirectory() != true) {
			directoryRoot.mkdir();
		}
				
		path += "/"+candidato.getNome()+" "+candidato.getCognome()+ " - "+candidato.getEmail();
		File directoryCandidato = new File(path);
		
		//Casistica candidato inserito la prima volta
		if(directoryCandidato.exists() != true && directoryCandidato.isDirectory() != true && 
				candidato.getDataInserimento() == null) {
			directoryCandidato.mkdir();
		}
		
		path += "/";
		
		return path;
	}

	public String getPathNote(UtenteBean utente) {
		String path = new String();
		CandidatoDAO cDao = new Candidato();
		
		if(utente.getRuolo().equals("CANDIDATO")) {
			CandidatoBean bean = new CandidatoBean();
			try {
				bean = cDao.retrieveByKey(utente.getEmail());
			} catch (SQLException e) {
				System.out.println("Reperimento Utente non riuscito.");
				e.printStackTrace();
			}
			
			path += System.getProperty("user.dir")+"/WebContent/file/candidati/"+bean.getNome()+" "
			+bean.getCognome()+" - "+bean.getEmail();
			File directoryCandidato = new File(path);
			
			//Casistica candidato inserito la prima volta
			if(directoryCandidato.exists() != true && directoryCandidato.isDirectory() != true) {
				directoryCandidato.mkdir();
			}
			
			path += "/"+bean.getNome()+"_"+bean.getCognome()+"_note.txt";
			
		} else if(utente.getRuolo().equals("PARTECIPANTE")) {
			
		}
		
		return null;
	}
	
	public String checkMonth(int mese) {

		String meseStr = new String();
		
		switch(mese) {
			case 1: meseStr = "GENNAIO";
					break;
			case 2: meseStr = "FEBBRAIO";
					break;
			case 3: meseStr = "MARZO";
					break;
			case 4: meseStr = "APRILE";
					break;
			case 5: meseStr = "MAGGIO";
					break;
			case 6: meseStr = "GIUGNO";
					break;
			case 7: meseStr = "LUGLIO";
					break;
			case 8: meseStr = "AGOSTO";
				    break;
			case 9:	meseStr = "SETTEBRE";
			        break;
			case 10: meseStr = "OTTOBRE";
					 break;
			case 11: meseStr = "NOVEMBRE";
					 break;
			case 12: meseStr = "DICEMBRE";
					 break;
			default: break;
		}
	
		return meseStr;
	}
	
}
