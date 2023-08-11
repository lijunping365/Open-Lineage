package com.saucesubfresh.lineage.druid.process;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * SqlObjectType.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:20
 */
@Component
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlObjectType {

  @Nullable Class<?> clazz();

  Class<?>[] parent() default {};

}
