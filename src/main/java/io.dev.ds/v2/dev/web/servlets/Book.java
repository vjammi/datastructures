package dev.vjammi.ds.v2.dev.web.servlets;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {
    static final long serialVersionUID = 1L;
    private String author;
    private String title;
    private int id;

    public Book() { }

    public void setAuthor(final String author) { this.author = author; }
    public String getAuthor() { return this.author; }
    public void setTitle(final String title) { this.title = title; }
    public String getTitle() { return this.title; }
    public void setId(final int id) { this.id = id; }
    public int getId() { return this.id; }

    public int compareTo(final Book other) { return this.id - other.id; }
}