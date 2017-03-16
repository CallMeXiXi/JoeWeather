package com.example.joe_pc.joeweather.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Joe_PC on 2017/3/16.
 */

public class BaseRxJava {

    private CompositeSubscription mSubscription;

    protected void addSubscription(Subscription subscriptions) {
        if (mSubscription == null) {
            mSubscription = new CompositeSubscription();
        }
        mSubscription.add(subscriptions);
    }

    protected void baseUnSubscription() {
        if (mSubscription != null && mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
