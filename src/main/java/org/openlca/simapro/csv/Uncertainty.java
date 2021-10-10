package org.openlca.simapro.csv;

/**
 * An uncertainty distribution can be stored in 4 slots of a CSV row. The first
 * slot contains the distribution type ({@code Undefined, Lognormal, Normal,
 * Triangle, Uniform}). The other slots contain the distribution parameters.
 */
public abstract class Uncertainty {

  public final boolean isUndefined() {
    return this instanceof Undefined;
  }

  public final Undefined getAsUndefined() {
    return this.isUndefined()
      ? (Undefined) this
      : Undefined.instance;
  }

  public static Undefined undefined() {
    return Undefined.instance;
  }
  public static LogNormal logNormal(double xsd) {
    return new LogNormal(xsd);
  }

  public static Normal normal(double xsd) {
    return new Normal(xsd);
  }

  public static Uniform uniform(double min, double max) {
    return new Uniform(min, max);
  }

  public static Triangular triangular(double min, double max) {
    return new Triangular(min, max);
  }


  public static final class Undefined extends Uncertainty {

    private static final Undefined instance = new Undefined();

    private Undefined() {
    }
  }

  public static final class LogNormal extends Uncertainty {

    private final double xsd;

    private LogNormal(double xsd) {
      this.xsd = xsd;
    }

    /**
     * Returns the squared geometric standard deviation.
     */
    public double xsd() {
      return xsd;
    }
  }

  public static final class Normal extends Uncertainty {

    private final double xsd;

    private Normal(double xsd) {
      this.xsd = xsd;
    }

    public double xsd() {
      return xsd;
    }
  }

  public static final class Uniform extends Uncertainty {

    private final double min;
    private final double max;

    private Uniform(double min, double max) {
      this.min = min;
      this.max = max;
    }

    public double min() {
      return min;
    }

    public double max() {
      return max;
    }
  }

  public static final class Triangular extends Uncertainty {

    private final double min;
    private final double max;

    private Triangular(double min, double max) {
      this.min = min;
      this.max = max;
    }

    public double min() {
      return min;
    }

    public double max() {
      return max;
    }
  }

}
