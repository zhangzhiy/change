package com.onetarget.onetargetdemo2.ui.filedownload;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.onetarget.common.mvp.MvpActivity;
import com.onetarget.common.mvp.MvpPresenter;
import com.onetarget.onetargetdemo2.R;
import com.onetarget.onetargetdemo2.ui.constraintlayout.ConstraintLayoutPresenter;
import com.onetarget.onetargetdemo2.utils.NormalTitleBar;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zzy on 2017/11/27.
 */

public class FileDownloadActivity extends MvpActivity {

    @BindView(R.id.common_title)
    NormalTitleBar commonTitle;
    @BindView(R.id.button7)
    Button button7;
    @BindView(R.id.button8)
    Button button8;
    @BindView(R.id.button9)
    Button button9;
    @BindView(R.id.progressBar_1)
    ProgressBar progressBar1;
    @BindView(R.id.filename_tv_1)
    TextView filenameTv1;
    @BindView(R.id.speed_tv_1)
    TextView speedTv1;
    private int downloadId1;
    private String url;
    private String path;
    private Context mContext;
    @Override
    protected void init() {
        mContext=this;

        commonTitle.setTitleText("LeakCanary");
        commonTitle.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FileDownloader.setup(this);
      //  url = "http://47.93.163.199/group1/M00/00/01/L12jx1oXxXKANSD4AiFVarE2QHI737.zip";
        url = "http://47.93.163.199/group1/M00/00/01/L12jx1oX2ZGAQ4xpAYs3BwCbh3s806.zip";
        String filePath=Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "com.yujing"+File.separator;
        File file=new File(filePath);
        if(!file.exists()){
            file.mkdirs();
        }
        path =  filePath+"webapp.zip";

    }


    PermissionListener permissionLister = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {

            if (AndPermission.hasPermission(mContext, Permission.STORAGE)) {
                downloadId1 = createDownloadTask(1).start();
            } else {
                // 使用AndPermission提供的默认设置dialog，用户点击确定后会打开App的设置页面让用户授权。
                AndPermission.defaultSettingDialog((Activity) mContext, requestCode).show();

                // 建议：自定义这个Dialog，提示具体需要开启什么权限，自定义Dialog具体实现上面有示例代码。
            }
        }

        @Override
        public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
            if (AndPermission.hasPermission(mContext, Permission.STORAGE)) {
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
    @Override
    protected int getContentViewId() {
        return R.layout.activity_file_download;
    }

    @Override
    public MvpPresenter createPresenter() {
        return new FileDownloadPresenter(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button7, R.id.button8, R.id.button9})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button7:
                AndPermission.with(this)
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

                break;
            case R.id.button8:
                FileDownloader.getImpl().pause(downloadId1);
                break;
            case R.id.button9:
                new File(path).delete();
                break;
        }
    }

    private BaseDownloadTask createDownloadTask(final int position) {
        final ViewHolder tag;
        boolean isDir = false;

        switch (position) {
            case 1:

                tag = new ViewHolder(new WeakReference<>(this), progressBar1, null, speedTv1, 1);
                tag.setFilenameTv(filenameTv1);
                break;
                default:
                    tag = new ViewHolder(new WeakReference<>(this), progressBar1, null, speedTv1, 1);
                    tag.setFilenameTv(filenameTv1);
                    break;

        }

        return FileDownloader.getImpl().create(url)
                .setPath(path, isDir)
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setTag(tag)
                .setListener(new FileDownloadSampleListener() {

                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.pending(task, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updatePending(task);
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.progress(task, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updateProgress(soFarBytes, totalBytes,
                                task.getSpeed());
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        super.error(task, e);
                        ((ViewHolder) task.getTag()).updateError(e, task.getSpeed());
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        super.connected(task, etag, isContinue, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updateConnected(etag, task.getFilename());
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        super.paused(task, soFarBytes, totalBytes);
                        ((ViewHolder) task.getTag()).updatePaused(task.getSpeed());
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        super.completed(task);
                        ((ViewHolder) task.getTag()).updateCompleted(task);
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        super.warn(task);
                        ((ViewHolder) task.getTag()).updateWarn();
                    }
                });
    }

    private static class ViewHolder {
        private ProgressBar pb;
        private TextView detailTv;
        private TextView speedTv;
        private int position;
        private TextView filenameTv;

        private WeakReference<FileDownloadActivity> weakReferenceContext;

        public ViewHolder(WeakReference<FileDownloadActivity> weakReferenceContext,
                          final ProgressBar pb, final TextView detailTv, final TextView speedTv,
                          final int position) {
            this.weakReferenceContext = weakReferenceContext;
            this.pb = pb;
            this.detailTv = detailTv;
            this.position = position;
            this.speedTv = speedTv;
        }

        public void setFilenameTv(TextView filenameTv) {
            this.filenameTv = filenameTv;
        }

        private void updateSpeed(int speed) {
            speedTv.setText(String.format("%dKB/s", speed));
        }

        public void updateProgress(final int sofar, final int total, final int speed) {
            if (total == -1) {
                // chunked transfer encoding data
                pb.setIndeterminate(true);
            } else {
                pb.setMax(total);
                pb.setProgress((int)(sofar*0.7));
            }

            updateSpeed(speed);

            if (detailTv != null) {
                detailTv.setText(String.format("sofar: %d total: %d", sofar, total));
            }
        }

        public void updatePending(BaseDownloadTask task) {
            if (filenameTv != null) {
                filenameTv.setText(task.getFilename());
            }
        }

        public void updatePaused(final int speed) {
            toast(String.format("paused %d", position));
            updateSpeed(speed);
            pb.setIndeterminate(false);
        }

        public void updateConnected(String etag, String filename) {
            if (filenameTv != null) {
                filenameTv.setText(filename);
            }
        }

        public void updateWarn() {
            toast(String.format("warn %d", position));
            pb.setIndeterminate(false);
        }

        public void updateError(final Throwable ex, final int speed) {
            toast(String.format("error %d %s", position, ex));
            updateSpeed(speed);
            pb.setIndeterminate(false);
            ex.printStackTrace();
        }

        public void updateCompleted(final BaseDownloadTask task) {

            toast(String.format("completed %d %s", position, task.getTargetFilePath()));

            if (detailTv != null) {
                detailTv.setText(String.format("sofar: %d total: %d",
                        task.getSmallFileSoFarBytes(), task.getSmallFileTotalBytes()));
            }

            updateSpeed(task.getSpeed());
            pb.setIndeterminate(false);
            pb.setMax(task.getSmallFileTotalBytes());
            pb.setProgress(task.getSmallFileSoFarBytes());
        }

        private void toast(final String msg) {
            if (this.weakReferenceContext != null && this.weakReferenceContext.get() != null) {
                Snackbar.make(weakReferenceContext.get().button7, msg, Snackbar.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        FileDownloader.getImpl().pause(downloadId1);
        super.onDestroy();
    }
}
