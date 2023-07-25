package dev.vjammi.ds.v2.dev.concurrency.immutablity;

import java.util.Date;

public final class Account {
    private final String accountId;
    private final String name;
    private final Date creationDate;

    public Account(String accountId, String name, Date creationDate) {
        this.accountId = accountId;
        this.name = name;
        this.creationDate = new Date(creationDate.getTime());
    }
    public String getAccountId() {
        return accountId;
    }
    public String getName() {
        return name;
    }

    public Date getCreationDate() {
        return new Date(this.creationDate.getTime());
    }
}