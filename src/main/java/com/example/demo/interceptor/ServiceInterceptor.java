package com.example.demo.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class ServiceInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(ServiceInterceptor.class);
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
            MDC.put("unique-id", UUID.randomUUID().toString());
            logger.info("Client(true-client-ip: " + remoteAddr + ") has a request with UUID: ");
        } catch(Exception e){
            MDC.clear();
        }
        return true;
    }
}

