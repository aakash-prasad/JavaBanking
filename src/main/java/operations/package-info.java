package operations;


/*
The package contains all the operation which user can do after login, I have created different package
for every single operation for loose coupling, so if anyday we want to remove or add any feature from the app
we can do it just by removing or adding the class file

The Deposit servlet contains the logic of depositing the amount by grabbing the balance and adding the 
amount entered by the user

The Withdraw servlet contains all the information and logic of withdrawing the amount after the user pits 
in the amount then it will check the balance and then withdraw

The Check Balance servlet contains the logic of checking the balance via grabbing the balance from the 
UserAccess class and printing it.

*/