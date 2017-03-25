package com.zksyp.informationgot.util;

import android.support.annotation.ColorInt;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with Android Studio.
 * User:kaishen
 * Date:2017/3/20
 * Time:下午4:28
 * Desc:字符串处理工具类
 */

public class StringUtil {

    /**
     * 判断字符串是否是整数
     */
    public static boolean isInteger(String value) {
        try {
            Long.parseLong(value);
            return !value.contains(".");
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 将字符串转化为整数
     *
     * @param value        字符串
     * @param defaultValue 默认值
     * @return 整数
     */
    public static int parseInt(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long parseLong(String value, long defaultValue) {
        try {
            return Long.parseLong(value);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是浮点数
     */
    public static boolean isFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否是数字
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 是否为合法手机号
     */
    public static boolean isPhoneNumber(String phone) {
        return isPhoneNumberSimple(phone);
    }

    /**
     * 是否为合法手机号的简单校验，11位数字
     */
    public static boolean isPhoneNumberSimple(String phone) {
        if (phone == null) {
            return false;
        }
        Pattern p = Pattern.compile("^[1-9]\\d{10}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    /**
     * 是否是有效的身份证号
     */
    public static boolean isCertNo(String certNo) {
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}";
        return Pattern.matches(regex, certNo);
    }

    /**
     * 是否是合法邮箱地址
     */
    public static boolean isEmail(String email) {
        if (isEmpty(email))
            return false;
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean isEmpty(String str) {
        return ((str == null) || str.trim().equals(""));
    }

    public static boolean isNotEmpty(String str) {
        return (str != null && !str.trim().equals(""));
    }

    public static boolean isAuthCode(String code) {
        return !isEmpty(code) && code.length() == 4;
    }

    // 吧文件url字符串转换成list
    public static ArrayList<String> strToURLList(String urls) {
        ArrayList<String> list = new ArrayList<>();
        if (urls.contains("%3B")) {
            for (String str : urls.split("%3B")) {
                if (str.startsWith("http%3A//"))
                    str = str.substring(9);
                if (str.startsWith("http://"))
                    str = str.substring(7);
                str = "http://" + str;
                list.add(str);
            }
        } else if (urls.contains(";")) {
            for (String str : urls.split(";")) {
                if (str.startsWith("http%3A//"))
                    str = str.substring(9);
                if (str.startsWith("http://"))
                    str = str.substring(7);
                str = "http://" + str;
                list.add(str);
            }
        }

        return list;
    }

    public static String timeToSimple(String time) {
        return time.substring(5, time.length() - 3).replace("T", " ");
    }

    /**
     * 把数字转换为中文数字形式
     */
    public static String numberToChinese(int number) {
        String str = String.valueOf(number);
        StringBuilder result = new StringBuilder();
        int length = str.length();
        boolean bZero = false;
        char[] digits = new char[]{' ', '十', '百', '千', '万', '十', '百', '千'};
        char[] chs = new char[]{'零', '一', '二', '三', '四', '五', '六', '七', '八',
                '九'};
        for (int i = 0; i < length; i++) {
            char ch = chs[Integer.parseInt(str.charAt(i) + "")];
            char digit = digits[length - 1 - i];
            // 处理零和连续零的情况
            if (ch == '零') {
                bZero = true;
                if (digit == '万') {
                    result.append('万');
                }
                continue;
            }
            if (bZero) {
                result.append('零');
                bZero = false;
            }
            // 处理一十的情况
            if (digit == '十' && ch == '一') {
                result.append("十");
                continue;
            }
            result.append(ch).append(digit);
        }
        return result.toString();
    }


    public static boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        } else if (a != null && b != null && a.equals(b)) {
            return true;
        } else
            return (a == null && b != null && b.equals("")) || (a != null && a.equals("") && b == null);
    }

    public static boolean isEqual(Integer a, Integer b) {
        return (a == null && b == null) ||
                (a != null && b != null && a.intValue() == b.intValue());
    }

    public static boolean isEqual(Long a, Long b) {
        return (a == null && b == null) ||
                (a != null && b != null && a.intValue() == b.intValue());
    }

    public static boolean isNotEqual(String a, String b) {
        return !isEqual(a, b);
    }


    /**
     * 每4位插入一个空格 格式化银行卡
     */
    public static String spaceBlan(String s) {
        String regex = "(.{4})";
        return s.replaceAll(regex, "$1 ");
    }


    /**
     * 给传入的链接添加参数返回
     */
    public static String convertUrlWithParam(String url, String paramName, String paramContent) {
        StringBuilder sb = new StringBuilder();

        if (isNotEmpty(url) && isNotEmpty(paramName) && isNotEmpty(paramContent)) {
            if (url.contains("?")) { //www.maihaoche.com?pp=11  --> //www.maihaoche.com?user_id=1&pp=11
                int position = url.indexOf("?");
                sb.append(url.substring(0, position + 1));
                sb.append(paramName);
                sb.append("=");
                sb.append(paramContent);
                sb.append("&");
                sb.append(url.substring(position + 1, url.length()));
            } else {
                if (url.contains("#")) {//www.maihaoche.com#ss  -->www.maihaoche.com?user_id=1#ss
                    int position = url.indexOf("#");
                    sb.append(url.substring(0, position));
                    sb.append("?");
                    sb.append(paramName);
                    sb.append("=");
                    sb.append(paramContent);
                    sb.append(url.substring(position + 1, url.length()));
                } else { //www.maihaoche.com   -->  www.maihaoche.com?user_id=1
                    sb.append(url);
                    sb.append("?");
                    sb.append(paramName);
                    sb.append("=");
                    sb.append(paramContent);
                }
            }
        } else {
            sb.append(url);
        }

        return sb.toString();
    }


    /**
     * 符号化加密手机号
     *
     * @param phone 原始手机号
     * @return 处理后的手机号——187****1876
     */
//    public static String convertPhoneWithStar(String phone) {
//        StringBuilder sb = new StringBuilder();
//
//        if (isPhoneNumber(phone)) {
//            sb.append(phone.substring(0, 3));
//            sb.append("****");
//            sb.append(phone.substring(7, phone.length()));
//        } else {
//            sb.append(phone);
//        }
//
//        return sb.toString();
//    }

    /**
     * 符号化加密手机号
     *
     * @param phone 原始手机号
     * @return 处理后的手机号——187****1876
     */
    public static String convertPhoneWithStar(String phone) {
        if (isPhoneNumber(phone)) {
            phone = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        }
        return phone;
    }

    /**
     * 将字符串转化为数组
     *
     * @param str   目标字符串
     * @param split 分隔符
     * @return 数组
     */
    public static List<String> convertStrToList(String str, String split) {
        if (str == null || split == null) {
            return null;
        }
        List<String> list = new ArrayList<>();
        String[] s = str.split(split);
        Collections.addAll(list, s);
        return list;
    }

    /**
     * 万元转换成分
     */
    public static Long convertWToCent(Double money) {
        if (money == null || money < 0) {
            return 0L;
        }

        try {
            Double m = money * 10000 * 100;
            return m.longValue();
        } catch (NumberFormatException e) {
            LogUtil.e("convertWToCent", e.toString());
            return 0L;
        }
    }

    /**
     * 元转换成分
     */
    public static long convertYuanToCent(Double money) {
        if (money == null || money < 0) {
            return 0L;
        }
        try {
            Double m = money * 100;
            return m.longValue();
        } catch (NumberFormatException e) {
            LogUtil.e("convertYuanToCent", e.toString());
            return 0L;
        }
    }

    /**
     * 分转换成万元
     */
    public static Double convertCentToW(Long cent) {
        if (cent == null || cent < 0) {
            return 0.0;
        }
        return cent / 100.0 / 10000.0;
    }

    public static String checkNotNull(String str, String nullStr) {
        return isEmpty(str) ? nullStr : str;
    }

    public static String checkNotNull(String str, String nullStr, String notNullStr) {
        return isEmpty(str) ? nullStr : str;
    }

    public static String convertUrlToBase64(String url) {
        String str = Base64.encodeToString(url.getBytes(), Base64.DEFAULT);
        return str.replace("+", "-").replace("/", "_");
    }

    public static String convertListToStr(List list, String split) {
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); ++i) {
            String s = list.get(i) == null ? "" : list.get(i).toString();
            sb.append(s);
            if (i < list.size() - 1) {
                sb.append(split);
            }
        }
        return sb.toString();
    }

    public static String stripZeros(String s) {
        try {
            BigDecimal bd = new BigDecimal(s.trim());
            if (bd.compareTo(BigDecimal.ZERO) == 0) {
                return "0";
            } else {
                return bd.setScale(2, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
            }
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 获取包含{}的字符串剔除{}后展示不同颜色的字体
     *
     * @param content
     * @param color
     */
    public static SpannableStringBuilder getSpannableStringBuilder(String content, int color) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        Pattern p = Pattern.compile("\\{.*?\\}");// 查找规则公式中大括号以内的字符
        Matcher m = p.matcher(content);

        String param = "";
        while (m.find()) {// 找到第一个大括号内容
            param = m.group();
            param = param.substring(1, param.length() - 1);
            break;
        }

        return getDiffColorText(content.replaceAll("\\{", "").replaceAll("\\}", ""), param, color);
    }

    /**
     * 获取不同颜色段的字体
     *
     * @param wholeStr 整串字符串
     * @param colorStr 整串字符中的颜色字符
     * @param color    特殊颜色
     * @return 含有不同颜色的字符
     */
    public static SpannableStringBuilder getDiffColorText(String wholeStr, String colorStr, @ColorInt int color) {
        SpannableStringBuilder sb = new SpannableStringBuilder(wholeStr);
        if (TextUtils.isEmpty(colorStr)) {
            return sb;
        }
        int start = 0, end = 0;
        if (wholeStr.contains(colorStr)) {  // 返回colorStr字符串中第一次出现的索引处
            start = wholeStr.indexOf(colorStr);
            end = start + colorStr.length();
        }
        CharacterStyle cs = new ForegroundColorSpan(color);
        sb.setSpan(cs, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    public static SpannableString getColorText(String str, @ColorInt int color) {
        if (isEmpty(str)) return new SpannableString("");
        SpannableString sp = new SpannableString(str);
        sp.setSpan(new ForegroundColorSpan(color), 0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sp;
    }

    /**
     * Integer 类型转成字符串
     */
    public static String getIntegerStr(Integer integer) {
        return integer == null ? "0" : integer.toString();
    }

    /**
     * Double 类型转成字符串
     */
    public static String getDoubleStr(Double d) {
        return d == null ? "0" : d.toString();
    }

    /**
     * 去除价格的单位
     */
    public static String getPriceWithOutUnit(String price) {
        if (TextUtils.isEmpty(price)) {
            return "";
        }
        if (price.endsWith("万")) {
            return price.length() > 1 ? price.substring(0, price.length() - 1) : "";
        }
        if (price.endsWith("万元")) {
            return price.length() > 2 ? price.substring(0, price.length() - 2) : "";
        }
        return price;
    }

    /**
     * 将对象转化为字符串，防止对象为空时得到null字符
     */
    public static String valueOf(Object o) {
        if (o == null) return "";
        return String.valueOf(o);
    }

    /**
     * 将文本中的英文符号转化为中文符号
     */
    public static String changeString(String source) {
        if (isEmpty(source)) return "";
        String str = source.replaceAll(":", "：");
        str = str.replaceAll(",", "，");
        str = str.replaceAll("\\(", "（");
        str = str.replaceAll("\\)", "）");
        return str;
    }
}
