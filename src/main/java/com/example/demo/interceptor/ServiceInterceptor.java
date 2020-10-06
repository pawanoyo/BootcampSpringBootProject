package com.example.demo.interceptor;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class ServiceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{

        try{
            String remoteAddr = "";
            if (request != null) {
                remoteAddr = request.getHeader("TRUE_CLIENT_IP");
                if (remoteAddr == null || "".equals(remoteAddr)) {
                    remoteAddr = request.getRemoteAddr();
                }
            }
            //MDC.put("unique-id", UUID.randomUUID().toString());
            System.out.println("uuid:" + UUID.randomUUID().toString());
        } catch(Exception e){
            MDC.clear();
        }
        return true;
    }
}

