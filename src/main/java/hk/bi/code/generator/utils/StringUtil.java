package hk.bi.code.generator.utils;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;

public class StringUtil {

    /**
     * 转化成属性名 例如：user_name to userName
     * @param field
     * @return
     */
    public static String toVariableName(String field) {
        if (null == field) {
            return "";
        }
        field = field.toLowerCase();
        char[] chars = field.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '_') {
                int j = i + 1;
                if (j < chars.length) {
                    sb.append(StringUtils.upperCase(CharUtils.toString(chars[j])));
                    i++;
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /** 首字母大写
     *
     * @param name
     * @return
     */
    public static String captureName(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }

    /** 首字母小写
     *
     * @param name
     * @return
     */
    public static String lowerName(String name) {
        char[] cs=name.toCharArray();
        cs[0]+=32;
        return String.valueOf(cs);
    }

    /** 首字母和末字母大写
     *
     * @param name
     * @return
     */
    public static String captureNameDouble(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        cs[cs.length-1] -= 32;
        return String.valueOf(cs);
    }



    /** 转化成类名
     *
     * @param tableName
     * @return
     */
    public static String toClassName(String tableName) {
        return captureName(toVariableName(tableName));
    }

    /** 转化成VO名称
     *
     * @param tableName
     * @return
     */
    public static String toVoName(String tableName) {
        return captureNameDouble(toVariableName(tableName));
    }

    public static void main(String[] args) {
        System.out.println(toVoName("test1_vo"));
    }
 
}
