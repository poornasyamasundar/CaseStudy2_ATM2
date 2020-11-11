import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public interface CustomerTasks {
	
	public static void showMenu( int account )
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();
		CustomerTasks c = new CustomerTasksImplementor();
		Scanner input = new Scanner( System.in );
		BankCustomer b = c.getBankCustomerObject(account);
		AdminTasksImplementor.setDetail(dtf.format(now) + "- " +String.valueOf(b.getAccountNumber()) + " logged in" + "\n");
		CustomerTasksImplementor.update();
		showOptions(b.getIfScode());
		int choice = input.nextInt();
		while( choice != 8 )
		{
			if( choice == 1 )
				c.showBankDetails(b);
			else if( choice == 2 )
				c.withdraw(b);
			else if( choice == 3 )
				c.deposit(b);
			else if( choice == 4 )
				c.transfer(b);
			else if( choice == 5 )
				c.changePin(b);
			else if( choice == 6 )
				c.miniStatement(b);
			else if( choice == 7 )
				c.familyMemberAccount(b);
			else if( choice != 8 )
				System.out.println("Enter valid choice");
			showOptions( b.getIfScode());
			choice = input.nextInt();
		}
		if( choice == 8 )
		{
			AdminTasksImplementor.setDetail(dtf.format(now) + "- " +String.valueOf(b.getAccountNumber()) + " logged out" + "\n");
			CustomerTasksImplementor.update();
			Solution.login();
		}
		input.close();
	}
	
	private static void showOptions( String ifscode )
	{
		System.out.println("Enter 1 to See your All Bank Details");
		System.out.println("Enter 2 to Withdraw money");
		System.out.println("Enter 3 to Deposit money");
		System.out.println("Enter 4 to Transfer money to Other Account");
		System.out.println("Enter 5 to Changer your Pin");
		System.out.println("Enter 6 to Get a Mini Statement");
		System.out.println("Enter 7 to login into your family member account");
		System.out.println("Enter 8 to LogOut");
		if(ifscode.charAt(0) != 'S')
		{
			System.out.println("");
			System.out.println("NOTE : Transactions fees of 0.05% of the amount withdrawed/deposited/transferred will be charged against making transactions in other bank atm");
			System.out.println("However no chrges will be charged for other facilities like ministatement/pin change/viewing Details");
		}
	}
	
	public BankCustomer getBankCustomerObject( int account );
	public void showBankDetails( BankCustomer b );
	public void withdraw( BankCustomer b );
	public void deposit( BankCustomer b );
	public void transfer( BankCustomer b );
	public void changePin( BankCustomer b );
	public void miniStatement( BankCustomer b );
	public void familyMemberAccount( BankCustomer b );
}
