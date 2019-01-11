package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2018/9/14.
 */

public class GetLoginM {

    /**
     * code : 206
     * data : {"name":"15907170807","pic":"me.png","truename":"DY20180914","logintime":1536912091,"is_vip":1,"id":"27"}
     * info : 登录成功
     * status : 2
     */

    private int code;
    private DataBean data;
    private String info;
    private int status;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean implements Parcelable {
        /**
         * name : 15907170807
         * pic : me.png
         * truename : DY20180914
         * logintime : 1536912091
         * is_vip : 1
         * id : 27
         */

        private String name;
        private String pic;
        private String truename;
        private int logintime;
        private int is_vip;
        private String id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public int getLogintime() {
            return logintime;
        }

        public void setLogintime(int logintime) {
            this.logintime = logintime;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.name);
            dest.writeString(this.pic);
            dest.writeString(this.truename);
            dest.writeInt(this.logintime);
            dest.writeInt(this.is_vip);
            dest.writeString(this.id);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.name = in.readString();
            this.pic = in.readString();
            this.truename = in.readString();
            this.logintime = in.readInt();
            this.is_vip = in.readInt();
            this.id = in.readString();
        }

        public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
