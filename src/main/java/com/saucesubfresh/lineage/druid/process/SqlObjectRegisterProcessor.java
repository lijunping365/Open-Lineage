package com.saucesubfresh.lineage.druid.process;

import com.alibaba.druid.sql.ast.SQLObject;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * SqlObjectRegisterProcessor.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:21
 */
@Component
@ConditionalOnClass(SQLObject.class)
public class SqlObjectRegisterProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) {
    Class<?> cla = bean.getClass();
    SqlObjectType sqlObjectType = cla.getAnnotation(SqlObjectType.class);
    if (sqlObjectType == null) {
      return bean;
    }
    Class<?> clazz = sqlObjectType.clazz();
    ProcessorRegister.register(clazz, bean);
    return bean;
  }
}
