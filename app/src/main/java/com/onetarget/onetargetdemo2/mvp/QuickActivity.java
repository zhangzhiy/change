package com.onetarget.onetargetdemo2.mvp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.onetarget.onetargetdemo2.net.NetworkReceiver;

/**
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class QuickActivity extends AppCompatActivity implements NetworkReceiver.NetWorkListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetworkReceiver.registerListener(this);
    }

    @Override
    protected void onDestroy() {
        NetworkReceiver.unRegisterListener(this);
        super.onDestroy();
    }

    @Override
    public void onNetStatusChanged(boolean isAvailable) {

    }
}
