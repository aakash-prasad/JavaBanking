package landing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><div class =\"card\">\r\n"
				+ "		<center><h2>Forgot Passwrord</h2>\r\n"
				+ "	<div class=ForgotPasswordForm >\r\n"
				+ "		<form action=\"./DisplayPassword\" method=\"post\">\r\n"
				+ "	  		<label for=\"customer_id\"> Enter Customer Id:</label><br>\r\n"
				+ "	  		<input type=\"number\" id=\"customer_id\" name=\"customer_id\" value=\"\"><br>\r\n"
				+ "			<label for=\"customer_name\">Enter name:</label><br>\r\n"
				+ "			<input type=\"text\" id=\"customer_name\" name=\"customer_name\" value=\"\"><br><br>\r\n"
				+ "			<input type=\"submit\" value=\"Get Password\"><br>\r\n"
				+ "	</form> \r\n"
				+ "	</div>\r\n"
				+ "	</div></center></html>");
	}

}
