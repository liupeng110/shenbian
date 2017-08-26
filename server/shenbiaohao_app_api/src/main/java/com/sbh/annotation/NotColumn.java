<<<<<<< HEAD
package com.sbh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用于DTO，当此DTO上的属性不需要DML时使用
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotColumn {

}
=======
package com.sbh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 用于DTO，当此DTO上的属性不需要DML时使用
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotColumn {

}
>>>>>>> e7eb74d832a92ae08214a7cb6b7869295e64c0c2
