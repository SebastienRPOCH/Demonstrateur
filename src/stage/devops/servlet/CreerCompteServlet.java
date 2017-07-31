package stage.devops.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import fr.istic.gla.tp.beans.Fil;
import fr.istic.gla.tp.beans.Utilisateur;
import stage.devops.beans.Joueur;

/**
 * 
 * 
 * @author sebastien radenac
 *
 */
public class CreerCompteServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Récupération login et pwd
		String login = req.getParameter("identifiant");
		String pwd = req.getParameter("mdp");
		String pwdConf = req.getParameter("mdpConf");
		HttpSession hs = req.getSession();
		hs.setAttribute("msg", "");
		if (!pwd.equals(pwdConf) || login.equals("") || login == null){
			hs.setAttribute("msg", "Echec de la creation");
			resp.sendRedirect("/Demonstrateur/accueil");
		}
		else {
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

			try {

				// To connect to mongodb server
				MongoClient mongoClient = new MongoClient("localhost", 27017);
				// MongoClient mongoClient = new MongoClient("172.9.9.5",
				// 27017);

				// Now connect to your databases
				DB db = mongoClient.getDB("test");
				System.out.println("Connect to database successfully");

				DBCollection coll = db.getCollection("mycol");
				System.out.println("Collection mycol selected successfully");

				BasicDBObject whereQuery = new BasicDBObject("login", login);

				DBCursor c = coll.find(whereQuery);
				Joueur joueur = new Joueur();
				if (c.hasNext()) {
					
					hs.setAttribute("msg", "Ce login existe déjà");
					
					resp.sendRedirect("/Demonstrateur/accueil");
				} else {
					BasicDBObject amiDefault = new BasicDBObject("login", "").append("dessinerPour", false)
							.append("repondreA", false).append("repText", "").append("repImage", "")
							.append("commentaire", "");
					List<BasicDBObject> listAmi = new ArrayList<BasicDBObject>();
					listAmi.add(amiDefault);
					BasicDBObject newUtil = new BasicDBObject("login", login).append("mdp", strpwd).append("image", "default")
							.append("profil", "joueur").append("listAmi", listAmi).append("score", 0);

					coll.insert(newUtil);
					System.out.println("Document inserted successfully");

					DBCursor cursor = coll.find();
					int i = 1;

					while (cursor.hasNext()) {
						System.out.println("Inserted Document: " + i);
						System.out.println(cursor.next());
						i++;
					}
					hs.setAttribute("msg", "Compte " + login + " créé");
				}

			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			}

			

			resp.sendRedirect("/Demonstrateur/");
		}
	

	}

	/**
	 * {@inheritDoc}
	 */
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/accueil.jsp").forward(req, resp);

	}
}
