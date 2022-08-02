package landing;

/*
 * This class is created so that the customer who is logged in can be share all acrooss the package 
 * by creating static variables along with the getters and seters.
 */
public class UserAccess {
     private static String customer_name;
     private static int customer_id;
     private static int amount;
	public static String getCustomer_name() {
		return customer_name;
	}
	// Function to set the customername which will be called in login servlet as soon as the user log in
	public static void setCustomer_name(String customer_name) {
		UserAccess.customer_name = customer_name;
	}
	//This function is created to fetch the customer id in deposit or withdraw servlet
	public static int getCustomer_id() {
		return customer_id;
	}
	// Function to set the customer Id which will be called in login servlet as soon as the user log in
	public static void setCustomer_id(int customer_id) {
		UserAccess.customer_id = customer_id;
	}
	//This function is created to fetch the balance in deposit or withdraw servlet
	public static int getAmount() {
		return amount;
	}
	// Function to set the Balance which will be called in login servlet as soon as the user log in
	public static void setAmount(int amount) {
		UserAccess.amount = amount;
	}
     
     
     
}

