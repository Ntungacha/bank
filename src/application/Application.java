package application;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import people.Customer;
import pvibank.Account;
import pvibank.AccountStatement;
import pvibank.Card;

public class Application {

	public Application() {
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Customer customer = null;
		Account account = null;
		Card card = null;
		AccountStatement accountStatement = null;
		List<Customer> customers = new ArrayList<Customer>();
		LocalDate dateOfBirth = null;
		LocalDateTime date = null;

		for (int a = 0; a < 100; a++) {
			int list = 0;
			for (;;) {
				scan.hasNextLine();
				try {
					System.out.println("welcome to ABC Bank");
					System.out.println("choose an option from the list below");
					System.out.println("choose 1. for Open Account");
					System.out.println("choose 2. for Deposit");
					System.out.println("choose 3. for Withdraw");
					System.out.println("choose 4. for Check Balance");
					System.out.println("choose 5. for Statements");
					System.out.println("choose 6. for Other Options");
					System.out.println("choose 0. for EXIT");
					System.out.println("choose the operation you want to perform:");
					list = scan.nextInt();
					break;
				} catch (Exception ex) {
					System.out.println("Enter an option from the list below");
				}
			} // try and catch loop;

			if (list == 0) {
				System.out.println("\t\t\tTHANKS FOR USING OUR SYSTEM ");
				a = 100000;

			} else if (list == 1) {
				System.out.println("\t\t\t OPEN ACCOUNT ");
				scan.nextLine();
				System.out.print("Enter First Name :");
				String firstName = scan.nextLine();
				System.out.print("Enter Last Name :");
				String lastName = scan.nextLine();
				System.out.print("Enter Gender :");
				String gender = scan.nextLine();
				System.out.print("Enter Your Phone Number :");
				String phoneNumber = scan.nextLine();
				System.out.print("Enter Your email :");
				String emailAddress = scan.nextLine();
				System.out.println("Enter Your Date of Birth :");
				System.out.print("Enter year:");
				int year = scan.nextInt();
				System.out.print("Enter month:");
				int month = scan.nextInt();
				System.out.print("Enter day:");
				int day = scan.nextInt();
				dateOfBirth = LocalDate.of(year, month, day);
				System.out.println(dateOfBirth);
				System.out.print("Enter Your id Number :");
				String idNumber = scan.nextLine();
				System.out.print("Enter your Account Number :");
				long accountNumber = new Customer().accountN();
				System.out.print("Enter Account Type :");
				String accountType = scan.nextLine();
				scan.nextLine();
				System.out.print("Card Number :");
				long cardNumber = new Customer().cardN();
				System.out.print("Pin :");
				int pin = scan.nextInt();
				scan.nextLine();
				System.out.print("enter card type :");
				String cardType = scan.nextLine();
				System.out.print("Enter amount : ");
				double balance = scan.nextDouble();
				date = LocalDateTime.now();
				customer = new Customer(firstName, lastName, gender, phoneNumber, emailAddress, idNumber, dateOfBirth);
				account = new Account(accountNumber, accountType, balance);
				card = new Card(cardNumber, cardType, pin);
				customer.creatAccount(customer, account, card);
				customers.add(customer);
				System.out.println(customer);
			} // open account

			else if (list == 2) {
				System.out.println("\t\t\t DEPOSITE");
				scan.nextLine();
				System.out.print("enter account number :");
				long accountNumber = scan.nextLong();
				System.out.print("Enter amount to deposite : R");
				double amount = scan.nextDouble();
				new Customer().deposites(accountNumber, amount);
			} // deposite; list == 2

			else if (list == 3) {
				System.out.println("\t\t\t WITHDRAW");
				scan.nextLine();
				System.out.print("Enter account number : ");
				long accountNumber = scan.nextLong();
				System.out.print("Enter pin : ");
				int pin = scan.nextInt();
				System.out.print("Enter amount to withdraw : R");
				double amount = scan.nextDouble();
				new Customer().withdrawal(accountNumber, amount, pin);
			} // withdrawal; list == 3;

			else if (list == 4) {
				System.out.println("\t\t\t CHECK STATEMENT ");
				scan.nextLine();
				System.out.print("Enter account number : ");
				long accountNumber = scan.nextLong();
				System.out.print("Enter pin : ");
				int pin = scan.nextInt();
				new Customer().checkBalance(accountNumber, pin);
			} // check balance; list == 4;

			else if (list == 5) {
				System.out.println("\t\t\t STATEMENT");
				scan.nextLine();
				System.out.print("Enter account number :");
				long accountNumber = scan.nextLong();
				System.out.print("Enter pin : ");
				int pin = scan.nextInt();
				new Customer().viewStatement(accountNumber);
			} // statemet; list == 5;

		} // loop a;

	}// main method;

}// class Application;
