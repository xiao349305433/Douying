package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/12/25.
 */

public class GetNewM {


    /**
     * code : 200
     * data : {"trend":[{"id":"150","title":"动态通知","content":"8J+YgfCfmILwn5iD8J+YhPCfkb/wn5iJ8J+Yig==\n","uid":"49","time":1545729178,"area":"2","type":"praise","pic":"https://xch.lxx673.shop/Uploads/20181217/5c170ef038891.jpeg","truename":"华丽不是残影"},{"id":"150","title":"动态通知","content":"8J+YgfCfmILwn5iD8J+YhPCfkb/wn5iJ8J+Yig==\n","uid":"49","time":1545729173,"area":"2","type":"praise","pic":"https://xch.lxx673.shop/Uploads/20181217/5c170ef038891.jpeg","truename":"华丽不是残影"}],"system":[{"id":"1","title":"放假了","content":"好好好","time":"2018-12-03 16:05:18","area":5}]}
     * info : 请求成功
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
        private List<TrendBean> trend;
        private List<SystemBean> system;

        public List<TrendBean> getTrend() {
            return trend;
        }

        public void setTrend(List<TrendBean> trend) {
            this.trend = trend;
        }

        public List<SystemBean> getSystem() {
            return system;
        }

        public void setSystem(List<SystemBean> system) {
            this.system = system;
        }

        public static class TrendBean implements Parcelable {
            /**
             * id : 150
             * title : 动态通知
             * content : 8J+YgfCfmILwn5iD8J+YhPCfkb/wn5iJ8J+Yig==

             * uid : 49
             * time : 1545729178
             * area : 2
             * type : praise
             * pic : https://xch.lxx673.shop/Uploads/20181217/5c170ef038891.jpeg
             * truename : 华丽不是残影
             */

            private String id;
            private String title;
            private String content;
            private String uid;
            private String time;
            private String area;
            private String type;
            private String pic;
            private String truename;
            private String reply;
            private int aid ;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.title);
                dest.writeString(this.content);
                dest.writeString(this.uid);
                dest.writeString(this.time);
                dest.writeString(this.area);
                dest.writeString(this.type);
                dest.writeString(this.pic);
                dest.writeString(this.truename);
            }

            public TrendBean() {
            }

            protected TrendBean(Parcel in) {
                this.id = in.readString();
                this.title = in.readString();
                this.content = in.readString();
                this.uid = in.readString();
                this.time = in.readString();
                this.area = in.readString();
                this.type = in.readString();
                this.pic = in.readString();
                this.truename = in.readString();
            }

            public static final Creator<TrendBean> CREATOR = new Creator<TrendBean>() {
                @Override
                public TrendBean createFromParcel(Parcel source) {
                    return new TrendBean(source);
                }

                @Override
                public TrendBean[] newArray(int size) {
                    return new TrendBean[size];
                }
            };
        }

        public static class SystemBean implements Parcelable {
            /**
             * id : 1
             * title : 放假了
             * content : 好好好
             * time : 2018-12-03 16:05:18
             * area : 5
             */

            private String id;
            private String title;
            private String content;
            private String time;
            private int area;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getArea() {
                return area;
            }

            public void setArea(int area) {
                this.area = area;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.id);
                dest.writeString(this.title);
                dest.writeString(this.content);
                dest.writeString(this.time);
                dest.writeInt(this.area);
            }

            public SystemBean() {
            }

            protected SystemBean(Parcel in) {
                this.id = in.readString();
                this.title = in.readString();
                this.content = in.readString();
                this.time = in.readString();
                this.area = in.readInt();
            }

            public static final Creator<SystemBean> CREATOR = new Creator<SystemBean>() {
                @Override
                public SystemBean createFromParcel(Parcel source) {
                    return new SystemBean(source);
                }

                @Override
                public SystemBean[] newArray(int size) {
                    return new SystemBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(this.trend);
            dest.writeList(this.system);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.trend = new ArrayList<TrendBean>();
            in.readList(this.trend, TrendBean.class.getClassLoader());
            this.system = new ArrayList<SystemBean>();
            in.readList(this.system, SystemBean.class.getClassLoader());
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
