package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Terrarium;

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
		if (!fouten.isEmpty()) {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		} else {
			String URL = REDIRECT + "?hoogte=" + hoogte + "&breedte="
					+ breedte;
			response.sendRedirect(response.encodeRedirectURL(String.format(URL, request.getContextPath())));
		}
	}

}
