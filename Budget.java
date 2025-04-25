
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Account {
    String getUsername() { return ""; }
}
/**
 * The {@code Budget} class will handle all budget related operations such as transactions, updating, creating, deleting, and reading in from CSV files
 *  @author Shaeem Rockcliffe
 *  @author Raphael Spoerri
 *  @author Giovanni Carrion
 *  @author John Ortega
 *  @version %I%, %G%
 */
class draft {
    private String userDataDir;

    /**
     * Constructs a Budget instance for a specific account
     * @params account is the account that will be associated with this budget instance. A valid account needs to be passed in order to create a budget instance.
     */
    public draft(Account account) throws IOException {
        String dir = System.getProperty("user.dir");
        userDataDir = dir + "/pfm_data/" + account.getUsername();
        var file = new File(userDataDir);
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("failed to create directory for user "
                + account.getUsername());
        }
    }

    /**
     * Represents one transaction, i.e. one row in the CSV file of transactions.
     * @author Shaeem Rockcliffe
     * @author Raphael Spoerri
     * @author Giovanni Carrion
     * @author John Ortega
     * @version %I%, %G%
     */
    public static class Transaction {
    	
    	private String date;
        private String category;
        private long amount;

        public Transaction(String date, String category, long amount) {
        	this.date = date;
        	this.category = category;
        	this.amount = amount;
        }
        
        /**
         * Returns the date the transaction took place, in the format MM/DD/YYYY.
         * @return the date of the transaction
         */
        public String getDate() { return date; }
        
        /**
         * Returns a string categorizing the transaction.
         * @return the transaction category
         */
        public String getCategory() { return category; }
        
        /**
         * Returns the net change (in cents) to the user's bank account, positive if money was added and negative if money was spent.
         * @return the net change
         */
        public long getAmount() { return amount; }
        
    }
    /**
     * Prompts the user for the year number for creating or 
     * updating the user's file.
     */
    public void promptToCreateOrUpdate() { /* not implemented yet */ }
    /**
     * Prompts the user for the year number of the file to
     * delete.
     */
    void promptToDelete() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> years = getYears();

        if (years == null || years.isEmpty()) {
            System.out.println("No transaction files available to delete.");
            scanner.close();
            return;
        }

        System.out.println("Available years:");
        for (int i = 0; i < years.size(); i++) {
            System.out.println((i + 1) + ". " + years.get(i));
        }

        System.out.print("Enter the corresponding number of the year you want to delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > years.size()) {
            System.out.println("Invalid choice.");
            scanner.close();
            return;
        }

        String yearToDelete = years.get(choice - 1);
        File fileToDelete = new File(userDataDir + "/transactions_" + yearToDelete + ".csv");

        if (fileToDelete.exists() && fileToDelete.delete()) {
            System.out.println("Successfully deleted: " + fileToDelete.getName());
        } else {
            System.out.println("Failed to delete: " + fileToDelete.getName());
        }
        
        scanner.close();
    }
    /**
     * 
     * Reads transaction data from a CSV file for a specific year.
     * @param year the year for which transactions are to be read
     * @return an ArrayList of Transaction objects for the given year
     */
    public ArrayList<Transaction> readCSV(String year) { /* not implemented yet */ return null; }
    /**
     * Retrieves a list of all years for which transaction data exists.
     * @return an ArrayList of String years
     */
    public ArrayList<String> getYears() { /* not implemented yet */ return null; }
}