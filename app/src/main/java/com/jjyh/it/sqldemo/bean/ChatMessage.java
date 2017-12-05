package com.jjyh.it.sqldemo.bean;

/**
 * Created by zhangjunjie on 2017/8/9.
 */

public class ChatMessage {
    public static final int SEND = 1;
    public static final int RECEIVE = 2;

    private int direct;
    public void setDirect(int v){
        this.direct = v;
    }
    public int getDirect() {
        return this.direct;
    }

    private Object body;
    public void setBody(Object v){
        this.body = v;
    }
    public Object getBody() {
        return this.body;
    }

    private int type;//文本或者卡片布局
    public void setType(int v){
        this.type = v;
    }
    public int getType() {
        return this.type;
    }

    public ChatMessage(int d, Object b, int t) {
        this.direct = d;
        this.body = b;
        this.type = t;
    }

}
