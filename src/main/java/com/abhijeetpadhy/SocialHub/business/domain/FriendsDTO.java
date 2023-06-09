package com.abhijeetpadhy.SocialHub.business.domain;

import java.sql.Timestamp;

public class FriendsDTO {
    private String friend;
    private int since;
    private String name;

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public int getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = since.toLocalDateTime().getYear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
