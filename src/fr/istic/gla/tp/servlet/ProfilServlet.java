package fr.istic.gla.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import fr.istic.gla.tp.beans.Fil;
import fr.istic.gla.tp.beans.Message;
import fr.istic.gla.tp.beans.Utilisateur;

/**
 * Servlet vide permettant uniquement d'afficher la page d'accueil en passant la main à la JSP accueil.jsp.
 * 
 * @author yves
 *
 */
public class ProfilServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session s  = factory.openSession();
		
		Transaction tx = s.beginTransaction();
		String strLogin = req.getParameter("login");
		session.setAttribute("utilName", strLogin);
		Criteria criteria = s.createCriteria(Utilisateur.class).add(Restrictions.eq("login", strLogin));
		List<Utilisateur> utilisateurList = criteria.list();
		if(utilisateurList.size() == 0)
			req.getRequestDispatcher("/echecProfil.jsp").forward(req, resp);
		else{
			session.setAttribute("profil", utilisateurList.get(0).getProfil());
			if(utilisateurList.get(0).getImage() != null){
				String str = "./fichiers/".concat(utilisateurList.get(0).getImage());
				session.setAttribute("image", str);
			}
			else{
				session.removeAttribute("image");
			}
			
			req.getRequestDispatcher("/profil.jsp").forward(req, resp);
		}
			
	}

}
	