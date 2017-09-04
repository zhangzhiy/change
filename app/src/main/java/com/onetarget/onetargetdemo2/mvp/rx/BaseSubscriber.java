package com.onetarget.onetargetdemo2.mvp.rx;

import android.content.Context;

import com.google.gson.JsonSyntaxException;

import java.io.EOFException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.net.ProgressCancelListener;
import com.onetarget.onetargetdemo2.net.ProgressDialogHandler;
import com.onetarget.onetargetdemo2.utils.Logger;
import com.onetarget.onetargetdemo2.utils.ToastUtil;
import com.onetarget.onetargetdemo2.utils.Utils;
import rx.Subscriber;

public abstract class BaseSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private static final String TAG=BaseSubscriber.class.getSimpleName();
    private Context context;
    private ProgressDialogHandler mProgressDialogHandler;

    public BaseSubscriber(Context context) {
        this.context = context;
        mProgressDialogHandler = new ProgressDialogHandler(context, this, true);
    }
    private void showProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog(){
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }
    @Override
    public void onError(Throwable e) {
        dismissProgressDialog();
        Logger.e(TAG, "---onError:" + e.toString());
        doErrorMessage(e);
    }

    private void doErrorMessage(Throwable e) {
        String warningText;
        if(e instanceof EOFException){
            //response 为空的情况
            warningText=context.getResources().getString(R.string.server_has_error);
        }else if(e instanceof JsonSyntaxException){
            warningText=context.getResources().getString(R.string.error_data_format);
        }else if(e instanceof SocketTimeoutException){
            warningText=context.getResources().getString(R.string.connect_time_out);
        }else if(e instanceof ConnectException){
            if(Utils.isNetConnect(context)){
                warningText=context.getResources().getString(R.string.server_has_error);
            }else{
                warningText=context.getResources().getString(R.string.net_not_connect);
            }
        }else{
            // 一般是程序处理错误
            warningText=context.getResources().getString(R.string.error_data_format);
        }

        ToastUtil.showToast(context, warningText);
    }


    @Override
    public void onStart() {
        super.onStart();
        showProgressDialog();
        // if  NetworkAvailable no !   must to call onCompleted
        if (!Utils.isNetConnect(context)) {
            ToastUtil.showToast(context, R.string.net_not_connect);
            onCompleted();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }
}
