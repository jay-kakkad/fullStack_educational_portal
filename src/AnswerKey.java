

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class AnswerKey
 */
@WebServlet("/AnswerKey")
public class AnswerKey extends HttpServlet {
	Context ctx;
	DataSource ds;
	Connection con;
	PreparedStatement ps;
	String query = "SELECT Q_NO,QUESTION,OPTION1,OPTION2,OPTION3,OPTION4,CORRECT FROM QUESTIONS WHERE EXAM_ID = ?";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerKey() {
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
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
//		HttpSession sess = request.getSession(false);
//		int exam_id = (int)sess.getAttribute("exam_id");4
		int exam_id = 1;
		
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("jdbc/examination");
			con = ds.getConnection();
			ps = con.prepareStatement(query);
	
			ps.setInt(1,exam_id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				int q_no = rs.getInt("Q_NO");
				String question = rs.getString("QUESTION");
				String option1 = rs.getString("OPTION1");
				String option2 = rs.getString("OPTION2");
				String option3 = rs.getString("OPTION3");
				String option4 = rs.getString("OPTION4");
				String correct = rs.getString("CORRECT");
				
				out.println("<h3>(Q." + q_no + ") " + question + "</h3>");
				out.println("<h5>" + option1 + "</h5>");
				out.println("<h5>" + option2 + "</h5>");
				out.println("<h5>" + option3 + "</h5>");
				out.println("<h5>" + option4 + "</h5>");
				out.println("<h4>" + correct + "</h4>");
				
				
			}
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

}
