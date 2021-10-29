package org.openlca.simapro.csv.process;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.openlca.simapro.csv.CsvBlock;
import org.openlca.simapro.csv.CsvBuffer;
import org.openlca.simapro.csv.CsvLine;
import org.openlca.simapro.csv.CsvRecord;
import org.openlca.simapro.csv.enums.ProductStageCategory;
import org.openlca.simapro.csv.refdata.CalculatedParameterRow;
import org.openlca.simapro.csv.refdata.InputParameterRow;

public class ProductStageBlock implements CsvBlock, CsvRecord {

  private ProductStageCategory category;
  private String status;
  private TechExchangeRow referenceAssembly;
  private final List<ProductStageOutputRow> products = new ArrayList<>();
  private final List<TechExchangeRow> materialsAndAssemblies = new ArrayList<>();
  private final List<TechExchangeRow> processes = new ArrayList<>();
  private final List<TechExchangeRow> wasteScenarios = new ArrayList<>();
  private final List<TechExchangeRow> disassemblies = new ArrayList<>();
  private final List<TechExchangeRow> disposalScenarios = new ArrayList<>();
  private final List<TechExchangeRow> reuses = new ArrayList<>();
  private final List<InputParameterRow> inputParameters = new ArrayList<>();
  private final List<CalculatedParameterRow> calculatedParameters = new ArrayList<>();

  public ProductStageCategory category() {
    return category;
  }

  public ProductStageBlock category(ProductStageCategory category) {
    this.category = category;
    return this;
  }

  public String status() {
    return status;
  }

  public ProductStageBlock status(String status) {
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

  public static ProductStageBlock read(Iterable<CsvLine> lines) {

    var iter = lines.iterator();
    var block = new ProductStageBlock();
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

        case "Category type":
          var category = ProductStageCategory.of(nextFirst.get());
          block.category(category);
          break;

        case "Status":
          block.status(nextFirst.get());
          break;

        case "Products":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = ProductStageOutputRow.read(nextLine);
            block.products.add(row);
          });
          break;

        case "Reference assembly":
          CsvLine.nextOf(iter).ifPresent(nextLine -> {
            if (nextLine.isEmpty())
              return;
            var row = TechExchangeRow.read(nextLine);
            block.referenceAssembly(row);
          });
          break;

        case "Materials/assemblies":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = TechExchangeRow.read(nextLine);
            block.materialsAndAssemblies.add(row);
          });
          break;

        case "Processes":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = TechExchangeRow.read(nextLine);
            block.processes.add(row);
          });
          break;

        case "Disposal scenarios":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = TechExchangeRow.read(nextLine);
            block.disposalScenarios.add(row);
          });
          break;

        case "Waste scenarios":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = TechExchangeRow.read(nextLine);
            block.wasteScenarios.add(row);
          });
          break;

        case "Disassemblies":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = TechExchangeRow.read(nextLine);
            block.disassemblies.add(row);
          });
          break;

        case "Reuses":
          CsvLine.untilEmpty(iter, nextLine -> {
            var row = TechExchangeRow.read(nextLine);
            block.reuses.add(row);
          });
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
      .putString(status).writeln()
      .writeln();

    // Products
    writeRows(buffer, "Products", products);

    // Assembly
    // TODO: Assembly is a section in product stages of type `life cycle`
    // buffer.putString("Assembly").writeln();

    // Reference assembly
    if (referenceAssembly != null) {
      buffer.putString("Reference assembly").writeln();
      referenceAssembly.write(buffer);
      buffer.writeln();
    }

    // Materials/assemblies
    writeRows(buffer, "Materials/assemblies", materialsAndAssemblies);

    // Processes
    writeRows(buffer, "Processes", processes);

    // Waste/Disposal scenario
    // TODO: Waste/Disposal scenario is a section in product stages of type `life cycle`
    // buffer.putString("Waste/Disposal scenario").writeln();

    // Additional life cycles
    // TODO: Additional life cycles is a section in product stages of type `life cycle`
    // writeRows(buffer, "Additional life cycles", additionalLifeCycles);

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
