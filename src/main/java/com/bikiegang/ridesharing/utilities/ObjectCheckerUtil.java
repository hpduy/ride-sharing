package com.bikiegang.ridesharing.utilities;

import java.lang.reflect.Field;

/**
 * Created by hpduy17 on 6/28/15.
 */
public class ObjectCheckerUtil {
    public static String checkNull(Field field, Object target) throws IllegalAccessException {
        String message = "";
        if (field == null) {
            message = "Field is not exits in object\n";
        } else if (field.get(target) == null) {
            message = field.getName() + " is null\n";
        }
        return message;
    }

    public static String checkNull(String fieldName, Object target) throws IllegalAccessException, NoSuchFieldException {
        String message = "";
        Field field = target.getClass().getDeclaredField(fieldName);
        message = checkNull(field, target);
        return message;
    }

    public static String checkNull(Field[] fields, Object target) throws IllegalAccessException {
        String message = "";
        for (Field field : fields) {
            message += checkNull(field, target);
        }
        return message;
    }

    public static String checkNull(String[] fieldNames, Object target) throws IllegalAccessException, NoSuchFieldException {
        String message = "";
        for (String fieldName : fieldNames) {
            message += checkNull(fieldName, target);
        }
        return message;
    }

    public static String checkNull(Object target) throws IllegalAccessException, NoSuchFieldException {
        String message = "";
        message = checkNull(target.getClass().getDeclaredFields(), target);
        return message;
    }
    public static String checkNullAndId(Object target) throws IllegalAccessException {
        String message = "";
        Field [] fields = target.getClass().getDeclaredFields();
        for(Field field: fields){
            if (field.get(target) == null) {
                message = field.getName() + " is null\n";
            }else if(field.getName().toLowerCase().contains("id")){
                if(field.getLong(target) <= 0) {
                    message = field.getName() + " is invalid ( id < or = 0)\n";
                }
            }
        }
        return message;
    }
}
