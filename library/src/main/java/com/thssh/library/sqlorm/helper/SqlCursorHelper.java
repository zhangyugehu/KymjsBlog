package com.thssh.library.sqlorm.helper;

import android.database.Cursor;

import com.thssh.library.sqlorm.exception.SqlOrmExecption;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public class SqlCursorHelper<T> {
    private SqlAnnotationHelper mSqlAnnotationHelper;
    private Class mEntityClass;

    public SqlCursorHelper(Class clazz) {
        this.mEntityClass = clazz;
        this.mSqlAnnotationHelper = new SqlAnnotationHelper(clazz);
    }

    public List<T> parser(Cursor cursor) throws SqlOrmExecption {

        List<T> rst = new ArrayList<>();
        while (cursor.moveToNext()){
            try {
                T t = null;
                for(String field:mSqlAnnotationHelper.getAllFields()){
                    t = (T) mEntityClass.newInstance();
                    int index = cursor.getColumnIndex(field);
                    if(index < 0) { continue; }
                    String value = cursor.getString(index);
                    mSqlAnnotationHelper.setValueByField(t, field, value);
                    rst.add(t);
                }
            } catch (Exception e) {
                throw new SqlOrmExecption("parser course. " + e.toString());
            }
        }
        return rst;
    }
}
