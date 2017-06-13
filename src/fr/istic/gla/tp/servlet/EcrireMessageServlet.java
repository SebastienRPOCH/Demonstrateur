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

import fr.istic.gla.tp.beans.Message;
import fr.istic.gla.tp.beans.Utilisateur;

public class EcrireMessageServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		configuration.setProperty(org.hibernate.cfg.Environment.URL, "jdbc:derby:DB;create=true");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();
		
		String message = req.getParameter("message");
		
		HttpSession httpsession = req.getSession();
		
		Utilisateur util = (Utilisateur) httpsession.getAttribute("utilisateur");
		
		String curIdFil = httpsession.getAttribute("currentIdFil").toString();
		System.out.println(curIdFil);
		
		try {
			
				Message msg = new Message();
				msg.setNomAuteur(util.getLogin());
				//message = StringConvertHtml.stringToHTMLString(message);
				msg.setTexte(message);
				msg.setIdFil(curIdFil);
				session.save(msg);
				tx.commit();
		
		} catch (Exception e) {
			tx.rollback();
		}
		
		Criteria criteriaMsg = session.createCriteria(Message.class).add(Restrictions.eq("idFil", curIdFil));
		List<Message> MsgList = criteriaMsg.list();
		
		httpsession.setAttribute("messages", MsgList);
		
		resp.sendRedirect("/ForumGLA/fil");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/fil.jsp").forward(req, resp);
		
			
	}




}
