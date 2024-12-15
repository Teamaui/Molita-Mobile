package com.molita.molita.model.data;

public class Message {
    private String messageText;
    private boolean isSent;
    private String time;

    public Message(String messageText, boolean isSent, String time) {
        this.messageText = messageText;
        this.isSent = isSent;
        this.time = time;
    }

    public String getMessageText() { return messageText; }
    public boolean isSent() { return isSent; }
    public String getTime() { return time; }
}
