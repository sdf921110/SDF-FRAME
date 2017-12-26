package com.sdf.codeGeneration.code.tool.db;

import com.sdf.codeGeneration.code.tool.model.FieldBean;
import com.sdf.codeGeneration.code.tool.utils.StringUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;


/**
 * DbServiceImpl
 *
 * @author: SDF
 * @dateTime: 2017-3-11 下午10:06:16
 * @version: 1.0.0
 */
public class DbServiceImpl implements DbService {

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String sqlstr = null;

    /**
     * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型、字段注释
     *
     * @param tableName 表名
     * @return
     * @time 2017年3月13日 下午5:24:58
     */
    @Override
    public List<FieldBean> getTableInfo(String tableName) {
        List<FieldBean> result = new ArrayList<>();

        DatabaseMetaData dbmd = null;
        DbConn dbConn = new DbConn();

        try {
            this.conn = dbConn.getConnection();
            dbmd = conn.getMetaData();
            ResultSet resultSet = dbmd.getTables(null, "%", tableName, new String[]{"TABLE"});

            while (resultSet.next()) {
                String tableName1 = resultSet.getString("TABLE_NAME");
                if (tableName1.equals(tableName)) {
                    ResultSet rs = conn.getMetaData().getColumns(null, getSchema(conn), tableName.toUpperCase(), "%");

                    while (rs.next()) {
                        FieldBean fieldBean = new FieldBean();
                        String colName = rs.getString("COLUMN_NAME");

                        fieldBean.setFieldName(colName.toLowerCase());
                        fieldBean.setProName(colName);
                        fieldBean.setBigProName(
                                colName.substring(0, 1).toUpperCase() + colName.substring(1, colName.length()));

                        String remarks = rs.getString("REMARKS");
                        if (remarks == null || remarks.equals("")) {
                            remarks = colName;
                        }
                        fieldBean.setFieldDesc(remarks);
                        String dbType = rs.getString("TYPE_NAME");
                        String typeName = changeDbType(dbType);
                        fieldBean.setProType(typeName);
                        fieldBean.setIntProType("Integer".equals(typeName) ? "Int" : typeName);

                        result.add(fieldBean);
                    }
                    // break;
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    /**
     * 根据数据库的连接参数，获取指定表的基本信息：字段名、字段类型
     * <p>
     * ************ (作废) ************
     *
     * @param tableName
     * @return
     */
    @Override
    public List<FieldBean> getAllColums(String tableName) {

        ArrayList<FieldBean> returnList = new ArrayList<FieldBean>();
        DbConn dbConn = new DbConn();

        try {
            this.conn = dbConn.getConnection();
            this.sqlstr = "select * from " + tableName;
            this.st = conn.createStatement();
            this.rs = this.st.executeQuery(sqlstr);
            ResultSetMetaData resultSetMetaData = rs.getMetaData();

            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                String columName = resultSetMetaData.getColumnName(i);
                String proName = StringUtils.convertColumnField(columName);

                String dataType = getTypeName(resultSetMetaData.getColumnType(i));

                FieldBean fieldBean = new FieldBean();
                fieldBean.setFieldName(columName.toLowerCase());
                fieldBean.setProName(proName);
                fieldBean.setProType(dataType);

                returnList.add(fieldBean);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConn.closeALL(conn, st, rs, pst);
        }
        return returnList;

    }

    /**
     * 获取字符串型的类型名
     *
     * @param type
     * @return
     */
    private String getTypeName(int type) {

        String typeName = String.class.getSimpleName();

        switch (type) {
            case Types.VARCHAR:
            case Types.CHAR:
            case Types.BLOB:
            case Types.CLOB:
            case Types.NCHAR:
            case Types.NCLOB:
            case Types.NVARCHAR:
                break;
            case Types.INTEGER:
            case Types.SMALLINT:
            case Types.BIGINT:
            case Types.NUMERIC:
                typeName = Integer.class.getSimpleName();
                break;
            case Types.DATE:
            case Types.TIMESTAMP:
                typeName = Timestamp.class.getSimpleName();
                break;
            case Types.BOOLEAN:
                typeName = Boolean.class.getSimpleName();
                break;
            case Types.FLOAT:
                typeName = Float.class.getSimpleName();
                break;
            case Types.DOUBLE:
                typeName = Double.class.getSimpleName();
                break;
            case Types.DECIMAL:
                typeName = BigDecimal.class.getSimpleName();
                break;
            default:
                break;
        }

        return typeName;

    }

    // 其他数据库不需要这个方法 oracle和db2需要
    private static String getSchema(Connection conn) throws Exception {
        String schema;
        schema = conn.getMetaData().getUserName();
        if ((schema == null) || (schema.length() == 0)) {
            throw new Exception("ORACLE数据库模式不允许为空");
        }
        return schema.toUpperCase().toString();

    }

    /**
     * 获取字符串型的类型名(含字段注释方法)
     *
     * @param dbType
     * @return
     * @time 2017年3月13日 下午9:16:46
     */
    private static String changeDbType(String dbType) {
        dbType = dbType.toUpperCase();
        switch (dbType) {

            case "VARCHAR":
            case "VARCHAR2":
            case "CHAR":
            case "BLOB":
            case "CLOB":
            case "NCHAR":
            case "NCLOB":
            case "NVARCHAR":
            case "NVARCHAR2":
            case "TEXT":
                return "String";

            case "INT":
            case "TINYINT":
            case "BIGINT":
            case "SMALLINT":
            case "INTEGER":
            case "NUMBER":
            case "NUMERIC":
                return "Integer";

            case "FLOAT":
                return "Float";

            case "DOUBLE":
                return "Double";

            case "LONG":
                return "long";

            case "DECIMAL":
                return "BigDecimal";

            case "BOOLEAN":
                return "Boolean";

            case "DATETIME":
            case "TIMESTAMP":
            case "DATE":
                return "Timestamp";

            default:
                return "unknow";
        }
    }

}
