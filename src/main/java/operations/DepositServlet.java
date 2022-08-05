package operations;
import landing.SqlConnection;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import landing.UserAccess;

@WebServlet("/DepositServlet")
public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		// Get the input entered by the user
		String enteredAmount  = request.getParameter("to_deposit");
		
		//Field validation: cannot be empty and greater then 0
		if( enteredAmount =="") {
			out.print("<body><center><div style=padding:50px;><h1>Please Enter amount to deposit</h1></div></center></body>");
		}
		if(Integer.parseInt(enteredAmount)<0) {
			out.print("<body><center><div style=padding:50px;><h1>Anmount cannot be less then 0</h1></div></center></body>");
		}
		else {
			
			//Getting the details of logged in customer
			int currentBalance = UserAccess.getAmount();
			int currentUser = UserAccess.getCustomer_id();
			int toBeInserted = currentBalance + Integer.valueOf(enteredAmount);
			
			//import the connection and run an insert query
			try {
				Connection conn = SqlConnection.initializeDatabase();
				System.out.println("Object from DepositServlet: "+conn);
				
				PreparedStatement updateQueryStmt = conn.prepareStatement("UPDATE userData SET balance = "+toBeInserted+" WHERE customer_id= "+currentUser+";");
				updateQueryStmt.executeUpdate();
				
				PreparedStatement updateTransactionQuery = conn.prepareStatement("insert into transactions value("+Integer.valueOf(currentUser)+",\"deposit\","+Integer.valueOf(enteredAmount)+", now())");
				updateTransactionQuery.executeUpdate();
				
			}catch(Exception e) {System.out.println("There is an exception: "+e);}
			
			
			out.print("<html><head><link rel=\"stylesheet\" href=\"./CSS/style.css\"></head><body><center><div class = card><div style= padding-top:10px;><h2>Deposit Succesful</h2></div></div></center><</body><html>");
			
			
			
		}
		}

}

