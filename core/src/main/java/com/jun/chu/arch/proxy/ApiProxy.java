package com.jun.chu.arch.proxy;

import com.jun.chu.arch.exception.BusinessException;
import com.jun.chu.arch.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by chujun on 2017/8/23.
 */
@Aspect
@Slf4j
public class ApiProxy {
    public ApiProxy() {
    }

    //spring aop private方法不会拦截
    @Around("execution(* com..*.api.impl..*.*(..)) or execution(* com..*.service.api.impl..*.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            log.info("调用:" + calledMethodSignatureName(point) + "入参:" + JsonUtils.toJson(point.getArgs()));
            validateAndHandleParam(point.getArgs());
            Object result = point.proceed();
            log.info("调用:" + calledMethodSignatureName(point) + "耗时{}ms,返回结果:{}", (System.currentTimeMillis() - start),
                    JsonUtils.toJson(result));
            return result;
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                BusinessException be = (BusinessException) e;
                log.error("{}调用失败!【业务异常-参数:{}】code={},msg={}", calledMethodSignatureName(point),
                        JsonUtils.toJson(point.getArgs()), be.getErrorCode(), be.getErrorMsg());
            } else {
                log.error(calledMethodSignatureName(point) + "调用失败!【系统异常-参数:" + JsonUtils.toJson(point.getArgs()) + "】",
                        e);
            }
            throw e;
        }
    }

    private String calledMethodSignatureName(ProceedingJoinPoint point) {
        return point.getSignature().getDeclaringTypeName() + "$" + point.getSignature().getName();
    }

    private void validateAndHandleParam(Object[] args) throws BusinessException {
//        if (null == args || 0 == args.length || !(args[0] instanceof BaseReq)) {
//            throw BaseExceptionBuilder.buildBuninessException(BaseExceptionMessageConstant.PARAM_ILLEGAL);
//        }
//        BaseReq baseReq = (BaseReq) args[0];
//        if (null == baseReq || StringUtils.isEmpty(baseReq.getCustId())) {
//            throw ExceptionBuilder.buildBusinessException(ErrorMessageEnum.PARAM_ILLEGAL);
//        }
//
//        //用户ID验证
//        UserInfoDTO user = UserInfoHelper.getUserById(baseReq.getCustId());
//        if (null == user) {
//            throw ExceptionBuilder.buildBusinessException(ErrorMessageEnum.USER_NOT_EXIST);
//        }
//
//        //2.activity设置当前用户,activity底层会使用到该信息
//        Authentication.setAuthenticatedUserId(baseReq.getCustId());
    }
}
