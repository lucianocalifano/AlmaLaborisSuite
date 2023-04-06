package it.almalaborissuite.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.almalaborissuite.bean.CandidatoBean;
import it.almalaborissuite.bean.OperatoreAdvertiserBean;
import it.almalaborissuite.bean.OperatoreContentBean;
import it.almalaborissuite.bean.OperatoreDataEntryBean;
import it.almalaborissuite.bean.OperatoreDirezioneBean;
import it.almalaborissuite.bean.OperatoreFrontOfficeBean;
import it.almalaborissuite.bean.OperatoreSegreteriaBean;
import it.almalaborissuite.bean.OperatoreSelezionatoreBean;
import it.almalaborissuite.bean.OperatoreTecnicoBean;
import it.almalaborissuite.bean.PartecipanteBean;
import it.almalaborissuite.bean.UtenteBean;
import it.almalaborissuite.facade.GestioneAccountFacade;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public LoginServlet() {
    super();
    // TODO Auto-generated constructor stub
  }

  /**
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    GestioneAccountFacade gestore = new GestioneAccountFacade();
    UtenteBean utente = null;

    // Controllo se le credenziali inserite sono != null
    if(email != null && password != null) {

      //Recupero delle informazioni sull'utente dal database
      utente = gestore.login(email, password);
    }

    //Se utente == null allora le credenziali inserite sono errate
    if(utente == null) {

      request.getSession().setAttribute("loginOK", false);
      String url=response.encodeRedirectURL("/index.jsp");
      RequestDispatcher dispatcher = request.getRequestDispatcher(url);
      dispatcher.forward(request, response);

    }else {
      //...altrimenti le credenziali sono giuste

      // Controllo del ruolo
      String ruolo = gestore.checkRuolo(utente);

      // Se ruolo != null allora prendo le info sull'utente in base al ruolo...
      if(ruolo != null) {
        request.getSession().setAttribute("ruolo", ruolo);
        
        // Se l'utente è di tipo 'DATAENTRY'
        if(ruolo.equals("DATAENTRY")){
          OperatoreDataEntryBean dataEntry = (OperatoreDataEntryBean) utente;
          request.getSession().setAttribute("utente", dataEntry);
        }
        
        // Se l'utente è di tipo 'FRONTOFFICE'
        if(ruolo.equals("FRONTOFFICE")){
          OperatoreFrontOfficeBean frontOff = (OperatoreFrontOfficeBean) utente;
          request.getSession().setAttribute("utente", frontOff);
        }
        
        // Se l'utente è di tipo 'SEGRETERIA'
        if(ruolo.equals("SEGRETERIA")){
          OperatoreSegreteriaBean segreteria = (OperatoreSegreteriaBean) utente;
          request.getSession().setAttribute("utente", segreteria);
        }
        
        // Se l'utente è di tipo 'CONTENT'
        if(ruolo.equals("CONTENT")){
          OperatoreContentBean content = (OperatoreContentBean) utente;
          request.getSession().setAttribute("utente", content);
        }
        
        // Se l'utente è di tipo 'ADVERTISER'
        if(ruolo.equals("ADVERTISER")){
          OperatoreAdvertiserBean advertiser = (OperatoreAdvertiserBean) utente;
          request.getSession().setAttribute("utente", advertiser);
        }
        
        // Se l'utente è di tipo 'TECNICO'
        if(ruolo.equals("TECNICO")){
          OperatoreTecnicoBean tecnico = (OperatoreTecnicoBean) utente;
          request.getSession().setAttribute("utente", tecnico);
        }
        
        // Se l'utente è di tipo 'DIREZIONE'
        if(ruolo.equals("DIREZIONE")){
          OperatoreDirezioneBean direzione = (OperatoreDirezioneBean) utente;
          request.getSession().setAttribute("utente", direzione);
        }
        
        // Se l'utente è di tipo 'SELEZIONATORE'
        if(ruolo.equals("SELEZIONATORE")){
          OperatoreSelezionatoreBean selezionatore = (OperatoreSelezionatoreBean) utente;
          request.getSession().setAttribute("utente", selezionatore);
        }
        
        // Se l'utente è di tipo 'CANDIDATO'
        if(ruolo.equals("CANDIDATO")){
          CandidatoBean candidato = (CandidatoBean) utente;
          request.getSession().setAttribute("utente", candidato);
        }

        // Se l'utente è di tipo 'PARTECIPANTE'
        if(ruolo.equals("PARTECIPANTE")){
          PartecipanteBean pax = (PartecipanteBean) utente;
          request.getSession().setAttribute("utente", pax);
        }

        request.getSession().setAttribute("ruolo", ruolo);
        String url=response.encodeRedirectURL("/AreaPersonale.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

      }else {
        // ...altrimenti c'&egrave; un errore nel recupero del ruolo
        request.getSession().setAttribute("loginOK", false);
        String url=response.encodeRedirectURL("/index.jsp");
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
      }

    }

  }

}
