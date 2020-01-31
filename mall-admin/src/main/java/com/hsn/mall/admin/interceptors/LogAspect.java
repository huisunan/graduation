package com.hsn.mall.admin.interceptors;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hsn.mall.admin.util.RequestUtil;
import com.hsn.mall.admin.util.SubjectUtil;
import com.hsn.mall.core.model.LogModel;
import com.hsn.mall.core.service.ILogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private ThreadLocal<LogModel> modelLocal = new ThreadLocal<LogModel>(){
        @Override
        protected LogModel initialValue() {
            return new LogModel();
        }
    };

    @Reference(async = true)
    private ILogService logService;

    /**
     * 访问日志配置织入点(controller中并且以SysAccessLog注解为标志)
     */
    @Pointcut("execution(* com..controller.*.*(..)) && !@annotation(com.hsn.mall.admin.annotation.LogExclude)")
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
        String remoteHost = RequestUtil.getRemoteHost(request);
        LogModel logModel = modelLocal.get();
        logModel.setUserId(SubjectUtil.getUserId());
        logModel.setUserName(SubjectUtil.getUserName());
        logModel.setIp(remoteHost);
        logModel.setType(1);
        logModel.setAction(request.getRequestURI());
        logModel.setStatus(true);
        logModel.setResult("");
        logModel.setUseTime((int) time);
        return proceed;
    }

    @AfterReturning("accessLogPointCut()")
    public void doAfterReturning(){
        this.save();
    }

    private void save(){
        LogModel logModel = modelLocal.get();
        new Thread(()->{
            logService.save(logModel);
        }).start();
        modelLocal.remove();
    }

    @AfterThrowing("accessLogPointCut()")
    public void doThrowing(){
        LogModel logModel = modelLocal.get();
        logModel.setStatus(false);
        this.save();
    }

    private HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }




}
