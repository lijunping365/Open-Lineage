package com.saucesubfresh.lineage.druid.handler;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.saucesubfresh.lineage.druid.ParserException;
import com.saucesubfresh.lineage.druid.SqlRequestContext;
import com.saucesubfresh.lineage.druid.SqlResponseContext;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 表血缘的处理 必须, order 为first.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@Order(PriorityConstants.LITTLE_HIGH)
@Component
public class PerpetualTableHandler implements Handler {

  @Override
  public void handleRequest(SqlRequestContext request, SqlResponseContext response) {
    verify(request);
    handleTableRelation(request, response);
  }

  private void verify(SqlRequestContext sqlContext) {
    if (Objects.isNull(sqlContext)) {
      throw new ParserException("sql.is.null");
    }
    if (StringUtils.isEmpty(sqlContext.getSql())) {
      throw new ParserException("sql.content.is.empty");
    }
    if (StringUtils.isEmpty(sqlContext.getDbType())) {
      throw new ParserException("sql.type.is.empty");
    }
  }

  public void handleTableRelation(SqlRequestContext sqlContext, SqlResponseContext response) {
    AtomicInteger sequence = new AtomicInteger();
    TreeNode<TableNode> root = new TreeNode<>();
    SQLStatement statement;
    try {
      statement =
          SQLUtils.parseSingleStatement(sqlContext.getSql(), sqlContext.getDbType().toLowerCase());
    } catch (Exception e) {
      throw new ParserException("statement.parser.err", e);
    }
    response.setStatementType(statement.getDbType().getClass().getSimpleName().toUpperCase());
    // 处理
    ProcessorRegister.getStatementProcessor(statement.getClass())
        .process(sqlContext.getDbType(), sequence, root, statement);
    // save
    response.setLineageTableTree(root);
  }
}
