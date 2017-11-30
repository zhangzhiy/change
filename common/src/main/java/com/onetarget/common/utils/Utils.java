package com.onetarget.common.utils;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.File;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 *
 */
public  class Utils {
	private Utils(){
	}
	private static long lastClickTime;
	public static boolean isFastDoubleClick(int time) {
		long curTime = System.currentTimeMillis();
		long interval = curTime - lastClickTime;
		if (0 < interval && interval < time) {
			return true;
		}
		lastClickTime = curTime;
		return false;
	}
	/***
	 * 获取当前网络的状态
	 *
	 * @param context
	 * @return true 代表网络连接状体
	 */
	public static boolean isNetConnect(Context context) {
		if (context == null) {
			return false;
		}
		// 获取手机所以连接管理对象（包括wi-fi，net等连接的管理）
		ConnectivityManager conn = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (conn != null) {
			// 网络管理连接对象
			NetworkInfo info = conn.getActiveNetworkInfo();

			if (info != null && info.isConnected()) {
				// 判断当前网络是否连接
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 判断SD卡是否可用
	 */
	public static boolean isSDCardEnable() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}
	/**
	 * get SDPath, if no sdcard, return null
	 *
	 * @return
	 */
	public static String getSDPath() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().getAbsolutePath();
		} else {
			return null;
		}
	}
	public static String getBasePath(Context context) {
		if (null == getSDPath()) {
			return context.getCacheDir().getAbsolutePath();
		}

		File file = new File(getSDPath() + File.separator + context.getPackageName());
		if (!file.exists()) {
			file.mkdir();
		}

		return file.getAbsolutePath();
	}



	public static void hideKeybord(final View view,final Context context){
		//触摸屏幕空白区域,失去焦点并隐藏软键盘
		view.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				view.setFocusable(true);
				view.setFocusableInTouchMode(true);
				view.requestFocus();
				InputMethodManager mInputMethodManager = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
				mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
				return false;
			}
		});
	}
}
