package hk.bi.code.generator.bean;

import hk.bi.code.generator.utils.StringUtil;

import java.util.List;


/**  表模型
 *   @author yuyanwu
 *   @date 2018-1-24
 */
public class TableBean {

    /** 表名称 */
    private String tableName;
    /** 表注释 */
    private String tableCommnets;
    /** 类名 */
    private String className;
    /** 变量名(类名首字母小写) */
    private String variableName;
    /** 列 */
    private List<FieldBean> columns;
    /**  主键 */
    private List<FieldBean> primaryKeys;
    /** 类型：(view:视图   table:表) */
    private String tableType;
    /** 数据库类型 */
    private String catalog;
    /** 主键个数 */
    private int keyCount;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableCommnets() {
        return tableCommnets;
    }

    public void setTableCommnets(String tableCommnets) {
        this.tableCommnets = tableCommnets;
    }

    public List<FieldBean> getColumns() {
        return columns;
    }

    public void setColumns(List<FieldBean> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }

    public List<FieldBean> getPrimaryKeys() {
        return primaryKeys;
    }
    public void setPrimaryKeys(List<FieldBean> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public int getKeyCount() {
        return primaryKeys == null ? 0 : primaryKeys.size();
    }

    public String getVariableName() {
        return StringUtil.lowerName(className);
    }
}
