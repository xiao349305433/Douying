package example.com.douying.model;

/**
 * Created by admin on 2018/9/14.
 */

public class CodeM {

    /**
     * code : 200
     * data : {"sendCode":"997274","sendTime":"20180914","news":{"Message":"OK","RequestId":"BCE7042B-CD50-4392-ADE4-7ED78F18E691","BizId":"573619036894891441^0","Code":"OK"}}
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
         * sendCode : 997274
         * sendTime : 20180914
         * news : {"Message":"OK","RequestId":"BCE7042B-CD50-4392-ADE4-7ED78F18E691","BizId":"573619036894891441^0","Code":"OK"}
         */

        private String sendCode;
        private String sendTime;
        private NewsBean news;

        public String getSendCode() {
            return sendCode;
        }

        public void setSendCode(String sendCode) {
            this.sendCode = sendCode;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public NewsBean getNews() {
            return news;
        }

        public void setNews(NewsBean news) {
            this.news = news;
        }

        public static class NewsBean {
            /**
             * Message : OK
             * RequestId : BCE7042B-CD50-4392-ADE4-7ED78F18E691
             * BizId : 573619036894891441^0
             * Code : OK
             */

            private String Message;
            private String RequestId;
            private String BizId;
            private String Code;

            public String getMessage() {
                return Message;
            }

            public void setMessage(String Message) {
                this.Message = Message;
            }

            public String getRequestId() {
                return RequestId;
            }

            public void setRequestId(String RequestId) {
                this.RequestId = RequestId;
            }

            public String getBizId() {
                return BizId;
            }

            public void setBizId(String BizId) {
                this.BizId = BizId;
            }

            public String getCode() {
                return Code;
            }

            public void setCode(String Code) {
                this.Code = Code;
            }
        }
    }
}
