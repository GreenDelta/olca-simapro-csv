package org.openlca.simapro.csv;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CsvLineTest {

  @Test
  public void testGetString() {
    var line =  Tests.lineOf(" a ;\" b \";c");
    for (int i = -1; i < 5; i++) {
      var s = line.getString(i);
      switch (i) {
        case 0:
          assertEquals("a", s);
          break;
        case 1:
          assertEquals("b", s);
          break;
        case 2:
          assertEquals("c", s);
          break;
        default:
          assertEquals("", s);
      }
    }
  }

  @Test
  public void testNumeric() {

    var line = Tests.lineOf(" 42,0 ; sin(42,0) ; ");

    var num1 = line.getNumeric(0);
    assertFalse(num1.hasFormula());
    assertEquals(42.0, num1.value(), 1e-10);

    var num2 = line.getNumeric(1);
    assertTrue(num2.hasFormula());
    assertEquals("sin(42,0)", num2.formula());

    var num3 = line.getNumeric(2);
    assertFalse(num3.hasFormula());
    assertEquals(0.0, num3.value(), 1e-10);
  }

  @Test
  public void testReadMultiLineStrings() {
    var line = Tests.lineOf(
      "this\u007F has\u007F; multi-line\u007F; \u007Fstrings");
    assertEquals("this\n has\n", line.getString(0));
    assertEquals("multi-line\n", line.getString(1));
    assertEquals("\nstrings", line.getString(2));
  }

}
