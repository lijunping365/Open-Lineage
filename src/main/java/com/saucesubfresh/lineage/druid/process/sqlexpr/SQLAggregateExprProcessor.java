package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLAggregateExpr;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * SQLAggregateExpr use case: max() min() ...
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLAggregateExpr.class)
public class SQLAggregateExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLAggregateExpr sqlAggregateExpr = (SQLAggregateExpr) expr;
    sqlAggregateExpr
        .getArguments()
        .forEach(
            sqlExpr ->
                ProcessorRegister.getSQLExprProcessor(sqlExpr.getClass())
                    .process(dbType, sqlExpr, content));
  }
}
