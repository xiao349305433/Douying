package example.com.douying.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 2018/12/18.
 */

public class GetOrderM {

    /**
     * code : 200
     * data : {"partnerid":"1520936071","prepayid":"wx1817300956168757bba4d3043330015673","timestamp":"1545125409","appid":"wxec29f95f93e8fa39","noncestr":"pxi2035t73lmn5fpypejmfvpek7tgqwt","package":"Sign=WXPay","sign":"BAE0ED1B1F3031F869CEFB5ECB96945D"}
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
         * partnerid : 1520936071
         * prepayid : wx1817300956168757bba4d3043330015673
         * timestamp : 1545125409
         * appid : wxec29f95f93e8fa39
         * noncestr : pxi2035t73lmn5fpypejmfvpek7tgqwt
         * package : Sign=WXPay
         * sign : BAE0ED1B1F3031F869CEFB5ECB96945D
         */

        private String partnerid; //微信支付分配的商户号
        private String prepayid;   //微信返回的支付交易会话ID
        private String timestamp;  //时间戳
        private String appid;
        private String noncestr; //随机字符串，不长于32位。推荐随机数生成算法
        @SerializedName("package")
        private String packageX; //
        private String sign; //签名

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
