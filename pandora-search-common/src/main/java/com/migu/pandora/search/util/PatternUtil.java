package com.migu.pandora.search.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.migu.pandora.search.constant.InputStrType;


public class PatternUtil {

	private static Pattern onlyHzPat = Pattern.compile("[\\u4e00-\\u9fa5]+");
	private static Pattern onlyAlPat = Pattern.compile("[a-zA-Z]+");
	private static Pattern alHzPat = Pattern.compile("[a-zA-Z\\u4e00-\\u9fa5]+");

	private static Pattern digitalPat = Pattern.compile("[0-9]+");
	private static Pattern digitalAlPat = Pattern.compile("[0-9a-zA-Z]+");
	private static Pattern digitalHzPat = Pattern.compile("[0-9\\u4e00-\\u9fa5]+");
	private static Pattern digitalAlHzPat = Pattern.compile("[0-9a-zA-Z\\u4e00-\\u9fa5]+");
	
	
	//判断用户的输入属于哪一类别
	public static InputStrType getQueryType(String input){
		//只有汉字
		Matcher matcher=onlyHzPat.matcher(input);
		if (matcher.matches()) {
			return InputStrType.ONLY_HZ;
		}
		//只有字母
		matcher = onlyAlPat.matcher(input);
		if (matcher.matches()) {
			return InputStrType.ONLY_ALPHA;
		}	
		//字母汉字组合
		matcher=alHzPat.matcher(input);
		if (matcher.matches()) {
			return InputStrType.ALPHA_HZ;
		}	
		//仅有数字
		matcher=digitalPat.matcher(input);
		if(matcher.matches()){
			return InputStrType.ONLY_DIGITAL;
		}
		//数字字母组合
		matcher=digitalAlPat.matcher(input);
		if(matcher.matches()){
			return InputStrType.DIGITAL_ALPHA;
		}
		//数字汉字组合
		matcher=digitalHzPat.matcher(input);
		if(matcher.matches()){
			return InputStrType.DIGITAL_HZ;
		}		
		//数字字母汉字组合 
		matcher=digitalAlHzPat.matcher(input);
		if(matcher.matches()){
			return InputStrType.DIGITAL_AL_HZ;
		}
		//复杂类型
		return InputStrType.OTHER;
	}

}
