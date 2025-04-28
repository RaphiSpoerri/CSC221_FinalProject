
import java.io.File;
import java.io.FileWriter;
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
class Budget {
    private String userDataDir;

    /**
     * Constructs a Budget instance for a specific account
     * @params account is the account that will be associated with this budget instance. A valid account needs to be passed in order to create a budget instance.
     */
    public Budget(Account account) throws IOException {
        String dir = System.getProperty("user.dir");
        userDataDir = dir + "/pfm_data/" + account.getUsername();
        var file = new File(userDataDir);
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("Failed to create directory for user "
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
    public void promptToCreateOrUpdate() {
        /* not implemented yet */ 
        Scanner userInput = new Scanner(System.in);
        ArrayList<String> years = getYears();

        while (true) {
            if (years == null) {
                System.out.println("No existing data found. Would you like to create a new file? (yes/no)");
                String response = userInput.next();

                if (response.toLowerCase().equals("yes")) {
                    System.out.println("Enter the year for the new file: ");

                    while (true) {
                        int userYear = userInput.nextInt();

                        if (userYear >= 1000 && userYear <= 9999) {

                            String filename = userYear + ".csv";

                            try (FileWriter writer = new FileWriter(filename)) {
                                System.out.println("Successfully created file: " + filename);

                                /* inserting initial data not implemented yet */

                            } 
                            catch (IOException e) {
                                e.printStackTrace(); /* testing purposes */
                            }
                            break; 
                        } 
                        else {
                            System.out.println("Invalid input. Please enter a valid year between 1000 and 9999:");
                        }
                    }
                    break; 

                } 
                else if (response.toLowerCase().equals("no")) {
                    System.out.println("Exiting process...");
                    break; 
                } 
                else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    // No break here, restart the outer loop
                }
            } 
            else{
                System.out.println("Would you like to create or update a file? (create/update)");
                String response = userInput.next();

                if (response.toLowerCase().equals("yes")) {
                    System.out.println("Enter the year for the new file: ");

                    while (true) {
                        int userYear = userInput.nextInt();

                        if (userYear >= 1000 && userYear <= 9999) {

                            String filename = userYear + ".csv";

                            try (FileWriter writer = new FileWriter(filename)) {
                                System.out.println("Successfully created file: " + filename);

                                /* inserting initial data not implemented yet */

                            } 
                            catch (IOException e) {
                                e.printStackTrace(); /* testing purposes */
                            }
                            break; 
                        } 
                        else {
                            System.out.println("Invalid input. Please enter a valid year between 1000 and 9999:");
                        }
                    }
                    break; 

                }  
                if(response.toLowerCase().equals("update")){

                /*Waiting for the readCSV and getYears to be implemented, to implement */

                // print out the years

                // ask for which year
                System.out.println("Enter the year to update: ");
                int updateYear = userInput.nextInt();

                // print out that years transactions
                // delete, or update a specific transaction
                // keep looping til user content
                break;
                } 
                else {
                    System.out.println("Invalid input. Please enter 'create' or 'update'.");
                    // No break here, restart the outer loop
                }
            }
        }
        userInput.close();
    }
    /**
     * Prompts the user for the year number of the file to
     * delete.
     */
    void promptToDelete() throws IOException{
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the year of the file you want to delete: ");
        String year = scanner.nextLine();

        File fileToDelete = new File(userDataDir + "/" + year + ".csv");

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
