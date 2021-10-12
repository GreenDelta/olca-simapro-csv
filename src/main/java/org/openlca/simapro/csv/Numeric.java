package org.openlca.simapro.csv;

/**
 * A numeric field can contain a formula or a number.
 */
public class Numeric {

  private final double value;
  private final String formula;
  private static final Numeric zero = new Numeric(0, null);

  private Numeric(double value, String formula) {
    this.value = value;
    this.formula = formula;
  }

  public static Numeric of(double value) {
    return new Numeric(value, null);
  }

  public static Numeric of(String formula) {
    if (formula == null || formula.isBlank())
      return zero;
    return new Numeric(0, formula);
  }

  public static Numeric zero() {
    return zero;
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

  @Override
  public String toString() {
    return hasFormula()
      ? formula
      : Double.toString(value);
  }
}
