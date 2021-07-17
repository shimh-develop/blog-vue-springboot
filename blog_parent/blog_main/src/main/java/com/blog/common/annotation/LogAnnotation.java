package com.blog.common.annotation;

//Java编程语言注解设施库支持
import java.lang.annotation.*;

/**
 * 日志注解
 *
 * @author admin
 * <p>
 * 2018年4月18日
 */

//自定义方法注解在那些范围使用，method为方法
@Target(ElementType.METHOD)

 /* Retention注解保留多久，
source：注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃；被编译器忽略
class：注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期
runtime：注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在*/
@Retention(RetentionPolicy.RUNTIME)

//@Documented注解只是用来做标识，没什么实际作用,带@Documented生成的javadoc
@Documented

//@interface,@interface Annotation{ } 定义一个注解 @Annotation，一个注解是一个类
public @interface LogAnnotation {

    //注解定义，默认值为空
    String module() default "";

    String operation() default "";
}
