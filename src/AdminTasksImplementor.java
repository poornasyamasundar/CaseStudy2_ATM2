import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AdminTasksImplementor implements AdminTasks{
	private static String detail = getDetails();
	private static double limit;
	private static String adminUserAndPwd;
	Scanner input = new Scanner( System.in );	
	private static String getDetails()
	{
		String details = "";
		try( Scanner fileinput = new Scanner( Paths.get( "AdminDetails.txt" )))
		{
			adminUserAndPwd = fileinput.nextLine() + "\n";
			limit = fileinput.nextDouble();
			fileinput.nextLine();
			while( fileinput.hasNext() )
			{
				details = details + fileinput.nextLine() + "\n";
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		return details;
	}
	
	public void setTransactionLimit()
	{
		System.out.println("Enter the Transaction Limit");
		double limit = input.nextDouble();
		
		if( limit >= 0 )
		{
			System.out.println("Transaction limit is set to "+limit);
			setLimit( limit );
		}
		else
		{
			System.out.println("Enter valid limit");
		}
		CustomerTasksImplementor.update();
		System.out.println("Press Enter to continue");
		input.nextLine();
	}
	
	public void seeAllTransactions()
	{
		if( !getDetail().equals("") )
			System.out.println(getDetail());
		else
			System.out.println("No Transactions made yet");
	}
	
	private static void setLimit( double l )
	{
		if( l >= 0 )
	       limit = l;
	}
	
	public static double getLimit()
	{
		return limit;
	}
	
	public static void setDetail( String str )
	{
		detail = detail + str;
	}
	
	public static String getDetail()
	{
		return detail;
	}
	
	public static String getAdminUserAndPwd()
	{
		return adminUserAndPwd;
	}
}
