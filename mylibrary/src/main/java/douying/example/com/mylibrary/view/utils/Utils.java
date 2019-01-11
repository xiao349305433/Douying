package douying.example.com.mylibrary.view.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 16/12/08
 *     desc  : Utils初始化相关
 * </pre>
 */
public class Utils {

    private static Context context;
    private File file;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
    /**
     * 部分字符颜色变化
     * @return Stringhtml
     */
    public static String getcolorString(String datastr1,String [] strs){
        //先排序
        for (int i = 0; i <strs.length ; i++) {
            for (int j = 0; j < i; j++) {
                if(datastr1.indexOf(strs[j])>datastr1.indexOf(strs[j+1])){
                    String temp=strs[j];
                    strs[j]=strs[j+1];
                    strs[j+1]=temp;
                }
            }
        }
        //indexof 数组
        int [] strs_indexof = new int[strs.length];
        //字符串长度数组
        int [] strs_length= new int[strs.length];
        //最终结果
        String textall="";
        for (int i = 0; i < strs.length; i++) {
            strs_indexof[i]= datastr1.indexOf(strs[i]);
            strs_length[i]=strs[i].length();
        }
        switch (strs.length){
            case 0:
                textall=text1(datastr1);
                break;
            case 1:
                textall=text1(datastr1.substring(0,datastr1.indexOf(strs[0])))+
                        text2(strs[0])+
                        text1(datastr1.substring(strs[0].length()+datastr1.indexOf(strs[0]),datastr1.length()));
                break;
            //大于1
            default:
                textall=text1(datastr1.substring(0,strs_indexof[0]))+
                    text2(strs[0]);
                for (int i = 1; i <strs.length ; i++) {
                    textall= textall+text1(datastr1.substring(strs[i-1].length()+datastr1.indexOf(strs[i-1]),strs_indexof[i]))+
                            text2(strs[i]);
                }
                int size=strs.length;
                textall=textall+text1(datastr1.substring(strs_indexof[size-1]+strs[size-1].length(),datastr1.length()));
                break;
        }

        return textall;
    }

    public static String getcolorString2(String datastr1,String [] strs){
        //先排序
        for (int i = 0; i <strs.length ; i++) {
            for (int j = 0; j < i; j++) {
                if(datastr1.indexOf(strs[j])>datastr1.indexOf(strs[j+1])){
                    String temp=strs[j];
                    strs[j]=strs[j+1];
                    strs[j+1]=temp;
                }
            }
        }
        //indexof 数组
        int [] strs_indexof = new int[strs.length];
        //字符串长度数组
        int [] strs_length= new int[strs.length];
        //最终结果
        String textall="";
        for (int i = 0; i < strs.length; i++) {
            strs_indexof[i]= datastr1.indexOf(strs[i]);
            strs_length[i]=strs[i].length();
        }
        switch (strs.length){
            case 0:
                textall=text1(datastr1);
                textall=textall+tvimg();
                break;
            case 1:
                textall=text1(datastr1.substring(0,datastr1.indexOf(strs[0])))+
                        text2(strs[0])+
                        text1(datastr1.substring(strs[0].length()+datastr1.indexOf(strs[0]),datastr1.length()));
                textall=textall+tvimg();
                break;
            //大于1
            default:
                textall=text1(datastr1.substring(0,strs_indexof[0]))+
                        text2(strs[0]);
                for (int i = 1; i <strs.length ; i++) {
                    textall= textall+text1(datastr1.substring(strs[i-1].length()+datastr1.indexOf(strs[i-1]),strs_indexof[i]))+
                            text2(strs[i]);
                }
                int size=strs.length;
                textall=textall+text1(datastr1.substring(strs_indexof[size-1]+strs[size-1].length(),datastr1.length()));
                textall=textall+tvimg();
                break;
        }
        return textall;
    }


    //白色字体
    private static String text1(String str1){
        return "<font color=\"#FFFFFF\">"+str1+"</font>";
    }
    //红色字体
    private static String text2(String str2){
        return  "<font color=\"#40C8FF\">"+str2+"</font>";
    }

    private static String tvimg(){
        return  "<img src=\"R.drawable.taskmain_zhankai\"/>";
    }

    private static String text3(String str3){return  "<a href="+str3+">"+str3+"</a>";}

