package com.wangp.learn.microserviceapigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class UserLoginZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        // 该过滤器需要执行
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        //编写业务逻辑
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            rc.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            rc.setResponseStatusCode(401);// 设置响应状态码
            rc.setResponseBody("token is empty!");// 设置响应状态码
            return null;
        }
        return null;
    }
}
