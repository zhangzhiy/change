package com.onetarget.onetargetdemo2.ui.login;

import android.text.TextUtils;

import com.onetarget.common.mvp.BaseMode;
import com.onetarget.common.net.AppConstants;


public class TokenDataMode extends BaseMode {

    public boolean isResponseValid(){
        return this.code == AppConstants.NET_OK_CODE;
    }

    public boolean isRootDataValid(){
        return this!=null;
    }

    public boolean isDataValid(){
        return data!=null;
    }

    private TokenMode data;

    public TokenMode getData() {
        return data;
    }

    public void setData(TokenMode data) {
        this.data = data;
    }

    public class TokenMode{
        private String appkey;
        private String appkeyValue;
        private String token;

        public boolean isAppKeyValid(){
            return !TextUtils.isEmpty(appkey);
        }

        public String getAppkey() {
            return appkey;
        }

        public void setAppkey(String appkey) {
            this.appkey = appkey;
        }

        public String getAppkeyValue() {
            return appkeyValue;
        }

        public void setAppkeyValue(String appkeyValue) {
            this.appkeyValue = appkeyValue;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        @Override
        public String toString() {
            return "TokenMode{" +
                    "appkey='" + appkey + '\'' +
                    ", appkeyValue='" + appkeyValue + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }
}
