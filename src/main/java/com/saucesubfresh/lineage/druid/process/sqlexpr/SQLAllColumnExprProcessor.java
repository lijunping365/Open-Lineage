package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAllColumnExpr;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * select * 的处理
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLAllColumnExpr.class)
public class SQLAllColumnExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    // 需后置处理，节点处理时由下至上
    // *  select *
    content.addItem(SqlExprContent.builder().name("*").build());
    // select a.*, b*. 识别为 SQLIdentifierExpr
  }
}
