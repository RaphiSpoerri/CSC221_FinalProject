<h2>Breaking Changes</h2>

Planned:
* Change `ArrayList<Transaction> readCSVForYear(int year)` to `String year`
* Change `ArrayList<Integer> getYears()` to `String`
* Each transaction record’s `long transaction.getAmount()` now returns the number of dollars, not cents. Similarly, the CSV files’ records must now be long integers, representing the number of dollars.

<h2>Non-breaking Changes</h2>

Planned:
* Use `BufferedWriter` and `BufferedReader` whenever possible.
