import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Function;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.appserv.jdbc.DataSource;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InitialContext ctx;
	DataSource ds;
	Connection con;
	PreparedStatement s=null,s2=null;
	ResultSet rs;
	String query="select correct from questions where exam_id=?";
	String query2 = "INSERT INTO RESULT VALUES (?,?,?,?)";
	
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPut(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// TODO Auto-generated method stub
 System.out.println("HI");

		 int count=0;
		 int i = 1;
		 int correct;
	HttpSession sess = request.getSession(false);
	int exam_id = (Integer)sess.getAttribute("exam_id");
	int sid = (Integer) sess.getAttribute("id");
	
	response.setContentType("text/html");
	
	try {
	PrintWriter out=response.getWriter();
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
	
	ctx=new InitialContext();
	ds=(DataSource)ctx.lookup("jdbc/OracleResource");
	con=ds.getConnection();
	s=con.prepareStatement(query);
	
	s.setInt(1, exam_id);
	
    rs = s.executeQuery();
    
    while(rs.next())
    {
    	
    	int answer=rs.getInt(1);
    	correct=Integer.parseInt(request.getParameter(String.valueOf(i)));
    	if(answer==correct) 
    	{
    	 
    		count++;
    	}
    	i++;

  }
    s2 = con.prepareStatement(query2);
    s2.setInt(1,exam_id);
    s2.setInt(2, sid);
    s2.setInt(3,count);
    s2.setInt(4, i-count-1);
    int retval = s2.executeUpdate();
    if(retval == 1)
    	System.out.println("Sucessfully inserted");
    out.println("You have answered these many answers correctly: "+count + "<br>");
    out.println("You have answered "+(i-count -1)+" incorrectly");
    
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("Error");
		e.printStackTrace();
	}
   	
   
	
	
	}

}
