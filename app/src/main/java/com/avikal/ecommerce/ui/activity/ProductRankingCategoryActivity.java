package com.avikal.ecommerce.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.model.ProductResponse;
import com.avikal.ecommerce.ui.adapter.ProductFilterSubCategoryAdapter;
import com.avikal.ecommerce.utils.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by avikaljain on 23/2/18.
 */

public class ProductRankingCategoryActivity extends BaseActivity {

    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerCategory;

    ArrayList<ProductResponse.ProductsRankingBean> productList;
    String title;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_category);
        ButterKnife.bind(this);
        productList = getIntent().getParcelableArrayListExtra(Constants.PRODUCT_LIST_KEY);
        title = getIntent().getStringExtra(Constants.PRODUCT_TITLE);
        textViewTitle.setText(title);
        recyclerCategory.setLayoutManager(new LinearLayoutManager(mContext));
        ProductFilterSubCategoryAdapter productSubCategoryAdapter =
                new ProductFilterSubCategoryAdapter(ProductRankingCategoryActivity.this, productList) {
                    @Override
                    public void clickEvent(int position) {

                    }
                };

        recyclerCategory.setAdapter(productSubCategoryAdapter);
    }

    @OnClick(R.id.imageViewBack)
    public void backEvent() {
        finish();
    }

    /**
     * Start Application life cycle method
     */

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * End Application life cycle method
     */
}
