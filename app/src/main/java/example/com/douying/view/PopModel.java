package example.com.douying.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;

import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activity.CaptureActivity;
import example.com.douying.activity.MoreActivity;
import example.com.douying.adapter.SwtichModelAdapter;
import example.com.douying.http.MainHttp;
import example.com.douying.model.FaceM;

import static android.widget.LinearLayout.HORIZONTAL;

/**
 * Created by admin on 2018/10/8.
 */

public class PopModel {
    Context mContext;
    PopupWindow mPopupWindow;
    ImageView mface;
    public PopModel(Context context,ImageView face ){
        mContext=context;
        mface=face;
    }

    //显示popupwindow
    public void showPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_swtichmodel, null);
        RecyclerView recyclerView=view.findViewById(R.id.pop_swtichmodel_rv);
        FaceM faceM=new Gson().fromJson(BaseData.Face,FaceM.class);
        SwtichModelAdapter modelAdapter=new SwtichModelAdapter(faceM.getModel());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext,HORIZONTAL,true));
        recyclerView.setAdapter(modelAdapter);
        modelAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ImgLoadUtils.loadCircularBead(mContext, MainHttp.RES_STRING + modelAdapter.getData().get(position).getList().get(0).getUrl(),mface);
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(view,((Activity)mContext).getWindowManager().getDefaultDisplay().getWidth()*2/4, ViewGroup.LayoutParams.WRAP_CONTENT, false);
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
//        mPopupWindow.showAsDropDown(More,0,0,Gravity.BOTTOM);
        mPopupWindow.showAtLocation(((Activity)mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });

    }
}
