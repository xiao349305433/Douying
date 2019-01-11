package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin on 2018/9/17.
 */

public class GetListM implements Parcelable {

    /**
     * code : 200
     * data : [{"id":"4","uid":"27","poster":"product/poster/zuopin1_800x494.png","playurl":"product/poster/zuopin1_800x494.png","posttime":"2018-09-17 12:00:01","introduce":"第一部作品","praise":"100"}]
     * info : 请求成功
     * status : 2
     */

    private int code;
    private String info;
    private int status;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * id : 4
         * uid : 27
         * poster : product/poster/zuopin1_800x494.png
         * playurl : product/poster/zuopin1_800x494.png
         * posttime : 2018-09-17 12:00:01
         * introduce : 第一部作品
         * praise : 100
         */

        private String id;
        private String uid;
        private String poster;
        private String playurl;
        private String posttime;
        private String introduce;
        private String praise;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getPlayurl() {
            return playurl;
        }

        public void setPlayurl(String playurl) {
            this.playurl = playurl;
        }

        public String getPosttime() {
            return posttime;
        }

        public void setPosttime(String posttime) {
            this.posttime = posttime;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getPraise() {
            return praise;
        }

        public void setPraise(String praise) {
            this.praise = praise;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.uid);
            dest.writeString(this.poster);
            dest.writeString(this.playurl);
            dest.writeString(this.posttime);
            dest.writeString(this.introduce);
            dest.writeString(this.praise);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.uid = in.readString();
            this.poster = in.readString();
            this.playurl = in.readString();
            this.posttime = in.readString();
            this.introduce = in.readString();
            this.praise = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.code);
        dest.writeString(this.info);
        dest.writeInt(this.status);
        dest.writeTypedList(this.data);
    }

    public GetListM() {
    }

    protected GetListM(Parcel in) {
        this.code = in.readInt();
        this.info = in.readString();
        this.status = in.readInt();
        this.data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Parcelable.Creator<GetListM> CREATOR = new Parcelable.Creator<GetListM>() {
        @Override
        public GetListM createFromParcel(Parcel source) {
            return new GetListM(source);
        }

        @Override
        public GetListM[] newArray(int size) {
            return new GetListM[size];
        }
    };
}
