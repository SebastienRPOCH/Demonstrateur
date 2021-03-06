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
public class DessinerPourServlet extends HttpServlet{

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
		List<Ami> amiList = joueur.getListAmi();
		httpsession.setAttribute("amiList", amiList);
		req.getRequestDispatcher("/dessinerPour.jsp").forward(req, resp);
	}
	
}
