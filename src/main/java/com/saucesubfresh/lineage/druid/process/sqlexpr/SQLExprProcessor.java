package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;

/**
 * SQLExpr 处理器
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:25
 */
public interface SQLExprProcessor {

  /**
   * SQLExpr 内容提取
   *
   * @param dbType  数据库类型
   * @param expr    SQLExpr
   * @param content SqlExprContent
   */
  void process(String dbType, SQLExpr expr, SqlExprContent content);

}
