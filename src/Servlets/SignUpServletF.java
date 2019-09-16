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

import jdbc.*;

/**
 * Servlet implementation class Sign_Up_Servlet
 */
@WebServlet("/SignUpServletF")
public class SignUpServletF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServletF() {
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
		
		String fname=request.getParameter("fname");
		String lname=request.getParameter("lname");
		String pwd=request.getParameter("password");
		
		
		long id = 0;
		Function<String,Long> stringToLongConversion=Long::parseLong;
		
		
		
		try{
			id=stringToLongConversion.apply(request.getParameter("id"));
			
		}catch(NumberFormatException e)
		{
			out.println("Wrong ID");
		}
		
		 
		 UserF u=new UserF();
		 
		 u.setFid(id);
		 u.setPassword(pwd);
		 u.setFname(fname);
		 u.setLname(lname);
		 
		 session.setAttribute("User", u);
		 
		 	 
		  CRUD2 cr=new CRUD2();
		 
		 if(cr.retrieve(id, pwd)>0)
		 {
			 out.println("user already exist");
			 rd=request.getRequestDispatcher("indexfaculty.jsp");
			 rd.forward(request, response);
		 }
		 else
		 { 
			 
			 if(cr.register(u)>0)
				 out.println("Successfully registered");
			 rd=request.getRequestDispatcher("facultyWelcome.jsp");
			 rd.forward(request, response);
			 
		 }
		 
		 
	}
}
