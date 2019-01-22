package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2019/1/14.
 */

public class GetModelM {


    /**
     * code : 200
     * data : {"list":[{"pid":1,"list":[{"url":"20190108/5c344a0e1f5be.png"},{"url":"20190108/5c344a0e1e1dc.png"},{"url":"20190108/5c344a0e1d17a.png"},{"url":"20190108/5c344a0e1d17a.png"}],"title":"模型1","is_default":"2"}],"face":"{\"model\":\"[{\\\"pid\\\": 1, \\\"list\\\": [{\\\"url\\\": \\\"20190108\\/5c344a0e1f5be.png\\\"}, {\\\"url\\\": \\\"20190108\\/5c344a0e1e1dc.png\\\"}, {\\\"url\\\": \\\"20190108\\/5c344a0e1d17a.png\\\"}, {\\\"url\\\": \\\"20190108\\/5c344a0e1d17a.png\\\"}], \\\"title\\\": \\\"\\u6a21\\u578b1\\\", \\\"is_default\\\": \\\"2\\\"}]\"}"}
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

    public static class DataBean {
        /**
         * list : [{"pid":1,"list":[{"url":"20190108/5c344a0e1f5be.png"},{"url":"20190108/5c344a0e1e1dc.png"},{"url":"20190108/5c344a0e1d17a.png"},{"url":"20190108/5c344a0e1d17a.png"}],"title":"模型1","is_default":"2"}]
         * face : {"model":"[{\"pid\": 1, \"list\": [{\"url\": \"20190108\/5c344a0e1f5be.png\"}, {\"url\": \"20190108\/5c344a0e1e1dc.png\"}, {\"url\": \"20190108\/5c344a0e1d17a.png\"}, {\"url\": \"20190108\/5c344a0e1d17a.png\"}], \"title\": \"\u6a21\u578b1\", \"is_default\": \"2\"}]"}
         */

        private String face;
        private List<ListBeanX> list;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public List<ListBeanX> getList() {
            return list;
        }

        public void setList(List<ListBeanX> list) {
            this.list = list;
        }

        public static class ListBeanX implements Parcelable {
            /**
             * pid : 1
             * list : [{"url":"20190108/5c344a0e1f5be.png"},{"url":"20190108/5c344a0e1e1dc.png"},{"url":"20190108/5c344a0e1d17a.png"},{"url":"20190108/5c344a0e1d17a.png"}]
             * title : 模型1
             * is_default : 2
             */

            private int pid;
            private String title;
            private String is_default;
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

            public String getIs_default() {
                return is_default;
            }

            public void setIs_default(String is_default) {
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
                 * url : 20190108/5c344a0e1f5be.png
                 */

                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(this.url);
                }

                public ListBean() {
                }

                protected ListBean(Parcel in) {
                    this.url = in.readString();
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
                dest.writeInt(this.pid);
                dest.writeString(this.title);
                dest.writeString(this.is_default);
                dest.writeList(this.list);
            }

            public ListBeanX() {
            }

            protected ListBeanX(Parcel in) {
                this.pid = in.readInt();
                this.title = in.readString();
                this.is_default = in.readString();
                this.list = new ArrayList<ListBean>();
                in.readList(this.list, ListBean.class.getClassLoader());
            }

            public static final Parcelable.Creator<ListBeanX> CREATOR = new Parcelable.Creator<ListBeanX>() {
                @Override
                public ListBeanX createFromParcel(Parcel source) {
                    return new ListBeanX(source);
                }

                @Override
                public ListBeanX[] newArray(int size) {
                    return new ListBeanX[size];
                }
            };
        }
    }
}
