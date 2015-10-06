package com.bikiegang.ridesharing.DreamCatcher;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hpduy17 on 10/1/15.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ParameterDescription {
    public enum ParaType {
        PRIMARY,OBJECT,ARRAY
    }
    boolean isOptional() default false;
    String description() default "";
    ParaType type() default ParaType.PRIMARY;
}
