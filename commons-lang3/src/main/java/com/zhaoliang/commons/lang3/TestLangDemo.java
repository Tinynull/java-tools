package com.zhaoliang.commons.lang3;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.CharSet;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.text.WordUtils;

public class TestLangDemo {
	public static void main(String[] args) {
		System.out.println("**CharSetDemo**");
		CharSet charSet = CharSet.getInstance("aeiou");
		String demoStr = "The quick brown fox jumps over the lazy dog.";
		int count = 0;
		for (int i = 0, len = demoStr.length(); i < len; i++) {
			if (charSet.contains(demoStr.charAt(i))) {
				count++;
			}
		}
		System.out.println("count: " + count);
		System.out.println("----------------------------------------------");

		System.out.println("**CharSetUtilsDemo**");
		System.out.println("计算字符串中包含某字符数.");
		System.out.println(CharSetUtils.count("The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("删除字符串中某字符.");
		System.out.println(CharSetUtils.delete("The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("保留字符串中某字符.");
		System.out.println(CharSetUtils.keep("The quick brown fox jumps over the lazy dog.", "aeiou"));

		System.out.println("合并重复的字符.");
		System.out.println(CharSetUtils.squeeze("a  bbbbbb     c dd", "b d"));
		System.out.println("----------------------------------------------");

		System.out.println("**ObjectUtilsDemo**");
		System.out.println("Object为null时，默认打印某字符.");
		Object obj = null;
		System.out.println(ObjectUtils.defaultIfNull(obj, "空"));

		System.out.println("验证两个引用是否指向的Object是否相等,取决于Object的equals()方法.");
		Object a = new Object();
		Object b = a;
		Object c = new Object();
		System.out.println(Objects.equals(a, b));
		System.out.println(Objects.equals(a, c));

		System.out.println("用父类Object的toString()方法返回对象信息.");
		Date date = new Date();
		System.out.println(ObjectUtils.identityToString(date));
		System.out.println(date);

		System.out.println("返回类本身的toString()方法结果,对象为null时，返回0长度字符串.");
		System.out.println(Objects.toString(date));
		System.out.println(Objects.toString(null));
		System.out.println(date);
		System.out.println("----------------------------------------------");

		System.out.println("*SerializationUtils**");
		Date date1 = new Date();
		byte[] bytes = SerializationUtils.serialize(date1);
		System.out.println(ArrayUtils.toString(bytes));
		System.out.println(date1);

		Date reDate = (Date) SerializationUtils.deserialize(bytes);
		System.out.println(reDate);
		System.out.println(Objects.equals(date1, reDate));
		System.out.println(date1 == reDate);

		System.out.println("----------------------------------------------");

		System.out.println("**RandomStringUtilsDemo**");
		System.out.println("生成指定长度的随机字符串,好像没什么用.");
		System.out.println(RandomStringUtils.random(500));

		System.out.println("在指定字符串中生成长度为n的随机字符串.");
		System.out.println(RandomStringUtils.random(5, "abcdefghijk"));

		System.out.println("指定从字符或数字中生成随机字符串.");
		System.out.println(RandomStringUtils.random(5, true, false));
		System.out.println(RandomStringUtils.random(5, false, true));

		System.out.println("----------------------------------------------");

		System.out.println("**StringUtilsDemo**");
		System.out.println("将字符串重复n次，将文字按某宽度居中，将字符串数组用某字符串连接.");
		String[] header = new String[3];
		header[0] = StringUtils.repeat("*", 50);
		header[1] = StringUtils.center("  StringUtilsDemo  ", 50, "^O^");
		header[2] = header[0];
		String head = StringUtils.join(header, "\n");
		System.out.println(head);

		System.out.println("缩短到某长度,用...结尾.");
		System.out.println(StringUtils.abbreviate("The quick brown fox jumps over the lazy dog.", 10));
		System.out.println(StringUtils.abbreviate("The quick brown fox jumps over the lazy dog.", 15, 10));

		System.out.println("返回两字符串不同处索引号.");
		System.out.println(StringUtils.indexOfDifference("aaabc", "aaacc"));

		System.out.println("返回两字符串不同处开始至结束.");
		System.out.println(StringUtils.difference("aaabcde", "aaaccde"));

		System.out.println("截去字符串为以指定字符串结尾的部分.");
		System.out.println(StringUtils.removeEnd("aaabcde", "de"));

		System.out.println("检查一字符串是否为另一字符串的子集.");
		System.out.println(StringUtils.containsOnly("aad", "aadd"));

		System.out.println("检查一字符串是否不是另一字符串的子集.");
		System.out.println(StringUtils.containsNone("defg", "aadd"));

		System.out.println("检查一字符串是否包含另一字符串.");
		System.out.println(StringUtils.contains("defg", "ef"));
		System.out.println(StringUtils.containsOnly("ef", "defg"));

		System.out.println("返回可以处理null的toString().");
		System.out.println(StringUtils.defaultString("aaaa"));
		System.out.println("?" + StringUtils.defaultString(null) + "!");

		System.out.println("去除字符中的空格.");
		System.out.println(StringUtils.deleteWhitespace("aa  bb  cc"));

		System.out.println("分隔符处理成数组.");
		String[] strArray = StringUtils.split("a,b,,c,d,null,e", ",");
		System.out.println(strArray.length);
		System.out.println(strArray.toString());

		System.out.println("判断是否是某类字符.");
		System.out.println(StringUtils.isAlpha("ab"));
		System.out.println(StringUtils.isAlphanumeric("12"));
		System.out.println(StringUtils.isBlank(""));
		System.out.println(StringUtils.isNumeric("123"));

		System.out.println("----------------------------------------------");

		System.out.println("获得系统文件分隔符.");
		System.out.println(SystemUtils.FILE_SEPARATOR);

		System.out.println(SystemUtils.USER_HOME);
		System.out.println(SystemUtils.getJavaHome());
		System.out.println(SystemUtils.USER_NAME);
		System.out.println(SystemUtils.USER_DIR);

		System.out.println("获得源文件编码.");
		System.out.println(SystemUtils.FILE_ENCODING);

		System.out.println("获得ext目录.");
		System.out.println(SystemUtils.JAVA_EXT_DIRS);

		System.out.println("获得java版本.");
		System.out.println(SystemUtils.JAVA_VM_VERSION);

		System.out.println("获得java厂商.");
		System.out.println(SystemUtils.JAVA_VENDOR);

		System.out.println("----------------------------------------------");

		System.out.println("获取类实现的所有接口.");
		System.out.println(ClassUtils.getAllInterfaces(Date.class));

		System.out.println("获取类所有父类.");
		System.out.println(ClassUtils.getAllSuperclasses(Date.class));

		System.out.println("获取简单类名.");
		System.out.println(ClassUtils.getShortClassName(Date.class));

		System.out.println("获取包名.");
		System.out.println(ClassUtils.getPackageName(Date.class));

		System.out.println("判断是否可以转型.");
		System.out.println(ClassUtils.isAssignable(Date.class, Object.class));
		System.out.println(ClassUtils.isAssignable(Object.class, Date.class));

		System.out.println("----------------------------------------------");

		System.out.println("单词处理功能");
		String str1 = "wOrD";
		String str2 = "ghj\nui\tpo";
		System.out.println(WordUtils.capitalize(str1)); // 首字母大写
		System.out.println(WordUtils.capitalizeFully(str1)); // 首字母大写其它字母小写
		char[] ctrg = { '.' };
		System.out.println(WordUtils.capitalizeFully("i aM.fine", ctrg)); // 在规则地方转换
		System.out.println(WordUtils.initials(str1)); // 获取首字母
		System.out.println(WordUtils.initials("Ben John Lee", null)); // 取每个单词的首字母
		char[] ctr = { ' ', '.' };
		System.out.println(WordUtils.initials("Ben J.Lee", ctr)); // 按指定规则获取首字母
		System.out.println(WordUtils.swapCase(str1)); // 大小写逆转
		System.out.println(WordUtils.wrap(str2, 1)); // 解析\n和\t等字符

	}

}