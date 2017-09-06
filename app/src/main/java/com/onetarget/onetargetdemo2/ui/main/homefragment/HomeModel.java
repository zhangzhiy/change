package com.onetarget.onetargetdemo2.ui.main.homefragment;

import com.onetarget.onetargetdemo2.mvp.BaseMode;

/**
 * Created by zzy on 2017/9/6.
 */

public class HomeModel extends BaseMode {
    private String pictureUrl;
    private String title;
    private String desc;

    public HomeModel(String pictureUrl, String title, String desc) {
        this.pictureUrl = pictureUrl;
        this.title = title;
        this.desc = desc;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
