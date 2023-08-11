package com.saucesubfresh.lineage.druid.handler;

import com.saucesubfresh.lineage.druid.ParserException;
import com.saucesubfresh.lineage.druid.SqlRequestContext;
import com.saucesubfresh.lineage.druid.SqlResponseContext;
import com.saucesubfresh.lineage.druid.node.ColumnNode;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.tracer.ColumnLineageTracer;
import com.saucesubfresh.lineage.druid.tracer.ColumnLineageTracerFactory;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 字段血缘解析 顺序为最后执行字段解析
 *
 * @author JupiterMouse 2020/10/15
 * @since 1.0
 */
@Order(PriorityConstants.LITTLE_LOW)
@Component
public class LineageColumnHandler implements Handler {

  @Override
  public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
    handleColumnRelation(request, response);
  }

  private void handleColumnRelation(SqlRequestContext sqlContext, SqlResponseContext response) {
    TreeNode<TableNode> lineageTableTree = response.getLineageTableTree();
    TreeNode<TableNode> firstHaveColumnTableNode =
        this.findFirstHaveColumnTableNode(lineageTableTree);
    List<ColumnNode> rootColumns = firstHaveColumnTableNode.getValue().getColumns();
    if (CollectionUtils.isEmpty(rootColumns)) {
      throw new ParserException("node.not.found.effective");
    }
    ColumnLineageTracer columnLineageTracer = ColumnLineageTracerFactory.getDefaultTracer();
    // 获取到字段血缘树
    List<TreeNode<ColumnNode>> lineageColumnTreeList = new ArrayList<>();
    rootColumns.stream()
        .map(TreeNode::of)
        .forEach(
            nodeTreeNode -> {
              lineageColumnTreeList.add(nodeTreeNode);
              columnLineageTracer.traceColumnLineageTree(
                  sqlContext.getDbType(), nodeTreeNode, firstHaveColumnTableNode);
            });
    // save
    response.setLineageColumnTreeList(lineageColumnTreeList);
  }

  /**
   * 找到第一个有字段的节点
   *
   * @param root TableNode
   * @return TreeNode<TableNode>
   */
  private TreeNode<TableNode> findFirstHaveColumnTableNode(TreeNode<TableNode> root) {
    if (!CollectionUtils.isEmpty(root.getValue().getColumns())) {
      return root;
    }
    if (CollectionUtils.isEmpty(root.getChildList()) || root.getChildList().size() != 1) {
      throw new ParserException("node.found.more");
    }
    // 第一个有字段的节点，其父级仅有一个子元素
    return root.getChildList().get(0);
  }
}
