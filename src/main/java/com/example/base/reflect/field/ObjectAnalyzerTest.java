package com.example.base.reflect.field;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzf
 * @date 2018/9/20 23:07.
 */
public class ObjectAnalyzerTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(i * i);
        }
//        System.out.println(new ObjectAnalyzer().toString(5));

        Integer[] array = new Integer[2];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        System.out.println(new ObjectAnalyzer().toString(array));
    }
}
