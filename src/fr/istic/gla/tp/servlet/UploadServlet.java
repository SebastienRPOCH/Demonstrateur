package fr.istic.gla.tp.servlet;

import java.io.File;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import fr.istic.gla.tp.beans.Utilisateur;

public class UploadServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String filePath = "/home/sebastien/workspace/ForumGLA/WebContent/fichiers/";

		HttpSession httpsession = req.getSession();
		Utilisateur util = (Utilisateur) httpsession.getAttribute("utilisateur");

		Configuration configuration = new Configuration();
		configuration.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "create");
		configuration.setProperty(org.hibernate.cfg.Environment.URL, "jdbc:derby:DB;create=true");
		SessionFactory factory = configuration.configure("/ressources/hibernate.cfg.xml").buildSessionFactory();
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();

		if (ServletFileUpload.isMultipartContent(req)) {
			try {
				FileItemFactory diskFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(diskFactory);
				List<FileItem> multiparts = upload.parseRequest(new ServletRequestContext(req));

				FileItem item = multiparts.get(0);
				
				double a = Math.random();
				
				System.out.println("strImage : " +util);
				if(util.getImage() != null){
					System.out.println(filePath + util.getImage() + " : S'efface");
					File MyFile = new File(filePath + util.getImage());
					MyFile.delete(); 
					util.setImage("");
				}
				
				if (item.getName().endsWith(".png") || item.getName().endsWith(".jpg")) {
					if (item.getSize() < 10000) {
						item.write(new File(filePath + a));
						util.setImage(String.valueOf(a));
						session.update(util);
					}
				}

			} catch (Exception ex) {

			}

		} else {
		}
		tx.commit();
		resp.sendRedirect("/ForumGLA/upload");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récup la source de l'image pour le mettre
		HttpSession httpsession = req.getSession();
		Utilisateur util = (Utilisateur) httpsession.getAttribute("utilisateur");
		if (util.getImage() != null) {
			String str = ".\\fichiers\\".concat(util.getImage());
			httpsession.setAttribute("image", str);
		}
		req.getRequestDispatcher("/upload.jsp").forward(req, resp);

	}

}