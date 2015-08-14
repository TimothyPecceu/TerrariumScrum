package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Terrarium;

/**
 * Servlet implementation class TerrariumServlet
 */
@WebServlet("/terrarium.htm")
public class TerrariumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/terrarium.jsp";
	private static final String REDIRECT_URL = "%s/keuze.htm";
	private static Terrarium terrarium;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		terrarium = (Terrarium) session.getAttribute("terrarium");
		String volgendeDag = request.getParameter("volgendeDag");
		if (volgendeDag != null) {
			terrarium.volgendeDag();
		} else {
			if (request.getParameter("aantalPlanten") != null && request.getParameter("aantalHerbivoren") != null
					&& request.getParameter("aantalCarnivoren") != null
					&& request.getParameter("aantalMensen") != null) {
				int aantalPlanten = Integer.parseInt(request.getParameter("aantalPlanten"));
				int aantalHerbivoren = Integer.parseInt(request.getParameter("aantalHerbivoren"));
				int aantalCarnivoren = Integer.parseInt(request.getParameter("aantalCarnivoren"));
				int aantalMensen = Integer.parseInt(request.getParameter("aantalMensen"));

				terrarium.setOrganismes(aantalPlanten, aantalHerbivoren, aantalCarnivoren, aantalMensen);
				session.setAttribute("terrarium", terrarium);
			} else {
				terrarium.setRandomOrganismes();
			}
		}
		request.setAttribute("terrarium", terrarium);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int hoogte = 0;
		int breedte = 0;
		HttpSession session = request.getSession();

		Map<String, String> fouten = new HashMap<>();

		if (request.getParameter("hoogteNieuw") != null) {

			try {
				hoogte = Integer.parseInt(request.getParameter("hoogteNieuw"));
			} catch (NumberFormatException ex) {
				fouten.put("hoogte", "Geef een positief getal in");
			}
			if (hoogte < 1) {
				fouten.put("hoogte", "Geef een positief getal in");
			}
		} else {
			fouten.put("hoogte", "Geef een positief getal in");
		}
		if (request.getParameter("breedteNieuw") != null) {
			try {
				breedte = Integer.parseInt(request.getParameter("breedteNieuw"));
			} catch (NumberFormatException ex) {
				fouten.put("breedte", "Geef een positief getal in");
			}
			if (breedte < 1) {
				fouten.put("breedte", "Geef een positief getal in");
			}
		} else {
			fouten.put("breedte", "Geef een positief getal in");
		}
		if (fouten.isEmpty()) {
			session.removeAttribute("terrarium");
			String URL = REDIRECT_URL + "?hoogte=" + hoogte + "&breedte=" + breedte;
			response.sendRedirect(response.encodeRedirectURL(String.format(URL, request.getContextPath())));
		} else {
			request.setAttribute("fouten", fouten);
			terrarium = (Terrarium) session.getAttribute("terrarium");
			request.setAttribute("terrarium", session.getAttribute("terrarium"));
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}

}
