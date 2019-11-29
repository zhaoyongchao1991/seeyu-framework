package com.seeyu.normal.utils;

import com.seeyu.core.constant.ActivationState;
import com.seeyu.core.utils.Alert;
import com.seeyu.lang.utils.StringUtils;
import com.seeyu.normal.constant.options.OptionGroup;

import java.util.regex.Pattern;

/**
 * @author seeyu
 */
public class FormValidator {

	/**
	 * 检查是否存在
	 * @param input
	 * @param b
	 */
	public static void notExist(String input, boolean b){
		if(!b){
			Alert.alert("{}已经存在", input);
		}
	}


	/**
	 * 检查是否不存在
	 * @param input
	 * @param b
	 */
	public static void exist(String input, boolean b){
		if(!b){
			Alert.alert( "{}不存在", input);
		}
	}


	/**
	 * 检查语法是否符合规范
	 * @param input
	 * @param b
	 */
	public static void syntaxError(String input, boolean b){
		if(!b){
			Alert.alert("{}的格式不符合规范", input);
		}
	}


	/**
	 * 检查activationState的值是否合法
	 * @param input
	 * @param val
	 */
	public static void validActivationState(String input, Integer val){
		if(val == null){
			return;
		}
		if(ActivationState.getActiveState() != val && ActivationState.getDisableState() != val){
			Alert.alert("{}的合法值为: {},{}", input, ActivationState.getDisableState(), ActivationState.getActiveState());
		}
	}

	/**
	 * 检查范围
	 * @param input
	 * @param val
	 * @param min
	 * @param max
	 * @return
	 */
	public static void range(String input, Integer val, int min, int max){
		if(val == null){
			return;
		}
		if(val > max || val < min){
			Alert.alert("{}的范围为:" + "{}--{}", input, min, max);
		}
	}
	
	/**检查最大限制
	 * @param input
	 * @param val
	 * @param max
	 * @return
	 */
	public static void max(String input, Integer val, int max){
		if(val == null){
			return;
		}
		if(val > max){
			Alert.alert("{}最大为:{}", input, max);
		}
	}
	
	/**检查最小限制
	 * @param input
	 * @param val
	 * @param min
	 * @return
	 */
	public static void min(String input, Integer val, int min){
		if(val == null){
			return;
		}
		if(val < min){
			Alert.alert("{}最小为:{}", input, min);
		}
	}
	

	/**
	 * 判断长度范围
	 * @param input
	 * @param val
	 * @param min
	 * @param max
	 * @return
	 */
	public static void rangeLength(String input, String val, int min, int max){
		if(val == null){
			return;
		}
		if(val.length() > max || val.length() < min){
			Alert.alert(  "{}长度范围为:" + "{}--{}", input, min, max);
		}
	}
	
	
	/**
	 * 判断最大长度
	 * @param input
	 * @param val
	 * @param max
	 * @return
	 */
	public static void maxLength(String input, String val, int max){
		if(val == null){
			return;
		}
		if(val.length() > max){
			Alert.alert("{}长度最大为:{}", input, max);
		}
	}
	
	/**
	 * 判断最小长度
	 * @param input
	 * @param val
	 * @param min
	 * @return
	 */
	public static void minLength(String input, String val, int min){
		if(val == null){
			return;
		}
		if(val.length() < min){
			Alert.alert( "{}长度最小为:{}", input, min);
		}
	}


	/**
	 * 手机号码 1开头的11位数字
	 * @param input
	 * @param val
	 * @return
	 */
	public static void mobilePhone(String input, String val){
		if(val == null){
			return;
		}
		String cellPhone = "^1\\d{10}$";
		if(!Pattern.compile(cellPhone).matcher(val).matches()){
			Alert.alert( "{}格式错误", input);
		}
	}

	
	/**
	 * 电话号码
	 * 手机号码
	 * @param input
	 * @param val
	 * @return
	 */
	public static void phone(String input, String val){
		if(val == null){
			 return;
		}
		String telePhone = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
		if(!Pattern.compile(telePhone).matcher(val).matches()){
			mobilePhone(input, val);
		}
	}

	/**
	 * 不能包含空白符
	 * @param input
	 * @param val
	 * @return
	 */
	public static void noBlank(String input, String val){
		if(val == null){
			return;
		}
		String reg = "\\s";
		if(Pattern.compile(reg).matcher(val).find()){
			Alert.alert("{}不能包含空白符", input);
		}
	}

	/**
	 * 不能全是空白符
	 * @param input
	 * @param val
	 * @return
	 */
	public static void notBlank(String input, String val){
		if(val == null){
			return;
		}
		if(StringUtils.isBlank(val)){
			Alert.alert("{}不能全是空白符", input);
		}
	}

