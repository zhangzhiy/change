package com.onetarget.onetargetdemo2.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.onetarget.onetargetdemo2.utils.Logger;

import java.util.ArrayList;
import java.util.List;


public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // 这个监听网络连接的设置，包括wifi和移动数据的打开和关闭。.
        // 最好用的还是这个监听。wifi如果打开，关闭，以及连接上可用的连接都会接到监听。见log
        // 这个广播的最大弊端是比上边两个广播的反应要慢，如果只是要监听wifi，我觉得还是用上边两个配合比较合适
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo gprs = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            Logger.i("test", "网络状态改变:" + wifi.isConnected() + " 3g:" + gprs.isConnected());
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                boolean netAvailable = NetworkInfo.State.CONNECTED == info.getState();
                for (NetWorkListener l : sListens) {
                    l.onNetStatusChanged(netAvailable);
                }
            }
        }
    }

    private static List<NetWorkListener> sListens = new ArrayList<>();


    public static void registerListener(NetWorkListener l) {
        sListens.add(l);
    }

    public static void unRegisterListener(NetWorkListener l) {
        sListens.remove(l);
    }


    public interface NetWorkListener {
        void onNetStatusChanged(boolean isAvailable);
    }
}
