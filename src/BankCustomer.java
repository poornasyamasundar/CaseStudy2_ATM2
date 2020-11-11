public class BankCustomer {
	private String firstName = "";
	private String lastName = "";
	private int accountNumber = 0;
	private String IFScode = "";
	private long phoneNumber = 0;
	private double balance = 0;
	private String transactions = "";
	BankCustomer( int accountNumber, String firstName, String lastName, String IFScode, long phoneNumber, double balance )
	{
		if( !firstName.equals("") )
			this.firstName = firstName;
		if( !lastName.equals("") )
			this.lastName = lastName;
		if( accountNumber > 9999 && accountNumber < 100000 )
			this.accountNumber = accountNumber;
		if( !IFScode.equals(""))
			this.IFScode = IFScode;
		if( phoneNumber > 999999999 && phoneNumber < 10000000000l )
			this.phoneNumber = phoneNumber;
		if( balance >= 0 )
			this.balance = balance;
		
	}
	
	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	public String getIfScode()
	{
		return IFScode;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public long getPhoneNumber()
	{
		return phoneNumber;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public void setPhoneNumber( long phoneNumber )
	{
		this.phoneNumber = phoneNumber;
	} 
	
	public void setBalance( double balance )
	{
		if( balance >= 0 )
			this.balance = balance;
	}
	
	public void addTransaction( String str )
	{
		transactions += str;
	}
	
	public String getTracsactions( )
	{
		return transactions;
	}
}
