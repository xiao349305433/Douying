package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public class FaceM {


    private List<ModelBean> model;

    public List<ModelBean> getModel() {
        return model;
    }

    public void setModel(List<ModelBean> model) {
        this.model = model;
    }

    public static class ModelBean {
        /**
         * pid : 1
         * list : [{"url":"20190115/5c3d59d33600c.jpeg"},{"url":"20190115/5c3d59d3363c8.jpeg"},{"url":"20190115/5c3d7bd358f94.jpeg"}]
         * title : 第二天
         * is_default : 2
         */

        private int pid;
        private String title;
        private int is_default;
        private List<ListBean> list;

        public int getPid() {
            return pid;
        }

        public void setPid(int pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * url : 20190115/5c3d59d33600c.jpeg
             */

            private String url;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
