package dev.vjammi.ds.v2.dev.systems.wal;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Next, we'll create a WAL class that manages the log entries and writes them to a file
 *
 * The WriteAheadLog class has a constructor that takes a filename, which is the name of the file that the log will be
 * written to. It also has two methods: write, which writes a new entry to the log file and adds it to the in-memory
 * list of log entries, and read, which reads all the entries from the log file into the in-memory list.
 *
 * When the write method is called, it creates a new LogEntry object and adds it to the list of log entries. It then
 * opens the log file in append mode using a BufferedWriter, writes the log entry to the file in the format
 * transactionId,data, and closes the file.
 *
 * When the read method is called, it opens the log file in read mode using a BufferedReader and reads each line
 * of the file. For each line, it splits the line into two parts using a comma as the delimiter, converts the first
 * part to a long, and creates a new LogEntry object with the transaction ID and data. It then adds the new log entry
 * to the in-memory list of log entries. Once all the lines have been read, it returns the list of log entries.
 *
 * And that's it! You can use this WriteAheadLog class to implement durability in your application by ensuring that all
 * writes are first written to the WAL before being committed to the database.
 *
 * */
public class WriteAheadLog {
    private final String filename;
    private final List<LogEntry> logEntries;

    public WriteAheadLog(String filename) {
        this.filename = filename;
        logEntries = new ArrayList<>();
    }

    public void write(long transactionId, String data) throws IOException {
        logEntries.add(new LogEntry(transactionId, data));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.write(transactionId + "," + data + "\n");
        writer.close();
    }

    public List<LogEntry> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            long transactionId = Long.parseLong(parts[0]);
            String data = parts[1];
            logEntries.add(new LogEntry(transactionId, data));
        }
        reader.close();
        return logEntries;
    }
}
