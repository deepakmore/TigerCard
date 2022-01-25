package com.morez.app.utils;

public class KeyGenerator {

    public static int generate(String source, String destination) {
        int value = source.compareTo(destination);
        return value > 0 ? (source + destination).hashCode() :
                value < 0 ? (destination + source).hashCode() : source.hashCode();
    }
}
