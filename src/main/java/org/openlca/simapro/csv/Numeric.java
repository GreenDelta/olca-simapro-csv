package org.openlca.simapro.csv;

/**
 * A numeric field can contain a formula or a number.
 */
public class Numeric {

  private final double value;
  private final String formula;

  private Numeric(double value, String formula) {
    this.value = value;
    this.formula = formula;
  }

  public static Numeric of(double value) {
    return new Numeric(value, null);
  }

  public static Numeric of(String formula) {
    return new Numeric(0, formula);
  }

  public boolean hasFormula() {
    return formula != null;
  }

  public String formula() {
    return formula;
  }

  public double value() {
    return value;
  }
}
