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

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import fr.istic.gla.tp.beans.Fil;
import fr.istic.gla.tp.beans.Utilisateur;

/**
 * Servlet vide permettant uniquement d'afficher la page d'accueil en passant la main à la JSP accueil.jsp.
 * 
 * @author yves
 *
 */
public class AccueilServlet extends HttpServlet {


	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//Récupération login et pwd
		String login = req.getParameter("identifiant");
		String pwd = req.getParameter("mdp");
		
		
		Utilisateur utilisateur = new Utilisateur();

		//Recherche dans la base de donnée si le login est présent
		Configuration configuration = new Configuration();
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session  = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Utilisateur.class).add(Restrictions.eq("login", login));
		List<Utilisateur> utilisateurList = criteria.list();
		
		for(Utilisateur util : utilisateurList){
			if(util.getLogin().equals(login)){
				utilisateur = util;
				break;
			}
		}
		tx.commit();
		
		//Hash du pwd
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
	
		//Compare pour savoir si le login est null puis compare le hash et le pwd trouvé en base
		if (utilisateur.getLogin() == null || strpwd == null) {
			resp.sendRedirect("/ForumGLA/echecAuth.jsp");
		} else {
			
			if (!strpwd.equals(utilisateur.getPassword())) {
			resp.sendRedirect("/ForumGLA/echecAuth.jsp");
			} else {
				
				//Met en session l'utilisateur
				HttpSession httpsession = req.getSession();
				httpsession.setAttribute("utilisateur", utilisateur);
				
				resp.sendRedirect("/ForumGLA/accueil");
			}
		}

		
		
		

	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/acceuil.jsp").forward(req, resp);

	}

}
	