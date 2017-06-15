package stage.devops.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.ServerAddress;

import java.util.ArrayList;
import java.util.Arrays;

import fr.istic.gla.tp.beans.Utilisateur;
import stage.devops.beans.Ami;
import stage.devops.beans.Joueur;

/**
 * Servlet vide permettant uniquement d'afficher la page d'accueil en passant la
 * main à la JSP accueil.jsp.
 * 
 * @author yves
 *
 */
public class AccueilServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Récupération login et pwd

		String login = req.getParameter("identifiant");
		String pwd = req.getParameter("mdp");

		try {

			// To connect to mongodb server
			MongoClient mongoClient = new MongoClient("172.9.9.5", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("test");
			System.out.println("Connect to database successfully");

			DBCollection coll = db.getCollection("mycol");
			System.out.println("Collection mycol selected successfully");

			// Hash du pwd
			MessageDigest md = null;
			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			byte[] hash = md.digest(pwd.getBytes());
			BigInteger bi = new BigInteger(1, hash);
			String strpwd = (String.format("%0" + (hash.length << 1) + "X", bi)).toLowerCase();

			BasicDBObject whereQuery = new BasicDBObject("login", login).append("mdp", strpwd);

			DBCursor cursor = coll.find(whereQuery);
			Joueur joueur = new Joueur();
			System.out.println(cursor.count());
			if (cursor.hasNext()) {
				DBObject jDB = cursor.next();
				System.out.println("Here : " + jDB.get("login"));
				
				joueur.setLogin(jDB.get("login").toString());
				joueur.setImage(jDB.get("image").toString());
				joueur.setProfil(jDB.get("profil").toString());
				joueur.setScore((int) jDB.get("score"));


				List<BasicDBObject> listDoc = (List<BasicDBObject>) jDB.get("listAmi");
				List<Ami> listAmi = new ArrayList<Ami>();

				for (BasicDBObject ami : listDoc) {

					Ami newAmi = new Ami();
					newAmi.setLogin(ami.get("login").toString());
					newAmi.setRepondreImage(ami.get("repImage").toString());
					newAmi.setRepondreTexte(ami.get("repText").toString());
					newAmi.setDessinerpour((boolean)ami.get("dessinerPour"));
					newAmi.setRepondre((boolean)ami.get("repondreA"));
					newAmi.setCommentaire(ami.get("commentaire").toString());
					listAmi.add(newAmi);
				}

				joueur.setListAmi(listAmi);

				System.out.println(joueur.getLogin() + " et " + joueur.getProfil());
			}

			HttpSession httpsession = req.getSession();
			httpsession.setAttribute("joueur", joueur);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		/*
		 * Utilisateur utilisateur = new Utilisateur();
		 * 
		 * //Recherche dans la base de donnée si le login est présent
		 * Configuration configuration = new Configuration(); SessionFactory
		 * factory = configuration.configure("/ressources/hibernate.cfg.xml").
		 * buildSessionFactory(); Session session = factory.openSession();
		 * 
		 * Transaction tx = session.beginTransaction(); Criteria criteria =
		 * session.createCriteria(Utilisateur.class).add(Restrictions.eq(
		 * "login", login)); List<Utilisateur> utilisateurList =
		 * criteria.list();
		 * 
		 * for(Utilisateur util : utilisateurList){
		 * if(util.getLogin().equals(login)){ utilisateur = util; break; } }
		 * tx.commit();
		 * 
		 * //Hash du pwd MessageDigest md = null; try { md =
		 * MessageDigest.getInstance("SHA-256"); } catch
		 * (NoSuchAlgorithmException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } byte[] hash = md.digest(pwd.getBytes());
		 * BigInteger bi = new BigInteger(1, hash); String strpwd =
		 * (String.format("%0" + (hash.length << 1) + "X", bi)).toLowerCase();
		 * 
		 * //Compare pour savoir si le login est null puis compare le hash et le
		 * pwd trouvé en base if (utilisateur.getLogin() == null || strpwd ==
		 * null) { resp.sendRedirect("/ForumGLA/echecAuth.jsp"); } else {
		 * 
		 * if (!strpwd.equals(utilisateur.getPassword())) {
		 * resp.sendRedirect("/ForumGLA/echecAuth.jsp"); } else {
		 * 
		 * //Met en session l'utilisateur HttpSession httpsession =
		 * req.getSession(); httpsession.setAttribute("utilisateur",
		 * utilisateur);
		 * 
		 *  } }
		 */
		resp.sendRedirect("/Demonstrateur/accueil");

	}

	/**
	 * {@inheritDoc}
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/accueil.jsp").forward(req, resp);

	}

}
