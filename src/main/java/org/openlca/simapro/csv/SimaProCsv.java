package org.openlca.simapro.csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.openlca.simapro.csv.enums.ElementaryFlowType;
import org.openlca.simapro.csv.method.ImpactMethodBlock;
import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.process.ProductStageBlock;
import org.openlca.simapro.csv.refdata.CalculatedParameterBlock;
import org.openlca.simapro.csv.refdata.CalculatedParameterRow;
import org.openlca.simapro.csv.refdata.ElementaryFlowBlock;
import org.openlca.simapro.csv.refdata.ElementaryFlowRow;
import org.openlca.simapro.csv.refdata.InputParameterBlock;
import org.openlca.simapro.csv.refdata.InputParameterRow;
import org.openlca.simapro.csv.refdata.LiteratureReferenceBlock;
import org.openlca.simapro.csv.refdata.QuantityBlock;
import org.openlca.simapro.csv.refdata.QuantityRow;
import org.openlca.simapro.csv.refdata.SystemDescriptionBlock;
import org.openlca.simapro.csv.refdata.UnitBlock;
import org.openlca.simapro.csv.refdata.UnitRow;

public final class SimaProCsv {

  private SimaProCsv() {
  }

  /**
   * The default character set of SimaPro CSV is Windows 1252.
   *
   * @return the Windows 1252 character set
   */
  public static Charset defaultCharset() {
    return Charset.forName("windows-1252");
  }

  static CSVFormat formatOf(char delimiter) {
    return CSVFormat.Builder.create()
      .setDelimiter(delimiter)
      .setTrim(true)
      .setIgnoreEmptyLines(false)
      .setQuote('"')
      .setIgnoreSurroundingSpaces(true)
      .build();
  }

  public static Reader readerOf(File file) {
    return readerOf(file, defaultCharset());
  }

  public static Reader readerOf(File file, Charset charset) {
    if (file == null)
      return null;
    try {
      var stream = new FileInputStream(file);
      return readerOf(stream, charset);
    } catch (IOException e) {
      throw new RuntimeException("failed to read file: " + file, e);
    }
  }

  public static Reader readerOf(InputStream stream, Charset charset) {
    if (stream == null)
      return null;
    var cs = charset == null
      ? defaultCharset()
      : charset;
    return new InputStreamReader(stream, cs);
  }

  public static void read(File file, Consumer<CsvBlock> fn) {
    try (var reader = readerOf(file)) {
      read(reader, fn);
    } catch (IOException e) {
      throw new RuntimeException("failed to read blocks from file: " + file, e);
    }
  }

  public static void read(Reader reader, Consumer<CsvBlock> fn) {
    var buffer = reader instanceof BufferedReader
      ? (BufferedReader) reader
      : new BufferedReader(reader);
    try {
      // read the SimaPro header, a typical header size is ~500 bytes; we set
      // a read-ahead limit to 2k, note that a larger limit could allocate a
      // new buffer of that size if the input size is smaller than that limit
      buffer.mark(2 * 1024);
      var header = CsvHeader.readFrom(buffer);
      buffer.reset();
      read(header, buffer, fn);
    } catch (IOException e) {
      throw new RuntimeException("failed to read blocks from reader", e);
    }
  }

