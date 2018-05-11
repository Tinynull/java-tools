package com.zhaoliang.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: zhaoliang
 * @create: 2018-05-10
 * @description:
 **/
public class Level {
    public static final Logger log = LoggerFactory.getLogger(SLForJ.class);

    public static void main(String[] args) {
        log.error("Error Message!");
        log.warn("Warn Message!");
        log.info("Info Message!");
        log.debug("Debug Message!");
        log.trace("Trace Message!");
    }
}
