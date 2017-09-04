package com.onetarget.onetargetdemo2.mvp;


public class BaseMode {

    protected int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return message;
    }

    public void setMsg(String msg) {
        this.message = msg;
    }

    @Override
    public String toString() {
        return "BaseMode{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
