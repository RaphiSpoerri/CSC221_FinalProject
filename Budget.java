
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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

        public Transaction(String date, String category, long amount) { /* not implemented yet */ }
        /**
         * Returns the date the transaction took place, in the format MM/DD/YYYY.
         * @return the date of the transaction
         */
        public String getDate() { /* not implemented yet */ return null; }
        /**
         * Returns a string categorizing the transaction.
         * @return the transaction category
         */
        public String getCategory() { /* not implemented yet */ return null;  }
        /**
         * Returns the net change (in cents) to the user's bank account, positive if money was added and negative if money was spent.
         * @return the net change
         */
        public long getAmount() { /* not implemented yet */ return 0;  }
    }
    /**
     * Prompts the user for the year number for creating or 
     * updating the user's file.
     */
    public void promptToCreateOrUpdate() { /* not implemented yet */ }
    /**
     * 
     * testing testing
     * 
     * Prompts the user for the year number of the file to
     * delete.
     */
    public void promptToDelete() { /* not implemented yet */ }
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



