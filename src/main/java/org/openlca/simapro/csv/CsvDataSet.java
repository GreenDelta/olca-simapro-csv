package org.openlca.simapro.csv;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.openlca.simapro.csv.enums.ElementaryFlowType;
import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.process.ProductStageBlock;
import org.openlca.simapro.csv.refdata.CalculatedParameterRow;
import org.openlca.simapro.csv.refdata.ElementaryFlowRow;
import org.openlca.simapro.csv.refdata.InputParameterRow;
import org.openlca.simapro.csv.refdata.QuantityRow;
import org.openlca.simapro.csv.refdata.SystemDescriptionBlock;
import org.openlca.simapro.csv.refdata.UnitRow;

public class CsvDataSet {

  private final List<ProductStageBlock> productStages = new ArrayList<>();
  private final List<ProcessBlock> processes = new ArrayList<>();
  private final List<SystemDescriptionBlock> systemDescriptions = new ArrayList<>();
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

  public List<ProductStageBlock> productStages() {
    return productStages;
  }

  public List<ProcessBlock> processes() {
    return processes;
  }

  public List<SystemDescriptionBlock> systemDescriptions() {
    return systemDescriptions;
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

  public static CsvDataSet read(File file) {
    var header = CsvHeader.readFrom(file);
    try (var reader = Csv.readerOf(file)) {
      return read(header, reader);
    } catch (IOException e) {
      throw new RuntimeException("failed to read file: " + file, e);
    }
  }

  public static CsvDataSet read(CsvHeader header, Reader reader) {
    var dataSet = new CsvDataSet();
    var iter = CsvLine.iter(header, reader);
    var lines = iter.iterator();
    while(lines.hasNext()) {
      var line = lines.next();

      if (line.first().equals("Process")) {
        var process = ProcessBlock.read(iter);
        dataSet.processes.add(process);
        continue;
      }



    }


    return dataSet;
  }

}
