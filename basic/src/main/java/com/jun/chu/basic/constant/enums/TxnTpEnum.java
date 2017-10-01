package com.jun.chu.basic.constant.enums;

/**
 * Created by chujun on 2017/3/31.
 * 交易类型
 */
public enum TxnTpEnum {
    BATCHRECEIPT(3, "BATCHRECEIPT", "批量结汇", ""),
    PAYMENT(4, "PAYMENT", "跨境电商付汇", ""),
    RECEIPT(5, "RECEIPT", "跨境电商收汇", "CONV"),;
    private int state;
    private String code;
    private String desc;
    private String aBDesc;

    TxnTpEnum(int state, String code, String desc, String aBDesc) {
        this.state = state;
        this.code = code;
        this.desc = desc;
        this.aBDesc = aBDesc;
    }

    public static TxnTpEnum getByState(int state) {
        for (TxnTpEnum txnTpEnum : TxnTpEnum.values()) {
            if (txnTpEnum.getState() == state) {
                return txnTpEnum;
            }
        }
        return null;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getaBDesc() {
        return aBDesc;
    }

    public void setaBDesc(String aBDesc) {
        this.aBDesc = aBDesc;
    }
}