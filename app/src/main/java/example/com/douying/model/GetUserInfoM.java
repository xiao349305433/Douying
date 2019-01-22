package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by admin on 2019/1/17.
 */

public class GetUserInfoM {

    /**
     * code : 200
     * data : {"id":"27","name":"15907170807","pic":"https://xch.lxx673.shop/Uploads/20181221/5c1c4ccce49ea.jpg","truename":"Bleach","logintime":"1536912091","face":"{\"model\": [{\"pid\": 1, \"list\": [{\"url\": \"20190115/5c3d59d33600c.jpeg\"}, {\"url\": \"20190115/5c3d59d3363c8.jpeg\"}, {\"url\": \"20190115/5c3d7bd358f94.jpeg\"}], \"title\": \"第二天\", \"is_default\": 2}, {\"pid\": 2, \"list\": [{\"url\": \"20190115/5c3d7cff97d65.jpeg\"}, {\"url\": \"20190115/5c3d7cff98464.jpeg\"}, {\"url\": \"20190115/5c3d7cff98c34.jpg\"}, {\"url\": \"20190115/5c3d7cff990f6.jpg\"}, {\"url\": \"20190115/5c3d7cff9953c.jpg\"}, {\"url\": \"20190115/5c3d7cff99afa.jpg\"}], \"title\": \"润之\", \"is_default\": 1}]}","address":"武汉市","location":null,"count":"0","weikey":null,"weipic":null,"weiname":null,"privilege":"2","ticket":"10,9,9,9,10","credits":"9998399","money":"0.00","active":"0","grade":"1","attention":"26","care":"1","favorite_id":"26,47","favorite":"2","token":"1104a8979282f0fe7fe","status":"1","device":"android"}
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
         * truename : Bleach
         * logintime : 1536912091
         * face : {"model": [{"pid": 1, "list": [{"url": "20190115/5c3d59d33600c.jpeg"}, {"url": "20190115/5c3d59d3363c8.jpeg"}, {"url": "20190115/5c3d7bd358f94.jpeg"}], "title": "第二天", "is_default": 2}, {"pid": 2, "list": [{"url": "20190115/5c3d7cff97d65.jpeg"}, {"url": "20190115/5c3d7cff98464.jpeg"}, {"url": "20190115/5c3d7cff98c34.jpg"}, {"url": "20190115/5c3d7cff990f6.jpg"}, {"url": "20190115/5c3d7cff9953c.jpg"}, {"url": "20190115/5c3d7cff99afa.jpg"}], "title": "润之", "is_default": 1}]}
         * address : 武汉市
         * location : null
         * count : 0
         * weikey : null
         * weipic : null
         * weiname : null
         * privilege : 2
         * ticket : 10,9,9,9,10
         * credits : 9998399
         * money : 0.00
         * active : 0
         * grade : 1
         * attention : 26
         * care : 1
         * favorite_id : 26,47
         * favorite : 2
         * token : 1104a8979282f0fe7fe
         * status : 1
         * device : android
         */

        private String id;
        private String name;
        private String pic;
        private String truename;
        private String logintime;
        private String face;
        private String address;
        private String location;
        private String count;
        private String weikey;
        private String weipic;
        private String weiname;
        private String privilege;
        private String ticket;
        private int credits;
        private String money;
        private int active;
        private int grade;
        private String attention;
        private int care;
        private String favorite_id;
        private int favorite;
        private String token;
        private int status;
        private String device;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getWeikey() {
            return weikey;
        }

        public void setWeikey(String weikey) {
            this.weikey = weikey;
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

        public String getPrivilege() {
            return privilege;
        }

        public void setPrivilege(String privilege) {
            this.privilege = privilege;
        }

        public String getTicket() {
            return ticket;
        }

        public void setTicket(String ticket) {
            this.ticket = ticket;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {
            this.credits = credits;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getActive() {
            return active;
        }

        public void setActive(int active) {
            this.active = active;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getAttention() {
            return attention;
        }

        public void setAttention(String attention) {
            this.attention = attention;
        }

        public int getCare() {
            return care;
        }

        public void setCare(int care) {
            this.care = care;
        }

        public String getFavorite_id() {
            return favorite_id;
        }

        public void setFavorite_id(String favorite_id) {
            this.favorite_id = favorite_id;
        }

        public int getFavorite() {
            return favorite;
        }

        public void setFavorite(int favorite) {
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

        public String getDevice() {
            return device;
        }

        public void setDevice(String device) {
            this.device = device;
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
            dest.writeString(this.address);
            dest.writeString(this.location);
            dest.writeString(this.count);
            dest.writeString(this.weikey);
            dest.writeString(this.weipic);
            dest.writeString(this.weiname);
            dest.writeString(this.privilege);
            dest.writeString(this.ticket);
            dest.writeInt(this.credits);
            dest.writeString(this.money);
            dest.writeInt(this.active);
            dest.writeInt(this.grade);
            dest.writeString(this.attention);
            dest.writeInt(this.care);
            dest.writeString(this.favorite_id);
            dest.writeInt(this.favorite);
            dest.writeString(this.token);
            dest.writeInt(this.status);
            dest.writeString(this.device);
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
            this.address = in.readString();
            this.location = in.readString();
            this.count = in.readString();
            this.weikey = in.readString();
            this.weipic = in.readString();
            this.weiname = in.readString();
            this.privilege = in.readString();
            this.ticket = in.readString();
            this.credits = in.readInt();
            this.money = in.readString();
            this.active = in.readInt();
            this.grade = in.readInt();
            this.attention = in.readString();
            this.care = in.readInt();
            this.favorite_id = in.readString();
            this.favorite = in.readInt();
            this.token = in.readString();
            this.status = in.readInt();
            this.device = in.readString();
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
