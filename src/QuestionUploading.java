
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class QuestionUploading
 */
@WebServlet("/QuestionUploading")
public class QuestionUploading extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rt;
    String action;
	String qno;
	String que;
	String opt1;
	String opt2;
	String opt3;
	String opt4;
	String ans;
	Context ctx;
	DataSource ds;
	Connection con;
	PreparedStatement ps;
	String query="insert into questions values(?,?,?,?,?,?,?,?)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionUploading() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		out.println("Welcome to MainQue");
		qno=request.getParameter("qno");
		que=request.getParameter("que");
		opt1=request.getParameter("opt1");
		opt2=request.getParameter("opt2");
		opt3=request.getParameter("opt3");
		opt4=request.getParameter("opt4");
		ans=request.getParameter("ans");
		int exam_id = Integer.parseInt(request.getParameter("exam_id"));
		out.println("<br>"+qno+"<br>"+que+"<br>"+opt1+"<br>"+opt2+"<br>"+opt3+"<br>"+opt4+"<br>"+ans);
		
		try
		{
		
		
		
		ctx=new InitialContext();
		ds=(DataSource)ctx.lookup("jdbc/examination");
		con=ds.getConnection();
	   ps=con.prepareStatement(query);
	   
	   ps.setInt(1,exam_id);
	   ps.setInt(2,Integer.parseInt(qno));
	   ps.setString(3, que);
	   ps.setString(4, opt1);
	   ps.setString(5, opt2);
	   ps.setString(6, opt3);
	   ps.setString(7, opt4);
	   ps.setInt(8,Integer.parseInt(ans));
	   
	   
	   int retval=ps.executeUpdate();
	   if(retval==1)
	   {
		   out.println("You are registered");
	   }
	}catch(NamingException e)
		{
		e.printStackTrace();
		}
		catch(SQLException e)
		{
		e.printStackTrace();
		}
		
	
	rt=request.getRequestDispatcher("questionuploading.jsp");
	rt.forward(request, response);
	}
//}
//if("Set".equals(action))
//{
	//Home page of administrator
//}
}


