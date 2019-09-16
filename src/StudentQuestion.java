




import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class StudentQuestion
 */
@WebServlet("/StudentQuestion")
public class StudentQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	InitialContext ctx;
	DataSource ds;
	Connection con;
	PreparedStatement ps;
	String query="insert into questions values(?,?,?,?,?,?,?)";
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentQuestion() {
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
		try
		{
			response.setContentType("text/html");
			PrintWriter out=response.getWriter();
			ctx=new InitialContext();
			ds=(DataSource)ctx.lookup("jdbc/OracleResource");
			con=ds.getConnection();
			
			ps=con.prepareStatement(query);
			int exam_id = 1;
			ps.setInt(1,exam_id);
			int retval=ps.executeUpdate();
			if(retval==1)
			{
				out.println("you are registered");
			}
		}catch(NamingException e)
		{
			System.out.println("probably the name registered is different");
			e.printStackTrace();
			
		}
		catch(SQLException e)
		{
			System.out.println("You have some sql exception");
			e.printStackTrace();
		}
	}

}
