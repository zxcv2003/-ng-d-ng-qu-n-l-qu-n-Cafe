package fpt.edu.Sarangcoffee.utils;

import android.content.Context;
import android.widget.Toast;

import fpt.edu.Sarangcoffee.R;
import io.github.muddz.styleabletoast.StyleableToast;

public class MyToast {
    public static void successful(Context context, String notification){
        // thống báo hệ thông -> thành công
        StyleableToast.makeText(context, notification, Toast.LENGTH_LONG, R.style.successfulToast).show();
    }
    public static void error(Context context, String notification){
        // thống báo hệ thông -> lỗi
        StyleableToast.makeText(context,  notification, Toast.LENGTH_LONG, R.style.errorToast).show();
    }
}
