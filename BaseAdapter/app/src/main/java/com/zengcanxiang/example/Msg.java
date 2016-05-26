package com.zengcanxiang.example;

/**
 * Created by zengcanxiang on 2016/1/29.
 */
public class Msg {

    private int type;
    private String msg;

    private String groupMsg;

    public Msg(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public Msg() {
    }

    public void setGroupMsg(String groupMsg) {
        this.groupMsg = groupMsg;
    }

    public String getGroupMsg() {
        return groupMsg;
    }

    public int getType() {
        return type;
    }

    public Msg setType(int type) {
        this.type = type;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Msg setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
