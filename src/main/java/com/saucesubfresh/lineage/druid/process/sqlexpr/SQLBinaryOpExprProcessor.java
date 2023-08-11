package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLBinaryOpExpr;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * SQLBinaryOpExpr use case
 *
 * <p>select ((a1+a2)-a3)*a4/a5 as a
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLBinaryOpExpr.class)
public class SQLBinaryOpExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLBinaryOpExpr sqlBinaryOpExpr = (SQLBinaryOpExpr) expr;
    ProcessorRegister.getSQLExprProcessor(sqlBinaryOpExpr.getLeft().getClass())
        .process(dbType, sqlBinaryOpExpr.getLeft(), content);
    ProcessorRegister.getSQLExprProcessor(sqlBinaryOpExpr.getRight().getClass())
        .process(dbType, sqlBinaryOpExpr.getRight(), content);
  }
}
