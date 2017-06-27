package com.thssh.library.sqlorm.impl;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.thssh.library.sqlorm.IBaseDao;
import com.thssh.library.sqlorm.exception.SqlOrmExecption;
import com.thssh.library.sqlorm.helper.SqlCommandHelper;
import com.thssh.library.sqlorm.helper.SqlCursorHelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public abstract class AbsDBDao<T> implements IBaseDao<T> {

    private SqlCommandHelper mSqlCommandHelper;
    private SqlCursorHelper mCursorHelper;
    private SQLiteDatabase mDatabase;
    private Class mEntityClass;

    public void init(SQLiteDatabase database, Class entityClass) {
        this.mDatabase = database;
        this.mEntityClass = entityClass;
        this.mSqlCommandHelper = new SqlCommandHelper(entityClass);
        this.mCursorHelper = new SqlCursorHelper(entityClass);
    }

    private void check() throws SqlOrmExecption {
        if(mEntityClass == null){ throw new SqlOrmExecption("entity class is null"); }
        if(mDatabase == null) { throw new SqlOrmExecption("database is null"); }
        if(mSqlCommandHelper == null){ throw new SqlOrmExecption("SqlCommandHelper not init."); }
        if(mCursorHelper == null){ throw new SqlOrmExecption("SqlCursorHelper not init."); }
        if(!mDatabase.isOpen()) { throw new SqlOrmExecption("database is not open"); }
    }

    @Override
    public long insert(T entity) throws SqlOrmExecption {
        check();
        return mDatabase.insert(mSqlCommandHelper.tableName(),
                mSqlCommandHelper.nullColumnHack(),
                mSqlCommandHelper.createContentValues(entity));
    }

    @Override
    public int delete(T where) throws SqlOrmExecption {
        check();
        return 0;
    }

    @Override
    public int delete(T entity, String... bys) throws SqlOrmExecption {
        check();
        return 0;
    }

    @Override
    public int clear() throws SqlOrmExecption {
        check();
        return 0;
    }

    @Override
    public int update(T entity, T where) throws SqlOrmExecption {
        check();
        return 0;
    }

    @Override
    public int update(T entity, String... bys) throws SqlOrmExecption {
        check();
        return 0;
    }

    @Override
    public List<T> query(QueryBuilder builder) throws SqlOrmExecption {
        check();
        Cursor cursor = mDatabase.query(mSqlCommandHelper.tableName(),
                builder.columns,
                mSqlCommandHelper.buildSelection(builder.columns),
                builder.columnArgs,
                builder.groupBy,
                builder.having,
                builder.orderBy);
        return mCursorHelper.parser(cursor);
    }

    @Override
    public void createTable() throws SqlOrmExecption {
        check();
        mDatabase.execSQL(mSqlCommandHelper.buildCreateSql());
    }

    @Override
    public void upgradeTable() throws SqlOrmExecption {
        check();
        mDatabase.execSQL(mSqlCommandHelper.buildUpgradeSql());
    }

    @Override
    public void dropTable() throws SqlOrmExecption {
        check();
        mDatabase.execSQL(mSqlCommandHelper.dropTableSql());
    }

    public QueryBuilder<T> newBuilder(){
        return new QueryBuilder<T>().dao(this);
    }

    public Class getType(){
        Class<T> entityClass = null;
        Type t = getClass().getGenericSuperclass();
        if(t instanceof ParameterizedType){
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<T>)p[0];
        }
        return entityClass;
    }
}
