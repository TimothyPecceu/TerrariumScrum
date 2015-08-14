package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Terrarium;
import be.vdab.valueobjects.Positie;

/**
 * Servlet implementation class KeuzeServlet
 */
@WebServlet("/keuze.htm")
public class KeuzeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/keuze.jsp";
	private static Terrarium terrarium;
	private static final String REDIRECT = "%s/terrarium.htm";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		int breedte = Integer.parseInt(request.getParameter("breedte"));
		int hoogte = Integer.parseInt(request.getParameter("hoogte"));
		session.setAttribute("terrarium", new Terrarium(hoogte, breedte));

		request.setAttribute("terrarium", session.getAttribute("terrarium"));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> fouten = new HashMap<>();
		HttpSession session = request.getSession();
		if (session.getAttribute("terrarium") != null) {
			terrarium = (Terrarium) session.getAttribute("terrarium");
			List<Positie> aanvaardePosities = new ArrayList<>();
			String[] posities = request.getParameterValues("posities");
			for (String positie : posities) {
				String[] coordinaten = positie.split(",");
				aanvaardePosities.add(
						new Positie(Integer.parseInt(coordinaten[0]), Integer.parseInt(coordinaten[1]), terrarium));
			}
			terrarium.setAanvaardePosities(aanvaardePosities);
		}
		int breedte = Integer.parseInt(request.getParameter("breedte"));
		int hoogte = Integer.parseInt(request.getParameter("hoogte"));

		if (request.getParameter("opties") != null) {

			String planten = request.getParameter("aantalPlanten");
			try {
				if (Integer.parseInt(request.getParameter("aantalPlanten")) < 1) {
					fouten.put("planten", "Geef een positief getal in");
				}
			} catch (NumberFormatException ex) {
				fouten.put("planten", "Geef een positief getal in");
			}

			String herbivoren = request.getParameter("aantalHerbivoren");
			try {
				if (Integer.parseInt(request.getParameter("aantalHerbivoren")) < 1) {
					fouten.put("herbivoren", "Geef een positief getal in");
				}
			} catch (NumberFormatException ex) {
				fouten.put("herbivoren", "Geef een positief getal in");
			}
			String carnivoren = request.getParameter("aantalCarnivoren");
			try {
				if (Integer.parseInt(request.getParameter("aantalCarnivoren")) < 1) {
					fouten.put("carnivoren", "Geef een positief getal in");
				}
			} catch (NumberFormatException ex) {
				fouten.put("carnivoren", "Geef een positief getal in");
			}
			String mensen = request.getParameter("aantalMensen");
			try {
				if (Integer.parseInt(request.getParameter("aantalMensen")) < 1) {
					fouten.put("mensen", "Geef een positief getal in");
				}
			} catch (NumberFormatException ex) {
				fouten.put("mensen", "Geef een positief getal in");
			}

			if (fouten.isEmpty()) {

				if (!planten.isEmpty() && !herbivoren.isEmpty() && !carnivoren.isEmpty() && !mensen.isEmpty()) {
					int aantalPlanten = Integer.parseInt(planten);
					int aantalHerbivoren = Integer.parseInt(herbivoren);
					int aantalCarnivoren = Integer.parseInt(carnivoren);
					int aantalMensen = Integer.parseInt(mensen);
					if (aantalPlanten > 0 && aantalHerbivoren > 0 && aantalCarnivoren > 0 && aantalMensen > 0) {
						String URL = REDIRECT + "?hoogte=" + hoogte + "&breedte=" + breedte + "&aantalPlanten="
								+ aantalPlanten + "&aantalHerbivoren=" + aantalHerbivoren + "&aantalCarnivoren="
								+ aantalCarnivoren + "&aantalMensen=" + aantalMensen;
						response.sendRedirect(response.encodeRedirectURL(String.format(URL, request.getContextPath())));
					}

				} else {
					request.setAttribute("fouten", fouten);
					request.getRequestDispatcher(VIEW).forward(request, response);

				}
			} else {
				request.setAttribute("fouten", fouten);
				terrarium = (Terrarium) session.getAttribute("terrarium");
				request.setAttribute("terrarium", session.getAttribute("terrarium"));
				request.setAttribute("checked", "checked");
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		} else {
			String URL = REDIRECT + "?hoogte=" + hoogte + "&breedte=" + breedte;
			response.sendRedirect(response.encodeRedirectURL(String.format(URL, request.getContextPath())));
		}

	}

}
