package com.saucesubfresh.lineage.druid.process.tablesource;

import com.alibaba.druid.sql.ast.statement.SQLTableSource;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TableSource 处理.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:24
 */
public interface TableSourceProcessor {

  /**
   * TableSource 的处理
   *
   * @param dbType 数据库类型
   * @param sequence 序列
   * @param parent 父节点
   * @param sqlTableSource SQLTableSource 子类
   */
  void process(
      String dbType,
      AtomicInteger sequence,
      TreeNode<TableNode> parent,
      SQLTableSource sqlTableSource);
}
