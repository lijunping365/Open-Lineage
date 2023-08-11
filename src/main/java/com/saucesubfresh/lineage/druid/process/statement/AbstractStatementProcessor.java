package com.saucesubfresh.lineage.druid.process.statement;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.sqlexpr.SqlExprContent;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;

/**
 * AbstractStatementProcessor.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:29
 */
@Slf4j
public abstract class AbstractStatementProcessor implements StatementProcessor {

  @Override
  public void process(
      String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
    this.doProcess(dbType, sequence, root, statement);
    this.after(dbType, sequence, root, statement);
  }

  protected void doProcess(
      String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {}

  protected void constructRootNode(
      String dbType,
      TreeNode<TableNode> root,
      SQLStatement statement,
      SQLExprTableSource sqlExprTableSource) {
    SQLExpr sqlExpr = sqlExprTableSource.getExpr();
    SqlExprContent content = new SqlExprContent();
    ProcessorRegister.getSQLExprProcessor(sqlExpr.getClass()).process(dbType, sqlExpr, content);
    String tableName = content.getName();
    String schemaName = content.getOwner();
    TableNode tableNode =
        TableNode.builder().schemaName(schemaName).name(tableName).isVirtualTemp(false).build();
    root.setValue(tableNode);
    try {
      tableNode.setExpression(SQLUtils.toSQLString(statement));
    } catch (Exception e) {
      log.warn(e.getMessage());
    }
  }

  protected void after(
      String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement) {
    //  处理完之后的操作
    //  合并Union
  }
}
