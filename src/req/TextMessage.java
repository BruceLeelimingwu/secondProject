package req;

/**
 * Created by limingwu on 2016/10/26.
 */

public class TextMessage extends BaseMessage{
    private String Content;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content=content;
    }
}