package com.onetarget.onetargetdemo2.ui.glide;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.mvp.MvpActivity;
import com.onetarget.onetargetdemo2.utils.GlideApp;
import com.onetarget.onetargetdemo2.utils.GlideLoader;
import com.onetarget.onetargetdemo2.utils.Logger;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;
import com.onetarget.onetargetdemo2.utils.ToastUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zzy on 2017/9/8.
 */

public class GlideActivity extends MvpActivity<GlideView, GlidePresenter> implements GlideView {
    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;
    @BindView(R.id.rl_button)
    RecyclerView rlButton;
    @BindView(R.id.iv_show)
    ImageView ivShow;
    private Context mContext;
    private ButtonAdapter adapter;

    @Override
    protected void init() {
        mContext = this;
        commonTitle.setTitleText("Glide加载图片");
        commonTitle.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getPresenter().getPicURl();

    }


    @Override
    public void getDataSuccess(final GlideModel data) {
        Logger.v(TAG, "getDataSuccess");
        rlButton.setLayoutManager(new LinearLayoutManager(mContext));
        rlButton.setHasFixedSize(true);
        List<GlideModel.DataBean> functions = data.getData();
        adapter = new ButtonAdapter(R.layout.item_button2, functions);
        rlButton.setAdapter(adapter);
        rlButton.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL));
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Logger.v(TAG, "setOnItemClickListener" + position);
                if (position == 0) {
                    //加载网络图片
                    GlideLoader.displayImage(mContext, data.getData().get(position).getPictureUrl(), ivShow);
                } else if (position == 1) {
                    //加载网络圆形图片
                    GlideLoader.displayCircleImage(mContext, data.getData().get(position).getPictureUrl(), ivShow);
                } else if (position == 2) {
                    //加载网络圆角图片
                    GlideLoader.displayRoundImage(mContext, data.getData().get(position).getPictureUrl(), ivShow, 30);
                } else if (position == 3) {
                    //加载失败显示默认图片
                    GlideLoader.displayImage(mContext, "", ivShow, R.drawable.home);
                } else if (position == 4) {
                    //加载文件图片
                    String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/com.yujingceping.pad/27.jpg";
                    File file = new File(path);
                    GlideLoader.displaySDImage(mContext, file, ivShow);
                } else if (position == 5) {
                    //加载固定大小图片
                    GlideLoader.displayImageFixSize(mContext, data.getData().get(position - 3).getPictureUrl(), ivShow, 150, 150);
                } else if (position == 6) {
                    //加载drawable图片
                    GlideLoader.displayDrawableImage(mContext, R.drawable.image_movie_header_12231501221682438, ivShow);
                } else if (position == 7) {
                    //加载图片的监听
                    GlideApp.with(mContext)
                            .load(data.getData().get(position).getPictureUrl())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    ToastUtil.showToast(mContext, "加载图片成功");
                                    //此处为资源准备好时调用的方法，返回true表示时间将会被拦截，不再继续传递下去；返回false表示事件会传递下去，一般为false
                                    return false;
                                }
                            })
                            .into(ivShow);
                }

            }
        });

    }

    @Override
    public void dealError(String message) {
        ToastUtil.showToast(this, message);
    }

    public class ButtonAdapter extends BaseQuickAdapter<GlideModel.DataBean, BaseViewHolder> {

        ButtonAdapter(@LayoutRes int layoutResId, @Nullable List data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(final BaseViewHolder helper, GlideModel.DataBean item) {
            helper.setText(R.id.tv_name, item.getTitle());
            //如果adapter内部设置点击事件，外面adapter.setOnItemClickListener将无效
//            helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                }
//            });
        }
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_glide;
    }

    @Override
    public GlidePresenter createPresenter() {
        return new GlidePresenter(this);
    }
}
