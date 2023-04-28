package org.openlca.simapro.csv;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.csv.CSVParser;

public class CsvHeader {

  private String version;
  private String date;
  private String time;
  private String project;
  private String formatVersion;
  private SimpleDateFormat shortDateFormat;

  private char csvSeparator = ';';
  private char decimalSeparator = '.';
  private char dateSeparator = '.';

  public String version() {
    return version;
  }

  public CsvHeader version(String version) {
    this.version = version;
    return this;
  }

  public String date() {
    return date;
  }

  public CsvHeader date(String date) {
    this.date = date;
    return this;
  }

  public String time() {
    return time;
  }

  public CsvHeader time(String time) {
    this.time = time;
    return this;
  }

  public String project() {
    return project;
  }

  public CsvHeader project(String project) {
    this.project = project;
    return this;
  }

  public String formatVersion() {
    return formatVersion;
  }

  public CsvHeader formatVersion(String formatVersion) {
    this.formatVersion = formatVersion;
    return this;
  }

  public char csvSeparator() {
    return csvSeparator;
  }

  public CsvHeader csvSeparator(char csvSeparator) {
    this.csvSeparator = csvSeparator;
    return this;
  }

  public char decimalSeparator() {
    return decimalSeparator;
  }

  public CsvHeader decimalSeparator(char decimalSeparator) {
    this.decimalSeparator = decimalSeparator;
    return this;
  }

  public char dateSeparator() {
    return dateSeparator;
  }

  public CsvHeader dateSeparator(char dateSeparator) {
    this.dateSeparator = dateSeparator;
    return this;
  }

  public SimpleDateFormat shortDateFormat() {
    if (shortDateFormat == null) {
      shortDateFormat = new SimpleDateFormat("dd.MM.yyyy");
    }
    return shortDateFormat;
  }

  public CsvHeader shortDateFormat(SimpleDateFormat shortDateFormat) {
    this.shortDateFormat = shortDateFormat;
    return this;
  }

  public static CsvHeader readFrom(File file) {
    try (var reader = SimaProCsv.readerOf(file)) {
      return readFrom(reader);
    } catch (IOException e) {
      throw new RuntimeException("failed to read header from " + file, e);
    }
  }

  public static CsvHeader readFrom(Reader reader) {
    var header = new CsvHeader();
    if (reader == null)
      return header;

    try {
      var parser = CSVParser.parse(reader, SimaProCsv.formatOf(';'));
      for (var record : parser) {
        var s = unbraced(record.get(0));
        if (s.isEmpty())
          break;

        var version = match(s, "SimaPro ");
        if (version.isPresent()) {
          header.version(version.get());
          continue;
        }

        var date = match(s, "Date: ");
        if (date.isPresent()) {
          header.date(date.get());
          continue;
        }

        var time = match(s, "Time: ");
        if (time.isPresent()) {
          header.time(time.get());
          continue;
        }

        var project = match(s, "Project: ");
        if (project.isPresent()) {
          header.project(project.get());
          continue;
        }

        var formatVersion = match(s, "CSV Format version: ");
        if (formatVersion.isPresent()) {
          header.formatVersion(formatVersion.get());
          continue;
        }

        var csvSeparator = match(s, "CSV separator: ");
        if (csvSeparator.isPresent()) {
          var del = csvSeparator.get().toLowerCase();
          header.csvSeparator(del.equals("comma")
            ? ','
            : ';');
          continue;
        }

        var decimalSeparator = match(s, "Decimal separator: ");
        if (decimalSeparator.isPresent()) {
          var sep = decimalSeparator.get();
          header.decimalSeparator(sep.length() == 0
            ? '.'
            : sep.charAt(0));
          continue;
        }

        var dateSeparator = match(s, "Date separator: ");
        if (dateSeparator.isPresent()) {
          var sep = dateSeparator.get();
          header.dateSeparator(sep.length() == 0
            ? '-'
            : sep.charAt(0));
          continue;
        }

        var dateFormat = match(s, "Short date format: ");
        if (dateFormat.isPresent()) {
          try {
            header.shortDateFormat = new SimpleDateFormat(dateFormat.get());
          } catch (Exception ignored) {
          }
        }
      }

      return header;
    } catch (IOException e) {
      throw new RuntimeException("failed to read CSV", e);
    }
  }

  private static String unbraced(String s) {
    int len = s.length();
    if (len == 0)
      return s;
    int start = s.charAt(0) == '{' ? 1 : 0;
    int end = s.charAt(len - 1) == '}'
      ? len - 1
      : len;
    return start == 1 || end < len
      ? s.substring(start, end)
      : s;
  }

  private static Optional<String> match(String s, String prefix) {
    if (!s.startsWith(prefix))
      return Optional.empty();
    var m = s.substring(prefix.length());
    return Optional.of(m.trim());
  }

  public void write(CsvBuffer buffer) {

    // version
    var v = version != null
      ? version
      : "9.1.0.0";
    buffer.putString("{SimaPro " + v + "}").writeln();

    // date
    var dateStr = date == null
      ? shortDateFormat().format(new Date())
      : date;
    buffer.putString("{Date: " + dateStr + "}").writeln();

    // time
    var timeStr = time == null
      ? new SimpleDateFormat("hh:mm:ss").format(new Date())
      : time;
    buffer.putString("{Time: " + timeStr + "}").writeln();

    // project
    if (project != null) {
      buffer.putString("{Project: " + project + "}").writeln();
    }

    // CSV Format version
    var f = formatVersion != null
      ? formatVersion
      : "9.0.0";
    buffer.putString("{CSV Format version: " + f + "}").writeln();

    // CSV separator
    var csvSep = csvSeparator == ';'
      ? "Semicolon"
      : "Comma";
    buffer.putString("{CSV separator: " + csvSep + "}").writeln();

    // Decimal separator
    buffer.putString("{Decimal separator: " + decimalSeparator + "}").writeln();

    // Date separator
    buffer.putString("{Date separator: " + dateSeparator + "}").writeln();

    buffer.putString(
      "{Short date format: " + shortDateFormat().toPattern() + "}").writeln();

    buffer.writeln();
  }

}
