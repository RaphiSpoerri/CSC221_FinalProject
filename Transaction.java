/**
 * Represents one transaction, i.e. one row in the CSV file of transactions.
 * @author Shaeem Rockcliffe
 * @author Raphael Spoerri
 * @author Giovanni Carrion
 * @author John Ortega
 * @version %I%, %G%
 */
public class Transaction {
    private int month;
    private int day;
    private int year;
    private String category;
    private long amount;

    public Transaction(String date, String category, long amount) {
        String[] parts = date.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid date format: " + date);
        }

        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);

        this.category = category;
        this.amount = amount;
    }

    public int getMonth() {return month;}
    public int getDay() { return day; }
    public int getYear() { return year; }
    public String getCategory() { return category; }
    public long getAmount() { return amount; }
}
