package com.saucesubfresh.lineage.druid.process.sqlexpr;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SqlExprContent.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:26
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SqlExprContent {

  /** items 中的字段 */
  private String owner;

  /**
   * name = "*" & owner!=null & isConstant=true 此时为AllColumn， 若只是 "*" 还可能是 SQLCharExprProcessor
   * 处理的结果
   */
  private String name;

  /**
   * 是否为字符串常量， 默认为false true @see cn.jupitermouse.lineage.parser.druid.process.sqlexpr.SQLCharExpr
   */
  @Builder.Default private boolean isConstant = false;

  private final List<SqlExprContent> items = new ArrayList<>();

  public boolean isFirst() {
    return name == null;
  }

  public void addItem(SqlExprContent content) {
    if (isFirst()) {
      this.owner = content.owner;
      this.name = content.name;
    } else {
      items.add(content);
    }
  }

  public List<SqlExprContent> getAllItems() {
    List<SqlExprContent> list = new ArrayList<>();
    list.add(SqlExprContent.builder().owner(this.owner).name(this.name).build());
    list.addAll(items);
    return list;
  }

  public boolean isEmptyChildren() {
    return items.size() == 0;
  }

  public static SqlExprContent of() {
    return new SqlExprContent();
  }
}
