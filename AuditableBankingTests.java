import java.util.Arrays;

public class AuditableBankingTests {
	/**
	 * test class to test AuditableBanking.java's methods
	 */
	
	public static boolean testProcessCommand() {
		/**
		 * tests that the processCommand method works
		 * @return returns true if the correct value is returned and false if an incorrect value is returned
		 */
		
		//initialize variables
		String teststring = new String ("0 0 1 1 0 1 1");
		int[][] bigarray = new int[10][];
		bigarray[0] = new int[]{2,3,4,5,6};
		int allTransactionsCount = 1;
		boolean bugs = true;
		
		int bugs2 = AuditableBanking.processCommand(teststring, bigarray, allTransactionsCount);
		
		//change boolean to false if the correct numbers are not reached
		if (bigarray[1][4] != 0) {
			bugs = false;
		}
		
		else if (bugs2 != 2) {
			bugs = false;
		}
		
		return bugs;
	}
	
	public static boolean testCalculateCurrentBalance() {
		/**
		 * tests that the calculateCurrentBalance() method works
		 * @return returns true if the correct value is returned and false if an incorrect value is returned
		 */
		
		//initialize variables
		int[][] bigarray = new int[10][];
		bigarray[0] = new int[]{2,3,4,5,6};
		bigarray[1] = new int[] {1,10,30};
		bigarray[2] = new int[] {0,1,1,1,1,0};
		int allTransactionsCount = 1;
		boolean bugs = true;
		
		int balance = AuditableBanking.calculateCurrentBalance(bigarray, allTransactionsCount);
		
		//change boolean to false if the correct numbers are not reached. there is one test case for each
		//transaction type
		if (balance != 1000) {
			bugs = false;
		}
		else {
			allTransactionsCount++;
			balance = AuditableBanking.calculateCurrentBalance(bigarray, allTransactionsCount);
		}
		
		if (balance != 1040) {
			bugs = false;
		}
		else {
			allTransactionsCount++;
			balance = AuditableBanking.calculateCurrentBalance(bigarray, allTransactionsCount);
		}
		
		if (balance != 1043) {
			bugs = false;
		}
		
		return bugs;
	}
	
	public static boolean testCalculateNumberOfOverdrafts() {
		/**
		 * tests that the calculateNumberOfOverdrafts method returns the correct amount
		 */
		boolean foundProblem = false;
		int[][] transactions = new int[][] {
			{1,10,-20,+30,-20,-20}, // +2 overdrafts (ending balance:  -20)
			{0,1,1,1,0,0,1,1,1,1},  // +2 overdrafts (ending balance:  -15)
			{1,115},                // +0 overdrafts (ending balance: +100)
			{2,3,1,0,1},            // +1 overdrafts (ending balance: -100)
		};
  
		// test with a single transaction of the Integer Amount encoding
		int transactionCount = 1;    
		int overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		if(overdrafts != 2) {
			System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 2 when transactionCount = 1, and transactions contained: "+Arrays.deepToString(transactions));
			foundProblem = true;
		} else
			System.out.println("PASSED TEST 1/2 of TestCalculateNumberOfOverdrafts!!!");
 
		// test with four transactions: including one of each encoding
		transactionCount = 4;
		overdrafts = AuditableBanking.calculateNumberOfOverdrafts(transactions,transactionCount);
		if(overdrafts != 5) {
			System.out.println("FAILURE: calculateNumberOfOverdrafts did not return 5 when transactionCount = 4, and transactions contained: "+Arrays.deepToString(transactions));
			foundProblem = true;
		} else
			System.out.println("PASSED TESTS 2/2 of TestCalculateNumberOfOverdrafts!!!");
  
		return !foundProblem;
}
	
	public static void main (String[] args) {
		//main method to run the tests
		if (AuditableBankingTests.testProcessCommand() == true) {
			System.out.println("True");
		}
		else {
			System.out.println("False");
		}
		
		AuditableBankingTests.testCalculateNumberOfOverdrafts();
	}
}
