package com.yijingz.eventnearby;

public class Event {
    private String title;
    private String address;
    private String description;
    private String url;

    /* Constructor */
    public Event (String title, String address, String description, String url) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.url = url;
    }

    /* Accessors */
    public String getTitle() { return this.title; }

    public String getAddress() { return this.address; }

    public String getDescription() { return this.description; }

    public String getUrl() { return this.url; }
}
