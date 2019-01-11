package douying.example.com.mylibrary.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import douying.example.com.mylibrary.R;


public class AppLoading {

    private static Dialog dialog;

    public static void show(Context context) {
        dialog = new Dialog(context, R.style.app_dialog);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCanceledOnTouchOutside(false);
        TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("正在加载...");
        dialog.show();
    }

    public static void close() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
