package com.fuiou.gpay.arch.datavalidate.bean.http;

import com.fuiou.gpay.arch.datavalidate.constant.Constants;
import com.fuiou.gpay.dataValidate.utils.DataValidateUtils;
import net.sf.oval.configuration.annotation.IsInvariant;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MemberOf;
import net.sf.oval.constraint.NotNull;

/**
 * Created by chujun on 2017/4/24.
 * 还原/明细文件登记结果通知
 * 中信->富友
 */
public class CefilresRequest {
    /**
     * 银行名称,字符型100,固定"中信银行"
     */
    private String bankName;

    /**
     * 第三方支付机构号,字符型 6,在中信银行开通跨境支付业务时分配的编号
     */
    @NotNull
    @MemberOf(value = {Constants.e3rdPayNo}, message = "与中信分配给富友的编号不符合")
    private String e3rdPayNo;

    /**
     * 银行发起时间,字符型17
     * 交易日期和时间 格式：YYYYMMDD HH:MM:SS
     */
    @NotNull
    @MatchPattern(pattern = {DataValidateUtils.YYYYMMDDHHMMSSRegEp}, message="日期格式不符合要求,YYYYMMDD HH:MM:SS")
    private String transTime;

    /**
     * 原文件名称
     */
    private String fileName;

    /**
     * 交易类型	字符型 4	原申请交易的交易类型。
     * JWFH：境外付汇明细
     * JWFT:境外付汇退款明细
     * SHMX：收汇明细
     * STMX：收汇退款明细
     * JGHR：结售汇登记明细
     * KHMX：客户基础信息
     */
    private String tranType;

    /**
     * 文件批次号
     */
    private String fileBatNo;


    /**
     * 文件处理结果	字符型 2	用于表示文件处理结果状态
     */
    private String fileStt;

    /**
     * 详细错误描述
     */
    private String errDesc;

    @IsInvariant
    @NotNull
    public String getHelloWorld() {
        return null;
    }

    @IsInvariant
    @NotNull
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getE3rdPayNo() {
        return e3rdPayNo;
    }

    public void setE3rdPayNo(String e3rdPayNo) {
        this.e3rdPayNo = e3rdPayNo;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getFileBatNo() {
        return fileBatNo;
    }

    public void setFileBatNo(String fileBatNo) {
        this.fileBatNo = fileBatNo;
    }

    public String getFileStt() {
        return fileStt;
    }

    public void setFileStt(String fileStt) {
        this.fileStt = fileStt;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }
}
