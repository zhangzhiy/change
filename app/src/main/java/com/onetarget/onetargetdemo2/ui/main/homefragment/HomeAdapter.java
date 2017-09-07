package com.onetarget.onetargetdemo2.ui.main.homefragment;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.ui.main.homefragment.HomeModel;
import com.onetarget.onetargetdemo2.utils.GlideLoader;
import com.onetarget.onetargetdemo2.utils.Logger;

import java.util.List;

/**
 * Created by zzy on 2017/9/6.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeModel.DataBean.ListDataBean,BaseViewHolder>{
    private Context context;
    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List data, Context context) {
        super(layoutResId, data);
        this.context=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeModel.DataBean.ListDataBean item) {
        helper.setText(R.id.tv_item_title,item.getTitle())
                .setText(R.id.tv_item_desc,item.getDesc());
        Logger.v(TAG,"sss"+item.getPictureUrl());
        GlideLoader.displayRoundImage(context,item.getPictureUrl(),(ImageView)helper.getView(R.id.item_image));
    }

}
