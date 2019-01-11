package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin on 2018/12/7.
 */

public class GetPostReplyM {


    /**
     * code : 200
     * data : [{"id":"97","uid":"47","aid":"62","content":"在家","add_time":"2018-12-06 10:43:05","to_uid":"47","pass":"1","area":"2","send":"1","to_id":"0","truename":"华丽不是残影","pic":"https://xch.lxx673.shop/Uploads/20181204/5c063788c10c5.jpeg","to_name":"华丽不是残影","sub":[{"id":"102","uid":"48","aid":"62","content":"哈哈","add_time":"2018-12-06 16:53:21","to_uid":"47","pass":"1","area":"2","send":"2","to_id":"97","truename":"DY20181206","pic":"https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg","sub":[]}]},{"id":"94","uid":"26","aid":"62","content":"是啊、不会因为我知道","add_time":"2018-12-05 17:18:03","to_uid":"47","pass":"1","area":"2","send":"1","to_id":"0","truename":"平星而论","pic":"https://wx.qlogo.cn/mmopen/vi_32/MYhGNAI2vAHqhGUzGV9RIvhWLuz3icfo6WAy0TSj3fvm1YZuARb8fKtNm8wWTfxoj7ymkMJCsS1oEnTCpibT4uDw/132","to_name":"华丽不是残影","sub":[{"id":"101","uid":"48","aid":"62","content":"喔喔喔？我想知道为什么","add_time":"2018-12-06 16:53:03","to_uid":"26","pass":"1","area":"2","send":"2","to_id":"94","truename":"DY20181206","pic":"https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg","sub":[]},{"id":"100","uid":"48","aid":"62","content":"在家干吗不知道为什么会这样做","add_time":"2018-12-06 16:42:33","to_uid":"26","pass":"1","area":"2","send":"2","to_id":"94","truename":"DY20181206","pic":"https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg","sub":[]},{"id":"99","uid":"48","aid":"62","content":"我摸摸摸鱼。我们都在一起11111包邮到手！在一起的时候我们一起走过来走下去！在一起的时候真的","add_time":"2018-12-06 16:42:01","to_uid":"26","pass":"1","area":"2","send":"2","to_id":"94","truename":"DY20181206","pic":"https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg","sub":[]},{"id":"98","uid":"48","aid":"62","content":"我哟","add_time":"2018-12-06 16:41:37","to_uid":"26","pass":"1","area":"2","send":"2","to_id":"94","truename":"DY20181206","pic":"https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg","sub":[]}]},{"id":"93","uid":"47","aid":"62","content":"在家干吗的时候才知道什么到了这个问题很简单？我们都在一起了。我们都在家好好看看吧！在干嘛在你面前的那一刻开始时是因为自己而努力了","add_time":"2018-12-05 17:16:09","to_uid":"47","pass":"1","area":"2","send":"1","to_id":"0","truename":"华丽不是残影","pic":"https://xch.lxx673.shop/Uploads/20181204/5c063788c10c5.jpeg","to_name":"华丽不是残影","sub":[{"id":"96","uid":"47","aid":"62","content":"333","add_time":"2018-12-06 10:31:18","to_uid":"47","pass":"1","area":"2","send":"2","to_id":"93","truename":"华丽不是残影","pic":"https://xch.lxx673.shop/Uploads/20181204/5c063788c10c5.jpeg","sub":[]},{"id":"95","uid":"26","aid":"62","content":"这种事情就可以做什么都可以的、","add_time":"2018-12-05 17:18:11","to_uid":"47","pass":"1","area":"2","send":"2","to_id":"93","truename":"平星而论","pic":"https://wx.qlogo.cn/mmopen/vi_32/MYhGNAI2vAHqhGUzGV9RIvhWLuz3icfo6WAy0TSj3fvm1YZuARb8fKtNm8wWTfxoj7ymkMJCsS1oEnTCpibT4uDw/132","sub":[]}]}]
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
         * id : 97
         * uid : 47
         * aid : 62
         * content : 在家
         * add_time : 2018-12-06 10:43:05
         * to_uid : 47
         * pass : 1
         * area : 2
         * send : 1
         * to_id : 0
         * truename : 华丽不是残影
         * pic : https://xch.lxx673.shop/Uploads/20181204/5c063788c10c5.jpeg
         * to_name : 华丽不是残影
         * sub : [{"id":"102","uid":"48","aid":"62","content":"哈哈","add_time":"2018-12-06 16:53:21","to_uid":"47","pass":"1","area":"2","send":"2","to_id":"97","truename":"DY20181206","pic":"https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg","sub":[]}]
         */

