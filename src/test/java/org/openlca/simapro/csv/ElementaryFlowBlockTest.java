package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;

import static org.junit.Assert.*;

public class ElementaryFlowBlockTest {

  @Test
  public void testElementaryFlows() {
    var ds = Tests.testFile("process.csv");
    for (var type : ElementaryFlowType.values()) {
      var flows = ds.getElementaryFlows(type);
      assertEquals(1, flows.size());
      var flow = flows.get(0);
      if (flow.name().equals("Acids")) {
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
      if (flow.name().equals("(+-)-Citronellol")) {
        assertEquals("kg", flow.unit());
        assertEquals("026489-01-0", flow.cas());
      }
      if (flow.name().equals("(1r,4r)-(+)-Camphor")) {
        assertEquals("kg", flow.unit());
        assertEquals("000464-49-3", flow.cas());
      }
      if (flow.name().equals("1'-Acetoxysafrole")) {
        assertEquals("kg", flow.unit());
        assertEquals("034627-78-6", flow.cas());
      }
      if (flow.name().equals("Asbestos")) {
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
      if (flow.name().equals("Noise from bus km")) {
        assertEquals("km", flow.unit());
        assertEquals("", flow.cas());
      }
      if (flow.name().equals("venting of argon, crude, liquid")) {
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
      if (flow.name().equals("Sample economic issue")) {
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
    }
  }

}
