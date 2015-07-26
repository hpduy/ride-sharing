package com.bikiegang.ridesharing.utilities;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

/**
 * Created by hpduy17 on 7/24/15.
 */
public class APIAutoTesting {

    public Object createTestObject(Class objectClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        Object tester = objectClass.newInstance();
        Field[] fields = objectClass.getDeclaredFields();
        for(Field f : fields){
            Class type = f.getType();
            if(type.toString().equals("boolean")||type.equals(Boolean.class)){
                f.set(tester, RandomUtils.nextBoolean());
            }
            else if (type.toString().equals("short")||type.equals(Short.class)){
                f.set(tester, RandomUtils.nextInt(300));
            }
            else if (type.toString().equals("int")||type.equals(Integer.class)){
                f.set(tester, RandomUtils.nextInt());
            }
            else if (type.toString().equals("long")||type.equals(Long.class)){
                f.set(tester, RandomUtils.nextLong());
            }
            else if (type.toString().equals("float")||type.equals(Float.class)){
                f.set(tester, RandomUtils.nextFloat());
            }
            else if (type.toString().equals("double")||type.equals(Double.class)){
                f.set(tester, RandomUtils.nextDouble());
            }
            else if (type.equals(String.class)){
                f.set(tester, RandomStringUtils.random(10));
            }
            else if (type.toString().equals("char")||type.equals(Character.class)){
                f.set(tester, 'D');
            }
            else if (type.toString().equals("byte")||type.equals(Byte.class)){
                f.set(tester, RandomStringUtils.random(2).getBytes());
            }
            else {
                if(type.getCanonicalName().contains("[]")){
                    String childClassPath = type.getCanonicalName().replaceAll("\\[\\]", "");
                    Object arr  =  Array.newInstance(Class.forName(childClassPath),2);
                    f.set(tester, arr);
                }else{
                    f.set(tester,createTestObject(type));
                }

            }

        }
        return tester;
    }
}
