package com.onetarget.onetargetdemo2.ui.permission;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.onetarget.onetargetdemo2.mvp.rx.scheduler.MvpRxPresenter;
import com.onetarget.onetargetdemo2.utils.FileUtils;
import com.onetarget.onetargetdemo2.utils.ToastUtil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.util.List;

/**
 * Created by zzy on 2017/9/1.
 */

class PermissionPersenter extends MvpRxPresenter<IPermissionView, PermissionDataMode> {
    private Activity mContext;

    public PermissionPersenter(Context context) {
        mContext = (Activity) context;
    }


    public void saveData() {
        AndPermission.with(mContext)
                .requestCode(100)
                .permission(Permission.STORAGE)
                .callback(permissionLister)
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        AndPermission.rationaleDialog(mContext, rationale).show();
                    }
                })
                .start();
    }

    @Override
    protected void onNext(PermissionDataMode data, int requestType) {

    }

    PermissionListener permissionLister = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

            if (AndPermission.hasPermission(mContext, Permission.STORAGE)) {
                saveToSDCard();
            } else {
                // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                AndPermission.defaultSettingDialog(mContext, requestCode).show();

                // 建议：自定义这个Dialog，提示具体需要开启什么权限，自定义Dialog具体实现上面有示例代码。
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            if (AndPermission.hasPermission(mContext, Permission.STORAGE)) {
                saveToSDCard();
            } else {
                // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
                if (AndPermission.hasAlwaysDeniedPermission(mContext, deniedPermissions)) {
                    // 第一种：用默认的提示语。
                    AndPermission.defaultSettingDialog((Activity) mContext, 100).show();

                    // 第二种：用自定义的提示语。
//             AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
//                     .setTitle("权限申请失败")
//                     .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
//                     .setPositiveButton("好，去设置")
//                     .show();

//            第三种：自定义dialog样式。
//            SettingService settingService = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            你的dialog点击了确定调用：
//            settingService.execute();
//            你的dialog点击了取消调用：
//            settingService.cancel();
                }
            }

        }
    };

    private void saveToSDCard() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "aonetarget";
        FileUtils.createOrExistsDir(path);
        File file = new File(path, "a.txt");
        FileUtils.writeFileFromString(file, "hahahahh", false);
        ToastUtil.showToast(mContext, "保存成功");
    }

}
