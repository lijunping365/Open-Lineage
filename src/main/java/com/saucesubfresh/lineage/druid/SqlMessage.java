package com.saucesubfresh.lineage.druid;

import lombok.Data;

/**
 * SqlMessage.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 20:50
 */
@Data
public class SqlMessage {

  private String dbType;

  private String sql;

}
