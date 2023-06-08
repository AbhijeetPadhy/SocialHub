package com.abhijeetpadhy.SocialHub.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "FOLLOWS")
public class Follows {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "FOLLOWER")
    private String follower;

    @Column(name = "FOLLOWED_USER")
    private String followedUser;

    @Column(name = "SINCE")
    private Timestamp since;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(String followedUser) {
        this.followedUser = followedUser;
    }

    public Timestamp getSince() {
        return since;
    }

    public void setSince(Timestamp since) {
        this.since = since;
    }
}
