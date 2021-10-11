package org.openlca.simapro.csv;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVRecord;

public final class CsvLine {

	private final CSVRecord csv;
	private final char decimalSeparator;

	private CsvLine(CSVRecord csv, CsvHeader header) {
		this.csv = Objects.requireNonNull(csv);
		this.decimalSeparator = header.decimalSeparator();
	}

  static CsvLine of(CSVRecord csv, CsvHeader header) {
    return new CsvLine(csv, header);
  }

  public static Iterable<CsvLine> iter(CsvHeader header, Reader reader) {
    return new CsvScanner(header, reader);
  }

  public static Optional<CsvLine> nextOf(Iterator<CsvLine> it) {
    return it == null || !it.hasNext()
      ? Optional.empty()
      : Optional.ofNullable(it.next());
  }

  public static void untilEmpty(Iterator<CsvLine> it, Consumer<CsvLine> fn) {
    if (it == null || fn == null)
      return;
    while (it.hasNext()) {
      var next = it.next();
      if (next.isEmpty())
        break;
      fn.accept(next);
    }
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

  public Numeric getNumeric(int pos) {
		var cleaned = decimalPoint(getString(pos));
		if (cleaned.length() == 0)
			return Numeric.of(0);
		try {
			var number = Double.parseDouble(cleaned);
			return Numeric.of(number);
		} catch (NumberFormatException e) {
			return Numeric.of(cleaned);
		}
	}

  public String getFormula(int pos) {
		return decimalPoint(getString(pos));
	}

  public boolean getBoolean(int pos) {
    var s = getString(pos);
    return s.equals("yes");
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

  public boolean isProcessStart() {
    return first().equals("Process");
  }

  public boolean isUnitsStart() {
    return first().equals("Units");
  }

}
