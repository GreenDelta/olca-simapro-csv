package org.openlca.simapro.csv.process;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.enums.ProductStageCategory;
import org.openlca.simapro.csv.enums.Status;
import org.openlca.simapro.csv.refdata.CalculatedParameterRow;
import org.openlca.simapro.csv.refdata.InputParameterRow;

public class ProductStageBlock implements CsvBlock {

  private ProductStageCategory category;
  private Status status;

  private TechExchangeRow assembly;
  private TechExchangeRow referenceAssembly;
  private TechExchangeRow wasteOrDisposalScenario;

  private final List<ProductStageOutputRow> products = new ArrayList<>();
  private final List<TechExchangeRow> materialsAndAssemblies = new ArrayList<>();
  private final List<TechExchangeRow> processes = new ArrayList<>();
  private final List<TechExchangeRow> wasteScenarios = new ArrayList<>();
  private final List<TechExchangeRow> disassemblies = new ArrayList<>();
  private final List<TechExchangeRow> disposalScenarios = new ArrayList<>();
  private final List<TechExchangeRow> reuses = new ArrayList<>();
  private final List<TechExchangeRow> additionalLifeCycles = new ArrayList<>();

  private final List<InputParameterRow> inputParameters = new ArrayList<>();
  private final List<CalculatedParameterRow> calculatedParameters = new ArrayList<>();

  public ProductStageCategory category() {
    return category;
  }

  public ProductStageBlock category(ProductStageCategory category) {
    this.category = category;
    return this;
  }

  public Status status() { return status; }

  public ProductStageBlock status(Status status) {
    this.status = status;
    return this;
  }

  public TechExchangeRow referenceAssembly() {
    return referenceAssembly;
  }

  public ProductStageBlock referenceAssembly(TechExchangeRow referenceAssembly) {
    this.referenceAssembly = referenceAssembly;
    return this;
  }

  public TechExchangeRow assembly() {
    return assembly;
  }

  public ProductStageBlock assembly(TechExchangeRow assembly) {
    this.assembly = assembly;
    return this;
  }

  public TechExchangeRow wasteOrDisposalScenario() {
    return wasteOrDisposalScenario;
  }

  public ProductStageBlock wasteOrDisposalScenario(
    TechExchangeRow wasteOrDisposalScenario) {
    this.wasteOrDisposalScenario = wasteOrDisposalScenario;
    return this;
  }

  public List<ProductStageOutputRow> products() {
    return products;
  }

  public List<TechExchangeRow> materialsAndAssemblies() {
    return materialsAndAssemblies;
  }

  public List<TechExchangeRow> processes() {
    return processes;
  }

  public List<TechExchangeRow> wasteScenarios() {
    return wasteScenarios;
  }

  public List<TechExchangeRow> disassemblies() {
    return disassemblies;
  }

  public List<TechExchangeRow> disposalScenarios() {
    return disposalScenarios;
  }

  public List<TechExchangeRow> reuses() {
    return reuses;
  }

  public List<InputParameterRow> inputParameters() {
    return inputParameters;
  }

  public List<CalculatedParameterRow> calculatedParameters() {
    return calculatedParameters;
  }

  public List<TechExchangeRow> additionalLifeCycles() {
    return additionalLifeCycles;
  }

  public static ProductStageBlock read(Iterable<CsvLine> lines) {

    var iter = lines.iterator();
    var block = new ProductStageBlock();

    // utility functions
    Supplier<String> nextFirst = () -> CsvLine.nextOf(iter)
      .map(CsvLine::first)
      .orElse("");
    Consumer<Consumer<TechExchangeRow>> nextTechExchange = setter ->
      CsvLine.nextOf(iter).ifPresent(nextLine -> {
        if (nextLine.isEmpty())
          return;
        var row = TechExchangeRow.read(nextLine);
        setter.accept(row);
      });
    Consumer<List<TechExchangeRow>> nextTechExchanges = list ->
      CsvLine.untilEmpty(iter, nextLine -> {
        var row = TechExchangeRow.read(nextLine);
        list.add(row);
      });

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

        case "Category type":
          var category = ProductStageCategory.of(nextFirst.get());
          block.category(category);
          break;

        case "Status":
          var status = Status.of(nextFirst.get());
          block.status(status);
          break;

        case "Products":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = ProductStageOutputRow.read(nextLine);
            block.products.add(row);
          });
          break;

        case "Assembly":
          nextTechExchange.accept(block::assembly);
          break;

        case "Waste/Disposal scenario":
          nextTechExchange.accept(block::wasteOrDisposalScenario);
          break;

        case "Reference assembly":
          nextTechExchange.accept(block::referenceAssembly);
          break;

        case "Materials/assemblies":
          nextTechExchanges.accept(block.materialsAndAssemblies);
          break;

        case "Processes":
          nextTechExchanges.accept(block.processes);
          break;

        case "Disposal scenarios":
          nextTechExchanges.accept(block.disposalScenarios);
          break;

        case "Waste scenarios":
          nextTechExchanges.accept(block.wasteScenarios);
          break;

        case "Disassemblies":
          nextTechExchanges.accept(block.disassemblies);
          break;

        case "Reuses":
          nextTechExchanges.accept(block.reuses);
          break;

        case "Additional life cycles":
          nextTechExchanges.accept(block.additionalLifeCycles);
          break;

        case "Input parameters":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = InputParameterRow.read(nextLine);
            block.inputParameters.add(row);
          });
          break;

        case "Calculated parameters":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = CalculatedParameterRow.read(nextLine);
            block.calculatedParameters.add(row);
          });
          break;
      }
    }
    return block;
  }

  @Override
  public void write(CsvBuffer buffer) {

    buffer.putString("Product stage").writeln()
      .writeln();

    // Category type
    buffer.putString("Category type").writeln();
    buffer.putString(category == null
        ? ProductStageCategory.ASSEMBLY.toString()
        : category.toString())
      .writeln()
      .writeln();

    // Status
    buffer.putString("Status").writeln()
      .putString(status == null
        ? Status.NONE.toString()
        : status.toString())
      .writeln()
      .writeln();

    // Products
    writeRows(buffer, "Products", products);

    // Assembly
    if (assembly != null) {
      buffer.putString("Assembly").writeln()
        .putRecord(assembly)
        .writeln();
    }

    // Reference assembly
    if (referenceAssembly != null) {
      buffer.putString("Reference assembly").writeln()
        .putRecord(referenceAssembly)
        .writeln();
    }

    // Materials/assemblies
    writeRows(buffer, "Materials/assemblies", materialsAndAssemblies);

    // Processes
    writeRows(buffer, "Processes", processes);

    // Waste/Disposal scenario
    if (wasteOrDisposalScenario != null) {
      buffer.putString("Waste/Disposal scenario").writeln()
        .putRecord(wasteOrDisposalScenario)
        .writeln();
    }

    // Additional life cycles
    writeRows(buffer, "Additional life cycles", additionalLifeCycles);

    // Disposal scenarios
    writeRows(buffer, "Disposal scenarios", disposalScenarios);

    // Waste scenarios
    writeRows(buffer, "Waste scenarios", wasteScenarios);

    // Disassemblies
    writeRows(buffer, "Disassemblies", disassemblies);

    // Reuses
    writeRows(buffer, "Reuses", reuses);

    // Input parameters
    writeRows(buffer, "Input parameters", inputParameters);

    // Calculated parameters
    writeRows(buffer, "Calculated parameters", calculatedParameters);

    // End
    buffer.writeln()
      .putString("End").writeln()
      .writeln();
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

}
