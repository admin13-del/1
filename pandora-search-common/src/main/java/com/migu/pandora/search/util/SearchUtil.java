package com.migu.pandora.search.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by piguangtao on 12/3/18.
 */
@Service
public class SearchUtil {
    public String removeSpecialChar(String inputStr) {
        return StringUtils.isBlank(inputStr) ? inputStr : inputStr.replaceAll("[\\*:~《》\\\\]", "");
    }

    public String cutQueryStr(String inputStr, int maxCount) {
        if (StringUtils.isBlank(inputStr)) {
            return inputStr;
        }
        return inputStr.length() > maxCount ? inputStr.substring(0, maxCount) : inputStr;
    }


    /**
     * 对用户输入的solr特殊字符进行转义
     *
     * @param input
     * @return
     */
    public static String transformSolrMetacharactor(String input){
        StringBuffer sb = new StringBuffer();
        String regex = "[+\\-&|!(){}\\[\\]^\"~*?:(\\)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while(matcher.find()){
            matcher.appendReplacement(sb, "\\\\"+matcher.group());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}
