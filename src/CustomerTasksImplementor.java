import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime; 

public class CustomerTasksImplementor implements CustomerTasks{
	Scanner input = new Scanner( System.in );   
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");  
	LocalDateTime now;
	public void showBankDetails( BankCustomer b )
	{
		System.out.println("Account Number : "+b.getAccountNumber());
		System.out.println("First Name : "+b.getFirstName());
		System.out.println("Last Name : "+b.getLastName());
		System.out.println("IFScode : "+b.getIfScode());
		System.out.println("Phone Number : *******"+(b.getPhoneNumber()%1000));
		System.out.println("Balance : "+b.getBalance());
		System.out.println("Press Enter to continue");
		input.nextLine();
	}
	
	public void withdraw( BankCustomer b )
	{
		System.out.println("Enter the amount you want withdraw ( Only in multiples of 100 )");
		double amount = input.nextDouble();
		if( amount > 0)
		{
		if( amount <= b.getBalance() )
		{
			if( amount % 100 == 0 )
			{
				if( amount <= AdminTasksImplementor.getLimit() )
				{
					if( verifyOTP( b ) )
					{
						System.out.println("Please Take Your Cash");
						if( b.getIfScode().charAt(0) == 'S')
						{
							now = LocalDateTime.now();
							b.setBalance( b.getBalance() - amount );
							b.addTransaction(dtf.format(now) + "  - withdrawn amount of Rs." +amount +"\n");
							AdminTasksImplementor.setDetail(String.valueOf(b.getAccountNumber()) + " - " + dtf.format(now) + "  - withdrawn amount of Rs." +amount +"\n");
						}
						else
						{
							now = LocalDateTime.now();
							b.setBalance( b.getBalance() - amount - (0.0005*amount));
							System.out.println("Rs."+amount*0.0005+" is charged against tranctions fees for making transactions in other bank atm");
							b.addTransaction(dtf.format(now) + "  - withdrawn amount of Rs." +amount + " with charges of Rs."+amount*0.0005+"\n");
							AdminTasksImplementor.setDetail(String.valueOf(b.getAccountNumber()) + " - " + dtf.format(now) + "  - withdrawn amount of Rs." +amount + " with charges of Rs."+amount*0.0005+"\n");
						}	
					
						update( b );
					}
					else
						System.out.println("Incorrect OTP");
				}
				else
					System.out.println("The Max Limit is "+AdminTasksImplementor.getLimit() + " Try again");
			}
			else
				System.out.println("Enter cash only in multiples of 100");
		}
		else
			System.out.println("You do not have enough cash");
		}
		else
			System.out.println("Enter Valid Amount");
		input.nextLine();
		System.out.println("Press enter to continue");
		input.nextLine();
	}
	
	public void deposit( BankCustomer b )
	{
		System.out.println("Enter the amount you want to deposit( Only in multiples of 100 )");
		double amount = input.nextDouble();
		if( amount > 0 )
		{
			if( amount <= AdminTasksImplementor.getLimit())
			{
				if( amount % 100 == 0 )
				{
					System.out.println("Please Place Your Cash");
					if( b.getIfScode().charAt(0) == 'S')
					{
						now = LocalDateTime.now();
						System.out.println("Successfully deposited Rs."+amount);
						b.setBalance( b.getBalance() + amount );
						b.addTransaction(dtf.format(now) + "  - deposited amount of Rs." + amount+"\n");
						AdminTasksImplementor.setDetail(String.valueOf(b.getAccountNumber()) + " - " + dtf.format(now) + "  - deposited amount of Rs." + amount+"\n");
					}
					else
					{
						now = LocalDateTime.now();
						b.setBalance( b.getBalance() + amount - (0.0005*amount));
						System.out.println("Successfully deposited Rs."+amount);
						System.out.println("Rs."+amount*0.0005+" is charged against tranctions fees for making transactions in other bank atm");
						b.addTransaction(dtf.format(now) + "  - deposited amount of Rs." + amount+ " with charges of Rs."+amount*0.0005+"\n");
						AdminTasksImplementor.setDetail(String.valueOf(b.getAccountNumber()) + " - " + dtf.format(now) + "  - deposited amount of Rs." + amount+" with charges of Rs."+amount*0.0005+"\n");
					}
				
					update( b );
				}
				else
					System.out.println("Enter amount of multiples of 100 Try Again");
			}
			else
				System.out.println("The Max Limit is "+AdminTasksImplementor.getLimit() + " Try again");
		}
		else
			System.out.println("Enter Valid amount");
		input.nextLine();
		System.out.println("Press enter to continue");
		input.nextLine();
	} 
	
