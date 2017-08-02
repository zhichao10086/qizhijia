package com.wuzhichao.test1.mvp.data.Source.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 黑客 on 2017/7/1.
 */


public class DataDBhelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "qizhijia.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";


    public static final String SQL_CREATE_PROJECT =
            "CREATE TABLE " + DataPersistnetContract.Project.TABLE_NAME + " (" +
                    DataPersistnetContract.Project._ID + "PRIMARY KEY," +
                    DataPersistnetContract.Project.COLUMN_NAME_PROJECT_ID + INTEGER_TYPE + COMMA_SEP +
                    DataPersistnetContract.Project.COLUMN_NAME_PROJECT_NAME + TEXT_TYPE + COMMA_SEP +
                    DataPersistnetContract.Project.COLUMN_NAME_PROJECT_KIND_ID + INTEGER_TYPE + COMMA_SEP +
                    DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_KIND_NAME + TEXT_TYPE + COMMA_SEP +
                    DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_NUM + INTEGER_TYPE + COMMA_SEP +
                    DataPersistnetContract.Project.COLUMN_NAME_PROJECCT_IMG + TEXT_TYPE +
                    " )";

    public DataDBhelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PROJECT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
