package com.bikiegang.ridesharing.pojo.static_object;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by hpduy17 on 7/6/15.
 */
public class ApiDocument {
    private String name;
    private Class request;
    private Class response;
    private String apiDescription;
    private LinkedHashMap<String, ApiParameter> requestParameters = new LinkedHashMap<>();
    private LinkedHashMap<String, ApiParameter> responseParameters = new LinkedHashMap<>();
    private boolean responseIsArray = false;
    //final field
    public static final int REQUEST = 1;
    public static final int RESPONSE = 2;

    public ApiDocument(String name, Class request, Class response, String apiDescription, boolean responseIsArray) {
        this.name = name;
        this.request = request;
        this.response = response;
        this.apiDescription = apiDescription;
        this.responseIsArray = responseIsArray;
    }

    public void generate() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        // request generate
        if (request != null) {
            Field[] allRequestFields = getFieldExceptFinalAndStatic(request);
            generate(allRequestFields, requestParameters);
        }
        // response generate
        if (response != null) {
            Field[] allResponseFields = getFieldExceptFinalAndStatic(response);
            generate(allResponseFields, responseParameters);
        }
    }

    private void generate(Field[] allFields, LinkedHashMap<String, ApiParameter> parameterList) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Field field : allFields) {
            field.setAccessible(true);
            ApiParameter apiParameter = new ApiParameter();
            apiParameter.name = field.getName();
            if (isWrapperType(field.getType()) || isPrimaryType(field.getType().toString())) {
                apiParameter.dataType = field.getType().getSimpleName();
            } else {
                if (field.getType().getCanonicalName().contains("[]")) {
                    String childClassPath = field.getType().getCanonicalName().replaceAll("\\[\\]", "");
                    Field[] allChildFields = getFieldExceptFinalAndStatic(Class.forName(childClassPath));
                    generate(allChildFields, apiParameter.childParameter);
                    apiParameter.dataType = field.getType().getSimpleName();
                } else {
                    Field[] allChildFields = getFieldExceptFinalAndStatic(field.getType());
                    generate(allChildFields, apiParameter.childParameter);
                    apiParameter.dataType = field.getType().getSimpleName();
                }
            }
            parameterList.put(apiParameter.name, apiParameter);
        }
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

    public void importDescription(HashMap<String, String> descriptionMap, int type) {
        switch (type) {
            case 1:
                for (String parameterName : descriptionMap.keySet()) {
                    ApiParameter parameter = requestParameters.get(parameterName);
                    if (parameter != null) {
                        parameter.description = descriptionMap.get(parameterName);
                    }
                    requestParameters.put(parameterName, parameter);
                }
                break;
            case 2:
                for (String parameterName : descriptionMap.keySet()) {
                    ApiParameter parameter = responseParameters.get(parameterName);
                    if (parameter != null) {
                        parameter.description = descriptionMap.get(parameterName);
                    }
                    responseParameters.put(parameterName, parameter);
                }
                break;
        }
    }

    public void importIsRequired(HashMap<String, Boolean> isRequiredMap, int type) {
        switch (type) {
            case 1:
                for (String parameterName : isRequiredMap.keySet()) {
                    ApiParameter parameter = requestParameters.get(parameterName);
                    if (parameter != null) {
                        parameter.isRequired = isRequiredMap.get(parameterName);
                    }
                    requestParameters.put(parameterName, parameter);
                }
                break;
            case 2:
                for (String parameterName : isRequiredMap.keySet()) {
                    ApiParameter parameter = responseParameters.get(parameterName);
                    if (parameter != null) {
                        parameter.isRequired = isRequiredMap.get(parameterName);
                    }
                    responseParameters.put(parameterName, parameter);
                }
                break;
        }
    }

    public String toNiceString(int type) {
        String result = "{";
        LinkedHashMap<String, ApiParameter> src = null;
        switch (type) {
            case 1:
                src = requestParameters;
                break;
            case 2:
                src = responseParameters;
                break;
        }
        if (src == null)
            return "null";
        if (src.size() == 0)
            return "empty";
        result += toNiceString(src);
        result += "}";
        if (responseIsArray && type == 2) {
            result = "[" + result + "]";
        }
        return result;
    }

    private String toNiceString(LinkedHashMap<String, ApiParameter> src) {
        String result = "";
        int count = 0;
        for (String paramName : src.keySet()) {
            ApiParameter param = src.get(paramName);
            result += "\"" + paramName+"\"";
            //result += " - " + (param.isRequired ? "required" : "optional");
            if (!isPrimaryType(param.getDataType()) && !param.getDataType().equals("String")) {
                result += " : [{" + toNiceString(param.getChildParameter()).replaceAll("\\n", "") + "}]";
            }else{
                result += ":\"" + param.dataType + "\"";
            }
            if(count<src.keySet().size()-1){
                result+=",";
            }
            count++;

            result += " ";
        }
        return result;
    }

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();
    private static final Set<String> PRIMARY_TYPES = getPrimaryTypes();

    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    public static boolean isPrimaryType(String clazz) {
        return PRIMARY_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        ret.add(String.class);
        return ret;
    }

    private static Set<String> getPrimaryTypes() {
        Set<String> ret = new HashSet<>();
        ret.add("boolean");
        ret.add("char");
        ret.add("byte");
        ret.add("short");
        ret.add("int");
        ret.add("long");
        ret.add("float");
        ret.add("double");
        return ret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public LinkedHashMap<String, ApiParameter> getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(LinkedHashMap<String, ApiParameter> requestParameters) {
        this.requestParameters = requestParameters;
    }

    public LinkedHashMap<String, ApiParameter> getResponseParameters() {
        return responseParameters;
    }

    public void setResponseParameters(LinkedHashMap<String, ApiParameter> responseParameters) {
        this.responseParameters = responseParameters;
    }

    public boolean isResponseIsArray() {
        return responseIsArray;
    }

    public void setResponseIsArray(boolean responseIsArray) {
        this.responseIsArray = responseIsArray;
    }

    public class ApiParameter {
        public String name = "";
        public String description = "";
        public String dataType;
        public boolean dataTypeIsObject = false;
        public LinkedHashMap<String, ApiParameter> childParameter = new LinkedHashMap();
        public boolean isRequired = true;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDataType() {
            return dataType;
        }

        public void setDataType(String dataType) {
            this.dataType = dataType;
        }

        public LinkedHashMap<String, ApiParameter> getChildParameter() {
            return childParameter;
        }

        public void setChildParameter(LinkedHashMap<String, ApiParameter> childParameter) {
            this.childParameter = childParameter;
        }

        public boolean isRequired() {
            return isRequired;
        }

        public void setIsRequired(boolean isRequired) {
            this.isRequired = isRequired;
        }

        public boolean isDataTypeIsObject() {
            return dataTypeIsObject;
        }

        public void setDataTypeIsObject(boolean dataTypeIsObject) {
            this.dataTypeIsObject = dataTypeIsObject;
        }
    }
}
