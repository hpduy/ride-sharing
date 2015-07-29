package com.bikiegang.ridesharing.utilities;

import com.bikiegang.ridesharing.parsing.Parser;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hpduy17 on 7/24/15.
 */
public class APIAutoTesting {

    public String createTestObject(Class objectClass) throws NoSuchMethodException, IllegalAccessException, InstantiationException, ClassNotFoundException, JsonProcessingException {
        Object tester = objectClass.newInstance();
        Field[] fields = getFieldExceptFinalAndStatic(objectClass);
        for(Field f : fields){
            Class type = f.getType();
            f.setAccessible(true);
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
                f.set(tester, RandomStringUtils.randomAlphabetic(10));
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
        return Parser.ObjectToJSon(tester).replaceAll("\\n","").replaceAll(" ","");
    }
    private Field[] getFieldExceptFinalAndStatic(Class cls) {
        List<Field> results = new ArrayList<>();
        Class<?> i = cls;
        while (i != null && i != Object.class) {
            results.addAll(Arrays.asList(i.getDeclaredFields()));
            i = i.getSuperclass();
        }
        for (Field f : new ArrayList<>(results)) {
            if (Modifier.isFinal(f.getModifiers()) || Modifier.isStatic(f.getModifiers())) {
                results.remove(f);
            }
        }

        return results.toArray(new Field[results.size()]);
    }
}
