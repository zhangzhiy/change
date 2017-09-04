package com.onetarget.onetargetdemo2.rxbus;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by onetarget on 2017/7/18.
 */
public class RxBus {
    private final Subject<Object,Object> bus;

    private RxBus() {
        bus=new SerializedSubject<>(PublishSubject.create());
    }

    private static RxBus instance;

    public static RxBus getInstance() {
        if (instance == null) {
            synchronized (RxBus.class) {
                if (instance == null) {
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }

    /**
     * @param obj 发送的内容
     */
    public void send(Object obj) {
        bus.onNext(obj);
    }

    public Observable<Object> toObservable() {
        return bus;
    }
}
