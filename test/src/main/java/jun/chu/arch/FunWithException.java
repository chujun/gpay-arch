package jun.chu.arch;

/**
 * Created by chujun on 2017/12/12.
 */
@FunctionalInterface
public interface FunWithException<T> {
    T execute() throws Exception;
}
