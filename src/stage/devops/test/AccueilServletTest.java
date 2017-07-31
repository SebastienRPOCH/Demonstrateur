package stage.devops.test;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import stage.devops.servlet.AccueilServlet;

	
	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
		req.setAttribute("identifiant", "a");
		req.setAttribute("mdp", "a");
		HttpSession httpsession = mock(HttpSession.class);
		httpsession.setAttribute("msg", "test");
		
		

		System.out.println(httpsession.getAttribute("msg"));
		when(req.getSession()).thenReturn(httpsession);
		
		when(req.getParameter("identifiant")).thenReturn("a");
		when(req.getParameter("mdp")).thenReturn("a");
		
		new AccueilServlet().doPost(req, resp);
		

		//Assert.assertEquals("",httpsession.getAttribute("msg"));

	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws ServletException, IOException {
		
		/*HttpServletRequest req = mock(HttpServletRequest.class);
		HttpServletResponse resp = mock(HttpServletResponse.class);
        ArgumentCaptor<String> dispatcherArgument = ArgumentCaptor.forClass(String.class);
		
        new AccueilServlet().doGet(req, resp);
        
        verify(req).getRequestDispatcher(dispatcherArgument.capture());
        

	}
}
