package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.process.ElementaryExchangeRow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ElementaryExchangeRowTest {

  @Test
  public void testRead() {
    var line = Tests.lineOf(
      "Methane, fossil;high. pop.;kg;0,00011855;Lognormal;"
      + "2,3802;0;0;(4,5,5,5,5,na)Estimation;3d39c98e-46d4-11ec-81d3-0242ac130003");

    var e = ElementaryExchangeRow.read(line);
    assertEquals("Methane, fossil", e.name());
    assertEquals("high. pop.", e.subCompartment());
    assertEquals("kg", e.unit());
    assertEquals(0.00011855, e.amount().value(), 1e-10);
    assertTrue(e.uncertainty().isLogNormal());
    assertEquals(2.3802, e.uncertainty().getAsLogNormal().xsd(), 1e-10);
    assertEquals("(4,5,5,5,5,na)\nEstimation\n", e.comment());
    assertEquals("3d39c98e-46d4-11ec-81d3-0242ac130003", e.platformId());
  }

  @Test
  public void testWrite() {
    var e = new ElementaryExchangeRow()
      .name("Methane, fossil")
      .subCompartment("high. pop.")
      .unit("kg");
    var csv = Tests.toCsv(e);
    assertTrue(csv.startsWith("Methane, fossil;high. pop.;kg;"));
  }

}
