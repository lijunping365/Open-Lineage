package com.saucesubfresh.lineage.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LineageDTO.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/11 20:41
 */
@NoArgsConstructor
@Data
public class LineageDTO {

  /** refFields */
  private List<LineageField> refFields;

  /** lineageField */
  private LineageField targetField;
}
