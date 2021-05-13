package pvibank;

import java.time.LocalDateTime;

public class AccountStatement {

	private LocalDateTime date;
	private String type;
	private double amount;
	private long accountNumber;

	public AccountStatement() {

	}
	
	
	public AccountStatement(LocalDateTime date, String type, double amount, long accountNumber) {
		super();
		this.date = date;
		this.type = type;
		this.amount = amount;
		this.accountNumber = accountNumber;
	}


	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String toString() {
		return "Statement \nDate =" + date + "\n Type=" + type + "\n Amount=" + amount + "]";
	}

}
