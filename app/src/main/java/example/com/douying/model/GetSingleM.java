package example.com.douying.model;

/**
 * Created by admin on 2018/9/17.
 */

public class GetSingleM {

    /**
     * code : 200
     * data : {"id":"4","uid":"27","poster":"product/poster/zuopin1_800x494.png","playurl":"product/poster/zuopin1_800x494.png","posttime":"2018-09-17 12:00:01","introduce":"第一部作品","praise":"100"}
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
    }
}
