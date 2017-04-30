package com.humorousz.commonutils.log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangzhiquan on 2017/4/24.
 */

public class JsonTools {

    public static<T> T parse(JSONObject jsonObj,Class<T> cls){
        T a ;
        try {
            a = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                setField(field, a, jsonObj);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        return a;
    }




    private static void setField(Field field,Object object,JSONObject value){
        try {

            if(Modifier.isStatic(field.getModifiers())){
                return;
            }
            Class typeClass = field.getType();
            if (typeClass ==  List.class) {
                Type type = field.getGenericType();
                ParameterizedType pt = (ParameterizedType) type;
                Class<?> dataClass = (Class<?>) pt.getActualTypeArguments()[0];
                JSONArray jsonArray = value.getJSONArray(field.getName());
                Object typeObj = createObject(typeClass);
                for (int i = 0; i < jsonArray.length(); i++) {
                    ((List<Object>) typeObj).add(parse(value.getJSONArray(field.getName()).getJSONObject(i), dataClass));
                }
                field.set(object, typeObj);
            } else if (typeClass == String.class) {
                field.set(object, value.opt(field.getName()));

            } else if (typeClass == Integer.class || typeClass == int.class) {

                field.setInt(object, Integer.valueOf(value.optString(field.getName())));

            } else if (typeClass == Double.class) {

                field.setDouble(object, Double.valueOf(value.optString(field.getName())));

            } else if (typeClass == Long.class) {

                field.setLong(object, Long.valueOf(value.optString(field.getName())));

            } else if (typeClass == Float.class) {

                field.setFloat(object, Float.valueOf(value.optString(field.getName())));

            } else {

                field.set(object,parse(value.getJSONObject(field.getName()),typeClass));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static<T>  T createObject(Class<T> cls) throws IllegalAccessException, InstantiationException {
        if(cls == List.class){
            return (T) ArrayList.class.newInstance();
        }
        if(cls == int.class){
            return (T) Integer.class.newInstance();
        }
        if(cls == double.class){
            return (T) Double.class.newInstance();
        }

        return cls.newInstance();
    }


}
