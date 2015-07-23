package com.framgia.lupx.androidtraining.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by FRAMGIA\pham.xuan.lu on 23/07/2015.
 */

/**
 * Annotation đánh dấu là một bảng trong SQLite
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    public String tableName();
}
