package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLPropertyExpr;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * 字段前缀解析 eg:
 *
 * <p>select dwd.a1
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLPropertyExpr.class)
public class SQLPropertyExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLPropertyExpr sqlPropertyExpr = (SQLPropertyExpr) expr;
    // 出口
    content.addItem(
        SqlExprContent.builder()
            .name(sqlPropertyExpr.getName())
            .owner(sqlPropertyExpr.getOwnernName())
            .build());
  }
}
