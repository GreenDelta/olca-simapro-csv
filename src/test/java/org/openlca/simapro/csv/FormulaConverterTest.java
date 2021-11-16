package org.openlca.simapro.csv;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormulaConverterTest {

  @Test
  public void testPointToComma() {
    var converter = FormulaConverter.of(new CsvHeader().decimalSeparator('.'))
      .decimalSeparator(',')
      .parameterSeparator(';');
    assertEquals("fun(1,2; 3,4)", converter.convert("fun(1.2, 3.4)"));
  }

  @Test
  public void testCommaToPoint() {
    var converter = FormulaConverter.of(new CsvHeader().decimalSeparator(','))
      .decimalSeparator('.')
      .parameterSeparator(',');
    assertEquals("fun(1.2, 3.4)", converter.convert("fun(1,2; 3,4)"));
  }

  @Test
  public void testKeepSemicolon() {
    var converter = FormulaConverter.of(new CsvHeader().decimalSeparator(','))
      .decimalSeparator('.')
      .parameterSeparator(';');
    assertEquals("fun(1.2; 3.4)", converter.convert("fun(1,2; 3,4)"));
  }
}
