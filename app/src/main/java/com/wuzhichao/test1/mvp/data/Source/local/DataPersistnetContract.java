package com.wuzhichao.test1.mvp.data.Source.local;

import android.provider.BaseColumns;

/**
 * Created by 黑客 on 2017/7/1.
 */

public class DataPersistnetContract {

    public static abstract class Project implements BaseColumns {
        public static final String TABLE_NAME = "project";
        public static final String COLUMN_NAME_PROJECT_ID = "project_id";
        public static final String COLUMN_NAME_PROJECT_NAME = "project_name";
        public static final String COLUMN_NAME_PROJECT_KIND_ID = "project_kind_id";
        public static final String COLUMN_NAME_PROJECCT_KIND_NAME = "project_kind_name";
        public static final String COLUMN_NAME_PROJECCT_NUM = "project_num";
        public static final String COLUMN_NAME_PROJECCT_IMG = "project_img";
    }
}
