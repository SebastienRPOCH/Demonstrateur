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

import com.mongodb.MongoClient;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import stage.devops.beans.Ami;
import stage.devops.beans.Joueur;

public class AjoutAmiServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		HttpSession httpsession = req.getSession();
		Joueur joueur = (Joueur) httpsession.getAttribute("joueur");
		String ajoutami = (String) req.getParameter("ajoutami");
		List<Ami> amiList = joueur.getListAmi();
		boolean trouve = false;
		int i = 0;
		if(ajoutami.equals(joueur.getLogin())){
			httpsession.setAttribute("msg", "Erreur");
			trouve = true;
		}
		while (i < amiList.size() && !trouve) {

			if (ajoutami.equals(amiList.get(i).getLogin())) {
				httpsession.setAttribute("msg", ajoutami + " inexistant ou déjà ajouté");
				trouve = true;
			}
			i++;
		}
		
		if (!trouve) {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// MongoClient mongoClient = new MongoClient("172.9.9.5", 27017);

			// Now connect to your databases
			DB db = mongoClient.getDB("test");
			System.out.println("Connect to database successfully");

			DBCollection coll = db.getCollection("mycol");
			System.out.println("Collection mycol selected successfully");

			BasicDBObject whereQuery = new BasicDBObject("login", ajoutami);
			DBCursor c = coll.find(whereQuery);
			if (c.hasNext()) {

				BasicDBObject amiDefault = new BasicDBObject("login", ajoutami).append("dessinerPour", false)
						.append("repondreA", false).append("repText", "").append("repImage", "")
						.append("commentaire", "");
				whereQuery = new BasicDBObject("login", joueur.getLogin());
				c = coll.find(whereQuery);

				DBObject update = c.next();

				List<BasicDBObject> listDoc = (List<BasicDBObject>) update.get("listAmi");

				listDoc.add(amiDefault);

				update.put("listAmi", listDoc);
				coll.update(whereQuery, update);

				httpsession.setAttribute("msg", ajoutami + " ajouté");
				
				Ami a = new Ami();
				
				a.setLogin(ajoutami);
				amiList.add(a);
				joueur.setListAmi(amiList);
				

			} else {
				httpsession.setAttribute("msg", ajoutami + " inexistant ou déjà ajouté");
			}
		}
		resp.sendRedirect("/Demonstrateur/dessinerPour");
	}

}
