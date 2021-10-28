package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Supplier;

import org.apache.commons.csv.CSVParser;

class Tests {

  static CsvLine lineOf(String line) {
    try {
      var r = CSVParser.parse(line, Csv.formatOf(';'))
        .iterator()
        .next();
      var h = new CsvHeader()
        .csvSeparator(';')
        .decimalSeparator(',');
      return CsvLine.of(r, h);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static String toCsv(CsvRecord record) {
    if (record == null)
      return "";
    var writer = new StringWriter();
    var buffer = new CsvBuffer(writer, new CsvHeader());
    record.write(buffer);
    buffer.writeln();
    writer.flush();
    return writer.toString().trim();
  }

  /**
   * Reads the test file with the given name from the test resources into a CSV
   * data set, writes that CSV data set to a temporary file, reads it again from
   * that file, and returns that final data set. With these actions, we test if
   * things are correctly read and written using different encodings and IO
   * targets. You just need to check that the expected elements are in the
   * returned CSV data set.
   */
  static CsvDataSet testFile(String name) {

    Supplier<Reader> reader = () -> {
      var stream = Tests.class.getResourceAsStream(name);
      return Csv.readerOf(stream, StandardCharsets.UTF_8);
    };

    try {
      CsvHeader header;
      try (var r = reader.get()) {
        header = CsvHeader.readFrom(r);
      }
      CsvDataSet dataSet;
      try (var r = reader.get()) {
        dataSet = CsvDataSet.read(header, r);
      }

      var file = Files.createTempFile("__sp_csv_", ".csv").toFile();
      dataSet.write(file);
      var copy = CsvDataSet.read(file);
      Files.delete(file.toPath());

      return copy;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
