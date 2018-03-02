package com.avikal.ecommerce.communicator;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.avikal.ecommerce.R;
import com.avikal.ecommerce.utils.ProjectUtils;


/**
 * This class check network availability, either wifi of any mobile network.
 * It have one static method which return true or false.
 * If network available turn True otherwise false
 */
public class CheckNetworkState {
    public static boolean isOnline(Context context) {
        boolean value = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null) {
                value = activeNetwork.isConnectedOrConnecting();
            }
            NetworkInfo wifiNetwork = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null) {
                value = wifiNetwork.isConnectedOrConnecting();
            }
            NetworkInfo mobileNetwork = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null) {
                value = mobileNetwork.isConnectedOrConnecting();
            }
            NetworkInfo otherNetwork = cm.getActiveNetworkInfo();
            if (otherNetwork != null) {
                value = otherNetwork.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!value){
            ProjectUtils.showToast(context.getString(R.string.network_validation_msg));
        }
        
        return value;
    }

    public static boolean isOnlineWOToast(Context context) {
        boolean value = false;
        try {
            ConnectivityManager cm = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            if (activeNetwork != null) {
                value = activeNetwork.isConnectedOrConnecting();
            }
            NetworkInfo wifiNetwork = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (wifiNetwork != null) {
                value = wifiNetwork.isConnectedOrConnecting();
            }
            NetworkInfo mobileNetwork = cm
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (mobileNetwork != null) {
                value = mobileNetwork.isConnectedOrConnecting();
            }
            NetworkInfo otherNetwork = cm.getActiveNetworkInfo();
            if (otherNetwork != null) {
                value = otherNetwork.isConnectedOrConnecting();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* if(!value){
            ProjectUtils.showToast(context.getString(R.string.network_validation_msg));
        }*/

        return value;
    }
}
