package org.openlca.simapro.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.openlca.simapro.csv.process.ProcessBlock;
import org.openlca.simapro.csv.refdata.UnitBlock;

public final class Csv {

  private Csv() {
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

  public static void eachBlock(File file, Consumer<CsvBlock> fn) {
    var header = CsvHeader.readFrom(file);
    try (var reader = readerOf(file)) {
      var iter = CsvLine.iter(header, reader);
      for (var line : iter) {

        if (line.first().equals("Process")) {
          var process = ProcessBlock.read(iter);
          fn.accept(process);
          continue;
        }

        if (line.first().equals("Units")) {
          var units = UnitBlock.read(iter);
          fn.accept(units);
        }

      }

    } catch (IOException e) {
      throw new RuntimeException("failed to read blocks from file: " + file, e);
    }
  }

}
