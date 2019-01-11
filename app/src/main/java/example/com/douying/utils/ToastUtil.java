package example.com.douying.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2017/12/20.
 */

public class ToastUtil {
    public static void  show(Context context,String str){
        Toast.makeText(context,str,Toast.LENGTH_LONG).show();
    }
}
