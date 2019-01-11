package example.com.douying.view;

import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.io.IOException;

import example.com.douying.activity.CameraActivity;
import example.com.douying.utils.CamParaUtil;
import example.com.douying.utils.ToastUtil;

/**
 * Created by gxj on 2018/2/17 01:46.
 * 相机预览封装
 */
public class CustomCameraPreview extends SurfaceView implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceHolder mholder;
    public int flashState = 1;

    public CustomCameraPreview(Context context) {
        super(context);
        init();
    }

    public CustomCameraPreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomCameraPreview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setKeepScreenOn(true);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        mCamera = CamParaUtil.openCamera(CamParaUtil.FRONT);
        if (mCamera != null) {
            mholder=holder;
            startPreview(holder);
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        mCamera.stopPreview();
        startPreview(holder);
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        //回收释放资源
        release();
    }

    /**
     * 预览相机
     */
    private void startPreview( SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            // 设置脸部检测监听
            mCamera.setFaceDetectionListener(new CameraActivity());
            // 开始脸部检测
            startFaceDetection();
            Camera.Parameters parameters = mCamera.getParameters();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //竖屏拍照时，需要设置旋转90度，否者看到的相机预览方向和界面方向不相同
                mCamera.setDisplayOrientation(90);
                parameters.setRotation(90);
            } else {
                mCamera.setDisplayOrientation(0);
                parameters.setRotation(0);
            }
            Camera.Size bestSize = CamParaUtil.getBestSize(parameters.getSupportedPreviewSizes());
            if (bestSize != null) {
                parameters.setPreviewSize(bestSize.width, bestSize.height);
                parameters.setPictureSize(bestSize.width, bestSize.height);
            } else {
                parameters.setPreviewSize(1920, 1080);
                parameters.setPictureSize(1920, 1080);
            }
            mCamera.setParameters(parameters);
            mCamera.startPreview();
            focus();
        } catch (Exception e) {
            try {
                Camera.Parameters parameters = mCamera.getParameters();
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    mCamera.setDisplayOrientation(90);
                    parameters.setRotation(90);
                } else {
                    mCamera.setDisplayOrientation(0);
                    parameters.setRotation(0);
                }
                mCamera.setParameters(parameters);
                mCamera.startPreview();
                focus();
            } catch (Exception e1) {
                e.printStackTrace();
                mCamera = null;
            }
        }
    }


    public void currentCameraType(int type){
        if(mCamera!=null){
            release();
            mCamera=CamParaUtil.openCamera(type);
            startPreview(mholder);
        }

    }

    /**
     * 启动脸部检测，如果getMaxNumDetectedFaces()!=0说明不支持脸部检测
     */
    public void startFaceDetection() {
        // Try starting Face Detection
        Camera.Parameters params = mCamera.getParameters();
        // start face detection only *after* preview has started
        if (params.getMaxNumDetectedFaces() > 0) {
            // mCamera supports face detection, so can start it:
            mCamera.startFaceDetection();
        } else {
            Log.e("tag", "【FaceDetectorActivity】类的方法：【startFaceDetection】: " + "不支持");
        }
    }







    /**
     * 释放资源
     */
    private void release() {
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
            mCamera = null;
        }
    }

    /**
     * 对焦，在CameraActivity中触摸对焦
     */
    public void focus() {
        if (mCamera != null) {
            mCamera.autoFocus(null);
        }
    }

    /**
     * 拍摄照片
     *
     * @param pictureCallback 在pictureCallback处理拍照回调
     */
    public void takePhoto(Camera.PictureCallback pictureCallback) {
        if (mCamera != null) {
            mCamera.takePicture(null, null, pictureCallback);
        }
    }



    public void offFlash() {
        flashState = 1;
        Camera.Parameters parameter = mCamera.getParameters();
        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        mCamera.setParameters(parameter);
    }

    public void onFlash() {
        flashState = 2;
        Camera.Parameters parameter = mCamera.getParameters();
        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
        mCamera.setParameters(parameter);
    }

    public void autoFlash() {
        flashState = 3;
        Camera.Parameters parameter = mCamera.getParameters();
        parameter.setFlashMode(Camera.Parameters.FLASH_MODE_AUTO);
        mCamera.setParameters(parameter);
    }
}
