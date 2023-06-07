package com.abhijeetpadhy.SocialHub.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "FRIENDS")
public class Friends {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "FRIEND")
    private String friend;

    @Column(name = "SINCE")
    private Timestamp since;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public Timestamp getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = since;
    }
}
