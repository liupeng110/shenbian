<<<<<<< HEAD
package com.sbh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解用于导数据中,确定导出字段在excel中的位置
 * @author zhouwei
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Position {
	int value() default Integer.MAX_VALUE;
}
=======
package com.sbh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 这个注解用于导数据中,确定导出字段在excel中的位置
 * @author zhouwei
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Position {
	int value() default Integer.MAX_VALUE;
}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
