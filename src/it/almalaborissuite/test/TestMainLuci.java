package it.almalaborissuite.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import it.almalaborissuite.bean.AulaBean;
import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.ChiamataBean;
import it.almalaborissuite.bean.ColloquioBean;
import it.almalaborissuite.bean.EdizioneBean;
import it.almalaborissuite.bean.MasterBean;
import it.almalaborissuite.bean.OperatoreAdvertiserBean;
import it.almalaborissuite.bean.OperatoreContentBean;
import it.almalaborissuite.bean.OperatoreDataEntryBean;
import it.almalaborissuite.bean.OperatoreDirezioneBean;
import it.almalaborissuite.bean.OperatoreFrontOfficeBean;
import it.almalaborissuite.bean.SedeBean;
import it.almalaborissuite.bean.SettoreBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.dao.AulaDAO;
import it.almalaborissuite.dao.CandidatoDAO;
import it.almalaborissuite.dao.ChiamataDAO;
import it.almalaborissuite.dao.ColloquioDAO;
import it.almalaborissuite.dao.EdizioneDAO;
import it.almalaborissuite.dao.MasterDAO;
import it.almalaborissuite.dao.OperatoreAdvertiserDAO;
import it.almalaborissuite.dao.OperatoreContentDAO;
import it.almalaborissuite.dao.OperatoreDataEntryDAO;
import it.almalaborissuite.dao.OperatoreDirezioneDAO;
import it.almalaborissuite.dao.OperatoreFrontOfficeDAO;
import it.almalaborissuite.dao.SedeDAO;
import it.almalaborissuite.dao.SettoreDAO;
import it.almalaborissuite.dao.UtenteDAO;
import it.almalaborissuite.impl.Aula;
import it.almalaborissuite.impl.Candidato;
import it.almalaborissuite.impl.Chiamata;
import it.almalaborissuite.impl.Colloquio;
import it.almalaborissuite.impl.Edizione;
import it.almalaborissuite.impl.Master;
import it.almalaborissuite.impl.OperatoreAdvertiser;
import it.almalaborissuite.impl.OperatoreContent;
import it.almalaborissuite.impl.OperatoreDataEntry;
import it.almalaborissuite.impl.OperatoreDirezione;
import it.almalaborissuite.impl.OperatoreFrontOffice;
import it.almalaborissuite.impl.Sede;
import it.almalaborissuite.impl.Settore;
import it.almalaborissuite.impl.Utente;

public class TestMainLuci {

