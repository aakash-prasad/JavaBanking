package landing;

/*

This package contains all the landing servlets namely the login servlet which serves our purpose of login 
and all the code related to user authentication is inside login seevlet

The signup servlet serves the purpose of signing up the user it checks in the databse if the user exist, if it exist then 
don't create the user, if it doesn't exist then create a new user

The UserAccess class is created purposefuly so that we can pass the static variables which user is logged in
in all other servlets like deposit and withdraw so that the balance can be fetched

The SqlConnection class in the package contains the connection file which is connected in the database 

*/