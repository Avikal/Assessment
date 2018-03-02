package com.avikal.ecommerce.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.model.ProductResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by avikaljain on 23/2/18.
 */

public abstract class ProductFilterSubCategoryAdapter extends RecyclerView.Adapter<ProductFilterSubCategoryAdapter.ProductSubCategoryHolder> {

    private Context context;
    private List<ProductResponse.ProductsRankingBean> productsBeans;
    private LayoutInflater layoutInflater;

    private ProductResponse.VariantsBean variantsBean;

    public ProductFilterSubCategoryAdapter(Context context, List<ProductResponse.ProductsRankingBean> productsBeans) {
        this.context = context;
        this.productsBeans = productsBeans;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductSubCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = layoutInflater.inflate(R.layout.item_sub_category, parent, false);

        return new ProductSubCategoryHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ProductSubCategoryHolder holder, int position) {
        holder.textViewName.setText("Product " + productsBeans.get(position).getId());
        holder.textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));
    }

    @Override
    public int getItemCount() {
        return productsBeans.size();
    }

    public class ProductSubCategoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewItem)
        CardView cardViewItem;

        @BindView(R.id.textViewName)
        TextView textViewName;

        @BindView(R.id.cardViewColor)
        CardView cardViewColor;

        @BindView(R.id.cardViewPrice)
        CardView cardViewPrice;

        @BindView(R.id.cardViewSize)
        CardView cardViewSize;

        public ProductSubCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cardViewColor.setVisibility(View.GONE);
            cardViewPrice.setVisibility(View.GONE);
            cardViewSize.setVisibility(View.GONE);
        }

        @OnClick(R.id.cardViewItem)
        public void clickCard() {
            clickEvent(getAdapterPosition());
        }

    }

    public abstract void clickEvent(int position);

}
