package com.saucesubfresh.lineage.druid.handler;

import com.saucesubfresh.lineage.druid.SqlRequestContext;
import com.saucesubfresh.lineage.druid.SqlResponseContext;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * HandlerChain.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:08
 */
@Component
public class HandlerChain {

  private final List<Handler> handlers;

  public HandlerChain(List<Handler> handlers) {
    this.handlers = handlers;
  }

  public void handle(SqlRequestContext request, SqlResponseContext response) {
    handlers.forEach(handler -> handler.handleRequest(request, response));
  }
}
