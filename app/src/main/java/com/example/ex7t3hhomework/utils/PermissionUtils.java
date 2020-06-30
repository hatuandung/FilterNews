package com.example.ex7t3hhomework.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.content.ContextCompat;

public class PermissionUtils {


    public static boolean checkPermissions(Context context, String[] permissions){
        if (Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            for (String p: permissions){
                int status = ContextCompat.checkSelfPermission(context,p);
                if (status == PackageManager.PERMISSION_DENIED){
                    return false;
                }
            }
        }
        return true;
    }

    public static void requestPermissons(Activity context, String[] permissions, int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            context.requestPermissions(permissions, requestCode);
        }
    }
}
