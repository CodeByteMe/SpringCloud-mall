package com.mall.zuul.rallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * UserLoginFallbackProvider
 *
 * @Author BessCroft
 * @Date 2020/9/20 19:24
 */
/**
 * 1.创建回调类implements FallbackProvider
 * 2.getRoute方法返回回调服务的服务名“springcloud-customer1”
 * 3.实现fallbackResponse方法，如果请求服务失败，则返回指定的信息给调用者
 */
public class UserLoginFallbackProvider implements FallbackProvider {

    @Override
    public String getRoute() {
        //ServiceId，如果需要所有调用都支持回退，则 return "*" 或 return null
        return "user-login";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        ClientHttpResponse clientHttpResponse = new ClientHttpResponse() {
            /**
             * 网关向 api 服务请求失败了，但是消费者客户端向网关发起的请求是成功的，
             * 不应该把 api 的 404,500 等问题抛给客户端
             * 网关和 api 服务集群对于客户端来说是黑盒
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.OK.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return HttpStatus.OK.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = new HashMap<>();
                map.put("status",200);
                map.put("message","无法连接，请重试");
                return new ByteArrayInputStream(mapper.writeValueAsString(map).getBytes("UTF-8"));
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.add("Access-Control-Allow-Origin","*");      //设置允许跨域
                headers.add("Access-Control-Allow-Methods","*");     //设置允许多种请求方式
                headers.add("Access-Control-Max-Age","3600");        //设置跨域缓存的最大时间
                headers.add("Access-Control-Allow-Headers","*");     //设置允许携带header
                headers.add("Access-Control-Allow-Credentials","*"); //设置允许携带cookie
                // 和 getBody 中的内容编码一致
                headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
                return headers;
            }
        };
        return clientHttpResponse;
    }
}