package org.openlca.simapro.csv.process;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.enums.ProcessCategory;
import org.openlca.simapro.csv.enums.ProcessType;
import org.openlca.simapro.csv.refdata.CalculatedParameterRow;
import org.openlca.simapro.csv.refdata.InputParameterRow;

public class ProcessRecord {

  private ProcessCategory category;
  private String identifier;
  private ProcessType processType;
  private String name;
  private String status;
  private String time;
  private String geography;
  private String technology;
  private String representativeness;
  private String allocation;
  private String substitution;
  private String cutoff;
  private String capitalGoods;
  private String boundaryWithNature;
  private Boolean infrastructure;
  private String date;
  private String record;
  private String generator;
  private String collectionMethod;
  private String verification;
  private String comment;
  private String allocationRules;
  private String dataTreatment;

  private SystemDescriptionRow systemDescription;
  private WasteTreatmentRow wasteTreatment;

  private final List<LiteratureRow> literatures = new ArrayList<>();
  private final List<ProductOutputRow> products = new ArrayList<>();

  private final List<ProductExchangeRow> avoidedProducts = new ArrayList<>();
  private final List<ProductExchangeRow> materialsAndFuels = new ArrayList<>();
  private final List<ProductExchangeRow> electricityAndHeat = new ArrayList<>();
  private final List<ProductExchangeRow> wasteToTreatment = new ArrayList<>();

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

  public ProcessCategory category() {
    return category;
  }

  public ProcessRecord category(ProcessCategory category) {
    this.category = category;
    return this;
  }

  public String identifier() {
    return identifier;
  }

  public ProcessRecord identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  public ProcessType processType() {
    return processType;
  }

  public ProcessRecord processType(ProcessType processType) {
    this.processType = processType;
    return this;
  }

  public String name() {
    return name;
  }

  public ProcessRecord name(String name) {
    this.name = name;
    return this;
  }

  public String status() {
    return status;
  }

  public ProcessRecord status(String status) {
    this.status = status;
    return this;
  }

  public String time() {
    return time;
  }

  public ProcessRecord time(String time) {
    this.time = time;
    return this;
  }

  public String geography() {
    return geography;
  }

  public ProcessRecord geography(String geography) {
    this.geography = geography;
    return this;
  }

  public String technology() {
    return technology;
  }

  public ProcessRecord technology(String technology) {
    this.technology = technology;
    return this;
  }

  public String representativeness() {
    return representativeness;
  }

  public ProcessRecord representativeness(String representativeness) {
    this.representativeness = representativeness;
    return this;
  }

  public String allocation() {
    return allocation;
  }

  public ProcessRecord allocation(String allocation) {
    this.allocation = allocation;
    return this;
  }

  public String substitution() {
    return substitution;
  }

  public ProcessRecord substitution(String substitution) {
    this.substitution = substitution;
    return this;
  }

  public String cutoff() {
    return cutoff;
  }

  public ProcessRecord cutoff(String cutoff) {
    this.cutoff = cutoff;
    return this;
  }

  public String capitalGoods() {
    return capitalGoods;
  }

  public ProcessRecord capitalGoods(String capitalGoods) {
    this.capitalGoods = capitalGoods;
    return this;
  }

  public String boundaryWithNature() {
    return boundaryWithNature;
  }

  public ProcessRecord boundaryWithNature(String boundaryWithNature) {
    this.boundaryWithNature = boundaryWithNature;
    return this;
  }

  public Boolean infrastructure() {
    return infrastructure;
  }

  public ProcessRecord infrastructure(Boolean infrastructure) {
    this.infrastructure = infrastructure;
    return this;
  }

  public String date() {
    return date;
  }

  public ProcessRecord date(String date) {
    this.date = date;
    return this;
  }

  public String record() {
    return record;
  }

  public ProcessRecord record(String record) {
    this.record = record;
    return this;
  }

  public String generator() {
    return generator;
  }

  public ProcessRecord generator(String generator) {
    this.generator = generator;
    return this;
  }

  public String collectionMethod() {
    return collectionMethod;
  }

  public ProcessRecord collectionMethod(String collectionMethod) {
    this.collectionMethod = collectionMethod;
    return this;
  }

  public String verification() {
    return verification;
  }

  public ProcessRecord verification(String verification) {
    this.verification = verification;
    return this;
  }

  public String comment() {
    return comment;
  }

  public ProcessRecord comment(String comment) {
    this.comment = comment;
    return this;
  }

  public String allocationRules() {
    return allocationRules;
  }

  public ProcessRecord allocationRules(String allocationRules) {
    this.allocationRules = allocationRules;
    return this;
  }

  public String dataTreatment() {
    return dataTreatment;
  }

  public ProcessRecord dataTreatment(String dataTreatment) {
    this.dataTreatment = dataTreatment;
    return this;
  }