    static class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener listener) {
            mListener = listener;
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view);
        }
    }

    public static void textHtmlClick(Context context, TextView tv) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable sp = (Spannable) text;
            URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
            SpannableStringBuilder style = new SpannableStringBuilder(text);
            style.clearSpans();// should clear old spans
            for (URLSpan url : urls) {
                MyURLSpan myURLSpan = new MyURLSpan(url.getURL(), context);
                style.setSpan(myURLSpan, sp.getSpanStart(url),
                        sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
            }
            tv.setText(style);
        }
    }
    private static class MyURLSpan extends ClickableSpan {
        private String mUrl;
        private Context mContext;

        MyURLSpan(String url, Context context) {
            mContext = context;
            mUrl = url;
        }

        @Override
        public void onClick(View widget) {
         //   Toast.makeText(mContext,"跳转网页",Toast.LENGTH_SHORT).show();
        }
    }



    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }


    /**
     * getBase64
     * @param str
     * @return
     */
    public static String getBase64(String str) {
      String base64str = android.util.Base64.encodeToString(str.getBytes(), android.util.Base64.DEFAULT);
        return base64str;
    }

    /**
     * Base64toStr
     * @param Base64str
     * @return
     */
    public static String Base64toStr(String Base64str) {
        String decodedString =new String(android.util.Base64.decode(Base64str,android.util.Base64.DEFAULT));
        return decodedString;
    }


    public static double getAngle1(double lat_a, double lng_a, double lat_b, double lng_b) {

        double y = Math.sin(lng_b-lng_a) * Math.cos(lat_b);
        double x = Math.cos(lat_a)*Math.sin(lat_b) - Math.sin(lat_a)*Math.cos(lat_b)*Math.cos(lng_b-lng_a);
        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        if(brng < 0)
            brng = brng +360;
        return brng;

    }

    // 读文件，返回字符串
    public static String ReadFile(String path) {
        File file = new File(path);
        BufferedReader reader = null;
        String laststr = "";
        try {
            // System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                laststr = laststr + tempString;
                ++line;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return laststr;
    }

    //数字转文字
    public static String convert(int number) {
        //数字对应的汉字
        String[] num = {"零","一","二","三","四","五","六","七","八","九"};
        //单位
        String[] unit = {"","十","百","千","万","十","百","千","亿","十","百","千","万亿"};
        //将输入数字转换为字符串
        String result = String.valueOf(number);
        //将该字符串分割为数组存放
        char[] ch = result.toCharArray();
        //结果 字符串
        String str = "";
        int length = ch.length;
        for (int i = 0; i < length; i++) {
            int c = (int)ch[i]-48;
            if(c != 0) {
                str += num[c]+unit[length-i-1];
            } else {
                str += num[c];
            }
        }
        System.out.println(str);
        return str;
    }
    //数字转文字
    public static String convertAB(int number) {
        //数字对应的汉字
        String[] num = {"A","B","C","D","E","F","G","H","I","J"};
        //单位
        String[] unit = {"","十","百","千","万","十","百","千","亿","十","百","千","万亿"};
        //将输入数字转换为字符串
        String result = String.valueOf(number);
        //将该字符串分割为数组存放
        char[] ch = result.toCharArray();
        //结果 字符串
        String str = "";
        int length = ch.length;
        for (int i = 0; i < length; i++) {
            int c = (int)ch[i]-48;
            if(c != 0) {
                str += num[c]+unit[length-i-1];
            } else {
                str += num[c];
            }
        }
        System.out.println(str);
        return str;
    }




    //参数不重复的随机数
    public static int[] randomCommon(int min, int max, int n){
        if (n > (max - min + 1) || max < min) {
            return null;
        }
        int[] result = new int[n];
        int count = 0;
        while(count < n) {
            int num = (int) (Math.random() * (max - min)) + min;
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                if(num == result[j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                result[count] = num;
                count++;
            }
        }
        return result;
    }

    public static int dp2px(int dp) {
        float density = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * density + .5f);
    }


    //获取设备串码
    public static String getDeviceId(Context context) {
        SharedPreferences mPerferences = PreferenceManager.getDefaultSharedPreferences(context);
        String deviceId = mPerferences.getString("deviceId", "");
        if (deviceId.equals("")) {
            deviceId = System.currentTimeMillis() + "";
            SharedPreferences.Editor mEditor = mPerferences.edit();
            mEditor.putString("deviceId", deviceId);
            mEditor.apply();
        }
        return deviceId;
    }


    public static String getMyUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }

