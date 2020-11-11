import java.util.Scanner;
public interface AdminTasks {
	
	public static void showMenu( )
	{
		AdminTasks a = new AdminTasksImplementor();
		Scanner input = new Scanner( System.in );
		System.out.println("Enter 1 to set Transaction limit \nEnter 2 to view all Transactions made \nEnter 3 to logout");
		int choice = input.nextInt();
		while( choice != 3 )
		{
			if( choice == 1 )
				a.setTransactionLimit();
			else if( choice == 2 )
				a.seeAllTransactions();
			else if( choice != 3 )
				System.out.println("Enter valid choice");
			System.out.println("Enter 1 to set Transaction limit \nEnter 2 to view all Transactions made \nEnter 3 to logout");
			choice = input.nextInt();
		}
		if( choice == 3 )
			Solution.login();
		input.close();
	}
	
	public void setTransactionLimit();
	public void seeAllTransactions();
}
