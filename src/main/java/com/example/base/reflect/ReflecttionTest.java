package com.example.base.reflect;

import java.lang.reflect.*;
import java.util.Scanner;

/**
 *  输入一个class，打印他的 Field， Method，Constructor
 * @author zzf
 * @date 2018/9/19 08:18.
 */
public class ReflecttionTest {

    public static void main(String[] args) {
        String name;
        if (args.length > 0) {
            name = args[0];
        } else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (eg. java.util.Date):");
            name = in.next();
        }

        try {

            Class<?> cl = Class.forName(name);
            Class<?> superclass = cl.getSuperclass();
            String modifier = Modifier.toString(cl.getModifiers());
            if (modifier.length() > 0) {
                System.out.print(modifier + " ");
            }
            System.out.print("class " + name);

            if (superclass != null && superclass != Object.class) {
                System.out.print("extends " + superclass.getName());
            }
            System.out.println(" {");

//            printConstructors(cl);
            System.out.println();

            printMethods(cl);
            System.out.println();

//            printFields(cl);

            System.out.println("}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void printFields(Class<?> cl) {

        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            String modifier = Modifier.toString(field.getModifiers());

            System.out.println("    " + modifier + " " + field.getType().getTypeName() + " " + field.getName());
        }
    }

    private static void printMethods(Class<?> cl) {
        Method[] methods = cl.getDeclaredMethods();

        for (Method method : methods) {
            String modifier = Modifier.toString(method.getModifiers());

            Class<?> returnType = method.getReturnType();

            System.out.print("    " + modifier + " " + returnType + " " +  method.getName() + "(" );

            Parameter[] parameters = method.getParameters();

            for (int i = 0; i < parameters.length ; i++) {
                System.out.print(parameters[i].getParameterizedType().getTypeName() + " " + parameters[i].getName());
                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");

        }
    }

    private static void printConstructors(Class<?> cl) {

        //获取该 class 的构造器
        Constructor<?>[] constructors = cl.getDeclaredConstructors();

        for (Constructor constructor : constructors) {

            String modifier = Modifier.toString(constructor.getModifiers());
            System.out.print("    " + modifier + " " + constructor.getName() + "(");

            Parameter[] parameters = constructor.getParameters();
            for (int i = 0; i < parameters.length; i++) {
                System.out.print(parameters[i].getName());
                if (i < parameters.length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }
    }
}
