package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCharExpr;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * SQLCharExpr eg:
 *
 * <p>select 'str1' + 'st2r' as c
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLCharExpr.class)
public class SQLCharExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    // SQLCharExpr
    SQLCharExpr sqlCharExpr = (SQLCharExpr) expr;
    // 出口
    //        content.addItem(SqlExprContent
    //                .builder()
    //                .name(sqlCharExpr.getText())
    //                .isConstant(true)
    //                .build());
    // TODO 常量解析待开发
  }
}
