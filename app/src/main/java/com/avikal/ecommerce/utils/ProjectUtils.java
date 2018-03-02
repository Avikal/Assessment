package com.avikal.ecommerce.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.avikal.ecommerce.app.ECommerceApp;

/**
 * Created by avikaljain on 21/2/18.
 */

public class ProjectUtils {

    private static ProgressDialog mProgressDialog;
    public static void showToast(String msg) {
        Toast.makeText(ECommerceApp.getInstance().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showProgressDialog(Context mContext) {
        if (mProgressDialog != null) {
            mProgressDialog = null;
        }
        Logger.e("", "progress dailog start...");
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setMessage("Please wait...");
        mProgressDialog.setCancelable(false);
        if (mProgressDialog != null && !mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public static void hideProgressDialog(Activity activity) {
        if (activity != null && !activity.isFinishing()
                && mProgressDialog != null && mProgressDialog.isShowing()) {
            Logger.e("", "dialog dismiss");
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


}
