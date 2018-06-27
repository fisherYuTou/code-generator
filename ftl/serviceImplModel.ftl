package ${packageName}.service.impl;

import ${packageName}.domain.${tableInfo.className};
<#if tableInfo.keyCount gt 1 >
import ${packageName}.domain.${tableInfo.className}Key;
</#if>
import ${packageName}.mapper.${tableInfo.className}Mapper;
import ${packageName}.service.${tableInfo.className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/** 表 ${tableInfo.tableName} service实现
*
* @author 自动生成
* date ${.now}
*/
@Service
public class ${tableInfo.className}ServiceImpl implements ${tableInfo.className}Service{

    @Autowired
    private ${tableInfo.className}Mapper ${tableInfo.variableName}Mapper;

    <#if tableInfo.keyCount gt 0 >
    /**  按主键查询记录
    *  @param key 主键
    *  @return ${tableInfo.tableCommnets}
    */
    @Override
        <#if tableInfo.keyCount = 1 >
    public ${tableInfo.className} selectRecordByKey(${tableInfo.primaryKeys?first.proType} key){
        <#else>
    public ${tableInfo.className} selectRecordByKey(${tableInfo.className}Key key){
        </#if>
        return ${tableInfo.variableName}Mapper.selectRecordByKey(key);
    }
    </#if>
    <#if tableInfo.tableType == "TABLE">
    /**  表插入
    *  @param record 表记录
    *  @return 插入结果
    */
    @Override
    public int insertRecord(${tableInfo.className} record){
        return ${tableInfo.variableName}Mapper.insertRecord(record);
    }
    </#if>
    <#if tableInfo.keyCount gt 0 >
    /**  按主键更新
    *  @param record 表记录
    *  @return 更新结果
    */
    @Override
    public int updateRecordByKey(${tableInfo.className} record){
        return ${tableInfo.variableName}Mapper.updateRecordByKey(record);
    }
    </#if>
    <#if tableInfo.keyCount gt 0 >
    /**  按主键更新（为空则不更新）
    *  @param record 表记录
    *  @return 更新结果
    */
    @Override
    public int updateRecordSelectiveByKey(${tableInfo.className} record){
        return ${tableInfo.variableName}Mapper.updateRecordSelectiveByKey(record);
    }
    </#if>
    <#if tableInfo.keyCount gt 0 >
    /**  按主键删除
    *  @param key 主键
    *  @return 删除结果
    */
    @Override
        <#if tableInfo.keyCount = 1 >
    public int deleteRecordByKey(${tableInfo.primaryKeys?first.proType} key){
        <#else>
    public int deleteRecordByKey(${tableInfo.className}Key key){
        </#if>
        return ${tableInfo.variableName}Mapper.deleteRecordByKey(key);
    }
    </#if>
    /**  查询所有记录
    *  @return
    */
    @Override
    public List<${tableInfo.className}> selectRecords(){
        return ${tableInfo.variableName}Mapper.selectRecords();
    }



}
