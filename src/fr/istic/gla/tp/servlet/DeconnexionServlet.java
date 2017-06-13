package fr.istic.gla.tp.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.istic.gla.tp.beans.Fil;
import fr.istic.gla.tp.beans.Utilisateur;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;

public class DeconnexionServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		HttpSession httpsession = req.getSession();
		httpsession.removeAttribute("utilisateur");
		List<Fil> filList = null;
		
		httpsession.setAttribute("filList", filList);
		
		req.getRequestDispatcher("/deconnexion.jsp").forward(req, resp);

	}
}
