package com.migu.pandora.search.util;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class PinYinUtil {
    public static Pattern onlyHzPat = Pattern.compile("[\\u4e00-\\u9fa5]+");

    public static String getQP(String str) {
        if (StringUtils.isBlank(str)){
            return str.toLowerCase();
        }
        String res = "";
        //char[] tmp = str.toCharArray();
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(str);
        pinyinList = pinyinList.stream().filter(s -> !"none".equalsIgnoreCase(s.getPinyinWithoutTone())).collect(Collectors.toList());
        for (int i = 0; i < pinyinList.size(); i++) {
            res += pinyinList.get(i).getPinyinWithoutTone();
        }
        return StringUtils.isBlank(res) ? str.toLowerCase() : res;
    }

    public static List<String> getJP(String str) {
        List<String> res = new ArrayList<String>();
        if (StringUtils.isBlank(str)) {
            return res;
        }
        String tmpJP1 = "";
        String tmpJP2 = "";
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(str);
        pinyinList = pinyinList.stream().filter(s -> !"none".equalsIgnoreCase(s.getPinyinWithoutTone())).collect(Collectors.toList());
        for (Pinyin node : pinyinList) {
            tmpJP1 += node.getHeadString();
            tmpJP2 += String.valueOf(node.getFirstChar());
        }
        if(StringUtils.isNotBlank(tmpJP1)){
            res.add(tmpJP1);
        }
        if (StringUtils.isNotBlank(tmpJP2) && !tmpJP1.equals(tmpJP2)) {
            res.add(tmpJP2);
        }
        if(res.isEmpty()) {
            res.add(str);
        }
        return res;
    }

    @SuppressWarnings("unused")
    private static String getQP4OnlyHZ(String str) {
        char[] tmp = str.toCharArray();
        String res = "";
        for (int i = 0; i < tmp.length; i++) {
            String byteStr = tmp[i] + "";
            Matcher matcher = onlyHzPat.matcher(byteStr);
            if (matcher.matches()) {
                res += getQP(byteStr);
            } else
                res += byteStr;
        }
        return res;
    }

    public static void main(String[] args) {

        String [] splitWords = "zhengABzhengC".split("zheng");
        System.out.println(splitWords);

        splitWords = "ABzhengCzheng".split("zheng");
        System.out.println(splitWords);

        splitWords = "ABzhengC".split("zheng");
        System.out.println(splitWords);

        System.out.println(getQP("she"));
        System.out.println(getQP("七里香(sdd)"));
        System.out.println(getQP("七里香c"));
        System.out.println(getQP("七里香,"));

        System.out.println("=====");
        System.out.println(getJP("七里香"));
        System.out.println(getJP("七里香c"));
        System.out.println(getJP("七里香,"));

        System.out.println("=====");
        System.out.println(getQP("qilixiang"));
        System.out.println(getQP("qlx"));
        System.out.println(getQP("California 88rising"));
        System.out.println(getQP("my"));
        System.out.println("=====");
        System.out.println(getJP("qilixiang"));
        System.out.println(getJP("qlx"));
        System.out.println(getJP("California 88rising"));
        System.out.println(getJP("my"));
        System.out.println("=====");
        // System.out.println(getQP4OnlyHZ("00你好昌盛的哦噢的放得开的方法破以为mklop为空逻辑符号大反馈是代计费来晚了abc群殴拉这么12多房产部也完全"));
        System.out.println(getQP("0啊我饿一无言"));
        System.out.println(getJP("好的的正确"));

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            getQP("00你好昌盛的方法破以为mklop为空逻辑abc群殴拉这么12多房产部也完全");
        long stop = System.currentTimeMillis();
        System.out.println("time = " + (stop - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
            getJP("00你好昌盛的方法破以为mklop为空逻辑abc群殴拉这么12多房产部也完全");
        stop = System.currentTimeMillis();
        System.out.println("time = " + (stop - start));

        System.out.println(HanLP.convertToPinyinFirstCharString("好的的确", "", false));

    }
}
