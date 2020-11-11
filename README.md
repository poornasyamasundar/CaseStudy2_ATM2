# CaseStudy2_ATM2
The code contains 6 classes and 2 interfaces and files is used as database.
  1.Solution class : The program executes from the main menu of the class
  2.Login class : takes care of verifying the customer and admin login details and directs them to CustomerTasks and AdminTasks respectively
  3.CustomerTasks interface : Contains showMenu which shows all the options to the customer and declares the functions to accomplish this
  4.CustomerTasksImplementor class: Implements CustomerTasks functions with the help of some helper functions
  5.AdminTasks interface: Contains showMenu which shows all the options to the admin and declares the functions to accomplish this
  6.AdminTasksImplementor class: Implements AdminTasks functions with the help of some helper functions
  7.PasswordEncryption class: contains the function encrypt which takes a string as the input and returns an encrypted string
  8.BankCustomer class: contains all the attributes of a customer with set and get methods for these attributes
  
  files and folders part of the code:
  1.Pins.txt contains account numbers and their pins in encrypted form for login purposes
  2.AdminDetails.txt contains userid and password of the admin with transactions limit and list of all transactions made
  3.folder BankCustomers contains all the account holders files with accountnumber.txt as names and contains all the personal details along with
    balance and transaction history of the customer.
  
  Steps to run the program:
    Execute the code by running the solution class and proceed as per the given options( Menu Driven ) ( No GUI)
    
 All the account numbers and pins along with admin userId and password are provided in the file OriginalPins.txt( it is not the part of the code )
 All indivisual constraints with all common constraints are implemented;
 
