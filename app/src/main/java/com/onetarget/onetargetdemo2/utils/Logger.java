package com.onetarget.onetargetdemo2.utils;


import com.onetarget.onetargetdemo2.net.AppConstants;

public class Logger {

	public static final boolean DEBUG = AppConstants.DEBUG;

	public static final String TAG = "LOG";


	public static void v(String TAG, String msg) {
		if (DEBUG) {
			android.util.Log.v(TAG, msg);
		}
	}

	public static void v(String TAG, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.v(TAG, msg, tr);
		}
	}

	public static void d(String msg) {
		if (DEBUG) {
			d(TAG, msg);
		}
	}

	public static void d(String TAG, String msg) {
		if (DEBUG) {
			android.util.Log.d(TAG, msg);
		}
	}

	public static void d(String TAG, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.d(TAG, msg, tr);
		}
	}

	public static void i(String msg) {
		if (DEBUG) {
			i(TAG, msg);
		}
	}

	public static void i(String TAG, String msg) {
		if (DEBUG) {
			android.util.Log.i(TAG, msg);
		}
	}

	public static void i(String TAG, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.i(TAG, msg, tr);
		}
	}

	public static void w(String msg) {
		if (DEBUG) {
			w(TAG, msg);
		}
	}

	public static void w(String TAG, String msg) {
		if (DEBUG) {
			android.util.Log.w(TAG, msg);
		}
	}

	public static void w(String TAG, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.w(TAG, msg, tr);
		}
	}

	/**
	 * 
	 * @param msg
	 */
	public static void e(String msg) {
		if (DEBUG) {
			e(TAG, msg);
		}
	}

	/**
	 * 
	 * @param TAG
	 * @param msg
	 */
	public static void e(String TAG, String msg) {
		if (DEBUG) {
			android.util.Log.e(TAG, msg);
		}
	}

	/**
	 * 
	 * @param TAG
	 * @param msg
	 * @param tr
	 */
	public static void e(String TAG, String msg, Throwable tr) {
		if (DEBUG) {
			android.util.Log.e(TAG, msg, tr);
		}
	}

}
