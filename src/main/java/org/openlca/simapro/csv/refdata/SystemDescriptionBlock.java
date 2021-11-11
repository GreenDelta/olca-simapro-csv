package org.openlca.simapro.csv.refdata;

import java.util.function.Supplier;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;

public class SystemDescriptionBlock implements CsvBlock {

  private String name;
  private String category;
  private String description;
  private String subSystems;
  private String cutOffRules;
  private String energyModel;
  private String transportModel;
  private String wasteModel;
  private String otherAssumptions;
  private String otherInformation;
  private String allocationRules;

  public String name() {
    return name;
  }

  public SystemDescriptionBlock name(String name) {
    this.name = name;
    return this;
  }

  public String category() {
    return category;
  }

  public SystemDescriptionBlock category(String category) {
    this.category = category;
    return this;
  }

  public String description() {
    return description;
  }

  public SystemDescriptionBlock description(String description) {
    this.description = description;
    return this;
  }

  public String subSystems() {
    return subSystems;
  }

  public SystemDescriptionBlock subSystems(String subSystems) {
    this.subSystems = subSystems;
    return this;
  }

  public String cutOffRules() {
    return cutOffRules;
  }

  public SystemDescriptionBlock cutOffRules(String cutOffRules) {
    this.cutOffRules = cutOffRules;
    return this;
  }

  public String energyModel() {
    return energyModel;
  }

  public SystemDescriptionBlock energyModel(String energyModel) {
    this.energyModel = energyModel;
    return this;
  }

  public String transportModel() {
    return transportModel;
  }

  public SystemDescriptionBlock transportModel(String transportModel) {
    this.transportModel = transportModel;
    return this;
  }

  public String wasteModel() {
    return wasteModel;
  }

  public SystemDescriptionBlock wasteModel(String wasteModel) {
    this.wasteModel = wasteModel;
    return this;
  }

  public String otherAssumptions() {
    return otherAssumptions;
  }

  public SystemDescriptionBlock otherAssumptions(String otherAssumptions) {
    this.otherAssumptions = otherAssumptions;
    return this;
  }

  public String otherInformation() {
    return otherInformation;
  }

  public SystemDescriptionBlock otherInformation(String otherInformation) {
    this.otherInformation = otherInformation;
    return this;
  }

  public String allocationRules() {
    return allocationRules;
  }

  public SystemDescriptionBlock allocationRules(String allocationRules) {
    this.allocationRules = allocationRules;
    return this;
  }

  public static SystemDescriptionBlock read(Iterable<CsvLine> lines) {
    var block = new SystemDescriptionBlock();
    var iter = lines.iterator();
    Supplier<String> nextFirst = () -> CsvLine.nextOf(iter)
      .map(CsvLine::first)
      .orElse("");

    while (iter.hasNext()) {

      var line = iter.next();
      if (line.isEmpty())
        continue;
      var header = line.first();
      if (header.equalsIgnoreCase("End"))
        break;
      if (header.isEmpty())
        continue;

      switch (header) {

        case "Name":
          block.name(nextFirst.get());
          break;

        case "Category":
          block.category(nextFirst.get());
          break;

        case "Description":
          block.description(nextFirst.get());
          break;

        case "Sub-systems":
          block.subSystems(nextFirst.get());
          break;

        case "Cut-off rules":
          block.cutOffRules(nextFirst.get());
          break;

        case "Energy model":
          block.energyModel(nextFirst.get());
          break;

        case "Transport model":
          block.transportModel(nextFirst.get());
          break;

        case "Waste model":
          block.wasteModel(nextFirst.get());
          break;

        case "Other assumptions":
          block.otherAssumptions(nextFirst.get());
          break;

        case "Other information":
          block.otherInformation(nextFirst.get());
          break;

        case "Allocation rules":
          block.allocationRules(nextFirst.get());
          break;
      }
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {
    buffer.putString("System description").writeln()
      .writeln()
      .putString("Name").writeln()
      .putString(name).writeln()
      .writeln()
      .putString("Category").writeln()
      .putString(category).writeln()
      .writeln()
      .putString("Description").writeln()
      .putString(description).writeln()
      .writeln()
      .putString("Sub-systems").writeln()
      .putString(subSystems).writeln()
      .writeln()
      .putString("Cut-off rules").writeln()
      .putString(cutOffRules).writeln()
      .writeln()
      .putString("Energy model").writeln()
      .putString(energyModel).writeln()
      .writeln()
      .putString("Transport model").writeln()
      .putString(transportModel).writeln()
      .writeln()
      .putString("Waste model").writeln()
      .putString(wasteModel).writeln()
      .writeln()
      .putString("Other assumptions").writeln()
      .putString(otherAssumptions).writeln()
      .writeln()
      .putString("Other information").writeln()
      .putString(otherInformation).writeln()
      .writeln()
      .putString("Allocation rules").writeln()
      .putString(allocationRules).writeln()
      .writeln()
      .putString("End").writeln()
      .writeln();
  }
}
