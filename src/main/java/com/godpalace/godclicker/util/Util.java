package com.godpalace.godclicker.util;

public final class Util {
    public static int SpiltCharToInt(String str) {
        int num = 0;
        for (char c : str.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
            }
        }
        return num;
    }
}
