package fr.istic.gla.tp.servlet;

import java.io.IOException;


import javax.servlet.Filter;

import javax.servlet.FilterChain;

import javax.servlet.FilterConfig;

import javax.servlet.ServletException;

import javax.servlet.ServletRequest;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import fr.istic.gla.tp.beans.Utilisateur;


public class FilterServlet implements Filter {


    public void init( FilterConfig config ) throws ServletException {
    }


    public void doFilter( ServletRequest req, ServletResponse res, FilterChain chain ) throws IOException,
            ServletException {
        /* Cast des objets request et response */
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        /* Récupération de la session depuis la requête */
        
        HttpSession session = request.getSession();
        Utilisateur util = (Utilisateur) session.getAttribute("utilisateur");
        
        int[][]filtre ={{1,0,0,0,0,0,0},{1,1,1,1,0,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
        
        int utilId=0;
        String reqString;
        int reqId=0;
        
        if(util == null)
        	request.getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
        
        switch (util.getProfil()) {
        	case "invité":
        		utilId = 0;
        		break;
        	case "utilisateur":
        		utilId = 1;
        		break;
        	case "moderateur":
        		utilId = 2;
        		break;
        	case "administrateur":
        		utilId = 3;
        		break;
        }
        
        
        if(request.getRequestURI().startsWith("/ForumGLA/profil"))
        	reqString = "/ForumGLA/profil";
        else if(request.getRequestURI().startsWith("/ForumGLA/supprMessage"))
        	reqString = "/ForumGLA/supprMessage";
        else
        	reqString = request.getRequestURI();
        
        
        switch (reqString) {
    	case "/ForumGLA/createFil":
    		reqId = 1;
    		break;
    	case "/ForumGLA/ecrireMessage":
    		reqId = 2;
    		break;
    	case "/ForumGLA/profil":
    		reqId = 3;
    		break;
    	case "/ForumGLA/supprMessage":
    		reqId = 4;
    		break;
    	case "/ForumGLA/upload":
    		reqId = 5;
    		break;	
    	case "/ForumGLA/deconnexion":
    		reqId = 6;
    		break;
    	case "/ForumGLA/supprFil":
    		reqId = 4;
    		break;
        }
        
       
        if(filtre[utilId][reqId] ==0){
        	switch (reqId){
        	case 1:
        		req.setAttribute("messageError", "Pas les droits pour créer un fil");
        		break;
        	case 2:
        		req.setAttribute("messageError", "Pas les droits pour créer un message");
        		break;
        	case 3:
        		req.setAttribute("messageError", "Pas les droits pour voir un profil");
        		break;
        	case 4:
        		req.setAttribute("messageError", "Pas les droits pour supprimer un message/fil");
        		break;
        	case 5:
        		req.setAttribute("messageError", "Vous n'avez pas de profil, donc pas besoin de le mettre à jour !");
        		break;
        	case 6:
        		req.setAttribute("messageError", "Vous n'êtes même pas connecté ...");
        		break;
        	}
        
    	
        	request.getRequestDispatcher( "/accueil.jsp" ).forward( request, response );
        	
        }
        else{
        	// req.removeAttribute("message");
        	 chain.doFilter(req, res);
        }
    }


    public void destroy() {
        
    }

}