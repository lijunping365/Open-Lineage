package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * SQLIdentifierExpr eg:
 *
 * <p>select a1
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLIdentifierExpr.class)
public class SQLIdentifierExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLIdentifierExpr sqlIdentifierExpr = (SQLIdentifierExpr) expr;
    // 第一层 除了SQLIdentifierExpr 外，其它可看作是需要查找来源字段的
    // 出口
    content.addItem(SqlExprContent.builder().name(sqlIdentifierExpr.getName()).build());
  }
}
