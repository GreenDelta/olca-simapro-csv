package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Objects;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

class CsvScanner implements Iterable<CsvLine> {

  private final CsvHeader header;
  private final CSVParser parser;

  CsvScanner(CsvHeader header, Reader reader) {
    this.header = Objects.requireNonNull(header);
    var format = SimaProCsv.formatOf(header.csvSeparator());
    try {
      this.parser = CSVParser.parse(reader, format);
    } catch (IOException e) {
      throw new RuntimeException("failed to create CsvScanner", e);
    }
  }

  @Override
  public Iterator<CsvLine> iterator() {
    return new Iter(header, parser.iterator());
  }

  private static class Iter implements Iterator<CsvLine> {

    private final CsvHeader header;
    private final Iterator<CSVRecord> records;

    Iter(CsvHeader header, Iterator<CSVRecord> records) {
      this.header = header;
      this.records = records;
    }

    @Override
    public boolean hasNext() {
      return records.hasNext();
    }

    @Override
    public CsvLine next() {
      var record = records.next();
      return CsvLine.of(record, header);
    }
  }
}
