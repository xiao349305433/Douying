package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2018/12/25.
 */

public class GetTicketM  {

    /**
     * code : 200
     * data : {"off":[{"id":"10","name":"50元代金券","credits":"500","img":"ticket/ticket4.png","type":"3","cost":"45"},{"id":"9","name":"5元代金券","credits":"200","img":"ticket/ticket3.png","type":"2","cost":null},{"id":"9","name":"5元代金券","credits":"200","img":"ticket/ticket3.png","type":"2","cost":null}],"on":[]}
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
        private List<OffBean> off;
        private List<OnBean> on;

        public List<OffBean> getOff() {
            return off;
        }

        public void setOff(List<OffBean> off) {
            this.off = off;
        }

        public List<OnBean> getOn() {
            return on;
        }

        public void setOn(List<OnBean> on) {
            this.on = on;
        }

        public static class OffBean extends BaseBean{

        }

        public static class OnBean extends BaseBean{

        }

        public static class BaseBean {
            /**
             * id : 10
             * name : 50元代金券
             * credits : 500
             * img : ticket/ticket4.png
             * type : 3
             * cost : 45
             * offon
             */

            private String id;
            private String name;
            private String credits;
            private String img;
            private String type;
            private String cost;
            private int offon;

            public int getOffon() {
                return offon;
            }

            public void setOffon(int offon) {
                this.offon = offon;
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

            public String getCredits() {
                return credits;
            }

            public void setCredits(String credits) {
                this.credits = credits;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }
        }



    }
}
