package com.jun.chu.arch.exception;


import com.jun.chu.arch.constant.BaseExceptionMessageConstant;

/**
 * Created by chujun on 2017/3/31.
 * 每个系统集成这个类扩展异常
 */
public class BaseExceptionBuilder {
    public static BusinessException buildBuninessException(BaseExceptionMessageConstant exceptionMessageConstant){
        return new BusinessException(exceptionMessageConstant.getCode(),exceptionMessageConstant.getDesc());
    }

    public static BusinessException buildBuninessException(BaseExceptionMessageConstant exceptionMessageConstant, Throwable e){
        return new BusinessException(exceptionMessageConstant.getCode(),exceptionMessageConstant.getDesc(),e);
    }

    public static BusinessException buildBuninessException(Throwable e){
        return new BusinessException(BaseExceptionMessageConstant.BUSINESS_EXCEPTION.getCode(),BaseExceptionMessageConstant.BUSINESS_EXCEPTION.getDesc(),e);
    }



    public static SystemException buildSystemException(BaseExceptionMessageConstant exceptionMessageConstant){
        return new SystemException(exceptionMessageConstant.getCode(),exceptionMessageConstant.getDesc());
    }

    public static SystemException buildSystemException(BaseExceptionMessageConstant exceptionMessageConstant, Throwable e){
        return new SystemException(exceptionMessageConstant.getCode(),exceptionMessageConstant.getDesc(),e);
    }

    public static SystemException buildSystemException(String message, Throwable e){
        return new SystemException(message,e);
    }

    public static SystemException buildSystemException(Throwable e){
        return new SystemException(BaseExceptionMessageConstant.SYSTEM_EXCEPTION.getCode(),BaseExceptionMessageConstant.SYSTEM_EXCEPTION.getDesc(),e);
    }
}
