package com.abhijeetpadhy.SocialHub.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "FRIEND_REQUESTS")
public class FriendRequests {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "REQUESTER")
    private String requester;

    @Column(name = "REQUESTEE")
    private String requestee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRequestee() {
        return requestee;
    }

    public void setRequestee(String requestee) {
        this.requestee = requestee;
    }
}
