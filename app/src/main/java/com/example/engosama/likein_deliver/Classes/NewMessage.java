package com.example.engosama.likein_deliver.Classes;


public class NewMessage {
    private String msg_time_str, msg_str;
    private int type;

    public NewMessage(String msg_time_str, String msg_str, int type) {
        this.msg_time_str = msg_time_str;
        this.msg_str = msg_str;
        this.type = type;
    }

    public NewMessage() {
    }

    public String getMsg_time_str() {
        return msg_time_str;
    }

    public void setMsg_time_str(String msg_time_str) {
        this.msg_time_str = msg_time_str;
    }

    public String getMsg_str() {
        return msg_str;
    }

    public void setMsg_str(String msg_str) {
        this.msg_str = msg_str;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
