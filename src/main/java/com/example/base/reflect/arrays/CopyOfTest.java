package com.example.base.reflect.arrays;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author zzf
 * @date 2018/9/25 22:56.
 */
public class CopyOfTest {

    public static void main(String[] args) {

        int[] a= {1, 2 ,3};
        a = (int[])goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] b = {"tom", "jack"};
        b = (String[])goodCopyOf(b, 10);

        System.out.println("The following call will generate an exception:");
        b = (String[])badCopyOf(b, 10);
    }

    /**
     * 类型限定，强转 异常
     * @param b
     * @param newLength
     * @return
     */
    private static Object badCopyOf(Object[] b, int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(b, 0, newArray, 0, Math.min(newLength, b.length));
        return newArray;
    }

    private static Object goodCopyOf(Object a, int newLength) {

        Class<?> cl = a.getClass();
        if (!cl.isArray()) {
            return null;
        }

        Class<?> componentType = cl.getComponentType();
        int length = Array.getLength(a);
        Object newArray = Array.newInstance(componentType, length);
        System.arraycopy(a, 0, newArray, 0, Math.min(newLength, length));
        return newArray;
    }


}
