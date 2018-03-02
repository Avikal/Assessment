package com.avikal.ecommerce.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.communicator.CheckNetworkState;
import com.avikal.ecommerce.communicator.ServiceCall;
import com.avikal.ecommerce.model.ProductResponse;
import com.avikal.ecommerce.ui.adapter.ProductCategoryAdapter;
import com.avikal.ecommerce.ui.adapter.ProductFilterCategoryAdapter;
import com.avikal.ecommerce.utils.Constants;

import com.avikal.ecommerce.utils.ProjectUtils;
import com.avikal.ecommerce.utils.SharedPrefsUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import tr.xip.errorview.ErrorView;

/**
 * Created by avikaljain on 21/2/18.
 */

public class ProductCategoryActivity extends BaseActivity {

    @BindView(R.id.recyclerCategory)
    RecyclerView recyclerCategory;

    @BindView(R.id.errorView)
    ErrorView errorView;

    @BindView(R.id.imageViewFilter)
    ImageView imageViewFilter;

    @BindView(R.id.imageViewBack)
    ImageView imageViewBack;

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;

    private boolean isFilterApply;
    private ProductCategoryAdapter productCategoryAdapter;
    private ProductFilterCategoryAdapter productFilterCategoryAdapter;
    public ProductResponse productResponse;
    public ServiceCall serviceCall;
    List<ProductResponse.ProductsBean> productList = new ArrayList<>();

    /**
     * Fetch API Response and show on UI
     *
     */
    public DisposableObserver productCategory = new DisposableObserver() {
        @Override
        public void onNext(@NonNull Object o) {
            if (o != null && o instanceof ProductResponse) {
                productResponse = (ProductResponse) o;
                SharedPrefsUtils.saveProductDetailsSharedPref(ProductCategoryActivity.this, productResponse);
                errorView.setVisibility(View.GONE);
                ProjectUtils.hideProgressDialog(ProductCategoryActivity.this);
                List<ProductResponse.CategoriesBean> newData = productResponse.getCategories();
                for (ProductResponse.CategoriesBean data : newData) {
//                    data.setPageCount(movieResponse.getPage());
//                    dbHelper.insertTopProductResponse(data);

                }

                updateUI(productResponse, isFilterApply);
            } else {
                ProjectUtils.hideProgressDialog(ProductCategoryActivity.this);
                errorView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            e.printStackTrace();
            errorView.setVisibility(View.VISIBLE);
            ProjectUtils.hideProgressDialog(ProductCategoryActivity.this);
        }

        @Override
        public void onComplete() {

        }
    };

    /**
     * After Succesful Response of API Update the UI
     * @param productResponse
     * @param isFilter
     */
    private void updateUI(final ProductResponse productResponse, boolean isFilter) {
        recyclerCategory.setLayoutManager(new GridLayoutManager(mContext, 2));
        // Condition for Filter or regular UI
        if (isFilter) {
            final List<ProductResponse.RankingsBean> rankingsBeans = productResponse.getRankings();
            productFilterCategoryAdapter = new ProductFilterCategoryAdapter(ProductCategoryActivity.this, rankingsBeans) {
                @Override
                public void clickEvent(int position) {
                    Intent intent = new Intent(ProductCategoryActivity.this, ProductRankingCategoryActivity.class);
                    List<ProductResponse.ProductsRankingBean> productList = rankingsBeans.get(position).getProducts();
                    intent.putParcelableArrayListExtra(Constants.PRODUCT_LIST_KEY, (ArrayList<? extends Parcelable>) productList);
                    intent.putExtra(Constants.PRODUCT_TITLE, productResponse.getRankings().get(position).getRanking());
                    startActivity(intent);
                }
            };
            recyclerCategory.setAdapter(productFilterCategoryAdapter);
        } else {
            final List<ProductResponse.CategoriesBean> categoriesBean = productResponse.getCategories();
            productCategoryAdapter = new ProductCategoryAdapter(ProductCategoryActivity.this, categoriesBean) {
                @Override
                public void clickEvent(int position) {
                    Intent intent = new Intent(ProductCategoryActivity.this, ProductSubCategoryActivity.class);
                    List<ProductResponse.ProductsBean> productList = categoriesBean.get(position).getProducts();
                    intent.putParcelableArrayListExtra(Constants.PRODUCT_LIST_KEY, (ArrayList<? extends Parcelable>) productList);
                    intent.putExtra(Constants.PRODUCT_TITLE, productResponse.getCategories().get(position).getName());
                    startActivity(intent);
                }
            };
            recyclerCategory.setAdapter(productCategoryAdapter);
        }

    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_p_category);
        ButterKnife.bind(this);
        setUpUi();
        serviceCall = new ServiceCall();
        if (CheckNetworkState.isOnline(ProductCategoryActivity.this)) {
            errorView.setVisibility(View.GONE);
            ProjectUtils.showProgressDialog(this);
            serviceCall.fetchProduct(productCategory);
        } else {
            productResponse = SharedPrefsUtils.getProductResponseSharedPref(this);
            if (productResponse != null) {
                updateUI(productResponse, isFilterApply);
            } else {
                errorView.setVisibility(View.VISIBLE);
            }
            showToast(getString(R.string.network_validation_msg));
        }


    }

    /**
     * Set up Basic Ui when Screen launch
     */
    private void setUpUi() {
        imageViewFilter.setVisibility(View.VISIBLE);
        imageViewBack.setImageResource(R.mipmap.app_icon);
        textViewTitle.setText(getString(R.string.app_name));
    }

    /**
     * Apply or Remove filter Button event
     */
    @OnClick(R.id.imageViewFilter)
    public void filterEvent() {
        if (isFilterApply) {
            isFilterApply = false;
            updateUI(productResponse, isFilterApply);
            imageViewFilter.setImageResource(R.mipmap.apply_filter);
        } else {
            isFilterApply = true;
            updateUI(productResponse, isFilterApply);
            imageViewFilter.setImageResource(R.mipmap.clear_filter);
        }
    }


    @Override
    public void onBackPressed() {
        exitDialog();
    }

    /**
     * Alert popup to ask user he/she want to exit fot app
     */
    private void exitDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle(mContext.getString(R.string.app_name))
                .setMessage(mContext.getString(R.string.exit_dialog_msg))
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                       finishAffinity();
                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(R.mipmap.app_icon)
                .show();
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