  public SystemDescriptionRow systemDescription() {
    return systemDescription;
  }

  public ProcessRecord systemDescription(SystemDescriptionRow systemDescription) {
    this.systemDescription = systemDescription;
    return this;
  }

  public WasteTreatmentRow wasteTreatment() {
    return wasteTreatment;
  }

  public ProcessRecord wasteTreatment(WasteTreatmentRow wasteTreatment) {
    this.wasteTreatment = wasteTreatment;
    return this;
  }

  public List<LiteratureRow> literatures() {
    return literatures;
  }

  public List<ProductOutputRow> products() {
    return products;
  }

  public List<ProductExchangeRow> avoidedProducts() {
    return avoidedProducts;
  }

  public List<ProductExchangeRow> materialsAndFuels() {
    return materialsAndFuels;
  }

  public List<ProductExchangeRow> electricityAndHeat() {
    return electricityAndHeat;
  }

  public List<ProductExchangeRow> wasteToTreatment() {
    return wasteToTreatment;
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

  public static ProcessRecord read(Iterable<CsvLine> lines) {
    var iter = lines.iterator();
    var process = new ProcessRecord();

    // utility functions
    Supplier<String> nextFirst = () -> {
      if (!iter.hasNext())
        return "";
      var next = iter.next();
      return next.first();
    };
    Supplier<CsvLine> next = () -> iter.hasNext()
      ? iter.next()
      : null;
    Consumer<Consumer<CsvLine>> nextRows = fn -> {
      while (iter.hasNext()) {
        var n = iter.next();
        if(n.isEmpty())
          break;
        fn.accept(n);
      }
    };

    while (iter.hasNext()) {

      var header = nextFirst.get();
      if (header.equalsIgnoreCase("End"))
        break;
      if (header.isEmpty()) {
        continue;
      }

      switch (header) {

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
          process.status(nextFirst.get());
          break;

        case "Time period":
          process.time(nextFirst.get());
          break;

        case "Geography":
          process.geography(nextFirst.get());
          break;

        case "Technology":
          process.technology(nextFirst.get());
          break;

        case "Representativeness":
          process.representativeness(nextFirst.get());
          break;

        case "Multiple output allocation":
          process.allocation(nextFirst.get());
          break;

        case "Substitution allocation":
          process.substitution(nextFirst.get());
          break;

        case "Cut off rules":
          process.cutoff(nextFirst.get());
          break;

        case "Capital goods":
          process.capitalGoods(nextFirst.get());
          break;

        case "Boundary with nature":
          process.boundaryWithNature(nextFirst.get());
          break;

        case "Infrastructure":
          var infrastructureVal = next.get();
          if (infrastructureVal != null) {
            process.infrastructure(infrastructureVal.getBoolean(0));
          }
          break;

        case "Date":
          process.date(nextFirst.get());
          break;

        case "Record":
          process.record(nextFirst.get());
          break;

        case "Generator":
          process.generator(nextFirst.get());
          break;

        case "Literature references":
          nextRows.accept(n -> process.literatures.add(LiteratureRow.read(n)));
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
          var systemRow = next.get();
          if (systemRow != null) {
            process.systemDescription(SystemDescriptionRow.read(systemRow));
          }
          break;

        case "Data treatment":
          process.dataTreatment(nextFirst.get());
          break;

        case "Products":
          nextRows.accept(line
            -> process.products().add(ProductOutputRow.read(line)));
          break;

        case "Waste treatment":

          process.wasteTreatment(nextFirst.get());
          break;

        case "Avoided products":
          // TODO: inner loop for avoidedProducts
          break;

        case "Materials/fuels":
          // TODO: inner loop for materialsAndFuels
          break;

        case "Electricity/heat":
          // TODO: inner loop for electricityAndHeat
          break;

        case "Waste to treatment":
          // TODO: inner loop for wasteToTreatment
          break;

        case "Resources":
          // TODO: inner loop for resources
          break;

        case "Emissions to air":
          // TODO: inner loop for emissionsToAir
          break;

        case "Emissions to water":
          // TODO: inner loop for emissionsToWater
          break;

        case "Emissions to soil":
          // TODO: inner loop for emissionsToSoil
          break;

        case "Final waste flows":
          // TODO: inner loop for finalWasteFlows
          break;

        case "Non material emissions":
          // TODO: inner loop for nonMaterialEmissions
          break;

        case "Social issues":
          // TODO: inner loop for socialIssues
          break;

        case "Economic issues":
          // TODO: inner loop for economicIssues
          break;

        case "Input parameters":
          // TODO: inner loop for inputParameters
          break;

        case "Calculated parameters":
          // TODO: inner loop for calculatedParameters
          break;
      }

    }

    return process;

  }
}
