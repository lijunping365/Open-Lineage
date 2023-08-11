package com.saucesubfresh.lineage.druid.node;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 数据血缘解析时字段节点
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:08
 */
@Builder
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class ColumnNode {

  /** 列所属的表，考虑 */
  private TableNode owner;
  /** 表 */
  private String tableName;
  /** 名称 */
  private String name;
  /** 别名 */
  private String alias;
  /** 来源列 */
  private final List<ColumnNode> sourceColumns = new ArrayList<>();
  /** 此节点表达式 */
  private String expression;

  /** 字段所在的表树Id */
  private Long tableTreeNodeId;

  /** 表的表达式 */
  private String tableExpression;

  /** 字段是否为常量 */
  @Builder.Default private boolean isConstant = false;
}
