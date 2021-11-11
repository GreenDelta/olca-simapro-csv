package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.refdata.UnitRow;

import static org.junit.Assert.*;

public class UnitBlockTest {

  @Test
  public void testUnits() {
    var ds = Tests.testDataSet("process.csv");
    assertEquals(34, ds.units().size());

    assertContains(ds, new UnitRow()
      .name("kg")
      .quantity("Mass")
      .conversionFactor(1)
      .referenceUnit("kg"));

    assertContains(ds, new UnitRow()
      .name("oz")
      .quantity("Mass")
      .conversionFactor(0.02834952)
      .referenceUnit("kg"));

    assertContains(ds, new UnitRow()
      .name("Wh")
      .quantity("Energy")
      .conversionFactor(0.0036)
      .referenceUnit("MJ"));
  }

  private void assertContains(CsvDataSet ds, UnitRow expected) {
    boolean found = false;
    for (var unit : ds.units()) {
      if (unit.name().equals(expected.name())) {
        found = true;
        assertEquals(
          expected.referenceUnit(), unit.referenceUnit());
        assertEquals(
          expected.quantity(), unit.quantity());
        assertEquals(
          expected.conversionFactor(), unit.conversionFactor(), 1e-10);
      }
    }
    assertTrue(found);
  }

}
