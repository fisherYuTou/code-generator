package hk.bi.code.generator.utils;

import org.apache.commons.lang3.StringUtils;

public class TypeConvertUtil {

    /** oracle类型转化成JAVA类型
     *
     * @param typeName  类型名称
     * @param length    总长度
     * @param demicalLength  小位数长度
     * @return
     */
    public static String oracleToJava(String typeName, String length, String demicalLength){

        String returnType;
        if("NUMBER".equals(typeName)){
            if(demicalLength != null && Integer.parseInt(demicalLength) == 0){
                returnType = "int";
            }else{
                returnType = "BigDecimal";
            }
        }else if( "DATE".equals(typeName) || "TIMESTAMP(6)".equals(typeName)){
            returnType = "Timestamp";
        }else if( "BLOB".equals(typeName)){
            returnType = "byte[]";
        }else if( "CLOB".equals(typeName)){
            returnType = "String";
        }else{
            returnType = "String";
        }
        return returnType;
    }

    /** oracle类型转化成jdbc类型
     *
     * @param typeName  类型名称
     * @param length    总长度
     * @param demicalLength  小位数长度
     * @return
     */
    public static String oracleToJdbc(String typeName, String length, String demicalLength){

        String returnType;
        if("NUMBER".equals(typeName)){
            if(demicalLength != null && Integer.parseInt(demicalLength) == 0){
                returnType = "INTEGER";
            }else{
                returnType = "DECIMAL";
            }
        }else if( "DATE".equals(typeName) || "TIMESTAMP(6)".equals(typeName)){
            returnType = "TIMESTAMP";
        }else if( "BLOB".equals(typeName)){
            returnType = "BLOB";
        }else if( "CLOB".equals(typeName)){
            returnType = "CLOB";
        }else if( "DATE".equals(typeName) || "TIMESTAMP(6)".equals(typeName)){
            returnType = "TIMESTAMP";
        }else{
            returnType = "VARCHAR";
        }
        return returnType;
    }


    /** postgresql类型转化成JAVA类型
     *
     * @param typeName  类型名称
     * @return
     */
    public static String postgresqlToJava(String typeName){

        String returnType;
        if("int2".equals(typeName)){
            returnType = "short";
        }else if( "int4".equals(typeName)){
            returnType = "int";
        }else if( "int8".equals(typeName)){
            returnType = "long";
        }else if( "numeric".equals(typeName)){
            returnType = "BigDecimal";
        }else if( "varchar".equals(typeName)){
            returnType = "String";
        }else if( "date".equals(typeName) || "timestamp".equals(typeName)){
            returnType = "Timestamp";
        }else{
            returnType = "String";
        }
        return returnType;
    }

    /** oracle类型转化成jdbc类型
     *
     * @param typeName  类型名称
     * @return
     */
    public static String postgresqlToJdbc(String typeName){

        String returnType;

        if("int2".equals(typeName)){
            returnType = "SMALLINT";
        }else if( "int4".equals(typeName)){
            returnType = "INTEGER";
        }else if( "int8".equals(typeName)){
            returnType = "BIGINT";
        }else if( "numeric".equals(typeName)){
            returnType = "DECIMAL";
        }else if( "varchar".equals(typeName)){
            returnType = "VARCHAR";
        }else if( "date".equals(typeName) || "timestamp".equals(typeName)){
            returnType = "TIMESTAMP";
        }else{
            returnType = "VARCHAR";
        }
        return returnType;
    }


}
