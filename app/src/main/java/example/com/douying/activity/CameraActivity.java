package example.com.douying.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import douying.example.com.mylibrary.view.utils.Utils;
import example.com.douying.BaseActivity;

import example.com.douying.BaseData;
import example.com.douying.R;
import example.com.douying.activitys.SwtichActorActivityS;
import example.com.douying.http.JsonCallback;
import example.com.douying.model.TakeSingleM;
import example.com.douying.utils.BitmapUtil;
import example.com.douying.utils.CamParaUtil;
import example.com.douying.utils.FileUtil;
import example.com.douying.utils.PermissionChecker;
import example.com.douying.utils.ToastUtil;

import example.com.douying.view.CustomCameraPreview;
import example.com.douying.view.CustomDialog;
import qiu.niorgai.StatusBarCompat;

public class CameraActivity extends BaseActivity implements Camera.FaceDetectionListener {
    @BindView(R.id.camera_surface)
    CustomCameraPreview customCameraPreview;
    @BindView(R.id.camera_img1)
    ImageView camera_img1;
    @BindView(R.id.camera_img2)
    ImageView camera_img2;
    @BindView(R.id.camera_img3)
    ImageView camera_img3;
    @BindView(R.id.camera_swtich)
    ImageView imageView;
    @BindView(R.id.camera_flash)
    ImageView camera_flash;
    @BindView(R.id.camera_take)
    ImageView camera_take;
    @BindView(R.id.camera_next)
    TextView camera_next;
    @BindView(R.id.camera_center)
    RelativeLayout camera_center;
    @BindView(R.id.camera_lift)
    RelativeLayout camera_lift;
    @BindView(R.id.camera_right)
    RelativeLayout camera_right;
    private int mType = CamParaUtil.FRONT;
    private boolean Issave1 = false;
    private boolean Issave2 = false;
    private boolean Issave3 = false;
    private boolean IsFront = true;
    Bitmap bitmap = null;
    boolean IsModel = false;
    CustomDialog dialog;
    HttpParams httpParams = new HttpParams();

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.gg));
        IsModel = getIntent().getBooleanExtra("IsModel", false);
        camera_next.setVisibility(IsModel ? View.GONE : View.VISIBLE);
        switch (getIntent().getIntExtra("direction", 0)) {
            case 1: //center
                camera_center.setVisibility(View.VISIBLE);
                camera_lift.setVisibility(View.GONE);
                camera_right.setVisibility(View.GONE);
                break;
            case 2: //lift
                camera_center.setVisibility(View.GONE);
                camera_lift.setVisibility(View.VISIBLE);
                camera_right.setVisibility(View.GONE);
                break;
            case 3: //right
                camera_center.setVisibility(View.GONE);
                camera_lift.setVisibility(View.GONE);
                camera_right.setVisibility(View.VISIBLE);
                break;
            default:
                break;

        }
    }


    @Override
    public int provideContentViewId() {
        return R.layout.activity_camera;
    }


    @OnClick({R.id.camera_surface, R.id.camera_close, R.id.camera_take, R.id.camera_swtich, R.id.camera_flash, R.id.camera_next})
    public void test(View v) {
        switch (v.getId()) {
            case R.id.camera_surface:
                customCameraPreview.focus();
                break;
            case R.id.camera_close:
                finish();
                break;
            case R.id.camera_take:
                takePhoto();
                break;
            case R.id.camera_swtich:
                if (IsFront) {
                    IsFront = false;
                    mType = CamParaUtil.BACK;
                    customCameraPreview.currentCameraType(CamParaUtil.BACK);
                } else {
                    IsFront = true;
                    mType = CamParaUtil.FRONT;
                    customCameraPreview.currentCameraType(CamParaUtil.FRONT);
                }
                break;
            case R.id.camera_flash:
                switch (customCameraPreview.flashState) {
                    case 1:
                        customCameraPreview.onFlash();
//                        camera_flash.setBackgroundResource(R.drawable.flashing_on);
                        break;
                    case 2:
                        customCameraPreview.autoFlash();
//                        camera_flash.setBackgroundResource(R.drawable.flashing_auto);
                        break;
                    case 3:
                        customCameraPreview.offFlash();
//                        camera_flash.setBackgroundResource(R.drawable.flashing_off);
                        break;
                }
                break;
            case R.id.camera_next:
                startActivity(new Intent(this, WaitActivity.class));
                break;

        }
    }


    private void takePhoto() {
        customCameraPreview.setEnabled(false);
        customCameraPreview.takePhoto(new Camera.PictureCallback() {
            public void onPictureTaken(final byte[] data, final Camera camera) {
                //子线程处理图片，防止ANR
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (data != null) {
                            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                            camera.stopPreview();
                            camera.startPreview();
                        }
                        if (bitmap != null) {
                            Bitmap resBitmap = Bitmap.createBitmap(mType == CamParaUtil.FRONT ? BitmapUtil.rotaingImageView(180, bitmap) : bitmap,
                                    0,
                                    Utils.dp2px(70),
                                    bitmap.getWidth(),
                                    bitmap.getWidth());

                            FileUtil.saveBitmap(resBitmap);

                            if (IsModel) {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        switch (getIntent().getIntExtra("direction", 0)) {
                                            case 1:
                                                camera_img1.setImageBitmap(resBitmap);
                                                break;
                                            case 2:
                                                camera_img2.setImageBitmap(resBitmap);
                                                break;
                                            case 3:
                                                camera_img3.setImageBitmap(resBitmap);
                                                break;
                                        }
                                        Intent intent = new Intent();
                                        intent.putExtra("direction", getIntent().getIntExtra("direction", 0));
                                        intent.putExtra("ImgPath", FileUtil.getImgPath());
                                        setResult(getIntent().getIntExtra("direction", 0), intent);
                                        finish();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        if (!Issave1 && !Issave2 && !Issave3) {
                                            Issave1 = true;
                                            camera_img1.setImageBitmap(resBitmap);
                                            httpParams.put("preview1", new File(FileUtil.getImgPath()));
                                        } else if (Issave1 && !Issave2 && !Issave3) {
                                            Issave2 = true;
                                            camera_img2.setImageBitmap(resBitmap);
                                            httpParams.put("preview2", new File(FileUtil.getImgPath()));
                                        } else if (Issave1 && Issave2 && !Issave3) {
                                            camera_img3.setImageBitmap(resBitmap);
                                            httpParams.put("preview3", new File(FileUtil.getImgPath()));
                                            Issave3 = true;
                                        }
                                        if (Issave1 && Issave2 && Issave3) {
                                            setdialog();
                                            //     ToastUtil.show(CameraActivity.this, "请进行下一步");
                                        }
                                    }
                                });
                            }
                        }
                        return;
                    }
                }).start();
            }
        });
    }

    private void updata() {
        httpParams.put("uid", BaseData.Uid);
        mainHttp.HttpPost(this, "Index/takeSingle", httpParams).execute(new JsonCallback<String>() {
            @Override
            public void onSuccess(Response<String> response) {
                TakeSingleM takeSingleM = new Gson().fromJson(response.body(), TakeSingleM.class);
                switch (takeSingleM.getCode()) {
                    case 202:
                        ToastUtil.show(CameraActivity.this, takeSingleM.getInfo());
                        startActivity(new Intent(CameraActivity.this, WaitActivity.class));
                        break;
                    default:
                        ToastUtil.show(CameraActivity.this, takeSingleM.getInfo());
                        break;

                }
            }
        });

    }

    //uiHandler在主线程中创建，所以自动绑定主线程
