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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import stage.devops.beans.Ami;
import stage.devops.beans.Joueur;
public class LadderServlet extends HttpServlet{

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
		List<Joueur> list = new ArrayList<Joueur>();
		
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		// MongoClient mongoClient = new MongoClient("172.9.9.5", 27017);

		// Now connect to your databases
		DB db = mongoClient.getDB("test");
		System.out.println("Connect to database successfully");

		DBCollection coll = db.getCollection("mycol");
		System.out.println("Collection mycol selected successfully");
		
		DBCursor c = coll.find();
		while (c.hasNext()) {
			Joueur joueur = new Joueur();
			DBObject jDB = c.next();
			joueur.setLogin(jDB.get("login").toString());
			joueur.setImage(jDB.get("image").toString());
			joueur.setProfil(jDB.get("profil").toString());
			joueur.setScore((int) jDB.get("score"));
			list.add(joueur);
		}
		System.out.println("Sizeof list : "+list.size());
		Collections.sort(list, new Comparator<Joueur>(){
			public int compare(Joueur a, Joueur b){
				if(a.getScore() >= b.getScore()){
					return a.getScore();
				}
				else{
					return b.getScore();
				}
			}
		});
		httpsession.setAttribute("list", list);
		req.getRequestDispatcher("/ladder.jsp").forward(req, resp);
	}
}
