package be.vdab.servlets;

import java.io.IOException;

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
	private static Terrarium terrarium;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("terrarium") == null) {
			session.setAttribute("terrarium", new Terrarium());
		}
		terrarium = (Terrarium) session.getAttribute("terrarium");		
		String volgendeDag = request.getParameter("volgendeDag");
		if(volgendeDag != null){
			terrarium.volgendeDag();
		}		
		request.setAttribute("terrarium", terrarium);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
	
	

}
