package com.saucesubfresh.lineage.druid.handler;

import com.saucesubfresh.lineage.druid.SqlRequestContext;
import com.saucesubfresh.lineage.druid.SqlResponseContext;

/**
 * Handler.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:08
 */
public interface Handler {

  /**
   * 表血缘处理
   *
   * @param request sql 请求
   * @param response 响应
   */
  void handleRequest(SqlRequestContext request, SqlResponseContext response);
}
