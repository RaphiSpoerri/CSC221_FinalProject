
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Account {
    String getUsername() {
        return "";
    }
}
/**
 * The {@code Budget} class will handle all budget related operations such as
 * transactions, updating, creating, deleting, and reading in from CSV files
 *  @author Shaeem Rockcliffe
 *  @author Raphael Spoerri
 *  @author Giovanni Carrion
 *  @author John Ortega
 *  @version %I%, %G%
 */
public class Budget {
    // for testing:
    public static void main(String[] args) {
        try {
            var me = new Account();
            var b = new Budget(me);
            var sc = new Scanner(System.in);
            String cmd;
            while (true) {
                System.out.print("enter a command ([l]ist/[r]ead/[u]pdate/[d]elete/[q]uit)\n>>> ");
                cmd = sc.next();
                switch (cmd.charAt(0)) {
                    case 'l':
                        for (var year : b.getYears()) System.out.println(year);
                        break;
                    case 'r':
                        System.out.print("year #: ");
                        for (var tr : b.readCSV(sc.nextInt()))
                            System.out.printf(
                                "%s,%s,%s\n", tr.getDate(), tr.getAmount(), tr.getDate());
                        break;
                    case 'd': b.promptToDelete(); break;
                    case 'u': b.promptToCreateOrUpdate(); break;
                    case 'q': System.exit(0);
                    default: System.err.println("Invalid command.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private String userDataDir;

    /**
     * Constructs a Budget instance for a specific account
     * @params account is the account that will be associated with this budget
     * instance. A valid account needs to be passed in order to create a budget
     * instance.
     */
    public Budget(Account account) throws IOException {
        String dir = System.getProperty("user.dir");
        userDataDir = dir + "/pfm_data/" + account.getUsername();
        var file = new File(userDataDir);
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException("Failed to create directory for user " + account.getUsername());
        }
    }

    public void promptToCreateOrUpdate() throws IOException {
        Scanner userInput = new Scanner(System.in);
    
        promptEnsuringInput("Enter the year of the file you want to create/update: ", userInput);
        int userYear = userInput.nextInt();
        userInput.nextLine();
    
        // Validate the year input
        if (userYear < 1000 || userYear > 9999) {
            System.out.println("Invalid year. Please provide a valid year.\n");
            userInput.close();
            return;
        }
    
        verifyUserDataDir();
    
        String filename = userDataDir + "/" + userYear + ".csv";
        File savedFile = new File(filename);
    
        // Check file name format
        if (!savedFile.getName().matches("^[0-9]{4}\\.csv$")) {
            System.err.println("Error: The file name must match the pattern YYYY.csv.");
            return;
        }
    
        // Check if the file exists, if not, create it
        if (!savedFile.exists()) {
            try {
                if (savedFile.createNewFile()) {
                    System.out.println("File created: " + savedFile.getName());
                } else {
                    System.err.println("Error: Could not create the file.");
                    return;
                }
            } catch (IOException e) {
                System.err.println("Error creating file: " + e.getMessage());
                return;
            }
        } else {
            // If the file exists, check if it's a directory
            if (savedFile.isDirectory()) {
                System.err.println("Error: A directory with this name already exists.");
                return;
            }
    
            // Validate the file content using the validation manager
            boolean isValid = ValidationManager.CheckCSVContent.validateWholeCSVFile(userYear, filename);
            
            // If invalid, prompt the user
            if (!isValid) {
                promptEnsuringInput("The CSV file contains invalid records. Continue anyway? (y/n): ", userInput);
                String userResponse = userInput.nextLine();
                if (!userResponse.equalsIgnoreCase("y") && !userResponse.equalsIgnoreCase("yes")) {
                    System.out.println("No changes have been made.");
                    return;
                }
            }
    
            // Prompt the user to overwrite if the file exists
            String userResponse = promptEnsuringInput("CSV data for year already exists. Overwrite it (y/n): ", userInput);
    
            if (!userResponse.equalsIgnoreCase("y") && !userResponse.equalsIgnoreCase("yes")) {
                System.out.println("No changes have been made.");
                return;
            }
        }
    
        // Proceed with copying the file content
        try (BufferedReader fileReader = new BufferedReader(new FileReader(savedFile));
             BufferedWriter fileWriter = new BufferedWriter(new FileWriter(savedFile))) {
    
            String line;
            while ((line = fileReader.readLine()) != null) {
                fileWriter.write(line);
                fileWriter.newLine();
            }
    
            System.out.println("File created/updated successfully: " + savedFile.getName());
        } catch (IOException e) {
            System.err.println("Error reading or writing to file: " + e.getMessage());
        }
    }
    /**
     * Prompts the user for the year number of the file to
     * delete.
     */
    void promptToDelete() throws IOException {
        verifyUserDataDir();  // Ensures directory exists and is valid
        Scanner scanner = new Scanner(System.in);
        
        promptEnsuringInput("Enter the year of the file you want to delete: ", scanner);
        
        if (!scanner.hasNextInt()) {
            System.err.println("Error: Year must be an integer.");
            return;
        }
        
        int year = scanner.nextInt();
        
        if (year < 1000 || year > 9999) {
            System.err.println("Error: Year must be a 4-digit number.");
            return;
        }
    
        File fileToDelete = new File(userDataDir + "/" + year + ".csv");
    
        if (!fileToDelete.exists()) {
            System.err.println("Error: File does not exist for year " + year + ".");
            return;
        }
    
        if (fileToDelete.isDirectory()) {
            System.err.println("Error: Expected a file, but found a directory.");
            return;
        }
    
        if (!fileToDelete.delete()) {
            throw new IOException("Failed to delete file " + fileToDelete.getAbsolutePath());
        }
    
        System.out.println("Successfully deleted: " + fileToDelete.getName());
    }
    
    /**
     * Reads a CSV file for a given year and returns a list of transactions.
     * @param year the year to read
     * @return list of transactions from the file
     * @throws IOException if the file cannot be read
     */
    public ArrayList<Transaction> readCSV(int year) throws IOException {
        verifyUserDataDir(); 
    
        String filename = userDataDir + "/" + year + ".csv";
        File file = new File(filename);
    
        if (!file.exists()) {
            System.err.println("Error: File not found: " + filename);
            return null;
        }
    
        if (file.isDirectory()) {
            System.err.println("Error: Expected a file but found a directory: " + filename);
            return null;
        }
    
        if (!ValidationManager.CheckCSVContent.validateWholeCSVFile(year, filename)) {
            Scanner scanner = new Scanner(System.in);
            promptEnsuringInput("CSV file failed validation. Continue anyway (y/n)? ", scanner);
            String response = scanner.next().trim().toLowerCase();
            if (!(response.equals("y") || response.equals("yes"))) {
                return null;
            }
        }
    
        ArrayList<Transaction> transactions = new ArrayList<>();
    
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
    
                if (!ValidationManager.CheckCSVContent.validateLine(year, line)) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }
    
                String[] parts = line.split(",");
                String date = parts[0].trim();
                String category = parts[1].trim();
                long amount = Long.parseLong(parts[2].trim());
    
                transactions.add(new Transaction(date, category, amount));
            }
        }
    
        return transactions;
    }

    /**
     * Returns a list of years (based on files present in the saved files
     * directory).
     * @return list of years
     */
    public ArrayList<Integer> getYears() {
        verifyUserDataDir(); 
    
        ArrayList<Integer> years = new ArrayList<>();
        File directory = new File(userDataDir);
    
        File[] files = directory.listFiles();
        if (files == null) {
            System.err.println("Failed to fetch user data files");
            return null;
        }
    
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".csv")) {
                String filename = file.getName().replace(".csv", "");
                try {
                    int year = Integer.parseInt(filename);
                    if (year >= 1000 && year <= 9999) {
                        years.add(year);
                    }
                } catch (NumberFormatException e) {
                    // Ignore invalid filenames
                }
            }
        }
    
        return years;
    }

    private void panic(String msg, Object ...args) {
        System.err.println("Fatal error: " + String.format(msg, args));
        System.exit(1);
    }

    private void verifyUserDataDir() {
        var file = new File(userDataDir);
        if (!file.exists()) {
            panic("Internal storage is corrupt: Directory %s is missing.", userDataDir);
        }
        if (!file.isDirectory()) {
            panic("Internal storage is corrupt: %s is not a directory.", userDataDir);
        }
    }

    private void promptEnsuringInput(String prompt, Scanner scanner) {
        System.out.print(prompt);

        if (!scanner.hasNext()) {
            panic("Unexpected end of the input, exiting.");
        }
    }
}
