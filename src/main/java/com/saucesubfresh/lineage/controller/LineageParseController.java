package com.saucesubfresh.lineage.controller;

import com.saucesubfresh.lineage.druid.SqlMessage;
import com.saucesubfresh.lineage.druid.SqlMessageExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LineageParseController.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 22:27
 */
@RestController
@RequestMapping("/open-lineage")
public class LineageParseController {

  private final SqlMessageExecutor sqlMessageExecutor;

  public LineageParseController(SqlMessageExecutor sqlMessageExecutor) {
    this.sqlMessageExecutor = sqlMessageExecutor;
  }

  @PostMapping("/parse")
  public Object parse(@RequestBody SqlMessage sqlMessage) {
    return sqlMessageExecutor.handle(sqlMessage);
  }
}
