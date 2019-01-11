package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2018/12/14.
 */

public class GetTaskM {

    /**
     * code : 200
     * data : [{"id":"1","name":"完成一次作品","credits":"100","type":"1","step":"1","tip":"去拍摄","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"2","name":"关注3位好友","credits":"50","type":"1","step":"3","tip":"去关注","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"3","name":"发布一次素材","credits":"100","type":"1","step":"1","tip":"去发布","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"4","name":"论坛完成一次发帖","credits":"50","type":"1","step":"1","tip":"去发布","icon":"icon.png","ids":"","person":0,"count":"1","complete":2},{"id":"5","name":"与他人互动3次(回复/点赞）","credits":"50","type":"1","step":"3","tip":"去完成","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"6","name":"每日签到","credits":"50","type":"1","step":"1","tip":"去签到","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"7","name":"转发/分享一次作品","credits":"50","type":"1","step":"1","tip":"去分享","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"8","name":"商城购物/积分换购一次","credits":"50","type":"1","step":"1","tip":"去逛逛","icon":"icon.png","ids":"","person":0,"count":"0","complete":1},{"id":"9","name":"意见反馈一次","credits":"50","type":"1","step":"1","tip":"去反馈","icon":"icon.png","ids":"","person":0,"count":"0","complete":1}]
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
         * id : 1
         * name : 完成一次作品
         * credits : 100
         * type : 1
         * step : 1
         * tip : 去拍摄
         * icon : icon.png
         * ids :
         * person : 0
         * count : 0
         * complete : 1
         */

        private String id;
        private String name;
        private String credits;
        private String type;
        private String step;
        private String tip;
        private String icon;
        private String ids;
        private int person;
        private String count;
        private int complete;

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

        public String getCredits() {
            return credits;
        }

        public void setCredits(String credits) {
            this.credits = credits;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public int getPerson() {
            return person;
        }

        public void setPerson(int person) {
            this.person = person;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getComplete() {
            return complete;
        }

        public void setComplete(int complete) {
            this.complete = complete;
        }
    }
}
