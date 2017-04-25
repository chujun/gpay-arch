package com.fuiou.gpay.constant;

/**
 * Created by chujun on 2017/4/1.
 */
public enum BaseExceptionMessageConstant {
    SYSTEM_EXCEPTION("SYSTEM_EXCEPTION","系统异常"),
    BUSINESS_EXCEPTION("BUSINESS_EXCEPTION","业务异常"),
    MISSING_PARAM("MISSING_PARAM","缺少参数"),

    ;
    private String code;
    private String desc;

    BaseExceptionMessageConstant(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
