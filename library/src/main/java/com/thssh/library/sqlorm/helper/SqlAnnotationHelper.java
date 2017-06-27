package com.thssh.library.sqlorm.helper;


import android.text.TextUtils;

import com.thssh.library.sqlorm.annotation.DBField;
import com.thssh.library.sqlorm.annotation.DBTable;
import com.thssh.library.sqlorm.exception.SqlOrmExecption;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注解工具类
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public class SqlAnnotationHelper {

    private static final String[] EMPTY_ARRAY = new String[0];

    private Class mEntityClass;
//    private List<String> mDBFields;
    private String mTableName;
    private Map<String, Field> mCacheMap;

    public SqlAnnotationHelper(Class clazz) {
        this.mEntityClass = clazz;
//        this.mDBFields = new ArrayList<>();
        this.mCacheMap = new HashMap<>();
    }

    private void check() throws SqlOrmExecption {
        if(mEntityClass == null){ throw new SqlOrmExecption("entity class is null"); }
        if(mCacheMap == null){ throw new SqlOrmExecption("mCacheMap is not init."); }
    }

    /**
     * 反射class所有变量名
     * @return
     */
    public String[] getAllFields() throws SqlOrmExecption {
        check();
        if(mCacheMap.size() > 0){ return mCacheMap.keySet().toArray(EMPTY_ARRAY); }
        for(Field field : mEntityClass.getDeclaredFields()){
            String fieldName = field.getName();
            DBField annotation = field.getAnnotation(DBField.class);
            if(annotation != null && !TextUtils.isEmpty(annotation.value())) {
                fieldName = annotation.value();
            }
            mCacheMap.put(fieldName, field);
        }
        return mCacheMap.keySet().toArray(EMPTY_ARRAY);
    }

    /**
     * 根据class获取表名
     * @return
     */
    public String getTableName() throws SqlOrmExecption {
        check();
        if(!TextUtils.isEmpty(mTableName)) { return mTableName; }
        mTableName = mEntityClass.getSimpleName();
        DBTable annotation = (DBTable) mEntityClass.getAnnotation(DBTable.class);
        if(annotation != null && !TextUtils.isEmpty(annotation.value())){
            mTableName = annotation.value();
        }
        return mTableName;
    }

    public <T> String getFieldValue(T t, String by) throws SqlOrmExecption {
        check();
        if(mCacheMap == null || mCacheMap.size() < 1){ return null; }
        Field field = mCacheMap.get(by);
        field.setAccessible(true);
        try {
            return field.get(t).toString();
        } catch (IllegalAccessException e) {
            throw new SqlOrmExecption("invoke failed. " + e.toString());
        }
    }

    public <T> void setValueByField(T t, String sqlField, String value) throws SqlOrmExecption {
        Field field = mCacheMap.get(sqlField);
        field.setAccessible(true);
        try {
            field.set(t, value);
        } catch (IllegalAccessException e) {
            throw new SqlOrmExecption(e);
        }
    }
}
