package com.onetarget.onetargetdemo2.ui.glide;

import java.util.List;

/**
 * Created by zzy on 2017/9/8.
 */

public class GlideModel {

    /**
     * code : 200
     * message : ee获取秘钥成功
     * data : [{"pictureUrl":"http://192.168.10.77:8080/zzy/44.jpg","title":"Glide加载图片","desc":"描述"},{"pictureUrl":"http://192.168.10.77:8080/zzy/27.jpg","title":"标题2","desc":"描述"},{"pictureUrl":"http://192.168.10.77:8080/zzy/66.jpg","title":"标题3","desc":"描述"},{"pictureUrl":"http://192.168.10.77:8080/zzy/324.jpg","title":"标题4","desc":"描述"},{"pictureUrl":"http://192.168.10.77:8080/zzy/344.jpg","title":"标题5","desc":"描述"}]
     */

    private int code;
    private String message;
    /**
     * pictureUrl : http://192.168.10.77:8080/zzy/44.jpg
     * title : Glide加载图片
     * desc : 描述
     */

    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
