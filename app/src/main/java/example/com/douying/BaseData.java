package example.com.douying;

import android.os.Environment;

import java.io.File;

import static com.lzy.okgo.convert.FileConvert.DM_TARGET_FOLDER;

public class BaseData {
    public static boolean IsShowGuide=false;
    public static String Uid="";
    public static boolean IsLogin=false; //是否登陆
    public static String Turename=""; //昵称
    public static String PATH = Environment.getExternalStorageDirectory() + File.separator + "DouYing" + File.separator;
    public static String Pic=""; //头像链接
    public static int Credits; //积分
    public static int Ticket; //积分
    public static boolean IsSign=false; //签到
    public static int Level; //等级
    public static int Active; //活跃值
}
