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

public class SupprMessageServlet extends HttpServlet {

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

		httpsession.setAttribute("currentIdMessage", req.getParameter("idM"));

		Utilisateur util = (Utilisateur) httpsession.getAttribute("utilisateur");


		int curIdMes = Integer.parseInt(httpsession.getAttribute("currentIdMessage").toString());
		Criteria criteriaCurMsg = session.createCriteria(Message.class).add(Restrictions.eq("id", curIdMes));
		List<Message> CurMsgList = criteriaCurMsg.list();
		

		try {
			System.out.println("Message : " + CurMsgList);
			session.remove(CurMsgList.get(0));
			tx.commit();

		} catch (Exception e) {
			tx.rollback();
		}
		String curIdFil = (String) httpsession.getAttribute("currentIdFil");
		Criteria criteriaMsg = session.createCriteria(Message.class).add(Restrictions.eq("idFil", curIdFil));
		List<Message> MsgList = criteriaMsg.list();

		httpsession.setAttribute("messages", MsgList);

		resp.sendRedirect("/ForumGLA/fil");

	}

}
