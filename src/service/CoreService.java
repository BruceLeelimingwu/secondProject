package service;

/**
 * Created by limingwu on 2016/10/26.
 */

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import getdata.GetData;
import resp.TextMessage;
import util.MessageUtil;

public class CoreService {

    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {

            String respContent = "请求处理异常，请稍后尝试！";


            Map<String, String> requestMap = MessageUtil.parseXml(request);


            String fromUserName = requestMap.get("FromUserName");

            String toUserName = requestMap.get("ToUserName");

            String msgType = requestMap.get("MsgType");
            String myIP=requestMap.get("Content");

            TextMessage textMessage = new TextMessage();
            textMessage.setToUserName(fromUserName);
            textMessage.setFromUserName(toUserName);
            textMessage.setCreateTime(new Date().getTime());
            textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
            textMessage.setFuncFlag(0);
            //String myIP=(textMessage.getContent()).toString();

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

                GetData mydata=new GetData();
                respContent=mydata.getDatafromDB(myIP);
                mydata.destroy();
                //respContent = "您发送的是文本消息";
            }

            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {

                String eventType = requestMap.get("Event");

                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    respContent = "谢谢您的关注！\n"+"请输入需要查询的IP：";
                }

                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {

                }

                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
                }
            }

            textMessage.setContent(respContent);
            respMessage = MessageUtil.textMessageToXml(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return respMessage;
    }
}