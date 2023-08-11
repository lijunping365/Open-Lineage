package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * SQLCaseExpr use case: CASE WHEN condition THEN result [WHEN ...] [ELSE result] END constant think
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = SQLCaseExpr.class)
public class SQLCaseExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLCaseExpr sqlCaseExpr = (SQLCaseExpr) expr;
    this.getAllCaseExprChild(sqlCaseExpr)
        .forEach(
            expr1 ->
                ProcessorRegister.getSQLExprProcessor(expr1.getClass())
                    .process(dbType, expr1, content));
  }

  private List<SQLExpr> getAllCaseExprChild(SQLCaseExpr sqlCaseExpr) {
    List<SQLExpr> list = new ArrayList<>();
    if (Objects.nonNull(sqlCaseExpr.getValueExpr())) {
      list.add(sqlCaseExpr.getValueExpr());
    }
    if (Objects.nonNull(sqlCaseExpr.getElseExpr())) {
      list.add(sqlCaseExpr.getElseExpr());
    }
    List<SQLExpr> sqlItemExprList =
        sqlCaseExpr.getItems().stream()
            .map(SQLCaseExpr.Item::getValueExpr)
            .collect(Collectors.toList());
    list.addAll(sqlItemExprList);
    return list;
  }
}
