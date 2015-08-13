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
	private static final String REDIRECT_URL = "%s/terrarium.htm";
	private static Terrarium terrarium;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int hoogte = 0;
		int breedte = 0;

		Map<String, String> fouten = new HashMap<>();
		HttpSession session = request.getSession();
		if (session.getAttribute("terrarium") == null) {
			if (request.getParameter("hoogte") != null) {

				hoogte = Integer.parseInt(request.getParameter("hoogte"));
				if (hoogte < 1) {
					fouten.put("hoogte", "Getal moet minimum 1 zijn");
				}
			}
			if (request.getParameter("breedte") != null) {
				breedte = Integer.parseInt(request.getParameter("breedte"));
				if (breedte < 1) {
					fouten.put("breedte", "Getal moet minimum 1 zijn");
				}
			}
			if (fouten.isEmpty()) {
				session.setAttribute("terrarium", new Terrarium(hoogte, breedte));
			} else {
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
		}
		terrarium = (Terrarium) session.getAttribute("terrarium");
		String volgendeDag = request.getParameter("volgendeDag");
		if (volgendeDag != null) {
			terrarium.volgendeDag();
		}
		request.setAttribute("terrarium", terrarium);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("terrarium");
		int breedte = Integer.parseInt(request.getParameter("breedteNieuw"));
		int hoogte = Integer.parseInt(request.getParameter("hoogteNieuw"));
		session.setAttribute("terrarium", new Terrarium(hoogte, breedte));
		response.sendRedirect(response.encodeRedirectURL(String.format(REDIRECT_URL, request.getContextPath())));
	}

}