//    /**
//     * 圆形的Transformation
//     * Created by Raye on 2016/5/3.
//     */
//    public static class CircleTransform extends BitmapTransformation {
//        public CircleTransform(Context context) {
//            super(context);
//        }
//
//        @Override
//        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
//            return circleCrop(pool, toTransform);
//        }
//
//        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
//
//            int size = Math.min(source.getWidth(), source.getHeight());
//
//            int width = (source.getWidth() - size) / 2;
//            int height = (source.getHeight() - size) / 2;
//
//            Bitmap bitmap = pool.get(size, size, Bitmap.Config.ARGB_8888);
//            if (bitmap == null) {
//                bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
//            }
//
//            Canvas canvas = new Canvas(bitmap);
//            Paint paint = new Paint();
//            BitmapShader shader =
//                    new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//            if (width != 0 || height != 0) {
//                // source isn't square, move viewport to center
//                Matrix matrix = new Matrix();
//                matrix.setTranslate(-width, -height);
//                shader.setLocalMatrix(matrix);
//            }
//            paint.setShader(shader);
//            paint.setAntiAlias(true);
//
//            float r = size / 2f;
//            canvas.drawCircle(r, r, r, paint);
//
//            return bitmap;
//        }
//
//        @Override
//        public String getId() {
//            return getClass().getName();
//        }
//    }

    /**
     * 获取照片
     * @param requestCode
     * @param data
     * @param context
     * @return
     */
    public static Bitmap getPhoto(int requestCode, Intent data, Context context) {
        data.getData();
        Bitmap bitmap = null;
        if (requestCode == 1) {
            bitmap = data.getParcelableExtra("data");
        } else {
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        double maxSize = 512.00;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        double mid = b.length / 1024;
        if (mid > maxSize) {
            double i = mid / maxSize;
            bitmap = zoomImage(bitmap, bitmap.getWidth() / Math.sqrt(i), bitmap.getHeight() / Math.sqrt(i));
        }
        return bitmap;
    }

    /**
     * @param bgimage
     * @param newWidth
     * @param newHeight
     * @return
     */
    private static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 截图
     * @param bitmap
     * @param edgeLength
     * @return
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }
        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();

        if (widthOrg > edgeLength && heightOrg > edgeLength) {
            // 压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = edgeLength * Math.max(widthOrg, heightOrg) / Math.min(widthOrg, heightOrg);
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;

            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true);
            } catch (Exception e) {
                return null;
            }
            // 从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;

            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft, edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }
        return result;
    }


    public static File saveBitmapFile(Bitmap bitmap, String path){
        File file=new File(path);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }


    public static File saveBitmap(String path, Bitmap bitmap) {
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
        return file;
    }


    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
    }


    //设置成白色的背景，字体颜色为黑色。
    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //设置成白色的背景，字体颜色为黑色。
    public static boolean setMeizuStatusBarDarkIcon(Activity activity, boolean dark) {
        boolean result = false;
        if (activity != null) {
            try {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                activity.getWindow().setAttributes(lp);
                result = true;
            } catch (Exception e) {
            }
        }
        return result;
    }
    // 判断手机号
    public static boolean isMobile(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static File saveFile(Bitmap bm,String path, String fileName) throws IOException {
        File dirFile = new File(path);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }
        File myCaptureFile = new File(path , fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
        return myCaptureFile;
    }

    //毫秒转换为小时
    public static String ms2HMS(int _ms){
        String HMStime;
        _ms/=1000;
        int hour=_ms/3600;
        int mint=(_ms%3600)/60;
        int sed=_ms%60;
        String hourStr=String.valueOf(hour);
        if(hour<10){
            hourStr="0"+hourStr;
        }
        String mintStr=String.valueOf(mint);
        if(mint<10){
            mintStr="0"+mintStr;
        }
        String sedStr=String.valueOf(sed);
        if(sed<10){
            sedStr="0"+sedStr;
        }
        if(hourStr.equals("00")){
            HMStime=mintStr+":"+sedStr;
        }else {
            HMStime=hourStr+":"+mintStr+":"+sedStr;
        }
        return HMStime;
    }



}