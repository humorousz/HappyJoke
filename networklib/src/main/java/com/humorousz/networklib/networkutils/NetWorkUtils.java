package com.humorousz.networklib.networkutils;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;


import com.humorousz.commonutils.service.CommonService;

/**
 * Created by zhangzhiquan on 2017/5/5.
 * 获取网络状态相关的Utils,
 * 1.可以获取是否连接网络{@link #isNetWorkConnected(Context)}
 * 2.可以获取是否wifi连接{@link #isWifiConnected(Context)}
 * 3.可以获取是否4G、3G、2G连接{@link #isNet4GConnected(Context)}、{@link #isNet3GConnected(Context)}、{@link #isNet2GConnected(Context)}
 *
 * 注意：如果使用不传Context参数的方法，记得初始化CommonService{@link CommonService#init(Application)}
 * 参考{http://www.jianshu.com/p/10ed9ae02775}
 */

public class NetWorkUtils {
    public static final int TYPE_NO_CONNECT = 0x00;
    public static final int TYPE_WIFI = 0x01;
    public static final int TYPE_MOBILE = 0x01<<1;
    public static final int TYPE_2G = 0x01<<2;
    public static final int TYPE_3G = 0x01<<3;
    public static final int TYPE_4G = 0x01<<4;


    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */

    public static boolean isNetWorkConnected(Context context){
        return getAPNType(context) != TYPE_NO_CONNECT;
    }

    /**
     * 判断是否有网络连接
     * 需要初始化CommonService
     *
     * @return
     */

    public static boolean isNetWorkConnected(){
        return isNetWorkConnected(CommonService.getApplication());
    }


    /**
     * 判断是否wifi连接
     *
     * @param context
     * @return
     */

    public static boolean isWifiConnected(Context context){
        return (getAPNType(context) & TYPE_WIFI) != 0;
    }

    /**
     * 判断是否wifi连接
     *
     * 需要初始化CommonService
     * @return
     */
    public static boolean isWifiConnected(){
        return isWifiConnected(CommonService.getApplication());
    }

    /**
     * 判断是否移动网络
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context){
        return (getAPNType(context) & TYPE_MOBILE) != 0;
    }


    /**
     *
     * 判断是否移动网络
     *
     * 需要初始化CommonService
     * @return
     */

    public static boolean isMobileConnected(){
        return isMobileConnected(CommonService.getApplication());
    }

    /**
     * 判断是否4G网络
     * @param context
     * @return
     */

    public static boolean isNet4GConnected(Context context){
        return (getAPNType(context) & TYPE_4G) != 0;
    }

    /**
     * 判断是否4G网络
     * 需要初始化CommonService
     *
     * @return
     */
    public static boolean isNet4GConnected(){
        return isNet4GConnected(CommonService.getApplication());
    }

    /**
     * 判断是否3G网络
     * @param context
     * @return
     */

    public static boolean isNet3GConnected(Context context){
        return (getAPNType(context) & TYPE_3G) != 0;
    }

    /**
     * 判断是否3G网络
     * 需要初始化CommonService
     *
     * @return
     */
    public static boolean isNet3GConnected(){
        return isNet3GConnected(CommonService.getApplication());
    }

    /**
     * 判断是否2G网络
     * @param context
     * @return
     */

    public static boolean isNet2GConnected(Context context){
        return (getAPNType(context) & TYPE_2G) !=0;
    }

    /**
     * 判断是否2G网络
     * 需要初始化CommonService
     *
     * @return
     */

    public static boolean isNet2GConnected(){
        return isNet2GConnected(CommonService.getApplication());
    }



    public static int getAPNType(Context context){
        if(context == null){
            return TYPE_NO_CONNECT;
        }

        NetworkInfo networkInfo = getActiveNewWorkInfo(context);
        if(networkInfo == null){
            return TYPE_NO_CONNECT;
        }

        int netType;
        netType = networkInfo.getType();
        if( ConnectivityManager.TYPE_WIFI == netType ){
            netType = TYPE_WIFI;
        }else if(ConnectivityManager.TYPE_MOBILE == netType){
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            netType = getTypeMobile(networkInfo.getSubtype(),telephonyManager.isNetworkRoaming());
        }
        return  netType;
    }


    public static int getTypeMobile(int subType,boolean isRoaming){
        if(subType <= 0)
            return TYPE_NO_CONNECT;
        int netType = TYPE_MOBILE;

        if(TelephonyManager.NETWORK_TYPE_LTE == subType && !isRoaming){
            netType |= TYPE_4G;
        }else if (TelephonyManager.NETWORK_TYPE_UMTS == subType
                || TelephonyManager.NETWORK_TYPE_HSDPA == subType
                || TelephonyManager.NETWORK_TYPE_HSPAP == subType
                || TelephonyManager.NETWORK_TYPE_EVDO_0 == subType
                && !isRoaming){
            netType |= TYPE_3G;
        }else if(TelephonyManager.NETWORK_TYPE_GPRS == subType
                || TelephonyManager.NETWORK_TYPE_EDGE == subType
                || TelephonyManager.NETWORK_TYPE_CDMA == subType
                && !isRoaming){
            netType |= TYPE_2G;
        }else {
            netType |= TYPE_2G;
        }

        return netType;
    }


    private static NetworkInfo getActiveNewWorkInfo(Context context){
        if(context == null)
            return null;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        return networkInfo;
    }
}
