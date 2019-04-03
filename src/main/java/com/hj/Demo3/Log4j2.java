package com.hj.Demo3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author: hj
 * Date: 2019-04-03 13:52
 * Description: <log4j2日志使用>
 */
public class Log4j2 {

    private static Logger logger= LogManager.getLogger();

    public static void main(String[] args) {
        logger.debug("日志测试");
        logger.info("提示信息");
        logger.error("提示错误信息");
        logger.warn("警告信息");
        logger.trace("追踪信息");
    }
}
