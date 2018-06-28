package com.yijingz.eventnearby;

public class User {
    private String username;
    private String password;
    private long timeJoined;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getTimeJoined() {
        return timeJoined;
    }

    public void setTimeJoined(long timeJoined) {
        this.timeJoined = timeJoined;
    }

    public User (final String username, final String password, final long timeJoined) {
        this.username = username;
        this.password = password;
        this.timeJoined = timeJoined;
    }

}
