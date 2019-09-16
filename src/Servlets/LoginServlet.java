package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.function.Function;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jdbc.CRUD;
import jdbc.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
HttpSession session=request.getSession();
		
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Integer examid=Integer.parseInt(request.getParameter("exam_id"));
		User u=new User();
		Function<String,Long> stringToLongConversion=Long::parseLong;
		Function<String,Integer> stringToIntConversion=Integer::parseInt;
		
		int id=stringToIntConversion.apply(request.getParameter("exam_id"));
		u.setId(stringToLongConversion.apply(request.getParameter("id")));
		u.setPassword(request.getParameter("password"));
		
		session.setAttribute("LoginUser", u);
		
		System.out.println(u);
		session.setAttribute("exam_id",id);
		CRUD cr=new CRUD();
		int ch=cr.retrieve(u.getId(),u.getPassword());
		System.out.println(ch);
		switch(ch)
		{
		case 0:	
			out.println("Wrong Username");
			
			rd = request.getRequestDispatcher("indexstudent.jsp");
			rd.forward(request, response);
			break;
			
		case 1: 
			out.println("Wrong Password ");
			rd = request.getRequestDispatcher("indexstudent.jsp");
			rd.forward(request, response);
			break;
		case 2:
			out.println("Successful login");
			
			rd = request.getRequestDispatcher("checktime.jsp");
			rd.forward(request, response);
			break;
		default:
			out.println("Back to login page");
			rd = request.getRequestDispatcher("indexstudent.jsp");
			rd.forward(request, response);
			break;
		
	}

}
}
