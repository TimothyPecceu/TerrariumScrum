package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		if (session.getAttribute("terrarium") == null) {
			int breedte = Integer.parseInt(request.getParameter("breedte"));
			int hoogte = Integer.parseInt(request.getParameter("hoogte"));
			session.setAttribute("terrarium", new Terrarium(hoogte, breedte));
		}
		terrarium = (Terrarium) session.getAttribute("terrarium");
		String volgendeDag = request.getParameter("volgendeDag");
		if (volgendeDag != null) {
			terrarium.volgendeDag();
		}
		request.setAttribute("terrarium", terrarium);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
