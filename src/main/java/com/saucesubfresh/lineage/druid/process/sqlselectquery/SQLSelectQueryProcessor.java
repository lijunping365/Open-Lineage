package com.saucesubfresh.lineage.druid.process.sqlselectquery;

import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * SQLSelectQuery 处理
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:24
 */
public interface SQLSelectQueryProcessor {

  /**
   * SQLSelectQuery 处理
   *
   * @param dbType 数据库类型
   * @param sequence 节点主键
   * @param parent 传入的节点
   * @param sqlSelectQuery SQLSelectQuery子类
   */
  void process(
      String dbType,
      AtomicInteger sequence,
      TreeNode<TableNode> parent,
      SQLSelectQuery sqlSelectQuery);
}
