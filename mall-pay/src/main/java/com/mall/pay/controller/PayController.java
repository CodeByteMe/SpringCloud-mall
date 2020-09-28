package com.mall.pay.controller;

import com.github.wxpay.sdk.WXPay;
import com.mall.common.config.MyConfig;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay")
@CrossOrigin
public class PayController {
    @RequestMapping(value = "/wxdlj",method = RequestMethod.POST)
    public ResultVO wxPay(String orderId, @RequestHeader(required = true) String token) throws Exception {
        Jws<Claims> jws = JWTUtil.Decrypt(token);
        String issuer = jws.getBody().getIssuer();
        if ("member".equals(issuer)){
            //1.向微信平台发起支付请求，获取支付短连接
            WXPay wxPay = new WXPay(new MyConfig());
            Map<String, String> data = new HashMap<>();
            data.put("body", "java");
            data.put("out_trade_no", orderId);
            data.put("device_info", "");
            data.put("fee_type", "CNY");
            data.put("total_fee", "1");
            data.put("spbill_create_ip", "123.12.12.123");

            //付款状态的回调接口
            data.put("notify_url", " http://dongyang.free.idcfengye.com/pay/callback");
            data.put("trade_type", "NATIVE");  // 此处指定为扫码支付
            data.put("product_id", "12");

            //发送支付请求，resp就是微信支付平台的返回的数据（包含支付短链接）
            Map<String, String> resp = wxPay.unifiedOrder(data);
            String payUrl = resp.get("code_url");
            System.out.println(payUrl);
            return new ResultVO(0,payUrl,orderId);
        }else {
            return new ResultVO(1, "支付请求失败，权限校验未通过",null);
        }
    }
}
