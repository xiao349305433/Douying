package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2019/1/18.
 */

public class GetCareM {


    /**
     * code : 200
     * data : {"care":[{"id":"26","pic":"https://wx.qlogo.cn/mmopen/vi_32/MYhGNAI2vAHqhGUzGV9RIvhWLuz3icfo6WAy0TSj3fvm1YZuARb8fKtNm8wWTfxojtaTCibYYpqjDDWFAhxy0tug/132","truename":"平星而论","count":"0","favorite":"1","care":"1"}],"off":[{"id":"24","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIia7lCMHXhqf00vhddjL1gicPQ/132","truename":"Bleach","count":"0","favorite":"0","care":"0"},{"id":"29","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ8bIRIpiagkQic3iaUQu684m1a9tPXf7NjcaR0QHVATD3XIgLTqNViahpmPg3wdc8LevXe3HMcWP7O7A/132","truename":"lemon-郭佩","count":"0","favorite":"0","care":"0"},{"id":"30","pic":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFzRMmBz9RLPhSbwUsPXjrOKJialbibWiaWhfJP2GrNLbBRfYJjWnBb0DicvDOgVkavIb10yG4Md5XFA/132","truename":"陈健","count":"0","favorite":"0","care":"0"},{"id":"31","pic":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erQP0AR5RCjGAEONjk9B3JwiatIxwTFt5pLCwx4N1NAicqkydJZViaPH9ZyILurRp1sdNiaIJiaLLWn89g/132","truename":"在路上","count":"0","favorite":"0","care":"0"},{"id":"33","pic":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTIIto6H1ic96t3qn1ee8tjOZKsg1o4yVJ2DxeRXzcmGHJibh2VzECEsHfOiaWVusm0adDrk0hibbJLlnA/132","truename":"山里人","count":"0","favorite":"0","care":"0"},{"id":"34","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/CialvTG9PclVNjK0xYicbjsCrl3Pc2LGTu7WvgET15VffyuxKZRlU1VvrwJeSnOx5V4ia4Oiab1hyLhh5a3BghIRzQ/132","truename":"嗯哼","count":"0","favorite":"1","care":"0"},{"id":"37","pic":"https://xch.lxx673.shop/Uploads/me.png","truename":"普通用户","count":"0","favorite":"0","care":"0"},{"id":"38","pic":"https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJHDQdLKBYo2nT6RiagJSXbUTQ4Au2HuHicMTXCAD3UpEt4OOVqEnkp9iaWibpc7NROuPKz5LGqXXV9lQ/132","truename":"WC女神的国王","count":"0","favorite":"0","care":"0"},{"id":"44","pic":"https://xch.lxx673.shop/Uploads/me.png","truename":"测试用户","count":"0","favorite":"0","care":"0"},{"id":"45","pic":"https://xch.lxx673.shop/Uploads/me.png","truename":"测试用户","count":"0","favorite":"0","care":"0"}],"favor":[]}
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
        private List<CareBean> care;
        private List<OffBean> off;
        private List<FavorBean> favor;

        public List<CareBean> getCare() {
            return care;
        }

        public void setCare(List<CareBean> care) {
            this.care = care;
        }

        public List<OffBean> getOff() {
            return off;
        }

        public void setOff(List<OffBean> off) {
            this.off = off;
        }

        public List<FavorBean> getFavor() {
            return favor;
        }

        public void setFavor(List<FavorBean> favor) {
            this.favor = favor;
        }

        public static class CareBean extends BaseBean {
            /**
             * id : 26
             * pic : https://wx.qlogo.cn/mmopen/vi_32/MYhGNAI2vAHqhGUzGV9RIvhWLuz3icfo6WAy0TSj3fvm1YZuARb8fKtNm8wWTfxojtaTCibYYpqjDDWFAhxy0tug/132
             * truename : 平星而论
             * count : 0
             * favorite : 1
             * care : 1
             */

        }

        public static class OffBean extends BaseBean {
            /**
             * id : 24
             * pic : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIia7lCMHXhqf00vhddjL1gicPQ/132
             * truename : Bleach
             * count : 0
             * favorite : 0
             * care : 0
             */
        }

        public static class FavorBean extends BaseBean{

        }

        public static class BaseBean{
            private String id;
            private String pic;
            private String truename;
            private String count;
            private String favorite;
            private String care;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public String getFavorite() {
                return favorite;
            }

            public void setFavorite(String favorite) {
                this.favorite = favorite;
            }

            public String getCare() {
                return care;
            }

            public void setCare(String care) {
                this.care = care;
            }


        }




    }
}
