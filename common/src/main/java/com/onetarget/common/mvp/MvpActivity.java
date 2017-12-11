/*
 * Copyright 2015 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.onetarget.common.mvp;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import butterknife.ButterKnife;
import com.onetarget.common.mvp.delegate.ActivityMvpDelegate;
import com.onetarget.common.mvp.delegate.ActivityMvpDelegateImpl;
import com.onetarget.common.mvp.delegate.MvpDelegateCallback;
import com.onetarget.common.rxbus.RxBus;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * A {@link QuickActivity} that uses an {@link IPresenter} to implement a Model-View-Presenter
 * Architecture.
 *
 * @author Hannes Dorfmann
 * @since 1.0.0
 */
public abstract class MvpActivity<V extends IView, P extends IPresenter<V>>extends QuickActivity
        implements MvpDelegateCallback<V, P>, IView {

  protected ActivityMvpDelegate mvpDelegate;
  protected P presenter;
  protected Subscription eventSubscription;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getMvpDelegate().onCreate(savedInstanceState);
    setContentView(getContentViewId());
    //hideStatusBar();
    ButterKnife.bind(this);
    initRxBus();
    init();
  }

  private   void hideStatusBar(){
    if (Build.VERSION.SDK_INT >= 21) {
      View decorView = getWindow().getDecorView();
      int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
              | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
              | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option);
      getWindow().setNavigationBarColor(Color.TRANSPARENT);
      getWindow().setStatusBarColor(Color.TRANSPARENT);
    }
    ActionBar actionBar = getSupportActionBar();
    actionBar.hide();
  }

  private void initRxBus() {
    eventSubscription = RxBus.getInstance()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Action1<Object>() {
              @Override
              public void call(Object o) {
                doOnNext(o);
              }
            });
  }

  protected  void doOnNext(Object o){}

  protected abstract void init();

  protected abstract int getContentViewId();


  @Override
  protected void onDestroy() {
    super.onDestroy();
    unSubscribe();
    ButterKnife.bind(this).unbind();
    getMvpDelegate().onDestroy();
  }

  private void unSubscribe() {
    if (eventSubscription != null && !eventSubscription.isUnsubscribed()) {
      eventSubscription.unsubscribe();
    }
  }

  @Override
  protected void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    getMvpDelegate().onSaveInstanceState(outState);
  }

  @Override
  protected void onPause() {
    super.onPause();
    getMvpDelegate().onPause();
  }

  @Override
  protected void onResume() {
    super.onResume();
    getMvpDelegate().onResume();
  }

  @Override
  protected void onStart() {
    super.onStart();
    getMvpDelegate().onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    getMvpDelegate().onStop();
  }

  @Override
  protected void onRestart() {
    super.onRestart();
    getMvpDelegate().onRestart();
  }

  @Override
  public void onContentChanged() {
    super.onContentChanged();
    getMvpDelegate().onContentChanged();
  }

  @Override
  protected void onPostCreate(Bundle savedInstanceState) {
    super.onPostCreate(savedInstanceState);
    getMvpDelegate().onPostCreate(savedInstanceState);
  }

  /**
   * Instantiate a presenter instance
   *
   * @return The {@link IPresenter} for this view
   */
  public abstract P createPresenter();

  /**
   * Get the mvp delegate. This is internally used for creating presenter, attaching and detaching
   * view from presenter.
   *
   * <p><b>Please note that only one instance of mvp delegate should be used per Activity
   * instance</b>.
   * </p>
   *
   * <p>
   * Only override this method if you really know what you are doing.
   * </p>
   *
   * @return {@link ActivityMvpDelegateImpl}
   */
  protected ActivityMvpDelegate<V, P> getMvpDelegate() {
    if (mvpDelegate == null) {
      mvpDelegate = new ActivityMvpDelegateImpl(this,this);
    }

    return mvpDelegate;
  }

  @Override
  public P getPresenter() {
    return presenter;
  }

  @Override
  public void setPresenter(P presenter) {
    this.presenter = presenter;
  }

  @Override
  public V getMvpView() {
    return (V) this;
  }

  @Override
  public boolean isRetainingInstance() {
    return false;
  }
}
