package hk.bi.code.generator.bean;

/** 列模型
 * @author yuyanwu
 * @date 2018-1-24
 */
public class FieldBean {

    /** 数据库原字段名 */
    private String fieldName;
    /** 变量名 */
    private String proName;
    /** 变量类型 */
    private String proType;
    /** jdbc变量类型 */
    private String jdbcType;
    /** 变量说明 */
    private String comments;
    /** 是否是主键 */
    private boolean primaryKey;
    /** 拼装jdbc查询 */
    private String jdbcQuery;

    public String getProName() {
        return proName;
    }
    public void setProName(String proName) {
        this.proName = proName;
    }
    public String getProType() {
        return proType;
    }
    public void setProType(String proType) {
        this.proType = proType;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getComments() {
        return comments == null ? "" : comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getJdbcQuery() {
        return "#{"+proName+",jdbcType="+jdbcType+"}";
    }

}
