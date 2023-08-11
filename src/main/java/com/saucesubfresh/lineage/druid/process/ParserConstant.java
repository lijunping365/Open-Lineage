package com.saucesubfresh.lineage.druid.process;

/**
 * ParserConstant.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:17
 */
public class ParserConstant {

  private ParserConstant() {}

  public static final String TEMP_TABLE_PREFIX = "SQL_RESULT_";

  public static class DealType {
    private DealType() {}

    public static final String TABLE_QL_UNION_QUERY = "SQLUnionQuery";
  }
}
