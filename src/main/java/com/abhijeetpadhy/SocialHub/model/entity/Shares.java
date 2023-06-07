package com.abhijeetpadhy.SocialHub.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHARES")
public class Shares {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "POST_ID")
    private long postId;

    @Column(name = "USERNAME")
    private String username;
}
