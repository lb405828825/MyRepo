package com.fineos.learnannotation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

//@TestAnnotation(name = "testMainActivity", gid = MainActivity.class)
public class MainActivity extends AppCompatActivity {

//    @TestAnnotation(name = "testMainActivity$onCreate()", id = 1, gid = MainActivity.class)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            parseTypeAnnotation();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        parseConstructAnnotation();
        parseMethodAnnotation();
    }



    public static void parseTypeAnnotation() throws ClassNotFoundException {
        Class clazz = Class.forName("com.fineos.learnannotation.UserAnnotation");
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            TestAnnotation testA = (TestAnnotation)annotation;
            Log.d("liubo","id= " + testA.id() + "; name= " + testA.name() + "; gid = " + testA.gid());
        }
    }

    public static void parseMethodAnnotation(){
        Method[] methods = UserAnnotation.class.getDeclaredMethods();
        for (Method method : methods) {

            boolean hasAnnotation = method.isAnnotationPresent(TestAnnotation.class);
            if (hasAnnotation) {

                TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
                Log.d("liubo", "method = " + method.getName()
                        + " ; id = " + annotation.id() + " ; description = "
                        + annotation.name() + "; gid= "+annotation.gid());
            }
        }
    }

    public static void parseConstructAnnotation(){
        Constructor[] constructors = UserAnnotation.class.getConstructors();
        for (Constructor constructor : constructors) {

            boolean hasAnnotation = constructor.isAnnotationPresent(TestAnnotation.class);
            if (hasAnnotation) {

                TestAnnotation annotation =(TestAnnotation) constructor.getAnnotation(TestAnnotation.class);
                Log.d("liubo", "constructor = " + constructor.getName()
                        + " ; id = " + annotation.id() + " ; description = "
                        + annotation.name() + "; gid= "+annotation.gid());
            }
        }
    }
}

@TestAnnotation(name = "type",gid=Long.class) //类成员注解
class UserAnnotation {
    @TestAnnotation(name="param",id=1,gid=Long.class) //类成员注解
    private Integer age;
    @TestAnnotation (name="construct",id=2,gid=Long.class)//构造方法注解
    public UserAnnotation(){
    }
    @TestAnnotation(name="public method",id=3,gid=Long.class) //类方法注解
    public void a(){
        Map m = new HashMap(0);
    }
    @TestAnnotation(name="protected method",id=4,gid=Long.class) //类方法注解
    protected void b(){
        Map m = new HashMap(0);
    }
    @TestAnnotation(name="private method",id=5,gid=Long.class) //类方法注解
    private void c(){
        Map m = new HashMap(0);
    }
    public void b(Integer a){
    }
}
