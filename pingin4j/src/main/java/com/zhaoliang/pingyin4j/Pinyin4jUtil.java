package com.zhaoliang.pingyin4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin4jUtil {

	/**
	 * 将汉字转换为全拼
	 * 
	 * @param src 汉字字符串。
	 * @return String
	 */
	private static String getPinYin(String src) {
		char[] t1 = src.toCharArray();
		String[] t2;
		// 设置汉字拼音输出的格式
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		t3.setVCharType(HanyuPinyinVCharType.WITH_V);
		String t4 = "";
		try {
			for (char aT1 : t1) {
				// 判断能否为汉字字符
				if (Character.toString(aT1).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(aT1, t3);// 将汉字的几种全拼都存到t2数组中
					t4 += t2[0];// 取出该汉字全拼的第一种读音并连接到字符串t4后
				} else {
					// 如果不是汉字字符，间接取出字符并连接到字符串t4后
					t4 += Character.toString(aT1);
				}
			}
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			e.printStackTrace();
		}
		return t4;
	}

	/**
	 * 提取每个汉字的首字母
	 * 
	 * @param str 中文字符串。
	 * @return String
	 */
	private static String getPinYinHeadChar(String str) {
		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// 提取汉字的首字母
			String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
			if (pinyinArray != null) {
				convert += pinyinArray[0].charAt(0);
			} else {
				convert += word;
			}
		}
		return convert;
	}

	/**
	 * 将字符串转换成ASCII码
	 * 
	 * @param cnStr 中文字符串。
	 * @return String
	 */
	private static String getCnASCII(String cnStr) {
		StringBuilder strBuf = new StringBuilder();
		// 将字符串转换成字节序列
		byte[] bGBK = cnStr.getBytes();
		for (byte aBGBK : bGBK) {
			// System.out.println(Integer.toHexString(bGBK[i] & 0xff));
			// 将每个字符转换成ASCII码
			strBuf.append(Integer.toHexString(aBGBK & 0xff));
		}
		return strBuf.toString();
	}

	public static void main(String[] args) {
		String cnStr = "饮鸩止渴";
		System.out.println(getPinYin(cnStr));
		System.out.println(getPinYinHeadChar(cnStr));
		System.out.println(getCnASCII(cnStr));
	}

}