//    private Handler uiHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    camera_take.setEnabled(true);
//                    camera_take.setImageResource(R.mipmap.paizhao1);
//                    ToastUtil.show(getApplicationContext(), "发现脸部");
//                    break;
//                case 2:
//                    camera_take.setEnabled(false);
//                    break;
//            }
//        }
//    };





    /**
     * 脸部检测接口
     */
    @Override
    public void onFaceDetection(Camera.Face[] faces, Camera camera) {

        Message message = new Message();
        if (faces.length > 0) {
            Camera.Face face = faces[0];
//            message.what = 1;
//            uiHandler.sendMessage(message);

//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                camera_take.setEnabled(true);
//                camera_take.setImageResource(R.mipmap.paizhao1);
//                ToastUtil.show(getApplicationContext(),"发现脸部");
//            }
//        });

//                Rect rect = face.rect;
//                Log.d("FaceDetection", "可信度：" + face.score + "face detected: " + faces.length +
//                        " Face 1 Location X: " + rect.centerX() +
//                        "Y: " + rect.centerY() + "   " + rect.left + " " + rect.top + " " + rect.right + " " + rect.bottom);
//                Log.e("tag", "【FaceDetectorListener】类的方法：【onFaceDetection】: ");
//                Matrix matrix = updateFaceRect();
//                facesView.updateFaces(matrix, faces);
        } else {
            //     camera_take.setEnabled(false);
//            message.what = 2;
//            uiHandler.sendMessage(message);
            // 只会执行一次
            Log.e("tag", "【FaceDetectorListener】类的方法：【onFaceDetection】: " + "没有脸部");
//                facesView.removeRect();
        }
    }


    private void setdialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(this);
        dialog =
                builder.cancelTouchout(false)
                        .view(R.layout.dialog_swtichface)
                        .heightDimenRes(R.dimen.dialog_loginerror_height)
                        .widthDimenRes(R.dimen.dialog_loginerror_width)
                        .style(R.style.Dialog)
                        .setButtonText(R.id.tocamera, "暂不更新")
                        .setButtonText(R.id.todatabasse, "立即更新")
                        .addViewOnclick(R.id.close, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        })
                        .addViewOnclick(R.id.tocamera, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .addViewOnclick(R.id.todatabasse, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                updata();
                            }
                        })
                        .build();
        dialog.show();

    }
}
