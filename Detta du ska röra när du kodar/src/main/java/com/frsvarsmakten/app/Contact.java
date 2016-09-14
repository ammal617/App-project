package com.frsvarsmakten.app;

/**
 * Created by T420S on 2014-04-08.
 */
public class Contact {
    private long contactId;
    private String number;
    private String firstname;
    private String lastname;


    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return firstname;
    }
}