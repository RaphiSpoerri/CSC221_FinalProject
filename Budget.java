import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget {
    private static final String userDataDir = "saved_files"; 

    public static class Transaction {
        private String date;
        private String category;
        private long amount;

        public Transaction(String date, String category, long amount) {
            this.date = date;
            this.category = category;
            this.amount = amount;
        }

        public String getDate() {
            return date;
        }

        public String getCategory() {
            return category;
        }

        public long getAmount() {
            return amount;
        }
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
