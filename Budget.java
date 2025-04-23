
import java.util.ArrayList;

class Account {}
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
    Budget(Account account) { }
    /**
     * Represents one transaction, i.e. one row in the CSV file of transactions.
     * @author Shaeem Rockcliffe
     * @author Raphael Spoerri
     * @author Giovanni Carrion
     * @author John Ortega
     * @version %I%, %G%
     */
    public static class Transaction {
        /**
         * Returns the date the transaction took place, in the format MM/DD/YYYY.
         * @return the date of the transaction
         */
        public String getDate() { return null; }
        /**
         * Returns a string categorizing the transaction.
         * @return the transaction category
         */
        public String getCategory() { return null;  }
        /**
         * Returns the net change (in cents) to the user's bank account, positive if money was added and negative if money was spent.
         * @return the net change
         */
        public long getAmount() { return 0;  }
    }
    /**
     * Prompts the user for the year number for creating or 
     * updating the user's file.
     * The implementation of how the prompt is displayed and
     * how the user's choice is handled is left to the implementing 
     * class.
     */
    void promptToCreateOrUpdate() { }
    /**
     * Prompts the user for the year number of the file to
     * delete.
     * The implementation of how the prompt is displayed and
     * how the user's choice is handled is left to the implementing 
     * class.
     */
    void promptToDelete() { }
    /**
     * Reads transaction data from a CSV file for a specific year.
     * @param year the year for which transactions are to be read
     * @return an ArrayList of Transaction objects for the given year
     */
    ArrayList<Transaction> readCSV(String year) { return null; }
    /**
     * Retrieves a list of all years for which transaction data exists.
     * @return an ArrayList of String years
     */
    ArrayList<String> getYears() { return null; }
}