	/**
	 * 不能存在多余的空白符 不能以空白符开头,结尾,不能连续超过2个空白符
	 * @param input
	 * @param val
	 * @return
	 */
	public static void noMuchBlank(String input, String val){
		if(val == null){
			 return;
		}
		String reg = "^\\s|\\s$|\\s{2}";
		if(Pattern.compile(reg).matcher(val).find()){
			Alert.alert( "{}不能以空白符开头或结尾,不能连续超过2个空白符", input);
		}
	}
	
	
	/**
	 * 验证用户名
	 * @param input
	 * @param val
	 * @return
	 */
	public static void username(String input, String val){
		if(val == null){
			 return;
		}
		String reg = "^[0-9a-zA-Z_]*$";
		if(!Pattern.compile(reg).matcher(val).matches()){
			Alert.alert(  "{}只能是数字,字母,以\"_\"组成", input);
		}
	}
	
	/**
	 * 验证密码
	 * 密码只能是数字字母以及标点符号组成
	 * 标点符号包括(!"#$%&'()*+,-./:;<=>?@[\]^_`{|}~)
	 * @param input
	 * @param val
	 * @return
	 */
	public static void password(String input, String val){
		if(val == null){
			 return;
		}
		String reg = "^[0-9a-zA-Z\\p{Punct}]*$";
		if(!Pattern.compile(reg).matcher(val).matches()){
			Alert.alert( "{}只能是数字,字母组成,以及标点符号组成", input);
		}
		checkPasswordComplexity(input, val);
		return;
	}

	/**
	 * 验证数字和字母组成
	 * @param input
	 * @param val
	 * @return
	 */
	public static void numberLetter(String input, String val){
		if(val == null){
			 return;
		}
		String reg = "^[0-9a-zA-Z]*$";
		if(!Pattern.compile(reg).matcher(val).matches()){
			Alert.alert( "{}只能是数字,字母组成", input);
		}
	}
	
	 /**
	  * 必填项
	 * @param input
	 * @param ele
	 * @return
	 */
	public static void required(String input, Object ele){
		 String msg = "{}为必须项,不能为空";
		 if(ele == null){
		 	Alert.alert(msg, input);
		 }
		 else if(ele instanceof String && "".equals(String.valueOf(ele))){
		 	Alert.alert(msg, input);
		 }
	 }
	
	
	/**
	 * 邮箱
	 * @param input
	 * @param email
	 * @return
	 */
	public static void email(String input, String email){
		if(email == null){
			return;
		}
		String reg = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if(!Pattern.compile(reg).matcher(email).matches()){
			Alert.alert( "{}格式错误", input);
		}
	}

    /**
     * value 是否存在于选项组 options中
     * @param input
     * @param options
     * @param value
     */
    public static void inOptions(String input, OptionGroup options, Object value){
        if(value == null){
            return;
        }
        Alert.notNull(options.findOptionByValue(String.valueOf(value)), "{}未知选项:{}", input, value);
    }
	
	/**
	 * 验证表单输入的数据
	 * @param input
	 * @param ele
	 * @param vts
	 * @return
	 */
	public static void validate(String input,Object ele, ValidateType ...vts){
		for(ValidateType _v : vts){
			switch(_v){
				case required : required(input, ele); break;
				case email : email(input, (String)ele); break;
				case numberLetter : numberLetter(input, (String)ele); break;
				case noBlank : noBlank(input, (String)ele); break;
				case username : username(input, (String)ele); break;
				case password : password(input, (String)ele); break;
				case phone : phone(input, (String)ele); break;
				case mobile: mobilePhone(input, (String)ele); break;
				case noMuchBlank: noMuchBlank(input, (String)ele); break;
				case notBlank: notBlank(input, (String)ele); break;
				case validActiveState: validActivationState(input, (Integer)ele);break;
			default:
				throw new RuntimeException("未知验证类型:" + _v.name());
			}
		}
	}

	/**
	 * 检查密码复杂度
	 */
	private static void checkPasswordComplexity(String input, String password) {
		//数字
		String REG_NUMBER = ".*\\d+.*";
		//小写字母
		String REG_UPPERCASE = ".*[A-Z]+.*";
		//大写字母
		String REG_LOWERCASE = ".*[a-z]+.*";
		//特殊符号
		String REG_SYMBOL = ".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*";
		if (password.length() < 8) {
			Alert.alert("{}长度不能小于8位", input);
		}
		int i = 0;
		if (password.matches(REG_NUMBER)) {
			i++;
		}
		if (password.matches(REG_LOWERCASE)) {
			i++;
		}
		if (password.matches(REG_UPPERCASE)) {
			i++;
		}
		if (password.matches(REG_SYMBOL)) {
			i++;
		}
		if (i < 3) {
			Alert.alert("{}必须由大小写字母、数字、特殊字符中的至少三种组成", input);
		}
	}


	/**
	 * 验证类型
	 * @author nurteen_tyler
	 *
	 */
	public static enum ValidateType{
		
		required(1),email(2),numberLetter(3),noBlank(4),username(5),password(6),phone(7),
		mobile(8),noMuchBlank(9),notBlank(10),validActiveState(11);
		
		private Integer type;
		
		private ValidateType(int type){
			this.type = type;
		}
		
		public Integer getType(){
			return type;
		}
	}



}
