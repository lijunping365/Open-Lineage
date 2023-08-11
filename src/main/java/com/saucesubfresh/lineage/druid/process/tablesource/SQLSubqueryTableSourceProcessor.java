package com.saucesubfresh.lineage.druid.process.tablesource;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSubqueryTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SQLSubqueryTableSource eg:
 *
 * <p>select t1.a1 from (select a1 from tablex) t1
 *
 * @author JupiterMouse 2020/09/10
 * @since 1.0
 */
@SqlObjectType(clazz = SQLSubqueryTableSource.class)
public class SQLSubqueryTableSourceProcessor implements TableSourceProcessor {

  @Override
  public void process(
      String dbType,
      AtomicInteger sequence,
      TreeNode<TableNode> parent,
      SQLTableSource sqlTableSource) {
    SQLSelectQuery sqlSelectQuery =
        ((SQLSubqueryTableSource) sqlTableSource).getSelect().getQuery();
    ProcessorRegister.getSQLSelectQueryProcessor(sqlSelectQuery.getClass())
        .process(dbType, sequence, parent, sqlSelectQuery);
  }
}
