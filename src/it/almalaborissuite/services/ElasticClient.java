package it.almalaborissuite.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

// --------------ElasticClient.java--------
// ULR FONTE: https://help.elasticemail.com/en/articles/2376685-how-to-send-email-with-attachments-via-api

import java.io.IOException;
import java.util.HashMap;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.almalaborissuite.bean.UtenteBean;

public class ElasticClient {
	
	public ElasticClient() {
		
	}
    
    /**
     * Consente di inviare una mail ad una lista di destinatari specificandone i parametri fondamentali
     * 
     * @param oggetto l'oggetto della mail
     * @param destinatari i destinatari della mail
     * @param tipoAllegato il tipo di allegato che dovrà contenere la mail
     * @param allegato il nome dell'allegato che si desidera inviare
     * @param template il template della mail che vogliamo inviare
     * @return true se la mail è stata inviata con successo false altrimenti
     */
  	public static boolean inviaEmail(String oggetto, Collection<UtenteBean> destinatari, String tipoAllegato, String allegato, String template) {
  		//Creo un'istanza della classe API
    	API client = new API();
        ArrayList<FileData>files = new ArrayList<>();
        boolean r = false;
        
        Path path = Paths.get("C:\\Users\\OperatoreEntry\\git\\AlmaLaborisSuite\\WebContent\\template\\"+tipoAllegato+"\\"+ allegato);
        byte[] data = null;
        try {
            data = Files.readAllBytes(path);
        } catch (IOException ex) {
            Logger.getLogger(ElasticClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        FileData contactsFile = new FileData();
        contactsFile.contentType = "application/pdf"; // Change correspondingly to the file type
        contactsFile.fileName = allegato;
        contactsFile.content = data;
        files.add(contactsFile);
        
        //devo scorrere tutti i destinatari
        for(UtenteBean destinatario : destinatari) {
        	//Per personalizzare l'invio occorre intervenire sul singolo template sovrascrivendolo quindi occorre passare alla map una stringa direttamente;
            HashMap<String, String> values = setMailMap(oggetto, destinatario, template);
            
            try {
                String result = client.httpPostFile("/email/send", files, values);
                System.out.println(result);
                r = true;
            } catch (Exception ex) {
                Logger.getLogger(ElasticClient.class.getName()).log(Level.SEVERE, null, ex);
            }  
      		
        }
        
  		return r;
  	}
 
  	/**
  	 * Permette di settare i parametri di una singola mail racchiudendoli in una HashMap
  	 *  
  	 * @param oggetto l'oggetto che la mail dovrà contenere
  	 * @param destinatario il destinatario della mail
  	 * @param fileTemplate il template della mail da inviare
  	 * @return HashMap contenete tutti i parametri per l'invio di una singola mail
  	 */
    private static HashMap<String, String> setMailMap(String oggetto, UtenteBean destinatario, String fileTemplate) {
    	HashMap<String, String> map = new HashMap<>();
    	String template = new String();
    	
    	template = convertTemplateString(destinatario, fileTemplate);    	
    	
    	map.put("apikey", Dizionario.API_KEY);
        map.put("from", Dizionario.EMAIL_SENDER);
        map.put("fromName", Dizionario.COMPANY_NAME);
        map.put("subject", oggetto);
        map.put("to", destinatario.getEmail());
        map.put("bodyHtml", template);
        
        return map;
    }
    
    /**
     * Consente di convertire il template della mail da inviare in una stringa univoca
     * già personalizzata per il destinatario
     * 
     * @param destinatario il destinatario a cui vogliamo inviare la mail
     * @param fileTemplate il tipo di email che vogliamo inviare
     * @return template della mail che voglimao inviare in una stringa univoca
     * @throws FileNotFoundException 
     */
    private static String convertTemplateString(UtenteBean destinatario, String fileTemplate) {
    	BufferedReader reader = null;
		
    	try {
			reader = new BufferedReader(new FileReader("C:\\Users\\OperatoreEntry\\git\\AlmaLaborisSuite\\WebContent\\template\\email\\"+fileTemplate));
		} catch (FileNotFoundException e1) {
			System.out.println("Errore in apertura del file template per la mail");
			e1.printStackTrace();
		}
    	
        String line = new String();
        
		try {
			line = reader.readLine();
		} catch (IOException e) {
			System.out.println("Errore in lettura del file template per la mail");
			e.printStackTrace();
		}
		
		String template = line;
        
        while(line != null) {
            System.out.println(line);
            try {
				line = reader.readLine();
				template += line;
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
    	
        template = template.replaceAll("##USERNAME##", destinatario.getNome()+" "+destinatario.getCognome());
        template = template.replaceAll("</html>null", "</html>");
        
        System.out.println("TEMPLATE EMAIL: ");
        System.out.println(template);
        
    	return template;
    }
    
    /**
     * Consente di ripulire la stringa ricavata dal toString() su una Collection di destinatari
     * (UTILE QUANDO OCCORRE INVIARE EMAIL DI MASSA)
     * 
     * @param recipient lista dei partecipanti ai quali inviare la mail
     * @return stringa univoca contenente le mail dei destinatari (format stringa email1; email2; ...)
     */
    private static String cleanStringRecipient(Collection<String> recipient) {
    	String app = recipient.toString();
    	int dim = app.length();
    	char[] dest = new char[dim];
    	   
    	for(int i =0; i < dim; i++){
    		dest[i] = app.charAt(i);
    	}
    	   
    	String finale = new String();
    	for(int i =0; i < dim; i++){
    		if(dest[i] != '[' && dest[i] != ']'){
    			if(dest[i] == ','){
    				finale += ';';
    			} else
    				finale += dest[i];
    		 }
    	 }
    	 
    	 return finale;
    }

    /*
    public static void main(String[] args) {
  	  UtenteDAO utenteDao = new Utente();
  	  UtenteBean utente = new UtenteBean();
  	  Collection<String> listaDest = new ArrayList<String>(); 
  	  Collection<UtenteBean> dest = new ArrayList<UtenteBean>();
      
  	  listaDest.add("adv.content@almalaboris.com");
  	  listaDest.add("adv@almalaboris.com");
  	  listaDest.add("luciano.califano@almalaboris.com");
  	  listaDest.add("vincenzo.pepe@almalaboris.com");
  	  listaDest.add("vincenzopepealmadev@gmail.com");
  	  
  	  for(String d : listaDest) {
  		  try {
  			dest.add(utenteDao.retrieveByKey(d));
  		} catch (SQLException e) {
  			e.printStackTrace();
  		}
  	  }
  	  
        inviaEmail("TEST CONSEGNATO", dest, "bandi", "Bando_Master_Risorse_Aula.pdf", "test_finale_inviato.txt");
        
      } */
}
