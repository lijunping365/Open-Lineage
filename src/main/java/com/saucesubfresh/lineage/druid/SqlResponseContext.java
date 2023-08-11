package com.saucesubfresh.lineage.druid;

import com.saucesubfresh.lineage.druid.node.ColumnNode;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import java.util.List;
import lombok.Data;

/**
 * SqlResponseContext.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:09
 */
@Data
public class SqlResponseContext {

  /** 语句类型 INSERT ..., CREATE VIEW AS... , ... */
  private String statementType;

  /** 引擎处理的类型 */
  private String engineProcessingType;

  /** 表血缘解析结果 */
  private TreeNode<TableNode> lineageTableTree;

  /** 字段血缘解析结果 */
  private List<TreeNode<ColumnNode>> lineageColumnTreeList;
}
