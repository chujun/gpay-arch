package com.jun.chu.arch.service.handler;

import com.jun.chu.arch.service.pattern.reponsibilitychain.SortReponsibilityChain;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author chujun
 * @date 2019-07-17 09:42
 */
@NoArgsConstructor
public class HandlerManager implements ApplicationContextAware, InitializingBean {
    private ApplicationContext context;
    private SortReponsibilityChain<Integer, Class<?>, IServiceHandler> chain;

    public void setChain(SortReponsibilityChain<Integer, Class<?>, IServiceHandler> chain) {
        this.chain = chain;
    }

    /*
     * get first handler of one type
     * */
    @SuppressWarnings("unchecked")
    public <T extends IServiceHandler> T getHandler(Class<T> type) {
        return (T) chain.getHandler(type);
    }

    @SuppressWarnings("unchecked")
    public <T extends IServiceHandler> T getHandler(Class<T> type, Integer order) {
        return (T) chain.getHandler(type, order);
    }

    /*
     * get the next handler of the handler
     * */
    @SuppressWarnings("unchecked")
    public <T extends IServiceHandler> T getNextHandler(T handler) {

        return (T) chain.getNextHandler(handler);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, IServiceHandler> handlers = context.getBeansOfType(IServiceHandler.class);
        for (IServiceHandler handler : handlers.values()) {
            chain.register(handler);
        }
    }
}
