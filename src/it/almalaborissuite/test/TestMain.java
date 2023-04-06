package it.almalaborissuite.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.*;
import it.almalaborissuite.dao.*;
import it.almalaborissuite.impl.*;
import it.almalaborissuite.services.ReadExcel;

import java.sql.Blob;

public class TestMain {

	public static void main(String[] args) {
		
		/* TEST STATO IMPL */
		StatoDAO sDao = new Stato();
		StatoBean sBean = new StatoBean();
		UtenteDAO uDao = new Utente();
		MasterDAO mDao = new Master();
		
		/* BLOCCO METODI DA GENERIC */
		try {
			sBean.setUtente(uDao.retrieveByKey("Anna.Sernicola@gmail.com"));
		} catch (SQLException e1) {
			System.out.println("Errore reperimento Utente");
			//e1.printStackTrace();
		}		
		sBean.setEdizione("MARZO 2021");
		try {
			sBean.setMaster(mDao.retrieveByKey(3));
		} catch (SQLException e) {
			System.out.println("Errore reperimento Master");
			//e.printStackTrace();
		}
		
		try {
			sDao.insert(sBean);
		} catch (SQLException e) {
			System.out.println("Errore inserimento Stato");
			//e.printStackTrace();
		}
		
		/* BLOCCO METODI NEW DA IMPL */
		
		/*
		retrieveByKey(String emailUtente, String nomeEdizione, MasterBean master)
	
		checkUtenteInteressato(String emailUtente, String edizione)

		checkUtenteInfoSpecifica(String emailUtente, String edizione)
			
		checkUtenteInfoGenerica(String emailUtente, String edizione);
			
		checkUtenteContattato(String emailUtente, String edizione);
			
		checkUtentePrenotazioneColloquio(String emailUtente, String edizione);
			
		checkUtenteAttesaDataSelezione(String emailUtente, String edizione);

		checkUtentePresenzaSelezione(String emailUtente, String edizione);
			
		checkUtenteAttesaEsitoSelezione(String emailUtente, String edizione);	
			
		checkUtenteAmmesso(String emailUtente);
			
		checkUtenteSchedaIscrizioneInviata(String emailUtente);
			
		checkUtenteSchedaIscrizioneApprovata(String emailUtente);
			
		checkUtenteIscritto(String emailUtente);
			
		checkUtenteQuietanza1Inviata(String emailUtente);
			
		checkUtenteQuietanza2Inviata(String emailUtente);
			
		checkUtenteQuietanza3Inviata(String emailUtente);

		checkUtenteQuietanza1Pagata(String emailUtente);
			
		checkUtenteQuietanza2Pagata(String emailUtente);
			
		checkUtenteQuietanza3Pagata(String emailUtente);
			
		setUtenteInteressato(String emailUtente, String edizione, boolean interesse);
			
		setUtenteInfoSpecifica(String emailUtente, String edizione, boolean infoSpec);
			
		setUtenteInfoGenerica(String emailUtente, String edizione, boolean infoGen);
			
		setUtenteContattato(String emailUtente, String edizione, boolean contattato);
			
		setUtentePrenotazioneColloquio(String emailUtente, String edizione, String nomeMaster, boolean prenotazione);

		setUtenteAttesaDataSelezione(String emailUtente, String edizione, boolean attesa);
			
		setUtentePresenzaSelezione(String emailUtente, String edizione, boolean presenza);
			
		setUtenteAttesaEsitoSelezione(String emailUtente, String edizione, boolean attesa);
			
		setUtenteAmmesso(String emailUtente, String edizione, String nomeMaster, boolean ammesso);
			
		setUtenteSchedaIscrizioneInviata(String emailUtente, String edizione, boolean schedaInviata);
			
		setUtenteSchedaIscrizioneApprovata(String emailUtente, String edizione, boolean schedaApprovata);
			
		setUtenteIscritto(String emailUtente, String edizione, boolean iscritto);
			
		setUtenteQuietanza1Inviata(String emailUtente, String edizione, boolean quietanza);
			
		setUtenteQuietanza2Inviata(String emailUtente, String edizione, boolean quietanza);
			
		setUtenteQuietanza3Inviata(String emailUtente, String edizione, boolean quietanza);
			
		setUtenteQuietanza1Pagata(String emailUtente, String edizione, boolean quietanza);
			
		setUtenteQuietanza2Pagata(String emailUtente, String edizione, boolean quietanza);
			
		setUtenteQuietanza3Pagata(String emailUtente, String edizione, boolean quietanza);
			
		Collection<StatoBean> getStatiPerUtenteEdizione(String emailUtente, String edizione);
			
		Collection<StatoBean> getStatoUtentiPerEdizione(String nomeEdizione);
			
		Collection<StatoBean> getStatoUtentiPerMaster(String nomeMaster);
		
		Collection<StatoBean> getStatoUtentiPerEdizioneMaster(String nomeEdizione, String nomeMaster);
		*/
	
		/**/
		
		/* TEST READ EXCEL */
		ReadExcel rE = new ReadExcel();
		boolean result = false;
		
		//TEST insertCandidatiFromExcel -> FUNZIONANTE
		try {
			result = rE.insertCandidatiFromExcel("C:\\example\\impCandTest.xls");
			
		} catch (FileNotFoundException e) {
			System.out.println("Errore in apertura del file impCandTest.xls");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Errore di IOException");
			e.printStackTrace();
		}
		
		if(result != false)
			System.out.println("Caricamento contatti avvenuto con successo");
		
		
		/* TEST System.getProperty 
		File[] roots = File.listRoots();
		for(File f : roots) {
		System.out.println(f);
		}
		
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
		/**/
		
		/* TEST METODO PER EDIZIONE IMPL GETEDIZIONEPERNOMEEMASTER
		EdizioneDAO eDao = new Edizione();
		String nomeM = "risorse";
		String nomeE = "Marzo 2020";
		try {
			EdizioneBean eBean = eDao.getEdizioneByNomeEMaster(nomeE, nomeM);
			System.out.println("Reperimento Edizione per Nome Edizione e Nome Master riuscito: ");
			System.out.println(eBean);
		} catch (SQLException e) {
			System.out.println("Reperimento Edizione non riuscito");
			e.printStackTrace();
		} */
		
		/* TEST GET MESE ED ANNO CON LOCALDATETIME 
		PathComposer pc = new PathComposer();
		LocalDateTime data = LocalDateTime.now().withNano(0);
		
		int anno = data.getYear();
		int mese = data.getMonthValue();
		
		System.out.println("Anno: "+anno+" Mese: "+pc.checkMonth(mese));
		System.out.println("cartella: "+pc.checkMonth(mese)+" "+anno);
		*/
		
		/* TEST FILE BLOB
		UtenteDAO userDao = new Utente();
        String word = "Questo è un testo di prova per il file blob di un utente";
        
        UtenteBean uT = new UtenteBean();
        
        try {
			uT = userDao.retrieveByKey("ludovico.enaudi@gmail.com");
			System.out.println(uT);
		} catch (SQLException e) {
			System.out.println("ERRORE REPERIMENTO UTENTE");
			e.printStackTrace();
		}
		
		//Aggiunta di testo
		try(FileWriter fw = new FileWriter(System.getProperty("user.dir")+"/WebContent/edizioni/"+uT.getNome()+"_"+uT.getCognome()+"_note.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println("the text");
			    //more code
			    out.println("more text");
			    //more code
			} catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
		
		File newFile = new File(System.getProperty("user.dir")+"/WebContent/edizioni/"+uT.getNome()+"_"+uT.getCognome()+"_note.txt");
		uT.setNote(newFile);
		try {
			userDao.update(uT);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        
        /*
        InputStream in = null;
		try {
			if(uT.getNote() != null)
				in = uT.getNote().getBinaryStream();
			else {
				Blob blob = DriverManagerConnectionPool.getConnection().createBlob();
				in = blob.getBinaryStream();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		/*
        OutputStream out = null;
		try {
			out = new FileOutputStream(System.getProperty("user.dir")+"/WebContent/edizioni/"+uT.getNome()+"_"+uT.getCognome()+"_note.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        byte[] buff = new byte[4096];  // how much of the blob to read/write at a time
        int len = 0;

        try {
			while ((len = in.read(buff)) != -1) {
			    out.write(buff, 0, len);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Blob newBlob = null;
        
        try {
			newBlob = DriverManagerConnectionPool.getConnection().createBlob();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        
        File fileApp = new File(System.getProperty("user.dir")+"/WebContent/edizioni/"+uT.getNome()+"_"+uT.getCognome()+"_note.txt");
        InputStream input = null;
        
        try {
			input = new FileInputStream(fileApp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        
        uT.setNote(null); */

        
        
        /*
        System.out.println("ClassPath: "+System.getProperty("user.dir"));
        System.out.println("ClassPath: "+System.getProperty("java.class.path")); */
		
		/**/
		
		/* *********** TEST IMPL SETTORE ***********
		SettoreDAO setDao = new Settore();
		SettoreBean setBean = new SettoreBean();
		Collection<SettoreBean> collSet = new ArrayList<SettoreBean>();

		//TEST RETRIEVEBYKEY -> FUNZIONANTE
		int setId = 3;

		try {
			setBean = setDao.retrieveByKey(setId);
			System.out.println("REPERIMENTO SETTORE RIUSCITO->");
			System.out.println(setBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO SETTORE NON RIUSCITO.");
			e.printStackTrace();
		}  

		//TEST RETRIEVEALL -> FUNZIONANTE
		try {
			collSet = setDao.retrieveAll(null);
			System.out.println("REPERIMENTO LISTA SETTORE RIUSCITO->");
			for(SettoreBean s: collSet) 
				System.out.println(s);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SETTORE NON RIUSCITO.");
			e.printStackTrace();
		}

		//TEST INSERT -> FUNZIONANTE
		SettoreBean setBeanNew = new SettoreBean();
		setBeanNew.setNome("Settore insert test");

		try {
			setDao.insert(setBeanNew);
			System.out.println("INSERIMENTO SETTORE RIUSCITO");
		} catch (SQLException e) {
			System.out.println("INSERIMENTO SETTORE NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST UPDATE -> FUNZIONANTE
		System.out.println("SETTORE NON AGGIORNATA: ");
		int idSettore = 1;
		
		try {
			setBean = setDao.retrieveByKey(idSettore);
			System.out.println(setBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO DEL SETTORE-> "+setBean);
			e.printStackTrace();
		}

		try {
			setBean.setNome("Settore update test");

			setDao.update(setBean);
			System.out.println("AGGIORNAMENTO RIUSCITO DEL SETTORE->");
			System.out.println(setBean);
		} catch (SQLException e) {
			System.out.println("AGGIORNAMENTO NON RIUSCITO DELLA SEDE: "+setBean);
			e.printStackTrace();
		}

		//TEST DELETE ->  FUNZIONANTE
		idSettore = 12;
		try {
			setDao.delete(idSettore);
			System.out.println("CANCELLAZIONE SEDE AVVENUTA CON SUCCESSO");
		} catch (SQLException e) {
			System.out.println("CANCELLAZIONE SEDE AVVENUTA CON SUCCESSO");
			e.printStackTrace();
		}
		*/
		
		/* *********** TEST IMPL SEDE **********
		SedeDAO sedDao = new Sede();
		SedeBean sedBean = new SedeBean();
		Collection<SedeBean> collSed = new ArrayList<SedeBean>();

		//TEST RETRIEVEBYKEY -> FUNZIONANTE
		int sedId = 3;

		try {
			sedBean = sedDao.retrieveByKey(3);
			System.out.println("REPERIMENTO SEDE RIUSCITO->");
			System.out.println("Nome: "+sedBean.getNome()+", Indirizzo: "+sedBean.getIndirizzo()+", Riferimento: "+sedBean.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO SEDE NON RIUSCITO.");
			e.printStackTrace();
		}  

		//TEST RETRIEVEALL -> FUNZIONANTE
		try {
			collSed = sedDao.retrieveAll(null);
			System.out.println("REPERIMENTO LISTA SEDI RIUSCITO->");
			for(SedeBean s: collSed) 
				System.out.println("Nome: "+s.getNome()+", Indirizzo: "+s.getIndirizzo()+", Riferimento: "+s.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SEDI NON RIUSCITO.");
			e.printStackTrace();
		}

		//TEST INSERT -> FUNZIONANTE
		sedBean.setNome("Sede insert test");
		sedBean.setRiferimento("Riferimento per sede test");
		sedBean.setIndirizzo("Indirizzo per sede test");
		sedBean.setCap("80143");
		sedBean.setCitta("Napoli");

		try {
			sedDao.insert(sedBean);
			sedDao.insert(sedBean);
			System.out.println("INSERIMENTO SEDE RIUSCITO");
		} catch (SQLException e) {
			System.out.println("INSERIMENTO SEDE NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST UPDATE -> FUNZIONANTE
		System.out.println("SEDE NON AGGIORNATA: ");
		int idSede = 1;
		
		try {
			sedBean = sedDao.retrieveByKey(idSede);
			System.out.println(sedBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO DELLA SEDE-> "+sedBean);
			e.printStackTrace();
		}

		try {
			sedBean.setNome("Sede update test");
			sedBean.setRiferimento("Riferimento per sede test update");
			sedBean.setIndirizzo("Indirizzo per sede test update");
			sedBean.setCap("80143");
			sedBean.setCitta("Roma");

			sedDao.update(sedBean);
			System.out.println("AGGIORNAMENTO RIUSCITO DELLA SEDE->");
			System.out.println(sedBean);
		} catch (SQLException e) {
			System.out.println("AGGIORNAMENTO NON RIUSCITO DELLA SEDE: "+sedBean);
			e.printStackTrace();
		}

		//TEST DELETE ->  FUNZIONANTE
		idSede = 10;
		try {
			sedDao.delete(idSede);
			System.out.println("CANCELLAZIONE SEDE AVVENUTA CON SUCCESSO");
		} catch (SQLException e) {
			System.out.println("CANCELLAZIONE SEDE AVVENUTA CON SUCCESSO");
			e.printStackTrace();
		}

		//TEST getSediPerNome -> FUNZIONANTE
		String nomeSede = "Roma";
		
		try {
			collSed = sedDao.getSediPerNome(nomeSede);
			System.out.println("REPERIMENTO LISTA SEDI X NOME RIUSCITO->");
			for(SedeBean s: collSed) 
				System.out.println("Nome: "+s.getNome()+", Indirizzo: "+s.getIndirizzo()+", Riferimento: "+s.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SEDI X NOME NON RIUSCITO.");
			e.printStackTrace();
		}
		
		//TEST getSediPerRiferimento -> FUNZIONANTE
		String riferimentoSede = "Meet";
		
		try {
			collSed = sedDao.getSediPerRiferimento(riferimentoSede);
			System.out.println("REPERIMENTO LISTA SEDI X RIFERIMENTO RIUSCITO->");
			for(SedeBean s: collSed) 
				System.out.println("Nome: "+s.getNome()+", Indirizzo: "+s.getIndirizzo()+", Riferimento: "+s.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SEDI X RIFERIMENTO NON RIUSCITO.");
			e.printStackTrace();
		}

		//TEST getSediPerIndirizzo -> FUNZIONANTE
		String indirizzoSede = "Sc";
		
		try {
			collSed = sedDao.getSediPerIndirizzo(indirizzoSede);
			System.out.println("REPERIMENTO LISTA SEDI X INDIRIZZO RIUSCITO->");
			for(SedeBean s: collSed) 
				System.out.println("Nome: "+s.getNome()+", Indirizzo: "+s.getIndirizzo()+", Riferimento: "+s.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SEDI X INDIRIZZO NON RIUSCITO.");
			e.printStackTrace();
		}
		
		//TEST getSediPerCap -> FUNZIONANTE
		String capSede = "80143";
		
		try {
			collSed = sedDao.getSediPerCap(capSede);
			System.out.println("REPERIMENTO LISTA SEDI X CAP RIUSCITO->");
			for(SedeBean s: collSed) 
				System.out.println("Nome: "+s.getNome()+", Indirizzo: "+s.getIndirizzo()+", Riferimento: "+s.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SEDI X CAP NON RIUSCITO.");
			e.printStackTrace();
		}		

		//TEST getSediPerCitta -> FUNZIONANTE
		String cittaSede = "Napoli";
		
		try {
			collSed = sedDao.getSediPerCitta(cittaSede);
			System.out.println("REPERIMENTO LISTA SEDI X CITTA RIUSCITO->");
			for(SedeBean s: collSed) 
				System.out.println("Nome: "+s.getNome()+", Indirizzo: "+s.getIndirizzo()+", Riferimento: "+s.getRiferimento());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA SEDI X CITTA NON RIUSCITO.");
			e.printStackTrace();
		}
		*/


		/* *********** TEST IMPL PARTECIPANTE *********** 
		PartecipanteDAO paxDao = new Partecipante();
		PartecipanteBean paxBean = new PartecipanteBean();
		Collection<PartecipanteBean> collPax = new ArrayList<PartecipanteBean>();
		EdizioneBean ediBean = new EdizioneBean();
		EdizioneDAO ediDao = new Edizione();
		MasterBean masBean = new MasterBean();
		MasterDAO masDao = new Master();

		//TEST RETRIEVEBYKEY -> FUNZIONANTE
		try {
			paxBean = paxDao.retrieveByKey("antonio.persico@gmail.com");
			System.out.println("REPERIMENTO PAX RIUSCITO->");
			System.out.println("Email: "+paxBean.getEmail()+", Nome: "+paxBean.getNome()+", Cognome: "+paxBean.getCognome()+""
					+ ", Edizione: "+paxBean.getEdizione().getNome()+", Master: "+paxBean.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX NON RIUSCITO.");
			e.printStackTrace();
		} 

		//TEST RETRIEVEALL -> FUNZIONANTE
		try {
			collPax = paxDao.retrieveAll("id_edizione");
			System.out.println("REPERIMENTO LISTA PAX RIUSCITO->");
			for(PartecipanteBean p: collPax) 
				System.out.println("Email: "+p.getEmail()+", Nome: "+p.getNome()+", Cognome: "+p.getCognome()+", "
						+ "Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO LISTA PAX NON RIUSCITO.");
			e.printStackTrace();
		}

		//TEST INSERT -> FUNZIONANTE
		int idEdizione = 1;

		try {
			ediBean = ediDao.retrieveByKey(idEdizione);
			System.out.println("REPERIMENTO RIUSCITO X EDIZIONE->");
			System.out.println("Nome: "+ediBean.getNome()+", Master: "+ediBean.getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO X EDIZIONE CON ID->"+idEdizione);
			e.printStackTrace();
		}

		paxBean.setEmail("mariano.fibia@almalaboris.com");
		paxBean.setNome("Mariano");
		paxBean.setCognome("Fibia");
		paxBean.setRuolo("PARTECIPANTE");
		paxBean.setCap("84013");
		paxBean.setCitta("Nocera Inferiore");
		paxBean.setIndirizzo("Via Nazionale 45");
		paxBean.setProvincia("SA");
		paxBean.setTelefono("3568899774");
		paxBean.setDataNascita(LocalDateTime.now().withNano(0));
		paxBean.setEdizione(ediBean);
		paxBean.setFonte("ECLIPSE");
		paxBean.setFormatScelto("ONLINE");
		paxBean.setCv("cvExample");
		paxBean.setSchedaIscrizione("schedaIscrizioneExample");
		paxBean.setQuietanza1("quietanza1Example");
		paxBean.setQuietanza2(null);
		paxBean.setQuietanza3(null);

		try {
			paxDao.insert(paxBean, GestorePassword.generateInitPassword(paxBean));
			System.out.println("INSERIMENTO PAX RIUSCITO");
		} catch (SQLException e) {
			System.out.println("INSERIMENTO PAX NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST UPDATE -> FUNZIONANTE
		System.out.println("PARTECIPANTE NON AGGIORNATO: ");

		try {
			paxBean = paxDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("Email: "+paxBean.getEmail()+", Nome: "+paxBean.getNome()+", Cognome: "+paxBean.getCognome()+""
					+ ", Città: "+paxBean.getCitta()+", Indirizzo: "+paxBean.getIndirizzo()+", Provincia: "+paxBean.getProvincia()+""
					+ ", Telefono: "+paxBean.getTelefono()+", DataNascita: "+paxBean.getDataNascita()+", Fonte: "+paxBean.getFonte());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO DEL PAX-> "+paxBean);
			e.printStackTrace();
		}

		try {
			paxBean.setCitta("Cava De Tirreni");
			paxBean.setIndirizzo("Piazza Felice Baldi 4");
			paxBean.setProvincia("SA");
			paxBean.setTelefono("0000000000");
			paxBean.setDataNascita(LocalDateTime.now().withNano(0));
			paxBean.setFonte("SITO");

			paxDao.update(paxBean);
			System.out.println("AGGIORNAMENTO RIUSCITO DEL PAX->");
			System.out.println("Email: "+paxBean.getEmail()+", Nome: "+paxBean.getNome()+", Cognome: "+paxBean.getCognome()+""
					+ ", Città: "+paxBean.getCitta()+", Indirizzo: "+paxBean.getIndirizzo()+", Provincia: "+paxBean.getProvincia()+""
					+ ", Telefono: "+paxBean.getTelefono()+", DataNascita: "+paxBean.getDataNascita()+", Fonte: "+paxBean.getFonte());
		} catch (SQLException e) {
			System.out.println("AGGIORNAMENTO NON RIUSCITO DEL PAX: "+paxBean);
			e.printStackTrace();
		}

		//TEST DELETE ->  FUNZIONANTE
		try {
			paxDao.delete("mariano.fibia@almalaboris.com");
			System.out.println("CANCELLAZIONE PAX AVVENUTA CON SUCCESSO");
		} catch (SQLException e) {
			System.out.println("CANCELLAZIONE PAX AVVENUTA CON SUCCESSO");
			e.printStackTrace();
		}

		//TEST GET PAX PER EDIZIONE
		String edizione1 = "MARZO 2020";

		try {
			collPax = paxDao.getPartecipantiPerEdizione(edizione1);
			System.out.println("REPERIMENTO PAX X EDIZIONE: "+edizione1+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+""
						+ ", Nome: "+p.getNome()+", Cognome: "+p.getCognome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X EDIZIONE: "+edizione1+" NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PAX PER EDIZIONE MASTER
		String edizione = "MARZO 2020";
		String master = "Energy Management";

		try {
			collPax = paxDao.getPartecipantiPerEdizioneMaster(edizione, master);
			System.out.println("REPERIMENTO PAX X EDIZIONE: "+edizione+" E MASTER: "+master+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+""
						+ ", Nome: "+p.getNome()+", Cognome: "+p.getCognome());
		} catch (SQLException e1) {
			System.out.println("REPERIMENTO PAX X EDIZIONE: "+edizione+" E MASTER: "+master+" NON AVVENUTA");
			e1.printStackTrace();
		}

		//TEST GET PAX PER MASTER
		String master1 = "Export Management";

		try {
			collPax = paxDao.getPartecipantiPerMaster(master1);
			System.out.println("REPERIMENTO PAX X MASTER: "+master1+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Master: "+p.getEdizione().getMaster().getNome()+", Edizione: "+p.getEdizione().getNome()+""
						+ ", Nome: "+p.getNome()+", Cognome: "+p.getCognome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X MASTER: "+master1+" NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PAX PER NOME -> FUNZIONANTE
		String nome = "l";
		try {
			collPax = paxDao.getPartecipantiPerNome(nome);
			System.out.println("REPERIMENTO PAX X NOME: "+nome+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Nome: "+p.getNome()+", Cognome: "+p.getCognome()+", Edizione: "+p.getEdizione().getNome()+""
						+ ", Master: "+p.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X NOME: "+nome+" NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PAX PER COGNOME -> FUNZIONANTE
		String cognome = "l";
		try {
			collPax = paxDao.getPartecipantiPerCognome(cognome);
			System.out.println("REPERIMENTO PAX X COGNOME: "+cognome+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Cognome: "+p.getCognome()+", Nome: "+p.getNome()+", Edizione: "+p.getEdizione().getNome()+""
						+ ", Master: "+p.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X COGNOME: "+cognome+" NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PAX PER FONTE -> FUNZIONANTE
		String fonte = "LINK";

		try {
			collPax = paxDao.getPartecipantiPerFonte(fonte);
			System.out.println("REPERIMENTO PAX X FONTE: "+fonte+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Fonte: "+p.getFonte()+", Cognome: "+p.getCognome()+", Nome: "+p.getNome()+", Edizione: "+p.getEdizione().getNome()+""
						+ ", Master: "+p.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X FONTE: "+fonte+" NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PAX PER FORMAT SCELTO -> FUNZIONANTE
		String format = "aula";

		try {
			collPax = paxDao.getPartecipantiPerFormat(format);
			System.out.println("REPERIMENTO PAX X FORMAT: "+format+" AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Format: "+p.getFormatScelto()+", Cognome: "+p.getCognome()+", Nome: "+p.getNome()+", Edizione: "+p.getEdizione().getNome()+""
						+ ", Master: "+p.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X FORMAT: "+format+" NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA1 PAGATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza1Pagata();
			System.out.println("REPERIMENTO PAX X QUIETANZA1 PAGATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA1 PAGATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI QUIETANZA1 PAGATA PER EDIZIONE -> FUNZIONANTE
		String edizione2 = "Novembre 2020";
		try {
			collPax = paxDao.getPartecipantiQuietanza1PagataPerEdizione(edizione2);
			System.out.println("REPERIMENTO PAX X QUIETANZA1 PAGATA X EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Cognome: "+p.getCognome()+", Nome: "+p.getNome()+""
						+ ", Master: "+p.getEdizione().getMaster().getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX QUIETANZA1 PAGATA X EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI QUIETANZA1 PAGATA PER EDIZIONE E MASTER -> FUNZIONANTE
		String edizione3 = "Ottobre 2020";
		String master3 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza1PagataPerEdizioneMaster(edizione3, master3);
			System.out.println("REPERIMENTO PAX X QUIETANZA1 PAGATA X EDIZIONE E MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master"+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX QUIETANZA1 PAGATA X EDIZIONE E MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza2Inviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza2NonInviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 PAGATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza2Pagata();
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione4 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2InviataPerEdizione(edizione4);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione5 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonInviataPerEdizione(edizione5);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 PAGATA PER EDZIONE -> FUNZIONANTE
		String edizione6 = "NOVEMBRE 2020"; 

		try {
			collPax = paxDao.getPartecipantiQuietanza2PagataPerEdizione(edizione6);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON PAGATA PER EDIZIONE -> FUNZIONANTE
		String edizione7 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonPagataPerEdizione(edizione6);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione8 = "NOVEMBRE 2020";
		String master8 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2InviataPerEdizioneMaster(edizione8, master8);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione9 = "NOVEMBRE 2020";
		String master9 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonInviataPerEdizioneMaster(edizione9, master9);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione10 = "NOVEMBRE 2020";
		String master10 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2PagataPerEdizioneMaster(edizione10, master10);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione11 = "NOVEMBRE 2020";
		String master11 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonPagataPerEdizioneMaster(edizione11, master11);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza2Inviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON INVIATA -> FUNZIONANTE
		try { 
			collPax = paxDao.getPartecipantiQuietanza2NonInviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 PAGATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza2Pagata();
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione12 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2InviataPerEdizione(edizione12);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione13 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonInviataPerEdizione(edizione13);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 PAGATA PER EDZIONE -> FUNZIONANTE
		String edizione14 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2PagataPerEdizione(edizione14);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON PAGATA PER EDIZIONE -> FUNZIONANTE
		String edizione15 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonPagataPerEdizione(edizione15);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione16 = "NOVEMBRE 2020";
		String master16 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2InviataPerEdizioneMaster(edizione16, master16);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione17 = "NOVEMBRE 2020";
		String master17 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonInviataPerEdizioneMaster(edizione17, master17);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione18 = "NOVEMBRE 2020";
		String master18 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2PagataPerEdizioneMaster(edizione18, master18);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA2 NON PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione19 = "NOVEMBRE 2020";
		String master19 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonPagataPerEdizioneMaster(edizione19, master19);
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA2 NON PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//QUIETANZA 3
		//TEST GET PARTECIPANTI PER QUIETANZA3 INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza3Inviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza3NonInviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 PAGATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza3Pagata();
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione20 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3InviataPerEdizione(edizione20);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione21 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonInviataPerEdizione(edizione21);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 PAGATA PER EDZIONE -> FUNZIONANTE
		String edizione22 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3PagataPerEdizione(edizione22);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON PAGATA PER EDIZIONE -> FUNZIONANTE
		String edizione23 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonPagataPerEdizione(edizione23);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione24 = "NOVEMBRE 2020";
		String master24 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3InviataPerEdizioneMaster(edizione24, master24);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione25 = "NOVEMBRE 2020";
		String master25 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonInviataPerEdizioneMaster(edizione25, master25);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione26 = "NOVEMBRE 2020";
		String master26 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3PagataPerEdizioneMaster(edizione26, master26);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione27 = "NOVEMBRE 2020";
		String master27 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonPagataPerEdizioneMaster(edizione11, master11);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza3Inviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON INVIATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza3NonInviata();
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 PAGATA -> FUNZIONANTE
		try {
			collPax = paxDao.getPartecipantiQuietanza3Pagata();
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione28 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3InviataPerEdizione(edizione28);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON INVIATA PER EDIZIONE -> FUNZIONANTE
		String edizione29 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonInviataPerEdizione(edizione29);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 PAGATA PER EDZIONE -> FUNZIONANTE
		String edizione30 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3PagataPerEdizione(edizione30);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON PAGATA PER EDIZIONE -> FUNZIONANTE
		String edizione31 = "NOVEMBRE 2020";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonPagataPerEdizione(edizione15);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione32 = "NOVEMBRE 2020";
		String master32 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3InviataPerEdizioneMaster(edizione32, master32);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON INVIATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione33 = "NOVEMBRE 2020";
		String master33 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3NonInviataPerEdizioneMaster(edizione17, master17);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON INVIATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione34 = "NOVEMBRE 2020";
		String master34 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza3PagataPerEdizioneMaster(edizione34, master34);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}

		//TEST GET PARTECIPANTI PER QUIETANZA3 NON PAGATA PER EDIZIONE MASTER -> FUNZIONANTE
		String edizione35 = "NOVEMBRE 2020";
		String master35 = "Energy";

		try {
			collPax = paxDao.getPartecipantiQuietanza2NonPagataPerEdizioneMaster(edizione35, master35);
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE MASTER AVVENUTA CON SUCCESSO");
			for(PartecipanteBean p: collPax)
				System.out.println("Edizione: "+p.getEdizione().getNome()+", Master: "+p.getEdizione().getMaster().getNome()+", "
						+ "Cognome: "+p.getCognome()+", Nome: "+p.getNome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO PAX X QUIETANZA3 NON PAGATA PER EDIZIONE MASTER NON AVVENUTA");
			e.printStackTrace();
		}
		 */

		/*	*********** TEST IMPL OPERATORE TECNICO ***********
		OperatoreTecnicoDAO opDao = new OperatoreTecnico();
		OperatoreTecnicoBean opBean = new OperatoreTecnicoBean();
		CandidatoDAO canDao = new Candidato();
		CandidatoBean canBean = new CandidatoBean();
		Collection<OperatoreTecnicoBean> opColBean = new ArrayList<OperatoreTecnicoBean>();
		MasterDAO masDao = new Master();
		MasterBean masBean = new MasterBean();

		//TEST RETRIEVEBYKEY -> FUNZIONANTE
		try {
			opBean = opDao.retrieveByKey("vincenzo.pepe@almalaboris.com");
			System.out.println("REPERIMENTO OPERATORE TECNICO RIUSCITO:");
			System.out.println(opBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO OPERATORE TECNICO NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST RETRIEVEALL -> FUNZIONANTE
		try {
			opColBean = opDao.retrieveAll("nome");
			System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE TECNICO RIUSCITO:");
			for(OperatoreTecnicoBean op: opColBean) {
				System.out.println(op);
			}
		} catch (SQLException e) {
			System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE TECNICO NON RIUSCITO");
			e.printStackTrace();
		}
		/*
		//TEST INSERT -> FUNZIONANTE
		try {
			opBean.setEmail("mariano.fibia@almalaboris.com");
			opBean.setNome("Mariano");
			opBean.setCognome("Fibia");
			opBean.setRuolo("TECNICO");
			opBean.setCap("84013");
			opBean.setCitta("Nocera Inferiore");
			opBean.setIndirizzo("Via Nazionale 45");
			opBean.setProvincia("SA");
			opBean.setTelefono("3568899774");
			opBean.setDataNascita(LocalDateTime.now().withNano(0));

			opDao.insert(opBean, GestorePassword.generateInitPassword(opBean));
			System.out.println("INSERIMENTO DELL'OPERATORE TECNICO RIUSCITO:");
			System.out.println(opDao.retrieveByKey(opBean.getEmail()));
		} catch (SQLException e) {
			System.out.println("INSERIMENTO DELL'OPERATORE TECNICO NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST UPDATE -> FUNZIONANTE
		System.out.println("UTENTE NON AGGIORNATO: ");
		try {
			System.out.println(opDao.retrieveByKey("mariano.fibia@almalaboris.com"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("REPERIMENTO RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
			e.printStackTrace();
		}

		try {
			opBean.setCitta("Cava De Tirreni");
			opBean.setIndirizzo("Piazza Felice Baldi 4");
			opBean.setProvincia("SA");
			opBean.setTelefono("0000000000");
			opBean.setDataNascita(LocalDateTime.now().withNano(0));

			opDao.update(opBean);
			System.out.println("AGGIORNAMENTO OPERATORE TECNICO RIUSCITO");
		} catch (SQLException e) {
			System.out.println("AGGIORNAMENTO OPERATORE TECNICO NON RIUSCITO");
			e.printStackTrace();
		}

		System.out.println("UTENTE "+opBean.getEmail()+" AGGIORNATO: ");
		System.out.println(opBean);

		//TEST DELETE ->  FUNZIONANTE
		try {
			opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("REPERIMENTO OPERATORE TECNICO RIUSCITO");
			System.out.println(opBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO OPERATORE TECNICO NON RIUSCITO");
			e.printStackTrace();
		}

		try {
			opDao.delete("mariano.fibia@almalaboris.com");
			System.out.println("CANCELLAZIONE OPERATORE TECNICO RIUSCITO");
		} catch (SQLException e) {
			System.out.println("CANCELLAZIONE OPERATORE TECNICO NON RIUSCITO");
			e.printStackTrace();
		} 

		//... 

		 */


		/*	*********** TEST IMPL OPERATORE SELEZIONATORE ***********
		OperatoreSelezionatoreDAO opDao = new OperatoreSelezionatore();
		OperatoreSelezionatoreBean opBean = new OperatoreSelezionatoreBean();
		CandidatoDAO canDao = new Candidato();
		CandidatoBean canBean = new CandidatoBean();
		Collection<OperatoreSelezionatoreBean> opColBean = new ArrayList<OperatoreSelezionatoreBean>();
		MasterDAO masDao = new Master();
		MasterBean masBean = new MasterBean();

		//TEST RETRIEVEBYKEY -> FUNZIONANTE
		try {
			opBean = opDao.retrieveByKey("giuliana.gaudiosi@almalaboris.com");
			System.out.println("REPERIMENTO OPERATORE SELEZIONATORE RIUSCITO:");
			System.out.println(opBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO OPERATORE SELEZIONATORE NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST RETRIEVEALL -> FUNZIONANTE
		try {
			opColBean = opDao.retrieveAll("nome");
			System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE SELEZIONATORE RIUSCITO:");
			for(OperatoreSelezionatoreBean op: opColBean) {
				System.out.println(op);
			}
		} catch (SQLException e) {
			System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE SELEZIONATORE NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST INSERT -> FUNZIONANTE
		try {
			opBean.setEmail("mariano.fibia@almalaboris.com");
			opBean.setNome("Mariano");
			opBean.setCognome("Fibia");
			opBean.setRuolo("SELEZIONATORE");
			opBean.setCap("84013");
			opBean.setCitta("Nocera Inferiore");
			opBean.setIndirizzo("Via Nazionale 45");
			opBean.setProvincia("SA");
			opBean.setTelefono("3568899774");
			opBean.setDataNascita(LocalDateTime.now().withNano(0));

			opDao.insert(opBean, GestorePassword.generateInitPassword(opBean));
			System.out.println("INSERIMENTO DELL'OPERATORE SELEZIONATORE RIUSCITO:");
			System.out.println(opDao.retrieveByKey(opBean.getEmail()));
		} catch (SQLException e) {
			System.out.println("INSERIMENTO DELL'OPERATORE SELEZIONATORE NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST UPDATE -> FUNZIONANTE
		System.out.println("UTENTE NON AGGIORNATO: ");
		try {
			System.out.println(opDao.retrieveByKey("mariano.fibia@almalaboris.com"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("REPERIMENTO RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
			e.printStackTrace();
		}

		try {
			opBean.setCitta("Cava De Tirreni");
			opBean.setIndirizzo("Piazza Felice Baldi 4");
			opBean.setProvincia("SA");
			opBean.setTelefono("0000000000");
			opBean.setDataNascita(LocalDateTime.now().withNano(0));

			opDao.update(opBean);
			System.out.println("AGGIORNAMENTO OPERATORE SELEZIONATORE RIUSCITO");
		} catch (SQLException e) {
			System.out.println("AGGIORNAMENTO OPERATORE SELEZIONATORE NON RIUSCITO");
			e.printStackTrace();
		}

		System.out.println("UTENTE "+opBean.getEmail()+" AGGIORNATO: ");
		System.out.println(opBean);

		//TEST DELETE ->  FUNZIONANTE
		try {
			opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("REPERIMENTO OPERATORE SELEZIONATORE RIUSCITO");
			System.out.println(opBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO OPERATORE SELEZIONATORE NON RIUSCITO");
			e.printStackTrace();
		}

		try {
			opDao.delete("mariano.fibia@almalaboris.com");
			System.out.println("CANCELLAZIONE OPERATORE SELEZIONATORE RIUSCITO");
		} catch (SQLException e) {
			System.out.println("CANCELLAZIONE OPERATORE SELEZIONATORE NON RIUSCITO");
			e.printStackTrace();
		} 

		//TEST SET ESITO SELEZIONE -> FUNZIONANTE
		try {
			masBean = masDao.retrieveByKey(5);
			System.out.println("MASTER REPERITO CON SUCCESSO");
		} catch (SQLException e1) {
			System.out.println("MASTER NON REPERITO");
			e1.printStackTrace();
		}

		try {
			canBean.setEmail("Anna.Sernicola@gmail.com");
			canBean = canDao.retrieveByKey(canBean, masBean);
			System.out.println("CANDIDATO REPERITO CON SUCCESSO");
		} catch (SQLException e) {
			System.out.println("CANDIDATO NON REPERITO");
			e.printStackTrace();
		}

		try {
			opDao.setEsitoSelezione(canBean, true);
			System.out.println("SETTAGGIO ESITO SELEZIONE AVVENUTO CON SUCCESSO");
		} catch (SQLException e) {
			System.out.println("SETTAGGIO ESITO SELEZIONE NON AVVENUTO");
			e.printStackTrace();
		}

		 */

		/*	*********** TEST IMPL OPERATORE SEGRETERIA ***********
		OperatoreSegreteriaDAO opDao = new OperatoreSegreteria();
		OperatoreSegreteriaBean opBean = new OperatoreSegreteriaBean();
		Collection<OperatoreSegreteriaBean> opColBean = new ArrayList<OperatoreSegreteriaBean>();

		//TEST RETRIEVEBYKEY -> FUNZIONANTE
		try {
			opBean = opDao.retrieveByKey("segreteria@almalaboris.it");
			System.out.println("REPERIMENTO OPERATORE SEGRETERIA RIUSCITO:");
			System.out.println(opBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO OPERATORE SEGRETERIA NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST RETRIEVEALL -> FUNZIONANTE
		try {
			opColBean = opDao.retrieveAll("nome");
			System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE SEGRETERIA RIUSCITO:");
			for(OperatoreSegreteriaBean op: opColBean) {
				System.out.println(op);
			}
		} catch (SQLException e) {
			System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE SEGRETERIA NON RIUSCITO");
			e.printStackTrace();
		}
		/*
		//TEST INSERT -> FUNZIONANTE
		try {
			opBean.setEmail("mariano.fibia@almalaboris.com");
			opBean.setNome("Mariano");
			opBean.setCognome("Fibia");
			opBean.setRuolo("SEGRETERIA");
			opBean.setCap("84013");
			opBean.setCitta("Nocera Inferiore");
			opBean.setIndirizzo("Via Nazionale 45");
			opBean.setProvincia("SA");
			opBean.setTelefono("3568899774");
			opBean.setDataNascita(LocalDateTime.now().withNano(0));

			opDao.insert(opBean, GestorePassword.generateInitPassword(opBean));
			System.out.println("INSERIMENTO DELL'OPERATORE SEGRETERIA RIUSCITO:");
			System.out.println(opDao.retrieveByKey(opBean.getEmail()));
		} catch (SQLException e) {
			System.out.println("INSERIMENTO DELL'OPERATORE SEGRETERIA NON RIUSCITO");
			e.printStackTrace();
		}

		//TEST UPDATE -> FUNZIONANTE
		System.out.println("UTENTE NON AGGIORNATO: ");
		try {
			System.out.println(opDao.retrieveByKey("mariano.fibia@almalaboris.com"));
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("REPERIMENTO RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
		} catch (SQLException e) {
			System.out.println("REPERIMENTO NON RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
			e.printStackTrace();
		}

		try {
			opBean.setCitta("Cava De Tirreni");
			opBean.setIndirizzo("Piazza Felice Baldi 4");
			opBean.setProvincia("SA");
			opBean.setTelefono("0000000000");
			opBean.setDataNascita(LocalDateTime.now().withNano(0));

			opDao.update(opBean);
			System.out.println("AGGIORNAMENTO OPERATORE SEGRETERIA RIUSCITO");
		} catch (SQLException e) {
			System.out.println("AGGIORNAMENTO OPERATORE SEGRETERIA NON RIUSCITO");
			e.printStackTrace();
		}

		System.out.println("UTENTE "+opBean.getEmail()+" AGGIORNATO: ");
		System.out.println(opBean);

		//TEST DELETE ->  FUNZIONANTE
		try {
			opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
			System.out.println("REPERIMENTO OPERATORE SEGRETERIA RIUSCITO");
			System.out.println(opBean);
		} catch (SQLException e) {
			System.out.println("REPERIMENTO OPERATORE SEGRETERIA NON RIUSCITO");
			e.printStackTrace();
		}

		try {
			opDao.delete("mariano.fibia@almalaboris.com");
			System.out.println("CANCELLAZIONE OPERATORE SEGRETERIA RIUSCITO");
		} catch (SQLException e) {
			System.out.println("CANCELLAZIONE OPERATORE SEGRETERIA NON RIUSCITO");
			e.printStackTrace();
		} 

	//...

		 */	

		/*	*********** TEST IMPL OPERATORE FRONT OFFICE ***********
	OperatoreFrontOfficeDAO opDao = new OperatoreFrontOffice();
	OperatoreFrontOfficeBean opBean = new OperatoreFrontOfficeBean();
	Collection<OperatoreFrontOfficeBean> opColBean = new ArrayList<OperatoreFrontOfficeBean>();

	//TEST RETRIEVEBYKEY -> FUNZIONANTE
	try {
		opBean = opDao.retrieveByKey("frontoffice@almalaboris.com");
		System.out.println("REPERIMENTO OPERATORE FRONT OFFICE RIUSCITO:");
		System.out.println(opBean);
	} catch (SQLException e) {
		System.out.println("REPERIMENTO OPERATORE FRONT OFFICE NON RIUSCITO");
		e.printStackTrace();
	}

	//TEST RETRIEVEALL -> FUNZIONANTE
	try {
		opColBean = opDao.retrieveAll("nome");
		System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE FRON OFFICE RIUSCITO:");
		for(OperatoreFrontOfficeBean op: opColBean) {
			System.out.println(op);
		}
	} catch (SQLException e) {
		System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE FRONT OFFICE NON RIUSCITO");
		e.printStackTrace();
	}

	/*
	//TEST INSERT -> FUNZIONANTE
	try {
		opBean.setEmail("mariano.fibia@almalaboris.com");
		opBean.setNome("Mariano");
		opBean.setCognome("Fibia");
		opBean.setRuolo("FRONTOFFICE");
		opBean.setCap("84013");
		opBean.setCitta("Nocera Inferiore");
		opBean.setIndirizzo("Via Nazionale 45");
		opBean.setProvincia("SA");
		opBean.setTelefono("3568899774");
		opBean.setDataNascita(LocalDateTime.now().withNano(0));

		opDao.insert(opBean, GestorePassword.generateInitPassword(opBean));
		System.out.println("INSERIMENTO DELL'OPERATORE FRONT OFFICE RIUSCITO:");
		System.out.println(opDao.retrieveByKey(opBean.getEmail()));
	} catch (SQLException e) {
		System.out.println("INSERIMENTO DELL'OPERATORE FRONT OFFICE NON RIUSCITO");
		e.printStackTrace();
	}	

	//TEST UPDATE -> FUNZIONANTE
	System.out.println("UTENTE NON AGGIORNATO: ");
	try {
		System.out.println(opDao.retrieveByKey("mariano.fibia@almalaboris.com"));
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
		System.out.println("REPERIMENTO RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
	} catch (SQLException e) {
		System.out.println("REPERIMENTO NON RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
		e.printStackTrace();
	}

	try {
		opBean.setCitta("Cava De Tirreni");
		opBean.setIndirizzo("Piazza Felice Baldi 4");
		opBean.setProvincia("SA");
		opBean.setTelefono("0000000000");
		opBean.setDataNascita(LocalDateTime.now().withNano(0));

		opDao.update(opBean);
		System.out.println("AGGIORNAMENTO OPERATORE FRONT OFFICE RIUSCITO");
	} catch (SQLException e) {
		System.out.println("AGGIORNAMENTO OPERATORE FRONT OFFICE NON RIUSCITO");
		e.printStackTrace();
	}

	System.out.println("UTENTE "+opBean.getEmail()+" AGGIORNATO: ");
	System.out.println(opBean);

	//TEST DELETE ->  FUNZIONANTE
	try {
		opBean = opDao.retrieveByKey("mariano.fibia@almalaboris.com");
		System.out.println("REPERIMENTO OPERATORE FRONT OFFICE RIUSCITO");
		System.out.println(opBean);
	} catch (SQLException e) {
		System.out.println("REPERIMENTO OPERATORE FRONT OFFICE NON RIUSCITO");
		e.printStackTrace();
	}

	try {
		opDao.delete("mariano.fibia@almalaboris.com");
		System.out.println("CANCELLAZIONE OPERATORE FRONT OFFICE RIUSCITO");
	} catch (SQLException e) {
		System.out.println("CANCELLAZIONE OPERATORE FRONT OFFICE NON RIUSCITO");
		e.printStackTrace();
	}

	//...

		 */

		/*  *********** TEST IMPL OPERATORE DIREZIONE *********** 
	OperatoreDirezioneDAO opDao = new OperatoreDirezione();
	OperatoreDirezioneBean opBean = new OperatoreDirezioneBean();
	Collection<OperatoreDirezioneBean> opColBean = new ArrayList<OperatoreDirezioneBean>();

	//TEST RETRIEVEBYKEY -> FUNZIONANTE
	try {
		opBean = opDao.retrieveByKey("amministrativo@almalaboris.com");
		System.out.println("REPERIMENTO OPERATORE DIREZIONE RIUSCITO:");
		System.out.println(opBean);
	} catch (SQLException e) {
		System.out.println("REPERIMENTO OPERATORE DIREZIONE NON RIUSCITO");
		e.printStackTrace();
	}

	//TEST RETRIEVEALL -> FUNZIONANTE
	try {
		opColBean = opDao.retrieveAll(null);
		System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE DIREZIONE RIUSCITO:");
		for(OperatoreDirezioneBean op: opColBean) {
			System.out.println(op);
		}
	} catch (SQLException e) {
		System.out.println("REPERIMENTO DELLA LISTA DI OPERATORE DIREZIONE NON RIUSCITO");
		e.printStackTrace();
	}

	//TEST INSERT
	try {
		opBean.setEmail("mariano.fibia@almalaboris.com");
		opBean.setNome("Mariano");
		opBean.setCognome("Fibia");
		opBean.setRuolo("DIREZIONE");
		opBean.setCap("84013");
		opBean.setCitta("Nocera Inferiore");
		opBean.setIndirizzo("Via Nazionale 45");
		opBean.setProvincia("SA");
		opBean.setTelefono("3568899774");
		opBean.setDataNascita(LocalDateTime.now().withNano(0));

		opDao.insert(opBean, GestorePassword.generateInitPassword(opBean));
		System.out.println("INSERIMENTO DELL'OPERATORE DIREZIONE RIUSCITO:");
		System.out.println(opDao.retrieveByKey(opBean.getEmail()));
	} catch (SQLException e) {
		System.out.println("INSERIMENTO DELL'OPERATORE DIREZIONE NON RIUSCITO");
		e.printStackTrace();
	}

	//TEST UPDATE -> FUNZIONANTE
	System.out.println("UTENTE NON AGGIORNATO: ");
	try {
		System.out.println(opDao.retrieveByKey("ernesto.sparalesto@almalaboris.com"));
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {
		opBean = opDao.retrieveByKey("ernesto.sparalesto@almalaboris.com");
		System.out.println("REPERIMENTO RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
	} catch (SQLException e) {
		System.out.println("REPERIMENTO NON RIUSCITO DELL'UTENTE: "+opBean.getNome()+" "+opBean.getCognome());
		e.printStackTrace();
	}

	try {
		opBean.setCitta("Cava De Tirreni");
		opBean.setIndirizzo("Piazza Felice Baldi 4");
		opBean.setProvincia("SA");
		opBean.setTelefono("0000000000");
		opBean.setDataNascita(LocalDateTime.now().withNano(0));

		opDao.update(opBean);
		System.out.println("AGGIORNAMENTO OPERATORE DIREZIONE RIUSCITO");
	} catch (SQLException e) {
		System.out.println("AGGIORNAMENTO OPERATORE DIREZIONE NON RIUSCITO");
		e.printStackTrace();
	}

	System.out.println("UTENTE "+opBean.getEmail()+" AGGIORNATO: ");
	System.out.println(opBean);

	//TEST DELETE -> FUNZIONANTE
	try {
		opBean = opDao.retrieveByKey("ernesto.sparalesto@almalaboris.com");
		System.out.println("REPERIMENTO OPERATORE DIREZIONE RIUSCITO");
		System.out.println(opBean);
	} catch (SQLException e) {
		System.out.println("REPERIMENTO OPERATORE DIREZIONE NON RIUSCITO");
		e.printStackTrace();
	}

	try {
		opDao.delete("ernesto.sparalesto@almalaboris.com");
		System.out.println("CANCELLAZIONE OPERATORE DIREZIONE RIUSCITO");
	} catch (SQLException e) {
		System.out.println("CANCELLAZIONE OPERATORE DIREZIONE NON RIUSCITO");
		e.printStackTrace();
	} */

		/*
	//TEST UPDATE DI CANDIDATO CON RETRIEVE BY KEY
	CandidatoDAO canDao = new Candidato();
	CandidatoBean customerBean = new CandidatoBean();
	MasterDAO mastDao = new Master();
	MasterBean master = new MasterBean();
	UtenteDAO utenteDao = new Utente();

	UtenteBean userBean = new UtenteBean();

	try {
		userBean = utenteDao.retrieveByKey("Anna.Sernicola@gmail.com");
		System.out.println("Reperimento Utente riuscito: "+userBean.toString());
	} catch (SQLException e2) {
		System.out.println("Reperimento Utente non riuscito");
		e2.printStackTrace();
	}

	try {
		master = mastDao.retrieveByKey(5);
		System.out.println("Reperimento master riuscito");
	} catch (SQLException e1) {
		System.out.println("Reperimento master non riuscito\"");
		e1.printStackTrace();
	}

	customerBean.setEmail(userBean.getEmail());

	try {
		customerBean = canDao.retrieveByKey(customerBean, master);
		System.out.println("Reperimento Candidato riuscito: "+customerBean.toString());
	} catch (SQLException e) {
		System.out.println("Reperimento Candidato non riuscito");
		e.printStackTrace();
	}

	LocalDateTime dataAggiornata =  LocalDateTime.now().withNano(0);
	customerBean.setDataInserimento(dataAggiornata);

	try {
		canDao.update(customerBean);
		System.out.println("Aggiornamento riuscito");
	} catch (SQLException e) {
		System.out.println("Aggiornamento non riuscito");
		e.printStackTrace();
	} 

	System.out.println("CANDIDATO AGGIORNATO : "+customerBean.toString());
		 */

		/*
	//TEST DATA PARSE CON LOCAL DATE TIME
	LocalDateTime dataOrigin = LocalDateTime.now().withNano(0);
	System.out.println("Data Origin: "+dataOrigin);

	//DA LOCAL DATE TIME A STRINGA 
	String dataStringa =  Dizionario.DATE_FORMAT.format(dataOrigin).toString();

	System.out.println("Data Post Format: " + dataStringa);

	//DA STRINGA A LOCAL DATE TIME
	LocalDateTime dataDaStringa = LocalDateTime.parse(dataStringa, Dizionario.DATE_FORMAT);

	System.out.println("Data LocalDateTime da Stringa: "+dataDaStringa);
		 */

		/* TEST GENERAZIONE PASSWORD

	UtenteBean utente = new UtenteBean();
	utente.setCap("84013");
	utente.setCitta("Nocera");
	utente.setCognome("zzzy12");
	utente.setDataNascita(null);
	utente.setEmail("frabotta@gmail.com");
	utente.setIndirizzo("via Nazionale 45");
	utente.setNome("12xuuu");
	utente.setProvincia("SA");
	utente.setRuolo("CANDIDATO");
	utente.setTelefono("3295566785");

	CandidatoBean candidato = new CandidatoBean();
	candidato = (CandidatoBean) utente;

	System.out.println(GestorePassword.generateInitPassword(utente)); */

		/* TEST INSERIMENTO UTENTE
			String pw = "fraLui@123";

			UtenteDAO utenteDao = new Utente();
			try {
				utenteDao.insert(utente, pw);
				System.out.println("Inserimento Utente Riuscito");
			} catch (SQLException e) {
				System.out.println("Errore nell'inserimento dell'utente");
				e.printStackTrace();
			}

			CandidatoDAO candidatoDao = new Candidato();
			CandidatoBean candidato = (CandidatoBean) utente;

			try {
				candidatoDao.insert(candidato, pw);
				System.out.println("Inserimento Candidato Riuscito");
			} catch (SQLException e) {
				System.out.println("Errore nell'inserimento del candidato");
				e.printStackTrace();
			}

		 */

	}

}
