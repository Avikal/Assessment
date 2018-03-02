package com.avikal.ecommerce.ui.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.model.ProductResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by avikaljain on 23/2/18.
 */

public abstract class ProductFilterCategoryAdapter extends RecyclerView.Adapter<ProductFilterCategoryAdapter.ProductCategoryHolder> {

    private Context context;
    private List<ProductResponse.RankingsBean> rankingList;
    private LayoutInflater layoutInflater;

    public ProductFilterCategoryAdapter(Context context, List<ProductResponse.RankingsBean> rankingList) {
        this.context = context;
        this.rankingList = rankingList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ProductCategoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = layoutInflater.inflate(R.layout.item_category, parent, false);

        return new ProductCategoryHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(ProductCategoryHolder holder, int position) {
        holder.textViewName.setText(rankingList.get(position).getRanking());
        holder.textViewName.setTextColor(ContextCompat.getColor(context, R.color.colorWhite));
        if (position % 5 == 0) {
            holder.cardViewItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorCyan));
        } else if (position % 5 == 1) {
            holder.cardViewItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorYellow));
        } else if (position % 5 == 2) {
            holder.cardViewItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorGreen));
        } else if (position % 5 == 3) {
            holder.cardViewItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorIndigo));
        } else if (position % 5 == 4) {
            holder.cardViewItem.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorDeepOrange));
        }

    }

    @Override
    public int getItemCount() {
        return rankingList.size();
    }

    public class ProductCategoryHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardViewItem)
        CardView cardViewItem;

        @BindView(R.id.textViewName)
        TextView textViewName;

        public ProductCategoryHolder(View itemView) {
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
