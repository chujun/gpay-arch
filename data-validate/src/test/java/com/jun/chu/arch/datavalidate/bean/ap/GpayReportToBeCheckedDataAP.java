package com.jun.chu.arch.datavalidate.bean.ap;

import com.jun.chu.dataValidate.utils.DataValidateUtils;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

/**
 * Created by chujun on 2017/5/18.
 */
public class GpayReportToBeCheckedDataAP {
    /**
     * 指定月份,格式YYYYMM,例如201705
     */
    @NotNull
    @MatchPattern(pattern = {DataValidateUtils.YYYYMMRegEp}, message="日期格式不符合要求,YYYYMMRegEp")
    private String month;

    /**
     * 是否为付汇
     * null:为空
     * true:付汇
     * false:收汇
     */
    private Boolean payment;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Boolean getPayment() {
        return payment;
    }

    public void setPayment(Boolean payment) {
        this.payment = payment;
    }
}
