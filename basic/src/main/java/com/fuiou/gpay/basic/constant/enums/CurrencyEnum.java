package com.fuiou.gpay.basic.constant.enums;



/**
 * Created by chujun on 2017/3/31.
 */
public enum CurrencyEnum {
    USD("USD","美元"),
    EUR("EUR","欧元"),
    HKD("HKD","港币"),
    GBP("GBP","英镑"),
    NZD("NZD","新西兰元"),
    JPY("JPY","日元"),
    KRW("KRW","韩元"),
    AUD("AUD","澳大利亚元"),
    CNY("CNY","人民币"),
    ;
    private String value;
    private String desc;

    CurrencyEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public static CurrencyEnum getByValue(String value) {
        if(null == value || "".equals(value)){
            return null;
        }
        for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
            if (currencyEnum.getValue().equals(value)) {
                return currencyEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}