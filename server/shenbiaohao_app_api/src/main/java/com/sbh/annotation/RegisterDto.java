<<<<<<< HEAD
package com.sbh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用于DAO，注册DTO，用于DAO通用操作,传参，返回值
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterDto {
	Class<?>  value();
}
=======
package com.sbh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用于DAO，注册DTO，用于DAO通用操作,传参，返回值
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RegisterDto {
	Class<?>  value();
}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
