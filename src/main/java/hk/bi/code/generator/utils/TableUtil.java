package hk.bi.code.generator.utils;

import hk.bi.code.generator.bean.FieldBean;
import hk.bi.code.generator.bean.TableBean;

import java.sql.*;
import java.util.*;

/**  获取表的相关信息
 *  @author yuyanwu
 *  @date 2018-1-24
 */
public class TableUtil {

    private static Connection conn = null;

    /** 初始化数据库连接 */
    static{
        Properties props = PropertiesUtil.getProperties("db.properties");
        try {
            Class.forName(props.getProperty("jdbc.driverClassName"));
            props.setProperty("user", props.getProperty("jdbc.userName"));
            props.setProperty("password", props.getProperty("jdbc.password"));
            props.setProperty("remarks", "true");
            conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /** 获取表信息
     * @param schemaName 架构名称
     * @param tableName：表名
     * @return
     * @throws SQLException
     */
    public static TableBean getTableInfo(String schemaName,String tableName) throws SQLException {

        TableBean tableBean = new TableBean();
        // 获取数据库元数据
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        //获取当前连接的数据库名
        tableBean.setCatalog(conn.getCatalog());
        //  获取表信息
        ResultSet tableInfo = databaseMetaData.getTables(conn.getCatalog(), null, tableName, null );
        while(tableInfo.next()) {
            tableBean.setTableName(tableInfo.getString("TABLE_NAME"));
            String comments = tableInfo.getString("REMARKS");
            tableBean.setTableCommnets(comments == null ? "" : comments);
            tableBean.setClassName(StringUtil.toClassName(tableInfo.getString("TABLE_NAME")));
            // 获取表类型（view or table）
            tableBean.setTableType(tableInfo.getString("TABLE_TYPE"));
        }
        // 获取表对应的列信息
        List<FieldBean> columns = getColumns(schemaName,tableName,databaseMetaData.getDatabaseProductName(), tableBean.getTableType());
        tableBean.setColumns(columns);
        //获取表的主键
        List<FieldBean> primaryKeys = new ArrayList<FieldBean>();
        for(FieldBean fieldBean : columns){
            if(fieldBean.isPrimaryKey()){
                primaryKeys.add(fieldBean);
            }
        }
        tableBean.setPrimaryKeys(primaryKeys);
        return tableBean;
    }


    /**  获取表的列信息
     *
     * @param schemaName  架构名称
     * @param tableName   表名称
     * @param dbType      数据库类型
     * @param tableType   表类型
     * @return
     * @throws SQLException
     */
    public static List<FieldBean> getColumns(String schemaName,String tableName, String dbType, String tableType) throws SQLException {

        if("TABLE".equals(tableType)){
            return getTableColumns(schemaName,tableName);
        }else{
            return getViewColumns(tableName,dbType);
        }
    }


    /** 获取实体表的列信息
     *
     * @param tableName:表名
     * @return
     * @throws SQLException
     */
    public static List<FieldBean> getTableColumns(String schemaName,String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet columnsInfo = databaseMetaData.getColumns(null,"public", tableName,"%");
        List<String> pks = getTablePrimaryKeys(tableName);
        List<FieldBean> columns = new ArrayList<FieldBean>();
        while(columnsInfo.next()) {
            FieldBean fieldBean = new FieldBean();
            String columnName = columnsInfo.getString("COLUMN_NAME");
            fieldBean.setFieldName(columnName);
            if(pks.contains(columnName)){
                fieldBean.setPrimaryKey(true);
            }else{
                fieldBean.setPrimaryKey(false);
            }
            fieldBean.setProType(TypeConvertUtil.postgresqlToJava(columnsInfo.getString("TYPE_NAME")));
            fieldBean.setComments(columnsInfo.getString("REMARKS"));
            fieldBean.setProName(StringUtil.toVariableName(columnName));
            fieldBean.setJdbcType(TypeConvertUtil.postgresqlToJdbc(columnsInfo.getString("TYPE_NAME")));
            columns.add(fieldBean);
        }
        return columns;
    }


    /** 获取视图的列信息
     *
     * @param tableName:表名
     * @return
     * @throws SQLException
     */
    public static List<FieldBean> getViewColumns(String tableName, String dbType) throws SQLException {

        String sql = "";
        List<FieldBean> list = new ArrayList<FieldBean>();
        if("PostgreSQL".equals(dbType)){
            sql = "SELECT col_description(a.attrelid,a.attnum) as comment,pg_type.typname as typename,a.attname as name\n" +
                    "FROM pg_class as c,pg_attribute as a inner join pg_type on pg_type.oid = a.atttypid\n" +
                    "where c.relname = ? and a.attrelid = c.oid and a.attnum>0";
        }else if("".equals(dbType)){
            sql = "";
        }else{
            sql = "";
        }

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, tableName);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            FieldBean fieldBean = new FieldBean();
            fieldBean.setComments(rs.getString(1));
            fieldBean.setProType(TypeConvertUtil.postgresqlToJava(rs.getString(2)));
            fieldBean.setJdbcType(TypeConvertUtil.postgresqlToJdbc(rs.getString(2)));
            fieldBean.setFieldName(rs.getString(3));
            fieldBean.setProName(StringUtil.toVariableName(rs.getString(3)));
            fieldBean.setPrimaryKey(false);

            list.add(fieldBean);
        }
        return list;
    }

    /** 获取表的主键信息
     *
     * @param tableName:表名
     * @return
     * @throws SQLException
     */
    public static List<String> getTablePrimaryKeys(String tableName) throws SQLException {
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        ResultSet pkInfo = databaseMetaData.getPrimaryKeys(conn.getCatalog(),"public", tableName);
        List<String> pk = new ArrayList<String>();
        while(pkInfo.next()) {
            pk.add(pkInfo.getString("COLUMN_NAME"));
        }
        return pk;
    }




}
