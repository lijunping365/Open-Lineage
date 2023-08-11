package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCastExpr;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * 类型转换 eg:
 *
 * <p>select col::text as c
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLCastExpr.class)
public class SQLCastExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLCastExpr sqlCastExpr = (SQLCastExpr) expr;
    SQLExpr castExprExpr = sqlCastExpr.getExpr();
    ProcessorRegister.getSQLExprProcessor(castExprExpr.getClass())
        .process(dbType, castExprExpr, content);
  }
}