        private int id;
        private int uid;
        private int aid;
        private String content;
        private String add_time;
        private String to_uid;
        private String pass;
        private String area;
        private String send;
        private String to_id;
        private String truename;
        private String pic;
        private String to_name;
        private List<SubBean> sub;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getTo_uid() {
            return to_uid;
        }

        public void setTo_uid(String to_uid) {
            this.to_uid = to_uid;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getSend() {
            return send;
        }

        public void setSend(String send) {
            this.send = send;
        }

        public String getTo_id() {
            return to_id;
        }

        public void setTo_id(String to_id) {
            this.to_id = to_id;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTo_name() {
            return to_name;
        }

        public void setTo_name(String to_name) {
            this.to_name = to_name;
        }

        public List<SubBean> getSub() {
            return sub;
        }

        public void setSub(List<SubBean> sub) {
            this.sub = sub;
        }

        public static class SubBean implements Parcelable {
            /**
             * id : 102
             * uid : 48
             * aid : 62
             * content : 哈哈
             * add_time : 2018-12-06 16:53:21
             * to_uid : 47
             * pass : 1
             * area : 2
             * send : 2
             * to_id : 97
             * truename : DY20181206
             * pic : https://xch.lxx673.shop/Uploads/20181206/5c08d5c81541d.jpeg
             * sub : []
             */

            private int id;
            private int uid;
            private int aid;
            private String content;
            private String add_time;
            private String to_uid;
            private String pass;
            private String area;
            private String send;
            private String to_id;
            private String truename;
            private String pic;



            public SubBean(String truename, String content) {
                this.truename = truename;
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }

            public String getTo_uid() {
                return to_uid;
            }

            public void setTo_uid(String to_uid) {
                this.to_uid = to_uid;
            }

            public String getPass() {
                return pass;
            }

            public void setPass(String pass) {
                this.pass = pass;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getSend() {
                return send;
            }

            public void setSend(String send) {
                this.send = send;
            }

            public String getTo_id() {
                return to_id;
            }

            public void setTo_id(String to_id) {
                this.to_id = to_id;
            }

            public String getTruename() {
                return truename;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeInt(this.uid);
                dest.writeInt(this.aid);
                dest.writeString(this.content);
                dest.writeString(this.pass);
                dest.writeString(this.to_uid);
                dest.writeString(this.add_time);
                dest.writeString(this.area);
                dest.writeString(this.send);
                dest.writeString(this.to_id);
                dest.writeString(this.truename);
                dest.writeString(this.pic);
            }

            protected SubBean(Parcel in) {
                this.id = in.readInt();
                this.uid = in.readInt();
                this.aid = in.readInt();
                this.content = in.readString();
                this.pass = in.readString();
                this.to_uid = in.readString();
                this.add_time = in.readString();
                this.area = in.readString();
                this.send = in.readString();
                this.to_id = in.readString();
                this.truename = in.readString();
                this.pic = in.readString();
            }

            public static final Parcelable.Creator<SubBean> CREATOR = new Parcelable.Creator<SubBean>() {
                @Override
                public SubBean createFromParcel(Parcel source) {
                    return new SubBean(source);
                }

                @Override
                public SubBean[] newArray(int size) {
                    return new SubBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeInt(this.uid);
            dest.writeInt(this.aid);
            dest.writeString(this.content);
            dest.writeString(this.add_time);
            dest.writeString(this.to_uid);
            dest.writeString(this.pass);
            dest.writeString(this.area);
            dest.writeString(this.send);
            dest.writeString(this.to_id);
            dest.writeString(this.truename);
            dest.writeString(this.pic);
            dest.writeString(this.to_name);
            dest.writeTypedList(this.sub);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.uid = in.readInt();
            this.aid = in.readInt();
            this.content = in.readString();
            this.add_time = in.readString();
            this.to_uid = in.readString();
            this.pass = in.readString();
            this.area = in.readString();
            this.send = in.readString();
            this.to_id = in.readString();
            this.truename = in.readString();
            this.pic = in.readString();
            this.to_name = in.readString();
            this.sub = in.createTypedArrayList(SubBean.CREATOR);
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
