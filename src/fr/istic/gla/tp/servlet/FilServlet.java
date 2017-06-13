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
public class FilServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		HttpSession session = req.getSession();
		
		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session s  = factory.openSession();
		
		Transaction tx = s.beginTransaction();
		
		session.setAttribute("currentIdFil", req.getParameter("id"));
		
		Criteria criteria = s.createCriteria(Fil.class).add(Restrictions.eq("id", Integer.parseInt(req.getParameter("id"))));
		List<Fil> filList = criteria.list();
		
		session.setAttribute("currentFil", filList.get(0));
		
		Criteria criteriaMsg = s.createCriteria(Message.class).add(Restrictions.eq("idFil", req.getParameter("id")));
		List<Message> MsgList = criteriaMsg.list();
		
		session.setAttribute("messages", MsgList);
		
		resp.sendRedirect("/ForumGLA/fil");
		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/fil.jsp").forward(req, resp);
		
	}

}
	