package com.saucesubfresh.lineage.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * LineageField.
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/11 20:42
 */
@NoArgsConstructor
@Data
public class LineageField {

  /** fieldName */
  private String fieldName;
  
  /** finalX */
  @JsonProperty("final")
  private boolean finalX;
  
  /** index */
  private int index;
  
  /** level */
  private int level;
}
