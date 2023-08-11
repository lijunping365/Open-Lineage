package com.saucesubfresh.lineage.druid.process.sqlselectquery;

import com.alibaba.druid.sql.ast.SQLObject;
import com.alibaba.druid.sql.ast.statement.SQLCreateTableStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;

/**
 * AbstractSQLSelectQueryProcessor.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:44
 */
public abstract class AbstractSQLSelectQueryProcessor implements SQLSelectQueryProcessor {

  /**
   * SQLSelectStatement 考虑中 SQLCreateTableStatement 考虑中 SQLSubqueryTableSource V SQLObject Contain
   * SQLSelect 时, 合并 SQLSubqueryTableSource 中的
   *
   * @param sqlObject sqlObject
   * @return alias
   */
  protected String getSubqueryTableSourceAlias(SQLObject sqlObject) {
    SQLObject parentObject = sqlObject.getParent().getParent();
    if (sqlObject.getParent() == null || parentObject == null) {
      return null;
    }
    if (parentObject instanceof SQLSubqueryTableSource) {
      SQLSubqueryTableSource sqlSubqueryTableSource = (SQLSubqueryTableSource) parentObject;
      return sqlSubqueryTableSource.getAlias();
    } else if (parentObject instanceof SQLSelectStatement
        || parentObject instanceof SQLCreateTableStatement) {
      throw new UnsupportedOperationException(parentObject.getClass().getName());
    } else {
      return null;
    }
  }
}
