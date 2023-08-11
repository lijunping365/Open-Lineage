package com.saucesubfresh.lineage.druid.process.tablesource;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLExprTableSource;
import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;
import com.saucesubfresh.lineage.druid.process.sqlexpr.SqlExprContent;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SQLExprTableSource
 *
 * @author JupiterMouse 2020/09/10
 * @since 1.0
 */
@SqlObjectType(clazz = SQLExprTableSource.class)
public class SQLExprTableSourceProcessor implements TableSourceProcessor {

  @Override
  public void process(
      String dbType,
      AtomicInteger sequence,
      TreeNode<TableNode> parent,
      SQLTableSource sqlTableSource) {
    // 建立TEMP节点 start
    TableNode proxyTable =
        TableNode.builder()
            .expression(SQLUtils.toSQLString(sqlTableSource))
            .alias(sqlTableSource.getAlias())
            .build();
    TreeNode<TableNode> proxyNode = TreeNode.of(proxyTable);
    parent.addChild(proxyNode);

    SQLExpr sqlExprTableSourceExpr = ((SQLExprTableSource) sqlTableSource).getExpr();
    SqlExprContent sqlExprContent = new SqlExprContent();
    ProcessorRegister.getSQLExprProcessor(sqlExprTableSourceExpr.getClass())
        .process(dbType, sqlExprTableSourceExpr, sqlExprContent);
    proxyTable.setName(sqlExprContent.getName());
    proxyTable.setSchemaName(sqlExprContent.getOwner());
  }
}
