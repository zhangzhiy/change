package com.onetarget.onetargetdemo2.ui.main.homefragment;

import com.onetarget.onetargetdemo2.mvp.BaseMode;

import java.util.List;

/**
 * Created by zzy on 2017/9/6.
 */

public class HomeModel extends BaseMode {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * title :
         * imgUrl : https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%BE%AE%E8%B7%9D%E6%91%84%E5%BD%B1&step_word=&hs=0&pn=8&spn=0&di=16311408210&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2750694183%2C4138033973&os=1613918231%2C3719675632&simid=0%2C0&adpicid=0&lpn=0&ln=1915&fr=&fmq=1480332039000_R_D&fm=&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=13&oriquery=&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fimage%2Fc0%3Dshijue1%2C0%2C0%2C294%2C40%2Fsign%3D4c3b2306ba51f819e5280b09b2dd2098%2F8718367adab44aed5b5a1bc9b91c8701a18bfb83.jpg&fromurl=ipprf_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bev2_z%26e3Bv54AzdH3Fv6jwptejAzdH3Fb88dlannl&gsm=0&rpstart=0&rpnum=0
         */

        private List<BannerDataBean> bannerData;
        /**
         * pictureUrl :
         * title : 标题1
         * desc : 描述
         */

        private List<ListDataBean> listData;

        public List<BannerDataBean> getBannerData() {
            return bannerData;
        }

        public void setBannerData(List<BannerDataBean> bannerData) {
            this.bannerData = bannerData;
        }

        public List<ListDataBean> getListData() {
            return listData;
        }

        public void setListData(List<ListDataBean> listData) {
            this.listData = listData;
        }

        public static class BannerDataBean {
            private String title;
            private String imgUrl;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

        public static class ListDataBean {
            private String pictureUrl;
            private String title;
            private String desc;

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
    }
}
