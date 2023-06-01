package com.company.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "EmailBlacklist")
@SuppressWarnings("serial")
public class EmailBlacklist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private Calendar blacklistedUntil;

    public EmailBlacklist() {
        super();
    }

    public EmailBlacklist(String email, Calendar until) {
        super();
        this.email = email;
        this.blacklistedUntil = until;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getBlacklistedUntil() {
        return blacklistedUntil;
    }

    public void setBlacklistedUntil(Calendar blacklistedUntil) {
        this.blacklistedUntil = blacklistedUntil;
    }
}
