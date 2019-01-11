package example.com.douying.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/9/12.
 */

public  class PartBean implements Parcelable {

    /**
     * pid : 1
     * play : section1/video/titanic.mp4
     * role : [{"rid":1,"name":"Jack","upic":"section1/role/Jack.png"},{"rid":2,"name":"Rose","upic":"section1/role/Rose.png"}]
     * count : 200
     * subtitle : 泰坦尼克号
     * subposter : section1/part/titanic582x582.png
     * playposter : section1/part/titanic800x494.png
     */

    private int pid;
    private String play;
    private String count;
    private String subtitle;
    private String subposter;
    private String playposter;
    private List<PartBean.RoleBean> role;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getSubposter() {
        return subposter;
    }

    public void setSubposter(String subposter) {
        this.subposter = subposter;
    }

    public String getPlayposter() {
        return playposter;
    }

    public void setPlayposter(String playposter) {
        this.playposter = playposter;
    }

    public List<PartBean.RoleBean> getRole() {
        return role;
    }

    public void setRole(List<PartBean.RoleBean> role) {
        this.role = role;
    }

    public static class RoleBean implements Parcelable {
        /**
         * rid : 1
         * name : Jack
         * upic : section1/role/Jack.png
         */

        private int rid;
        private String name;
        private String upic;
        private boolean ischoose;

        public boolean isIschoose() {
            return ischoose;
        }

        public void setIschoose(boolean ischoose) {
            this.ischoose = ischoose;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUpic() {
            return upic;
        }

        public void setUpic(String upic) {
            this.upic = upic;
        }

        public RoleBean() {
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.rid);
            dest.writeString(this.name);
            dest.writeString(this.upic);
            dest.writeByte(this.ischoose ? (byte) 1 : (byte) 0);
        }

        protected RoleBean(Parcel in) {
            this.rid = in.readInt();
            this.name = in.readString();
            this.upic = in.readString();
            this.ischoose = in.readByte() != 0;
        }

        public static final Creator<RoleBean> CREATOR = new Creator<RoleBean>() {
            @Override
            public RoleBean createFromParcel(Parcel source) {
                return new RoleBean(source);
            }

            @Override
            public RoleBean[] newArray(int size) {
                return new RoleBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pid);
        dest.writeString(this.play);
        dest.writeString(this.count);
        dest.writeString(this.subtitle);
        dest.writeString(this.subposter);
        dest.writeString(this.playposter);
        dest.writeList(this.role);
    }

    public PartBean() {
    }

    protected PartBean(Parcel in) {
        this.pid = in.readInt();
        this.play = in.readString();
        this.count = in.readString();
        this.subtitle = in.readString();
        this.subposter = in.readString();
        this.playposter = in.readString();
        this.role = new ArrayList<RoleBean>();
        in.readList(this.role, RoleBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<PartBean> CREATOR = new Parcelable.Creator<PartBean>() {
        @Override
        public PartBean createFromParcel(Parcel source) {
            return new PartBean(source);
        }

        @Override
        public PartBean[] newArray(int size) {
            return new PartBean[size];
        }
    };
}
