package com.avikal.ecommerce.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.avikal.ecommerce.model.ProductResponse;
import com.google.gson.Gson;

/**
 * Created by avikaljain on 23/2/2018.
 * <p>
 */
public class SharedPrefsUtils {

    public static final String SHARED_PREF_NAME = "ECommerce";
    public static final String PRODUCT_DETAILS_STRING = "PRODUCT_DETAIL";

    public static void saveProductDetailsSharedPref(Context mContext, ProductResponse productDetails) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(productDetails);
        prefsEditor.putString(PRODUCT_DETAILS_STRING, json);
        prefsEditor.apply();
    }

    public static ProductResponse getProductResponseSharedPref(Context mContext) {
        SharedPreferences mPrefs = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = mPrefs.getString(PRODUCT_DETAILS_STRING, "");
        if (json.equalsIgnoreCase("")) {
            return null;
        }
        ProductResponse obj = gson.fromJson(json, ProductResponse.class);
        return obj;
    }
}