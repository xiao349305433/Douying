package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2019/1/9.
 */

public class MaterialM {

    /**
     * code : 200
     * data : [{"id":"20","uid":"27","poster":"","playurl":"20190109/5c3596171c215.mp4","posttime":"2019-01-09 14:35:03","introduce":"KTV噢噢噢哦哦","praise":"0","device":null,"address":null,"show":"2","reply_count":"0"}]
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

    public static class DataBean {
        /**
         * id : 20
         * uid : 27
         * poster :
         * playurl : 20190109/5c3596171c215.mp4
         * posttime : 2019-01-09 14:35:03
         * introduce : KTV噢噢噢哦哦
         * praise : 0
         * device : null
         * address : null
         * show : 2
         * reply_count : 0
         */

        private String id;
        private String uid;
        private String poster;
        private String playurl;
        private String posttime;
        private String introduce;
        private String praise;
        private Object device;
        private Object address;
        private String show;
        private String reply_count;

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

        public Object getDevice() {
            return device;
        }

        public void setDevice(Object device) {
            this.device = device;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public String getShow() {
            return show;
        }

        public void setShow(String show) {
            this.show = show;
        }

        public String getReply_count() {
            return reply_count;
        }

        public void setReply_count(String reply_count) {
            this.reply_count = reply_count;
        }
    }
}
