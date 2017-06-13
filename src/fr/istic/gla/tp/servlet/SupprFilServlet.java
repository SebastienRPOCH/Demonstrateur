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

public class SupprFilServlet extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		HttpSession httpsession = req.getSession();

		httpsession.setAttribute("currentIdFil", req.getParameter("idF"));

		Utilisateur util = (Utilisateur) httpsession.getAttribute("utilisateur");


		int curIdFil = Integer.parseInt(httpsession.getAttribute("currentIdFil").toString());
		Criteria criteriaCurFil = session.createCriteria(Message.class).add(Restrictions.eq("id", curIdFil));
		List<Message> CurFilList = criteriaCurFil.list();
		

		try {
			session.remove(CurFilList.get(0));
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
		String curIdMsg = (String) httpsession.getAttribute("currentIdMsg");
		Criteria criteriaMsg = session.createCriteria(Message.class).add(Restrictions.eq("idFil", curIdMsg));
		List<Message> MessageList = criteriaMsg.list();

		try {
			for(int a=0;a<MessageList.size();a++)
				session.remove(MessageList.get(a));
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
		
		
		resp.sendRedirect("/ForumGLA/fil");

	}

}