	public void transfer( BankCustomer b )
	{
		System.out.println("Enter the account number you want to transfer to");
		int account = input.nextInt();
		if( Login.isAccountAvailable(account) != 0 )
		{
			BankCustomer c = getBankCustomerObject(account);
			System.out.println("Enter the IFScode");
			String ifscode = input.next();
			if( ifscode.equals( c.getIfScode() ))
			{
				System.out.println("Enter the amount you want to transfer");
				double amount = input.nextDouble();
				if( amount > 0 && amount < b.getBalance() )
				{
					if( amount <= AdminTasksImplementor.getLimit())
					{
						if( verifyOTP(b) )
						{	
							System.out.println("Amount Successfully transferred to the account "+c.getAccountNumber());
							b.setBalance(b.getBalance()-amount);
							c.setBalance(c.getBalance()+amount);
							now = LocalDateTime.now();
							if( b.getIfScode().charAt(0) == 'S')
							{
								b.setBalance( b.getBalance() - amount );
								b.addTransaction(dtf.format(now) + "  - transferred amount of Rs." +amount +" to account number "+c.getAccountNumber()+"\n");
								AdminTasksImplementor.setDetail(String.valueOf(b.getAccountNumber()) + " - " + dtf.format(now) + "  - transferred amount of Rs." +amount +" to account number "+c.getAccountNumber()+"\n");
							}
							else
							{
								b.setBalance( b.getBalance() - amount - (0.0005*amount));
								System.out.println("Rs."+amount*0.0005+" is charged against tranctions fees for making transactions in other bank atm");
								b.addTransaction(dtf.format(now) + "  - transferred amount of Rs." +amount +" to account number "+c.getAccountNumber()+" with charges of Rs."+amount*0.0005+"\n");
								AdminTasksImplementor.setDetail(String.valueOf(b.getAccountNumber()) + " - " + dtf.format(now) + "  - transferred amount of Rs." +amount +" to account number "+c.getAccountNumber()+" with charges of Rs."+amount*0.0005+"\n");
							}
							c.addTransaction(dtf.format(now) + "  - amount of Rs." + amount+" is deposited by "+b.getAccountNumber()+"\n");
							AdminTasksImplementor.setDetail(String.valueOf(c.getAccountNumber()) + " - " + dtf.format(now) + "  - amount of Rs." + amount+" is deposited by "+b.getAccountNumber()+"\n");
							update(b);
							update(c);
						}
						else
							System.out.println("Incorrect OTP Try Again");
					}
					else
						System.out.println("The Max Limit is "+AdminTasksImplementor.getLimit() + " Try again");
				}
				else
					System.out.println("Enter valid amount Try Again");
			}
			else
				System.out.println("Incorrect Ifscode Try Again");
		}
		else
			System.out.println("Incorrect Account Number Try Again");
		input.nextLine();
		System.out.println("Press enter to continue");
		input.nextLine();
	}
	
	public void changePin( BankCustomer b )
	{
		System.out.println("Enter the existing Pin for confirmation");
		if( Login.isPasswordCorrect(b.getAccountNumber(),input.nextInt()) )
		{
			if( verifyOTP(b) )
			{
				System.out.println("Enter your New Pin");
				int newpin = input.nextInt();
				System.out.println("Enter the new pin again for confirmation");
				if( newpin == input.nextInt() )
				{
					updatePin(b.getAccountNumber(),newpin);
					System.out.println("Pin Successfully updated");
				}
				else
					System.out.println("Pin MisMatched Try Again");
			}
			else
				System.out.println("Incorrect OTP Try again");
		}
		else
		{
			System.out.println("Incorrect Pin ");
			System.out.println("Logging out for security reasons");
			Solution.login();
		}
		input.nextLine();
		System.out.println("Press Enter to continue");
		input.nextLine();
	}
	
