
import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Servlet implementation class Timer
 */
@WebServlet("/Timer")
public class Timer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher rd;
	String subject;
    String EID;
    Timestamp st;
    Timestamp et;
    Context ctx;
    DataSource ds;
    Connection con;
    PreparedStatement ps;
    String query="insert into exam values(?,?,?,?,?)";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Timer() {
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
		HttpSession session=request.getSession();
		
		PrintWriter out=response.getWriter();
	String start=request.getParameter("start");
	String end=request.getParameter("end");
    subject=request.getParameter("subject");
     EID=request.getParameter("ExamID");
    
		
		 try
			{
			
			
			
			ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("jdbc/OracleResource");
			con=ds.getConnection();
		   ps=con.prepareStatement(query);
		   ps.setInt(1,Integer.parseInt(EID));
		   ps.setString(2,subject);
		  
		   ps.setTimestamp(3, Timestamp.valueOf(start));
		   ps.setTimestamp(4, Timestamp.valueOf(end));
		   ps.setInt(5,12);
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
		 	 
   rd=request.getRequestDispatcher("quepaper.jsp");  
	rd.forward(request, response);
	}
	
	
	
	
	public static Timestamp convertStringToTimestamp(String str_date) {
	    try {
	      DateFormat formatter;
	      formatter = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
	       // you can change format of date
	      Date date = formatter.parse(str_date);
	      java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

	      return timeStampDate;
	    } catch (ParseException e) {
	      System.out.println("Exception :" + e);
	      return null;
	    }
	  }
	

}
