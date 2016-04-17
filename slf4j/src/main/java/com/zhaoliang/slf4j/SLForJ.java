package com.zhaoliang.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLForJ {
	public final Logger logger = LoggerFactory.getLogger(SLForJ.class);
	Integer temp;
	Integer oldT;

	public void setTemperature(Integer temperature) {
		oldT = temp;
		temp = temperature;
		Object[] objs = { new java.util.Date().toString(), temp, oldT };
		logger.info(
				"Today is {}, Temperature set to {}. Old temperature was {}.",
				objs);
		if (temperature.intValue() > 50) {
			logger.warn("Temperature({}) has risen above 50 degrees.", temp);
		}
	}

	public static void main(String[] args) {
		SLForJ wombat = new SLForJ();
		wombat.setTemperature(10);
		wombat.setTemperature(60);
	}
}
