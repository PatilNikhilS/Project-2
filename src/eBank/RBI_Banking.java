package eBank;

import java.util.Scanner;
import java.io.*;

public class RBI_Banking {
	public static void main(String[] args) {

		System.out.println("*/*/*/*/*/*/***Welcome To RBI National Banking Interface***/*/*/*/*/*/*");
		System.out.println("\n");
		System.out.println("DO YOU WANT TO OPEN AN BANK ACCOUNT: YES/NO");
		// First input from user to create an account
		Scanner sc = new Scanner(System.in);
		String choice = sc.nextLine();

		if (choice.equalsIgnoreCase("Yes")) {
			NewAccount nacc = new NewAccount();
			nacc.CreateNewAcc();
		}
		if (choice.equalsIgnoreCase("No")) {
			BankAccount exBank = new BankAccount();
			exBank.bankInterface();
		}
	}
}
//class for New Account
	class NewAccount {
		String Bank, name, accuty, dob;
		int accotn;
		
		void CreateNewAcc() {
			Scanner sc = new Scanner(System.in);
			// Initiation of Account Process
			System.out.println("In Which Bank You would like to open Account 1.SBI 2.HDFC 3.ICICI");
			
//type of banks
			int preferedBank = sc.nextInt();
			if (preferedBank == 1) {
				Bank = "SBI";
			}
			if (preferedBank == 2) {
				Bank = "HDFC";
			}
			if (preferedBank == 3) {
				Bank = "ICICI";
			}
			//Prompt user to get basic details
			System.out.println("Please Enter your name: ");
			sc.nextLine();
			name = sc.nextLine(); 
			
			System.out.println("Please Enter your Date of Birth: ");
			dob = sc.nextLine();
			//type of account
			System.out.println("Type of Account you want to open: 1.Saving 2.Current ");
			int choice = sc.nextInt();
			if (choice == 1) {
				accuty = "Saving";
				
			}
			if (choice == 2) {
				accuty = "Current";
			}
			//Bank Details
			System.out.println("Your account has been opened with below details");
			System.out.println("Bank Name:" +Bank);
			System.out.println("Account Holder Name:" +name);
			System.out.println("Date of birth:" +dob);
			System.out.println("Account type :" +accuty);
			System.out.println("Account Number:" +Math.random());
				
			System.out.println("\n");
			
			BankAccount exBank = new BankAccount();
			exBank.bankInterface();
			sc.close();
		}

	}
//Bank options 
	class BankAccount {
		int balance;
		int previousTransaction;
		String customerName;
		String customerId;
		String typeOfAccount;
		double totalInterest;
		
		
		//Calculate interest for saving / current
		void InterestCalculation(double balance) {
			System.out.println("What type of account you have: 1.Saving 2.Current");
			Scanner sc = new Scanner(System.in);
			int choice =sc.nextInt();
			
			if(choice == 1) {
				// calculate interest on saving account
				typeOfAccount = "Saving";
				int r = 5;
				int time;
				System.out.println("Enter year to calculate interest");
				time =sc.nextInt();
				
				double finalAmount = (balance*r*time)/100;
				double totalInterest = finalAmount - balance;
				System.out.println("Total interest earned is: " + finalAmount);
			}
			if(choice == 2) {
				typeOfAccount = "Current";
				int r = 7;
				int time, numbertime;
				System.out.println("Enter year to calculate interest");
				time =sc.nextInt();
				System.out.println("Enter the frequency that interest has been compond in year ");
				
				numbertime = sc.nextInt();
				double finalAmount = balance *Math.pow (1+(r/numbertime),numbertime*time);
				
				double totalInterest = finalAmount - balance;
				
				System.out.println("Total interest earned is: " + totalInterest );
				
				sc.close();
			}
		}
		//credit
		void deposit(int amount) {
			if(amount !=0) 
			{
				balance = balance + amount;
				System.out.println("Balance after credited: " +balance);
				previousTransaction =  amount;
				
			}
		}
		//Debit
		void withdraw(int amount) {
			if (amount !=0) {
				
				balance = balance - amount;
				System.out.println("Balnce after debited: "+balance);
				previousTransaction = -amount;
			}
		}
		//Get previous transaction
		void getPreviouusTransaction() {
			
			FileOutputStream out;
			PrintStream p;
			
			try {
				out = new FileOutputStream("C:\\Users\\nikhil\\eclipse-workspace\\Project_2\\previousTrans.txt");
				p = new PrintStream(out);
				
				if (previousTransaction > 0)
				{
					p.append("Deposited: " + previousTransaction);
					System.out.println("Deposited: " + previousTransaction);
				}
				else if (previousTransaction < 0) {
					p.append("Withdrawn: " + previousTransaction);
					System.out.println("Withdrawn: " + Math.abs(previousTransaction));
				}
				else {
					System.out.println("No Transaction happened");	
					}
				p.close();
			} catch (Exception e) {
				System.out.println("Error in printing the data " +e);
			}
		}
		//main interface 
		void bankInterface() {
			char option = '\0';
			Scanner input = new Scanner(System.in);
			System.out.println("Welcome to the interface of NetBanking");
			System.out.println("\n");
			System.out.println("A. Check Balance");
			System.out.println("B. Deposit Amount");
			System.out.println("C. Withdraw amount");
			System.out.println("D. Check previous transtion");
			System.out.println("E. Calculate Interest");
			System.out.println("F. Exit");
			
			do {
				System.out.println("***********//////////////////**************///////////////////////**********");
				System.out.println("Enter an option");
				System.out.println("***********//////////////////**************///////////////////////**********");
				option = input.next().charAt(0);
				System.out.println("\n");
				
				//case's to select from options
				switch (option) {
				case 'A':
					System.out.println("_____________________***______________________________***__________________________");
					System.out.println("Balance = "+ balance);
					System.out.println("\n");
					break;
					
				case 'B':
					System.out.println("_____________________***______________________________***__________________________");
					System.out.println("Enter an amount to be credited");
					int amount = input.nextInt();
					deposit(amount);
					System.out.println("\n");
					break;
				case 'C': 
					System.out.println("_____________________***______________________________***__________________________");
					System.out.println("Enter an amount to be debited");
					int amount1 = input.nextInt();
					withdraw(amount1);
					System.out.println("\n");
					break;
				case 'D': 
					System.out.println("_____________________***______________________________***__________________________");
					System.out.println("Previous transaction");
					getPreviouusTransaction();
					System.out.println("\n");
					break;
				case 'E': 
					System.out.println("_____________________***______________________________***__________________________");
					System.out.println("Calculate Interest");
					InterestCalculation(balance);
					System.out.println("\n");
					break;
				case 'F':
					System.out.println("_____________________***______________________________***__________________________");
					break;
					default:
						System.out.println("Input is invalid! please try again...");
				}
			}while(option !='F');
			System.out.println("Thank for using Our Bank System");
			input.close();
		}
	}

