import java.util.Scanner;

////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           Auditable Banking
// Files:           AuditableBanking.java, AuditableBankingTests.java
// Course:          CS300, Fall 2018
//
// Author:          Stephen Fan
// Email:           sfan54@wisc.edu
// Lecturer's Name: Alexi Brooks
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates, 
//strangers, and others do.  If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  https://docs.oracle.com/javase/7/docs/api/
// used the above website for documentation in researching for the documentation in how to use some functions
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

public class AuditableBanking {
	
	public static int submitTransactions(int[] newTransactions, int[][] allTransactions, 
			int allTransactionsCount) {
		/**
		 * Adds a transaction group to an array of transaction groups. If the allTransactions array is
		 * already full then this method will do nothing other than return allTransactionCount.
		 * 
		 * @param newTransactions is the new transaction group being added (perfect size).
		 * @param allTransactions is the collection that newTransactions is being added to (oversize).
		 * @param allTransactionsCount is the number of transaction groups within allTransactions 
		 *        (before newTransactions is added).
		 * @return the number of transaction groups within allTransactions after newTransactions is added.
		 */
		
		if (allTransactionsCount != allTransactions.length) {
			allTransactions[allTransactionsCount] = newTransactions;
			allTransactionsCount++;
		}
		
		return allTransactionsCount;
	}
	
	public static int calculateNumberOfOverdrafts(int[][] allTransactions, int allTransactionsCount) {
		/**
		 * calculates the number of times the person has overdrafted their account
		 * @param allTransactions is the collection that newTransactions is being added to (oversize).
		 * @param allTransactionsCount is the number of transaction groups within allTransactions 
		 *        (before newTransactions is added).
		 * @return the number of times the person has overdrafted their account
		 */
		
		// initialize variables
		int overdrafts = 0;
		int balance = 0;
		
		//determines the type of transactions and calculates the number of overdrafts
		for (int i = 0; i < allTransactionsCount; i++) {
			
			//Binary Amount Transactions
			if (allTransactions[i][0] == 0)
				for (int q = 1; q < allTransactions[i].length; q++) {
					if (allTransactions[i][q] == 0) {
						balance--;
						if (balance < 0) {
							overdrafts++;
						}
					}
					else if (allTransactions[i][q] == 1) {
						balance++;
					}
				}
			
			//Integer Amount Transactions
			else if (allTransactions[i][0] == 1) {
				for (int q = 1; q < allTransactions[i].length; q++) {
					balance += allTransactions[i][q];
					if (balance < 0 && allTransactions[i][q] <0) {
						overdrafts++;
					}
				}
			}
			
			//Quick Withdraw Transactions
			else if (allTransactions[i][0] == 2) {
				for (int q = 1; q < allTransactions[i].length; q++) {
					if (q == 1) {
						for (int j = 0; j < allTransactions[i][q]; j++) {
							balance -= 20;
							if (balance < 0) {
								overdrafts++;
							}
						}
					}
					if (q == 2) {
						for (int j = 0; j < allTransactions[i][q]; j++) {
							balance -= 40;
							if (balance < 0) {
								overdrafts++;
							}
					}

					}
					if (q == 3) {
						for (int j = 0; j < allTransactions[i][q]; j++) {
							balance -= 60;
							if (balance < 0) {
								overdrafts++;
							}
					}

					}
					if (q == 4) {
						for (int j = 0; j < allTransactions[i][q]; j++) {
							balance -= 80;
							if (balance < 0) {
								overdrafts++;
							}
					}

				}
			}
			
		}
		}
		return overdrafts;
	}
	
	public static int processCommand (String command, int[][] allTransactions, int allTransactionsCount) {
		/**
		 * Adds a transaction group to an array of transaction groups. If the allTransactions array is
		 * already full then this method will do nothing other than return allTransactionCount.
		 * 
		 * @param command is a string of integers separated by spaces.
		 *  Once it has been converted into an array of integers, it will be the new transaction
		 *  group being added (perfect size).
		 * @param allTransactions is the collection that newTransactions is being added to (oversize).
		 * @param allTransactionsCount is the number of transaction groups within allTransactions 
		 *        (before newTransactions is added).
		 * @return the number of transaction groups within allTransactions after newTransactions is added.
		 */
		
		//initialize variables
		
		String[] splitarray = command.split(" ");
		int[] intsplitarray = new int[splitarray.length];
		int i = 0;
		
		//creates an integer array of command
		for(i = 0; i < splitarray.length; i++) {
			intsplitarray[i] = Integer.parseInt(splitarray[i]);
		}
		
		//updates allTransactionsCount and adds command to allTransactions array
		allTransactionsCount = AuditableBanking.submitTransactions(intsplitarray, allTransactions, allTransactionsCount);
		
		if (command.charAt(0) == 'B') {
			
		}
		
		return allTransactionsCount;
	}
	
