package com.web.business.generator.util.log;
import java.lang.annotation.*;
/** 

* @author 作者 Your-Name: 

* @version 创建时间：2019年7月9日 上午11:34:23 

* 类说明 

*/
@Retention(RetentionPolicy.RUNTIME)//元注解，定义注解被保留策略，一般有三种策略
//1、RetentionPolicy.SOURCE 注解只保留在源文件中，在编译成class文件的时候被遗弃
//2、RetentionPolicy.CLASS 注解被保留在class中，但是在jvm加载的时候北欧抛弃，这个是默认的声明周期
//3、RetentionPolicy.RUNTIME 注解在jvm加载的时候仍被保留
@Target({ElementType.METHOD}) //定义了注解声明在哪些元素之前
@Documented
public @interface SystemControllerLog {
	String type() default "0" ;//描述
    String actionType() default "" ;//操作的类型，1、添加 2、修改 3、删除 4、查询  5、登录
}
