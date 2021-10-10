package org.openlca.simapro.csv;

import java.util.Objects;

import org.apache.commons.csv.CSVRecord;

final class CsvLine {

	private final CSVRecord csv;
	private final char decimalSeparator;

	private CsvLine(CSVRecord csv, CsvHeader header) {
		this.csv = Objects.requireNonNull(csv);
		this.decimalSeparator = header.decimalSeparator();
	}

  static CsvLine of(CSVRecord csv, CsvHeader header) {
    return new CsvLine(csv, header);
  }



	String getString(int pos) {
    if (pos < 0 || pos >= csv.size())
      return "";
    return csv.get(pos).replace((char) 127, '\n');
	}

	double getDouble(int pos) {
		var str = getString(pos);
		return str.length() == 0
				? 0
				: Double.parseDouble(decimalPoint(str));
	}

	Numeric getNumeric(int pos) {
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

	String getFormula(int pos) {
		return decimalPoint(getString(pos));
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
}
