package operations;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import landing.UserAccess;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/CheckBalanceServlet")
public class CheckBalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentBalance = UserAccess.getAmount();
		
		PrintWriter out = response.getWriter();
		out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Your current balance is: "+currentBalance+"</h2></div></div></center><</body><html>");
		
	}

}