package org.openlca.simapro.csv.enums;

public enum ProductStageCategory {

  ASSEMBLY("assembly"),
  DISASSEMBLY("disassembly"),
  DISPOSAL_SCENARIO("disposal scenario"),
  LIFE_CYCLE("life cycle"),
  REUSE("reuse");

  private final String literal;

  ProductStageCategory(String literal) {
    this.literal = literal;
  }

  /**
   * Returns the product-stage category for the given string as used in
   * CSV files. If the given string does not match any of the defined
   * categories, {@code ASSEMBLY} is returned by default.
   */
  public static ProductStageCategory of(String literal) {
    if (literal == null)
      return ASSEMBLY;
    for (var value : values()) {
      if (literal.equalsIgnoreCase(value.literal))
        return value;
    }
    return ASSEMBLY;
  }

  @Override
  public String toString() {
    return literal;
  }

}
