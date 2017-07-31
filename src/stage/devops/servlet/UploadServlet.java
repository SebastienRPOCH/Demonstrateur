package stage.devops.servlet;

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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

import fr.istic.gla.tp.beans.Utilisateur;
import stage.devops.beans.Joueur;

public class UploadServlet extends HttpServlet {

	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String filePath = "C:/workspace/fichiers/";

		HttpSession httpsession = req.getSession();
		Joueur joueur = (Joueur) httpsession.getAttribute("joueur");
		
		httpsession.setAttribute("msg", "");
		
		String fileName = joueur.getImage();
		
		MongoClient mongoClient = new MongoClient("localhost",27017);
		//MongoClient mongoClient = new MongoClient("172.9.9.5", 27017);
		
		// Now connect to your databases
		DB db = mongoClient.getDB("test");
		System.out.println("Connect to database successfully");

		DBCollection coll = db.getCollection("mycol");
		System.out.println("Collection mycol selected successfully");
		

		if (ServletFileUpload.isMultipartContent(req)) {
			try {
				FileItemFactory diskFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(diskFactory);
				List<FileItem> multiparts = upload.parseRequest(new ServletRequestContext(req));

				FileItem item = multiparts.get(0);
				
				double a = Math.random();
				
				if(fileName != null  || fileName == ""){
					System.out.println(filePath + fileName + " : S'efface");
					File MyFile = new File(filePath + fileName);
					MyFile.delete(); 
					joueur.setImage("default");
				}
				
				if (item.getName().endsWith(".png") || item.getName().endsWith(".jpg")) {
					if (item.getSize() < 10000) {
						item.write(new File(filePath + a));
						joueur.setImage(String.valueOf(a));
						BasicDBObject whereQuery = new BasicDBObject("login", joueur.getLogin());
						DBCursor c = coll.find(whereQuery);
						if(c.hasNext()){
							DBObject update = c.next();
							update.put("image", String.valueOf(a));
							coll.update(whereQuery,update);
						}
					}
					else{
						httpsession.setAttribute("msg", "Fichier trop grand");
					}
				}
				else{
					
					httpsession.setAttribute("msg", "Format requis .png/.jpg");
				}

			} catch (Exception ex) {

			}

		} else {
		}
		resp.sendRedirect("/Demonstrateur/Profil");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Récup la source de l'image pour le mettre
		HttpSession httpsession = req.getSession();
		Joueur joueur = (Joueur) httpsession.getAttribute("joueur");
		if (joueur.getImage() != null) {
			String str = ".\\fichiers\\".concat(joueur.getImage());
			httpsession.setAttribute("image", str);
		}
		req.getRequestDispatcher("/upload.jsp").forward(req, resp);

	}

}