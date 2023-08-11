package com.saucesubfresh.lineage.druid.handler;

import com.saucesubfresh.lineage.druid.SqlRequestContext;
import com.saucesubfresh.lineage.druid.SqlResponseContext;
import com.saucesubfresh.lineage.druid.node.ColumnNode;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 填充信息 1. 填充字段信息 setTableExpression(String)
 *
 * @author JupiterMouse 2020/10/15
 * @see ColumnNode#setTableExpression(String) ()
 * @since 1.0
 */
@Order(PriorityConstants.LITTLE_LOW - 10)
@Component
@Slf4j
public class RichColumnHandler implements Handler {

  @Override
  public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
    fillingTableExpression(response.getLineageTableTree());
  }

  /**
   * Tree<Table> 填充Column的TableExpression 字段
   *
   * @param root 当前表关系树节点
   */
  public void fillingTableExpression(TreeNode<TableNode> root) {
    root.getValue()
        .getColumns()
        .forEach(columnNode -> columnNode.setTableExpression(root.getValue().getExpression()));
    if (root.isLeaf()) {
      return;
    }
    root.getChildList().forEach(this::fillingTableExpression);
  }
}
