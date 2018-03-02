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
 * Created by avikaljain on 22/2/18.
 */

public abstract class ProductSubCategoryAdapter extends RecyclerView.Adapter<ProductSubCategoryAdapter.ProductSubCategoryHolder> {

    private Context context;
    private List<ProductResponse.ProductsBean> productsBeans;
    private List<ProductResponse.VariantsBean> variantsList;
    private LayoutInflater layoutInflater;

    public ProductResponse.VariantsBean variantsBean;

    public ProductSubCategoryAdapter(Context context, List<ProductResponse.ProductsBean> productsBeans) {
        this.context = context;
        this.productsBeans = productsBeans;
        variantsBean = new ProductResponse.VariantsBean();
        variantsBean.setColor("Color");
        variantsBean.setPrice("Price");
        variantsBean.setSize("Size");
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductSubCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = layoutInflater.inflate(R.layout.item_sub_category, parent, false);

        return new ProductSubCategoryHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ProductSubCategoryHolder holder, int position) {
        holder.textViewName.setText(productsBeans.get(position).getName());
        holder.textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorBlack));

        variantsList = productsBeans.get(position).getVariants();
        variantsList.add(0, variantsBean);

        setColorSpinnerData(holder.colorSpinner, (ArrayList<ProductResponse.VariantsBean>) variantsList);
        setPriceSpinnerData(holder.priceSpinner, (ArrayList<ProductResponse.VariantsBean>) variantsList);
        setSizeSpinnerData(holder.sizeSpinner, (ArrayList<ProductResponse.VariantsBean>) variantsList);
    }

    private void setColorSpinnerData(final Spinner colorSpinner, final ArrayList<ProductResponse.VariantsBean> variantsList) {

        colorSpinner.setAdapter(new ColorSpinnerAdapter(context, variantsList));
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                variantsBean = variantsList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setPriceSpinnerData(Spinner priceSpinner, final ArrayList<ProductResponse.VariantsBean> variantsList) {

        priceSpinner.setAdapter(new PriceSpinnerAdapter(context, variantsList));
        priceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void setSizeSpinnerData(Spinner sizeSpinner, final ArrayList<ProductResponse.VariantsBean> variantsList) {

        sizeSpinner.setAdapter(new SizeSpinnerAdapter(context, variantsList));
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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

        @BindView(R.id.colorSpinner)
        Spinner colorSpinner;

        @BindView(R.id.sizeSpinner)
        Spinner sizeSpinner;

        @BindView(R.id.priceSpinner)
        Spinner priceSpinner;


        public ProductSubCategoryHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.cardViewItem)
        public void clickCard() {
            clickEvent(getAdapterPosition());
        }

    }

    public abstract void clickEvent(int position);

}
