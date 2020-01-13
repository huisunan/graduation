package com.hsn.mall.admin.interceptors;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hsn.mall.core.model.LogModel;
import com.hsn.mall.core.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Reference
    ILogService logService;

    /**
     * 访问日志配置织入点(controller中并且以SysAccessLog注解为标志)
     */
    @Pointcut("execution(* com..controller.*.*(..))")
    public void accessLogPointCut() {
    }

    @Around("accessLogPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long time = System.currentTimeMillis();
        String className =  joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String name = className + "." + methodName;
        Object proceed = joinPoint.proceed();
        time = System.currentTimeMillis() - time;
        log.info("[{}]执行耗时:{}ms!",name,time);
        HttpServletRequest request = getHttpServletRequest();
        String remoteHost = getRemoteHost(request);

        LogModel logModel = new LogModel();
        logModel.setUserId(1);
        logModel.setUserName("huisunan");
        logModel.setIp(remoteHost);
        logModel.setType(1);
        logModel.setAction(request.getRequestURI());
        logModel.setStatus(true);
        logModel.setResult(proceed.toString());
        logModel.setUseTime((int) time);

        logService.save(logModel);
        return proceed;
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

    /**
     * 获取目标主机的ip
     *
     * @param request request请求
     * @return ip地址
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }


}
