package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.expr.SQLMethodInvokeExpr;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.CollectionUtils;

/**
 * SQLMethodInvokeExpr eg L
 *
 * <p>select substring(a1,a2) as c
 *
 * @author JupiterMouse 2020/09/11
 * @since 1.0
 */
@SqlObjectType(clazz = SQLMethodInvokeExpr.class)
public class SQLMethodInvokeExprProcessor implements SQLExprProcessor {

  @Override
  public void process(String dbType, SQLExpr expr, SqlExprContent content) {
    SQLMethodInvokeExpr sqlMethodInvokeExpr = (SQLMethodInvokeExpr) expr;
    this.getAllCaseExprChild(sqlMethodInvokeExpr)
        .forEach(
            ep ->
                ProcessorRegister.getSQLExprProcessor(ep.getClass()).process(dbType, ep, content));
  }

  private List<SQLExpr> getAllCaseExprChild(SQLMethodInvokeExpr expr) {
    List<SQLExpr> list = new ArrayList<>();
    Optional.ofNullable(expr.getOwner()).ifPresent(list::add);
    Optional.ofNullable(expr.getFrom()).ifPresent(list::add);
    Optional.ofNullable(expr.getUsing()).ifPresent(list::add);
    Optional.ofNullable(expr.getFor()).ifPresent(list::add);
    if (CollectionUtils.isNotEmpty(expr.getArguments())) {
      list.addAll(expr.getArguments());
    }
    return list;
  }
}
