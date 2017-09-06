package com.onetarget.onetargetdemo2.ui.main.homefragment;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.ui.main.homefragment.HomeModel;

import java.util.List;

/**
 * Created by zzy on 2017/9/6.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeModel.DataBean.ListDataBean,BaseViewHolder>{
    public HomeAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeModel.DataBean.ListDataBean item) {
        helper.setText(R.id.tv_item_title,item.getTitle())
                .setText(R.id.tv_item_desc,item.getDesc());
        ((ImageView)helper.getView(R.id.item_image)).setImageResource(R.drawable.function);
    }

}
