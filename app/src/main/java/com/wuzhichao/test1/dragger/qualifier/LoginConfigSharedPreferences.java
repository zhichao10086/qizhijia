package com.wuzhichao.test1.dragger.qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by 黑客 on 2017/8/1.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginConfigSharedPreferences {
}
