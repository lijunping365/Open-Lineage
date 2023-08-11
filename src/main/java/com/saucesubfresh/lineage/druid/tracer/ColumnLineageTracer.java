package com.saucesubfresh.lineage.druid.tracer;

import com.saucesubfresh.lineage.druid.node.ColumnNode;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;

/**
 * 构建字段血缘
 *
 * @author JupiterMouse 2020/10/15
 * @since 1.0
 */
public interface ColumnLineageTracer {

  /**
   * 构建血缘关系
   *
   * @param dbType 数据库类型
   * @param currentColumnNode 当前的Column
   * @param tableNode 表血缘树
   */
  void traceColumnLineageTree(
      String dbType, TreeNode<ColumnNode> currentColumnNode, TreeNode<TableNode> tableNode);
}
