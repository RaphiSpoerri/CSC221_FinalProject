import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget {
    private static final String FILE_DIRECTORY = "saved_files";

    public static class Transaction {
        private String date;
        private String category;
        private long amount;

        public Transaction(String date, String category, long amount) {
            this.date = date;
            this.category = category;
            this.amount = amount;
        }

        public String getDate()      { return date; }
        public String getCategory()  { return category; }
        public long   getAmount()    { return amount; }
    }

    /**
     * Reads a CSV file for the given year (YYYY.csv) from the save directory
     * and returns the list of transactions.
     *
     * @param year the 4-digit year whose file you want to read
     * @return an ArrayList of Transaction objects
     * @throws IOException if the file doesn’t exist or can’t be read
     */
    public ArrayList<Transaction> readCSV(int year) throws IOException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        File file = new File(FILE_DIRECTORY, year + ".csv");

        if (!file.exists()) {
            throw new FileNotFoundException("No file found for year: " + year);
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;              // skip blank lines
                String[] parts = line.split(",");
                if (parts.length != 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String date     = parts[0].trim();
                String category = parts[1].trim();
                long amount     = Long.parseLong(parts[2].trim());
                transactions.add(new Transaction(date, category, amount));
            }
        }
        return transactions;
    }

    /**
     * Scans the save directory for files named YYYY.csv and returns
     * a list of the years found.
     *
     * @return an ArrayList of Integer years for which a CSV exists
     */
    public ArrayList<Integer> getYears() {
        ArrayList<Integer> years = new ArrayList<>();
        File dir = new File(FILE_DIRECTORY);

        if (!dir.isDirectory()) {
            System.out.println("Save directory not found: " + FILE_DIRECTORY);
            return years;
        }

        File[] files = dir.listFiles((d, name) -> name.matches("\\d{4}\\.csv"));
        if (files == null) return years;

        for (File f : files) {
            String name = f.getName();
            try {
                int year = Integer.parseInt(name.substring(0, 4));
                years.add(year);
            } catch (NumberFormatException e) {
                // ignore files that aren’t valid YYYY.csv
            }
        }
        return years;
    }
}
