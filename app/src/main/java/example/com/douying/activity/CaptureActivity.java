package example.com.douying.activity;

import android.app.Activity;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.jslx.qccodelib.CameraConfigurationManager;
import com.jslx.qccodelib.ViewfinderView;

import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;

import example.com.douying.R;

//二维码扫描界面
public class CaptureActivity extends Activity implements SurfaceHolder.Callback {

    private Camera mCamera;
    private SurfaceHolder mHolder;
    private SurfaceView surface_view;
    private ImageScanner scanner;
    private Handler autoFocusHandler;
    private CameraConfigurationManager configManager;
    private boolean initialized;

    private AsyncDecode asyncDecode;
    private float nLenSatrt = 0;
    private int maxzoom = 0;

    private ViewfinderView finder_view;

    static {
        System.loadLibrary("iconv");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        configManager = new CameraConfigurationManager(this);
        init();
//        findViewById(R.id.captu_finish).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                finish();
//            }
//        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int nCnt = event.getPointerCount();
        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && 2 == nCnt) {
            nLenSatrt = distance(event);
        } else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE && 2 == nCnt) {
            float nLenEnd = distance(event);
            if (nLenEnd > nLenSatrt) {
                upZoom();
            } else {
                downZoom();
            }
        }
        return super.onTouchEvent(event);
    }

    public float distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
    }

    public void upZoom() {
        if (mCamera == null)
            return;
        Parameters p = mCamera.getParameters();
        int zoom = p.getZoom();
        zoom = zoom + 3;
        if (maxzoom == 0 || zoom > maxzoom)
            zoom = maxzoom;
        p.setZoom(zoom);
        mCamera.setParameters(p);
    }

    public void downZoom() {
        if (mCamera == null)
            return;
        Parameters p = mCamera.getParameters();
        int zoom = p.getZoom();
        zoom = zoom - 3;
        if (zoom < 0 || maxzoom == 0)
            zoom = 0;
        p.setZoom(zoom);
        mCamera.setParameters(p);
    }

    private void init() {
        surface_view = (SurfaceView) findViewById(R.id.preview_view);
        finder_view = (ViewfinderView) findViewById(R.id.viewfinder_view);

        mHolder = surface_view.getHolder();
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mHolder.addCallback(this);
        scanner = new ImageScanner();
        scanner.setConfig(0, net.sourceforge.zbar.Config.X_DENSITY, 3);
        scanner.setConfig(0, net.sourceforge.zbar.Config.Y_DENSITY, 3);
        autoFocusHandler = new Handler();
        asyncDecode = new AsyncDecode();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera = Camera.open();
            Parameters myParam = mCamera.getParameters();
            maxzoom = myParam.getMaxZoom();
            if (!initialized) {
                initialized = true;
                configManager.initFromCameraParameters(mCamera);
            }
            configManager.setDesiredCameraParameters(mCamera);
        } catch (Exception e) {
            mCamera = null;
        }
    }

    PreviewCallback previewCallback = new PreviewCallback() {
        public void onPreviewFrame(final byte[] data, final Camera camera) {
            if (asyncDecode.isStoped()) {
                Parameters parameters = camera.getParameters();
                Size size = parameters.getPreviewSize();
                Image source = new Image(size.width, size.height, "Y800");
                Rect scanImageRect = finder_view.getScanImageRect(size.height, size.width);
                source.setCrop(scanImageRect.top, scanImageRect.left, scanImageRect.bottom, scanImageRect.right);
                source.setData(data);

                asyncDecode = new AsyncDecode();
                asyncDecode.execute(source);
            } else {
                camera.addCallbackBuffer(data);
            }
        }
    };

    AutoFocusCallback autoFocusCallback = new AutoFocusCallback() {
        public void onAutoFocus(boolean success, Camera camera) {
            autoFocusHandler.postDelayed(doAutoFocus, 1000);
        }
    };

    private Runnable doAutoFocus = new Runnable() {
        public void run() {
            if (null == mCamera || null == autoFocusCallback) {
                return;
            }
            mCamera.autoFocus(autoFocusCallback);
        }
    };

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if (mHolder.getSurface() == null) {
            return;
        }
        try {
            mCamera.stopPreview();
        } catch (Exception e) {
        }
        try {
            Size previewSize = mCamera.getParameters().getPreviewSize();
            int dataBufferSize = (int) (previewSize.height * previewSize.width * (ImageFormat.getBitsPerPixel(mCamera.getParameters().getPreviewFormat()) / 8.0));
            mCamera.addCallbackBuffer(new byte[dataBufferSize]);
            mCamera.addCallbackBuffer(new byte[dataBufferSize]);
            mCamera.addCallbackBuffer(new byte[dataBufferSize]);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setPreviewCallback(previewCallback);
            mCamera.startPreview();
            mCamera.autoFocus(autoFocusCallback);
        } catch (Exception e) {
            Toast.makeText(CaptureActivity.this, "您未允许应用访问您的相机\n请在授权管理中更改设置", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mCamera != null) {
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    private boolean handlermessage = false;

    private class AsyncDecode extends AsyncTask<Image, Void, Void> {
        private boolean stoped = true;
        private String str = "";

        @Override
        protected Void doInBackground(Image... params) {
            stoped = false;
            Image barcode = params[0];
            int result = scanner.scanImage(barcode);
            if (mCamera != null)
                mCamera.addCallbackBuffer(barcode.getData());
            if (result != 0) {
                SymbolSet syms = scanner.getResults();
                for (Symbol sym : syms) {
                    str = sym.getData();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            stoped = true;
            if (!handlermessage && !str.isEmpty()) {
                handlermessage = true;

//                Class<?> clazz= null;
//                try {
//                    clazz = Class.forName("com.jslx.mobileteaching.activity.StartTingkeActivity");
//                    Intent intent=new Intent(CaptureActivity.this,clazz);
//                    startActivity(intent);
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//                finish();
//                new AlertDialog.Builder(CaptureActivity.this).setMessage(str).setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        handlermessage = false;
//                    }
//                }).show();
            }
        }

        public boolean isStoped() {
            return stoped;
        }
    }

}
