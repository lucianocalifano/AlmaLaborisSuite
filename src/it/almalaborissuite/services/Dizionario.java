package it.almalaborissuite.services;

import java.time.format.DateTimeFormatter;

public class Dizionario {
	
	/**
	 * Rappresenta la tabella aula nel db
	 */
	public static final String TABLE_AULA = "als_aula";
	
	/**
	 * Rappresenta la tabella colloquio nel db
	 */
	public static final String TABLE_COLLOQUIO = "als_colloquio";
	
	/**
	 * Rappresenta la tabella chiamata nel db
	 */
	public static final String TABLE_CHIAMATA = "als_chiamata";
	
	/**
	 * Rappresenta la tabella nel candidato nel db
	 */
	public static final String TABLE_CANDIDATO = "als_candidato";
	
	/**
	 * Rappresenta la tabella edizione nel db
	 */
	public static final String TABLE_EDIZIONE = "als_edizione";
	
	/**
	 * Rappresenta la tabella master nel db
	 */
	public static final String TABLE_MASTER = "als_master";
	
	/**
	 * Rappresenta la tabella operatore advertiser nel db
	 */
	public static final String TABLE_OPERATORE_ADV = "als_operatore_advertiser";
	
	/**
	 * Rappresenta la tabella opearatore content nel db
	 */
	public static final String TABLE_OPERATORE_CON = "als_operatore_content";
	
	/**
	 * Rappresenta la tabella opearatore data entry nel db
	 */
	public static final String TABLE_OPERATORE_DAT = "als_operatore_dataentry";
	
	/**
	 * Rappresenta la tabella opearatore direzione nel db
	 */
	public static final String TABLE_OPERATORE_DIR = "als_operatore_direzione";
	
	/**
	 * Rappresenta la tabella opearatore front office nel db
	 */
	public static final String TABLE_OPERATORE_FRO = "als_operatore_frontoffice";
	
	/**
	 * Rappresenta la tabella opearatore segreteria nel db
	 */
	public static final String TABLE_OPERATORE_SEG = "als_operatore_segreteria";
	
	/**
	 * Rappresenta la tabella opearatore selezionatore nel db
	 */
	public static final String TABLE_OPERATORE_SEL = "als_operatore_selezionatore";
	
	/**
	 * Rappresenta la tabella opearatore tecnico nel db
	 */
	public static final String TABLE_OPERATORE_TEC = "als_operatore_tecnico";
	
	/**
	 * Rappresenta la tabella partecipante nel db
	 */
	public static final String TABLE_PAX = "als_partecipante";
	
	/**
	 * Rappresenta la tabella sede nel db
	 */
	public static final String TABLE_SEDE = "als_sede";
	
	/**
	 * Rappresenta la tabella settore nel db
	 */
	public static final String TABLE_SETTORE = "als_settore";
	
	/**
	 * Rappresenta la tabella stato nel db
	 */
	public static final String TABLE_STATO = "als_stato";
	
	/**
	 * Rappresenta la tabella utente nel db
	 */
	public static final String TABLE_UTENTE = "als_utente";
	
	/**
	 * Rappresenta il formato della data per le varbiabili di tipo LocalDateTime dei Bean
	 */
	public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Rapppresenta l'API KEY di Elastic Mail
	 * OLD API KEY: "c1c5febe-1e08-460c-87e6-01c357457896" 
	 */
    protected static final String API_KEY = "E8747411849278ACEFCC81B0A66EA37BC7B3512E62175FE5BBA045CDFC5876531ACD39F3E5024C70C71ED7DD403CA686";
    
    /**
     * Rappresenta l'API URI di Elastic Mail
     */
    protected static final String API_URI = "https://api.elasticemail.com/v2";
    
    /**
     * Rappresenta la ragione sociale di Alma Laboris Business School
     */
    public static final String COMPANY_NAME = "ALMA LABORIS Business School";
    
    /**
     * Rappresenta la mail designata all'invio di comunicazioni in Alma Laboris
     */
    public static final String EMAIL_SENDER = "segreteria@almalaboris.com";
    
}
