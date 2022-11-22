package org.openlca.simapro.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.enums.ElementaryFlowType;
import org.openlca.simapro.csv.method.ImpactMethodBlock;
import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.process.ProductStageBlock;
import org.openlca.simapro.csv.refdata.*;

public class CsvDataSet {

  private final CsvHeader header;
  private final List<ProductStageBlock> productStages = new ArrayList<>();
  private final List<ProcessBlock> processes = new ArrayList<>();
  private final List<SystemDescriptionBlock> systemDescriptions = new ArrayList<>();
  private final List<ImpactMethodBlock> methods = new ArrayList<>();
  private final List<QuantityRow> quantities = new ArrayList<>();
  private final List<UnitRow> units = new ArrayList<>();
  private final List<ElementaryFlowRow> rawMaterials = new ArrayList<>();
  private final List<ElementaryFlowRow> airborneEmissions = new ArrayList<>();
  private final List<ElementaryFlowRow> waterborneEmissions = new ArrayList<>();
  private final List<ElementaryFlowRow> finalWasteFlows = new ArrayList<>();
  private final List<ElementaryFlowRow> emissionsToSoil = new ArrayList<>();
  private final List<ElementaryFlowRow> nonMaterialEmissions = new ArrayList<>();
  private final List<ElementaryFlowRow> socialIssues = new ArrayList<>();
  private final List<ElementaryFlowRow> economicIssues = new ArrayList<>();
  private final List<InputParameterRow> databaseInputParameters = new ArrayList<>();
  private final List<CalculatedParameterRow> databaseCalculatedParameters = new ArrayList<>();
  private final List<InputParameterRow> projectInputParameters = new ArrayList<>();
  private final List<CalculatedParameterRow> projectCalculatedParameters = new ArrayList<>();
  private final List<LiteratureReferenceBlock> literatureReferences = new ArrayList<>();

  public CsvDataSet() {
    this(new CsvHeader());
  }

  public CsvDataSet(CsvHeader header) {
    this.header = header;
  }

  public CsvHeader header() {
    return header;
  }

  public List<ProductStageBlock> productStages() {
    return productStages;
  }

  public List<ProcessBlock> processes() {
    return processes;
  }

  public List<SystemDescriptionBlock> systemDescriptions() {
    return systemDescriptions;
  }

  public List<ImpactMethodBlock> methods() {
    return methods;
  }

  public List<QuantityRow> quantities() {
    return quantities;
  }

  public List<UnitRow> units() {
    return units;
  }

  public List<ElementaryFlowRow> rawMaterials() {
    return rawMaterials;
  }

  public List<ElementaryFlowRow> airborneEmissions() {
    return airborneEmissions;
  }

  public List<ElementaryFlowRow> waterborneEmissions() {
    return waterborneEmissions;
  }

  public List<ElementaryFlowRow> finalWasteFlows() {
    return finalWasteFlows;
  }

  public List<ElementaryFlowRow> emissionsToSoil() {
    return emissionsToSoil;
  }

  public List<ElementaryFlowRow> nonMaterialEmissions() {
    return nonMaterialEmissions;
  }

  public List<ElementaryFlowRow> socialIssues() {
    return socialIssues;
  }

  public List<ElementaryFlowRow> economicIssues() {
    return economicIssues;
  }

  public List<InputParameterRow> databaseInputParameters() {
    return databaseInputParameters;
  }

  public List<CalculatedParameterRow> databaseCalculatedParameters() {
    return databaseCalculatedParameters;
  }

  public List<InputParameterRow> projectInputParameters() {
    return projectInputParameters;
  }

  public List<CalculatedParameterRow> projectCalculatedParameters() {
    return projectCalculatedParameters;
  }

  public List<LiteratureReferenceBlock> literatureReferences() {
    return literatureReferences;
  }

  public List<ElementaryFlowRow> getElementaryFlows(ElementaryFlowType type) {
    if (type == null)
      return new ArrayList<>();
    switch (type) {
      case RESOURCES:
        return rawMaterials;
      case EMISSIONS_TO_AIR:
        return airborneEmissions;
      case EMISSIONS_TO_WATER:
        return waterborneEmissions;
      case EMISSIONS_TO_SOIL:
        return emissionsToSoil;
      case FINAL_WASTE_FLOWS:
        return finalWasteFlows;
      case NON_MATERIAL_EMISSIONS:
        return nonMaterialEmissions;
      case SOCIAL_ISSUES:
        return socialIssues;
      case ECONOMIC_ISSUES:
        return economicIssues;
      default:
        return new ArrayList<>();
    }
  }


  public void write(File file) {
    try (var writer = new FileWriter(file, SimaProCsv.defaultCharset())) {
      write(new CsvBuffer(writer, header));
    } catch (IOException e) {
      throw new RuntimeException("failed to write file: " + file, e);
    }
  }

  public void write(CsvBuffer buffer) throws IOException {
    header.write(buffer);

    for (var stage : productStages) {
      stage.write(buffer);
    }

    for (var process : processes) {
      process.write(buffer);
    }

    for (var description : systemDescriptions) {
      description.write(buffer);
    }

    for (var method : methods) {
      method.write(buffer);
    }

    writeRows(buffer, "Quantities", quantities);
    writeRows(buffer, "Units", units);
    writeRows(buffer, "Raw materials", rawMaterials);
    writeRows(buffer, "Airborne emissions", airborneEmissions);
    writeRows(buffer, "Waterborne emissions", waterborneEmissions);
    writeRows(buffer, "Final waste flows", finalWasteFlows);
    writeRows(buffer, "Emissions to soil", emissionsToSoil);
    writeRows(buffer, "Non material emissions", nonMaterialEmissions);
    writeRows(buffer, "Social issues", socialIssues);
    writeRows(buffer, "Economic issues", economicIssues);
    writeRows(buffer, "Database Input parameters", databaseInputParameters);
    writeRows(buffer, "Database Calculated parameters", databaseCalculatedParameters);
    writeRows(buffer, "Project Input parameters", projectInputParameters);
    writeRows(buffer, "Project Calculated parameters", projectCalculatedParameters);
    writeRows(buffer, "Literature reference", literatureReferences);
  }

  private void writeRows(
    CsvBuffer buffer, String header, List<? extends CsvRecord> rows) {
    if (rows.isEmpty())
      return;
    buffer.putString(header).writeln();
    for (var row : rows) {
      row.write(buffer);
    }
    buffer.writeln()
      .putString("End").writeln()
      .writeln();
  }
}
