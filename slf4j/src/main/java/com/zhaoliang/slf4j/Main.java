package com.zhaoliang.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 */
public class Main {
    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("hello");
        logger.info("Temperature set to {}. Old temperature was {}.", 37, 40);
        logger.debug("Temperature set to {}. Old temperature was {}.", 37, 40);
        logger.isDebugEnabled();
        System.out.print("hello world");
        logger.debug("Temperature set to {}. Old temperature was {}.", 37, 40);

        Exception exception = new Exception("error");
        logger.error(exception.getMessage(), exception);


        if (logger.isTraceEnabled()) {
            logger.trace("message");
        }
    }


}
