package pvibank;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Account {
	private long accountNumber;
	private String accountType;
	private double balance;
	public Card card;
	public List<AccountStatement> statements = new ArrayList<AccountStatement>();

	public Account() {

	}

	public Account(long accountNumber, String accountType, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.balance = balance;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public List<AccountStatement> getStatements() {
		return statements;
	}

	public void setStatements(List<AccountStatement> statements) {
		this.statements = statements;
	}

	public String toString() {
		return "\nAccount Number=" + accountNumber + "\nAccount Type=" + accountType + "\nBalance=" + balance + " "
				+ card;
	}

}
