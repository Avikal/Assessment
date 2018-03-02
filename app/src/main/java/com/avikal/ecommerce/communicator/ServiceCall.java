package com.avikal.ecommerce.communicator;


import com.avikal.ecommerce.app.ECommerceApp;
import com.avikal.ecommerce.model.ProductResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by avikaljain on 21/2/18.
 */

public class ServiceCall {

    @Inject
    DependService dependService;

    @Singleton
    public ServiceCall() {
        ECommerceApp.getInstance().getAppComponent().inject(this);
    }

    public void fetchProduct(final DisposableObserver productObserver) {
        dependService.getProduct()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ProductResponse>() {
                    @Override
                    public void onNext(@NonNull ProductResponse productResponse) {
                        productObserver.onNext(productResponse);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        productObserver.onError(e);
                    }

                    @Override
                    public void onComplete() {

                        productObserver.onComplete();
                    }
                });
    }
}
