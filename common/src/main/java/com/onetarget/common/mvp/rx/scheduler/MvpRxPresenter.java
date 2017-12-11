package com.onetarget.common.mvp.rx.scheduler;


import android.content.Context;

import com.onetarget.common.mvp.BaseMode;
import com.onetarget.common.mvp.IModel;
import com.onetarget.common.mvp.IView;
import com.onetarget.common.mvp.MvpBasePresenter;
import com.onetarget.common.mvp.rx.BaseSubscriber;
import rx.Observable;
import rx.Subscriber;

/**
 * A presenter for RxJava, that assumes that only one Observable is subscribed by this presenter.
 * The idea is, that you make your (chain of) Observable and pass it to {@link
 * #subscribe(Observable, Context)}. The presenter internally subscribes himself as Subscriber to the
 * observable
 * (which executes the observable). Before subscribing the presenter calls
 * {@link #applyScheduler(Observable)} to apply the <code>subscribeOn()</code> and
 * <code>observeOn()</code>
 *
 * @author Fahim Karim
 * @author Hannes Dorfmann
 * @since 1.1.0
 */
public abstract class MvpRxPresenter<V extends IView, M > extends MvpBasePresenter<V> {

  protected Subscriber<M> subscriber;
  /**
   * Unsubscribes the subscriber and set it to null
   */
  protected void unSubscribe() {
    if (subscriber != null && !subscriber.isUnsubscribed()) {
      subscriber.unsubscribe();
    }

    subscriber = null;
  }

  protected void subscribe(Observable<M> observable, Context context) {
     subscribe(observable, context, 0);
  }
  /**
   * Subscribes the presenter himself as subscriber on the observable
   *
   * @param observable The observable to subscribe
   */
  protected void subscribe(Observable<M> observable, Context context, final int requestType) {
    unSubscribe();

    subscriber = new BaseSubscriber<M>(context) {
      @Override
      public void onNext(M m) {
        MvpRxPresenter.this.onNext(m,requestType);
      }
    };

    observable = applyScheduler(observable);
    observable.subscribe(subscriber);
  }

  /**
   * Called in {@link #subscribe(Observable, Context)} to set  <code>subscribeOn()</code> and
   * <code>observeOn()</code>. As default it uses {@link AndroidSchedulerTransformer}. Override
   * this
   * method if you want to provide your own scheduling implementation.
   */
  protected Observable<M> applyScheduler(Observable<M> observable) {
    return observable.compose(new AndroidSchedulerTransformer<M>());
  }

  protected abstract void onNext(M data,int requestType);


  @Override
  public void detachView(boolean retainInstance) {
    super.detachView(retainInstance);
    if (!retainInstance) {
      unSubscribe();
    }
  }
}