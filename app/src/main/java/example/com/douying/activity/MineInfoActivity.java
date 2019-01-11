package example.com.douying.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.ImgLoadUtils;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseActivity;
import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.http.JsonCallback;
import example.com.douying.http.MainHttp;
import example.com.douying.model.GetEditM;
import example.com.douying.model.UserInfoM;

import example.com.douying.utils.ToastUtil;

import qiu.niorgai.StatusBarCompat;

/**
 * Created by admin on 2018/9/21.
 */

public class MineInfoActivity extends BaseActivity {
    @BindView(R.id.mineinfo_img)
    ImageView mineinfo_img;
    @BindView(R.id.mineinfo_title)
    TextView mineinfo_title;
    @BindView(R.id.mineinfo_name)
    TextView mineinfo_name;
    private SharedPreferences mPerferences;

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
        ImgLoadUtils.loadCirthumbnail(MineInfoActivity.this, BaseData.Pic, mineinfo_img);
        mineinfo_name.setText(BaseData.Turename);
        mineinfo_title.setText(BaseData.Turename);
    }

    @Override
    public int provideContentViewId() {
        return R.layout.activity_mineinfo;
    }

    @OnClick({R.id.mineinfo_name, R.id.mineinfo_img})
    public void test(View view) {
        switch (view.getId()) {
            case R.id.mineinfo_name:
                final EditText editText = new EditText(this);
                final AlertDialog.Builder normalDialog =
                        new AlertDialog.Builder(MineInfoActivity.this);
                normalDialog.setTitle("修改昵称");
                normalDialog.setView(editText);
                normalDialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mineinfo_name.setText(editText.getText().toString());
                                getEdit(2, null);
                            }
                        });

                // 显示
                normalDialog.show();
                break;
            case R.id.mineinfo_img:
                updatepicture();
                break;
        }
    }


    private void getEdit(int type, File file) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", BaseData.Uid);
        switch (type) {
            case 1:
                httpParams.put("pic", file);
                break;
            case 2:
                httpParams.put("truename", mineinfo_name.getText().toString());
                break;
        }
        mainHttp.HttpPost(this, "Index/getEdit", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                GetEditM getEditM = new Gson().fromJson(response.body(), GetEditM.class);
                switch (getEditM.getCode()) {
                    case 202:
                        switch (type) {
                            case 1:
//                                ImgLoadUtils.loadCircularBead(MineInfoActivity.this, file, mineinfo_img);
                                ImgLoadUtils.loadCirthumbnail(MineInfoActivity.this, getEditM.getData().getPic(), mineinfo_img);
                                SharedPreferences.Editor editor = mPerferences.edit();
                                editor.putString("pic", getEditM.getData().getPic());
                                editor.apply();
                                BaseData.Pic = getEditM.getData().getPic();
                                break;
                            case 2:
                                break;
                        }
                        ToastUtil.show(MineInfoActivity.this, getEditM.getInfo());
                        break;
                    default:
                        ToastUtil.show(MineInfoActivity.this, getEditM.getInfo());
                        break;
                }
            }
        });

    }

    private void updatepicture() {
        final String[] items = {"相机拍摄", "手机相册"};
        new AlertDialog.Builder(MineInfoActivity.this).setTitle("修改头像").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    if (ContextCompat.checkSelfPermission(MineInfoActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 1);
                    } else {
                        getSetting();
                    }
                } else {
                    try {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("image/*");
                        startActivityForResult(intent, 2);
                    } catch (ActivityNotFoundException e) {
                        ToastUtil.show(MineInfoActivity.this, "未能找到照片");
                    }
                }
            }
        }).show();
    }

    //返回数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case RESULT_OK:
                //上传头像
//                AppLoading.show(this);
                Bitmap temp = Utils.getPhoto(requestCode, data, MineInfoActivity.this);
                Bitmap bitmap = Utils.centerSquareScaleBitmap(temp, 300);
//                File file = new File(getRealPathFromURI(data.getData()));
                try {
                    getEdit(1, saveFile(bitmap, BaseData.PATH, System.currentTimeMillis() + ".jpg"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static File saveFile(Bitmap bm, String path, String fileName) throws IOException {
        File dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path, fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

}
