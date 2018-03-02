package com.avikal.ecommerce.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.app.ECommerceApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by avikaljain on 21/2/18.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.loadingImage)
    ImageView loadingImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ((ECommerceApp) getApplication()).showLoadingAnimation(loadingImage);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingImage.clearAnimation();
                startActivity(new Intent(SplashActivity.this, ProductCategoryActivity.class));
                finish();
            }
        }, 2000);
    }
}
