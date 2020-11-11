import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Login {
	
	public static void customerLogin()
	{
		Scanner input = new Scanner( System.in );
		System.out.println("Enter your 5 digit Account Number");
		int acc = input.nextInt();
		
		int choice = isAccountAvailable(acc);
		
		if( choice == 0 )
		{
			System.out.println("!!Wrong Account Number,Enter valid Account Number");
			Solution.login();
		}
		if( choice == 1 )
		{
			int cnt = 0;
			while( cnt < 3 )
			{
				System.out.println("Enter your 4 digit Pin");
				if( isPasswordCorrect(acc,input.nextInt()) )
				{
					CustomerTasks.showMenu(acc);
					break;
				}
				else
				{
					cnt++;
					if( cnt < 3)
					{
						System.out.println("Incorrect Password try again You have "+(3-cnt)+" channces left");
						System.out.println("Your Card will be Blocked after that");
					}
				}
			}
			if( cnt == 3 )
			{
				System.out.println("Your card has been blocked as you have entered wrong password for 3 times");					
				System.out.println("Contact your Bank to unblock your card");
				block(acc);
				Solution.login();
			}
		}
		if( choice == 2 )
		{
			System.out.println("Your card has been blocked, Contact your bank to unblock your card");
			Solution.login();
		}
		input.close();
	}
	
	public static void adminLogin()
	{
		Scanner input = new Scanner( System.in );
		System.out.println("Enter the Admin User ID");
		String id = input.nextLine();
		System.out.println("Enter the Password");
		if( isAdmin(id,PasswordEncryption.encrypt(input.nextLine())))
		{
			AdminTasks.showMenu();
		}
		else
		{
			System.out.println("Either Username or the password is incorrect try again");
			Solution.login();
		}
		input.close();
	}
	public static int isAccountAvailable( int account )
	{
		try( Scanner fileinput = new Scanner( Paths.get( "Pins.txt" )))
		{
			while( fileinput.hasNext() )
			{
				if( fileinput.nextInt() == account )
				{
					if( fileinput.next().equals("unblocked"))
						return 1;
					else
						return 2;
				}
				else
					fileinput.nextLine();
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean isPasswordCorrect( int account, int password )
	{
		try( Scanner fileinput = new Scanner( Paths.get( "Pins.txt" )))
		{
			while( fileinput.hasNext() )
			{
				if( fileinput.nextInt() == account )
				{
					fileinput.next();
					if( PasswordEncryption.encrypt(String.valueOf(password)).equals(fileinput.next()))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
				else
					fileinput.nextLine();
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private static boolean isAdmin( String id , String pwd)
	{
		try( Scanner fileinput = new Scanner( Paths.get( "AdminDetails.txt" )))
		{
				if( fileinput.next().equals(id) )
				{
					if( fileinput.next().equals(pwd))
					{
						return true;
					}
					else
					{
						return false;
					}
				}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private static void block( int account )
	{
		String str ="";
		try( Scanner fileinput = new Scanner( Paths.get( "Pins.txt" )))
		{
			int acc = 0;
			while( fileinput.hasNext() )
			{
				acc = fileinput.nextInt();
				str = str + String.valueOf(acc);
				if( acc == account )
				{
					if( fileinput.next().equals("unblocked"))
						str = str + " blocked   " + fileinput.next() + "\n";
				}
				else
					str = str + fileinput.nextLine() + "\n";
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		
		try( Formatter output = new Formatter( "test.txt" ))
		{
			output.format("%s", str);
		}
		catch( SecurityException | FileNotFoundException | FormatterClosedException e )
		{
			e.printStackTrace();
		}
	}
}
