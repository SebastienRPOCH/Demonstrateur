package stage.devops.test;

import static org.junit.Assert.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class CreerCompteServletTest extends Mockito{

	@Test
	public void testDoPostHttpServletRequestHttpServletResponse() {
		fail("Not yet implemented");
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() {

		HttpServletRequest req = mock(HttpServletRequest.class);
        ArgumentCaptor<String> dispatcherArgument = ArgumentCaptor.forClass(String.class);
        verify(req).getRequestDispatcher(dispatcherArgument.capture());
        assertEquals("/accueil.jsp", dispatcherArgument.getValue());
        
	}

}
