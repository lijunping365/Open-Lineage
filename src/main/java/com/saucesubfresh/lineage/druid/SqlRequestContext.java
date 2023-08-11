package com.saucesubfresh.lineage.druid;

import lombok.Data;

/**
 * SqlRequestContext.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 20:54
 */
@Data
public class SqlRequestContext {

  /** sql 处理的数据库类型 */
  private String dbType;

  /** 单条 sql 语句 */
  private String sql;

  /** SQL 执行时的schema */
  private String schemaName;
}
