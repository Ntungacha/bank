package people;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

import pvibank.Account;
import pvibank.Card;

import pvibank.AccountStatement;

public class Customer {
	private String firstName;
	private String lastName;
	private String gander;
	private String phoneNumber;
	private String email;
	private String idNumber;
	private LocalDate dateOfBirth;
	private Account account;
	List<AccountStatement> stat = new ArrayList<AccountStatement>();
	// ArrayList<Statement> statements = new ArrayList<Statement>();
	// public AccountStatement accountStatement;
	public int num = 0;

	public Customer() {

	}

	public Customer(String firstName, String lastName, String gander, String phoneNumber, String email, String idNumber,
			LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gander = gander;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.idNumber = idNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGander() {
		return gander;
	}

	public void setGander(String gander) {
		this.gander = gander;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public void creatAccount(Customer customer, Account account, Card card) {
		try {
			this.setFirstName(customer.getFirstName());
			this.setLastName(customer.getLastName());
			this.setGander(customer.getGander());
			this.setPhoneNumber(customer.getPhoneNumber());
			this.setEmail(customer.getEmail());
			this.setIdNumber(customer.getIdNumber());
			this.setDateOfBirth(customer.getDateOfBirth());
			account.setCard(card);
			List<AccountStatement> statements = account.getStatements();
			AccountStatement accountStatement = createAccountStatement(account.getAccountNumber(),
					account.getBalance());
			statements.add(accountStatement);
			account.setStatements(statements);
			this.account = account;

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pviBank_db", "root",
					"Pvi@2020");
			Statement statement = connection.createStatement();
			String customerQuery = "INSERT INTO customer (customerid,firstName,lastName, gander,phoneNumber,"
					+ "emailAddress,dateOfBirth)" + "VALUES ('" + customer.getIdNumber() + "','"
					+ customer.getFirstName() + "','" + customer.getLastName() + "','" + customer.getGander() + "','"
					+ customer.getPhoneNumber() + "','" + customer.getEmail() + "','" + customer.getDateOfBirth()
					+ "');";

			String accountstmtQuery = "INSERT INTO accountStatement(date, type, amount, accountNumber) VALUES(' "
					+ accountStatement.getDate() + "' , ' " + accountStatement.getType() + " ' , "
					+ accountStatement.getAmount() + " , " + account.getAccountNumber() + " ) ";

			String cardQuery = "INSERT INTO card (cardNumber,pin,accountNumber) VALUES(" + card.getCardNumber() + " , "
					+ card.getPin() + "," + account.getAccountNumber() + ");";

			String accountQuery = "INSERT INTO account (accountNumber,accountType,accountBelance,customerid)"
					+ " VALUES(" + account.getAccountNumber() + ",'" + account.getAccountType() + "' , "
					+ account.getBalance() + ",'" + customer.getIdNumber() + "');";

			statement.executeUpdate(customerQuery);
			statement.executeUpdate(accountQuery);
			statement.executeUpdate(cardQuery);
			statement.executeUpdate(accountstmtQuery);
			// connection.close();

		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println(ex);
		}
	}

	public long cardN() {
		SplittableRandom rands = new SplittableRandom();
		String a = "45458800" + LocalDateTime.now().getSecond();
		String b = "" + rands.nextLong();
		b = b.substring(1, 6);
		a = a + b;
		Long n = Long.parseLong(a);
		return n;
	}

	public long accountN() {
		SplittableRandom rands = new SplittableRandom();
		String a = "1960292" + LocalDateTime.now().getSecond();
		String c = "" + rands.nextLong();
		c = c.substring(1, 4);
		a = a + c;
		long n = Long.parseLong(a);
		return n;
	}

	public long id(Customer customer) {
		SplittableRandom rands = new SplittableRandom();
		LocalDate a = customer.getDateOfBirth();
		String b = "" + rands.nextLong();
		b += a;
		long n = Long.parseLong(b);
		return n;
	}

	private AccountStatement createAccountStatement(long accNo, double amount) {
		AccountStatement accountStat = new AccountStatement();
		accountStat.setType("CREATE");
		accountStat.setDate(LocalDateTime.now());
		accountStat.setAccountNumber(accNo);
		accountStat.setAmount(amount);
		return accountStat;
	}

	public void deposites(long accountNumber, double amount) {
		AccountStatement accountStat = new AccountStatement();
		double balance = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pviBank_db", "root",
					"Pvi@2020");
			Statement statement = connection.createStatement();
			String selectQuery = " SELECT * FROM customer c JOIN account a "
					+ " WHERE c.customerid = a.customerid AND a.accountNumber = " + accountNumber;
			ResultSet resultSet = statement.executeQuery(selectQuery);

			if (amount >= 0) {
//					System.out.println("resultSet.getString(accountBelance) == " 
//				+ resultSet.getString("accountBelance"));;
				resultSet.next();
				double accBal = resultSet.getDouble("accountBelance");
				accBal += amount;
				accountStat.setType("DEPOSITE");
				accountStat.setDate(LocalDateTime.now());
				accountStat.setAmount(amount);
				stat.add(accountStat);
				String depositeQuery = "UPDATE account SET accountBelance = " + accBal + "WHERE accountNumber = "
						+ accountNumber;
				String accountstmtQuery = "INSERT INTO accountStatement(date, type, amount, accountNumber)"
						+ " VALUES(' " + accountStat.getDate() + "' , ' " + accountStat.getType() + " ' , "
						+ accountStat.getAmount() + " , " + accountNumber + " ) ";
				statement.executeUpdate(depositeQuery);
				statement.executeUpdate(accountstmtQuery);
				System.out.println("Transcation successfull.");
			} else {
				System.out.println("ERROR");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void withdrawal(long accountNumber, double amount, int pin) {
		AccountStatement accountStat = new AccountStatement();
		double balance = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pviBank_db", "root",
					"Pvi@2020");
			Statement statement = connection.createStatement();
			String selectQuery = "SELECT * FROM customer c JOIN account a JOIN card b"
					+ " WHERE c.customerid = a.customerid AND a.accountNumber =" + accountNumber + " AND b.pin = "
					+ pin;
			ResultSet resultSet = statement.executeQuery(selectQuery);
			resultSet.next();
			if (resultSet.getDouble("accountBelance") >= amount && amount >= 0) {
				double accBal = resultSet.getDouble("accountBelance");
				accBal -= amount;
				System.out.println("please collect your money");
				accountStat.setType("WITHDRAWAL");
				accountStat.setDate(LocalDateTime.now());
				accountStat.setAmount(amount);
				stat.add(accountStat);
				String withdrawQuery = "UPDATE account SET accountBelance = " + accBal + "WHERE accountNumber = "
						+ accountNumber;
				String accountstmtQuery = "INSERT INTO accountStatement(date, type, amount, accountNumber) "
						+ "VALUES(' " + accountStat.getDate() + "' , ' " + accountStat.getType() + " ' , "
						+ accountStat.getAmount() + " , " + accountNumber + " ) ";
				statement.executeUpdate(withdrawQuery);
				statement.executeUpdate(accountstmtQuery);
				System.out.println("TAKE YOUR MONEY");
			} else {
				System.out.println("INSUFFICIENT AMOUNT");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void viewStatement(long accountNumber) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pviBank_db", "root", "Pvi@2020");
			Statement stmt = conn.createStatement();
			String sqlStatement = "select * from accountStatement where accountNumber=" + accountNumber;
			ResultSet resultSet = stmt.executeQuery(sqlStatement);
			while (resultSet.next()) {
				String type = resultSet.getString("type");
				double amount = resultSet.getDouble("amount");
				System.out.println(
						"Date : " + resultSet.getDate("date") + "\n Type : " + type + "\n Amount : R" + amount);
				System.out.println("-------------------------------------------------------------------------");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkBalance(long accountNumber, int pin) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pviBank_db", "root", "Pvi@2020");
			Statement stmt = conn.createStatement();
			String sqlStatement = "SELECT * FROM customer c JOIN account a JOIN card b"
					+ " WHERE c.customerid = a.customerid AND a.accountNumber =" + accountNumber + " AND b.pin =" + pin;
			;
			ResultSet resultSet = stmt.executeQuery(sqlStatement);
			while (resultSet.next()) {
				double balance = resultSet.getDouble("accountBelance");
				System.out.println("YOUR BALANCE IS : \t\t" + resultSet.getDouble("accountBelance"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		return "Customer \nfirstName=" + firstName + "\nlastName=" + lastName + "\ngander=" + gander + "\nphoneNumber="
				+ phoneNumber + "\nemail=" + email + "\nidNumber=" + idNumber + "\ndateOfBirth=" + dateOfBirth
				+ account;
	}

}
