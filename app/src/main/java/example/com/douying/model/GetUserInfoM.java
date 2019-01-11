package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2018/12/21.
 */

public class GetUserInfoM {

    /**
     * code : 200
     * data : {"id":"27","name":"15907170807","pic":"https://xch.lxx673.shop/Uploads/20181221/5c1c4ccce49ea.jpg","truename":"DY20180914","logintime":"1536912091","face":"{\"list\": [{\"id\": 1, \"url\": \"https://xch.lxx673.shop/Uploads/20181018/5bc83ade91205.jpg\", \"title\": \"模型1-左侧面\"}, {\"id\": 2, \"url\": \"https://xch.lxx673.shop/Uploads/20181018/5bc83ade92cbe.jpg\", \"title\": \"模型2-正面\"}, {\"id\": 3, \"url\": \"https://xch.lxx673.shop/Uploads/20181018/5bc83ade942ba.jpg\", \"title\": \"模型3-右侧面\"}]}","weikey":null,"is_vip":"2","order_time":null,"credits":"0","address":"武汉市","count":"0","weipic":null,"weiname":null,"attention":"","location":null,"privilege":"2","money":"0.00","favorite":"0","token":"3215sdf13ad1f65asd4f3ads1f","status":"2","favorite_id":null,"device":"android","ticket":""}
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
        /**
         * id : 27
         * name : 15907170807
         * pic : https://xch.lxx673.shop/Uploads/20181221/5c1c4ccce49ea.jpg
         * truename : DY20180914
         * logintime : 1536912091
         * face : {"list": [{"id": 1, "url": "https://xch.lxx673.shop/Uploads/20181018/5bc83ade91205.jpg", "title": "模型1-左侧面"}, {"id": 2, "url": "https://xch.lxx673.shop/Uploads/20181018/5bc83ade92cbe.jpg", "title": "模型2-正面"}, {"id": 3, "url": "https://xch.lxx673.shop/Uploads/20181018/5bc83ade942ba.jpg", "title": "模型3-右侧面"}]}
         * weikey : null
         * is_vip : 2
         * order_time : null
         * credits : 0
         * address : 武汉市
         * count : 0
         * weipic : null
         * weiname : null
         * attention :
         * location : null
         * privilege : 2
         * money : 0.00
         * favorite : 0
         * token : 3215sdf13ad1f65asd4f3ads1f
         * status : 2
         * favorite_id : null
         * device : android
         * ticket :
         */

        private String id;
        private String name;
        private String pic;
        private String truename;
        private String logintime;
        private String face;
        private String weikey;
        private String is_vip;
        private String order_time;
        private int credits;
        private String address;
        private String count;
        private String weipic;
        private String weiname;
        private String attention;
        private String location;
        private String privilege;
        private String money;
        private String favorite;
        private String token;
        private int status;
        private String favorite_id;
        private String device;
        private String ticket;
        private int grade;
        private int active;
        private int care;

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getCare() {
            return care;
        }

        public void setCare(int care) {
            this.care = care;
        }

        public int getGrade() {

            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getLogintime() {
            return logintime;
        }

        public void setLogintime(String logintime) {
            this.logintime = logintime;
        }

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getWeikey() {
            return weikey;
        }

        public void setWeikey(String weikey) {
            this.weikey = weikey;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public String getOrder_time() {
            return order_time;
        }

        public void setOrder_time(String order_time) {
            this.order_time = order_time;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getWeipic() {
            return weipic;
        }

        public void setWeipic(String weipic) {
            this.weipic = weipic;
        }

        public String getWeiname() {
            return weiname;
        }

        public void setWeiname(String weiname) {
            this.weiname = weiname;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPrivilege() {
            return privilege;
        }

        public void setPrivilege(String privilege) {
            this.privilege = privilege;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getFavorite() {
            return favorite;
        }

        public void setFavorite(String favorite) {
            this.favorite = favorite;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(String favorite_id) {
            this.favorite_id = favorite_id;
        }

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.pic);
            dest.writeString(this.truename);
            dest.writeString(this.logintime);
            dest.writeString(this.face);
            dest.writeString(this.weikey);
            dest.writeString(this.is_vip);
            dest.writeString(this.order_time);
            dest.writeInt(this.credits);
            dest.writeString(this.address);
            dest.writeString(this.count);
            dest.writeString(this.weipic);
            dest.writeString(this.weiname);
            dest.writeString(this.attention);
            dest.writeString(this.location);
            dest.writeString(this.privilege);
            dest.writeString(this.money);
            dest.writeString(this.favorite);
            dest.writeString(this.token);
            dest.writeInt(this.status);
            dest.writeString(this.favorite_id);
            dest.writeString(this.device);
            dest.writeString(this.ticket);
            dest.writeInt(this.grade);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.pic = in.readString();
            this.truename = in.readString();
            this.logintime = in.readString();
            this.face = in.readString();
            this.weikey = in.readString();
            this.is_vip = in.readString();
            this.order_time = in.readString();
            this.credits = in.readInt();
            this.address = in.readString();
            this.count = in.readString();
            this.weipic = in.readString();
            this.weiname = in.readString();
            this.attention = in.readString();
            this.location = in.readString();
            this.privilege = in.readString();
            this.money = in.readString();
            this.favorite = in.readString();
            this.token = in.readString();
            this.status = in.readInt();
            this.favorite_id = in.readString();
            this.device = in.readString();
            this.ticket = in.readString();
            this.grade = in.readInt();
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
