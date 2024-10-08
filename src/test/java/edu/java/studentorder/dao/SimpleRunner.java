package edu.java.studentorder.dao;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {

    public static void main(String[] args) {
         SimpleRunner sr = new SimpleRunner();

         sr.runTest();
    }

    private void runTest() {
        try {
        Class cl = Class.forName("edu.java.studentorder.dao.DictionaryDaoImplTest");

        Constructor cst = cl.getConstructor();

        Object entity = cst.newInstance();

            Method[] methods = cl.getMethods();
            for (Method m : methods){
                Test ann = m.getAnnotation(Test.class);
                if (ann != null) {
                    m.invoke(entity);
                }
            }

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
