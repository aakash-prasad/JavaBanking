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
		
		String enteredAmount  = request.getParameter("to_withdraw");
		PrintWriter out = response.getWriter();
		
		// Validation that the withdraw field should not be empty
		if( enteredAmount =="") {
			out.print("<body><center><div style=padding:50px;><h1>Please Enter amount to Withdrw</h1></div></center></body>");
		}
		if(Integer.parseInt(enteredAmount)<0) {
			out.print("<body><center><div style=padding:50px;><h1>Anmount cannot be less then 0</h1></div></center></body>");
		}
		//User input validation
		if(Integer.valueOf(enteredAmount)<0 && enteredAmount == "") {
			System.out.println("Please enter valid amount");
		}
		
		else {
			
			//Getting the details of logged in customer
			int currentBalance = UserAccess.getAmount();
			int currentUser = UserAccess.getCustomer_id();
			
			//Check if the user has sufficient balance to withdraw
			if(currentBalance < Integer.valueOf(enteredAmount)) {
				out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Low Balance, Cannot Withdraw</h2></div></div></center><</body><html>");
			}
			else {
				int to_be_inserted = currentBalance - Integer.valueOf(enteredAmount);
				try {
					Connection conn = SqlConnection.initializeDatabase();
					System.out.println("Object from DepositServlet: "+conn);
					
					PreparedStatement update_query_stmt = conn.prepareStatement("UPDATE userData SET balance = "+to_be_inserted+" WHERE customer_id= "+currentUser+";");
					update_query_stmt.executeUpdate();
					
					PreparedStatement updateTransactionQuery = conn.prepareStatement("insert into transactions value("+Integer.valueOf(currentUser)+",\"withdraw\","+Integer.valueOf(enteredAmount)+", now())");
					updateTransactionQuery.executeUpdate();
					System.out.println("Transaction working");
					
				}catch(Exception e) {System.out.println("");}
				
				
				out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Withdraw Succesful</h2></div></div></center><</body><html>");
				
			}
			
		}
		}
		

}

