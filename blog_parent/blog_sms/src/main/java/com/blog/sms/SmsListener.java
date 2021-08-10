package com.blog.sms;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 接收MQ的消息
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.tempCode}")
    private String tempCode; //短信模块的code

    @Value("${aliyun.sms.signName}")
    private String signName; //短信签名
    /**
     * 消息处理
     */
    @RabbitHandler
    public void receiveMsg(Map<String,String> map){
        System.out.println("手机号码:"+map.get("mobile"));
        System.out.println("验证码:"+map.get("code"));

        try {
            //发送短信
            SendSmsResponse sendSmsResponse = smsUtil.sendSms(map.get("mobile"), tempCode, signName, "{\"code\":\"" + map.get("code") + "\"}");
            if(sendSmsResponse.getCode().equals("OK")){
                System.out.println("短信发送成功");
            }else {
                System.out.println("短信发送失败:"+sendSmsResponse.getCode());
            }
        } catch (ClientException e) {
            System.out.println("发送短信失败:"+e.getMessage());
        }
    }
}
