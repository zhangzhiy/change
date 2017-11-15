package com.onetarget.common.net;

public class DataCenter {

    private static DataCenter sDataCenter;
    private String account;

    private DataCenter() {

    }

    public static DataCenter getInstance() {
        if (sDataCenter == null) {
            sDataCenter = new DataCenter();
        }
        return sDataCenter;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
