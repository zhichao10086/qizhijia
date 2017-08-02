package com.wuzhichao.test1.dragger.MyAnnonation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by 黑客 on 2017/8/2.
 */


@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseFragmentScoped {
}
