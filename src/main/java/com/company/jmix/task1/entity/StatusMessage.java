package com.company.jmix.task1.entity;

public class StatusMessage {
    private StatusType type;
    private String content;
    private String sender;

    public enum StatusType {
        JOIN,
        LEAVE
    }

    public StatusType getType() {
        return type;
    }

    public void setType(StatusType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}