  public static void read(
    CsvHeader header, Reader reader, Consumer<CsvBlock> fn
  ) {
    var iter = CsvLine.iter(header, reader);
    for (var line : iter) {

      if (firstEq("Project calculated parameters", line)) {
        var block = CalculatedParameterBlock.readProjectParameters(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Database calculated parameters", line)) {
        var block = CalculatedParameterBlock.readDatabaseParameters(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Method", line)) {
        var block = ImpactMethodBlock.read(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Project input parameters", line)) {
        var block = InputParameterBlock.readProjectParameters(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Database input parameters", line)) {
        var block = InputParameterBlock.readDatabaseParameters(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Process", line)) {
        var block = ProcessBlock.read(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Product stage", line)) {
        var block = ProductStageBlock.read(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Quantities", line)) {
        var block = QuantityBlock.read(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("System description", line)) {
        var block = SystemDescriptionBlock.read(iter);
        fn.accept(block);
        continue;
      }

      if (firstEq("Units", line)) {
        var block = UnitBlock.read(iter);
        fn.accept(block);
        continue;
      }

      var type = ElementaryFlowType.of(line.first());
      if (type != null) {
        var block = ElementaryFlowBlock.read(type, iter);
        fn.accept(block);
      }
    }
  }

  public static CsvDataSet read(File file) {
    var header = CsvHeader.readFrom(file);
    try (var reader = SimaProCsv.readerOf(file)) {
      return read(header, reader);
    } catch (IOException e) {
      throw new RuntimeException("failed to read file: " + file, e);
    }
  }

  public static CsvDataSet read(CsvHeader header, Reader reader) {
    var dataSet = new CsvDataSet(header);
    var iter = CsvLine.iter(header, reader);
    var lines = iter.iterator();
    while (lines.hasNext()) {
      var line = lines.next();

      if (firstEq("Product stage", line)) {
        var block = ProductStageBlock.read(iter);
        dataSet.productStages().add(block);
        continue;
      }

      if (firstEq("Process", line)) {
        var block = ProcessBlock.read(iter);
        dataSet.processes().add(block);
        continue;
      }

      if (firstEq("System description", line)) {
        var block = SystemDescriptionBlock.read(iter);
        dataSet.systemDescriptions().add(block);
        continue;
      }

      if (firstEq("Method", line)) {
        var block = ImpactMethodBlock.read(iter);
        dataSet.methods().add(block);
        continue;
      }

      if (firstEq("Quantities", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = QuantityRow.read(nextLine);
          dataSet.quantities().add(row);
        });
        continue;
      }

      if (firstEq("Units", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = UnitRow.read(nextLine);
          dataSet.units().add(row);
        });
        continue;
      }

      if (firstEq("Raw materials", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.rawMaterials().add(row);
        });
        continue;
      }

      if (firstEq("Airborne emissions", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.airborneEmissions().add(row);
        });
        continue;
      }

      if (firstEq("Waterborne emissions", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.waterborneEmissions().add(row);
        });
        continue;
      }

      if (firstEq("Final waste flows", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.finalWasteFlows().add(row);
        });
        continue;
      }

      if (firstEq("Emissions to soil", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.emissionsToSoil().add(row);
        });
        continue;
      }

      if (firstEq("Non material emissions", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.nonMaterialEmissions().add(row);
        });
        continue;
      }

      if (firstEq("Social issues", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.socialIssues().add(row);
        });
        continue;
      }

      if (firstEq("Economic issues", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = ElementaryFlowRow.read(nextLine);
          dataSet.economicIssues().add(row);
        });
        continue;
      }

      if (firstEq("Database input parameters", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = InputParameterRow.read(nextLine);
          dataSet.databaseInputParameters().add(row);
        });
        continue;
      }

      if (firstEq("Database calculated parameters", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = CalculatedParameterRow.read(nextLine);
          dataSet.databaseCalculatedParameters().add(row);
        });
        continue;
      }

      if (firstEq("Project input parameters", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = InputParameterRow.read(nextLine);
          dataSet.projectInputParameters().add(row);
        });
        continue;
      }

      if (firstEq("Project calculated parameters", line)) {
        CsvLine.untilEmpty(lines, nextLine -> {
          var row = CalculatedParameterRow.read(nextLine);
          dataSet.projectCalculatedParameters().add(row);
        });
        continue;
      }

      if (firstEq("Literature reference", line)) {
        var block = LiteratureReferenceBlock.read(iter);
        dataSet.literatureReferences().add(block);
      }
    }
    return dataSet;
  }

  private static boolean firstEq(String literal, CsvLine line) {
    return literal.equalsIgnoreCase(line.first());
  }
}
