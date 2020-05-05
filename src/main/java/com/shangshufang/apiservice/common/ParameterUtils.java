package com.shangshufang.apiservice.common;

public class ParameterUtils {
    public static String convertSpecialChar (String v) {
        return v.replace("%2B", "+")
                .replace("%2F", "/")
                .replace("%3F", "?")
                .replace("%25", "%")
                .replace("%23", "#")
                .replace("%26", "&")
                .replace("%3D", "=");
    }
}
