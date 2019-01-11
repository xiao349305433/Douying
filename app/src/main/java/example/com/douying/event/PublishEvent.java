package example.com.douying.event;

/**
 * Created by admin on 2018/12/3.
 */

public class PublishEvent {
    boolean IsPublish;

    public PublishEvent(boolean isPublish) {
        this.IsPublish = isPublish;
    }

    public boolean isPublish() {
        return IsPublish;
    }

    public void setPublish(boolean publish) {
        IsPublish = publish;
    }
}
