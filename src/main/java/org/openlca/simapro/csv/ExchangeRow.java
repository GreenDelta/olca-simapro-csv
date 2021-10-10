package org.openlca.simapro.csv;

abstract class ExchangeRow implements CsvRecord {

  private String name;
  private String amount;
  private String comment;
  private String unit;
  private String pedigree;

  public String name() {
    return name;
  }

  public ExchangeRow name(String name) {
    this.name = name;
    return this;
  }

  public String amount() {
    return amount;
  }

  public ExchangeRow amount(String amount) {
    this.amount = amount;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ExchangeRow comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String unit() {
    return unit;
  }

  public ExchangeRow unit(String unit) {
    this.unit = unit;
    return this;
  }

  public String pedigree() {
    return pedigree;
  }

  public ExchangeRow pedigree(String pedigree) {
    this.pedigree = pedigree;
    return this;
  }



}
