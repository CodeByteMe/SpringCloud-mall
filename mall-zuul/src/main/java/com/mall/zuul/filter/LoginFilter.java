package com.mall.zuul.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.util.JWTUtil;
import com.mall.common.vo.ResultVO;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * LoginFilter
 *
 * @Author BessCroft
 * @Date 2020/9/20 19:56
 */
@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 配置过滤类型，有四种不同生命周期的过滤器类型
     * 1. pre：路由之前
     * 2. routing：路由之时
     * 3. post：路由之后
     * 4. error：发送错误调用
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 配置过滤的顺序
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 配置是否需要过滤：true/需要，false/不需要
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 过滤器的具体业务代码
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();
        String uri = request.getRequestURI();
        try {
            if (!"OPTIONS".equalsIgnoreCase(request.getMethod())) {
                String token = request.getHeader("token");
                if (token == null) {
                    if (uri.endsWith("/auth/adminLogin") || uri.endsWith("/auth/memberLogin")
                        || uri.endsWith("/auth/register")||uri.endsWith("/product/allList")) {
                        // 放行
                        context.setSendZuulResponse(true);
                        context.setResponseStatusCode(200);
                        context.set("isSuccess", true);
                        return null;
                    } else {
                        request.setCharacterEncoding("utf-8");
                        response.setContentType("text/html;charset=utf-8");
                        context.setSendZuulResponse(false);
                        context.setResponseStatusCode(200);
                        response.setHeader("Access-Control-Allow-Origin","*");      //设置允许跨域
                        response.setHeader("Access-Control-Allow-Methods","*");     //设置允许多种请求方式
                        response.setHeader("Access-Control-Max-Age","3600");        //设置跨域缓存的最大时间
                        response.setHeader("Access-Control-Allow-Headers","*");     //设置允许携带header
                        response.setHeader("Access-Control-Allow-Credentials","*"); //设置允许携带cookie

                        ResultVO vo = new ResultVO(1, "请先登录！",null);
                        String s = new ObjectMapper().writeValueAsString(vo);
                        PrintWriter out = response.getWriter();
                        out.println(s);
                        out.flush();
                        out.close();
                        return null;
                    }
                } else {
                    Jws<Claims> jws = JWTUtil.Decrypt(token);
                    // 验证token
                    context.setSendZuulResponse(true);
                    context.setResponseStatusCode(200);
                    context.set("isSuccess", true);
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                request.setCharacterEncoding("utf-8");
                response.setContentType("text/html;charset=utf-8");
                context.setSendZuulResponse(false);
                context.setResponseStatusCode(200);
                response.setHeader("Access-Control-Allow-Origin","*");      //设置允许跨域
                response.setHeader("Access-Control-Allow-Methods","*");     //设置允许多种请求方式
                response.setHeader("Access-Control-Max-Age","3600");        //设置跨域缓存的最大时间
                response.setHeader("Access-Control-Allow-Headers","*");     //设置允许携带header
                response.setHeader("Access-Control-Allow-Credentials","*"); //设置允许携带cookie

                ResultVO vo = new ResultVO(1, "请先登录！",null);
                String s = new ObjectMapper().writeValueAsString(vo);
                PrintWriter out = response.getWriter();
                out.println(s);
                out.flush();
                out.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }
        return null;
    }
}