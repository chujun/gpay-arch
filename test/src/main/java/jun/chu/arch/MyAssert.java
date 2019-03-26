package jun.chu.arch;

import org.junit.Assert;

import java.util.Objects;

/**
 * @author jun.chu
 * @date 2019-03-26 15:06
 */
public class MyAssert {
    public static void assertException(Exception e, Class exceptionClass) {
        if (e.getClass() == exceptionClass) {
            return;
        }
        Assert.fail("code should not run here " + e);
    }

    public static void assertException(Exception e, Class exceptionClass, String exceptionMessage) {
        if (e.getClass() == exceptionClass && Objects.equals(exceptionMessage, e.getMessage())) {
            return;
        }
        Assert.fail("code should not run here " + e);
    }

    public static <T> void assertException(FunWithException<T> fun, Class exceptionClass) {
        try {
            fun.execute();
        } catch (Exception e) {
            assertException(e, exceptionClass);
        }
    }

    public static <T> void assertException(FunWithException<T> fun, Class exceptionClass, String exceptionMessage) {
        try {
            fun.execute();
        } catch (Exception e) {
            assertException(e, exceptionClass, exceptionMessage);
        }
    }
}
