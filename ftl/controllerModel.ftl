package ${packageName}.controller;

import ${packageName}.domain.${tableInfo.className};
<#if tableInfo.keyCount gt 1 >
import ${packageName}.domain.${tableInfo.className}Key;
</#if>
import ${packageName}.service.${tableInfo.className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

/** 表 ${tableInfo.tableName} 控制层
*
* @author 自动生成
* date ${.now}
*/
@Controller
@RequestMapping("/${tableInfo.variableName}")
public class ${tableInfo.className}Controller{

    @Autowired
    private ${tableInfo.className}Service ${tableInfo.variableName}Service;

    /**  主页 **/
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        return new ModelAndView("${tableInfo.variableName}");
    }

    <#if tableInfo.keyCount gt 0 >
    /**  按主键查询记录
    *  @param key 主键
    *  @return ${tableInfo.tableCommnets}
    */
    <#if tableInfo.keyCount = 1 >
    @RequestMapping(value="/{key}", method=RequestMethod.GET)
    <#else>
    @RequestMapping(value="/select", method=RequestMethod.POST)
    </#if>
    @ResponseBody
        <#if tableInfo.keyCount = 1 >
    public ${tableInfo.className} selectRecordByKey(@PathVariable("key") ${tableInfo.primaryKeys?first.proType} key){
        <#else>
    public ${tableInfo.className} selectRecordByKey(@RequestBody ${tableInfo.className}Key key){
        </#if>
        return ${tableInfo.variableName}Service.selectRecordByKey(key);
    }
    </#if>

    <#if tableInfo.tableType == "TABLE">
    /**  表插入
    *  @param record 表记录
    *  @return 插入结果
    */
    @RequestMapping(method=RequestMethod.POST)
    @ResponseBody
    public int insertRecord(@RequestBody ${tableInfo.className} record){
        return ${tableInfo.variableName}Service.insertRecord(record);
    }
    </#if>

    <#if tableInfo.keyCount gt 0 >
    /**  按主键更新
    *  @param record 表记录
    *  @return 更新结果
    */
    @RequestMapping(method=RequestMethod.PUT)
    @ResponseBody
    public int updateRecordByKey(@RequestBody ${tableInfo.className} record){
        return ${tableInfo.variableName}Service.updateRecordByKey(record);
    }
    </#if>

    <#if tableInfo.keyCount gt 0 >
    /**  按主键删除
    *  @param key 主键
    *  @return 删除结果
    */
    <#if tableInfo.keyCount = 1 >
    @RequestMapping(value="/{key}", method=RequestMethod.DELETE)
    <#else>
    @RequestMapping(value="/{delete}",method=RequestMethod.POST)
    </#if>
    @ResponseBody
        <#if tableInfo.keyCount = 1 >
    public int deleteRecordByKey(@PathVariable("key") ${tableInfo.primaryKeys?first.proType} key){
        <#else>
    public int deleteRecordByKey(@RequestBody ${tableInfo.className}Key key){
        </#if>
    return ${tableInfo.variableName}Service.deleteRecordByKey(key);
    }
    </#if>

    /**  查询所有记录
    *  @return
    */
    @RequestMapping(value="/all", method=RequestMethod.GET)
    @ResponseBody
    public List<${tableInfo.className}> selectRecords(){
    return ${tableInfo.variableName}Service.selectRecords();
    }
}
