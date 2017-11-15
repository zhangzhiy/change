package com.onetarget.onetargetdemo2.ui.login;


import com.onetarget.common.mvp.BaseMode;

/**
 * Created by onetarget on 2017/7/11.
 */
public class LoginDataMode extends BaseMode {

    private LoginMode data;

    public LoginMode getData() {
        return data;
    }

    public void setData(LoginMode data) {
        this.data = data;
    }

    public class LoginMode{
        private int id;
        private String loginName;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "LoginMode{" +
                    "id=" + id +
                    ", loginName='" + loginName + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
