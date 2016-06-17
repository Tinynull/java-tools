package com.zhaoliang.enumpackage;

/**
 * java version.
 *
 * Created by zhaoliang(weston_contribute@163.com) on 2016/6/15.
 */
public enum JavaVersion {

    JAVA_1_1(1.1f, "1.1"),
    JAVA_1_2(1.2f, "1.2"),
    JAVA_1_3(1.3f, "1.3"),
    JAVA_1_4(1.4f, "1.4"),
    JAVA_1_5(1.5f, "1.5"),
    JAVA_1_6(1.6f, "1.6"),
    JAVA_1_7(1.7f, "1.7"),
    JAVA_1_8(1.8f, "1.8"),
    JAVA_1_9(1.9f, "1.9");

    private final float number;

    private final String name;

    JavaVersion(float number, String name) {
        this.number = number;
        this.name = name;
    }

    public JavaVersion getJavaVersion(String name) {
        JavaVersion version;
        switch (name) {
            case "1.1":
                version = JAVA_1_1;
                break;
            case "1.2":
                version = JAVA_1_2;
                break;
            case "1.3":
                version = JAVA_1_3;
                break;
            case "1.4":
                version = JAVA_1_4;
                break;
            case "1.5":
                version = JAVA_1_5;
                break;
            case "1.6":
                version = JAVA_1_6;
                break;
            case "1.7":
                version = JAVA_1_7;
                break;
            case "1.8":
                version = JAVA_1_8;
                break;
            case "1.9":
                version = JAVA_1_9;
                break;
            default:
                version = null;
        }
        return version;
    }

    /**
     * test.
     *
     * @param version java version.
     * @return true if this version is equal to or greater than the specified version
     */
    public boolean atLeast(JavaVersion version) {
        return version.number > this.number;
    }


}
