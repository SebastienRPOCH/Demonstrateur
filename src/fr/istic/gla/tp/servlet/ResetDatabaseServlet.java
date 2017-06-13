package fr.istic.gla.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fr.istic.gla.tp.beans.Fil;
import fr.istic.gla.tp.beans.Message;
import fr.istic.gla.tp.beans.Utilisateur;

/**
 * Servlet d'initialisation de la base de données.
 * 
 * @author yves
 *
 */
public class ResetDatabaseServlet extends HttpServlet {

	/**
	 * Tableau représentant les utilisateurs. Il sera utilisé pour construire la Map.
	 * 
	 */
	String[][] UTILISATEURS = {
			{ "pierre", "d5a5d66b94e8da0cf63d4cd6d66cd489d78e77b697039c6c48e3ff8d81752139", "utilisateur" ,""},
			{ "paul", "0357513deb903a056e74a7e475247fc1ffe31d8be4c1d4a31f58dd47ae484100", "utilisateur" ,""},
			{ "jacques", "742b9bb2306eb494bbd3b50593a32cfe621fa9b3228ccfdc119dfd44155b5662", "moderateur" ,""},
			{ "admin", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "administrateur" ,""}
	};
	
	String[] FILS = {"Premier Sujet","Le salon du rémi","Qui a ouvert ce fil ??"};

	
	String[][] MESSAGES = {
			{ "pierre", "Bonjour tout le monde !", "1" },
			{ "paul", "Salut !", "1" },
			{ "jacques", "Hola", "1" },
			{ "admin", "Si vous pouvez partir de ce forum merci ...", "1" }
	};
	

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		configuration.setProperty(org.hibernate.cfg.Environment.URL, "jdbc:derby:DB;create=true");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		Transaction tx = session.beginTransaction();
		session.createQuery("delete from fr.istic.gla.tp.beans.Utilisateur").executeUpdate();
		session.createQuery("delete from fr.istic.gla.tp.beans.Fil").executeUpdate();
		session.createQuery("delete from fr.istic.gla.tp.beans.Message").executeUpdate();
		
		try {
			for (String[] UTILISATEUR : UTILISATEURS) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setLogin(UTILISATEUR[0]);
				utilisateur.setPassword(UTILISATEUR[1]);
				utilisateur.setProfil(UTILISATEUR[2]);
				utilisateur.setImage(UTILISATEUR[3]);
				session.save(utilisateur);
			}
			for(String FIL : FILS){
				Fil fil = new Fil();
				fil.setNom(FIL);
				session.save(fil);
			}
			for (String[] MESSAGE : MESSAGES) {
				Message message = new Message();
				message.setNomAuteur(MESSAGE[0]);
				message.setTexte(MESSAGE[1]);
				message.setIdFil(MESSAGE[2]);
				session.save(message);
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

		req.getRequestDispatcher("/resetDatabase.jsp").forward(req, resp);
	}

}
