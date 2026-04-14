package com.migu.pandora.search.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTool {

    /**
     * 搜索词特殊字符过滤
     *
     * @param text
     * @return
     */
    public static String queryTxtFilter(String text) {
        String textFilter = null;
        if (isTimePeriod(text)) {
            textFilter = getTimePeriodQuery(text);
        } else {
            String regex = "[_\"`~!@#$%^&*()（）+=|{}:;,//[//]<>/?~！@#￥%……&*——+|{}【】‘；：”“’。·，、？《》]";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            textFilter = matcher.replaceAll("");
        }
        return textFilter;
    }

    /**
     * 判断是否英文
     *
     * @param text
     * @return
     */
    public static boolean isEnglish(String text) {
        return text.matches("^[a-zA-Z]*$");
    }

    public static boolean isChinese(char c) {
        return c >= 0x4E00 && c <= 0x9FA5;
    }

    /**
     * 判断是否中文
     *
     * @param text
     * @return
     */
    public static boolean isChinese(String text) {
        boolean result = true;
        if (StringUtils.isNotBlank(text)) {
            for (char c : text.toCharArray()) {
                if (!(c >= 0x4E00 && c <= 0x9FA5)) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 判断是否中文或标点符号或空白字符
     *
     * @param text
     * @return
     */
    public static boolean isSuggestChinese(String text) {
        if (StringUtils.isNotBlank(text)) {
            Character.UnicodeScript sc = null;
            Character.UnicodeBlock ub = null;
            for (char c : text.toCharArray()) {
                sc = Character.UnicodeScript.of(c);
                if (Character.isWhitespace(c) || sc == Character.UnicodeScript.HAN) {
                    //是中文就继续
                    continue;
                }
                ub = Character.UnicodeBlock.of(c);
                if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                        || ub == Character.UnicodeBlock.VERTICAL_FORMS) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否时间段
     *
     * @param text
     * @return
     */
    public static boolean isTimePeriod(String text) {
        Pattern pattern = Pattern.compile("(\\d{4}\\.\\d{1,2}\\.\\d{1,2})?-(\\d{4}\\.\\d{1,2}\\.\\d{1,2})?");
        Matcher matcher = pattern.matcher(text);
        return !StringUtils.equals(text, "-") && matcher.matches();
    }

    /**
     * 获取solr时间段查询query
     *
     * @param text
     * @return
     */
    public static String getTimePeriodQuery(String text) {
        String queryTxt = null;
        Pattern pattern = Pattern.compile("(\\d{4}\\.\\d{1,2}\\.\\d{1,2})?-(\\d{4}\\.\\d{1,2}\\.\\d{1,2})?");
        Matcher matcher = pattern.matcher(text);
        if (!StringUtils.equals(text, "-") && matcher.matches()) {
            String startTime = matcher.group(1);
            if (StringUtils.isBlank(startTime)) {
                startTime = "*";
            } else {
                startTime = TimeTool.cst2utc(startTime + " 00:00:00", "yyyy.MM.dd HH:mm:ss");
            }
            String endTime = matcher.group(2);
            if (StringUtils.isBlank(endTime)) {
                endTime = "*";
            } else {
                endTime = TimeTool.cst2utc(endTime + " 23:59:59", "yyyy.MM.dd HH:mm:ss");
            }
            queryTxt = "[" + startTime + " TO " + endTime + "]";
        }
        return queryTxt;
    }

    /**
     * 正则匹配:类似(xxx演唱会，xxxlive版)
     *
     * @param content
     * @param regex
     * @return
     */
    public static boolean regex(String content, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(content);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static List<String> splitWords(String inputStr) {
        if (StringUtils.isBlank(inputStr)) {
            return null;
        }
        List<String> result = new ArrayList<>();
        String[] splittedInputStr = inputStr.split(" ");
        if (splittedInputStr.length > 0) {
            for (String single : splittedInputStr) {
                String filtedStr = queryTxtFilter(single);
                if (StringUtils.isBlank(filtedStr)) {
                    continue;
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < filtedStr.length(); i++) {
                        char c = filtedStr.charAt(i);
                        boolean isChinese = isChinese(c);
                        if (isChinese) {
                            String word = sb.toString();
                            if (StringUtils.isNotBlank(word)) {
                                result.add(sb.toString());
                            }
                            result.add(String.valueOf(c));
                            sb = new StringBuilder();
                        } else {
                            //已经结束
                            if (i == filtedStr.length() - 1) {
                                sb.append(c);
                                String word = sb.toString();
                                if (StringUtils.isNotBlank(word)) {
                                    result.add(sb.toString());
                                }
                            } else {
                                sb.append(c);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * 去除字符串中所有的标点符号（去除空格）
     */
    public static String rmPct(String str, boolean isDelWhitespace) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        //标点过滤正则表达式
        String regEx = "[`\\-=\\[\\]\\\\;',./~!@#$%^&*()_+{}|:\"<>?" + //半角英文
                "·【】、；‘’，。！￥…（）—：“”《》？" + //半角中文
                "｀－＝［］＼＇．／～＠＃＄％＾＆＊＿＋｛｝｜＂＜＞" + //全角英文
                "×]"; //全角中文

        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (isDelWhitespace) {
            return StringUtils.deleteWhitespace(m.replaceAll("").trim());
        } else {
            return m.replaceAll("").trim();
        }
    }

    /**
     * 判断：全数字
     */
    public static boolean isAllNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    /**
     * 判断：字母+数字，非汉字（包含空格）
     */
    public static boolean isNoChn(String str) {
        return str.matches("^[a-zA-Z0-9 ]+$");
    }

    /**
     * 判断：全汉字（包含空格）
     */
    public static boolean isAllChn(String str) {
        return str.matches("^[\\u4e00-\\u9fa5 ]+$");
    }

    /**
     * 判断:汉字+数字（包含空格）
     */
    public static boolean isChnAndNumb(String str) {
        return str.matches("^[[ ]*\\u4e00-\\u9fa5]+[ ]*[0-9]+[\\u4e00-\\u9fa5 0-9]*$|[ ]*[0-9]+[ ]*[\\u4e00-\\u9fa5]+[\\u4e00-\\u9fa5 0-9]*");
    }

    /**
     * 判断:汉字+字母（包含空格）
     */
    public static boolean isChnAndAlp(String str) {
        return str.matches("^[ ]*[\\u4e00-\\u9fa5]+[ ]*[a-zA-Z]+[ ]*[\\u4e00-\\u9fa5 a-zA-Z]*$|[ ]*[a-zA-Z]+[ ]*[\\u4e00-\\u9fa5]+[\\u4e00-\\u9fa5 a-zA-Z]*");
    }

    /**
     * 判断：包含字母
     */
    public static boolean isContainsAlp(String str) {
        String regex = ".*[a-zA-Z]+.*";
        Matcher m = Pattern.compile(regex).matcher(str);
        return m.matches();
    }

}
