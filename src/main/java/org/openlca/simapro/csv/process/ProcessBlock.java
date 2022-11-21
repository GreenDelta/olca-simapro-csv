package org.openlca.simapro.csv.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.enums.*;
import org.openlca.simapro.csv.refdata.CalculatedParameterRow;
import org.openlca.simapro.csv.refdata.InputParameterRow;

public class ProcessBlock implements CsvBlock {

  private String platformId;
  private ProcessCategory category;
  private String identifier;
  private ProcessType processType;
  private String name;
  private Status status;
  private Boolean infrastructure;
  private Date date;
  private String record;
  private String generator;
  private String collectionMethod;
  private String verification;
  private String comment;
  private String allocationRules;
  private String dataTreatment;

  private SystemDescriptionRow systemDescription;
  private WasteTreatmentRow wasteTreatment;
  private WasteTreatmentRow wasteScenario;

  private final List<LiteratureRow> literatures = new ArrayList<>();
  private final List<ProductOutputRow> products = new ArrayList<>();

  private final List<TechExchangeRow> avoidedProducts = new ArrayList<>();
  private final List<TechExchangeRow> materialsAndFuels = new ArrayList<>();
  private final List<TechExchangeRow> electricityAndHeat = new ArrayList<>();
  private final List<TechExchangeRow> wasteToTreatment = new ArrayList<>();

  private final List<WasteFractionRow> separatedWaste = new ArrayList<>();
  private final List<WasteFractionRow> remainingWaste = new ArrayList<>();

  private final List<ElementaryExchangeRow> resources = new ArrayList<>();
  private final List<ElementaryExchangeRow> emissionsToAir = new ArrayList<>();
  private final List<ElementaryExchangeRow> emissionsToWater = new ArrayList<>();
  private final List<ElementaryExchangeRow> emissionsToSoil = new ArrayList<>();
  private final List<ElementaryExchangeRow> finalWasteFlows = new ArrayList<>();
  private final List<ElementaryExchangeRow> nonMaterialEmissions = new ArrayList<>();
  private final List<ElementaryExchangeRow> socialIssues = new ArrayList<>();
  private final List<ElementaryExchangeRow> economicIssues = new ArrayList<>();

  private final List<InputParameterRow> inputParameters = new ArrayList<>();
  private final List<CalculatedParameterRow> calculatedParameters = new ArrayList<>();

  public String platformId() {
    return platformId;
  }

  public ProcessBlock platformId(String platformId) {
    this.platformId = platformId;
    return this;
  }

  public ProcessCategory category() {
    return category;
  }

  public ProcessBlock category(ProcessCategory category) {
    this.category = category;
    return this;
  }

  public String identifier() {
    return identifier;
  }

  public ProcessBlock identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  public ProcessType processType() {
    return processType;
  }

  public ProcessBlock processType(ProcessType processType) {
    this.processType = processType;
    return this;
  }

  public String name() {
    return name;
  }

  public ProcessBlock name(String name) {
    this.name = name;
    return this;
  }

  public Status status() {
    return status;
  }

  public ProcessBlock status(Status status) {
    this.status = status;
    return this;
  }

  public Boolean infrastructure() {
    return infrastructure;
  }

  public ProcessBlock infrastructure(Boolean infrastructure) {
    this.infrastructure = infrastructure;
    return this;
  }

  public Date date() {
    return date;
  }

  public ProcessBlock date(Date date) {
    this.date = date;
    return this;
  }

  public String record() {
    return record;
  }

  public ProcessBlock record(String record) {
    this.record = record;
    return this;
  }

  public String generator() {
    return generator;
  }

  public ProcessBlock generator(String generator) {
    this.generator = generator;
    return this;
  }

  public String collectionMethod() {
    return collectionMethod;
  }

  public ProcessBlock collectionMethod(String collectionMethod) {
    this.collectionMethod = collectionMethod;
    return this;
  }

  public String verification() {
    return verification;
  }

