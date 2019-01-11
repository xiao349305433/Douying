package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/1/3.
 */

public class GetModelM2 {


    /**
     * code : 200
     * data : [{"pid":1,"list":[{"id":1,"url":"weixin.jpg","title":"左侧面"},{"id":2,"url":"weixin.jpg","title":"正面"},{"id":3,"url":"weixin.jpg","title":"右侧面"}],"title":"模型1","is_default":2},{"pid":2,"list":[{"id":1,"url":"weixin.jpg","title":"左侧面"},{"id":2,"url":"weixin.jpg","title":"正面"},{"id":3,"url":"weixin.jpg","title":"右侧面"}],"title":"模型2","is_default":1},{"pid":3,"list":[{"id":1,"url":"weixin.jpg","title":"左侧面"},{"id":2,"url":"weixin.jpg","title":"正面"},{"id":3,"url":"weixin.jpg","title":"右侧面"}],"title":"模型3","is_default":1}]
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
         * pid : 1
         * list : [{"id":1,"url":"weixin.jpg","title":"左侧面"},{"id":2,"url":"weixin.jpg","title":"正面"},{"id":3,"url":"weixin.jpg","title":"右侧面"}]
         * title : 模型1
         * is_default : 2
         */
        private boolean isEdit;
        private int pid;
        private String title;
        private int is_default;

        public boolean isEdit() {
            return isEdit;
        }

        public void setEdit(boolean edit) {
            isEdit = edit;
        }

        private List<ListBean> list;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Parcelable {
            /**
             * id : 1
             * url : weixin.jpg
             * title : 左侧面
             */

            private int id;
            private String url;
            private String title;
            public boolean isDelete;
            public boolean imgshow;
            public boolean isImgshow() {
                return imgshow;
            }

            public void setImgshow(boolean imgshow) {
                this.imgshow = imgshow;
            }


            public boolean isDelete() {
                return isDelete;
            }

            public void setDelete(boolean delete) {
                isDelete = delete;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.id);
                dest.writeString(this.url);
                dest.writeString(this.title);
                dest.writeByte(this.isDelete ? (byte) 1 : (byte) 0);
                dest.writeByte(this.imgshow ? (byte) 1 : (byte) 0);
            }

            public ListBean() {
            }

            protected ListBean(Parcel in) {
                this.id = in.readInt();
                this.url = in.readString();
                this.title = in.readString();
                this.isDelete = in.readByte() != 0;
                this.imgshow = in.readByte() != 0;
            }

            public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                @Override
                public ListBean createFromParcel(Parcel source) {
                    return new ListBean(source);
                }

                @Override
                public ListBean[] newArray(int size) {
                    return new ListBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeByte(this.isEdit ? (byte) 1 : (byte) 0);
            dest.writeInt(this.pid);
            dest.writeString(this.title);
            dest.writeInt(this.is_default);
            dest.writeList(this.list);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.isEdit = in.readByte() != 0;
            this.pid = in.readInt();
            this.title = in.readString();
            this.is_default = in.readInt();
            this.list = new ArrayList<ListBean>();
            in.readList(this.list, ListBean.class.getClassLoader());
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
