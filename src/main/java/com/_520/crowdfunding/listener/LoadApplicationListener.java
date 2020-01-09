package com._520.crowdfunding.listener;

import com._520.crowdfunding.common.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *  创建application作用域时的监听器
 */
public class LoadApplicationListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(LoadApplicationListener.class);

    // 初始化
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 获取当前的上下文路径
        ServletContext servletContext = sce.getServletContext();
        String contextPath = servletContext.getContextPath();
        logger.debug("application = {}", servletContext.getContextPath());
        // 将上下文路径存到 application作用域中
        servletContext.setAttribute(Const.PATH, contextPath);
    }
}
