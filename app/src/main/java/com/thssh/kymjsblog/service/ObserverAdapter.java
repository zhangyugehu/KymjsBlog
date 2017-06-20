package com.thssh.kymjsblog.service;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/20
 */

public abstract class ObserverAdapter<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull T t) {
        next(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e.toString());
        e.printStackTrace();
    }

    @Override
    public void onComplete() {
        onSuccess();
    }

    protected void onFailure(String error) {

    }

    protected abstract void onSuccess();

    protected abstract void next(T t);
}
