package landing;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String signupId  = request.getParameter("signup_customer_id");
		String signupPassword  = request.getParameter("signupPassword");
		String signupName  = request.getParameter("signup_customer_name");
		
		PrintWriter out =response.getWriter();
		//user validation: if user is entering fields
		if(signupId == "") {
			out.print("<body><center><div style=padding:50px;><h1>Customer id cannot be empty</h1></div></center></body>");
		}
		if(signupPassword == "" || signupPassword.length()<6) {
			out.print("<body><center><div style=padding:50px;><h1>Create a strong password</h1></div></center></body>");
		}
		if(signupName == "") {
			out.print("<body><center><div style=padding:50px;><h1>Name field cannot be empty</h1></div></center></body>");
		}
		
		else {
			try {
				Connection con = SqlConnection.initializeDatabase();
				
				PreparedStatement sign_query = con.prepareStatement("insert into userData values(?,?,?,?,?)");
				sign_query.setInt(1, Integer.valueOf(signupId));
				sign_query.setString(2, signupName);
				sign_query.setString(3, signupPassword);
				sign_query.setInt(4, 0);
				sign_query.setBoolean(5, true);
				
				sign_query.executeUpdate();
				System.out.println("User created success");
			} catch (ClassNotFoundException e) {
				System.out.println("error creating user");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
	}

}