	public static void main(String[] args) {
		
		//TEST READ AND WRITE EXCEL CANDIDATO
		//ReadExcel read=new ReadExcel();
		//CandidatoDAO cdao= new Candidato();
		/*try {
			read.insertCandidatiFromExcel("C:/prova1.xls");
			//cdao.insertCandidatiFromExcel("C:/prova1.xls");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//TEST OPERATORI ,tutti i metodi
		/*OperatoreAdvertiserBean adbean = new OperatoreAdvertiserBean();
		OperatoreAdvertiserDAO daoadbean = new OperatoreAdvertiser();
		OperatoreContentBean contbean = new OperatoreContentBean();
		OperatoreContentDAO contdao= new OperatoreContent();
		OperatoreDataEntryBean entrybean = new OperatoreDataEntryBean();
		OperatoreDataEntryDAO entrydao= new OperatoreDataEntry();
		Collection<OperatoreAdvertiserBean> listad = new ArrayList<>();
		Collection<OperatoreContentBean> listcont = new ArrayList<>();
		Collection<OperatoreDataEntryBean> listdata = new ArrayList<>();
		try {
			adbean= daoadbean.retrieveByKey("advertiser@almalaboris.com");
			contbean=contdao.retrieveByKey("adv.content@almalaboris.com");
			entrybean= entrydao.retrieveByKey("annalisa.dammacco@almalaboris.com");
			listad= daoadbean.retrieveAll("email");
			listcont= contdao.retrieveAll("email");
			listdata= entrydao.retrieveAll("email");
			
			//insert Operatori tutte funzionanti
			entrybean.setEmail("nuovaannalisa@email.it");
			entrydao.insert(entrybean, "password");
			System.out.println(entrybean.toString());
			
			//delete tutte funzionanti
			//daoadbean.delete(adbean.getEmail());
			//contdao.delete(contbean.getEmail());
			//entrydao.delete(entrybean.getEmail());
			
			//update tutte funzionanti
			adbean.setEmail("Ernesto.medito@gmail.com");
			daoadbean.update(adbean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(OperatoreDataEntryBean b : listdata)
		{
			System.out.println(b.toString());
		}*/
		
		
		
		//TEST MASTERBEAN getMasterPerNome()
		/*MasterBean mbean= new MasterBean();
		MasterDAO mdao= new Master();
		try {
			mbean= mdao.getMasterPerNome("Energy Management");
			System.out.println(mbean.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST MASTERBEAN tutte le Collections() 
		/*Collection<MasterBean> list = new ArrayList<>();
		MasterDAO mdao= new Master();
		MasterBean mbean= new MasterBean();
		SettoreBean sbean= new SettoreBean();
		SettoreDAO sdao= new Settore();
		CandidatoBean cbean= new CandidatoBean();
		CandidatoDAO candao= new Candidato();
		 try {
			sbean= sdao.retrieveByKey(2);
			//list= cdao.getMastersPerSettore(sbean);
			//list= cdao.getMastersPerEdizione("MARZO 2020");
			//cbean= candao.retrieveByKey("Anna.Sernicola@gmail.com"); //quindi funz. anche quello singolo
			//list= mdao.getMastersPerCandidato(cbean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 for(MasterBean b : list)
			{
				System.out.println(b.toString());
			}*/
		
		//TEST MASTERBEAN delete
		/*MasterDAO mdao= new Master();
		try {
			mdao.delete(16);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST MASTERBEAN update
		/*MasterBean bean= new MasterBean();
		MasterDAO mdao= new Master();
		SettoreBean sbean= new SettoreBean();
		SettoreDAO sdao= new Settore();
		try {
			    bean= mdao.retrieveByKey(1);
				sbean= sdao.retrieveByKey(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bean.setNome("update-master");
			mdao.update(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST MASTERBEAN insert
		/*MasterBean bean= new MasterBean();
		MasterDAO mdao= new Master();
		SettoreBean sbean= new SettoreBean();
		SettoreDAO sdao= new Settore();
		try {
				sbean= sdao.retrieveByKey(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setId(16);
		bean.setNome("nuovo Master");
		bean.setTag("nuovo-tag");
		bean.setSettore(sbean);
		try {
			mdao.insert(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//TEST MASTERBEAN retrieveAll
		/*MasterBean bean= new MasterBean();
		Collection<MasterBean> list = new ArrayList<>();
		MasterDAO cdao= new Master();
		 try {
			list= cdao.retrieveAll("nome");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 for(MasterBean b : list)
			{
				System.out.println(b.toString());
			}*/
		
		
		//TEST MASTERBEAN retrieveBykey
		/*MasterBean bean= new MasterBean();
		MasterDAO cdao= new Master();
		try {
			bean = cdao.retrieveByKey(1);
			System.out.println(bean.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//TEST EDIZIONEBEAN Collections()
		/*EdizioneBean bean= new EdizioneBean();
		Collection<EdizioneBean> list = new ArrayList<>();
		EdizioneDAO cdao= new Edizione();
		String data = "2021-03-05 11:00:00";
		 try {
			list= cdao.getEdizioni();
			list=cdao.getEdizioniByName("MARZO 2020");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 for(EdizioneBean b : list)
			{
				System.out.println(b.toString());
			}*/
		

		
		//TEST EDIZIONEBEAN delete
		/*EdizioneDAO cdao= new Edizione();
		try {
			cdao.delete(16);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST EDIZIONEBEAN update
		/*EdizioneBean bean= new EdizioneBean();
		EdizioneDAO cdao= new Edizione();
		MasterBean master= new MasterBean();
		MasterDAO mdao= new Master();
		AulaDAO adao= new Aula();
		AulaBean aula = new AulaBean();
		String datainvioscheda = "2022-03-05 11:00:00";
		String datainiziovf = "2022-03-05 11:00:00";
		String datafinevf = "2022-03-05 11:00:00";
		String datainizio = "2022-03-05 11:00:00";
		String datafine= "2022-03-05 11:30:00";
		try {
			master= mdao.retrieveByKey(1);
			aula= adao.retrieveByKey(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setId(16);
		bean.setNome("MAGGIO 2020");
		bean.setDataInvioScheda(LocalDateTime.parse(datainvioscheda, Dizionario.DATE_FORMAT));
		bean.setDataInizio(LocalDateTime.parse(datainizio, Dizionario.DATE_FORMAT));
		bean.setDataFine(LocalDateTime.parse(datafine, Dizionario.DATE_FORMAT));
		bean.setInizioVf(LocalDateTime.parse(datainiziovf, Dizionario.DATE_FORMAT));
		bean.setFineVf(LocalDateTime.parse(datafinevf, Dizionario.DATE_FORMAT));
		bean.setMaster(master);
		bean.setAula(aula);
		try {
			cdao.update(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//TEST insert EDIZIONEBEAN
		/*EdizioneBean bean= new EdizioneBean();
		EdizioneDAO edao= new Edizione();
		MasterBean master= new MasterBean();
		MasterDAO mdao= new Master();
		AulaDAO adao= new Aula();
		AulaBean aula = new AulaBean();
		String datainvioscheda = "2022-03-05 11:00:00";
		String datainiziovf = "2022-03-05 11:00:00";
		String datafinevf = "2022-03-05 11:00:00";
		String datainizio = "2022-03-05 11:00:00";
		String datafine= "2022-03-05 11:30:00";
		try {
				master= mdao.retrieveByKey(1);
				aula= adao.retrieveByKey(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setId(16);
		bean.setNome("MAGGIO 2020");
		bean.setDataInvioScheda(LocalDateTime.parse(datainvioscheda, Dizionario.DATE_FORMAT));
		bean.setDataInizio(LocalDateTime.parse(datainizio, Dizionario.DATE_FORMAT));
		bean.setDataFine(LocalDateTime.parse(datafine, Dizionario.DATE_FORMAT));
		bean.setInizioVf(LocalDateTime.parse(datainiziovf, Dizionario.DATE_FORMAT));
		bean.setFineVf(LocalDateTime.parse(datafinevf, Dizionario.DATE_FORMAT));
		bean.setMaster(master);
		bean.setAula(aula);
		try {
			edao.insert(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//TEST EDIZIONEBEAN retrieveByKey
		/*EdizioneBean bean= new EdizioneBean();
		EdizioneDAO cdao= new Edizione();
		try {
			bean = cdao.retrieveByKey(1);
			System.out.println(bean.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST EDIZIONEBEAN Collections() retrieveAll
		/*EdizioneBean bean= new EdizioneBean();
		Collection<EdizioneBean> list = new ArrayList<>();
		EdizioneDAO cdao= new Edizione();
		String data = "2021-03-05 11:00:00";
		 try {
			list= cdao.retrieveAll("nome");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 for(EdizioneBean b : list)
			{
				System.out.println(b.toString());
			}
		
		
		
		
		
		
		//TEST COLLOQUIOBEAN update
		/*ColloquioBean bean= new ColloquioBean();
		ColloquioDAO cdao= new Colloquio();
		SedeBean sede= new SedeBean(1,"Polo Didattico Milano","Centro Congressi Cantoni, 7" ,"Via Giovanni Cantoni", "20144" ,"Milano");
		UtenteBean utente= new UtenteBean();
		UtenteDAO udao= new Utente();
		String datainizio = "2022-03-05 15:00:00";
		String datafine= "2022-03-05 15:30:00";
		try {
			utente= udao.retrieveByKey("Tiziano.cartone@gmail.com");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setId(5);
		bean.setUtente(utente);
		bean.setData_inzio(LocalDateTime.parse(datainizio, Dizionario.DATE_FORMAT));
		bean.setData_fine(LocalDateTime.parse(datafine, Dizionario.DATE_FORMAT));
		bean.setTipo("TELEFONICO");
		bean.setSedeColloquio(sede);
		try {
			cdao.update(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST COLLOQUIOBEAN delete
		/*ColloquioDAO cdao= new Colloquio();
		try {
			cdao.delete(3);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST INSERT COLLOQUIOBEAN
		/*ColloquioBean bean= new ColloquioBean();
		ColloquioDAO cdao= new Colloquio();
		SedeBean sede= new SedeBean(1,"Polo Didattico Milano","Centro Congressi Cantoni, 7" ,"Via Giovanni Cantoni", "20144" ,"Milano");
		UtenteBean utente= new UtenteBean();
		UtenteDAO udao= new Utente();
		String datainizio = "2022-03-05 11:00:00";
		String datafine= "2022-03-05 11:30:00";
		try {
			utente= udao.retrieveByKey("Tiziano.cartone@gmail.com");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean.setId(5);
		bean.setUtente(utente);
		bean.setData_inzio(LocalDateTime.parse(datainizio, Dizionario.DATE_FORMAT));
		bean.setData_fine(LocalDateTime.parse(datafine, Dizionario.DATE_FORMAT));
		bean.setTipo("TELEFONICO");
		bean.setSedeColloquio(sede);
		try {
			cdao.insert(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
		//TEST COLLOQUIOBEAN retrieveByKey
		/*ColloquioBean bean= new ColloquioBean();
		ColloquioDAO cdao= new Colloquio();
		try {
			bean = cdao.retrieveByKey(1);
			System.out.println(bean.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		 
		//TEST COLLOQUIOBEAN Collections() retrieveAll, getColloquiPerData
		/*ColloquioBean bean= new ColloquioBean();
		Collection<ColloquioBean> list = new ArrayList<>();
		ColloquioDAO cdao= new Colloquio();
		String data = "2021-03-05 11:00:00";
		 try {
			list= cdao.retrieveAll("utente");
			list= cdao.getColloquiPerData(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 for(ColloquioBean b : list)
			{
				System.out.println(b.toString());
			}*/
		
		
		//TEST AULA ANDATI TUTTI A BUON FINE
		
		
		//TEST CHIAMATABEAN retrieveByKey
		/*ChiamataBean chiamata= new ChiamataBean();
		ChiamataDAO cdao= new Chiamata();
		try {
			chiamata = cdao.retrieveByKey(2);
			System.out.println(chiamata.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST insert e update CHIAMATABEAN funzionanti.
		/*ChiamataBean bean= new ChiamataBean();
		ChiamataDAO cdao= new Chiamata();
		UtenteBean utente= new UtenteBean();
		OperatoreFrontOfficeBean op= new OperatoreFrontOfficeBean();
		OperatoreFrontOfficeDAO opdao= new OperatoreFrontOffice();
		UtenteDAO udao= new Utente();
		try {
			utente= udao.retrieveByKey("elena.bisola@gmail.com");
			op= opdao.retrieveByKey("frontoffice@almalaboris.com");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//bean.setId(34);
		bean.setUtente(utente);
		bean.setOperatore(op);
		bean.setData_chiamata(LocalDateTime.now().withNano(0));
		bean.setMotivo("Sollecito Pagamento");
		bean.setTipo("EFFETTUATA");
		try {
			//cdao.insert(bean);
			cdao.update(bean);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//TEST CHIAMATA BEAN delete
		/*ChiamataDAO cdao= new Chiamata();
		try {
			cdao.delete(29);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		//TEST CHIAMATABEAN retrieveAll()
		/*ChiamataBean chiamata= new ChiamataBean();
		Collection<ChiamataBean> list = new ArrayList<>();
		ChiamataDAO cdao= new Chiamata();
		try {
			list=cdao.retrieveAll("utente");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(ChiamataBean b : list)
		{
			System.out.println(b.toString());
		}*/
		

		//TEST insert CandidatoBean
		/*CandidatoBean cand= new CandidatoBean();
		cand.setEmail("luciano.c1@outlook.it");
		cand.setRuolo("CANDIDATO");
		cand.setNome("Luciano");
		cand.setCognome("Califano");
		cand.setTelefono("00000");
		cand.setIndirizzo("Via Napoli");
		cand.setCap("84014");
		cand.setCitta("Nocera");
		cand.setProvincia("salerno");
		cand.setDataNascita(LocalDateTime.now().withNano(0));
		CandidatoDAO canddao= new Candidato();
		SettoreBean settore= new SettoreBean(6,"Energia");
		MasterBean master = new MasterBean(2,"Energy Management","Energy",settore);
		cand.setMaster(master);
		cand.setFonte("LINKEDIN");
		cand.setFormatScelto("AULA");
		//Collection<CandidatoBean> list = new ArrayList<>();
		try {
			  canddao.insert(cand, "password");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*for(AulaBean b : list)
		{
			System.out.println(b.getNome());
		}
		System.out.println(cand.toString());*/
		
		
		//TEST CandidatoBean Collections(),retrieveAll,delete,update,retrieveByKey;
		/*Collection<CandidatoBean> list = new ArrayList<>();
		CandidatoDAO canddao= new Candidato();
		CandidatoBean bean= new CandidatoBean();
		SettoreBean settore= new SettoreBean(6,"Energia");
		MasterBean master = new MasterBean(2,"Energy Management","Energy",settore);
		try {
			  list=canddao.retrieveAll("email");
			  list=canddao.getCandidatiperMaster(master);
			  list=canddao.retrieveAll("email");
			  list=canddao.getCandidatiperFonte("LINKEDIN");
			  list=canddao.getCandidatiSchedaIscrizioneInviata();
			  list=canddao.getCandidatiSchedaIscrizioneApprovata();
			  list=canddao.getCandidatiQuietanza1Inviata()
			  list=canddao.getCandidatiSchedaIscrizioneApprovata();
			  list=canddao.getCandidatiInteressati();
			  list=canddao.getCandidatiContattati()
			  list=canddao.getCandidatiAttesaDataSelezione();
			  list=canddao.getCandidatiPresenzaSelezione();
		      list=canddao.getCandidatiAttesaEsitoSelezione();
			  list=canddao.getCandidatiAmmessi();
			  list=canddao.getCandidatiQuietanza1Nonpagata();
			  list=canddao.getCandidatiperFormat("AULA");
			  bean= canddao.retrieveByKey("Anna.Sernicola@gmail.com");
			  canddao.update(bean);
			  System.out.println(bean.toString());
			  list=canddao.getCandidatiSchedaIscrizioneNonInviata();
			  list=canddao.getCandidatiSchedaIscrizioneNonApprovata();
			  list=canddao.getCandidatiQuietanza1NonInviata();
			  list=canddao.getCandidatiNonInteressati();
			  list=canddao.getCandidatiNonContattati();
			  list=canddao.getCandidatiNonAttesaDataSelezione();
			  list=canddao.getCandidatiNonPresenzaSelezione();
			  list=canddao.getCandidatiNonAttesaEsitoSelezione();
			  list=canddao.getCandidatiNonAmmessi();
			  list=canddao.getCandidatiIscritti();
			  list=canddao.getCandidatiNonIscritti();
			  
			  
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		/*for(CandidatoBean b : list)
		{
			System.out.println(b.toString());
		}*/
		
	}
}
		
	

