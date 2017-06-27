package com.thssh.library.sqlorm.exception;

/**
 * @author zhangyugehu
 * @version V1.0
 * @data 2017/06/27
 */

public class SqlOrmExecption extends Exception {

    public SqlOrmExecption() {
    }

    public SqlOrmExecption(String message) {
        super(message);
    }

    public SqlOrmExecption(Throwable cause) {
        super(cause);
    }
}
