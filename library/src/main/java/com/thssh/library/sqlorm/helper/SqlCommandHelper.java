package com.thssh.library.sqlorm.helper;

import android.content.ContentValues;
import android.text.TextUtils;

import com.thssh.library.sqlorm.exception.SqlOrmExecption;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库语句辅助类
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public class SqlCommandHelper {

    static class Contact {
        /** , */
        public static final String COMMA = ",";
        /** [空格] */
        public static final String SPACE = " ";
        /** ( */
        public static final String LEFT = "(";
        /** ) */
        public static final String RIGHT = ")";
        /** = */
        public static final String EQUAL = "=";
        /** ? */
        public static final String QUESTION = "?";

        public static final String _ID = "_id";
        public static final String PRIMARY_KEY_AUTOINCREMENT = "INTEGER PRIMARY KEY AUTOINCREMENT";
        public static final String CREATE = "CREATE TABLE";
        public static final String NOT_EXISTS = "IF NOT EXISTS";
        public static final String EXISTS = "IF EXISTS";
        public static final String TEXT = "TEXT";
        public static final String DROP = "DROP";
        public static final String WHERE = "WHERE";
        public static final String AND = "AND";
    }
    private static final String[] EMPTY_ARRAY = new String[0];

    private Class mEntityClass;
    private SqlAnnotationHelper mSqlAnnotationHelper;

    public SqlCommandHelper(Class clazz) {
        this.mEntityClass = clazz;
        this.mSqlAnnotationHelper = new SqlAnnotationHelper(clazz);
    }

    private void check() throws SqlOrmExecption {
        if(mEntityClass == null) { throw new SqlOrmExecption("entity class is null."); }
        if(mSqlAnnotationHelper == null){ throw new SqlOrmExecption("SqlAnnotationHelper not init."); }
    }

    /**
     * 提供表名
     * @return
     * @throws SqlOrmExecption
     */
    public String tableName() throws SqlOrmExecption {
        check();
        return mSqlAnnotationHelper.getTableName();
    }

    /**
     * 提供非空列
     * @return
     * @throws SqlOrmExecption
     */
    public String nullColumnHack() throws SqlOrmExecption {
        check();
        return Contact._ID;
    }

    /**
     * 转换ContentValues
     * @param bys 数据库字段，非变量名
     * @return
     * @throws SqlOrmExecption
     */
    public <T> ContentValues createContentValues(T t, String... bys) throws SqlOrmExecption {
        check();
        ContentValues cv = new ContentValues();
        if(bys == null || bys.length <= 0) {
            bys = mSqlAnnotationHelper.getAllFields();
        }
        if(bys == null){ return cv; }
        for(String by : bys){
            String value = mSqlAnnotationHelper.getFieldValue(t, by);
            if(TextUtils.isEmpty(value)){ continue; }
            cv.put(by, value);
        }
        return cv;
    }

    /**
     * 建表语句
     *
     * @return
     */
    public String buildCreateSql() throws SqlOrmExecption {
        check();
        if(mEntityClass == null) { return null; }
        StringBuilder builder = new StringBuilder(Contact.CREATE).append(Contact.SPACE);
        String tableName = mSqlAnnotationHelper.getTableName();
        builder .append(Contact.NOT_EXISTS).append(Contact.SPACE)
                .append(tableName).append(Contact.SPACE)
                .append(Contact.LEFT)
                .append(Contact._ID).append(Contact.SPACE)
                .append(Contact.PRIMARY_KEY_AUTOINCREMENT).append(Contact.COMMA);
        String[] fields = mSqlAnnotationHelper.getAllFields();
        if(fields != null) {
            for (String field : fields) {
                builder.append(field).append(Contact.SPACE).append(Contact.TEXT)
                        .append(Contact.COMMA);
            }
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append(Contact.RIGHT);
        return builder.toString();
    }

    /**
     * 删除表语句
     * @return
     * @throws SqlOrmExecption
     */
    public String dropTableSql() throws SqlOrmExecption {
        check();
        StringBuilder builder = new StringBuilder(Contact.DROP).append(Contact.SPACE);
        builder .append(Contact.EXISTS).append(Contact.SPACE)
                .append(mSqlAnnotationHelper.getTableName());
        return builder.toString();
    }

    /**
     * 升级表语句
     * @return
     * @throws SqlOrmExecption
     */
    public String buildUpgradeSql() throws SqlOrmExecption {
        check();
        return null;
    }

    public String buildSelection(String[] columns) {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; columns != null && i < columns.length; i++){
            String column = columns[i];
            builder.append(column).append(Contact.EQUAL).append(Contact.QUESTION);
            if(i != columns.length -1){
                builder.append(Contact.SPACE).append(Contact.AND).append(Contact.SPACE);
            }
        }
        return builder.toString();
    }

    public <T> String[] buildSelectionArgs(T entity, String[] columns) throws SqlOrmExecption {
        List<String> columnArgs = new ArrayList<>();
        for(int i = 0; columns != null && i < columns.length; i++){
            columnArgs.add(mSqlAnnotationHelper.getFieldValue(entity, columns[i]));
        }
        return columnArgs.toArray(EMPTY_ARRAY);
    }
}
