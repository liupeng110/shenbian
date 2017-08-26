package com.sbh.annotation;

import java.lang.annotation.*;

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
