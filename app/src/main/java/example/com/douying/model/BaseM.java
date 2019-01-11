package example.com.douying.model;

/**
 * Created by admin on 2018/10/31.
 */

public class BaseM {

    /**
     * code : 200
     * data : 11
     * info : 请求成功
     * status : 2
     */

    private int code;
    private String info;
    private int status;

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
}
