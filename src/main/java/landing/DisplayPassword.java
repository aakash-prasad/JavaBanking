package landing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DisplayPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		String enteredId  = request.getParameter("customer_id");
		String enteredName = request.getParameter("customer_name");
		
		try {
			Connection conn = SqlConnection.initializeDatabase();
			PreparedStatement transactionStmt = conn.prepareStatement("SELECT customer_password, customer_name FROM userData WHERE customer_id = "+enteredId+"");
			ResultSet rs = transactionStmt.executeQuery();
			rs.next();
			//System.out.println("The password is: "+rs.getInt(1));
			
			if(rs.getString(2).equals(enteredName)) {
				out.print("<html>\r\n"
						+ "<head> <link rel=\"stylesheet\" href=\"./CSS/style.css\">\r\n"
						+ "</head>\r\n"
						+ "<body>\r\n"
						+ "<center>\r\n"
						+ "<div class = card>\r\n"
						+ "<h1>Your Password is:  "+rs.getString(1)+" </h1>\r\n"
						+ "</div>\r\n"
						+ "</center>\r\n"
						+ "</body>\r\n"
						+ "</html>");
			}
			else {
				out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Incorrect Name</h2></div></div></center><</body><html>");
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
