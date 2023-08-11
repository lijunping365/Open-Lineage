package com.saucesubfresh.lineage.druid;

import com.saucesubfresh.lineage.druid.context.LineageContext;
import com.saucesubfresh.lineage.druid.handler.HandlerChain;
import com.saucesubfresh.lineage.druid.node.ColumnNode;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.node.TreeNodeUtil;
import com.saucesubfresh.lineage.dto.LineageDTO;
import com.saucesubfresh.lineage.dto.LineageField;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

/**
 * SqlMessageExecutor.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 22:21
 */
@Component
public class SqlMessageExecutor {

  private final HandlerChain handlerChain;

  public SqlMessageExecutor(HandlerChain handlerChain) {
    this.handlerChain = handlerChain;
  }

  public LineageContext handle(SqlMessage sqlMessage) {
    LineageContext context = new LineageContext();
    context.setSqlMessage(sqlMessage);
    // 处理
    List<SqlRequestContext> contextList = SqlSplitUtils.convertSqlRequest(sqlMessage);
    // 解析SQL
    contextList.forEach(requestContext -> this.handleSql(requestContext, context));
    // 建立上层节点 Cluster、Platform、Schema
    // createUpperLayerNode(context);
    // 从元数据更新节点信息，写入图
    // updateNodeInfoMetadata(context);
    return context;
  }

  void handleSql(SqlRequestContext request, LineageContext context) {
    SqlResponseContext response = new SqlResponseContext();
    handlerChain.handle(request, response);
    System.out.println(response);
    if (Objects.isNull(response.getLineageTableTree())) {
      return;
    }

    // TODO: 2023/8/3  处理完 request 后，返回 response

    // 处理表关系
    // handleTableNode(request, response, context);

    // 获取字段关系树
    handleFieldNode(request, response, context);
  }

  // private void handleTableNode(
  //     SqlRequestContext request, SqlResponseContext response, LineageContext context) {
  //   TreeNode<TableNode> lineageTableTree = response.getLineageTableTree();
  //
  //   // 根节点
  //   TableNode rootTableNode = lineageTableTree.getRoot().getValue();
  //
  //   // 叶子节点
  //   List<TreeNode<TableNode>> leafTreeNodeList =
  //       TreeNodeUtil.searchTreeLeafNodeList(lineageTableTree);
  //
  //   leafTreeNodeList.forEach(
  //       tableNodeTreeNode -> {
  //         convertToLineageDTO(tableNodeTreeNode);
  //       });
  // }
  //
  // private void convertToLineageDTO(TreeNode<TableNode> tableNodeTreeNode) {
  //   LineageDTO lineage = new LineageDTO();
  //   ArrayList<LineageField> refFields = new ArrayList<>();
  //   LineageField lineageField = new LineageField();
  //   lineageField.setFinalX(true);
  //   lineageField.setLevel(tableNodeTreeNode.getHeight());
  //   lineageField.setFieldName(buildFieldName(tableNodeTreeNode));
  //   refFields.add(lineageField);
  //   lineage.setRefFields(refFields);
  // }
  //
  // private String buildFieldName(TreeNode<TableNode> tableNodeTreeNode) {
  //   TableNode tableNode = tableNodeTreeNode.getValue();
  //
  //   String schemaName = tableNode.getSchemaName();
  //   String name = tableNode.getName();
  //   return null;
  // }

  private void handleFieldNode(
      SqlRequestContext request, SqlResponseContext response, LineageContext context) {
    List<TreeNode<ColumnNode>> lineageColumnTreeList = response.getLineageColumnTreeList();
    if (CollectionUtils.isEmpty(lineageColumnTreeList)) {
      return;
    }
    lineageColumnTreeList.forEach(
        columnNodeTreeNode -> {
          List<TreeNode<ColumnNode>> leafColumnNodeList =
              TreeNodeUtil.searchTreeLeafNodeList(columnNodeTreeNode);

          leafColumnNodeList.forEach(
              columnNode -> {
                LineageDTO lineageDTO = new LineageDTO();
                List<LineageField> refFields = new ArrayList<>();

                LineageField lineageField = new LineageField();
                lineageField.setFinalX(true);
                lineageField.setLevel(columnNode.getHeight());
                lineageField.setFieldName(buildFieldName(columnNode));
                refFields.add(lineageField);
                lineageDTO.setRefFields(refFields);
              });
        });
  }

  private String buildFieldName(TreeNode<ColumnNode> columnNodeTreeNode) {
    ColumnNode columnNode = columnNodeTreeNode.getValue();
    String columnNodeValue = columnNode.getName();

    TableNode tableNode = columnNode.getOwner();
    String schemaName = tableNode.getSchemaName();
    String tableName = tableNode.getName();

    return schemaName + "." + tableName + "." + columnNodeValue;
  }
}
