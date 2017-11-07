package com.newx.staging.config;

import java.lang.annotation.*;

/**
 * Created by Nicholas on 2017/10/21.
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {

    String name();
}
