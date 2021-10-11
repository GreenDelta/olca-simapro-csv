package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Optional;
import java.util.function.Predicate;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CsvScanner {

  private final CsvHeader header;
  private final Iterator<CSVRecord> records;

  private CsvScanner(CsvHeader header, Iterator<CSVRecord> records) {
    this.header = header;
    this.records = records;
  }

  public static CsvScanner of(Reader reader) {
    try {
      var header = CsvHeader.readFrom(reader);
      var format = Csv.formatOf(header.csvSeparator());
      var iterator = CSVParser.parse(reader, format).iterator();
      return new CsvScanner(header, iterator);
    } catch (IOException e) {
      throw new RuntimeException("failed to create CsvScanner", e);
    }
  }

  public Optional<CsvLine> next() {
   if (records.hasNext()) {
      var record = records.next();
      if (record == null)
        return Optional.empty();
      var line = CsvLine.of(record, header);
      return Optional.of(line);
   }
   return Optional.empty();
  }

  public void eachWhile(Predicate<CsvLine> fn) {
    if (fn == null)
      return;
    for (var line = next(); line.isPresent(); line = next()) {
      if (!fn.test(line.get()))
        break;
    }
  }

}
