package ${packageName}.service;

import ${packageName}.domain.${tableInfo.className};
<#if tableInfo.keyCount gt 1 >
import ${packageName}.domain.${tableInfo.className}Key;
</#if>
import java.util.*;

/** 表 ${tableInfo.tableName} service
* @author 自动生成
* date ${.now}
*/
public interface ${tableInfo.className}Service {

    <#if tableInfo.keyCount gt 0 >
    /**  按主键查询"${tableInfo.tableCommnets}"
    *  @param key 主键
    *  @return ${tableInfo.tableCommnets}
    */
        <#if tableInfo.keyCount = 1 >
    ${tableInfo.className} selectRecordByKey(${tableInfo.primaryKeys?first.proType} key);
        <#else>
    ${tableInfo.className} selectRecordByKey(${tableInfo.className}Key key);
        </#if>
    </#if>


    <#if tableInfo.tableType == "TABLE">
    /**  表插入
    *  @param record 表记录
    *  @return 插入结果
    */
    int insertRecord(${tableInfo.className} record);
    </#if>

    <#if tableInfo.keyCount gt 0 >
    /**  按主键更新
    *  @param record 表记录
    *  @return 更新结果
    */
    int updateRecordByKey(${tableInfo.className} record);
    </#if>

    <#if tableInfo.keyCount gt 0 >
    /**  按主键删除
    *  @param key 主键
    *  @return 删除结果
    */
        <#if tableInfo.keyCount = 1 >
    int deleteRecordByKey(${tableInfo.primaryKeys?first.proType} key);
        <#else>
    int deleteRecordByKey(${tableInfo.className}Key key);
        </#if>
    </#if>


    /**  查询所有记录
    *  @return
    */
    List<${tableInfo.className}> selectRecords();
}
