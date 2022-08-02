package operations;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import landing.SqlConnection;
import landing.UserAccess;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/WithdrawlServlet")
public class WithdrawlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String entered_amount  = request.getParameter("to_withdraw");
		PrintWriter out = response.getWriter();
		
		// Validation that the withdraw field should not be empty
		if( entered_amount =="") {
			out.print("<body><center><div style=padding:50px;><h1>Please Enter amount to Withdrw</h1></div></center></body>");
		}
		if(Integer.parseInt(entered_amount)<0) {
			out.print("<body><center><div style=padding:50px;><h1>Anmount cannot be less then 0</h1></div></center></body>");
		}
		//User input validtion
		if(Integer.valueOf(entered_amount)<0 && entered_amount == "") {
			System.out.println("Please enter valid amount");
		}
		
		else {
			
			//Getting the details of logged in customer
			int current_balance = UserAccess.getAmount();
			int current_user = UserAccess.getCustomer_id();
			
			//Check if the user has sufficient balance to withdraw
			if(current_balance < Integer.valueOf(entered_amount)) {
				out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Low Balance, Cannot Withdraw</h2></div></div></center><</body><html>");
			}
			else {
				int to_be_inserted = current_balance - Integer.valueOf(entered_amount);
				try {
					Connection conn = SqlConnection.initializeDatabase();
					System.out.println("Object from DepositServlet: "+conn);
					
					PreparedStatement update_query_stmt = conn.prepareStatement("UPDATE userData SET balance = "+to_be_inserted+" WHERE customer_id= "+current_user+";");
					update_query_stmt.executeUpdate();
					
				}catch(Exception e) {System.out.println("");}
				
				
				out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Withdraw Succesful</h2></div></div></center><</body><html>");
			}
			
		}
		}
		

}

