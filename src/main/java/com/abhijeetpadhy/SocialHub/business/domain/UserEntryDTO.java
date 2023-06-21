package com.abhijeetpadhy.SocialHub.business.domain;

public class UserEntryDTO {
    private String name;
    private String email;
    private String username;
    private String profilePhotoName;
    private String profilePhotoPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfilePhotoName() {
        return profilePhotoName;
    }

    public void setProfilePhotoName(String profilePhotoName) {
        this.profilePhotoName = profilePhotoName;
        profilePhotoPath = "fetch/profile/" + username;
    }

    public boolean hasProfilePhoto() {
        if(getProfilePhotoName() == null)
            return false;
        return true;
    }

    public String getProfilePhotoPath() {
        return profilePhotoPath;
    }
}
