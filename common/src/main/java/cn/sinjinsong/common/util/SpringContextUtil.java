package cn.sinjinsong.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by SinjinSong on 2017/3/16.
 */
@Slf4j
@Component
public final class SpringContextUtil implements ApplicationContextAware {
    private SpringContextUtil() {
    }

    // Spring应用上下文环境  
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     *
     * @param applicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(String beanId) {
        T bean = null;
        try {
            if (StringUtils.isNotEmpty(StringUtils.trim(beanId))) {
                bean = (T) applicationContext.getBean(beanId);
            }
        } catch (NoSuchBeanDefinitionException e) {
            log.error("获取bean失败");
            return null;
        }
        return bean;
    }

    public static <T> T getBean(String... partName) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < partName.length; ++i) {
            sb.append(partName[i]);
            if (i != partName.length - 1) {
                sb.append(".");
            }
        }
        return getBean(sb.toString());
    }

}
