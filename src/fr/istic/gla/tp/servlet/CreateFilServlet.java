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
public class CreateFilServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		configuration.setProperty(org.hibernate.cfg.Environment.URL, "jdbc:derby:DB;create=true");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		String nom = req.getParameter("nomFil");
		
		try {
			
				Fil fil = new Fil();
				fil.setNom(nom);
				session.save(fil);
				tx.commit();
		
		} catch (Exception e) {
			tx.rollback();
		}
		
		Criteria criteriaFil = session.createCriteria(Fil.class);
		List<Message> FilList = criteriaFil.list();

		HttpSession httpsession = req.getSession();
		httpsession.setAttribute("filList", FilList);
		resp.sendRedirect("/ForumGLA/accueil");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/createFil.jsp").forward(req, resp);

	}
	

}
	