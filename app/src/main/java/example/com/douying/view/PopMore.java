package example.com.douying.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import example.com.douying.R;
import example.com.douying.activity.CaptureActivity;
import example.com.douying.activity.MoreActivity;

/**
 * Created by admin on 2018/10/8.
 */

public class PopMore {
    Context mContext;
    PopupWindow mPopupWindow;
    ImageView More;
    public PopMore(Context context ,ImageView  more){
        mContext=context;
        More=more;
    }

    //显示popupwindow
    public void showPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_homs_more, null);
        TextView message=view.findViewById(R.id.pop_homes_message);
        TextView sao=view.findViewById(R.id.pop_homes_sao);
//        TextView quan=view.findViewById(R.id.pop_homes_quan);
        TextView circle=view.findViewById(R.id.pop_homes_circle);
        TextView point=view.findViewById(R.id.pop_homes_point);
        mPopupWindow = new PopupWindow(view,((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth()*1/4, ViewGroup.LayoutParams.WRAP_CONTENT, false);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                    //不做任何响应,不 dismiss popupWindow
                    return true;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });
        mPopupWindow.showAsDropDown(More,0,0,Gravity.BOTTOM);
        mPopupWindow.showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContext.startActivity(new Intent(mContext, CaptureActivity.class));
            }
        });

//        quan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(mContext, MoreActivity.class);
//                intent.putExtra("Type","quan");
//                mContext.startActivity(intent);
//            }
//        });

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MoreActivity.class);
                intent.putExtra("Type","message");
                mContext.startActivity(intent);
            }
        });

        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext, MoreActivity.class);
                intent.putExtra("Type","circle");
                mContext.startActivity(intent);
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,MoreActivity.class);
                intent.putExtra("Type","point");
                mContext.startActivity(intent);
            }
        });


    }
}
