/**
 * Represents one transaction, i.e. one row in the CSV file of transactions.
 * @author John Ortega
 * @version %I%, %G%
 */
public class Transaction {
    private int month;
    private int day;
    private int year;
    private String category;
    private long amount;
    
    /**
     * Constructs a {@code Transaction} object, assuming the date and category are valid.
     * @param date the date of the transaction in the format MM/DD/YYYY.
     * @param category the category of the transaction.
     * @param amount the amount in dollars.
     */
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
    /** Returns the month the transaction took place.
     * @return the month (1-12) of the transaction.
     */
    public int getMonth() { return month; }
    /** Returns the day of the month the transaction took place.
     * @return the day (1-31) of the transaction.
     */
    public int getDay() { return day; }
    /** Returns the year the transaction took place.
     * @return the year (1000-9999) of the transaction (should be the same as the file it came from).
     */
    public int getYear() { return year; }
    /** Returns the category of the transaction.
     * @return the category.
     */
    public String getCategory() { return category; }
    /** Returns the amount of money added or withdrawn.
     * @return the net change in dollars.
     */
    public long getAmount() { return amount; }

    @Override
    public String toString() {
        return String.format("Transaction{%s/%s/%s, %s, %s}", month, day, year, category, amount);
    }
}
