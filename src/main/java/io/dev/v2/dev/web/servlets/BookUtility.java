package io.dev.v2.dev.web.servlets;

import org.json.JSONObject;
import org.json.XML;

import javax.servlet.ServletContext;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BookUtility {

    private final String fileName = "/WEB-INF/data/novels.db";
    private final ConcurrentMap<Integer, Book> books;
    private ServletContext context;
    private final AtomicInteger mapKey;

    public BookUtility() {
        books = new ConcurrentHashMap<Integer, Book>();
        mapKey = new AtomicInteger();
    }

    public void setServletContext(ServletContext sctx) {
        this.context = sctx;
    }

    public ServletContext getServletContext() {
        return this.context;
    }

    public ConcurrentMap<Integer, Book> getConcurrentMap() {
        if (getServletContext() == null) return null; // not initialized
        if (books.size() < 1) populate();
        return this.books;
    }

    public String toXml(Object obj) { // default encoding
        String xml = null;
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            XMLEncoder encoder = new XMLEncoder(out);
            encoder.writeObject(obj);
            encoder.close();
            xml = out.toString();
        } catch (Exception e) {
        }
        return xml;
    }

    public String toJson(String xml) { // option for requester
        try {
            JSONObject jobt = XML.toJSONObject(xml);
            return jobt.toString(3); // 3 is indentation level
        } catch (Exception e) {
        }
        return null;
    }

    public int addNovel(Book book) {
        int id = mapKey.incrementAndGet();
        book.setId(id);
        books.put(id, book);
        return id;
    }

    private void populate() {
        InputStream in = context.getResourceAsStream(this.fileName);

        // Convert novel.db string data into novels.
        if (in != null) {
            try {
                InputStreamReader isr = new InputStreamReader(in);
                BufferedReader reader = new BufferedReader(isr);

                String record = null;
                while ((record = reader.readLine()) != null) {
                    String[] parts = record.split("!");
                    if (parts.length == 2) {
                        Book book = new Book();
                        book.setAuthor(parts[0]);
                        book.setTitle(parts[1]);
                        addNovel(book); // sets the Id, adds to map
                    }
                }
                in.close();
            } catch (IOException e) {
            }
        }

    }
}