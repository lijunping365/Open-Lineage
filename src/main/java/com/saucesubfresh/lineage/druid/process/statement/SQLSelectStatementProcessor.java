package com.saucesubfresh.lineage.druid.process.statement;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SQLSelectStatementProcessor.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:39
 */
@SqlObjectType(clazz = SQLSelectStatement.class)
public class SQLSelectStatementProcessor extends AbstractStatementProcessor {

  @Override
  protected void doProcess(
      String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {

    SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) statement;
    SQLSelect sqlSelect = sqlSelectStatement.getSelect();
    // 获取SQLSelectQuery
    SQLSelectQuery sqlSelectQuery = sqlSelect.getQuery();

    // 执行SQLSelectQuery 查询
    ProcessorRegister.getSQLSelectQueryProcessor(sqlSelectQuery.getClass())
        .process(dbType, sequence, root, sqlSelectQuery);
  }
}
