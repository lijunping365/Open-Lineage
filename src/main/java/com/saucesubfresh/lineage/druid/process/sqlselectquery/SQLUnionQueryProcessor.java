package com.saucesubfresh.lineage.druid.process.sqlselectquery;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLSelectQuery;
import com.alibaba.druid.sql.ast.statement.SQLUnionQuery;
import com.saucesubfresh.lineage.druid.node.TableNode;
import com.saucesubfresh.lineage.druid.node.TreeNode;
import com.saucesubfresh.lineage.druid.process.ParserConstant;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.commons.collections4.CollectionUtils;

/**
 * SQLUnionQuery 处理
 *
 * <p>use case: create view view_test as select a1,a2,a3 from table_a union select b1,b2,b3 from
 * table_b union all select c1,c2,c3 from table_c
 *
 * <p>
 *
 * <p>Struct: Statement - SQLUnionQuery - SQLSelectQueryBlock | PgSQLSelectQueryBlock ...
 *
 * @author JupiterMouse 2020/10/15
 * @since 1.0
 */
@SqlObjectType(clazz = SQLUnionQuery.class)
public class SQLUnionQueryProcessor extends AbstractSQLSelectQueryProcessor {

  @Override
  public void process(
      String dbType,
      AtomicInteger sequence,
      TreeNode<TableNode> parent,
      SQLSelectQuery sqlSelectQuery) {
    TableNode proxyTable =
        TableNode.builder()
            .isVirtualTemp(true)
            .expression(SQLUtils.toSQLString(sqlSelectQuery))
            .name(ParserConstant.TEMP_TABLE_PREFIX + sequence.incrementAndGet())
            .alias(this.getSubqueryTableSourceAlias(sqlSelectQuery))
            .queryType(ParserConstant.DealType.TABLE_QL_UNION_QUERY)
            .build();
    TreeNode<TableNode> proxyNode = TreeNode.of(proxyTable);
    parent.addChild(proxyNode);
    // TODO 考虑字段合并到 proxyTable 的字段
    List<SQLSelectQuery> selectQueryList = ((SQLUnionQuery) sqlSelectQuery).getRelations();
    if (CollectionUtils.isNotEmpty(selectQueryList)) {
      selectQueryList.forEach(
          item ->
              ProcessorRegister.getSQLSelectQueryProcessor(item.getClass())
                  .process(dbType, sequence, proxyNode, item));
    }
  }
}
