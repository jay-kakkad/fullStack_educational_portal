import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class UpdatePaper
 */
@WebServlet("/UpdatePaper")
public class UpdatePaper extends HttpServlet {
	private static final long serialVersionUID = 1L;
       RequestDispatcher rt;
       String action;
		String qno;
		int i=1;
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
    public UpdatePaper() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//action=request.getParameter("action");
		
		//if("Next".equals(action))
		//{
			//System.out.println("hello from do Post");
			PrintWriter out=response.getWriter();
		
			//qno=request.getParameter("qno");
			qno=request.getParameter("qno");
			que=request.getParameter("que");
			opt1=request.getParameter("opt1");
			opt2=request.getParameter("opt2");
			opt3=request.getParameter("opt3");
			opt4=request.getParameter("opt4");
			ans=request.getParameter("ans");
			out.println("<br>"+qno+"<br>"+que+"<br>"+opt1+"<br>"+opt2+"<br>"+opt3+"<br>"+opt4+"<br>"+ans);
			
			try
			{
			
			
			
			ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("jdbc/OracleResource");
			con=ds.getConnection();
			int i=Integer.parseInt(qno);
		   ps=con.prepareStatement("update questions set EXAM_ID = ?,Q_NO = ?,QUESTION = ?,OPTION1 = ?,OPTION2=?,OPTION3=?,OPTION4=?,CORRECT=? where Q_NO=?");
		   ps.setInt(1,1);
		   ps.setInt(2,i);
		   ps.setString(3, que);
		   ps.setString(4, opt1);
		   ps.setString(5, opt2);
		   ps.setString(6, opt3);
		   ps.setString(7, opt4);
		   ps.setInt(8,Integer.parseInt(ans));
		   ps.setInt(9, i);
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
			rt=request.getRequestDispatcher("updatepaper.jsp");
			rt.forward(request, response);
		
		//if("Set".equals(action))
		//{
			//Home page of administrator
		//}
		}
	}