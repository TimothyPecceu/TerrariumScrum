package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Terrarium;

/**
 * Servlet implementation class TerrariumServlet
 */
@WebServlet("/terrarium.htm")
public class TerrariumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/terrarium.jsp"; 
    private static final Terrarium terrarium = Terrarium.getInstance();
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setAttribute("terrarium", terrarium.getTerrariumMap());
		String volgendeDag = request.getParameter("volgendeDag");		
		terrarium.volgendeDag();
		request.setAttribute("terrarium", terrarium.getTerrariumMap());
		request.getRequestDispatcher(VIEW).forward(request, response);		
	}

	

}
