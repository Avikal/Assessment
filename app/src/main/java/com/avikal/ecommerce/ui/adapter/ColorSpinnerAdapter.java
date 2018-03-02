package com.avikal.ecommerce.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.model.ProductResponse;

import java.util.ArrayList;

/**
 * Created by avikaljain on 22/2/18.
 */
public class ColorSpinnerAdapter extends ArrayAdapter<ProductResponse.VariantsBean> {

    private ArrayList<ProductResponse.VariantsBean> list;
    private LayoutInflater inflater;
    ProductResponse.VariantsBean variantsBean;


    public ColorSpinnerAdapter(Context context, ArrayList<ProductResponse.VariantsBean> list) {
        super(context, 0, list);
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Ui for selected the event
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        ColorViewHolder viewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.select_varinets_1, null);
            viewHolder = new ColorViewHolder();
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textViewColor);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ColorViewHolder) convertView.getTag();
        }
        viewHolder.textViewName.setText(list.get(position).getColor());
        return convertView;
    }

    /**
     * Drop down ui of the list
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    public View getDropDownView(int position, View convertView, ViewGroup
            parent) {
        View itemView = inflater.inflate(R.layout.select_varinets_2, null);
        TextView text = (TextView) itemView.findViewById(R.id.text);
        text.setText(list.get(position).getColor());
        return itemView;
    }

    public static class ColorViewHolder {
        TextView textViewName;
    }
}

