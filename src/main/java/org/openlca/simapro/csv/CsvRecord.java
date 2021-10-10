package org.openlca.simapro.csv;

public interface CsvRecord {

  /**
   * Writes this record to the given buffer. Dependening on
   * the record type, this could be a line, a part of a line,
   * or multiple lines.
   */
  void write(CsvBuffer buffer);

}
