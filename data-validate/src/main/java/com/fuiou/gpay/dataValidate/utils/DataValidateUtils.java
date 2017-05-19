package com.fuiou.gpay.dataValidate.utils;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by chujun on 2017/4/28.
 * 依赖oval数据验证框架
 * TODO:cj to be optimized
 * 框架有个痛点，所有验证都需要验证字段是否为null
 * 字符为null，都表示验证通过 ,例如 @NotEmpty @MemberOf
 * 需要加上@NotNull，这个比较恶心，不知道怎么调整
 */
public class DataValidateUtils {
    final static Logger LOGGER = Logger.getLogger(DataValidateUtils.class);


    public static final String YYYYMMRegEp = "\\d{4}(0[1-9]|1[0-2])";

    public static final String YYYYMMDDRegEp = "\\d{4}(0[1-9]|1[0-2])((0[1-9])|((1|2)[0-9])|30|31)";

    public static final String HHMMSSRegEp = "([0-1]\\d|2[0-3]):([0-5]\\d):([0-5]\\d)";

    /**
     * 日期格式:YYYYMMDD HH:MM:SS
     */
    public static final String YYYYMMDDHHMMSSRegEp = YYYYMMDDRegEp + " " + HHMMSSRegEp;
    /**
     * null值错误信息
     */
    private static List<String> nullErrorMessage = new ArrayList<String>() {
        {
            add("value is null");
        }
    };

    /**
     * 验证错误则直接抛出异常
     *
     * @param t
     * @param <T>
     */
    public static <T> void validate(T t) {
        Validator validator = new Validator();
        // collect the constraint violations
        List<ConstraintViolation> violations = validator.validate(t);

        if (violations.size() > 0) {
            LOGGER.error("Object is invalid," + violations.get(0) + ",obj=" + t);
            throw new RuntimeException("数据格式不合法," + violations.get(0), null);
        }
    }

    /**
     * 验证错误则获取错误信息返回
     *
     * @param t
     * @param <T>
     * @return 暂时只返回List<String>,如有必要以后再扩展
     */
    public static <T> List<String> validateAndGetErrorMessages(T t) {
        if (null == t)
            return nullErrorMessage;
        Validator validator = new Validator();
        // collect the constraint violations
        List<ConstraintViolation> violations = validator.validate(t);

        if (violations.size() > 0) {
            return getErrorMessages(violations);
        }
        return Collections.emptyList();
    }

    private static List<String> getErrorMessages(List<ConstraintViolation> violations) {
        if (null == violations || 0 == violations.size()) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<String>();
        for (ConstraintViolation violation : violations) {
            result.add(violation.getMessage());
        }
        return result;
    }
}
