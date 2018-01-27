package ${packageName}.domain;

import java.math.*;
import java.sql.*;

/** 表 ${tableInfo.tableName} 模型
*
* @author 自动生成
* date ${.now}
*/

public class ${tableInfo.className} {

    <#-- 生成字段列举  -->
    <#list tableInfo.columns as column>
    /** ${column.comments} */
    private  ${column.proType} ${column.proName};
    </#list>

    <#-- 生成set方法和get方法 -->
    <#list tableInfo.columns as column>
    public ${column.proType} get${column.proName?cap_first}() {
    return this.${column.proName};
    }
    public void set${column.proName?cap_first}(${column.proType} ${column.proName}) {
    this.${column.proName} = ${column.proName};
    }
    </#list>

}
