package com.migu.pandora.search.util;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class MurmurHashUtil {
    public static String getMurmurhash3Id(String uid, int seed, int num) {
        int hash = Hashing.murmur3_32(seed).hashString(uid, StandardCharsets.UTF_8).asInt();
        return cutHash(hash, num);
    }

    public static String getMurmurhash3Id(String uid, int num) {
        int hash = Hashing.murmur3_32().hashString(uid, StandardCharsets.UTF_8).asInt();
        return cutHash(hash, num);
    }

    private static String cutHash(int hash, int num) {
        String shash = hash >= 0 ? Integer.toString(hash) : Integer.toString(Math.abs(hash));
        int hashLen = shash.length();
        if (hashLen >= num) {
            return shash.substring(hashLen - num, hashLen);
        } else {
            StringBuilder stb = new StringBuilder(shash);
            int left = num - hashLen;

            for(int i = 0; i < left; ++i) {
                stb.append('0');
            }
            return stb.toString();
        }
    }
}