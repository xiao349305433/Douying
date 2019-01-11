package example.com.douying.model;

import java.util.List;

public class HomesModel {


    /**
     * code : 200
     * data : [{"id":"1","part":"[{\"pid\": 1, \"play\": \"section1/video/titanic.mp4\", \"role\": [{\"rid\": 1, \"name\": \"Jack\", \"upic\": \"section1/role/Jack.png\"}, {\"rid\": 2, \"name\": \"Rose\", \"upic\": \"section1/role/Rose.png\"}], \"count\": \"200\", \"subtitle\": \"泰坦尼克号\", \"subposter\": \"section1/part/titanic582x582.png\", \"playposter\": \"section1/part/titanic800x494.png\"}]","title":"泰坦尼克号","poster":"section1/titanic800x500.png","hot":"300"},{"id":"7","part":"[{\"pid\": 1, \"play\": \"section2/video/gongfu1.mp4\", \"role\": [{\"rid\": 1, \"name\": \"包租婆\", \"upic\": \"section2/role/baozupo.png\"}, {\"rid\": 2, \"name\": \"包租公\", \"upic\": \"section2/role/baozugong.png\"}], \"count\": \"200\", \"subtitle\": \"功夫\", \"subposter\": \"section2/part/gongfu1_800x800.png\", \"playposter\": \"section2/part/gongfu1_800x494.png\"}, {\"pid\": 2, \"play\": \"section2/video/gongfu2.mp4\", \"role\": [{\"rid\": 1, \"name\": \"琛哥\", \"upic\": \"section2/role/chengge.png\"}, {\"rid\": 2, \"name\": \"酱爆\", \"upic\": \"section2/role/jiangbao.png\"}], \"count\": \"300\", \"subtitle\": \"功夫\", \"subposter\": \"section2/part/gongfu2_600x600.png\", \"playposter\": \"section2/part/gongfu2_800x494.png\"}, {\"pid\": 3, \"play\": \"section2/video/gongfu3.mp4\", \"role\": [{\"rid\": 1, \"name\": \"星仔\", \"upic\": \"section2/role/xingzai.png\"}, {\"rid\": 2, \"name\": \"芳儿\", \"upic\": \"section2/role/fanger.png\"}, {\"rid\": 3, \"name\": \"火云邪神\", \"upic\": \"section2/role/huoyunxs.png\"}], \"count\": \"400\", \"subtitle\": \"功夫\", \"subposter\": \"section2/part/gongfu3_502x502.png\", \"playposter\": \"section2/part/gongfu3_800x494.png\"}]","title":"功夫","poster":"section2/gongfu800x500.png","hot":"800"}]
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
         * part : [{"pid": 1, "play": "section1/video/titanic.mp4", "role": [{"rid": 1, "name": "Jack", "upic": "section1/role/Jack.png"}, {"rid": 2, "name": "Rose", "upic": "section1/role/Rose.png"}], "count": "200", "subtitle": "泰坦尼克号", "subposter": "section1/part/titanic582x582.png", "playposter": "section1/part/titanic800x494.png"}]
         * title : 泰坦尼克号
         * poster : section1/titanic800x500.png
         * hot : 300
         */

        private String id;
        private String part;
     //   private List<PartDean> part;
        private String title;
        private String poster;
        private String hot;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

//        public void setParts(List<PartDean> parts){
//            this.part=parts;
//        }
//        public  List<PartDean>getParts(){
//            return  part;
//        }

        public String getPart() {
            return part;
        }

        public void setPart(String part) {
            this.part = part;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPoster() {
            return poster;
        }

        public void setPoster(String poster) {
            this.poster = poster;
        }

        public String getHot() {
            return hot;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }


//        public static class PartBean{
//            /**
//             * pid : 1
//             * play : section1/video/titanic.mp4
//             * role : [{"rid":1,"name":"Jack","upic":"section1/role/Jack.png"},{"rid":2,"name":"Rose","upic":"section1/role/Rose.png"}]
//             * count : 200
//             * subtitle : 泰坦尼克号
//             * subposter : section1/part/titanic582x582.png
//             * playposter : section1/part/titanic800x494.png
//             */
//
//            private int pid;
//            private String play;
//            private String count;
//            private String subtitle;
//            private String subposter;
//            private String playposter;
//            private List<RoleBean> role;
//
//            public int getPid() {
//                return pid;
//            }
//
//            public void setPid(int pid) {
//                this.pid = pid;
//            }
//
//            public String getPlay() {
//                return play;
//            }
//
//            public void setPlay(String play) {
//                this.play = play;
//            }
//
//            public String getCount() {
//                return count;
//            }
//
//            public void setCount(String count) {
//                this.count = count;
//            }
//
//            public String getSubtitle() {
//                return subtitle;
//            }
//
//            public void setSubtitle(String subtitle) {
//                this.subtitle = subtitle;
//            }
//
//            public String getSubposter() {
//                return subposter;
//            }
//
//            public void setSubposter(String subposter) {
//                this.subposter = subposter;
//            }
//
//            public String getPlayposter() {
//                return playposter;
//            }
//
//            public void setPlayposter(String playposter) {
//                this.playposter = playposter;
//            }
//
//            public List<RoleBean> getRole() {
//                return role;
//            }
//
//            public void setRole(List<RoleBean> role) {
//                this.role = role;
//            }
//
//            public static class RoleBean {
//                /**
//                 * rid : 1
//                 * name : Jack
//                 * upic : section1/role/Jack.png
//                 */
//
//                private int rid;
//                private String name;
//                private String upic;
//
//                public int getRid() {
//                    return rid;
//                }
//
//                public void setRid(int rid) {
//                    this.rid = rid;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getUpic() {
//                    return upic;
//                }
//
//                public void setUpic(String upic) {
//                    this.upic = upic;
//                }
//            }
//        }


    }





}
