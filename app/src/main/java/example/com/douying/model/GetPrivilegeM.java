package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2019/1/10.
 */

public class GetPrivilegeM {


    /**
     * code : 200
     * data : {"privilege":[{"id":"3","name":"无广告观看","ids":"1,2,3,4,5","icon":"icon/privilege.png","detail":"视频观看全程无广告"}],"grade":"1","active":"0"}
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
         * privilege : [{"id":"3","name":"无广告观看","ids":"1,2,3,4,5","icon":"icon/privilege.png","detail":"视频观看全程无广告"}]
         * grade : 1
         * active : 0
         */

        private String grade;
        private String active;
        private List<PrivilegeBean> privilege;

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public List<PrivilegeBean> getPrivilege() {
            return privilege;
        }

        public void setPrivilege(List<PrivilegeBean> privilege) {
            this.privilege = privilege;
        }

        public static class PrivilegeBean {
            /**
             * id : 3
             * name : 无广告观看
             * ids : 1,2,3,4,5
             * icon : icon/privilege.png
             * detail : 视频观看全程无广告
             */

            private String id;
            private String name;
            private String ids;
            private String icon;
            private String detail;

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

            public String getIds() {
                return ids;
            }

            public void setIds(String ids) {
                this.ids = ids;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }
        }
    }
}
