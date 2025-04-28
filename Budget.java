
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

                            String filename = userDataDir + "/" + userYear + ".csv";

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

                            String filename = userDataDir + "/" + userYear + ".csv";

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
    void promptToDelete() throws IOException {
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
     * Reads a CSV file for a given year and returns a list of transactions.
     * @param year the year to read
     * @return list of transactions from the file
     * @throws IOException if the file cannot be read
     */
    public ArrayList<Transaction> readCSV(int year) throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        String filename = userDataDir + "/" + year + ".csv";
        File file = new File(filename);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filename);
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue; 
                }
                String[] parts = line.split(",");

                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String date = parts[0].trim();
                String category = parts[1].trim();
                long amount = Long.parseLong(parts[2].trim());

                transactions.add(new Transaction(date, category, amount));
            }
        }
        return transactions;
    }

    /**
     * Returns a list of years (based on files present in the saved files directory).
     * @return list of years
     */
    public ArrayList<Integer> getYears() {
        ArrayList<Integer> years = new ArrayList<>();
        File directory = new File(userDataDir);

        if (!directory.exists() || !directory.isDirectory()) {
            return years; // Return empty list if null
        }

        File[] files = directory.listFiles();
        if (files == null) {
            return years;
        }

        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".csv")) {
                String filename = file.getName().replace(".csv", "");
                try {
                    int year = Integer.parseInt(filename);
                    years.add(year);
                } catch (NumberFormatException e) {
                    // Ignore files that are not valid years
                }
            }
        }

        return years;
    }
}
