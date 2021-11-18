package org.openlca.simapro.csv;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVRecord;

public final class CsvLine {

  private final CSVRecord csv;
  private final char decimalSeparator;
  private final SimpleDateFormat dateFormat;

  private CsvLine(CSVRecord csv, CsvHeader header) {
    this.csv = Objects.requireNonNull(csv);
    this.decimalSeparator = header.decimalSeparator();
    this.dateFormat = header.shortDateFormat();
  }

  static CsvLine of(CSVRecord csv, CsvHeader header) {
    return new CsvLine(csv, header);
  }

  public static Iterable<CsvLine> iter(CsvHeader header, Reader reader) {
    return new CsvScanner(header, reader);
  }

  /**
   * Advances the iterator and returns the next line if present.
   */
  public static Optional<CsvLine> nextOf(Iterator<CsvLine> it) {
    return it == null || !it.hasNext()
      ? Optional.empty()
      : Optional.ofNullable(it.next());
  }

  /**
   * Advances the given iterator until it finds a line where the first cell
   * has a string with the given value.
   */
  public static void moveTo(Iterator<CsvLine> it, String section) {
    while (it.hasNext()) {
      var next = it.next();
      if (next.first().equals(section))
        break;
    }
  }

  /**
   * Advances the iterator and returns the string value of the first cell from
   * the next line. If there is no such value, an empty string is returned.
   */
  public static String nextString(Iterator<CsvLine> it) {
    return nextOf(it).map(CsvLine::first).orElse("");
  }

  /**
   * Advances the iterator and returns the boolean value of the first cell from
   * the next line. Returns {@code false}, if there is no such value.
   */
  public static boolean nextBool(Iterator<CsvLine> it) {
    return nextOf(it).map(line -> line.getBoolean(0)).orElse(false);
  }

  /**
   * Reads the next sequence of rows and calls the given function until it
   * finds an empty line or a line with the word {@code End}.
   */
  public static void untilEmpty(Iterator<CsvLine> it, Consumer<CsvLine> fn) {
    if (it == null || fn == null)
      return;
    while (it.hasNext()) {
      var line = it.next();
      if (line.isEmpty() || line.first().equals("End"))
        break;
      fn.accept(line);
    }
  }

  /**
   * Returns the number of cells in this line.
   */
  public int size() {
    return csv.size();
  }

  public String getString(int pos) {
    if (pos < 0 || pos >= csv.size())
      return "";
    return csv.get(pos).replace((char) 127, '\n');
  }

  public double getDouble(int pos) {
    var str = getString(pos);
    return str.length() == 0
      ? 0
      : Double.parseDouble(decimalPoint(str));
  }

  public int getInt(int pos) {
    var str = getString(pos);
    return str.length() == 0
      ? 0
      : Integer.parseInt(str);
  }

  public Numeric getNumeric(int pos) {
    var raw = getString(pos);
    var cleaned = decimalPoint(raw);
    if (cleaned.length() == 0)
      return Numeric.of(0);
    try {
      var number = Double.parseDouble(cleaned);
      return Numeric.of(number);
    } catch (NumberFormatException e) {
      return Numeric.of(raw);
    }
  }

  public Date getDate(int pos) {
    var s = getString(pos);
    if (s.isEmpty())
      return null;
    try {
      return dateFormat.parse(s);
    } catch (Exception e) {
      return null;
    }
  }

  public String getFormula(int pos) {
    return decimalPoint(getString(pos));
  }

  public boolean getBoolean(int pos) {
    var s = getString(pos);
    return s.equalsIgnoreCase("yes");
  }

  private String decimalPoint(String s) {
    if (decimalSeparator == '.')
      return s;
    if (s.length() == 0)
      return "";
    var buffer = new StringBuilder(s.length());
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '.')
        continue;
      if (c == decimalSeparator) {
        buffer.append('.');
        continue;
      }
      buffer.append(c);
    }
    return buffer.toString();
  }

  /**
   * Returns true if this line is empty. It is empty when every cell of this
   * line contains an empty string. Note that in some SimaPro exports empty
   * lines could also be a sequence of separators like {@code ;;;;;;}.
   *
   * @return {@code true} when this line is empty, otherwise {@code false}
   */
  public boolean isEmpty() {
    for (int i = 0; i < csv.size(); i++) {
      var s = csv.get(i);
      if (s.length() > 0)
        return false;
    }
    return true;
  }

  public String first() {
    return getString(0);
  }
}
