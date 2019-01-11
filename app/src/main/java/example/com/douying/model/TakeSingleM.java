package example.com.douying.model;

import java.util.List;

/**
 * Created by admin on 2018/10/17.
 */

public class TakeSingleM {

    /**
     * code : 202
     * data : ["ok"]
     * info : 更新成功
     * status : 2
     */

    private int code;
    private String info;
    private int status;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
