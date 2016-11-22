package com.fangzheng.wolfutils.utils;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * @author fangzheng
 * @date 2016/10/5
 * @email fangzheng428@163.com
 */

public class PermissionManager {

    public static void hasPermission(Activity activity, String permission){

        if(Build.VERSION.SDK_INT<23){
            return;
        }

        if (ContextCompat.checkSelfPermission(activity, permission)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(activity, new String[]{permission},123);
            Log.i("msg","xx");
        }

    }

}
