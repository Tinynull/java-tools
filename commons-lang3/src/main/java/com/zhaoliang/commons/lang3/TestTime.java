package com.zhaoliang.commons.lang3;

import java.util.Date;

import org.apache.commons.lang3.time.DurationFormatUtils;

public class TestTime {
	private static long one_second = 1000L;
	private static long one_minute = one_second * 60L;
	private static long one_hour = one_minute * 60L;

	private static long testValue = one_hour + 31 * one_minute + 25
			* one_second;

	public static void main(String[] args) {

		// 1:31:25.000
		String tt = DurationFormatUtils.formatDurationHMS(testValue);

		// P0Y0M3DT20H37M33.463S
		String kk = DurationFormatUtils.formatDurationISO(333453463L);

		// 00年00月00日01:31:25
		String ll = DurationFormatUtils.formatDuration(testValue,
				"yy年MM月dd日HH:mm:ss");

		// 0年0月0日1:31:25
		String mm = DurationFormatUtils.formatDuration(testValue,
				"yy年MM月dd日HH:mm:ss", false);

		// 0 days 1 hour 31 minutes 25 seconds
		String nn = DurationFormatUtils.formatDurationWords(testValue, false,
				false);

		// 1 hour 31 minutes 25 seconds
		String nn1 = DurationFormatUtils.formatDurationWords(testValue, true,
				false);

		// 0 days 1 hour 31 minutes 25 seconds
		String nn2 = DurationFormatUtils.formatDurationWords(testValue, false,
				true);

		// 1 hour 31 minutes 25 seconds
		String nn3 = DurationFormatUtils.formatDurationWords(testValue, true,
				true);

		System.out.println(tt);
		System.out.println(kk);
		System.out.println(ll);
		System.out.println(mm);
		System.out.println(nn);
		System.out.println(nn1);
		System.out.println(nn2);
		System.out.println(nn3);
		System.out.println(new Date(1438917765448L));
		System.out.println(System.identityHashCode(tt));
	}
}
