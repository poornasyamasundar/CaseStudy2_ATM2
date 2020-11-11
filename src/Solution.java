import java.util.Scanner;

public class Solution{
	
	public static void login()
	{
		Scanner input = new Scanner( System.in );
		System.out.println("Enter 1 to login as customer \nEnter 2 to login as Admin\nEnter 3 to Exit ");
		switch( input.nextInt() )
		{
			case 1:
				Login.customerLogin();
			case 2:
				Login.adminLogin();
			case 3:
				System.out.println("GoodBye!");
				System.exit(0);
			default:
				System.out.println("Enter Valid Number");
				login();
		}
		input.close();
	}
	public static void main( String[] args )
	{
		System.out.println("welcome to SBI Bank ATM!");
		login();
	}
}
