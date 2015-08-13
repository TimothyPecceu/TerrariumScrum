package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private static final String REDIRECT = "%s/terrarium.htm";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int hoogte = 0;
		int breedte = 0;
		Map<String, String> fouten = new HashMap<>();
		if (request.getParameter("hoogte") != null) {
			try {
				hoogte = Integer.parseInt(request.getParameter("hoogte"));
			} catch (NumberFormatException ex) {
				fouten.put("hoogte", "Geef een positief getal in");
			}
			if (hoogte < 1) {
				fouten.put("hoogte", "Geef een positief getal in");
			}
		} else {
			fouten.put("hoogte", "Geef een positief getal in");
		}
		if (request.getParameter("breedte") != null) {
			try {
				breedte = Integer.parseInt(request.getParameter("breedte"));
			} catch (NumberFormatException ex) {
				fouten.put("breedte", "Geef een positief getal in");
			}
			if (breedte < 1) {
				fouten.put("breedte", "Geef een positief getal in");
			}
		} else {
			fouten.put("breedte", "Geef een positief getal in");
		}
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			String planten = request.getParameter("aantalPlanten");
			String herbivoren = request.getParameter("aantalHerbivoren");
			String carnivoren = request.getParameter("aantalCarnivoren");
			String mensen = request.getParameter("aantalMensen");
			if (!planten.isEmpty() && !herbivoren.isEmpty() && !carnivoren.isEmpty() && !mensen.isEmpty()) {
				int aantalPlanten = Integer.parseInt(planten);
				int aantalHerbivoren = Integer.parseInt(herbivoren);
				int aantalCarnivoren = Integer.parseInt(carnivoren);
				int aantalMensen = Integer.parseInt(mensen);
				String URL = REDIRECT + "?hoogte=" + hoogte + "&breedte=" + breedte + "&aantalPlanten=" + aantalPlanten
						+ "&aantalHerbivoren=" + aantalHerbivoren + "&aantalCarnivoren=" + aantalCarnivoren
						+ "&aantalMensen=" + aantalMensen;
				response.sendRedirect(response.encodeRedirectURL(String.format(URL, request.getContextPath())));
			} else {
				String URL = REDIRECT + "?hoogte=" + hoogte + "&breedte=" + breedte;
				response.sendRedirect(response.encodeRedirectURL(String.format(URL, request.getContextPath())));

			}

		}
	}

}