	public void miniStatement( BankCustomer b )
	{
		String split[] = b.getTracsactions().split("\n");
		int lineCount = split.length;
		if( lineCount == 1 )
			System.out.println("No Transactions made yet");
		else if( lineCount == 2 )
			System.out.println(split[1]);
		else if( lineCount == 3 )
			System.out.println(split[1]+"\n"+split[2]);
		else
		{
			System.out.println(split[lineCount-3]+"\n"+split[lineCount-2]+"\n"+split[lineCount-1]);
		}
		System.out.println("Press Enter to continue");
		input.nextLine();
	}
	
	public void familyMemberAccount( BankCustomer b )
	{
		System.out.println("Enter your Family member's Account Number");
		int account = input.nextInt();
		if( isFamilyMember(b,account) )
		{
			CustomerTasks.showMenu(account);
		}
		else
		{
			System.out.println("Incorrect Account Number or the account is not linked to your account");
		}
		input.nextLine();
		System.out.println("Press Enter to continue");
		input.nextLine();
	}
	private boolean verifyOTP( BankCustomer b )
	{
		int otp = (int)(Math.random()*9001 + 1000 );
		System.out.println("An OTP is sent to your Mobile Number (*******"+b.getPhoneNumber()%1000+")");
		System.out.println("Enter the OTP to validate your transaction");
		System.out.println("OTP : "+otp);
		if(otp == input.nextInt() )
			return true;
		return false;
	}
	
	public BankCustomer getBankCustomerObject( int account )
	{
		String filename = "BankCustomers\\" + String.valueOf(account) + ".txt";
		
		BankCustomer b = new BankCustomer(0,"","","",0,0);
		try( Scanner fileinput = new Scanner( Paths.get( filename )))
		{
			b = new BankCustomer( fileinput.nextInt(), fileinput.next(), fileinput.next(), fileinput.next(), fileinput.nextLong(), fileinput.nextDouble());
			fileinput.nextLine();
			while( fileinput.hasNext() )
			{
				b.addTransaction( fileinput.nextLine() + "\n" );
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		return b;
	}
	private boolean isFamilyMember( BankCustomer b , int account )
	{
		String filename = "BankCustomers\\" + String.valueOf(b.getAccountNumber()) + ".txt";
		
		try( Scanner fileinput = new Scanner( Paths.get( filename )))
		{
			fileinput.nextLine();
			String str = fileinput.nextLine();
			String split[] = str.split("\\s");
			for( int i = 0 ; i < split.length ; i++ )
			{
				if( split[i].equals(String.valueOf(account)))
					return true;
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private void update( BankCustomer b )
	{
		String filename = "BankCustomers\\" + String.valueOf(b.getAccountNumber()) + ".txt";
		try( Formatter output = new Formatter( filename ))
		{
			output.format("%d %s %s %s %d %f\n%s", b.getAccountNumber(), b.getFirstName(), b.getLastName(), b.getIfScode(), b.getPhoneNumber(), b.getBalance(), b.getTracsactions());
		}
		catch( SecurityException | FileNotFoundException | FormatterClosedException e )
		{
			e.printStackTrace();
		}
		
		try( Formatter output = new Formatter( "AdminDetails.txt" ))
		{
			output.format("%s%f\n%s", AdminTasksImplementor.getAdminUserAndPwd(),AdminTasksImplementor.getLimit(),AdminTasksImplementor.getDetail());
		}
		catch( SecurityException | FileNotFoundException | FormatterClosedException e )
		{
			e.printStackTrace();
		}
	}
	
	public static void update()
	{
		try( Formatter output = new Formatter( "AdminDetails.txt" ))
		{
			output.format("%s%f\n%s", AdminTasksImplementor.getAdminUserAndPwd(),AdminTasksImplementor.getLimit(),AdminTasksImplementor.getDetail());
		}
		catch( SecurityException | FileNotFoundException | FormatterClosedException e )
		{
			e.printStackTrace();
		}
	}
	private void updatePin(int account , int pin )
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
					str = str + " " + fileinput.next() + " " + PasswordEncryption.encrypt(String.valueOf(pin)) + "\n";
					fileinput.nextLine();
				}
				else
					str = str + fileinput.nextLine() + "\n";
			}
		}
		catch( IOException | NoSuchElementException | IllegalStateException e )
		{
			e.printStackTrace();
		}
		
		try( Formatter output = new Formatter( "Pins.txt" ))
		{
			output.format("%s", str);
		}
		catch( SecurityException | FileNotFoundException | FormatterClosedException e )
		{
			e.printStackTrace();
		}
	}
}
