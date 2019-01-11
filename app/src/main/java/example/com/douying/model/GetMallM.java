package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin on 2018/12/14.
 */

public class GetMallM {

    /**
     * code : 200
     * data : [{"id":"1","cost":"15","name":"月会员","on_price":"12","credits":"1500","img":"member.png","type":"3"},{"id":"2","cost":"38","name":"季会员","on_price":"30","credits":null,"img":"member.png","type":"1"},{"id":"3","cost":"80","name":"半年会员","on_price":"70","credits":null,"img":"member.png","type":"1"},{"id":"4","cost":"135","name":"年会员","on_price":"115","credits":null,"img":"member.png","type":"1"},{"id":"5","cost":null,"name":"一天会员","on_price":null,"credits":"50","img":"expire.png","type":"2"},{"id":"6","cost":null,"name":"三天会员","on_price":null,"credits":"150","img":"expire.png","type":"2"},{"id":"7","cost":null,"name":"3元代金券","on_price":null,"credits":"50","img":"save.png","type":"2"},{"id":"8","cost":null,"name":"5元代金券","on_price":null,"credits":"100","img":"save.png","type":"2"}]
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
         * id : 1
         * cost : 15
         * name : 月会员
         * on_price : 12
         * credits : 1500
         * img : member.png
         * type : 3
         */

        private String id;
        private String name;
        private String on_price;
        private int credits;
        private String img;
        private int type;
        private float cost;



        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public float getCost() {
            return cost;
        }

        public void setCost(float cost) {
            this.cost = cost;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOn_price() {
            return on_price;
        }

        public void setOn_price(String on_price) {
            this.on_price = on_price;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeFloat(this.cost);
            dest.writeString(this.name);
            dest.writeString(this.on_price);
            dest.writeInt(this.credits);
            dest.writeString(this.img);
            dest.writeInt(this.type);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.cost = in.readFloat();
            this.name = in.readString();
            this.on_price = in.readString();
            this.credits = in.readInt();
            this.img = in.readString();
            this.type = in.readInt();
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