  public ProcessBlock verification(String verification) {
    this.verification = verification;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ProcessBlock comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String allocationRules() {
    return allocationRules;
  }

  public ProcessBlock allocationRules(String allocationRules) {
    this.allocationRules = allocationRules;
    return this;
  }

  public String dataTreatment() {
    return dataTreatment;
  }

  public ProcessBlock dataTreatment(String dataTreatment) {
    this.dataTreatment = dataTreatment;
    return this;
  }

  public SystemDescriptionRow systemDescription() {
    return systemDescription;
  }

  public ProcessBlock systemDescription(SystemDescriptionRow systemDescription) {
    this.systemDescription = systemDescription;
    return this;
  }

  public WasteTreatmentRow wasteTreatment() {
    return wasteTreatment;
  }

  public ProcessBlock wasteTreatment(WasteTreatmentRow wasteTreatment) {
    this.wasteTreatment = wasteTreatment;
    return this;
  }

  public WasteTreatmentRow wasteScenario() {
    return wasteScenario;
  }

  public ProcessBlock wasteScenario(WasteTreatmentRow wasteScenario) {
    this.wasteScenario = wasteScenario;
    return this;
  }

  public List<LiteratureRow> literatures() {
    return literatures;
  }

  public List<ProductOutputRow> products() {
    return products;
  }

  public List<TechExchangeRow> avoidedProducts() {
    return avoidedProducts;
  }

  public List<TechExchangeRow> materialsAndFuels() {
    return materialsAndFuels;
  }

  public List<TechExchangeRow> electricityAndHeat() {
    return electricityAndHeat;
  }

  public List<TechExchangeRow> wasteToTreatment() {
    return wasteToTreatment;
  }

  public List<WasteFractionRow> separatedWaste() {
    return separatedWaste;
  }

  public List<WasteFractionRow> remainingWaste() {
    return remainingWaste;
  }

  public List<ElementaryExchangeRow> resources() {
    return resources;
  }

  public List<ElementaryExchangeRow> emissionsToAir() {
    return emissionsToAir;
  }

  public List<ElementaryExchangeRow> emissionsToWater() {
    return emissionsToWater;
  }

  public List<ElementaryExchangeRow> emissionsToSoil() {
    return emissionsToSoil;
  }

  public List<ElementaryExchangeRow> finalWasteFlows() {
    return finalWasteFlows;
  }

  public List<ElementaryExchangeRow> nonMaterialEmissions() {
    return nonMaterialEmissions;
  }

  public List<ElementaryExchangeRow> socialIssues() {
    return socialIssues;
  }

  public List<ElementaryExchangeRow> economicIssues() {
    return economicIssues;
  }

  public List<InputParameterRow> inputParameters() {
    return inputParameters;
  }

  public List<CalculatedParameterRow> calculatedParameters() {
    return calculatedParameters;
  }

  public static ProcessBlock read(Iterable<CsvLine> lines) {
    var iter = lines.iterator();
    var process = new ProcessBlock();
    Supplier<String> nextFirst = () -> CsvLine.nextOf(iter)
      .map(CsvLine::first)
      .orElse("");

    while (iter.hasNext()) {

      var next = iter.next();
      if (next.isEmpty())
        continue;
      var header = next.first();
      if (header.equalsIgnoreCase("End"))
        break;
      if (header.isEmpty()) {
        continue;
      }

      switch (header) {

        case "PlatformId":
          process.platformId(nextFirst.get());
          break;

        case "Category type":
          var category = ProcessCategory.of(nextFirst.get());
          process.category(category);
          break;

        case "Process identifier":
          process.identifier(nextFirst.get());
          break;

        case "Type":
          var processType = ProcessType.of(nextFirst.get());
          process.processType(processType);
          break;

        case "Process name":
          process.name(nextFirst.get());
          break;

        case "Status":
          var status = Status.of(nextFirst.get());
          process.status(status);
          break;

        case "Infrastructure":
          CsvLine.nextOf(iter).ifPresent(
            line -> process.infrastructure(line.getBoolean(0)));
          break;

        case "Date":
          CsvLine.nextOf(iter).ifPresent(
            line -> process.date(line.getDate(0)));
          break;

        case "Record":
          process.record(nextFirst.get());
          break;

        case "Generator":
          process.generator(nextFirst.get());
          break;

        case "Literature references":
          CsvLine.untilEmpty(iter,
            line -> process.literatures.add(LiteratureRow.read(line)));
          break;

        case "Collection method":
          process.collectionMethod(nextFirst.get());
          break;

        case "Verification":
          process.verification(nextFirst.get());
          break;

        case "Comment":
          process.comment(nextFirst.get());
          break;

        case "Allocation rules":
          process.allocationRules(nextFirst.get());
          break;

        case "System description":
          CsvLine.nextOf(iter).ifPresent(
            line -> process.systemDescription(SystemDescriptionRow.read(line)));
          break;

        case "Data treatment":
          process.dataTreatment(nextFirst.get());
          break;

        case "Products":
          CsvLine.untilEmpty(iter,
            line -> process.products.add(ProductOutputRow.read(line)));
          break;

        case "Waste treatment":
          CsvLine.nextOf(iter).ifPresent(
            line -> process.wasteTreatment(WasteTreatmentRow.read(line)));
          break;

        case "Waste scenario":
          CsvLine.nextOf(iter).ifPresent(
            line -> process.wasteScenario(WasteTreatmentRow.read(line)));

        case "Avoided products":
          CsvLine.untilEmpty(iter,
            line -> process.avoidedProducts.add(TechExchangeRow.read(line)));
          break;

        case "Materials/fuels":
          CsvLine.untilEmpty(iter,
            line -> process.materialsAndFuels.add(TechExchangeRow.read(line)));
          break;

        case "Electricity/heat":
          CsvLine.untilEmpty(iter,
            line -> process.electricityAndHeat.add(TechExchangeRow.read(line)));
          break;

        case "Waste to treatment":
          CsvLine.untilEmpty(iter,
            line -> process.wasteToTreatment.add(TechExchangeRow.read(line)));
          break;

        case "Separated waste":
          CsvLine.untilEmpty(iter,
            line -> process.separatedWaste.add(WasteFractionRow.read(line)));
          break;

        case "Remaining waste":
          CsvLine.untilEmpty(iter,
            line -> process.remainingWaste.add(WasteFractionRow.read(line)));
          break;

        case "Resources":
          CsvLine.untilEmpty(iter,
            line -> process.resources.add(ElementaryExchangeRow.read(line)));
          break;

        case "Emissions to air":
          CsvLine.untilEmpty(iter,
            line -> process.emissionsToAir.add(ElementaryExchangeRow.read(line)));
          break;

        case "Emissions to water":
          CsvLine.untilEmpty(iter,
            line -> process.emissionsToWater.add(ElementaryExchangeRow.read(line)));
          break;

        case "Emissions to soil":
          CsvLine.untilEmpty(iter,
            line -> process.emissionsToSoil.add(ElementaryExchangeRow.read(line)));
          break;

        case "Final waste flows":
          CsvLine.untilEmpty(iter,
            line -> process.finalWasteFlows.add(ElementaryExchangeRow.read(line)));
          break;

        case "Non material emissions":
          CsvLine.untilEmpty(iter,
            line -> process.nonMaterialEmissions.add(ElementaryExchangeRow.read(line)));
          break;

        case "Social issues":
          CsvLine.untilEmpty(iter,
            line -> process.socialIssues.add(ElementaryExchangeRow.read(line)));
          break;

        case "Economic issues":
          CsvLine.untilEmpty(iter,
            line -> process.economicIssues.add(ElementaryExchangeRow.read(line)));
          break;

        case "Input parameters":
          CsvLine.untilEmpty(iter,
            line -> process.inputParameters.add(InputParameterRow.read(line)));
          break;

        case "Calculated parameters":
          CsvLine.untilEmpty(iter,
            line -> process.calculatedParameters.add(CalculatedParameterRow.read(line)));
          break;
      }
    }

    return process;

  }

  @Override
  public void write(CsvBuffer buffer) {

    buffer.putString("Process").writeln()
      .writeln();

    // PlatformId
    if (platformId != null) {
      buffer.putString("PlatformId").writeln()
        .putString(platformId).writeln()
        .writeln();
    }

    // Category type
    buffer.putString("Category type").writeln();
    buffer.putString(category == null
        ? ProcessCategory.MATERIAL.toString()
        : category.toString())
      .writeln()
      .writeln();

    // Process identifier
    if (identifier != null) {
      buffer.putString("Process identifier").writeln()
        .putString(identifier).writeln()
        .writeln();
    }

    // Process type
    buffer.putString("Type").writeln();
    buffer.putString(processType == null
        ? ProcessType.UNIT_PROCESS.toString()
        : processType.toString())
      .writeln()
      .writeln();

    // Process name
    if (name != null) {
      buffer.putString("Process name").writeln()
        .putString(name).writeln()
        .writeln();
    }

    // Status
    buffer.putString("Status").writeln()
      .putString(status == null
        ? Status.NONE.toString()
        : status.toString())
      .writeln()
      .writeln();

    // Infrastructure
    if (infrastructure != null) {
      buffer.putString("Infrastructure").writeln()
        .putString(infrastructure ? "Yes" : "No").writeln()
        .writeln();
    }

    // Date
    if (date != null) {
      buffer.putString("Date").writeln()
        .putDate(date).writeln()
        .writeln();
    }

    // Record
    if (record != null) {
      buffer.putString("Record").writeln()
        .putString(record).writeln()
        .writeln();
    }

    // Generator
    if (generator != null) {
      buffer.putString("Generator").writeln()
        .putString(generator).writeln()
        .writeln();
    }

    // Literature references
    writeRows(buffer, "Literature references", literatures);

    // Collection method
    if (collectionMethod != null) {
      buffer.putString("Collection method").writeln()
        .putString(collectionMethod).writeln()
        .writeln();
    }

    // Data treatment
    if (dataTreatment != null) {
      buffer.putString("Data treatment").writeln()
        .putString(dataTreatment).writeln()
        .writeln();
    }

    // Verification
    if (verification != null) {
      buffer.putString("Verification").writeln()
        .putString(verification).writeln()
        .writeln();
    }

    // Comment
    if (comment != null) {
      buffer.putString("Comment").writeln()
        .putString(comment).writeln()
        .writeln();
    }

    // Allocation rules
    if (allocationRules != null) {
      buffer.putString("Allocation rules").writeln()
        .putString(allocationRules).writeln()
        .writeln();
    }

    // System description
    writeRow(buffer, "System description", systemDescription);

    // Products
    writeRows(buffer, "Products", products);

    // Waste scenario
    writeRow(buffer, "Waste scenario", wasteScenario);

    // Avoided Products
    writeRows(buffer, "Avoided products", avoidedProducts);

    // Resources
    writeRows(buffer, "Resources", resources);

    // Waste treatment
    writeRow(buffer, "Waste treatment", wasteTreatment);

    // Materials/fuels
    writeRows(buffer, "Materials/fuels", materialsAndFuels);

    // Electricity/heat
    writeRows(buffer, "Electricity/heat", electricityAndHeat);

    // Emissions to air
    writeRows(buffer, "Emissions to air", emissionsToAir);

    // Emissions to water
    writeRows(buffer, "Emissions to water", emissionsToWater);

    // Emissions to soil
    writeRows(buffer, "Emissions to soil", emissionsToSoil);

    // Final waste flows
    writeRows(buffer, "Final waste flows", finalWasteFlows);

    // Non material emissions
    writeRows(buffer, "Non material emissions", nonMaterialEmissions);

    // Social issues
    writeRows(buffer, "Social issues", socialIssues);

    // Economic issues
    writeRows(buffer, "Economic issues", economicIssues);

    // Waste to treatment
    writeRows(buffer, "Waste to treatment", wasteToTreatment);

    // Separated waste
    writeRows(buffer, "Separated waste", separatedWaste);

    // Remaining waste
    writeRows(buffer, "Remaining waste", remainingWaste);

    // Input parameters
    writeRows(buffer, "Input parameters", inputParameters);

    // Calculated parameters
    writeRows(buffer, "Calculated parameters", calculatedParameters);

    // End
    buffer.writeln()
      .putString("End").writeln()
      .writeln();
  }

  public List<ElementaryExchangeRow> exchangesOf(ElementaryFlowType type) {
    if (type == null)
      return Collections.emptyList();
    switch (type) {
      case ECONOMIC_ISSUES:
        return economicIssues;
      case EMISSIONS_TO_AIR:
        return emissionsToAir;
      case EMISSIONS_TO_SOIL:
        return emissionsToSoil;
      case EMISSIONS_TO_WATER:
        return emissionsToWater;
      case FINAL_WASTE_FLOWS:
        return finalWasteFlows;
      case NON_MATERIAL_EMISSIONS:
        return nonMaterialEmissions;
      case RESOURCES:
        return resources;
      case SOCIAL_ISSUES:
        return socialIssues;
      default:
        return Collections.emptyList();
    }
  }

  public List<TechExchangeRow> exchangesOf(ProductType type) {
    if (type == null)
      return Collections.emptyList();
    switch (type) {
      case AVOIDED_PRODUCTS:
        return avoidedProducts;
      case ELECTRICITY_HEAT:
        return electricityAndHeat;
      case MATERIAL_FUELS:
        return materialsAndFuels;
      case WASTE_TO_TREATMENT:
        return wasteToTreatment;
      default:
        return Collections.emptyList();
    }
  }

  private void writeRows(
    CsvBuffer buffer, String header, List<? extends CsvRecord> rows) {
    if (rows.isEmpty())
      return;
    buffer.putString(header).writeln();
    for (var row : rows) {
      row.write(buffer);
    }
    buffer.writeln();
  }

  private <T extends CsvRecord> void writeRow(CsvBuffer buffer, String header, T row) {
    if (row == null) return;
    buffer.putString(header).writeln();
    row.write(buffer);
    buffer.writeln();
  }

}
