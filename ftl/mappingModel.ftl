<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${tableInfo.className}Mapper">
    <resultMap id="BaseResultMap" type="${packageName}.domain.${tableInfo.className}">
    <#list tableInfo.columns as column>
    <#if (column.primaryKey) >
        <id column="${column.fieldName}" jdbcType="${column.jdbcType}" property="${column.proName}" />
    <#else >
        <result column="${column.fieldName}" jdbcType="${column.jdbcType}" property="${column.proName}" />
    </#if>
    </#list>
    </resultMap>

    <sql id="Base_Column_List">
    <#list tableInfo.columns as column>
    <#if column_index = 0>
         ${column.fieldName}
    <#else>
        ,${column.fieldName}
    </#if>
    </#list>
    </sql>


    <#if tableInfo.keyCount gt 0 >
    <select id="selectRecordByKey"
        <#if tableInfo.keyCount = 1 >
            parameterType="${tableInfo.primaryKeys?first.proType}"
        <#else>
            parameterType="${packageName}.domain.${tableInfo.className}Key"
        </#if>
            resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${tableInfo.tableName}
        <#list tableInfo.primaryKeys as column>
            <#if column_index = 0>
        where ${column.fieldName} =  ${column.jdbcQuery}
            <#else>
          and ${column.fieldName} =  ${column.jdbcQuery}
            </#if>
        </#list>
    </select>
    </#if>


    <#if tableInfo.tableType = "TABLE">
    <insert id="insertRecord" parameterType="${packageName}.domain.${tableInfo.className}">
        insert into ${tableInfo.tableName} (
        <#list tableInfo.columns as column>
            <#if column_index = 0>
                 ${column.fieldName}
            <#else>
                ,${column.fieldName}
            </#if>
        </#list>
        )
        values (
        <#list tableInfo.columns as column>
            <#if column_index = 0>
                 ${column.jdbcQuery}
            <#else>
                ,${column.jdbcQuery}
            </#if>
        </#list>
        )
    </insert>
    </#if>

    <#if tableInfo.keyCount gt 0 >
    <update id="updateRecordByKey" parameterType="${packageName}.domain.${tableInfo.className}">
        update ${tableInfo.tableName}
        <set>
            <#list tableInfo.columns as column>
                <#if !column.primaryKey>
            ${column.fieldName} = ${column.jdbcQuery},
                </#if>
            </#list>
        </set>
        <#list tableInfo.primaryKeys as column>
            <#if column_index = 0>
        where ${column.fieldName} =  ${column.jdbcQuery}
            <#else>
          and ${column.fieldName} =  ${column.jdbcQuery}
            </#if>
        </#list>
    </update>
    </#if>


<#if tableInfo.keyCount gt 0 >
    <update id="updateRecordSelectiveByKey" parameterType="${packageName}.domain.${tableInfo.className}">
        update ${tableInfo.tableName}
        <set>
            <#list tableInfo.columns as column>
                <#if !column.primaryKey>
            <if test="${column.proName}!=null">${column.fieldName} = ${column.jdbcQuery},</if>
                </#if>
            </#list>
        </set>
        <#list tableInfo.primaryKeys as column>
            <#if column_index = 0>
         where ${column.fieldName} =  ${column.jdbcQuery}
            <#else>
                and ${column.fieldName} =  ${column.jdbcQuery}
            </#if>
        </#list>
    </update>
</#if>



    <#if tableInfo.keyCount gt 0 >
    <delete id="deleteRecordByKey"
        <#if tableInfo.keyCount = 1 >
            parameterType="${tableInfo.primaryKeys?first.proType}">
        <#else>
            parameterType="${packageName}.domain.${tableInfo.className}Key">
        </#if>
        delete from ${tableInfo.tableName}
        <#list tableInfo.primaryKeys as column>
            <#if column_index = 0>
        where ${column.fieldName} =  ${column.jdbcQuery}
            <#else>
          and ${column.fieldName} =  ${column.jdbcQuery}
            </#if>
        </#list>
    </delete>
    </#if>

    <select id="selectRecords"  resultMap="BaseResultMap">
        select
        <include refid="Base_Column_List" />
        from ${tableInfo.tableName}
    </select>

</mapper>
