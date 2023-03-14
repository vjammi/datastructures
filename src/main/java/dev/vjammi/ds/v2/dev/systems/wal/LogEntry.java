package dev.vjammi.ds.v2.dev.systems.wal;

/**
 * A Write-Ahead Log (WAL) is a standard technique used in databases to ensure durability of data.
 * In this implementation, we'll create a simple WAL in Java using file I/O.
 *
 * First, let's define a class that represents a single WAL entry:
 *   This class has two fields: transactionId, which is a unique identifier for the transaction, and data, which is the data being written to the log.
 * */
public class LogEntry {
    private long transactionId;
    private String data;

    public LogEntry(long transactionId, String data) {
        this.transactionId = transactionId;
        this.data = data;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public String getData() {
        return data;
    }
}