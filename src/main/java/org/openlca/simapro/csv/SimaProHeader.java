package org.openlca.simapro.csv;

import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

import org.apache.commons.csv.CSVParser;

public class SimaProHeader {

  private String version;
  private String date;
  private String time;
  private String project;
  private String formatVersion;
  private char csvSeparator;
  private char decimalSeparator;
  private char dateSeparator;
  private String shortDateFormat;

  public String version() {
    return version;
  }

  public SimaProHeader version(String version) {
    this.version = version;
    return this;
  }

  public String date() {
    return date;
  }

  public SimaProHeader date(String date) {
    this.date = date;
    return this;
  }

  public String time() {
    return time;
  }

  public SimaProHeader time(String time) {
    this.time = time;
    return this;
  }

  public String project() {
    return project;
  }

  public SimaProHeader project(String project) {
    this.project = project;
    return this;
  }

  public String formatVersion() {
    return formatVersion;
  }

  public SimaProHeader formatVersion(String formatVersion) {
    this.formatVersion = formatVersion;
    return this;
  }

  public char csvSeparator() {
    return csvSeparator;
  }

  public SimaProHeader csvSeparator(char csvSeparator) {
    this.csvSeparator = csvSeparator;
    return this;
  }

  public char decimalSeparator() {
    return decimalSeparator;
  }

  public SimaProHeader decimalSeparator(char decimalSeparator) {
    this.decimalSeparator = decimalSeparator;
    return this;
  }

  public char dateSeparator() {
    return dateSeparator;
  }

  public SimaProHeader dateSeparator(char dateSeparator) {
    this.dateSeparator = dateSeparator;
    return this;
  }

  public String shortDateFormat() {
    return shortDateFormat;
  }

  public SimaProHeader shortDateFormat(String shortDateFormat) {
    this.shortDateFormat = shortDateFormat;
    return this;
  }

  public static SimaProHeader readFrom(Reader reader) {
    var header = new SimaProHeader();
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

        var separator = match(s, "CSV separator: ");
        if (separator.isPresent()) {
          var del = separator.get().toLowerCase();

          header.csvSeparator(
            switch (del) {
              case "semicolon" -> ';';
              case "comma" -> ',';
              default -> ';';
            });
          continue;
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

}
