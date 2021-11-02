package org.openlca.simapro.csv.method;

import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class ImpactFactorRow implements CsvRecord {

  private String compartment;
  private String subCompartment;
  private String flow;
  private String casNumber;
  private double factor;
  private String unit;

  public String compartment() {
    return compartment;
  }

  public ImpactFactorRow compartment(String compartment) {
    this.compartment = compartment;
    return this;
  }

  public String subCompartment() {
    return subCompartment;
  }

  public ImpactFactorRow subCompartment(String subCompartment) {
    this.subCompartment = subCompartment;
    return this;
  }

  public String flow() {
    return flow;
  }

  public ImpactFactorRow flow(String flow) {
    this.flow = flow;
    return this;
  }

  public String casNumber() {
    return casNumber;
  }

  public ImpactFactorRow casNumber(String casNumber) {
    this.casNumber = casNumber;
    return this;
  }

  public double factor() {
    return factor;
  }

  public ImpactFactorRow factor(double factor) {
    this.factor = factor;
    return this;
  }

  public String unit() {
    return unit;
  }

  public ImpactFactorRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public static ImpactFactorRow read(CsvLine line) {
    return new ImpactFactorRow()
      .compartment(line.getString(0))
      .subCompartment(line.getString(1))
      .flow(line.getString(2))
      .casNumber(line.getString(3))
      .factor(line.getDouble(4))
      .unit(line.getString(5));
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString(compartment())
      .putString(subCompartment())
      .putString(flow())
      .putString(casNumber())
      .putDouble(factor())
      .putString(unit())
      .writeln();
  }
}