	public static int calculateCurrentBalance(int[][] allTransactions, int allTransactionsCount) {
		/**
		 * calculates current balance based on a collection of provided transaction groups
		 * 
		 * @param allTransactions is the collection that newTransactions is being added to (oversize).
		 * @param allTransactionsCount is the number of transaction groups within allTransactions 
		 *        (before newTransactions is added).
		 * @return the total calculated balance
		 */
		
		//initialize variables
		
		final int QUICK_WITHDRAW_20BUCKS = 20;
		final int QUICK_WITHDRAW_40BUCKS = 40;
		final int QUICK_WITHDRAW_80BUCKS = 80;
		final int QUICK_WITHDRAW_100BUCKS = 100;
		int balance = 0;
		
		//calculate balance by first determining which type of transaction each array is, and then using the
		//correct corresponding calculations to determine the balance
		
		for (int i = 0; i < allTransactionsCount; i++) {
			
			//Binary Amount Transactions
			if (allTransactions[i][0] == 0) {
				for (int q = 1; q < allTransactions[i].length; q++) {
					if (allTransactions[i][q] == 0) {
						balance--;
					}
					if (allTransactions[i][q] == 1) {
						balance++;
					}
				}
			}
			
			//Integer Amount Transactions
			if (allTransactions[i][0] == 1) {
				for (int z = 1; z < allTransactions[i].length; z++) {
					balance += allTransactions[i][z];
				}
			}
			
			//Quick Withdraw Transactions
			if (allTransactions[i][0] == 2) {
					balance -= allTransactions[i][1] * QUICK_WITHDRAW_20BUCKS;
					balance -= allTransactions[i][2] * QUICK_WITHDRAW_40BUCKS;
					balance -= allTransactions[i][3] * QUICK_WITHDRAW_80BUCKS;
					balance -= allTransactions[i][4] * QUICK_WITHDRAW_100BUCKS;
			}
		}
		
		
		return balance;
		}
	
	public static void createNiceInterface () {
		/**
		 * creates a nice interface for the user to use this program more easily
		 */
		
		//initialize variables
		
		Scanner scan = new Scanner(System.in);
		boolean moreinput = true;
		boolean extraspace = false;
		int[][] allTransactions = new int[150][];
		int allTransactionsCount = 0;
		String userinput;
		
		//prints the user-friendly interface that gives the user a menu of options to choose from
		//runs the corresponding method depending on what the user inputs
		
		System.out.println("========== Welcome to the Auditable Banking App ==========");
		while (moreinput == true) {
			if (extraspace == true) {
				System.out.println("\n");
			}
			System.out.println("COMMAND MENU: \n\t Submit a Transaction (enter sequence of integers "
					+ "separated by spaces) \n\t Show Current [B]alance \n\t Show Number of [O]verdrafts \n\t"
					+ "[Q]uit Program \n ENTER COMMAND: ");
			userinput = scan.nextLine();
			if (userinput.length() > 1) {
				allTransactionsCount = AuditableBanking.processCommand(userinput,allTransactions,allTransactionsCount);
				extraspace = true;
			}
			else if (userinput.equals("B") || userinput.equals("b")) {
				System.out.println("Current Balance: " + AuditableBanking.calculateCurrentBalance(allTransactions,allTransactionsCount));
				extraspace = true;
			}
			else if (userinput.equals("O") || userinput.equals("o")) {
				System.out.println("Number of Overdrafts: " + AuditableBanking.calculateNumberOfOverdrafts(allTransactions, allTransactionsCount));
				extraspace = true;
			}
			else if (userinput.equals("Q") || userinput.equals("q")) {
				moreinput = false;
				System.out.println("============ Thank you for using this App!!!! ============");
			}
		}
		scan.close();
	}
	
	public static void main (String[] args) {
		//main method to run the program
		AuditableBanking.createNiceInterface();
	}
}