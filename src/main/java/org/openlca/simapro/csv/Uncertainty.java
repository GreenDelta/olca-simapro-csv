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

  public boolean isLogNormal() {
    return this instanceof LogNormal;
  }

  public LogNormal getAsLogNormal() {
    return (LogNormal) this;
  }

  public boolean isNormal() {
    return this instanceof Normal;
  }

  public Normal getAsNormal() {
    return (Normal) this;
  }

  public boolean isTriangle() {
    return this instanceof Triangle;
  }

  public Triangle getAsTriangle() {
    return (Triangle) this;
  }

  public boolean isUniform() {
    return this instanceof Uniform;
  }

  public Uniform getAsUniform() {
    return (Uniform) this;
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

  public static Triangle triangular(double min, double max) {
    return new Triangle(min, max);
  }

  /**
   * Reads the uncertainty distribution from the given CSV line starting at
   * the given position for the distribution type.
   */
  public static Uncertainty read(CsvLine line, int pos) {
    var type = line.getString(pos).toLowerCase();
    switch (type) {
      case "lognormal":
        return new LogNormal(line.getDouble(pos + 1));
      case "normal":
        return new Normal(line.getDouble(pos + 1));
      case "triangle":
        return new Triangle(line.getDouble(pos + 2), line.getDouble(pos + 3));
      case "uniform":
        return new Uniform(line.getDouble(pos + 2), line.getDouble(pos + 3));
      default:
        return Undefined.instance;
    }
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

  public static final class Triangle extends Uncertainty {

    private final double min;
    private final double max;

    private Triangle(double min, double max) {
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
