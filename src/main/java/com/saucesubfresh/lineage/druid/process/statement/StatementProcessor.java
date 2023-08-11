package com.saucesubfresh.lineage.druid.process.statement;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Statement 语句解析。 判断属于哪种模式的语句后进行处理 eg: PGInsertStatement: postgresql insert SQLCreateViewStatement:
 * create view as
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:19
 */
public interface StatementProcessor {

  /**
   * SQLStatement 处理
   *
   * @param dbType 数据库类型
   * @param sequence 序列
   * @param root 当前表节点
   * @param statement SQLStatement
   */
  void process(
      String dbType, AtomicInteger sequence, TreeNode<TableNode> root, SQLStatement statement);
}
