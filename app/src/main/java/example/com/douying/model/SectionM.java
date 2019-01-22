package example.com.douying.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by admin on 2019/1/11.
 */

public class SectionM extends SectionEntity<GetModelM.DataBean.ListBeanX.ListBean> {
    private int pid;

    public SectionM(boolean isHeader, String header,int pid) {
        super(isHeader, header);
        this.pid = pid;
    }

    public SectionM(GetModelM.DataBean.ListBeanX.ListBean listBean){
        super(listBean);
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }
}
