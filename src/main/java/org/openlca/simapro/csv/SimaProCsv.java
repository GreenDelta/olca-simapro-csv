package org.openlca.simapro.csv;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;

import org.apache.commons.csv.CSVFormat;

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

  public static CSVFormat formatOf(char delimiter) {
    return CSVFormat.Builder.create()
      .setDelimiter(delimiter)
      .setTrim(true)
      .setIgnoreEmptyLines(false)
      .setQuote('"')
      .setEscape('"')
      .setIgnoreSurroundingSpaces(true)
      .build();
  }

  public static Reader readerOf(File file) {
    return readerOf(file, defaultCharset());
  }

  public static Reader readerOf(File file, Charset charset) {
    if (file == null)
      return null;

    return null;
  }

  public static Reader readerOf(InputStream stream, Charset charset) {
    return null;
  }

}
