package com.mall.pay.controller;

import com.github.wxpay.sdk.WXPayUtil;
import com.mall.pay.service.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/pay")
public class PayCallbackController {

    @Resource
    private OrderService orderService;

    @RequestMapping("/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //1.接收微信返回的支付状态
        ServletInputStream inputStream = request.getInputStream();
        byte[] bs = new byte[1024];
        int len = -1;
        StringBuilder builder = new StringBuilder();
        while((len = inputStream.read(bs))!=-1){
            builder.append( new String(bs,0,len) );
        }
        String res = builder.toString();

        Map<String, String> map = WXPayUtil.xmlToMap(res);
        if(map.get("result_code").equalsIgnoreCase("success")) {
            //2.修改数据库订单状态为已支付
            String orderId = map.get("out_trade_no");
            String s = orderService.updateStatus(orderId);
            if (s.equals("true")) {
                //3. 推送消息到付款页面
                WebSocket.sendMessage(orderId, "success");
                //3.响应微信平台
                String str =  "<xml>" +
                        "   <return_code><![CDATA[" + map.get("return_code") + "]]></return_code>" +
                        "   <return_msg><![CDATA[OK]]></return_msg>" +
                        "   <appid><![CDATA[" + map.get("appid") + "]]></appid>" +
                        "   <mch_id><![CDATA[" + map.get("mch_id") + "]]></mch_id>" +
                        "   <nonce_str><![CDATA[" + map.get("nonce_str") + "]]></nonce_str>" +
                        "   <openid><![CDATA[" + map.get("openid") + "]]></openid>" +
                        "   <sign><![CDATA[" + map.get("sign") + "]]></sign>" +
                        "   <result_code><![CDATA[SUCCESS]]></result_code>" +
                        "  <prepay_id><![CDATA[wx201411101639507cbf6ffd8b0779950874]]></prepay_id>" +
                        "   <trade_type><![CDATA[" + map.get("trade_type") + "]]></trade_type>" +
                        "</xml>";
                PrintWriter out = response.getWriter();
                out.println(str);
                out.flush();
                out.close();
            }
        }
    }
}
