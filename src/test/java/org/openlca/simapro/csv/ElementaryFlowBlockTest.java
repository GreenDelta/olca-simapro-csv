package org.openlca.simapro.csv;

import org.junit.Test;
import org.openlca.simapro.csv.enums.ElementaryFlowType;

import static org.junit.Assert.*;

public class ElementaryFlowBlockTest {

  @Test
  public void testElementaryFlows() {
    var ds = Tests.testDataSet("process.csv");
    for (var type : ElementaryFlowType.values()) {
      var flows = ds.getElementaryFlows(type);
      assertEquals(1, flows.size());
      var flow = flows.get(0);
      if (type == ElementaryFlowType.RESOURCES) {
        assertEquals("Acids", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
      if (type == ElementaryFlowType.EMISSIONS_TO_AIR) {
        assertEquals("(+-)-Citronellol", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("026489-01-0", flow.cas());
      }
      if (type == ElementaryFlowType.EMISSIONS_TO_WATER) {
        assertEquals("(1r,4r)-(+)-Camphor", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("000464-49-3", flow.cas());
        assertEquals("80db70fc-46d4-11ec-81d3-0242ac130003", flow.platformId());
      }
      if (type == ElementaryFlowType.EMISSIONS_TO_SOIL) {
        assertEquals("1'-Acetoxysafrole", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("034627-78-6", flow.cas());
      }
      if (type == ElementaryFlowType.FINAL_WASTE_FLOWS) {
        assertEquals("Asbestos", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
      if (type == ElementaryFlowType.NON_MATERIAL_EMISSIONS) {
        assertEquals("Noise from bus km", flow.name());
        assertEquals("km", flow.unit());
        assertEquals("", flow.cas());
      }
      if (type == ElementaryFlowType.SOCIAL_ISSUES) {
        assertEquals("venting of argon, crude, liquid", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
      if (type == ElementaryFlowType.ECONOMIC_ISSUES) {
        assertEquals("Sample economic issue", flow.name());
        assertEquals("kg", flow.unit());
        assertEquals("", flow.cas());
      }
    }
  }

}
