package com.onetarget.onetargetdemo2.ui.main.homefragment;

import com.onetarget.onetargetdemo2.mvp.BaseMode;

/**
 * Created by zzy on 2017/9/6.
 */

public class BannerModel extends BaseMode {
    private String title;
    private int imgUrl;

    public BannerModel(String title, int imgUrl) {
        this.title = title;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(int imgUrl) {
        this.imgUrl = imgUrl;
    }
}
