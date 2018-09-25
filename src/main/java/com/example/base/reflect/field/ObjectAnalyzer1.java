package com.example.base.reflect.field;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzf
 * @date 2018/9/20 22:58.
 */
public class ObjectAnalyzer1 {

    private List<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        Class cl = obj.getClass();

        String r = cl.getName();

        do {
            r += "[";
            Field[] fields = cl.getDeclaredFields();

            AccessibleObject.setAccessible(fields, true);

            for (Field field : fields) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    if (!r.endsWith("[")) {
                        r += ", ";
                    }
                    r += field.getName() + "=";
                    try {
                        Object val = field.get(obj);
                        r += toString(val);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            r += "]";
            cl = cl.getSuperclass();
        } while (cl != null);
        return r;
    }
}
