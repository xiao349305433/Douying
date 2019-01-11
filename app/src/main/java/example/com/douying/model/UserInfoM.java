package example.com.douying.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/9/21.
 */

public class UserInfoM {

    /**
     * code : 200
     * data : {"id":"27","name":"15907170807","pic":"me.png","truename":"DY20180914","logintime":"1536912091","face":null,"weikey":null,"is_vip":"1","class":null,"order_time":null,"cid":null,"alive":null,"address":null,"count":"0","weipic":null,"weiname":null,"attention":null}
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
         * id : 27
         * name : 15907170807
         * pic : me.png
         * truename : DY20180914
         * logintime : 1536912091
         * face : null
         * weikey : null
         * is_vip : 1
         * class : null
         * order_time : null
         * cid : null
         * alive : null
         * address : null
         * count : 0
         * weipic : null
         * weiname : null
         * attention : null
         */

        private String id;
        private String name;
        private String pic;
        private String truename;
        private String logintime;
        private Object face;
        private Object weikey;
        private String is_vip;
        @SerializedName("class")
        private Object classX;
        private Object order_time;
        private Object cid;
        private Object alive;
        private Object address;
        private String count;
        private Object weipic;
        private Object weiname;
        private Object attention;

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

        public Object getFace() {
            return face;
        }

        public void setFace(Object face) {
            this.face = face;
        }

        public Object getWeikey() {
            return weikey;
        }

        public void setWeikey(Object weikey) {
            this.weikey = weikey;
        }

        public String getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(String is_vip) {
            this.is_vip = is_vip;
        }

        public Object getClassX() {
            return classX;
        }

        public void setClassX(Object classX) {
            this.classX = classX;
        }

        public Object getOrder_time() {
            return order_time;
        }

        public void setOrder_time(Object order_time) {
            this.order_time = order_time;
        }

        public Object getCid() {
            return cid;
        }

        public void setCid(Object cid) {
            this.cid = cid;
        }

        public Object getAlive() {
            return alive;
        }

        public void setAlive(Object alive) {
            this.alive = alive;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public Object getWeipic() {
            return weipic;
        }

        public void setWeipic(Object weipic) {
            this.weipic = weipic;
        }

        public Object getWeiname() {
            return weiname;
        }

        public void setWeiname(Object weiname) {
            this.weiname = weiname;
        }

        public Object getAttention() {
            return attention;
        }

        public void setAttention(Object attention) {
            this.attention = attention;
        }
    }
}
