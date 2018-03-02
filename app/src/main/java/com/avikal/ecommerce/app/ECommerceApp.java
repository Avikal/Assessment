package com.avikal.ecommerce.app;

import android.app.Application;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.avikal.ecommerce.dagger.AppComponent;
import com.avikal.ecommerce.dagger.AppModule;
import com.avikal.ecommerce.dagger.DaggerAppComponent;

/**
 * Created by avikaljain on 21/2/18.
 */

public class ECommerceApp extends Application {

    public static ECommerceApp instance = null;

    public static ECommerceApp getInstance() {
        return instance;
    }

    public Context getAppContext() {
        return instance.getApplicationContext();
    }

    private AppComponent appComponent;

    public AppComponent getAppComponent() {
        return appComponent;
    }

    protected AppComponent initDagger(ECommerceApp application) {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(application))
                .build();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

    }
    /**
     * This method makes rotating loader animation on loading icon.
     */
    public void showLoadingAnimation(ImageView loadingImage) {
        RotateAnimation rotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setDuration(1000);
        loadingImage.setAnimation(rotateAnimation);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            instance = this;
            appComponent = initDagger(this);
            // Branch logging for debugging
//            Branch.enableLogging();

            // Branch object initialization
//            Branch.getAutoInstance(this);
            //   Stetho.initializeWithDefaults(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
