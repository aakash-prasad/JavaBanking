package operations;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Once the user hits logout the html form will be printed again, instead of writing the entire html
		//again we could have requested the same html page via an ajax call and javascript
		PrintWriter out = response.getWriter();
		out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body>\r\n"
				+ "<center><h1 >Welcome to JSW Bank</h1></center>\r\n"
				+ "<div class=Container>\r\n"
				+ "	\r\n"
				+ "	<div class =\"card\">\r\n"
				+ "		<h2>Log in</h2>\r\n"
				+ "	<div class=LoginForm >\r\n"
				+ "		<form action=\"./LoginServlet\" method=\"post\">\r\n"
				+ "	  		<label for=\"customer_id\">Customer Id:</label><br>\r\n"
				+ "	  		<input type=\"number\" id=\"customer_id\" name=\"customer_id\" value=\"\"><br>\r\n"
				+ "			<label for=\"password\">Password:</label><br>\r\n"
				+ "			<input type=\"password\" id=\"password\" name=\"password\" value=\"\"><br><br>\r\n"
				+ "			<input type=\"submit\" value=\"Login\">\r\n"
				+ "	</form> \r\n"
				+ "	</div>\r\n"
				+ "	</div>\r\n"
				+ "	\r\n"
				+ "	<div class =\"card\">\r\n"
				+ "		<div class=SignupForm>\r\n"
				+ "	<h2>Sign Up</h2>\r\n"
				+ "		<form action=\"./SignupServlet\" method=\"post\">\r\n"
				+ "	  		<label for=\"signup_customer_id\">Customer Id:</label><br>\r\n"
				+ "	  		<input type=\"number\" id=\"signup_customer_id\" name=\"signup_customer_id\" value=\"\"><br>\r\n"
				+ "	  		<label for=\"signup_customer_name\">Customer Name:</label><br>\r\n"
				+ "	  		<input type=\"text\" id=\"signup_customer_name\" name=\"signup_customer_name\" value=\"\"><br>\r\n"
				+ "			<label for=\"signup_password\">Password:</label><br>\r\n"
				+ "			<input type=\"password\" id=\"signup_password\" name=\"signup_password\" value=\"\"><br><br>\r\n"
				+ "			<input type=\"submit\" value=\"Signup\">\r\n"
				+ "	</form> \r\n"
				+ "	</div>\r\n"
				+ "	</div>\r\n"
				+ "	\r\n"
				+ "</div>\r\n"
				+ "</body></html>");
		
	}

}

