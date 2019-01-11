package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by admin on 2018/11/19.
 */

public class GetArticlesListM {


    /**
     * code : 200
     * data : [{"id":"4","title":"第四次玩","type":"1","imgs":"product/poster/zuopin1_800x494.png","add_time":"2018-11-19 11:18:01","praise":"0","status":"2","content":"感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好，感觉还好感","video":"","is_reply":"2","credits":null,"uid":"24","forward":"0","praise_id":null,"forward_id":null,"truename":"Bleach","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIiaQQvvBIxaZwvL27fOyXiaaeQ/132"},{"id":"3","title":"第三次玩","type":"1","imgs":"","add_time":"2018-11-19 11:17:57","praise":"0","status":"2","content":"感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好感觉","video":"product/video/zuopin1.mp4","is_reply":"2","credits":null,"uid":"24","forward":"0","praise_id":null,"forward_id":null,"truename":"Bleach","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIiaQQvvBIxaZwvL27fOyXiaaeQ/132"},{"id":"2","title":"第二次玩","type":"1","imgs":"product/poster/zuopin1_800x494.png@product/poster/zuopin1_800x494.png@product/poster/zuopin1_800x494.png@product/poster/zuopin1_800x494.png","add_time":"2018-11-19 15:18:17","praise":"0","status":"2","content":"感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还","video":null,"is_reply":"2","credits":null,"uid":"24","forward":"0","praise_id":null,"forward_id":null,"truename":"Bleach","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIiaQQvvBIxaZwvL27fOyXiaaeQ/132"},{"id":"1","title":"第一次玩","type":"1","imgs":"","add_time":"2018-11-19 15:18:17","praise":"0","status":"2","content":"一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一二三四五六七八九十一","video":null,"is_reply":"2","credits":null,"uid":"24","forward":"0","praise_id":null,"forward_id":null,"truename":"Bleach","pic":"http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIiaQQvvBIxaZwvL27fOyXiaaeQ/132"}]
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

    public static class DataBean implements Parcelable {
        /**
         * id : 4
         * title : 第四次玩
         * type : 1
         * imgs : product/poster/zuopin1_800x494.png
         * add_time : 2018-11-19 11:18:01
         * praise : 0
         * status : 2
         * content : 感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好感觉还好感觉还好，感觉还好感觉还好感觉还好感觉还好，感觉还好感
         * video :
         * is_reply : 2
         * credits : null
         * uid : 24
         * forward : 0
         * praise_id : null
         * forward_id : null
         * truename : Bleach
         * pic : http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eool2POWsibdEERghjJO0YIXPObFEYTtqdGkAdvrByIic0RxlspqgrvIiaQQvvBIxaZwvL27fOyXiaaeQ/132
         * reply_count  评论数量
         * IsLike   是否点赞
         */

        private int id;
        private String title;
        private String type;
        private String imgs;
        private String add_time;
        private int praise;
        private String status;
        private String content;
        private String video;
        private String is_reply;
        private String credits;
        private int uid;
        private String forward;
        private String praise_id;
        private String forward_id;
        private String truename;
        private String pic;
        private int reply_count;
        private String is_praise;

        public String getIs_praise() {
            return is_praise;
        }

        public void setIs_praise(String is_praise) {
            this.is_praise = is_praise;
        }

        public int getReply_count() {
            return reply_count;
        }

        public void setReply_count(int reply_count) {
            this.reply_count = reply_count;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImgs() {
            return imgs;
        }

        public void setImgs(String imgs) {
            this.imgs = imgs;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getIs_reply() {
            return is_reply;
        }

        public void setIs_reply(String is_reply) {
            this.is_reply = is_reply;
        }

        public String getCredits() {
            return credits;
        }

        public void setCredits(String credits) {
            this.credits = credits;
        }

        public String getPraise_id() {
            return praise_id;
        }

        public void setPraise_id(String praise_id) {
            this.praise_id = praise_id;
        }

        public String getForward_id() {
            return forward_id;
        }

        public void setForward_id(String forward_id) {
            this.forward_id = forward_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getForward() {
            return forward;
        }

        public void setForward(String forward) {
            this.forward = forward;
        }


        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }


        public DataBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.imgs);
            dest.writeString(this.add_time);
            dest.writeInt(this.praise);
            dest.writeString(this.status);
            dest.writeString(this.content);
            dest.writeString(this.video);
            dest.writeString(this.is_reply);
            dest.writeString(this.credits);
            dest.writeInt(this.uid);
            dest.writeString(this.forward);
            dest.writeString(this.praise_id);
            dest.writeString(this.forward_id);
            dest.writeString(this.truename);
            dest.writeString(this.pic);
            dest.writeInt(this.reply_count);
            dest.writeString(this.is_praise );
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.title = in.readString();
            this.type = in.readString();
            this.imgs = in.readString();
            this.add_time = in.readString();
            this.praise = in.readInt();
            this.status = in.readString();
            this.content = in.readString();
            this.video = in.readString();
            this.is_reply = in.readString();
            this.credits = in.readString();
            this.uid = in.readInt();
            this.forward = in.readString();
            this.praise_id = in.readString();
            this.forward_id = in.readString();
            this.truename = in.readString();
            this.pic = in.readString();
            this.reply_count = in.readInt();
            this.is_praise = in.readString() ;
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }
}
